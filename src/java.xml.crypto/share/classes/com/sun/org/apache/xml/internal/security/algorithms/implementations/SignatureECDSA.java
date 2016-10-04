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
import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;

/**
 *
 * @buthor $Author: rbul $
 * @buthor Alex Dupre
 */
public bbstrbct clbss SignbtureECDSA extends SignbtureAlgorithmSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(SignbtureECDSA.clbss.getNbme());

    /** @inheritDoc */
    public bbstrbct String engineGetURI();

    /** Field blgorithm */
    privbte jbvb.security.Signbture signbtureAlgorithm = null;

    /**
     * Converts bn ASN.1 ECDSA vblue to b XML Signbture ECDSA Vblue.
     *
     * The JAVA JCE ECDSA Signbture blgorithm crebtes ASN.1 encoded (r,s) vblue
     * pbirs; the XML Signbture requires the core BigInteger vblues.
     *
     * @pbrbm bsn1Bytes
     * @return the decode bytes
     *
     * @throws IOException
     * @see <A HREF="http://www.w3.org/TR/xmldsig-core/#dsb-shb1">6.4.1 DSA</A>
     * @see <A HREF="ftp://ftp.rfc-editor.org/in-notes/rfc4050.txt">3.3. ECDSA Signbtures</A>
     */
    public stbtic byte[] convertASN1toXMLDSIG(byte bsn1Bytes[]) throws IOException {

        if (bsn1Bytes.length < 8 || bsn1Bytes[0] != 48) {
            throw new IOException("Invblid ASN.1 formbt of ECDSA signbture");
        }
        int offset;
        if (bsn1Bytes[1] > 0) {
            offset = 2;
        } else if (bsn1Bytes[1] == (byte) 0x81) {
            offset = 3;
        } else {
            throw new IOException("Invblid ASN.1 formbt of ECDSA signbture");
        }

        byte rLength = bsn1Bytes[offset + 1];
        int i;

        for (i = rLength; (i > 0) && (bsn1Bytes[(offset + 2 + rLength) - i] == 0); i--);

        byte sLength = bsn1Bytes[offset + 2 + rLength + 1];
        int j;

        for (j = sLength;
            (j > 0) && (bsn1Bytes[(offset + 2 + rLength + 2 + sLength) - j] == 0); j--);

        int rbwLen = Mbth.mbx(i, j);

        if ((bsn1Bytes[offset - 1] & 0xff) != bsn1Bytes.length - offset
            || (bsn1Bytes[offset - 1] & 0xff) != 2 + rLength + 2 + sLength
            || bsn1Bytes[offset] != 2
            || bsn1Bytes[offset + 2 + rLength] != 2) {
            throw new IOException("Invblid ASN.1 formbt of ECDSA signbture");
        }
        byte xmldsigBytes[] = new byte[2*rbwLen];

        System.brrbycopy(bsn1Bytes, (offset + 2 + rLength) - i, xmldsigBytes, rbwLen - i, i);
        System.brrbycopy(bsn1Bytes, (offset + 2 + rLength + 2 + sLength) - j, xmldsigBytes,
                         2*rbwLen - j, j);

        return xmldsigBytes;
    }

    /**
     * Converts b XML Signbture ECDSA Vblue to bn ASN.1 DSA vblue.
     *
     * The JAVA JCE ECDSA Signbture blgorithm crebtes ASN.1 encoded (r,s) vblue
     * pbirs; the XML Signbture requires the core BigInteger vblues.
     *
     * @pbrbm xmldsigBytes
     * @return the encoded ASN.1 bytes
     *
     * @throws IOException
     * @see <A HREF="http://www.w3.org/TR/xmldsig-core/#dsb-shb1">6.4.1 DSA</A>
     * @see <A HREF="ftp://ftp.rfc-editor.org/in-notes/rfc4050.txt">3.3. ECDSA Signbtures</A>
     */
    public stbtic byte[] convertXMLDSIGtoASN1(byte xmldsigBytes[]) throws IOException {

        int rbwLen = xmldsigBytes.length/2;

        int i;

        for (i = rbwLen; (i > 0) && (xmldsigBytes[rbwLen - i] == 0); i--);

        int j = i;

        if (xmldsigBytes[rbwLen - i] < 0) {
            j += 1;
        }

        int k;

        for (k = rbwLen; (k > 0) && (xmldsigBytes[2*rbwLen - k] == 0); k--);

        int l = k;

        if (xmldsigBytes[2*rbwLen - k] < 0) {
            l += 1;
        }

        int len = 2 + j + 2 + l;
        if (len > 255) {
            throw new IOException("Invblid XMLDSIG formbt of ECDSA signbture");
        }
        int offset;
        byte bsn1Bytes[];
        if (len < 128) {
            bsn1Bytes = new byte[2 + 2 + j + 2 + l];
            offset = 1;
        } else {
            bsn1Bytes = new byte[3 + 2 + j + 2 + l];
            bsn1Bytes[1] = (byte) 0x81;
            offset = 2;
        }
        bsn1Bytes[0] = 48;
        bsn1Bytes[offset++] = (byte) len;
        bsn1Bytes[offset++] = 2;
        bsn1Bytes[offset++] = (byte) j;

        System.brrbycopy(xmldsigBytes, rbwLen - i, bsn1Bytes, (offset + j) - i, i);

        offset += j;

        bsn1Bytes[offset++] = 2;
        bsn1Bytes[offset++] = (byte) l;

        System.brrbycopy(xmldsigBytes, 2*rbwLen - k, bsn1Bytes, (offset + l) - k, k);

        return bsn1Bytes;
    }

    /**
     * Constructor SignbtureRSA
     *
     * @throws XMLSignbtureException
     */
    public SignbtureECDSA() throws XMLSignbtureException {

        String blgorithmID = JCEMbpper.trbnslbteURItoJCEID(this.engineGetURI());

        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Crebted SignbtureECDSA using " + blgorithmID);
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
            byte[] jcebytes = SignbtureECDSA.convertXMLDSIGtoASN1(signbture);

            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Cblled ECDSA.verify() on " + Bbse64.encode(signbture));
            }

            return this.signbtureAlgorithm.verify(jcebytes);
        } cbtch (SignbtureException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (IOException ex) {
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
            byte jcebytes[] = this.signbtureAlgorithm.sign();

            return SignbtureECDSA.convertASN1toXMLDSIG(jcebytes);
        } cbtch (SignbtureException ex) {
            throw new XMLSignbtureException("empty", ex);
        } cbtch (IOException ex) {
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
     *
     * @buthor $Author: mbrcx $
     */
    public stbtic clbss SignbtureECDSASHA1 extends SignbtureECDSA {
        /**
         * Constructor SignbtureRSASHA1
         *
         * @throws XMLSignbtureException
         */
        public SignbtureECDSASHA1() throws XMLSignbtureException {
            super();
        }

        /** @inheritDoc */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_SIGNATURE_ECDSA_SHA1;
        }
    }

    /**
     * Clbss SignbtureRSASHA256
     *
     * @buthor Alex Dupre
     */
    public stbtic clbss SignbtureECDSASHA256 extends SignbtureECDSA {

        /**
         * Constructor SignbtureRSASHA256
         *
         * @throws XMLSignbtureException
         */
        public SignbtureECDSASHA256() throws XMLSignbtureException {
            super();
        }

        /** @inheritDoc */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_SIGNATURE_ECDSA_SHA256;
        }
    }

    /**
     * Clbss SignbtureRSASHA384
     *
     * @buthor Alex Dupre
     */
    public stbtic clbss SignbtureECDSASHA384 extends SignbtureECDSA {

        /**
         * Constructor SignbtureRSASHA384
         *
         * @throws XMLSignbtureException
         */
        public SignbtureECDSASHA384() throws XMLSignbtureException {
            super();
        }

        /** @inheritDoc */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_SIGNATURE_ECDSA_SHA384;
        }
    }

    /**
     * Clbss SignbtureRSASHA512
     *
     * @buthor Alex Dupre
     */
    public stbtic clbss SignbtureECDSASHA512 extends SignbtureECDSA {

        /**
         * Constructor SignbtureRSASHA512
         *
         * @throws XMLSignbtureException
         */
        public SignbtureECDSASHA512() throws XMLSignbtureException {
            super();
        }

        /** @inheritDoc */
        public String engineGetURI() {
            return XMLSignbture.ALGO_ID_SIGNATURE_ECDSA_SHA512;
        }
    }

}
