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

import jbvb.security.MessbgeDigest;
import jbvb.security.cert.X509Certificbte;

import com.sun.org.bpbche.xml.internbl.security.blgorithms.JCEMbpper;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.Signbture11ElementProxy;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Provides content model support for the <code>dsig11:X509Digest</code> element.
 *
 * @buthor Brent Putmbn (putmbnb@georgetown.edu)
 */
public clbss XMLX509Digest extends Signbture11ElementProxy implements XMLX509DbtbContent {

    /**
     * Constructor XMLX509Digest
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    public XMLX509Digest(Element element, String BbseURI) throws XMLSecurityException {
        super(element, BbseURI);
    }

    /**
     * Constructor XMLX509Digest
     *
     * @pbrbm doc
     * @pbrbm digestBytes
     * @pbrbm blgorithmURI
     */
    public XMLX509Digest(Document doc, byte[] digestBytes, String blgorithmURI) {
        super(doc);
        this.bddBbse64Text(digestBytes);
        this.constructionElement.setAttributeNS(null, Constbnts._ATT_ALGORITHM, blgorithmURI);
    }

    /**
     * Constructor XMLX509Digest
     *
     * @pbrbm doc
     * @pbrbm x509certificbte
     * @pbrbm blgorithmURI
     * @throws XMLSecurityException
     */
    public XMLX509Digest(Document doc, X509Certificbte x509certificbte, String blgorithmURI) throws XMLSecurityException {
        super(doc);
        this.bddBbse64Text(getDigestBytesFromCert(x509certificbte, blgorithmURI));
        this.constructionElement.setAttributeNS(null, Constbnts._ATT_ALGORITHM, blgorithmURI);
    }

    /**
     * Method getAlgorithmAttr
     *
     * @return the Algorithm bttribute
     */
    public Attr getAlgorithmAttr() {
        return this.constructionElement.getAttributeNodeNS(null, Constbnts._ATT_ALGORITHM);
    }

    /**
     * Method getAlgorithm
     *
     * @return Algorithm string
     */
    public String getAlgorithm() {
        return this.getAlgorithmAttr().getNodeVblue();
    }

    /**
     * Method getDigestBytes
     *
     * @return the digestbytes
     * @throws XMLSecurityException
     */
    public byte[] getDigestBytes() throws XMLSecurityException {
        return this.getBytesFromTextChild();
    }

    /**
     * Method getDigestBytesFromCert
     *
     * @pbrbm cert
     * @pbrbm blgorithmURI
     * @return digest bytes from the given certificbte
     *
     * @throws XMLSecurityException
     */
    public stbtic byte[] getDigestBytesFromCert(X509Certificbte cert, String blgorithmURI) throws XMLSecurityException {
        String jcbDigestAlgorithm = JCEMbpper.trbnslbteURItoJCEID(blgorithmURI);
        if (jcbDigestAlgorithm == null) {
            Object exArgs[] = { blgorithmURI };
            throw new XMLSecurityException("XMLX509Digest.UnknownDigestAlgorithm", exArgs);
        }

        try {
            MessbgeDigest md = MessbgeDigest.getInstbnce(jcbDigestAlgorithm);
            return md.digest(cert.getEncoded());
        } cbtch (Exception e) {
            Object exArgs[] = { jcbDigestAlgorithm };
            throw new XMLSecurityException("XMLX509Digest.FbiledDigest", exArgs);
        }

    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_X509DIGEST;
    }
}
