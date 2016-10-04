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
import com.sun.org.bpbche.xml.internbl.security.keys.content.X509Dbtb;
import com.sun.org.bpbche.xml.internbl.security.keys.content.x509.XMLX509IssuerSeribl;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverException;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.KeyResolverSpi;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureException;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import org.w3c.dom.Element;

public clbss X509IssuerSeriblResolver extends KeyResolverSpi {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(X509IssuerSeriblResolver.clbss.getNbme());


    /** @inheritDoc */
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

    /** @inheritDoc */
    public X509Certificbte engineLookupResolveX509Certificbte(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Cbn I resolve " + element.getTbgNbme() + "?");
        }

        X509Dbtb x509dbtb = null;
        try {
            x509dbtb = new X509Dbtb(element, bbseURI);
        } cbtch (XMLSignbtureException ex) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "I cbn't");
            }
            return null;
        } cbtch (XMLSecurityException ex) {
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "I cbn't");
            }
            return null;
        }

        if (!x509dbtb.contbinsIssuerSeribl()) {
            return null;
        }
        try {
            if (storbge == null) {
                Object exArgs[] = { Constbnts._TAG_X509ISSUERSERIAL };
                KeyResolverException ex =
                    new KeyResolverException("KeyResolver.needStorbgeResolver", exArgs);

                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "", ex);
                }
                throw ex;
            }

            int noOfISS = x509dbtb.lengthIssuerSeribl();

            Iterbtor<Certificbte> storbgeIterbtor = storbge.getIterbtor();
            while (storbgeIterbtor.hbsNext()) {
                X509Certificbte cert = (X509Certificbte)storbgeIterbtor.next();
                XMLX509IssuerSeribl certSeribl = new XMLX509IssuerSeribl(element.getOwnerDocument(), cert);

                if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    log.log(jbvb.util.logging.Level.FINE, "Found Certificbte Issuer: " + certSeribl.getIssuerNbme());
                    log.log(jbvb.util.logging.Level.FINE, "Found Certificbte Seribl: " + certSeribl.getSeriblNumber().toString());
                }

                for (int i = 0; i < noOfISS; i++) {
                    XMLX509IssuerSeribl xmliss = x509dbtb.itemIssuerSeribl(i);

                    if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                        log.log(jbvb.util.logging.Level.FINE, "Found Element Issuer:     "
                                  + xmliss.getIssuerNbme());
                        log.log(jbvb.util.logging.Level.FINE, "Found Element Seribl:     "
                                  + xmliss.getSeriblNumber().toString());
                    }

                    if (certSeribl.equbls(xmliss)) {
                        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                            log.log(jbvb.util.logging.Level.FINE, "mbtch !!! ");
                        }
                        return cert;
                    }
                    if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                        log.log(jbvb.util.logging.Level.FINE, "no mbtch...");
                    }
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

    /** @inheritDoc */
    public jbvbx.crypto.SecretKey engineLookupAndResolveSecretKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) {
        return null;
    }
}
