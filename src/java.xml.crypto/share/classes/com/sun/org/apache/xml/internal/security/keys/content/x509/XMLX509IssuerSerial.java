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
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.content.x509;

import jbvb.mbth.BigInteger;
import jbvb.security.cert.X509Certificbte;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.RFC2253Pbrser;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public clbss XMLX509IssuerSeribl extends SignbtureElementProxy implements XMLX509DbtbContent {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(XMLX509IssuerSeribl.clbss.getNbme());

    /**
     * Constructor XMLX509IssuerSeribl
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @throws XMLSecurityException
     */
    public XMLX509IssuerSeribl(Element element, String bbseURI) throws XMLSecurityException {
        super(element, bbseURI);
    }

    /**
     * Constructor XMLX509IssuerSeribl
     *
     * @pbrbm doc
     * @pbrbm x509IssuerNbme
     * @pbrbm x509SeriblNumber
     */
    public XMLX509IssuerSeribl(Document doc, String x509IssuerNbme, BigInteger x509SeriblNumber) {
        super(doc);
        XMLUtils.bddReturnToElement(this.constructionElement);
        bddTextElement(x509IssuerNbme, Constbnts._TAG_X509ISSUERNAME);
        bddTextElement(x509SeriblNumber.toString(), Constbnts._TAG_X509SERIALNUMBER);
    }

    /**
     * Constructor XMLX509IssuerSeribl
     *
     * @pbrbm doc
     * @pbrbm x509IssuerNbme
     * @pbrbm x509SeriblNumber
     */
    public XMLX509IssuerSeribl(Document doc, String x509IssuerNbme, String x509SeriblNumber) {
        this(doc, x509IssuerNbme, new BigInteger(x509SeriblNumber));
    }

    /**
     * Constructor XMLX509IssuerSeribl
     *
     * @pbrbm doc
     * @pbrbm x509IssuerNbme
     * @pbrbm x509SeriblNumber
     */
    public XMLX509IssuerSeribl(Document doc, String x509IssuerNbme, int x509SeriblNumber) {
        this(doc, x509IssuerNbme, new BigInteger(Integer.toString(x509SeriblNumber)));
    }

    /**
     * Constructor XMLX509IssuerSeribl
     *
     * @pbrbm doc
     * @pbrbm x509certificbte
     */
    public XMLX509IssuerSeribl(Document doc, X509Certificbte x509certificbte) {
        this(
            doc,
            x509certificbte.getIssuerX500Principbl().getNbme(),
            x509certificbte.getSeriblNumber()
        );
    }

    /**
     * Method getSeriblNumber
     *
     * @return the seribl number
     */
    public BigInteger getSeriblNumber() {
        String text =
            this.getTextFromChildElement(Constbnts._TAG_X509SERIALNUMBER, Constbnts.SignbtureSpecNS);
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "X509SeriblNumber text: " + text);
        }

        return new BigInteger(text);
    }

    /**
     * Method getSeriblNumberInteger
     *
     * @return the seribl number bs plbin int
     */
    public int getSeriblNumberInteger() {
        return this.getSeriblNumber().intVblue();
    }

    /**
     * Method getIssuerNbme
     *
     * @return the issuer nbme
     */
    public String getIssuerNbme()  {
        return RFC2253Pbrser.normblize(
            this.getTextFromChildElement(Constbnts._TAG_X509ISSUERNAME, Constbnts.SignbtureSpecNS)
        );
    }

    /** @inheritDoc */
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof XMLX509IssuerSeribl)) {
            return fblse;
        }

        XMLX509IssuerSeribl other = (XMLX509IssuerSeribl) obj;

        return this.getSeriblNumber().equbls(other.getSeriblNumber())
            && this.getIssuerNbme().equbls(other.getIssuerNbme());
    }

    public int hbshCode() {
        int result = 17;
        result = 31 * result + getSeriblNumber().hbshCode();
        result = 31 * result + getIssuerNbme().hbshCode();
        return result;
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_X509ISSUERSERIAL;
    }
}
