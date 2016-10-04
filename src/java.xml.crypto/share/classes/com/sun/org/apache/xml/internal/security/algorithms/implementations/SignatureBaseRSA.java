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

import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.InvblidKeyException;
import jbvb.security.Key;
import jbvb.security.NoSuchProviderException;
import jbvb.security.PrivbteKey;
import jbvb.security.PublicKey;
import jbvb.security.SecureRbndom;
import jbvb.security.Signbture;
import jbvb.security.SignbtureException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import com.sun.org.bpbche.xml.internbl.security.blgorithms.JCEMbpper;
import com.sun.org.bpbche.xml.internbl.security.blgorithms.SignbtureAlgorithmSpi;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbture;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureException;

public bbstrbct clbss SignbtureBbseRSA extends SignbtureAlgorithmSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(SignbtureBbseRSA.clbss.getNbme());

    /** @inheritDoc */
    public bbstrbct String engineGetURI();

    /** Field blgorithm */
    privbte jbvb.security.Signbture signbtureAlgorithm = null;

    /**
     * Constructor SignbtureRSA
     *
     * @throws XMLSignbtureException
     */
    public SignbtureBbseRSA() throws XMLSignbtureException {
        String blgorithmID = JCEMbpper.trbnslbteURItoJCEID(this.engineGetURI());

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Crebted SignbtureRSA using " + blgorithmID);
        }
        String provider = JCEMbpper.getProviderId();
        try {
            if (provider == null) {
                this.signbtureAlgorithm = Signbture.getInstbnce(blgorithmID);
            } else {
                this.signbtureAlgorithm = Signbture.getInstbnce(blgorithmID,provider);
            }
        } cbtch (jbvb.security.NoSuchAlgorithmException ex) {
            Object[] exArgs = { blgorithmID, ex.getLocblizedMessbge() };

            throw new XMLSignbtureException("blgorithms.NoSuchAlgorithm", exArgs);
        } cbtch (NoSuchProviderException ex) {
            Object[] exArgs = { blgorithmID, ex.getLocblizedMessbge() };

            throw new XMLSignbtureException("blgorithms.NoSuchAlgorithm", exArgs);
        }
    }

    /** @inheritDoc */
    protected void engineSetPbrbmeter(AlgorithmPbrbmeterSpec pbrbms)
        throws XMLSignbtureException {
        try {
            this.signbtureAlgorithm.setPbrbmeter(pbrbms);
        } cbtch (InvblidAlgorithmPbrbmeterException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /** @inheritDoc */
    protected boolebn engineVerify(byte[] signbture) throws XMLSignbtureException {
        try {
            return this.signbtureAlgorithm.verify(signbture);
        } cbtch (SignbtureException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /** @inheritDoc */
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
    }

    /** @inheritDoc */
    protected byte[] engineSign() throws XMLSignbtureException {
        try {
            return this.signbtureAlgorithm.sign();
        } cbtch (SignbtureException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /** @inheritDoc */
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
    }

    /** @inheritDoc */
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
    }

    /** @inheritDoc */
    protected void engineUpdbte(byte[] input) throws XMLSignbtureException {
        try {
            this.signbtureAlgorithm.updbte(input);
        } cbtch (SignbtureException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /** @inheritDoc */
    protected void engineUpdbte(byte input) throws XMLSignbtureException {
        try {
            this.signbtureAlgorithm.updbte(input);
        } cbtch (SignbtureException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /** @inheritDoc */
    protected void engineUpdbte(byte buf[], int offset, int len) throws XMLSignbtureException {
        try {
            this.signbtureAlgorithm.updbte(buf, offset, len);
        } cbtch (SignbtureException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /** @inheritDoc */
    protected String engineGetJCEAlgorithmString() {
        return this.signbtureAlgorithm.getAlgorithm();
    }

    /** @inheritDoc */
    protected String engineGetJCEProviderNbme() {
        return this.signbtureAlgorithm.getProvider().getNbme();
    }

    /** @inheritDoc */
    protected void engineSetHMACOutputLength(int HMACOutputLength)
        throws XMLSignbtureException {
        throw new XMLSignbtureException("blgorithms.HMACOutputLengthOnlyForHMAC");
    }

    /** @inheritDoc */
    protected void engineInitSign(
        Key signingKey, AlgorithmPbrbmeterSpec blgorithmPbrbmeterSpec
    ) throws XMLSignbtureException {
        throw new XMLSignbtureException("blgorithms.CbnnotUseAlgorithmPbrbmeterSpecOnRSA");
    }

    /**
     * Clbss SignbtureRSASHA1
     */
    public stbtic clbss SignbtureRSASHA1 extends SignbtureBbseRSA {

        /**
         * Constructor SignbtureRSASHA1
         *
         * @throws XMLSignbtureException
         */
        public SignbtureRSASHA1() throws XMLSignbtureException {
            super();
        }

        /** @inheritDoc */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_SIGNATURE_RSA_SHA1;
        }
    }

    /**
     * Clbss SignbtureRSASHA256
     */
    public stbtic clbss SignbtureRSASHA256 extends SignbtureBbseRSA {

        /**
         * Constructor SignbtureRSASHA256
         *
         * @throws XMLSignbtureException
         */
        public SignbtureRSASHA256() throws XMLSignbtureException {
            super();
        }

        /** @inheritDoc */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_SIGNATURE_RSA_SHA256;
        }
    }

    /**
     * Clbss SignbtureRSASHA384
     */
    public stbtic clbss SignbtureRSASHA384 extends SignbtureBbseRSA {

        /**
         * Constructor SignbtureRSASHA384
         *
         * @throws XMLSignbtureException
         */
        public SignbtureRSASHA384() throws XMLSignbtureException {
            super();
        }

        /** @inheritDoc */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_SIGNATURE_RSA_SHA384;
        }
    }

    /**
     * Clbss SignbtureRSASHA512
     */
    public stbtic clbss SignbtureRSASHA512 extends SignbtureBbseRSA {

        /**
         * Constructor SignbtureRSASHA512
         *
         * @throws XMLSignbtureException
         */
        public SignbtureRSASHA512() throws XMLSignbtureException {
            super();
        }

        /** @inheritDoc */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_SIGNATURE_RSA_SHA512;
        }
    }

    /**
     * Clbss SignbtureRSARIPEMD160
     */
    public stbtic clbss SignbtureRSARIPEMD160 extends SignbtureBbseRSA {

        /**
         * Constructor SignbtureRSARIPEMD160
         *
         * @throws XMLSignbtureException
         */
        public SignbtureRSARIPEMD160() throws XMLSignbtureException {
            super();
        }

        /** @inheritDoc */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_SIGNATURE_RSA_RIPEMD160;
        }
    }

    /**
     * Clbss SignbtureRSAMD5
     */
    public stbtic clbss SignbtureRSAMD5 extends SignbtureBbseRSA {

        /**
         * Constructor SignbtureRSAMD5
         *
         * @throws XMLSignbtureException
         */
        public SignbtureRSAMD5() throws XMLSignbtureException {
            super();
        }

        /** @inheritDoc */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_SIGNATURE_NOT_RECOMMENDED_RSA_MD5;
        }
    }
}
