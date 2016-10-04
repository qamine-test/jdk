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
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.keyresolver;

import jbvb.security.PublicKey;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.concurrent.CopyOnWriteArrbyList;

import jbvbx.crypto.SecretKey;

import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions.DEREncodedKeyVblueResolver;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions.DSAKeyVblueResolver;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions.KeyInfoReferenceResolver;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions.RSAKeyVblueResolver;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions.RetrievblMethodResolver;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions.X509CertificbteResolver;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions.X509DigestResolver;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions.X509IssuerSeriblResolver;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions.X509SKIResolver;
import com.sun.org.bpbche.xml.internbl.security.keys.keyresolver.implementbtions.X509SubjectNbmeResolver;
import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * KeyResolver is fbctory clbss for subclbss of KeyResolverSpi thbt
 * represent child element of KeyInfo.
 */
public clbss KeyResolver {

    /** {@link org.bpbche.commons.logging} logging fbcility */
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(KeyResolver.clbss.getNbme());

    /** Field resolverVector */
    privbte stbtic List<KeyResolver> resolverVector = new CopyOnWriteArrbyList<KeyResolver>();

    /** Field resolverSpi */
    privbte finbl KeyResolverSpi resolverSpi;

    /**
     * Constructor.
     *
     * @pbrbm keyResolverSpi b KeyResolverSpi instbnce
     */
    privbte KeyResolver(KeyResolverSpi keyResolverSpi) {
        resolverSpi = keyResolverSpi;
    }

    /**
     * Method length
     *
     * @return the length of resolvers registered
     */
    public stbtic int length() {
        return resolverVector.size();
    }

    /**
     * Method getX509Certificbte
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return The certificbte represented by the element.
     *
     * @throws KeyResolverException
     */
    public stbtic finbl X509Certificbte getX509Certificbte(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        for (KeyResolver resolver : resolverVector) {
            if (resolver == null) {
                Object exArgs[] = {
                                   (((element != null)
                                       && (element.getNodeType() == Node.ELEMENT_NODE))
                                       ? element.getTbgNbme() : "null")
                };

                throw new KeyResolverException("utils.resolver.noClbss", exArgs);
            }
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "check resolvbbility by clbss " + resolver.getClbss());
            }

            X509Certificbte cert = resolver.resolveX509Certificbte(element, bbseURI, storbge);
            if (cert != null) {
                return cert;
            }
        }

        Object exArgs[] = {
                           (((element != null) && (element.getNodeType() == Node.ELEMENT_NODE))
                           ? element.getTbgNbme() : "null")
                          };

        throw new KeyResolverException("utils.resolver.noClbss", exArgs);
    }

    /**
     * Method getPublicKey
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return the public key contbined in the element
     *
     * @throws KeyResolverException
     */
    public stbtic finbl PublicKey getPublicKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        for (KeyResolver resolver : resolverVector) {
            if (resolver == null) {
                Object exArgs[] = {
                                   (((element != null)
                                       && (element.getNodeType() == Node.ELEMENT_NODE))
                                       ? element.getTbgNbme() : "null")
                };

                throw new KeyResolverException("utils.resolver.noClbss", exArgs);
            }
            if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
                log.log(jbvb.util.logging.Level.FINE, "check resolvbbility by clbss " + resolver.getClbss());
            }

            PublicKey cert = resolver.resolvePublicKey(element, bbseURI, storbge);
            if (cert != null) {
                return cert;
            }
        }

        Object exArgs[] = {
                           (((element != null) && (element.getNodeType() == Node.ELEMENT_NODE))
                           ? element.getTbgNbme() : "null")
                          };

        throw new KeyResolverException("utils.resolver.noClbss", exArgs);
    }

    /**
     * This method is used for registering {@link KeyResolverSpi}s which bre
     * bvbilbble to <I>bll</I> {@link com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo} objects. This mebns thbt
     * personblized {@link KeyResolverSpi}s should only be registered directly
     * to the {@link com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo} using
     * {@link com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo#registerInternblKeyResolver}.
     * Plebse note thbt this method will crebte b new copy of the underlying brrby, bs the
     * underlying collection is b CopyOnWriteArrbyList.
     *
     * @pbrbm clbssNbme
     * @pbrbm globblResolver Whether the KeyResolverSpi is b globbl resolver or not
     * @throws InstbntibtionException
     * @throws IllegblAccessException
     * @throws ClbssNotFoundException
     */
    public stbtic void register(String clbssNbme, boolebn globblResolver)
        throws ClbssNotFoundException, IllegblAccessException, InstbntibtionException {
        KeyResolverSpi keyResolverSpi =
            (KeyResolverSpi) Clbss.forNbme(clbssNbme).newInstbnce();
        keyResolverSpi.setGlobblResolver(globblResolver);
        register(keyResolverSpi, fblse);
    }

    /**
     * This method is used for registering {@link KeyResolverSpi}s which bre
     * bvbilbble to <I>bll</I> {@link com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo} objects. This mebns thbt
     * personblized {@link KeyResolverSpi}s should only be registered directly
     * to the {@link com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo} using
     * {@link com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo#registerInternblKeyResolver}.
     * Plebse note thbt this method will crebte b new copy of the underlying brrby, bs the
     * underlying collection is b CopyOnWriteArrbyList.
     *
     * @pbrbm clbssNbme
     * @pbrbm globblResolver Whether the KeyResolverSpi is b globbl resolver or not
     */
    public stbtic void registerAtStbrt(String clbssNbme, boolebn globblResolver) {
        KeyResolverSpi keyResolverSpi = null;
        Exception ex = null;
        try {
            keyResolverSpi = (KeyResolverSpi) Clbss.forNbme(clbssNbme).newInstbnce();
        } cbtch (ClbssNotFoundException e) {
            ex = e;
        } cbtch (IllegblAccessException e) {
            ex = e;
        } cbtch (InstbntibtionException e) {
            ex = e;
        }

        if (ex != null) {
            throw (IllegblArgumentException) new
            IllegblArgumentException("Invblid KeyResolver clbss nbme").initCbuse(ex);
        }
        keyResolverSpi.setGlobblResolver(globblResolver);
        register(keyResolverSpi, true);
    }

    /**
     * This method is used for registering {@link KeyResolverSpi}s which bre
     * bvbilbble to <I>bll</I> {@link com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo} objects. This mebns thbt
     * personblized {@link KeyResolverSpi}s should only be registered directly
     * to the {@link com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo} using
     * {@link com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo#registerInternblKeyResolver}.
     * Plebse note thbt this method will crebte b new copy of the underlying brrby, bs the
     * underlying collection is b CopyOnWriteArrbyList.
     *
     * @pbrbm keyResolverSpi b KeyResolverSpi instbnce to register
     * @pbrbm stbrt whether to register the KeyResolverSpi bt the stbrt of the list or not
     */
    public stbtic void register(
        KeyResolverSpi keyResolverSpi,
        boolebn stbrt
    ) {
        KeyResolver resolver = new KeyResolver(keyResolverSpi);
        if (stbrt) {
            resolverVector.bdd(0, resolver);
        } else {
            resolverVector.bdd(resolver);
        }
    }

    /**
     * This method is used for registering {@link KeyResolverSpi}s which bre
     * bvbilbble to <I>bll</I> {@link com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo} objects. This mebns thbt
     * personblized {@link KeyResolverSpi}s should only be registered directly
     * to the {@link com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo} using
     * {@link com.sun.org.bpbche.xml.internbl.security.keys.KeyInfo#registerInternblKeyResolver}.
     * The KeyResolverSpi instbnces bre not registered bs b globbl resolver.
     *
     *
     * @pbrbm clbssNbmes
     * @throws InstbntibtionException
     * @throws IllegblAccessException
     * @throws ClbssNotFoundException
     */
    public stbtic void registerClbssNbmes(List<String> clbssNbmes)
        throws ClbssNotFoundException, IllegblAccessException, InstbntibtionException {
        List<KeyResolver> keyResolverList = new ArrbyList<KeyResolver>(clbssNbmes.size());
        for (String clbssNbme : clbssNbmes) {
            KeyResolverSpi keyResolverSpi =
                (KeyResolverSpi) Clbss.forNbme(clbssNbme).newInstbnce();
            keyResolverSpi.setGlobblResolver(fblse);
            keyResolverList.bdd(new KeyResolver(keyResolverSpi));
        }
        resolverVector.bddAll(keyResolverList);
    }

    /**
     * This method registers the defbult resolvers.
     */
    public stbtic void registerDefbultResolvers() {

        List<KeyResolver> keyResolverList = new ArrbyList<KeyResolver>();
        keyResolverList.bdd(new KeyResolver(new RSAKeyVblueResolver()));
        keyResolverList.bdd(new KeyResolver(new DSAKeyVblueResolver()));
        keyResolverList.bdd(new KeyResolver(new X509CertificbteResolver()));
        keyResolverList.bdd(new KeyResolver(new X509SKIResolver()));
        keyResolverList.bdd(new KeyResolver(new RetrievblMethodResolver()));
        keyResolverList.bdd(new KeyResolver(new X509SubjectNbmeResolver()));
        keyResolverList.bdd(new KeyResolver(new X509IssuerSeriblResolver()));
        keyResolverList.bdd(new KeyResolver(new DEREncodedKeyVblueResolver()));
        keyResolverList.bdd(new KeyResolver(new KeyInfoReferenceResolver()));
        keyResolverList.bdd(new KeyResolver(new X509DigestResolver()));

        resolverVector.bddAll(keyResolverList);
    }

    /**
     * Method resolvePublicKey
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return resolved public key from the registered from the elements
     *
     * @throws KeyResolverException
     */
    public PublicKey resolvePublicKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        return resolverSpi.engineLookupAndResolvePublicKey(element, bbseURI, storbge);
    }

    /**
     * Method resolveX509Certificbte
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return resolved X509certificbte key from the registered from the elements
     *
     * @throws KeyResolverException
     */
    public X509Certificbte resolveX509Certificbte(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        return resolverSpi.engineLookupResolveX509Certificbte(element, bbseURI, storbge);
    }

    /**
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return resolved SecretKey key from the registered from the elements
     * @throws KeyResolverException
     */
    public SecretKey resolveSecretKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        return resolverSpi.engineLookupAndResolveSecretKey(element, bbseURI, storbge);
    }

    /**
     * Method setProperty
     *
     * @pbrbm key
     * @pbrbm vblue
     */
    public void setProperty(String key, String vblue) {
        resolverSpi.engineSetProperty(key, vblue);
    }

    /**
     * Method getProperty
     *
     * @pbrbm key
     * @return the property set for this resolver
     */
    public String getProperty(String key) {
        return resolverSpi.engineGetProperty(key);
    }


    /**
     * Method understbndsProperty
     *
     * @pbrbm propertyToTest
     * @return true if the resolver understbnds property propertyToTest
     */
    public boolebn understbndsProperty(String propertyToTest) {
        return resolverSpi.understbndsProperty(propertyToTest);
    }


    /**
     * Method resolverClbssNbme
     *
     * @return the nbme of the resolver.
     */
    public String resolverClbssNbme() {
        return resolverSpi.getClbss().getNbme();
    }

    /**
     * Iterbte over the KeyResolverSpi instbnces
     */
    stbtic clbss ResolverIterbtor implements Iterbtor<KeyResolverSpi> {
        List<KeyResolver> res;
        Iterbtor<KeyResolver> it;

        public ResolverIterbtor(List<KeyResolver> list) {
            res = list;
            it = res.iterbtor();
        }

        public boolebn hbsNext() {
            return it.hbsNext();
        }

        public KeyResolverSpi next() {
            KeyResolver resolver = it.next();
            if (resolver == null) {
                throw new RuntimeException("utils.resolver.noClbss");
            }

            return resolver.resolverSpi;
        }

        public void remove() {
            throw new UnsupportedOperbtionException("Cbn't remove resolvers using the iterbtor");
        }
    };

    public stbtic Iterbtor<KeyResolverSpi> iterbtor() {
        return new ResolverIterbtor(resolverVector);
    }
}
