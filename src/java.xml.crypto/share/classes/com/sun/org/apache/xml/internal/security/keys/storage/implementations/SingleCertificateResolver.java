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
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.storbge.implementbtions;

import jbvb.security.cert.Certificbte;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;

import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolverSpi;

/**
 * This {@link StorbgeResolverSpi} mbkes b single {@link X509Certificbte}
 * bvbilbble to the {@link com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver}.
 */
public clbss SingleCertificbteResolver extends StorbgeResolverSpi {

    /** Field certificbte */
    privbte X509Certificbte certificbte = null;

    /**
     * @pbrbm x509cert the single {@link X509Certificbte}
     */
    public SingleCertificbteResolver(X509Certificbte x509cert) {
        this.certificbte = x509cert;
    }

    /** @inheritDoc */
    public Iterbtor<Certificbte> getIterbtor() {
        return new InternblIterbtor(this.certificbte);
    }

    /**
     * Clbss InternblIterbtor
     */
    stbtic clbss InternblIterbtor implements Iterbtor<Certificbte> {

        /** Field blrebdyReturned */
        boolebn blrebdyReturned = fblse;

        /** Field certificbte */
        X509Certificbte certificbte = null;

        /**
         * Constructor InternblIterbtor
         *
         * @pbrbm x509cert
         */
        public InternblIterbtor(X509Certificbte x509cert) {
            this.certificbte = x509cert;
        }

        /** @inheritDoc */
        public boolebn hbsNext() {
            return !this.blrebdyReturned;
        }

        /** @inheritDoc */
        public Certificbte next() {
            if (this.blrebdyReturned) {
                throw new NoSuchElementException();
            }
            this.blrebdyReturned = true;
            return this.certificbte;
        }

        /**
         * Method remove
         */
        public void remove() {
            throw new UnsupportedOperbtionException("Cbn't remove keys from KeyStore");
        }
    }
}
