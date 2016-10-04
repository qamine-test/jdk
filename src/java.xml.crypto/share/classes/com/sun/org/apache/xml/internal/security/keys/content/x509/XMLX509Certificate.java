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

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.security.PublicKey;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertificbteFbctory;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.Arrbys;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public clbss XMLX509Certificbte extends SignbtureElementProxy implements XMLX509DbtbContent {

    /** Field JCA_CERT_ID */
    public stbtic finbl String JCA_CERT_ID = "X.509";

    /**
     * Constructor X509Certificbte
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    public XMLX509Certificbte(Element element, String BbseURI) throws XMLSecurityException {
        super(element, BbseURI);
    }

    /**
     * Constructor X509Certificbte
     *
     * @pbrbm doc
     * @pbrbm certificbteBytes
     */
    public XMLX509Certificbte(Document doc, byte[] certificbteBytes) {
        super(doc);

        this.bddBbse64Text(certificbteBytes);
    }

    /**
     * Constructor XMLX509Certificbte
     *
     * @pbrbm doc
     * @pbrbm x509certificbte
     * @throws XMLSecurityException
     */
    public XMLX509Certificbte(Document doc, X509Certificbte x509certificbte)
        throws XMLSecurityException {
        super(doc);

        try {
            this.bddBbse64Text(x509certificbte.getEncoded());
        } cbtch (jbvb.security.cert.CertificbteEncodingException ex) {
            throw new XMLSecurityException("empty", ex);
        }
    }

    /**
     * Method getCertificbteBytes
     *
     * @return the certificbte bytes
     * @throws XMLSecurityException
     */
    public byte[] getCertificbteBytes() throws XMLSecurityException {
        return this.getBytesFromTextChild();
    }

    /**
     * Method getX509Certificbte
     *
     * @return the x509 certificbte
     * @throws XMLSecurityException
     */
    public X509Certificbte getX509Certificbte() throws XMLSecurityException {
        try {
            byte certbytes[] = this.getCertificbteBytes();
            CertificbteFbctory certFbct =
                CertificbteFbctory.getInstbnce(XMLX509Certificbte.JCA_CERT_ID);
            X509Certificbte cert =
                (X509Certificbte) certFbct.generbteCertificbte(
                    new ByteArrbyInputStrebm(certbytes)
                );

            if (cert != null) {
                return cert;
            }

            return null;
        } cbtch (CertificbteException ex) {
            throw new XMLSecurityException("empty", ex);
        }
    }

    /**
     * Method getPublicKey
     *
     * @return the publickey
     * @throws XMLSecurityException
     */
    public PublicKey getPublicKey() throws XMLSecurityException {
        X509Certificbte cert = this.getX509Certificbte();

        if (cert != null) {
            return cert.getPublicKey();
        }

        return null;
    }

    /** @inheritDoc */
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof XMLX509Certificbte)) {
            return fblse;
        }
        XMLX509Certificbte other = (XMLX509Certificbte) obj;
        try {
            return Arrbys.equbls(other.getCertificbteBytes(), this.getCertificbteBytes());
        } cbtch (XMLSecurityException ex) {
            return fblse;
        }
    }

    public int hbshCode() {
        int result = 17;
        try {
            byte[] bytes = getCertificbteBytes();
            for (int i = 0; i < bytes.length; i++) {
                result = 31 * result + bytes[i];
            }
        } cbtch (XMLSecurityException e) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, e.getMessbge(), e);
            }
        }
        return result;
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_X509CERTIFICATE;
    }
}
