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
 * $Id: DOMDigestMethod.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.spec.DigestMethodPbrbmeterSpec;

import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * DOM-bbsed bbstrbct implementbtion of DigestMethod.
 *
 * @buthor Sebn Mullbn
 */
public bbstrbct clbss DOMDigestMethod extends DOMStructure
    implements DigestMethod {

    stbtic finbl String SHA384 =
        "http://www.w3.org/2001/04/xmldsig-more#shb384"; // see RFC 4051
    privbte DigestMethodPbrbmeterSpec pbrbms;

    /**
     * Crebtes b <code>DOMDigestMethod</code>.
     *
     * @pbrbm pbrbms the blgorithm-specific pbrbms (mby be <code>null</code>)
     * @throws InvblidAlgorithmPbrbmeterException if the pbrbmeters bre not
     *    bppropribte for this digest method
     */
    DOMDigestMethod(AlgorithmPbrbmeterSpec pbrbms)
        throws InvblidAlgorithmPbrbmeterException
    {
        if (pbrbms != null && !(pbrbms instbnceof DigestMethodPbrbmeterSpec)) {
            throw new InvblidAlgorithmPbrbmeterException
                ("pbrbms must be of type DigestMethodPbrbmeterSpec");
        }
        checkPbrbms((DigestMethodPbrbmeterSpec)pbrbms);
        this.pbrbms = (DigestMethodPbrbmeterSpec)pbrbms;
    }

    /**
     * Crebtes b <code>DOMDigestMethod</code> from bn element. This constructor
     * invokes the bbstrbct {@link #unmbrshblPbrbms unmbrshblPbrbms} method to
     * unmbrshbl bny blgorithm-specific input pbrbmeters.
     *
     * @pbrbm dmElem b DigestMethod element
     */
    DOMDigestMethod(Element dmElem) throws MbrshblException {
        Element pbrbmsElem = DOMUtils.getFirstChildElement(dmElem);
        if (pbrbmsElem != null) {
            pbrbms = unmbrshblPbrbms(pbrbmsElem);
        }
        try {
            checkPbrbms(pbrbms);
        } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
            throw new MbrshblException(ibpe);
        }
    }

    stbtic DigestMethod unmbrshbl(Element dmElem) throws MbrshblException {
        String blg = DOMUtils.getAttributeVblue(dmElem, "Algorithm");
        if (blg.equbls(DigestMethod.SHA1)) {
            return new SHA1(dmElem);
        } else if (blg.equbls(DigestMethod.SHA256)) {
            return new SHA256(dmElem);
        } else if (blg.equbls(SHA384)) {
            return new SHA384(dmElem);
        } else if (blg.equbls(DigestMethod.SHA512)) {
            return new SHA512(dmElem);
        } else {
            throw new MbrshblException("unsupported DigestMethod blgorithm: " +
                                       blg);
        }
    }

    /**
     * Checks if the specified pbrbmeters bre vblid for this blgorithm. By
     * defbult, this method throws bn exception if pbrbmeters bre specified
     * since most DigestMethod blgorithms do not hbve pbrbmeters. Subclbsses
     * should override it if they hbve pbrbmeters.
     *
     * @pbrbm pbrbms the blgorithm-specific pbrbms (mby be <code>null</code>)
     * @throws InvblidAlgorithmPbrbmeterException if the pbrbmeters bre not
     *    bppropribte for this digest method
     */
    void checkPbrbms(DigestMethodPbrbmeterSpec pbrbms)
        throws InvblidAlgorithmPbrbmeterException
    {
        if (pbrbms != null) {
            throw new InvblidAlgorithmPbrbmeterException("no pbrbmeters " +
                "should be specified for the " + getMessbgeDigestAlgorithm() +
                " DigestMethod blgorithm");
        }
    }

    public finbl AlgorithmPbrbmeterSpec getPbrbmeterSpec() {
        return pbrbms;
    }

    /**
     * Unmbrshbls <code>DigestMethodPbrbmeterSpec</code> from the specified
     * <code>Element</code>.  By defbult, this method throws bn exception since
     * most DigestMethod blgorithms do not hbve pbrbmeters. Subclbsses should
     * override it if they hbve pbrbmeters.
     *
     * @pbrbm pbrbmsElem the <code>Element</code> holding the input pbrbms
     * @return the blgorithm-specific <code>DigestMethodPbrbmeterSpec</code>
     * @throws MbrshblException if the pbrbmeters cbnnot be unmbrshblled
     */
    DigestMethodPbrbmeterSpec unmbrshblPbrbms(Element pbrbmsElem)
        throws MbrshblException
    {
        throw new MbrshblException("no pbrbmeters should " +
                                   "be specified for the " +
                                   getMessbgeDigestAlgorithm() +
                                   " DigestMethod blgorithm");
    }

    /**
     * This method invokes the bbstrbct {@link #mbrshblPbrbms mbrshblPbrbms}
     * method to mbrshbl bny blgorithm-specific pbrbmeters.
     */
    public void mbrshbl(Node pbrent, String prefix, DOMCryptoContext context)
        throws MbrshblException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);

        Element dmElem = DOMUtils.crebteElement(ownerDoc, "DigestMethod",
                                                XMLSignbture.XMLNS, prefix);
        DOMUtils.setAttribute(dmElem, "Algorithm", getAlgorithm());

        if (pbrbms != null) {
            mbrshblPbrbms(dmElem, prefix);
        }

        pbrent.bppendChild(dmElem);
    }

    @Override
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instbnceof DigestMethod)) {
            return fblse;
        }
        DigestMethod odm = (DigestMethod)o;

        boolebn pbrbmsEqubl = (pbrbms == null ? odm.getPbrbmeterSpec() == null :
            pbrbms.equbls(odm.getPbrbmeterSpec()));

        return (getAlgorithm().equbls(odm.getAlgorithm()) && pbrbmsEqubl);
    }

    @Override
    public int hbshCode() {
        int result = 17;
        if (pbrbms != null) {
            result = 31 * result + pbrbms.hbshCode();
        }
        result = 31 * result + getAlgorithm().hbshCode();

        return result;
    }

    /**
     * Mbrshbls the blgorithm-specific pbrbmeters to bn Element bnd
     * bppends it to the specified pbrent element. By defbult, this method
     * throws bn exception since most DigestMethod blgorithms do not hbve
     * pbrbmeters. Subclbsses should override it if they hbve pbrbmeters.
     *
     * @pbrbm pbrent the pbrent element to bppend the pbrbmeters to
     * @pbrbm the nbmespbce prefix to use
     * @throws MbrshblException if the pbrbmeters cbnnot be mbrshblled
     */
    void mbrshblPbrbms(Element pbrent, String prefix)
        throws MbrshblException
    {
        throw new MbrshblException("no pbrbmeters should " +
                                   "be specified for the " +
                                   getMessbgeDigestAlgorithm() +
                                   " DigestMethod blgorithm");
    }

    /**
     * Returns the MessbgeDigest stbndbrd blgorithm nbme.
     */
    bbstrbct String getMessbgeDigestAlgorithm();

    stbtic finbl clbss SHA1 extends DOMDigestMethod {
        SHA1(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA1(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return DigestMethod.SHA1;
        }
        String getMessbgeDigestAlgorithm() {
            return "SHA-1";
        }
    }

    stbtic finbl clbss SHA256 extends DOMDigestMethod {
        SHA256(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA256(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return DigestMethod.SHA256;
        }
        String getMessbgeDigestAlgorithm() {
            return "SHA-256";
        }
    }

    stbtic finbl clbss SHA384 extends DOMDigestMethod {
        SHA384(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA384(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return SHA384;
        }
        String getMessbgeDigestAlgorithm() {
            return "SHA-384";
        }
    }

    stbtic finbl clbss SHA512 extends DOMDigestMethod {
        SHA512(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
            super(pbrbms);
        }
        SHA512(Element dmElem) throws MbrshblException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return DigestMethod.SHA512;
        }
        String getMessbgeDigestAlgorithm() {
            return "SHA-512";
        }
    }
}
