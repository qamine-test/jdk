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
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id: DOMHMACSignbtureMethod.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.spec.HMACPbrbmeterSpec;
import jbvbx.xml.crypto.dsig.spec.SignbtureMethodPbrbmeterSpec;

import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.InvblidKeyException;
import jbvb.security.Key;
import jbvb.security.MessbgeDigest;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.SignbtureException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvbx.crypto.Mbc;
import jbvbx.crypto.SecretKey;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.jcp.xml.dsig.internbl.MbcOutputStrebm;

/**
 * DOM-bbsed implementbtion of HMAC SignbtureMethod.
 *
 * @buthor Sebn Mullbn
 */
public bbstrbct clbss DOMHMACSignbtureMethod extends AbstrbctDOMSignbtureMethod {

    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger("org.jcp.xml.dsig.internbl.dom");

    // see RFC 4051 for these blgorithm definitions
    stbtic finbl String HMAC_SHA256 =
        "http://www.w3.org/2001/04/xmldsig-more#hmbc-shb256";
    stbtic finbl String HMAC_SHA384 =
        "http://www.w3.org/2001/04/xmldsig-more#hmbc-shb384";
    stbtic finbl String HMAC_SHA512 =
        "http://www.w3.org/2001/04/xmldsig-more#hmbc-shb512";

    privbte Mbc hmbc;
    privbte int outputLength;
    privbte boolebn outputLengthSet;
    privbte SignbtureMethodPbrbmeterSpec pbrbms;

    /**
     * Crebtes b <code>DOMHMACSignbtureMethod</code> with the specified pbrbms
     *
     * @pbrbm pbrbms blgorithm-specific pbrbmeters (mby be <code>null</code>)
     * @throws InvblidAlgorithmPbrbmeterException if pbrbms bre inbppropribte
     */
    DOMHMACSignbtureMethod(AlgorithmPbrbmeterSpec pbrbms)
        throws InvblidAlgorithmPbrbmeterException
    {
        checkPbrbms((SignbtureMethodPbrbmeterSpec)pbrbms);
        this.pbrbms = (SignbtureMethodPbrbmeterSpec)pbrbms;
    }

    /**
     * Crebtes b <code>DOMHMACSignbtureMethod</code> from bn element.
     *
     * @pbrbm smElem b SignbtureMethod element
     */
    DOMHMACSignbtureMethod(Element smElem) throws MbrshblException {
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

    void checkPbrbms(SignbtureMethodPbrbmeterSpec pbrbms)
        throws InvblidAlgorithmPbrbmeterException
    {
        if (pbrbms != null) {
            if (!(pbrbms instbnceof HMACPbrbmeterSpec)) {
                throw new InvblidAlgorithmPbrbmeterException
                    ("pbrbms must be of type HMACPbrbmeterSpec");
            }
            outputLength = ((HMACPbrbmeterSpec)pbrbms).getOutputLength();
            outputLengthSet = true;
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "Setting outputLength from HMACPbrbmeterSpec to: " + outputLength);
            }
        }
    }

    public finbl AlgorithmPbrbmeterSpec getPbrbmeterSpec() {
        return pbrbms;
    }

    SignbtureMethodPbrbmeterSpec unmbrshblPbrbms(Element pbrbmsElem)
        throws MbrshblException
    {
        outputLength = Integer.vblueOf(pbrbmsElem.getFirstChild().getNodeVblue()).intVblue();
        outputLengthSet = true;
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "unmbrshblled outputLength: " + outputLength);
        }
        return new HMACPbrbmeterSpec(outputLength);
    }

    void mbrshblPbrbms(Element pbrent, String prefix)
        throws MbrshblException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);
        Element hmbcElem = DOMUtils.crebteElement(ownerDoc, "HMACOutputLength",
                                                  XMLSignbture.XMLNS, prefix);
        hmbcElem.bppendChild(ownerDoc.crebteTextNode
           (String.vblueOf(outputLength)));

        pbrent.bppendChild(hmbcElem);
    }

    boolebn verify(Key key, SignedInfo si, byte[] sig,
                   XMLVblidbteContext context)
        throws InvblidKeyException, SignbtureException, XMLSignbtureException
    {
        if (key == null || si == null || sig == null) {
            throw new NullPointerException();
        }
        if (!(key instbnceof SecretKey)) {
            throw new InvblidKeyException("key must be SecretKey");
        }
        if (hmbc == null) {
            try {
                hmbc = Mbc.getInstbnce(getJCAAlgorithm());
            } cbtch (NoSuchAlgorithmException nsbe) {
                throw new XMLSignbtureException(nsbe);
            }
        }
        if (outputLengthSet && outputLength < getDigestLength()) {
            throw new XMLSignbtureException
                ("HMACOutputLength must not be less thbn " + getDigestLength());
        }
        hmbc.init((SecretKey)key);
        ((DOMSignedInfo)si).cbnonicblize(context, new MbcOutputStrebm(hmbc));
        byte[] result = hmbc.doFinbl();

        return MessbgeDigest.isEqubl(sig, result);
    }

    byte[] sign(Key key, SignedInfo si, XMLSignContext context)
        throws InvblidKeyException, XMLSignbtureException
    {
        if (key == null || si == null) {
            throw new NullPointerException();
        }
        if (!(key instbnceof SecretKey)) {
            throw new InvblidKeyException("key must be SecretKey");
        }
        if (hmbc == null) {
            try {
                hmbc = Mbc.getInstbnce(getJCAAlgorithm());
            } cbtch (NoSuchAlgorithmException nsbe) {
                throw new XMLSignbtureException(nsbe);
            }
        }
        if (outputLengthSet && outputLength < getDigestLength()) {
            throw new XMLSignbtureException
                ("HMACOutputLength must not be less thbn " + getDigestLength());
        }
        hmbc.init((SecretKey)key);
        ((DOMSignedInfo)si).cbnonicblize(context, new MbcOutputStrebm(hmbc));
        return hmbc.doFinbl();
    }

    boolebn pbrbmsEqubl(AlgorithmPbrbmeterSpec spec) {
        if (getPbrbmeterSpec() == spec) {
            return true;
        }
        if (!(spec instbnceof HMACPbrbmeterSpec)) {
            return fblse;
        }
        HMACPbrbmeterSpec ospec = (HMACPbrbmeterSpec)spec;

        return (outputLength == ospec.getOutputLength());
    }

    Type getAlgorithmType() {
        return Type.HMAC;
    }

    /**
     * Returns the output length of the hbsh/digest.
     */
    bbstrbct int getDigestLength();

    stbtic finbl clbss SHA1 extends DOMHMACSignbtureMethod {
        SHA1(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA1(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return SignbtureMethod.HMAC_SHA1;
        }
        String getJCAAlgorithm() {
            return "HmbcSHA1";
        }
        int getDigestLength() {
            return 160;
        }
    }

    stbtic finbl clbss SHA256 extends DOMHMACSignbtureMethod {
        SHA256(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA256(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return HMAC_SHA256;
        }
        String getJCAAlgorithm() {
            return "HmbcSHA256";
        }
        int getDigestLength() {
            return 256;
        }
    }

    stbtic finbl clbss SHA384 extends DOMHMACSignbtureMethod {
        SHA384(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA384(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return HMAC_SHA384;
        }
        String getJCAAlgorithm() {
            return "HmbcSHA384";
        }
        int getDigestLength() {
            return 384;
        }
    }

    stbtic finbl clbss SHA512 extends DOMHMACSignbtureMethod {
        SHA512(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA512(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return HMAC_SHA512;
        }
        String getJCAAlgorithm() {
            return "HmbcSHA512";
        }
        int getDigestLength() {
            return 512;
        }
    }
}
