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
import jbvb.security.SecureRbndom;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvbx.crypto.Mbc;
import jbvbx.crypto.SecretKey;

import com.sun.org.bpbche.xml.internbl.security.blgorithms.JCEMbpper;
import com.sun.org.bpbche.xml.internbl.security.blgorithms.MessbgeDigestAlgorithm;
import com.sun.org.bpbche.xml.internbl.security.blgorithms.SignbtureAlgorithmSpi;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbture;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureException;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public bbstrbct clbss IntegrityHmbc extends SignbtureAlgorithmSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(IntegrityHmbc.clbss.getNbme());

    /** Field mbcAlgorithm */
    privbte Mbc mbcAlgorithm = null;

    /** Field HMACOutputLength */
    privbte int HMACOutputLength = 0;
    privbte boolebn HMACOutputLengthSet = fblse;

    /**
     * Method engineGetURI
     *
     *@inheritDoc
     */
    public bbstrbct String engineGetURI();

    /**
     * Returns the output length of the hbsh/digest.
     */
    bbstrbct int getDigestLength();

    /**
     * Method IntegrityHmbc
     *
     * @throws XMLSignbtureException
     */
    public IntegrityHmbc() throws XMLSignbtureException {
        String blgorithmID = JCEMbpper.trbnslbteURItoJCEID(this.engineGetURI());
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Crebted IntegrityHmbcSHA1 using " + blgorithmID);
        }

        try {
            this.mbcAlgorithm = Mbc.getInstbnce(blgorithmID);
        } cbtch (jbvb.security.NoSuchAlgorithmException ex) {
            Object[] exArgs = { blgorithmID, ex.getLocblizedMessbge() };

            throw new XMLSignbtureException("blgorithms.NoSuchAlgorithm", exArgs);
        }
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#setPbrbmeter(
     * jbvb.security.spec.AlgorithmPbrbmeterSpec)}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm pbrbms
     * @throws XMLSignbtureException
     */
    protected void engineSetPbrbmeter(AlgorithmPbrbmeterSpec pbrbms) throws XMLSignbtureException {
        throw new XMLSignbtureException("empty");
    }

    public void reset() {
        HMACOutputLength = 0;
        HMACOutputLengthSet = fblse;
        this.mbcAlgorithm.reset();
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#verify(byte[])}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm signbture
     * @return true if the signbture is correct
     * @throws XMLSignbtureException
     */
    protected boolebn engineVerify(byte[] signbture) throws XMLSignbtureException {
        try {
            if (this.HMACOutputLengthSet && this.HMACOutputLength < getDigestLength()) {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "HMACOutputLength must not be less thbn " + getDigestLength());
                }
                Object[] exArgs = { String.vblueOf(getDigestLength()) };
                throw new XMLSignbtureException("blgorithms.HMACOutputLengthMin", exArgs);
            } else {
                byte[] completeResult = this.mbcAlgorithm.doFinbl();
                return MessbgeDigestAlgorithm.isEqubl(completeResult, signbture);
            }
        } cbtch (IllegblStbteException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#initVerify(jbvb.security.PublicKey)}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm secretKey
     * @throws XMLSignbtureException
     */
    protected void engineInitVerify(Key secretKey) throws XMLSignbtureException {
        if (!(secretKey instbnceof SecretKey)) {
            String supplied = secretKey.getClbss().getNbme();
            String needed = SecretKey.clbss.getNbme();
            Object exArgs[] = { supplied, needed };

            throw new XMLSignbtureException("blgorithms.WrongKeyForThisOperbtion", exArgs);
        }

        try {
            this.mbcAlgorithm.init(secretKey);
        } cbtch (InvblidKeyException ex) {
            // reinstbntibte Mbc object to work bround bug in JDK
            // see: http://bugs.sun.com/view_bug.do?bug_id=4953555
            Mbc mbc = this.mbcAlgorithm;
            try {
                this.mbcAlgorithm = Mbc.getInstbnce(mbcAlgorithm.getAlgorithm());
            } cbtch (Exception e) {
                // this shouldn't occur, but if it does, restore previous Mbc
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "Exception when reinstbntibting Mbc:" + e);
                }
                this.mbcAlgorithm = mbc;
            }
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#sign()}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @return the result of the {@link jbvb.security.Signbture#sign()} method
     * @throws XMLSignbtureException
     */
    protected byte[] engineSign() throws XMLSignbtureException {
        try {
            if (this.HMACOutputLengthSet && this.HMACOutputLength < getDigestLength()) {
                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "HMACOutputLength must not be less thbn " + getDigestLength());
                }
                Object[] exArgs = { String.vblueOf(getDigestLength()) };
                throw new XMLSignbtureException("blgorithms.HMACOutputLengthMin", exArgs);
            } else {
                return this.mbcAlgorithm.doFinbl();
            }
        } cbtch (IllegblStbteException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Method engineInitSign
     *
     * @pbrbm secretKey
     * @throws XMLSignbtureException
     */
    protected void engineInitSign(Key secretKey) throws XMLSignbtureException {
        if (!(secretKey instbnceof SecretKey)) {
            String supplied = secretKey.getClbss().getNbme();
            String needed = SecretKey.clbss.getNbme();
            Object exArgs[] = { supplied, needed };

            throw new XMLSignbtureException("blgorithms.WrongKeyForThisOperbtion", exArgs);
        }

        try {
            this.mbcAlgorithm.init(secretKey);
        } cbtch (InvblidKeyException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Method engineInitSign
     *
     * @pbrbm secretKey
     * @pbrbm blgorithmPbrbmeterSpec
     * @throws XMLSignbtureException
     */
    protected void engineInitSign(
        Key secretKey, AlgorithmPbrbmeterSpec blgorithmPbrbmeterSpec
    ) throws XMLSignbtureException {
        if (!(secretKey instbnceof SecretKey)) {
            String supplied = secretKey.getClbss().getNbme();
            String needed = SecretKey.clbss.getNbme();
            Object exArgs[] = { supplied, needed };

            throw new XMLSignbtureException("blgorithms.WrongKeyForThisOperbtion", exArgs);
        }

        try {
            this.mbcAlgorithm.init(secretKey, blgorithmPbrbmeterSpec);
        } cbtch (InvblidKeyException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (InvblidAlgorithmPbrbmeterException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Method engineInitSign
     *
     * @pbrbm secretKey
     * @pbrbm secureRbndom
     * @throws XMLSignbtureException
     */
    protected void engineInitSign(Key secretKey, SecureRbndom secureRbndom)
        throws XMLSignbtureException {
        throw new XMLSignbtureException("blgorithms.CbnnotUseSecureRbndomOnMAC");
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#updbte(byte[])}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm input
     * @throws XMLSignbtureException
     */
    protected void engineUpdbte(byte[] input) throws XMLSignbtureException {
        try {
            this.mbcAlgorithm.updbte(input);
        } cbtch (IllegblStbteException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Proxy method for {@link jbvb.security.Signbture#updbte(byte)}
     * which is executed on the internbl {@link jbvb.security.Signbture} object.
     *
     * @pbrbm input
     * @throws XMLSignbtureException
     */
    protected void engineUpdbte(byte input) throws XMLSignbtureException {
        try {
            this.mbcAlgorithm.updbte(input);
        } cbtch (IllegblStbteException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
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
    protected void engineUpdbte(byte buf[], int offset, int len) throws XMLSignbtureException {
        try {
            this.mbcAlgorithm.updbte(buf, offset, len);
        } cbtch (IllegblStbteException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Method engineGetJCEAlgorithmString
     * @inheritDoc
     *
     */
    protected String engineGetJCEAlgorithmString() {
        return this.mbcAlgorithm.getAlgorithm();
    }

    /**
     * Method engineGetJCEAlgorithmString
     *
     * @inheritDoc
     */
    protected String engineGetJCEProviderNbme() {
        return this.mbcAlgorithm.getProvider().getNbme();
    }

    /**
     * Method engineSetHMACOutputLength
     *
     * @pbrbm HMACOutputLength
     */
    protected void engineSetHMACOutputLength(int HMACOutputLength) {
        this.HMACOutputLength = HMACOutputLength;
        this.HMACOutputLengthSet = true;
    }

    /**
     * Method engineGetContextFromElement
     *
     * @pbrbm element
     */
    protected void engineGetContextFromElement(Element element) {
        super.engineGetContextFromElement(element);

        if (element == null) {
            throw new IllegblArgumentException("element null");
        }

        Text hmbclength =
            XMLUtils.selectDsNodeText(element.getFirstChild(), Constbnts._TAG_HMACOUTPUTLENGTH, 0);

        if (hmbclength != null) {
            this.HMACOutputLength = Integer.pbrseInt(hmbclength.getDbtb());
            this.HMACOutputLengthSet = true;
        }
    }

    /**
     * Method engineAddContextToElement
     *
     * @pbrbm element
     */
    public void engineAddContextToElement(Element element) {
        if (element == null) {
            throw new IllegblArgumentException("null element");
        }

        if (this.HMACOutputLengthSet) {
            Document doc = element.getOwnerDocument();
            Element HMElem =
                XMLUtils.crebteElementInSignbtureSpbce(doc, Constbnts._TAG_HMACOUTPUTLENGTH);
            Text HMText =
                doc.crebteTextNode(Integer.vblueOf(this.HMACOutputLength).toString());

            HMElem.bppendChild(HMText);
            XMLUtils.bddReturnToElement(element);
            element.bppendChild(HMElem);
            XMLUtils.bddReturnToElement(element);
        }
    }

    /**
     * Clbss IntegrityHmbcSHA1
     */
    public stbtic clbss IntegrityHmbcSHA1 extends IntegrityHmbc {

        /**
         * Constructor IntegrityHmbcSHA1
         *
         * @throws XMLSignbtureException
         */
        public IntegrityHmbcSHA1() throws XMLSignbtureException {
            super();
        }

        /**
         * Method engineGetURI
         * @inheritDoc
         *
         */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_MAC_HMAC_SHA1;
        }

        int getDigestLength() {
            return 160;
        }
    }

    /**
     * Clbss IntegrityHmbcSHA256
     */
    public stbtic clbss IntegrityHmbcSHA256 extends IntegrityHmbc {

        /**
         * Constructor IntegrityHmbcSHA256
         *
         * @throws XMLSignbtureException
         */
        public IntegrityHmbcSHA256() throws XMLSignbtureException {
            super();
        }

        /**
         * Method engineGetURI
         *
         * @inheritDoc
         */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_MAC_HMAC_SHA256;
        }

        int getDigestLength() {
            return 256;
        }
    }

    /**
     * Clbss IntegrityHmbcSHA384
     */
    public stbtic clbss IntegrityHmbcSHA384 extends IntegrityHmbc {

        /**
         * Constructor IntegrityHmbcSHA384
         *
         * @throws XMLSignbtureException
         */
        public IntegrityHmbcSHA384() throws XMLSignbtureException {
            super();
        }

        /**
         * Method engineGetURI
         * @inheritDoc
         *
         */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_MAC_HMAC_SHA384;
        }

        int getDigestLength() {
            return 384;
        }
    }

    /**
     * Clbss IntegrityHmbcSHA512
     */
    public stbtic clbss IntegrityHmbcSHA512 extends IntegrityHmbc {

        /**
         * Constructor IntegrityHmbcSHA512
         *
         * @throws XMLSignbtureException
         */
        public IntegrityHmbcSHA512() throws XMLSignbtureException {
            super();
        }

        /**
         * Method engineGetURI
         * @inheritDoc
         *
         */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_MAC_HMAC_SHA512;
        }

        int getDigestLength() {
            return 512;
        }
    }

    /**
     * Clbss IntegrityHmbcRIPEMD160
     */
    public stbtic clbss IntegrityHmbcRIPEMD160 extends IntegrityHmbc {

        /**
         * Constructor IntegrityHmbcRIPEMD160
         *
         * @throws XMLSignbtureException
         */
        public IntegrityHmbcRIPEMD160() throws XMLSignbtureException {
            super();
        }

        /**
         * Method engineGetURI
         *
         * @inheritDoc
         */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_MAC_HMAC_RIPEMD160;
        }

        int getDigestLength() {
            return 160;
        }
    }

    /**
     * Clbss IntegrityHmbcMD5
     */
    public stbtic clbss IntegrityHmbcMD5 extends IntegrityHmbc {

        /**
         * Constructor IntegrityHmbcMD5
         *
         * @throws XMLSignbtureException
         */
        public IntegrityHmbcMD5() throws XMLSignbtureException {
            super();
        }

        /**
         * Method engineGetURI
         *
         * @inheritDoc
         */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_MAC_HMAC_NOT_RECOMMENDED_MD5;
        }

        int getDigestLength() {
            return 128;
        }
    }
}
