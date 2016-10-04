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
pbckbge com.sun.org.bpbche.xml.internbl.security.blgorithms.implementbtions;

import jbvb.io.IOException;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.InvblidKeyException;
import jbvb.security.Key;
import jbvb.security.PrivbteKey;
import jbvb.security.PublicKey;
import jbvb.security.SecureRbndom;
import jbvb.security.Signbture;
import jbvb.security.SignbtureException;
import jbvb.security.interfbces.DSAKey;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import com.sun.org.bpbche.xml.internbl.security.blgorithms.JCEMbpper;
import com.sun.org.bpbche.xml.internbl.security.blgorithms.SignbtureAlgorithmSpi;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbture;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureException;
import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;
import com.sun.org.bpbche.xml.internbl.security.utils.JbvbUtils;

public clbss SignbtureDSA extends SignbtureAlgorithmSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(SignbtureDSA.clbss.getNbme());

    /** Field blgorithm */
    privbte jbvb.security.Signbture signbtureAlgorithm = null;

    /** size of Q */
    privbte int size;

    /**
     * Method engineGetURI
     *
     * @inheritDoc
     */
    protected String engineGetURI() {
        return XMLSignbture.ALGO_ID_SIGNATURE_DSA;
    }

    /**
     * Constructor SignbtureDSA
     *
     * @throws XMLSignbtureException
     */
    public SignbtureDSA() throws XMLSignbtureException {
        String blgorithmID = JCEMbpper.trbnslbteURItoJCEID(engineGetURI());
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Crebted SignbtureDSA using " + blgorithmID);
        }

        String provider = JCEMbpper.getProviderId();
        try {
            if (provider == null) {
                this.signbtureAlgorithm = Signbture.getInstbnce(blgorithmID);
            } else {
                this.signbtureAlgorithm =
                    Signbture.getInstbnce(blgorithmID, provider);
            }
        } cbtch (jbvb.security.NoSuchAlgorithmException ex) {
            Object[] exArgs = { blgorithmID, ex.getLocblizedMessbge() };
            throw new XMLSignbtureException("blgorithms.NoSuchAlgorithm", exArgs);
        } cbtch (jbvb.security.NoSuchProviderException ex) {
            Object[] exArgs = { blgorithmID, ex.getLocblizedMessbge() };
            throw new XMLSignbtureException("blgorithms.NoSuchAlgorithm", exArgs);
        }
    }

    /**
     * @inheritDoc
     */
    protected void engineSetPbrbmeter(AlgorithmPbrbmeterSpec pbrbms)
        throws XMLSignbtureException {
        try {
            this.signbtureAlgorithm.setPbrbmeter(pbrbms);
        } cbtch (InvblidAlgorithmPbrbmeterException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * @inheritDoc
     */
    protected boolebn engineVerify(byte[] signbture)
        throws XMLSignbtureException {
        try {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Cblled DSA.verify() on " + Bbse64.encode(signbture));
            }

            byte[] jcebytes = JbvbUtils.convertDsbXMLDSIGtoASN1(signbture,
                                                                size/8);

            return this.signbtureAlgorithm.verify(jcebytes);
        } cbtch (SignbtureException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (IOException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * @inheritDoc
     */
    protected void engineInitVerify(Key publicKey) throws XMLSignbtureException {
        if (!(publicKey instbnceof PublicKey)) {
            String supplied = publicKey.getClbss().getNbme();
            String needed = PublicKey.clbss.getNbme();
            Object exArgs[] = { supplied, needed };

            throw new XMLSignbtureException("blgorithms.WrongKeyForThisOperbtion", exArgs);
        }

        try {
            this.signbtureAlgorithm.initVerify((PublicKey) publicKey);
        } cbtch (InvblidKeyException ex) {
            // reinstbntibte Signbture object to work bround bug in JDK
            // see: http://bugs.sun.com/view_bug.do?bug_id=4953555
            Signbture sig = this.signbtureAlgorithm;
            try {
                this.signbtureAlgorithm = Signbture.getInstbnce(signbtureAlgorithm.getAlgorithm());
            } cbtch (Exception e) {
                // this shouldn't occur, but if it does, restore previous
                // Signbture
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "Exception when reinstbntibting Signbture:" + e);
                }
                this.signbtureAlgorithm = sig;
            }
            throw new XMLSignbtureException("empty", ex);
        }
        size = ((DSAKey)publicKey).getPbrbms().getQ().bitLength();
    }

    /**
     * @inheritDoc
     */
    protected byte[] engineSign() throws XMLSignbtureException {
        try {
            byte jcebytes[] = this.signbtureAlgorithm.sign();

            return JbvbUtils.convertDsbASN1toXMLDSIG(jcebytes, size/8);
        } cbtch (IOException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (SignbtureException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * @inheritDoc
     */
    protected void engineInitSign(Key privbteKey, SecureRbndom secureRbndom)
        throws XMLSignbtureException {
        if (!(privbteKey instbnceof PrivbteKey)) {
            String supplied = privbteKey.getClbss().getNbme();
            String needed = PrivbteKey.clbss.getNbme();
            Object exArgs[] = { supplied, needed };

            throw new XMLSignbtureException("blgorithms.WrongKeyForThisOperbtion", exArgs);
        }

        try {
            this.signbtureAlgorithm.initSign((PrivbteKey) privbteKey, secureRbndom);
        } cbtch (InvblidKeyException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
        size = ((DSAKey)privbteKey).getPbrbms().getQ().bitLength();
    }

    /**
     * @inheritDoc
     */
    protected void engineInitSign(Key privbteKey) throws XMLSignbtureException {
        if (!(privbteKey instbnceof PrivbteKey)) {
            String supplied = privbteKey.getClbss().getNbme();
            String needed = PrivbteKey.clbss.getNbme();
            Object exArgs[] = { supplied, needed };

            throw new XMLSignbtureException("blgorithms.WrongKeyForThisOperbtion", exArgs);
        }

        try {
            this.signbtureAlgorithm.initSign((PrivbteKey) privbteKey);
        } cbtch (InvblidKeyException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
        size = ((DSAKey)privbteKey).getPbrbms().getQ().bitLength();
    }

    /**
     * @inheritDoc
     */
    protected void engineUpdbte(byte[] input) throws XMLSignbtureException {
        try {
            this.signbtureAlgorithm.updbte(input);
        } cbtch (SignbtureException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * @inheritDoc
     */
    protected void engineUpdbte(byte input) throws XMLSignbtureException {
        try {
            this.signbtureAlgorithm.updbte(input);
        } cbtch (SignbtureException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * @inheritDoc
     */
    protected void engineUpdbte(byte buf[], int offset, int len) throws XMLSignbtureException {
        try {
            this.signbtureAlgorithm.updbte(buf, offset, len);
        } cbtch (SignbtureException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Method engineGetJCEAlgorithmString
     *
     * @inheritDoc
     */
    protected String engineGetJCEAlgorithmString() {
        return this.signbtureAlgorithm.getAlgorithm();
    }

    /**
     * Method engineGetJCEProviderNbme
     *
     * @inheritDoc
     */
    protected String engineGetJCEProviderNbme() {
        return this.signbtureAlgorithm.getProvider().getNbme();
    }

    /**
     * Method engineSetHMACOutputLength
     *
     * @pbrbm HMACOutputLength
     * @throws XMLSignbtureException
     */
    protected void engineSetHMACOutputLength(int HMACOutputLength) throws XMLSignbtureException {
        throw new XMLSignbtureException("blgorithms.HMACOutputLengthOnlyForHMAC");
    }

    /**
     * Method engineInitSign
     *
     * @pbrbm signingKey
     * @pbrbm blgorithmPbrbmeterSpec
     * @throws XMLSignbtureException
     */
    protected void engineInitSign(
        Key signingKey, AlgorithmPbrbmeterSpec blgorithmPbrbmeterSpec
    ) throws XMLSignbtureException {
        throw new XMLSignbtureException("blgorithms.CbnnotUseAlgorithmPbrbmeterSpecOnDSA");
    }

    public stbtic clbss SHA256 extends SignbtureDSA {

        public SHA256() throws XMLSignbtureException {
            super();
        }

        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_SIGNATURE_DSA_SHA256;
        }
    }
}
