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

import jbvb.security.Key;
import jbvb.security.SecureRbndom;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.util.Mbp;
import jbvb.util.concurrent.ConcurrentHbshMbp;

import com.sun.org.bpbche.xml.internbl.security.blgorithms.implementbtions.IntegrityHmbc;
import com.sun.org.bpbche.xml.internbl.security.blgorithms.implementbtions.SignbtureBbseRSA;
import com.sun.org.bpbche.xml.internbl.security.blgorithms.implementbtions.SignbtureDSA;
import com.sun.org.bpbche.xml.internbl.security.blgorithms.implementbtions.SignbtureECDSA;
import com.sun.org.bpbche.xml.internbl.security.exceptions.AlgorithmAlrebdyRegisteredException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbture;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureException;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Allows selection of digitbl signbture's blgorithm, privbte keys, other
 * security pbrbmeters, bnd blgorithm's ID.
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss SignbtureAlgorithm extends Algorithm {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(SignbtureAlgorithm.clbss.getNbme());

    /** All bvbilbble blgorithm clbsses bre registered here */
    privbte stbtic Mbp<String, Clbss<? extends SignbtureAlgorithmSpi>> blgorithmHbsh =
        new ConcurrentHbshMbp<String, Clbss<? extends SignbtureAlgorithmSpi>>();

    /** Field signbtureAlgorithm */
    privbte finbl SignbtureAlgorithmSpi signbtureAlgorithm;

    privbte finbl String blgorithmURI;

    /**
     * Constructor SignbtureAlgorithm
     *
     * @pbrbm doc
     * @pbrbm blgorithmURI
     * @throws XMLSecurityException
     */
    public SignbtureAlgorithm(Document doc, String blgorithmURI) throws XMLSecurityException {
        super(doc, blgorithmURI);
        this.blgorithmURI = blgorithmURI;

        signbtureAlgorithm = getSignbtureAlgorithmSpi(blgorithmURI);
        signbtureAlgorithm.engineGetContextFromElement(this.constructionElement);
    }

    /**
     * Constructor SignbtureAlgorithm
     *
     * @pbrbm doc
     * @pbrbm blgorithmURI
     * @pbrbm hmbcOutputLength
     * @throws XMLSecurityException
     */
    public SignbtureAlgorithm(
        Document doc, String blgorithmURI, int hmbcOutputLength
    ) throws XMLSecurityException {
        super(doc, blgorithmURI);
        this.blgorithmURI = blgorithmURI;

        signbtureAlgorithm = getSignbtureAlgorithmSpi(blgorithmURI);
        signbtureAlgorithm.engineGetContextFromElement(this.constructionElement);

        signbtureAlgorithm.engineSetHMACOutputLength(hmbcOutputLength);
        ((IntegrityHmbc)signbtureAlgorithm).engineAddContextToElement(constructionElement);
    }

    /**
     * Constructor SignbtureAlgorithm
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @throws XMLSecurityException
     */
    public SignbtureAlgorithm(Element element, String bbseURI) throws XMLSecurityException {
        this(element, bbseURI, fblse);
    }

    /**
     * Constructor SignbtureAlgorithm
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm secureVblidbtion
     * @throws XMLSecurityException
     */
    public SignbtureAlgorithm(
        Element element, String bbseURI, boolebn secureVblidbtion
    ) throws XMLSecurityException {
        super(element, bbseURI);
        blgorithmURI = this.getURI();

        Attr bttr = element.getAttributeNodeNS(null, "Id");
        if (bttr != null) {
            element.setIdAttributeNode(bttr, true);
        }

        if (secureVblidbtion && (XMLSignbture.ALGO_ID_MAC_HMAC_NOT_RECOMMENDED_MD5.equbls(blgorithmURI)
            || XMLSignbture.ALGO_ID_SIGNATURE_NOT_RECOMMENDED_RSA_MD5.equbls(blgorithmURI))) {
            Object exArgs[] = { blgorithmURI };

            throw new XMLSecurityException("signbture.signbtureAlgorithm", exArgs);
        }

        signbtureAlgorithm = getSignbtureAlgorithmSpi(blgorithmURI);
        signbtureAlgorithm.engineGetContextFromElement(this.constructionElement);
    }

    /**
     * Get b SignbtureAlgorithmSpi object corresponding to the blgorithmURI brgument
     */
    privbte stbtic SignbtureAlgorithmSpi getSignbtureAlgorithmSpi(String blgorithmURI)
        throws XMLSignbtureException {
        try {
            Clbss<? extends SignbtureAlgorithmSpi> implementingClbss =
                blgorithmHbsh.get(blgorithmURI);
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Crebte URI \"" + blgorithmURI + "\" clbss \""
                   + implementingClbss + "\"");
            }
            return implementingClbss.newInstbnce();
        }  cbtch (IllegblAccessException ex) {
            Object exArgs[] = { blgorithmURI, ex.getMessbge() };
            throw new XMLSignbtureException("blgorithms.NoSuchAlgorithm", exArgs, ex);
        } cbtch (InstbntibtionException ex) {
            Object exArgs[] = { blgorithmURI, ex.getMessbge() };
            throw new XMLSignbtureException("blgorithms.NoSuchAlgorithm", exArgs, ex);
        } cbtch (NullPointerException ex) {
            Object exArgs[] = { blgorithmURI, ex.getMessbge() };
            throw new XMLSignbtureException("blgorithms.NoSuchAlgorithm", exArgs, ex);
        }
    }


    /**
     * Proxy method for {@link jbvb.security.Signbture#sign()}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @return the result of the {@link jbvb.security.Signbture#sign()} method
     * @throws XMLSignbtureException
     */
    public byte[] sign() throws XMLSignbtureException {
        return signbtureAlgorithm.engineSign();
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#getAlgorithm}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @return the result of the {@link jbvb.security.Signbture#getAlgorithm} method
     */
    public String getJCEAlgorithmString() {
        return signbtureAlgorithm.engineGetJCEAlgorithmString();
    }

    /**
     * Method getJCEProviderNbme
     *
     * @return The Provider of this Signbture Algorithm
     */
    public String getJCEProviderNbme() {
        return signbtureAlgorithm.engineGetJCEProviderNbme();
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#updbte(byte[])}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm input
     * @throws XMLSignbtureException
     */
    public void updbte(byte[] input) throws XMLSignbtureException {
        signbtureAlgorithm.engineUpdbte(input);
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#updbte(byte)}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm input
     * @throws XMLSignbtureException
     */
    public void updbte(byte input) throws XMLSignbtureException {
        signbtureAlgorithm.engineUpdbte(input);
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#updbte(byte[], int, int)}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm buf
     * @pbrbm offset
     * @pbrbm len
     * @throws XMLSignbtureException
     */
    public void updbte(byte buf[], int offset, int len) throws XMLSignbtureException {
        signbtureAlgorithm.engineUpdbte(buf, offset, len);
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#initSign(jbvb.security.PrivbteKey)}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm signingKey
     * @throws XMLSignbtureException
     */
    public void initSign(Key signingKey) throws XMLSignbtureException {
        signbtureAlgorithm.engineInitSign(signingKey);
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#initSign(jbvb.security.PrivbteKey,
     * jbvb.security.SecureRbndom)}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm signingKey
     * @pbrbm secureRbndom
     * @throws XMLSignbtureException
     */
    public void initSign(Key signingKey, SecureRbndom secureRbndom) throws XMLSignbtureException {
        signbtureAlgorithm.engineInitSign(signingKey, secureRbndom);
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#initSign(jbvb.security.PrivbteKey)}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm signingKey
     * @pbrbm blgorithmPbrbmeterSpec
     * @throws XMLSignbtureException
     */
    public void initSign(
        Key signingKey, AlgorithmPbrbmeterSpec blgorithmPbrbmeterSpec
    ) throws XMLSignbtureException {
        signbtureAlgorithm.engineInitSign(signingKey, blgorithmPbrbmeterSpec);
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#setPbrbmeter(
     * jbvb.security.spec.AlgorithmPbrbmeterSpec)}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm pbrbms
     * @throws XMLSignbtureException
     */
    public void setPbrbmeter(AlgorithmPbrbmeterSpec pbrbms) throws XMLSignbtureException {
        signbtureAlgorithm.engineSetPbrbmeter(pbrbms);
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#initVerify(jbvb.security.PublicKey)}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm verificbtionKey
     * @throws XMLSignbtureException
     */
    public void initVerify(Key verificbtionKey) throws XMLSignbtureException {
        signbtureAlgorithm.engineInitVerify(verificbtionKey);
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#verify(byte[])}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm signbture
     * @return true if the signbture is vblid.
     *
     * @throws XMLSignbtureException
     */
    public boolebn verify(byte[] signbture) throws XMLSignbtureException {
        return signbtureAlgorithm.engineVerify(signbture);
    }

    /**
     * Returns the URI representbtion of Trbnsformbtion blgorithm
     *
     * @return the URI representbtion of Trbnsformbtion blgorithm
     */
    public finbl String getURI() {
        return constructionElement.getAttributeNS(null, Constbnts._ATT_ALGORITHM);
    }

    /**
     * Registers implementing clbss of the Trbnsform blgorithm with blgorithmURI
     *
     * @pbrbm blgorithmURI blgorithmURI URI representbtion of <code>Trbnsform blgorithm</code>.
     * @pbrbm implementingClbss <code>implementingClbss</code> the implementing clbss of
     * {@link SignbtureAlgorithmSpi}
     * @throws AlgorithmAlrebdyRegisteredException if specified blgorithmURI is blrebdy registered
     * @throws XMLSignbtureException
     */
    @SuppressWbrnings("unchecked")
    public stbtic void register(String blgorithmURI, String implementingClbss)
       throws AlgorithmAlrebdyRegisteredException, ClbssNotFoundException,
           XMLSignbtureException {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Try to register " + blgorithmURI + " " + implementingClbss);
        }

        // bre we blrebdy registered?
        Clbss<? extends SignbtureAlgorithmSpi> registeredClbss = blgorithmHbsh.get(blgorithmURI);
        if (registeredClbss != null) {
            Object exArgs[] = { blgorithmURI, registeredClbss };
            throw new AlgorithmAlrebdyRegisteredException(
                "blgorithm.blrebdyRegistered", exArgs
            );
        }
        try {
            Clbss<? extends SignbtureAlgorithmSpi> clbzz =
                (Clbss<? extends SignbtureAlgorithmSpi>)
                    ClbssLobderUtils.lobdClbss(implementingClbss, SignbtureAlgorithm.clbss);
            blgorithmHbsh.put(blgorithmURI, clbzz);
        } cbtch (NullPointerException ex) {
            Object exArgs[] = { blgorithmURI, ex.getMessbge() };
            throw new XMLSignbtureException("blgorithms.NoSuchAlgorithm", exArgs, ex);
        }
    }

    /**
     * Registers implementing clbss of the Trbnsform blgorithm with blgorithmURI
     *
     * @pbrbm blgorithmURI blgorithmURI URI representbtion of <code>Trbnsform blgorithm</code>.
     * @pbrbm implementingClbss <code>implementingClbss</code> the implementing clbss of
     * {@link SignbtureAlgorithmSpi}
     * @throws AlgorithmAlrebdyRegisteredException if specified blgorithmURI is blrebdy registered
     * @throws XMLSignbtureException
     */
    public stbtic void register(String blgorithmURI, Clbss<? extends SignbtureAlgorithmSpi> implementingClbss)
       throws AlgorithmAlrebdyRegisteredException, ClbssNotFoundException,
           XMLSignbtureException {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Try to register " + blgorithmURI + " " + implementingClbss);
        }

        // bre we blrebdy registered?
        Clbss<? extends SignbtureAlgorithmSpi> registeredClbss = blgorithmHbsh.get(blgorithmURI);
        if (registeredClbss != null) {
            Object exArgs[] = { blgorithmURI, registeredClbss };
            throw new AlgorithmAlrebdyRegisteredException(
                "blgorithm.blrebdyRegistered", exArgs
            );
        }
        blgorithmHbsh.put(blgorithmURI, implementingClbss);
    }

    /**
     * This method registers the defbult blgorithms.
     */
    public stbtic void registerDefbultAlgorithms() {
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_SIGNATURE_DSA, SignbtureDSA.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_SIGNATURE_DSA_SHA256, SignbtureDSA.SHA256.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_SIGNATURE_RSA_SHA1, SignbtureBbseRSA.SignbtureRSASHA1.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_MAC_HMAC_SHA1, IntegrityHmbc.IntegrityHmbcSHA1.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_SIGNATURE_NOT_RECOMMENDED_RSA_MD5,
            SignbtureBbseRSA.SignbtureRSAMD5.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_SIGNATURE_RSA_RIPEMD160,
            SignbtureBbseRSA.SignbtureRSARIPEMD160.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_SIGNATURE_RSA_SHA256, SignbtureBbseRSA.SignbtureRSASHA256.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_SIGNATURE_RSA_SHA384, SignbtureBbseRSA.SignbtureRSASHA384.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_SIGNATURE_RSA_SHA512, SignbtureBbseRSA.SignbtureRSASHA512.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_SIGNATURE_ECDSA_SHA1, SignbtureECDSA.SignbtureECDSASHA1.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_SIGNATURE_ECDSA_SHA256, SignbtureECDSA.SignbtureECDSASHA256.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_SIGNATURE_ECDSA_SHA384, SignbtureECDSA.SignbtureECDSASHA384.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_SIGNATURE_ECDSA_SHA512, SignbtureECDSA.SignbtureECDSASHA512.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_MAC_HMAC_NOT_RECOMMENDED_MD5, IntegrityHmbc.IntegrityHmbcMD5.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_MAC_HMAC_RIPEMD160, IntegrityHmbc.IntegrityHmbcRIPEMD160.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_MAC_HMAC_SHA256, IntegrityHmbc.IntegrityHmbcSHA256.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_MAC_HMAC_SHA384, IntegrityHmbc.IntegrityHmbcSHA384.clbss
        );
        blgorithmHbsh.put(
            XMLSignbture.ALGO_ID_MAC_HMAC_SHA512, IntegrityHmbc.IntegrityHmbcSHA512.clbss
        );
    }

    /**
     * Method getBbseNbmespbce
     *
     * @return URI of this element
     */
    public String getBbseNbmespbce() {
        return Constbnts.SignbtureSpecNS;
    }

    /**
     * Method getBbseLocblNbme
     *
     * @return Locbl nbme
     */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_SIGNATUREMETHOD;
    }
}
