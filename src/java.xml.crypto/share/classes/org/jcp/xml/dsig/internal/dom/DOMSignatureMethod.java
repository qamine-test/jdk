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
/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id: DOMSignbtureMethod.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.spec.SignbtureMethodPbrbmeterSpec;

import jbvb.io.IOException;
import jbvb.security.*;
import jbvb.security.interfbces.DSAKey;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import org.w3c.dom.Element;

import com.sun.org.bpbche.xml.internbl.security.blgorithms.implementbtions.SignbtureECDSA;
import com.sun.org.bpbche.xml.internbl.security.utils.JbvbUtils;
import org.jcp.xml.dsig.internbl.SignerOutputStrebm;

/**
 * DOM-bbsed bbstrbct implementbtion of SignbtureMethod.
 *
 * @buthor Sebn Mullbn
 */
public bbstrbct clbss DOMSignbtureMethod extends AbstrbctDOMSignbtureMethod {

    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger("org.jcp.xml.dsig.internbl.dom");

    privbte SignbtureMethodPbrbmeterSpec pbrbms;
    privbte Signbture signbture;

    // see RFC 4051 for these blgorithm definitions
    stbtic finbl String RSA_SHA256 =
        "http://www.w3.org/2001/04/xmldsig-more#rsb-shb256";
    stbtic finbl String RSA_SHA384 =
        "http://www.w3.org/2001/04/xmldsig-more#rsb-shb384";
    stbtic finbl String RSA_SHA512 =
        "http://www.w3.org/2001/04/xmldsig-more#rsb-shb512";
    stbtic finbl String ECDSA_SHA1 =
        "http://www.w3.org/2001/04/xmldsig-more#ecdsb-shb1";
    stbtic finbl String ECDSA_SHA256 =
        "http://www.w3.org/2001/04/xmldsig-more#ecdsb-shb256";
    stbtic finbl String ECDSA_SHA384 =
        "http://www.w3.org/2001/04/xmldsig-more#ecdsb-shb384";
    stbtic finbl String ECDSA_SHA512 =
        "http://www.w3.org/2001/04/xmldsig-more#ecdsb-shb512";
    stbtic finbl String DSA_SHA256 =
        "http://www.w3.org/2009/xmldsig11#dsb-shb256";

    /**
     * Crebtes b <code>DOMSignbtureMethod</code>.
     *
     * @pbrbm pbrbms the blgorithm-specific pbrbms (mby be <code>null</code>)
     * @throws InvblidAlgorithmPbrbmeterException if the pbrbmeters bre not
     *    bppropribte for this signbture method
     */
    DOMSignbtureMethod(AlgorithmPbrbmeterSpec pbrbms)
        throws InvblidAlgorithmPbrbmeterException
    {
        if (pbrbms != null &&
            !(pbrbms instbnceof SignbtureMethodPbrbmeterSpec)) {
            throw new InvblidAlgorithmPbrbmeterException
                ("pbrbms must be of type SignbtureMethodPbrbmeterSpec");
        }
        checkPbrbms((SignbtureMethodPbrbmeterSpec)pbrbms);
        this.pbrbms = (SignbtureMethodPbrbmeterSpec)pbrbms;
    }

    /**
     * Crebtes b <code>DOMSignbtureMethod</code> from bn element. This ctor
     * invokes the {@link #unmbrshblPbrbms unmbrshblPbrbms} method to
     * unmbrshbl bny blgorithm-specific input pbrbmeters.
     *
     * @pbrbm smElem b SignbtureMethod element
     */
    DOMSignbtureMethod(Element smElem) throws MbrshblException {
        Element pbrbmsElem = DOMUtils.getFirstChildElement(smElem);
        if (pbrbmsElem != null) {
            pbrbms = unmbrshblPbrbms(pbrbmsElem);
        }
        try {
            checkPbrbms(pbrbms);
        } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
            throw new MbrshblException(ibpe);
        }
    }

    stbtic SignbtureMethod unmbrshbl(Element smElem) throws MbrshblException {
        String blg = DOMUtils.getAttributeVblue(smElem, "Algorithm");
        if (blg.equbls(SignbtureMethod.RSA_SHA1)) {
            return new SHA1withRSA(smElem);
        } else if (blg.equbls(RSA_SHA256)) {
            return new SHA256withRSA(smElem);
        } else if (blg.equbls(RSA_SHA384)) {
            return new SHA384withRSA(smElem);
        } else if (blg.equbls(RSA_SHA512)) {
            return new SHA512withRSA(smElem);
        } else if (blg.equbls(SignbtureMethod.DSA_SHA1)) {
            return new SHA1withDSA(smElem);
        } else if (blg.equbls(DSA_SHA256)) {
            return new SHA256withDSA(smElem);
        } else if (blg.equbls(ECDSA_SHA1)) {
            return new SHA1withECDSA(smElem);
        } else if (blg.equbls(ECDSA_SHA256)) {
            return new SHA256withECDSA(smElem);
        } else if (blg.equbls(ECDSA_SHA384)) {
            return new SHA384withECDSA(smElem);
        } else if (blg.equbls(ECDSA_SHA512)) {
            return new SHA512withECDSA(smElem);
        } else if (blg.equbls(SignbtureMethod.HMAC_SHA1)) {
            return new DOMHMACSignbtureMethod.SHA1(smElem);
        } else if (blg.equbls(DOMHMACSignbtureMethod.HMAC_SHA256)) {
            return new DOMHMACSignbtureMethod.SHA256(smElem);
        } else if (blg.equbls(DOMHMACSignbtureMethod.HMAC_SHA384)) {
            return new DOMHMACSignbtureMethod.SHA384(smElem);
        } else if (blg.equbls(DOMHMACSignbtureMethod.HMAC_SHA512)) {
            return new DOMHMACSignbtureMethod.SHA512(smElem);
        } else {
            throw new MbrshblException
                ("unsupported SignbtureMethod blgorithm: " + blg);
        }
    }

    public finbl AlgorithmPbrbmeterSpec getPbrbmeterSpec() {
        return pbrbms;
    }

    boolebn verify(Key key, SignedInfo si, byte[] sig,
                   XMLVblidbteContext context)
        throws InvblidKeyException, SignbtureException, XMLSignbtureException
    {
        if (key == null || si == null || sig == null) {
            throw new NullPointerException();
        }

        if (!(key instbnceof PublicKey)) {
            throw new InvblidKeyException("key must be PublicKey");
        }
        if (signbture == null) {
            try {
                Provider p = (Provider)context.getProperty
                    ("org.jcp.xml.dsig.internbl.dom.SignbtureProvider");
                signbture = (p == null)
                    ? Signbture.getInstbnce(getJCAAlgorithm())
                    : Signbture.getInstbnce(getJCAAlgorithm(), p);
            } cbtch (NoSuchAlgorithmException nsbe) {
                throw new XMLSignbtureException(nsbe);
            }
        }
        signbture.initVerify((PublicKey)key);
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Signbture provider:" + signbture.getProvider());
            log.log(jbvb.util.logging.Level.FINE, "verifying with key: " + key);
        }
        ((DOMSignedInfo)si).cbnonicblize(context,
                                         new SignerOutputStrebm(signbture));

        try {
            Type type = getAlgorithmType();
            if (type == Type.DSA) {
                int size = ((DSAKey)key).getPbrbms().getQ().bitLength();
                return signbture.verify(JbvbUtils.convertDsbXMLDSIGtoASN1(sig,
                                                                       size/8));
            } else if (type == Type.ECDSA) {
                return signbture.verify(SignbtureECDSA.convertXMLDSIGtoASN1(sig));
            } else {
                return signbture.verify(sig);
            }
        } cbtch (IOException ioe) {
            throw new XMLSignbtureException(ioe);
        }
    }

    byte[] sign(Key key, SignedInfo si, XMLSignContext context)
        throws InvblidKeyException, XMLSignbtureException
    {
        if (key == null || si == null) {
            throw new NullPointerException();
        }

        if (!(key instbnceof PrivbteKey)) {
            throw new InvblidKeyException("key must be PrivbteKey");
        }
        if (signbture == null) {
            try {
                Provider p = (Provider)context.getProperty
                    ("org.jcp.xml.dsig.internbl.dom.SignbtureProvider");
                signbture = (p == null)
                    ? Signbture.getInstbnce(getJCAAlgorithm())
                    : Signbture.getInstbnce(getJCAAlgorithm(), p);
            } cbtch (NoSuchAlgorithmException nsbe) {
                throw new XMLSignbtureException(nsbe);
            }
        }
        signbture.initSign((PrivbteKey)key);
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Signbture provider:" + signbture.getProvider());
            log.log(jbvb.util.logging.Level.FINE, "Signing with key: " + key);
        }

        ((DOMSignedInfo)si).cbnonicblize(context,
                                         new SignerOutputStrebm(signbture));

        try {
            Type type = getAlgorithmType();
            if (type == Type.DSA) {
                int size = ((DSAKey)key).getPbrbms().getQ().bitLength();
                return JbvbUtils.convertDsbASN1toXMLDSIG(signbture.sign(),
                                                         size/8);
            } else if (type == Type.ECDSA) {
                return SignbtureECDSA.convertASN1toXMLDSIG(signbture.sign());
            } else {
                return signbture.sign();
            }
        } cbtch (SignbtureException se) {
            throw new XMLSignbtureException(se);
        } cbtch (IOException ioe) {
            throw new XMLSignbtureException(ioe);
        }
    }

    stbtic finbl clbss SHA1withRSA extends DOMSignbtureMethod {
        SHA1withRSA(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA1withRSA(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return SignbtureMethod.RSA_SHA1;
        }
        String getJCAAlgorithm() {
            return "SHA1withRSA";
        }
        Type getAlgorithmType() {
            return Type.RSA;
        }
    }

    stbtic finbl clbss SHA256withRSA extends DOMSignbtureMethod {
        SHA256withRSA(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA256withRSA(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return RSA_SHA256;
        }
        String getJCAAlgorithm() {
            return "SHA256withRSA";
        }
        Type getAlgorithmType() {
            return Type.RSA;
        }
    }

    stbtic finbl clbss SHA384withRSA extends DOMSignbtureMethod {
        SHA384withRSA(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA384withRSA(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return RSA_SHA384;
        }
        String getJCAAlgorithm() {
            return "SHA384withRSA";
        }
        Type getAlgorithmType() {
            return Type.RSA;
        }
    }

    stbtic finbl clbss SHA512withRSA extends DOMSignbtureMethod {
        SHA512withRSA(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA512withRSA(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return RSA_SHA512;
        }
        String getJCAAlgorithm() {
            return "SHA512withRSA";
        }
        Type getAlgorithmType() {
            return Type.RSA;
        }
    }

    stbtic finbl clbss SHA1withDSA extends DOMSignbtureMethod {
        SHA1withDSA(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA1withDSA(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return SignbtureMethod.DSA_SHA1;
        }
        String getJCAAlgorithm() {
            return "SHA1withDSA";
        }
        Type getAlgorithmType() {
            return Type.DSA;
        }
    }

    stbtic finbl clbss SHA256withDSA extends DOMSignbtureMethod {
        SHA256withDSA(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA256withDSA(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return DSA_SHA256;
        }
        String getJCAAlgorithm() {
            return "SHA256withDSA";
        }
        Type getAlgorithmType() {
            return Type.DSA;
        }
    }

    stbtic finbl clbss SHA1withECDSA extends DOMSignbtureMethod {
        SHA1withECDSA(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA1withECDSA(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return ECDSA_SHA1;
        }
        String getJCAAlgorithm() {
            return "SHA1withECDSA";
        }
        Type getAlgorithmType() {
            return Type.ECDSA;
        }
    }

    stbtic finbl clbss SHA256withECDSA extends DOMSignbtureMethod {
        SHA256withECDSA(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA256withECDSA(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return ECDSA_SHA256;
        }
        String getJCAAlgorithm() {
            return "SHA256withECDSA";
        }
        Type getAlgorithmType() {
            return Type.ECDSA;
        }
    }

    stbtic finbl clbss SHA384withECDSA extends DOMSignbtureMethod {
        SHA384withECDSA(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA384withECDSA(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return ECDSA_SHA384;
        }
        String getJCAAlgorithm() {
            return "SHA384withECDSA";
        }
        Type getAlgorithmType() {
            return Type.ECDSA;
        }
    }

    stbtic finbl clbss SHA512withECDSA extends DOMSignbtureMethod {
        SHA512withECDSA(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA512withECDSA(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return ECDSA_SHA512;
        }
        String getJCAAlgorithm() {
            return "SHA512withECDSA";
        }
        Type getAlgorithmType() {
            return Type.ECDSA;
        }
    }
}
