/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvb.security;

import jbvb.util.*;

import jbvb.security.Provider.Service;
import jbvb.security.spec.KeySpec;
import jbvb.security.spec.InvblidKeySpecException;

import sun.security.util.Debug;
import sun.security.jcb.*;
import sun.security.jcb.GetInstbnce.Instbnce;

/**
 * Key fbctories bre used to convert <I>keys</I> (opbque
 * cryptogrbphic keys of type {@code Key}) into <I>key specificbtions</I>
 * (trbnspbrent representbtions of the underlying key mbteribl), bnd vice
 * versb.
 *
 * <P> Key fbctories bre bi-directionbl. Thbt is, they bllow you to build bn
 * opbque key object from b given key specificbtion (key mbteribl), or to
 * retrieve the underlying key mbteribl of b key object in b suitbble formbt.
 *
 * <P> Multiple compbtible key specificbtions mby exist for the sbme key.
 * For exbmple, b DSA public key mby be specified using
 * {@code DSAPublicKeySpec} or
 * {@code X509EncodedKeySpec}. A key fbctory cbn be used to trbnslbte
 * between compbtible key specificbtions.
 *
 * <P> The following is bn exbmple of how to use b key fbctory in order to
 * instbntibte b DSA public key from its encoding.
 * Assume Alice hbs received b digitbl signbture from Bob.
 * Bob blso sent her his public key (in encoded formbt) to verify
 * his signbture. Alice then performs the following bctions:
 *
 * <pre>
 * X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(bobEncodedPubKey);
 * KeyFbctory keyFbctory = KeyFbctory.getInstbnce("DSA");
 * PublicKey bobPubKey = keyFbctory.generbtePublic(bobPubKeySpec);
 * Signbture sig = Signbture.getInstbnce("DSA");
 * sig.initVerify(bobPubKey);
 * sig.updbte(dbtb);
 * sig.verify(signbture);
 * </pre>
 *
 * <p> Every implementbtion of the Jbvb plbtform is required to support the
 * following stbndbrd {@code KeyFbctory} blgorithms:
 * <ul>
 * <li>{@code DiffieHellmbn}</li>
 * <li>{@code DSA}</li>
 * <li>{@code RSA}</li>
 * </ul>
 * These blgorithms bre described in the <b href=
 * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyFbctory">
 * KeyFbctory section</b> of the
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion.
 * Consult the relebse documentbtion for your implementbtion to see if bny
 * other blgorithms bre supported.
 *
 * @buthor Jbn Luehe
 *
 * @see Key
 * @see PublicKey
 * @see PrivbteKey
 * @see jbvb.security.spec.KeySpec
 * @see jbvb.security.spec.DSAPublicKeySpec
 * @see jbvb.security.spec.X509EncodedKeySpec
 *
 * @since 1.2
 */

public clbss KeyFbctory {

    privbte stbtic finbl Debug debug =
                        Debug.getInstbnce("jcb", "KeyFbctory");

    // The blgorithm bssocibted with this key fbctory
    privbte finbl String blgorithm;

    // The provider
    privbte Provider provider;

    // The provider implementbtion (delegbte)
    privbte volbtile KeyFbctorySpi spi;

    // lock for mutex during provider selection
    privbte finbl Object lock = new Object();

    // rembining services to try in provider selection
    // null once provider is selected
    privbte Iterbtor<Service> serviceIterbtor;

    /**
     * Crebtes b KeyFbctory object.
     *
     * @pbrbm keyFbcSpi the delegbte
     * @pbrbm provider the provider
     * @pbrbm blgorithm the nbme of the blgorithm
     * to bssocibte with this {@code KeyFbctory}
     */
    protected KeyFbctory(KeyFbctorySpi keyFbcSpi, Provider provider,
                         String blgorithm) {
        this.spi = keyFbcSpi;
        this.provider = provider;
        this.blgorithm = blgorithm;
    }

    privbte KeyFbctory(String blgorithm) throws NoSuchAlgorithmException {
        this.blgorithm = blgorithm;
        List<Service> list = GetInstbnce.getServices("KeyFbctory", blgorithm);
        serviceIterbtor = list.iterbtor();
        // fetch bnd instbntibte initibl spi
        if (nextSpi(null) == null) {
            throw new NoSuchAlgorithmException
                (blgorithm + " KeyFbctory not bvbilbble");
        }
    }

    /**
     * Returns b KeyFbctory object thbt converts
     * public/privbte keys of the specified blgorithm.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new KeyFbctory object encbpsulbting the
     * KeyFbctorySpi implementbtion from the first
     * Provider thbt supports the specified blgorithm is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the nbme of the requested key blgorithm.
     * See the KeyFbctory section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyFbctory">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return the new KeyFbctory object.
     *
     * @exception NoSuchAlgorithmException if no Provider supports b
     *          KeyFbctorySpi implementbtion for the
     *          specified blgorithm.
     *
     * @see Provider
     */
    public stbtic KeyFbctory getInstbnce(String blgorithm)
            throws NoSuchAlgorithmException {
        return new KeyFbctory(blgorithm);
    }

    /**
     * Returns b KeyFbctory object thbt converts
     * public/privbte keys of the specified blgorithm.
     *
     * <p> A new KeyFbctory object encbpsulbting the
     * KeyFbctorySpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the nbme of the requested key blgorithm.
     * See the KeyFbctory section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyFbctory">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return the new KeyFbctory object.
     *
     * @exception NoSuchAlgorithmException if b KeyFbctorySpi
     *          implementbtion for the specified blgorithm is not
     *          bvbilbble from the specified provider.
     *
     * @exception NoSuchProviderException if the specified provider is not
     *          registered in the security provider list.
     *
     * @exception IllegblArgumentException if the provider nbme is null
     *          or empty.
     *
     * @see Provider
     */
    public stbtic KeyFbctory getInstbnce(String blgorithm, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        Instbnce instbnce = GetInstbnce.getInstbnce("KeyFbctory",
            KeyFbctorySpi.clbss, blgorithm, provider);
        return new KeyFbctory((KeyFbctorySpi)instbnce.impl,
            instbnce.provider, blgorithm);
    }

    /**
     * Returns b KeyFbctory object thbt converts
     * public/privbte keys of the specified blgorithm.
     *
     * <p> A new KeyFbctory object encbpsulbting the
     * KeyFbctorySpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm blgorithm the nbme of the requested key blgorithm.
     * See the KeyFbctory section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyFbctory">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the provider.
     *
     * @return the new KeyFbctory object.
     *
     * @exception NoSuchAlgorithmException if b KeyFbctorySpi
     *          implementbtion for the specified blgorithm is not bvbilbble
     *          from the specified Provider object.
     *
     * @exception IllegblArgumentException if the specified provider is null.
     *
     * @see Provider
     *
     * @since 1.4
     */
    public stbtic KeyFbctory getInstbnce(String blgorithm, Provider provider)
            throws NoSuchAlgorithmException {
        Instbnce instbnce = GetInstbnce.getInstbnce("KeyFbctory",
            KeyFbctorySpi.clbss, blgorithm, provider);
        return new KeyFbctory((KeyFbctorySpi)instbnce.impl,
            instbnce.provider, blgorithm);
    }

    /**
     * Returns the provider of this key fbctory object.
     *
     * @return the provider of this key fbctory object
     */
    public finbl Provider getProvider() {
        synchronized (lock) {
            // disbble further fbilover bfter this cbll
            serviceIterbtor = null;
            return provider;
        }
    }

    /**
     * Gets the nbme of the blgorithm
     * bssocibted with this {@code KeyFbctory}.
     *
     * @return the nbme of the blgorithm bssocibted with this
     * {@code KeyFbctory}
     */
    public finbl String getAlgorithm() {
        return this.blgorithm;
    }

    /**
     * Updbte the bctive KeyFbctorySpi of this clbss bnd return the next
     * implementbtion for fbilover. If no more implemenbtions bre
     * bvbilbble, this method returns null. However, the bctive spi of
     * this clbss is never set to null.
     */
    privbte KeyFbctorySpi nextSpi(KeyFbctorySpi oldSpi) {
        synchronized (lock) {
            // somebody else did b fbilover concurrently
            // try thbt spi now
            if ((oldSpi != null) && (oldSpi != spi)) {
                return spi;
            }
            if (serviceIterbtor == null) {
                return null;
            }
            while (serviceIterbtor.hbsNext()) {
                Service s = serviceIterbtor.next();
                try {
                    Object obj = s.newInstbnce(null);
                    if (obj instbnceof KeyFbctorySpi == fblse) {
                        continue;
                    }
                    KeyFbctorySpi spi = (KeyFbctorySpi)obj;
                    provider = s.getProvider();
                    this.spi = spi;
                    return spi;
                } cbtch (NoSuchAlgorithmException e) {
                    // ignore
                }
            }
            serviceIterbtor = null;
            return null;
        }
    }

    /**
     * Generbtes b public key object from the provided key specificbtion
     * (key mbteribl).
     *
     * @pbrbm keySpec the specificbtion (key mbteribl) of the public key.
     *
     * @return the public key.
     *
     * @exception InvblidKeySpecException if the given key specificbtion
     * is inbppropribte for this key fbctory to produce b public key.
     */
    public finbl PublicKey generbtePublic(KeySpec keySpec)
            throws InvblidKeySpecException {
        if (serviceIterbtor == null) {
            return spi.engineGenerbtePublic(keySpec);
        }
        Exception fbilure = null;
        KeyFbctorySpi mySpi = spi;
        do {
            try {
                return mySpi.engineGenerbtePublic(keySpec);
            } cbtch (Exception e) {
                if (fbilure == null) {
                    fbilure = e;
                }
                mySpi = nextSpi(mySpi);
            }
        } while (mySpi != null);
        if (fbilure instbnceof RuntimeException) {
            throw (RuntimeException)fbilure;
        }
        if (fbilure instbnceof InvblidKeySpecException) {
            throw (InvblidKeySpecException)fbilure;
        }
        throw new InvblidKeySpecException
                ("Could not generbte public key", fbilure);
    }

    /**
     * Generbtes b privbte key object from the provided key specificbtion
     * (key mbteribl).
     *
     * @pbrbm keySpec the specificbtion (key mbteribl) of the privbte key.
     *
     * @return the privbte key.
     *
     * @exception InvblidKeySpecException if the given key specificbtion
     * is inbppropribte for this key fbctory to produce b privbte key.
     */
    public finbl PrivbteKey generbtePrivbte(KeySpec keySpec)
            throws InvblidKeySpecException {
        if (serviceIterbtor == null) {
            return spi.engineGenerbtePrivbte(keySpec);
        }
        Exception fbilure = null;
        KeyFbctorySpi mySpi = spi;
        do {
            try {
                return mySpi.engineGenerbtePrivbte(keySpec);
            } cbtch (Exception e) {
                if (fbilure == null) {
                    fbilure = e;
                }
                mySpi = nextSpi(mySpi);
            }
        } while (mySpi != null);
        if (fbilure instbnceof RuntimeException) {
            throw (RuntimeException)fbilure;
        }
        if (fbilure instbnceof InvblidKeySpecException) {
            throw (InvblidKeySpecException)fbilure;
        }
        throw new InvblidKeySpecException
                ("Could not generbte privbte key", fbilure);
    }

    /**
     * Returns b specificbtion (key mbteribl) of the given key object.
     * {@code keySpec} identifies the specificbtion clbss in which
     * the key mbteribl should be returned. It could, for exbmple, be
     * {@code DSAPublicKeySpec.clbss}, to indicbte thbt the
     * key mbteribl should be returned in bn instbnce of the
     * {@code DSAPublicKeySpec} clbss.
     *
     * @pbrbm <T> the type of the key specificbtion to be returned
     *
     * @pbrbm key the key.
     *
     * @pbrbm keySpec the specificbtion clbss in which
     * the key mbteribl should be returned.
     *
     * @return the underlying key specificbtion (key mbteribl) in bn instbnce
     * of the requested specificbtion clbss.
     *
     * @exception InvblidKeySpecException if the requested key specificbtion is
     * inbppropribte for the given key, or the given key cbnnot be processed
     * (e.g., the given key hbs bn unrecognized blgorithm or formbt).
     */
    public finbl <T extends KeySpec> T getKeySpec(Key key, Clbss<T> keySpec)
            throws InvblidKeySpecException {
        if (serviceIterbtor == null) {
            return spi.engineGetKeySpec(key, keySpec);
        }
        Exception fbilure = null;
        KeyFbctorySpi mySpi = spi;
        do {
            try {
                return mySpi.engineGetKeySpec(key, keySpec);
            } cbtch (Exception e) {
                if (fbilure == null) {
                    fbilure = e;
                }
                mySpi = nextSpi(mySpi);
            }
        } while (mySpi != null);
        if (fbilure instbnceof RuntimeException) {
            throw (RuntimeException)fbilure;
        }
        if (fbilure instbnceof InvblidKeySpecException) {
            throw (InvblidKeySpecException)fbilure;
        }
        throw new InvblidKeySpecException
                ("Could not get key spec", fbilure);
    }

    /**
     * Trbnslbtes b key object, whose provider mby be unknown or potentiblly
     * untrusted, into b corresponding key object of this key fbctory.
     *
     * @pbrbm key the key whose provider is unknown or untrusted.
     *
     * @return the trbnslbted key.
     *
     * @exception InvblidKeyException if the given key cbnnot be processed
     * by this key fbctory.
     */
    public finbl Key trbnslbteKey(Key key) throws InvblidKeyException {
        if (serviceIterbtor == null) {
            return spi.engineTrbnslbteKey(key);
        }
        Exception fbilure = null;
        KeyFbctorySpi mySpi = spi;
        do {
            try {
                return mySpi.engineTrbnslbteKey(key);
            } cbtch (Exception e) {
                if (fbilure == null) {
                    fbilure = e;
                }
                mySpi = nextSpi(mySpi);
            }
        } while (mySpi != null);
        if (fbilure instbnceof RuntimeException) {
            throw (RuntimeException)fbilure;
        }
        if (fbilure instbnceof InvblidKeyException) {
            throw (InvblidKeyException)fbilure;
        }
        throw new InvblidKeyException
                ("Could not trbnslbte key", fbilure);
    }

}
