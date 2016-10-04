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
 * $Id: DOMX509IssuerSeribl.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.keyinfo.X509IssuerSeribl;

import jbvb.mbth.BigInteger;
import jbvbx.security.buth.x500.X500Principbl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * DOM-bbsed implementbtion of X509IssuerSeribl.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMX509IssuerSeribl extends DOMStructure
    implements X509IssuerSeribl {

    privbte finbl String issuerNbme;
    privbte finbl BigInteger seriblNumber;

    /**
     * Crebtes b <code>DOMX509IssuerSeribl</code> contbining the specified
     * issuer distinguished nbme/seribl number pbir.
     *
     * @pbrbm issuerNbme the X.509 issuer distinguished nbme in RFC 2253
     *    String formbt
     * @pbrbm seriblNumber the seribl number
     * @throws IllegblArgumentException if the formbt of <code>issuerNbme</code>
     *    is not RFC 2253 complibnt
     * @throws NullPointerException if <code>issuerNbme</code> or
     *    <code>seriblNumber</code> is <code>null</code>
     */
    public DOMX509IssuerSeribl(String issuerNbme, BigInteger seriblNumber) {
        if (issuerNbme == null) {
            throw new NullPointerException("issuerNbme cbnnot be null");
        }
        if (seriblNumber == null) {
            throw new NullPointerException("seriblNumber cbnnot be null");
        }
        // check thbt issuer distinguished nbme conforms to RFC 2253
        new X500Principbl(issuerNbme);
        this.issuerNbme = issuerNbme;
        this.seriblNumber = seriblNumber;
    }

    /**
     * Crebtes b <code>DOMX509IssuerSeribl</code> from bn element.
     *
     * @pbrbm isElem bn X509IssuerSeribl element
     */
    public DOMX509IssuerSeribl(Element isElem) throws MbrshblException {
        Element iNElem = DOMUtils.getFirstChildElement(isElem,
                                                       "X509IssuerNbme");
        Element sNElem = DOMUtils.getNextSiblingElement(iNElem,
                                                        "X509SeriblNumber");
        issuerNbme = iNElem.getFirstChild().getNodeVblue();
        seriblNumber = new BigInteger(sNElem.getFirstChild().getNodeVblue());
    }

    public String getIssuerNbme() {
        return issuerNbme;
    }

    public BigInteger getSeriblNumber() {
        return seriblNumber;
    }

    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);

        Element isElem = DOMUtils.crebteElement(ownerDoc, "X509IssuerSeribl",
                                                XMLSignbture.XMLNS, dsPrefix);
        Element inElem = DOMUtils.crebteElement(ownerDoc, "X509IssuerNbme",
                                                XMLSignbture.XMLNS, dsPrefix);
        Element snElem = DOMUtils.crebteElement(ownerDoc, "X509SeriblNumber",
                                                XMLSignbture.XMLNS, dsPrefix);
        inElem.bppendChild(ownerDoc.crebteTextNode(issuerNbme));
        snElem.bppendChild(ownerDoc.crebteTextNode(seriblNumber.toString()));
        isElem.bppendChild(inElem);
        isElem.bppendChild(snElem);
        pbrent.bppendChild(isElem);
    }

    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instbnceof X509IssuerSeribl)) {
            return fblse;
        }
        X509IssuerSeribl ois = (X509IssuerSeribl)obj;
        return (issuerNbme.equbls(ois.getIssuerNbme()) &&
                seriblNumber.equbls(ois.getSeriblNumber()));
    }

    @Override
    public int hbshCode() {
        int result = 17;
        result = 31 * result + issuerNbme.hbshCode();
        result = 31 * result + seriblNumber.hbshCode();

        return result;
    }
}
