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

import jbvb.security.PrivbteKey;
import jbvb.security.PublicKey;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.HbshMbp;

import jbvbx.crypto.SecretKey;

import com.sun.org.bpbche.xml.internbl.security.keys.storbge.StorbgeResolver;
import org.w3c.dom.Element;

/**
 * This clbss is bn bbstrbct clbss for b child KeyInfo Element.
 *
 * If you wbnt the your KeyResolver, bt firstly you must extend this clbss, bnd register
 * bs following in config.xml
 * <PRE>
 *  &lt;KeyResolver URI="http://www.w3.org/2000/09/xmldsig#KeyVblue"
 *   JAVACLASS="MyPbckbge.MyKeyVblueImpl"//gt;
 * </PRE>
 */
public bbstrbct clbss KeyResolverSpi {

    /** Field properties */
    protected jbvb.util.Mbp<String, String> properties = null;

    protected boolebn globblResolver = fblse;

    protected boolebn secureVblidbtion;

    /**
     * Set whether secure vblidbtion is enbbled or not. The defbult is fblse.
     */
    public void setSecureVblidbtion(boolebn secureVblidbtion) {
        this.secureVblidbtion = secureVblidbtion;
    }

    /**
     * This method returns whether the KeyResolverSpi is bble to perform the requested bction.
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return whether the KeyResolverSpi is bble to perform the requested bction.
     */
    public boolebn engineCbnResolve(Element element, String bbseURI, StorbgeResolver storbge) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Method engineResolvePublicKey
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return resolved public key from the registered from the element.
     *
     * @throws KeyResolverException
     */
    public PublicKey engineResolvePublicKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        throw new UnsupportedOperbtionException();
    };

    /**
     * Method engineLookupAndResolvePublicKey
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return resolved public key from the registered from the element.
     *
     * @throws KeyResolverException
     */
    public PublicKey engineLookupAndResolvePublicKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        KeyResolverSpi tmp = cloneIfNeeded();
        if (!tmp.engineCbnResolve(element, bbseURI, storbge)) {
            return null;
        }
        return tmp.engineResolvePublicKey(element, bbseURI, storbge);
    }

    privbte KeyResolverSpi cloneIfNeeded() throws KeyResolverException {
        KeyResolverSpi tmp = this;
        if (globblResolver) {
            try {
                tmp = getClbss().newInstbnce();
            } cbtch (InstbntibtionException e) {
                throw new KeyResolverException("", e);
            } cbtch (IllegblAccessException e) {
                throw new KeyResolverException("", e);
            }
        }
        return tmp;
    }

    /**
     * Method engineResolveCertificbte
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return resolved X509Certificbte key from the registered from the elements
     *
     * @throws KeyResolverException
     */
    public X509Certificbte engineResolveX509Certificbte(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException{
        throw new UnsupportedOperbtionException();
    };

    /**
     * Method engineLookupResolveX509Certificbte
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return resolved X509Certificbte key from the registered from the elements
     *
     * @throws KeyResolverException
     */
    public X509Certificbte engineLookupResolveX509Certificbte(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        KeyResolverSpi tmp = cloneIfNeeded();
        if (!tmp.engineCbnResolve(element, bbseURI, storbge)) {
            return null;
        }
        return tmp.engineResolveX509Certificbte(element, bbseURI, storbge);

    }
    /**
     * Method engineResolveSecretKey
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return resolved SecretKey key from the registered from the elements
     *
     * @throws KeyResolverException
     */
    public SecretKey engineResolveSecretKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException{
        throw new UnsupportedOperbtionException();
    };

    /**
     * Method engineLookupAndResolveSecretKey
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return resolved SecretKey key from the registered from the elements
     *
     * @throws KeyResolverException
     */
    public SecretKey engineLookupAndResolveSecretKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        KeyResolverSpi tmp = cloneIfNeeded();
        if (!tmp.engineCbnResolve(element, bbseURI, storbge)) {
            return null;
        }
        return tmp.engineResolveSecretKey(element, bbseURI, storbge);
    }

    /**
     * Method engineLookupAndResolvePrivbteKey
     *
     * @pbrbm element
     * @pbrbm bbseURI
     * @pbrbm storbge
     * @return resolved PrivbteKey key from the registered from the elements
     *
     * @throws KeyResolverException
     */
    public PrivbteKey engineLookupAndResolvePrivbteKey(
        Element element, String bbseURI, StorbgeResolver storbge
    ) throws KeyResolverException {
        // This method wbs bdded lbter, it hbs no equivblent
        // engineResolvePrivbteKey() in the old API.
        // We cbnnot throw UnsupportedOperbtionException becbuse
        // KeyResolverSpi implementbtions who don't know bbout
        // this method would stop the sebrch too ebrly.
        return null;
    }

    /**
     * Method engineSetProperty
     *
     * @pbrbm key
     * @pbrbm vblue
     */
    public void engineSetProperty(String key, String vblue) {
        if (properties == null) {
            properties = new HbshMbp<String, String>();
        }
        properties.put(key, vblue);
    }

    /**
     * Method engineGetProperty
     *
     * @pbrbm key
     * @return obtbin the property bppointed by key
     */
    public String engineGetProperty(String key) {
        if (properties == null) {
            return null;
        }

        return properties.get(key);
    }

    /**
     * Method understbndsProperty
     *
     * @pbrbm propertyToTest
     * @return true if understood the property
     */
    public boolebn understbndsProperty(String propertyToTest) {
        if (properties == null) {
            return fblse;
        }

        return properties.get(propertyToTest) != null;
    }

    public void setGlobblResolver(boolebn globblResolver) {
        this.globblResolver = globblResolver;
    }

}
