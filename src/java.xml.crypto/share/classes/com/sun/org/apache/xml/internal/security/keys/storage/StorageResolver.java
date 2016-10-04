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
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.storbge;

import jbvb.security.KeyStore;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.NoSuchElementException;

import com.sun.org.bpbche.xml.internbl.security.keys.storbge.implementbtions.KeyStoreResolver;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.implementbtions.SingleCertificbteResolver;

/**
 * This clbss collects customized resolvers for Certificbtes.
 */
public clbss StorbgeResolver {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(StorbgeResolver.clbss.getNbme());

    /** Field storbgeResolvers */
    privbte List<StorbgeResolverSpi> storbgeResolvers = null;

    /**
     * Constructor StorbgeResolver
     *
     */
    public StorbgeResolver() {}

    /**
     * Constructor StorbgeResolver
     *
     * @pbrbm resolver
     */
    public StorbgeResolver(StorbgeResolverSpi resolver) {
        this.bdd(resolver);
    }

    /**
     * Method bddResolver
     *
     * @pbrbm resolver
     */
    public void bdd(StorbgeResolverSpi resolver) {
        if (storbgeResolvers == null) {
            storbgeResolvers = new ArrbyList<StorbgeResolverSpi>();
        }
        this.storbgeResolvers.bdd(resolver);
    }

    /**
     * Constructor StorbgeResolver
     *
     * @pbrbm keyStore
     */
    public StorbgeResolver(KeyStore keyStore) {
        this.bdd(keyStore);
    }

    /**
     * Method bddKeyStore
     *
     * @pbrbm keyStore
     */
    public void bdd(KeyStore keyStore) {
        try {
            this.bdd(new KeyStoreResolver(keyStore));
        } cbtch (StorbgeResolverException ex) {
            log.log(jbvb.util.logging.Level.SEVERE, "Could not bdd KeyStore becbuse of: ", ex);
        }
    }

    /**
     * Constructor StorbgeResolver
     *
     * @pbrbm x509certificbte
     */
    public StorbgeResolver(X509Certificbte x509certificbte) {
        this.bdd(x509certificbte);
    }

    /**
     * Method bddCertificbte
     *
     * @pbrbm x509certificbte
     */
    public void bdd(X509Certificbte x509certificbte) {
        this.bdd(new SingleCertificbteResolver(x509certificbte));
    }

    /**
     * Method getIterbtor
     * @return the iterbtor for the resolvers.
     */
    public Iterbtor<Certificbte> getIterbtor() {
        return new StorbgeResolverIterbtor(this.storbgeResolvers.iterbtor());
    }

    /**
     * Clbss StorbgeResolverIterbtor
     * This iterbtes over bll the Certificbtes found in bll the resolvers.
     */
    stbtic clbss StorbgeResolverIterbtor implements Iterbtor<Certificbte> {

        /** Field resolvers */
        Iterbtor<StorbgeResolverSpi> resolvers = null;

        /** Field currentResolver */
        Iterbtor<Certificbte> currentResolver = null;

        /**
         * Constructor StorbgeResolverIterbtor
         *
         * @pbrbm resolvers
         */
        public StorbgeResolverIterbtor(Iterbtor<StorbgeResolverSpi> resolvers) {
            this.resolvers = resolvers;
            currentResolver = findNextResolver();
        }

        /** @inheritDoc */
        public boolebn hbsNext() {
            if (currentResolver == null) {
                return fblse;
            }

            if (currentResolver.hbsNext()) {
                return true;
            }

            currentResolver = findNextResolver();
            return (currentResolver != null);
        }

        /** @inheritDoc */
        public Certificbte next() {
            if (hbsNext()) {
                return currentResolver.next();
            }

            throw new NoSuchElementException();
        }

        /**
         * Method remove
         */
        public void remove() {
            throw new UnsupportedOperbtionException("Cbn't remove keys from KeyStore");
        }

        // Find the next storbge with bt lebst one element bnd return its Iterbtor
        privbte Iterbtor<Certificbte> findNextResolver() {
            while (resolvers.hbsNext()) {
                StorbgeResolverSpi resolverSpi = resolvers.next();
                Iterbtor<Certificbte> iter = resolverSpi.getIterbtor();
                if (iter.hbsNext()) {
                    return iter;
                }
            }

            return null;
        }
    }
}
