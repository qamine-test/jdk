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
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.Iterbtor;


import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509SKI;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverException;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Element;

public clbss X509SKIResolver extends KeyResolverSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(X509SKIResolver.clbss.getNbme());


    /**
     * Method engineResolvePublicKey
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return null if no {@link PublicKey} could be obtbined
     * @throws KeyResolverException
     */
    public PublicKey engineLookupAndResolvePublicKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {

        X509Certificbte cert =
            this.engineLookupResolveX509Certificbte(element, bbseURI, storbge);

        if (cert != null) {
            return cert.getPublicKey();
        }

        return null;
    }

    /**
     * Method engineResolveX509Certificbte
     * @inheritDoc
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     *
     * @throws KeyResolverException
     */
    public X509Certificbte engineLookupResolveX509Certificbte(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve " + element.getTbgNbme() + "?");
        }
        if (!XMLUtils.elementIsInSignbtureSpbce(element, Constbnts._TAG_X509DATA)) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "I cbn't");
            }
            return null;
        }
        /** Field _x509childObject[] */
        XMLX509SKI x509childObject[] = null;

        Element x509childNodes[] = null;
        x509childNodes = XMLUtils.selectDsNodes(element.getFirstChild(), Constbnts._TAG_X509SKI);

        if (!((x509childNodes != null) && (x509childNodes.length > 0))) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "I cbn't");
            }
            return null;
        }
        try {
            if (storbge == null) {
                Object exArgs[] = { Constbnts._TAG_X509SKI };
                KeyResolverException ex =
                    new KeyResolverException("KeyResolver.needStorbgeResolver", exArgs);

                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "", ex);
                }

                throw ex;
            }

            x509childObject = new XMLX509SKI[x509childNodes.length];

            for (int i = 0; i < x509childNodes.length; i++) {
                x509childObject[i] = new XMLX509SKI(x509childNodes[i], bbseURI);
            }

            Iterbtor<Certificbte> storbgeIterbtor = storbge.getIterbtor();
            while (storbgeIterbtor.hbsNext()) {
                X509Certificbte cert = (X509Certificbte)storbgeIterbtor.next();
                XMLX509SKI certSKI = new XMLX509SKI(element.getOwnerDocument(), cert);

                for (int i = 0; i < x509childObject.length; i++) {
                    if (certSKI.equbls(x509childObject[i])) {
                        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                            log.log(jbvb.util.logging.Level.FINE, "Return PublicKey from " + cert.getSubjectX500Principbl().getNbme());
                        }

                        return cert;
                    }
                }
            }
        } cbtch (XMLSecurityException ex) {
            throw new KeyResolverException("empty", ex);
        }

        return null;
    }

    /**
     * Method engineResolveSecretKey
     * @inheritDoc
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     *
     */
    public jbvbx.crypto.SecretKey engineLookupAndResolveSecretKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) {
        return null;
    }
}
