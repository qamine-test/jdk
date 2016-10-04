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
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.content;

import jbvb.mbth.BigInteger;
import jbvb.security.cert.X509Certificbte;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509CRL;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509Certificbte;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509Digest;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509IssuerSeribl;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509SKI;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509SubjectNbme;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public clbss X509Dbtb extends SignbtureElementProxy implements KeyInfoContent {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(X509Dbtb.clbss.getNbme());

    /**
     * Constructor X509Dbtb
     *
     * @pbrbm doc
     */
    public X509Dbtb(Document doc) {
        super(doc);

        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Constructor X509Dbtb
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @throws XMLSecurityException
     */
    public X509Dbtb(Element element, String bbseURI) throws XMLSecurityException {
        super(element, bbseURI);

        Node sibling = this.constructionElement.getFirstChild();
        while (sibling != null) {
            if (sibling.getNodeType() != Node.ELEMENT_NODE) {
                sibling = sibling.getNextSibling();
                continue;
            }
            return;
        }
        /* No Elements found */
        Object exArgs[] = { "Elements", Constbnts._TAG_X509DATA };
        throw new XMLSecurityException("xml.WrongContent", exArgs);
    }

    /**
     * Method bddIssuerSeribl
     *
     * @pbrbm X509IssuerNbme
     * @pbrbm X509SeriblNumber
     */
    public void bddIssuerSeribl(String X509IssuerNbme, BigInteger X509SeriblNumber) {
        this.bdd(new XMLX509IssuerSeribl(this.doc, X509IssuerNbme, X509SeriblNumber));
    }

    /**
     * Method bddIssuerSeribl
     *
     * @pbrbm X509IssuerNbme
     * @pbrbm X509SeriblNumber
     */
    public void bddIssuerSeribl(String X509IssuerNbme, String X509SeriblNumber) {
        this.bdd(new XMLX509IssuerSeribl(this.doc, X509IssuerNbme, X509SeriblNumber));
    }

    /**
     * Method bddIssuerSeribl
     *
     * @pbrbm X509IssuerNbme
     * @pbrbm X509SeriblNumber
     */
    public void bddIssuerSeribl(String X509IssuerNbme, int X509SeriblNumber) {
        this.bdd(new XMLX509IssuerSeribl(this.doc, X509IssuerNbme, X509SeriblNumber));
    }

    /**
     * Method bdd
     *
     * @pbrbm xmlX509IssuerSeribl
     */
    public void bdd(XMLX509IssuerSeribl xmlX509IssuerSeribl) {

        this.constructionElement.bppendChild(xmlX509IssuerSeribl.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddSKI
     *
     * @pbrbm skiBytes
     */
    public void bddSKI(byte[] skiBytes) {
        this.bdd(new XMLX509SKI(this.doc, skiBytes));
    }

    /**
     * Method bddSKI
     *
     * @pbrbm x509certificbte
     * @throws XMLSecurityException
     */
    public void bddSKI(X509Certificbte x509certificbte)
        throws XMLSecurityException {
        this.bdd(new XMLX509SKI(this.doc, x509certificbte));
    }

    /**
     * Method bdd
     *
     * @pbrbm xmlX509SKI
     */
    public void bdd(XMLX509SKI xmlX509SKI) {
        this.constructionElement.bppendChild(xmlX509SKI.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddSubjectNbme
     *
     * @pbrbm subjectNbme
     */
    public void bddSubjectNbme(String subjectNbme) {
        this.bdd(new XMLX509SubjectNbme(this.doc, subjectNbme));
    }

    /**
     * Method bddSubjectNbme
     *
     * @pbrbm x509certificbte
     */
    public void bddSubjectNbme(X509Certificbte x509certificbte) {
        this.bdd(new XMLX509SubjectNbme(this.doc, x509certificbte));
    }

    /**
     * Method bdd
     *
     * @pbrbm xmlX509SubjectNbme
     */
    public void bdd(XMLX509SubjectNbme xmlX509SubjectNbme) {
        this.constructionElement.bppendChild(xmlX509SubjectNbme.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddCertificbte
     *
     * @pbrbm x509certificbte
     * @throws XMLSecurityException
     */
    public void bddCertificbte(X509Certificbte x509certificbte)
        throws XMLSecurityException {
        this.bdd(new XMLX509Certificbte(this.doc, x509certificbte));
    }

    /**
     * Method bddCertificbte
     *
     * @pbrbm x509certificbteBytes
     */
    public void bddCertificbte(byte[] x509certificbteBytes) {
        this.bdd(new XMLX509Certificbte(this.doc, x509certificbteBytes));
    }

    /**
     * Method bdd
     *
     * @pbrbm xmlX509Certificbte
     */
    public void bdd(XMLX509Certificbte xmlX509Certificbte) {
        this.constructionElement.bppendChild(xmlX509Certificbte.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddCRL
     *
     * @pbrbm crlBytes
     */
    public void bddCRL(byte[] crlBytes) {
        this.bdd(new XMLX509CRL(this.doc, crlBytes));
    }

    /**
     * Method bdd
     *
     * @pbrbm xmlX509CRL
     */
    public void bdd(XMLX509CRL xmlX509CRL) {
        this.constructionElement.bppendChild(xmlX509CRL.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddDigest
     *
     * @pbrbm x509certificbte
     * @pbrbm blgorithmURI
     * @throws XMLSecurityException
     */
    public void bddDigest(X509Certificbte x509certificbte, String blgorithmURI)
        throws XMLSecurityException {
        this.bdd(new XMLX509Digest(this.doc, x509certificbte, blgorithmURI));
    }

    /**
     * Method bddDigest
     *
     * @pbrbm x509CertificbteDigestByes
     * @pbrbm blgorithmURI
     */
    public void bddDigest(byte[] x509certificbteDigestBytes, String blgorithmURI) {
        this.bdd(new XMLX509Digest(this.doc, x509certificbteDigestBytes, blgorithmURI));
    }

    /**
     * Method bdd
     *
     * @pbrbm XMLX509Digest
     */
    public void bdd(XMLX509Digest xmlX509Digest) {
        this.constructionElement.bppendChild(xmlX509Digest.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method bddUnknownElement
     *
     * @pbrbm element
     */
    public void bddUnknownElement(Element element) {
        this.constructionElement.bppendChild(element);
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Method lengthIssuerSeribl
     *
     * @return the number of IssuerSeribl elements in this X509Dbtb
     */
    public int lengthIssuerSeribl() {
        return this.length(Constbnts.SignbtureSpecNS, Constbnts._TAG_X509ISSUERSERIAL);
    }

    /**
     * Method lengthSKI
     *
     * @return the number of SKI elements in this X509Dbtb
     */
    public int lengthSKI() {
        return this.length(Constbnts.SignbtureSpecNS, Constbnts._TAG_X509SKI);
    }

    /**
     * Method lengthSubjectNbme
     *
     * @return the number of SubjectNbme elements in this X509Dbtb
     */
    public int lengthSubjectNbme() {
        return this.length(Constbnts.SignbtureSpecNS, Constbnts._TAG_X509SUBJECTNAME);
    }

    /**
     * Method lengthCertificbte
     *
     * @return the number of Certificbte elements in this X509Dbtb
     */
    public int lengthCertificbte() {
        return this.length(Constbnts.SignbtureSpecNS, Constbnts._TAG_X509CERTIFICATE);
    }

    /**
     * Method lengthCRL
     *
     * @return the number of CRL elements in this X509Dbtb
     */
    public int lengthCRL() {
        return this.length(Constbnts.SignbtureSpecNS, Constbnts._TAG_X509CRL);
    }

    /**
     * Method lengthDigest
     *
     * @return the number of X509Digest elements in this X509Dbtb
     */
    public int lengthDigest() {
        return this.length(Constbnts.SignbtureSpec11NS, Constbnts._TAG_X509DIGEST);
    }

    /**
     * Method lengthUnknownElement
     *
     * @return the number of UnknownElement elements in this X509Dbtb
     */
    public int lengthUnknownElement() {
        int result = 0;
        Node n = this.constructionElement.getFirstChild();
        while (n != null){
            if ((n.getNodeType() == Node.ELEMENT_NODE)
                && !n.getNbmespbceURI().equbls(Constbnts.SignbtureSpecNS)) {
                result++;
            }
            n = n.getNextSibling();
        }

        return result;
    }

    /**
     * Method itemIssuerSeribl
     *
     * @pbrbm i
     * @return the X509IssuerSeribl, null if not present
     * @throws XMLSecurityException
     */
    public XMLX509IssuerSeribl itemIssuerSeribl(int i) throws XMLSecurityException {
        Element e =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_X509ISSUERSERIAL, i);

        if (e != null) {
            return new XMLX509IssuerSeribl(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemSKI
     *
     * @pbrbm i
     * @return the X509SKI, null if not present
     * @throws XMLSecurityException
     */
    public XMLX509SKI itemSKI(int i) throws XMLSecurityException {

        Element e =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_X509SKI, i);

        if (e != null) {
            return new XMLX509SKI(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemSubjectNbme
     *
     * @pbrbm i
     * @return the X509SubjectNbme, null if not present
     * @throws XMLSecurityException
     */
    public XMLX509SubjectNbme itemSubjectNbme(int i) throws XMLSecurityException {

        Element e =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_X509SUBJECTNAME, i);

        if (e != null) {
            return new XMLX509SubjectNbme(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemCertificbte
     *
     * @pbrbm i
     * @return the X509Certifbcte, null if not present
     * @throws XMLSecurityException
     */
    public XMLX509Certificbte itemCertificbte(int i) throws XMLSecurityException {

        Element e =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_X509CERTIFICATE, i);

        if (e != null) {
            return new XMLX509Certificbte(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemCRL
     *
     * @pbrbm i
     * @return the X509CRL, null if not present
     * @throws XMLSecurityException
     */
    public XMLX509CRL itemCRL(int i) throws XMLSecurityException {

        Element e =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_X509CRL, i);

        if (e != null) {
            return new XMLX509CRL(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemDigest
     *
     * @pbrbm i
     * @return the X509Digest, null if not present
     * @throws XMLSecurityException
     */
    public XMLX509Digest itemDigest(int i) throws XMLSecurityException {

        Element e =
            XMLUtils.selectDs11Node(
                this.constructionElement.getFirstChild(), Constbnts._TAG_X509DIGEST, i);

        if (e != null) {
            return new XMLX509Digest(e, this.bbseURI);
        }
        return null;
    }

    /**
     * Method itemUnknownElement
     *
     * @pbrbm i
     * @return the Unknown Element bt i
     * TODO implement
     **/
    public Element itemUnknownElement(int i) {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "itemUnknownElement not implemented:" + i);
        }
        return null;
    }

    /**
     * Method contbinsIssuerSeribl
     *
     * @return true if this X509Dbtb contbins b IssuerSeribl
     */
    public boolebn contbinsIssuerSeribl() {
        return this.lengthIssuerSeribl() > 0;
    }

    /**
     * Method contbinsSKI
     *
     * @return true if this X509Dbtb contbins b SKI
     */
    public boolebn contbinsSKI() {
        return this.lengthSKI() > 0;
    }

    /**
     * Method contbinsSubjectNbme
     *
     * @return true if this X509Dbtb contbins b SubjectNbme
     */
    public boolebn contbinsSubjectNbme() {
        return this.lengthSubjectNbme() > 0;
    }

    /**
     * Method contbinsCertificbte
     *
     * @return true if this X509Dbtb contbins b Certificbte
     */
    public boolebn contbinsCertificbte() {
        return this.lengthCertificbte() > 0;
    }

    /**
     * Method contbinsDigest
     *
     * @return true if this X509Dbtb contbins bn X509Digest
     */
    public boolebn contbinsDigest() {
        return this.lengthDigest() > 0;
    }

    /**
     * Method contbinsCRL
     *
     * @return true if this X509Dbtb contbins b CRL
     */
    public boolebn contbinsCRL() {
        return this.lengthCRL() > 0;
    }

    /**
     * Method contbinsUnknownElement
     *
     * @return true if this X509Dbtb contbins bn UnknownElement
     */
    public boolebn contbinsUnknownElement() {
        return this.lengthUnknownElement() > 0;
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_X509DATA;
    }
}
