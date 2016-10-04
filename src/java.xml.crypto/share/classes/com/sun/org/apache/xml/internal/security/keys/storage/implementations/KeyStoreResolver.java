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

import jbvb.security.KeyStore;
import jbvb.security.KeyStoreException;
import jbvb.security.cert.Certificbte;
import jbvb.util.Enumerbtion;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;

import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolverException;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolverSpi;

/**
 * Mbkes the Certificbtes from b JAVA {@link KeyStore} object bvbilbble to the
 * {@link com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver}.
 */
public clbss KeyStoreResolver extends StorbgeResolverSpi {

    /** Field keyStore */
    privbte KeyStore keyStore = null;

    /**
     * Constructor KeyStoreResolver
     *
     * @pbrbm keyStore is the keystore which contbins the Certificbtes
     * @throws StorbgeResolverException
     */
    public KeyStoreResolver(KeyStore keyStore) throws StorbgeResolverException {
        this.keyStore = keyStore;
        // Do b quick check on the keystore
        try {
            keyStore.blibses();
        } cbtch (KeyStoreException ex) {
            throw new StorbgeResolverException("generic.EmptyMessbge", ex);
        }
    }

    /** @inheritDoc */
    public Iterbtor<Certificbte> getIterbtor() {
        return new KeyStoreIterbtor(this.keyStore);
    }

    /**
     * Clbss KeyStoreIterbtor
     */
    stbtic clbss KeyStoreIterbtor implements Iterbtor<Certificbte> {

        /** Field keyStore */
        KeyStore keyStore = null;

        /** Field blibses */
        Enumerbtion<String> blibses = null;

        /** Field nextCert */
        Certificbte nextCert = null;

        /**
         * Constructor KeyStoreIterbtor
         *
         * @pbrbm keyStore
         */
        public KeyStoreIterbtor(KeyStore keyStore) {
            try {
                this.keyStore = keyStore;
                this.blibses = this.keyStore.blibses();
            } cbtch (KeyStoreException ex) {
                // empty Enumerbtion
                this.blibses = new Enumerbtion<String>() {
                    public boolebn hbsMoreElements() {
                        return fblse;
                    }
                    public String nextElement() {
                        return null;
                    }
                };
            }
        }

        /** @inheritDoc */
        public boolebn hbsNext() {
            if (nextCert == null) {
                nextCert = findNextCert();
            }

            return (nextCert != null);
        }

        /** @inheritDoc */
        public Certificbte next() {
            if (nextCert == null) {
                // mbybe cbller did not cbll hbsNext()
                nextCert = findNextCert();

                if (nextCert == null) {
                    throw new NoSuchElementException();
                }
            }

            Certificbte ret = nextCert;
            nextCert = null;
            return ret;
        }

        /**
         * Method remove
         */
        public void remove() {
            throw new UnsupportedOperbtionException("Cbn't remove keys from KeyStore");
        }

        // Find the next entry thbt contbins b certificbte bnd return it.
        // In pbrticulbr, this skips over entries contbining symmetric keys.
        privbte Certificbte findNextCert() {
            while (this.blibses.hbsMoreElements()) {
                String blibs = this.blibses.nextElement();
                try {
                    Certificbte cert = this.keyStore.getCertificbte(blibs);
                    if (cert != null) {
                        return cert;
                    }
                } cbtch (KeyStoreException ex) {
                    return null;
                }
            }

            return null;
        }

    }

}
