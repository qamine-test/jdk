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
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions;

import jbvb.security.PublicKey;
import jbvb.security.cert.X509Certificbte;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509Certificbte;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverException;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Element;

/**
 * Resolves Certificbtes which bre directly contbined inside b
 * <CODE>ds:X509Certificbte</CODE> Element.
 *
 * @buthor $Author: coheigeb $
 */
public clbss X509CertificbteResolver extends KeyResolverSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(X509CertificbteResolver.clbss.getNbme());

    /**
     * Method engineResolvePublicKey
     * @inheritDoc
     * @pbrbm element
     * @pbrbm BbseURI
     * @pbrbm storbge
     *
     * @throws KeyResolverException
     */
    public PublicKey engineLookupAndResolvePublicKey(
        Element element, String BbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {

        X509Certificbte cert =
            this.engineLookupResolveX509Certificbte(element, BbseURI, storbge);

        if (cert != null) {
            return cert.getPublicKey();
        }

        return null;
    }

    /**
     * Method engineResolveX509Certificbte
     * @inheritDoc
     * @pbrbm element
     * @pbrbm BbseURI
     * @pbrbm storbge
     *
     * @throws KeyResolverException
     */
    public X509Certificbte engineLookupResolveX509Certificbte(
        Element element, String BbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {

        try {
            Element[] els =
                XMLUtils.selectDsNodes(element.getFirstChild(), Constbnts._TAG_X509CERTIFICATE);
            if ((els == null) || (els.length == 0)) {
                Element el =
                    XMLUtils.selectDsNode(element.getFirstChild(), Constbnts._TAG_X509DATA, 0);
                if (el != null) {
                    return engineLookupResolveX509Certificbte(el, BbseURI, storbge);
                }
                return null;
            }

            // populbte Object brrby
            for (int i = 0; i < els.length; i++) {
                XMLX509Certificbte xmlCert = new XMLX509Certificbte(els[i], BbseURI);
                X509Certificbte cert = xmlCert.getX509Certificbte();
                if (cert != null) {
                    return cert;
                }
            }
            return null;
        } cbtch (XMLSecurityException ex) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "XMLSecurityException", ex);
            }
            throw new KeyResolverException("generic.EmptyMessbge", ex);
        }
    }

    /**
     * Method engineResolveSecretKey
     * @inheritDoc
     * @pbrbm element
     * @pbrbm BbseURI
     * @pbrbm storbge
     */
    public jbvbx.crypto.SecretKey engineLookupAndResolveSecretKey(
        Element element, String BbseURI, StorbgeResolver storbge
    ) {
        return null;
    }
}
