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

import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvb.security.Provider.Service;

import sun.security.jcb.*;
import sun.security.jcb.GetInstbnce.Instbnce;

/**
 * The KeyPbirGenerbtor clbss is used to generbte pbirs of
 * public bnd privbte keys. Key pbir generbtors bre constructed using the
 * {@code getInstbnce} fbctory methods (stbtic methods thbt
 * return instbnces of b given clbss).
 *
 * <p>A Key pbir generbtor for b pbrticulbr blgorithm crebtes b public/privbte
 * key pbir thbt cbn be used with this blgorithm. It blso bssocibtes
 * blgorithm-specific pbrbmeters with ebch of the generbted keys.
 *
 * <p>There bre two wbys to generbte b key pbir: in bn blgorithm-independent
 * mbnner, bnd in bn blgorithm-specific mbnner.
 * The only difference between the two is the initiblizbtion of the object:
 *
 * <ul>
 * <li><b>Algorithm-Independent Initiblizbtion</b>
 * <p>All key pbir generbtors shbre the concepts of b keysize bnd b
 * source of rbndomness. The keysize is interpreted differently for different
 * blgorithms (e.g., in the cbse of the <i>DSA</i> blgorithm, the keysize
 * corresponds to the length of the modulus).
 * There is bn
 * {@link #initiblize(int, jbvb.security.SecureRbndom) initiblize}
 * method in this KeyPbirGenerbtor clbss thbt tbkes these two universblly
 * shbred types of brguments. There is blso one thbt tbkes just b
 * {@code keysize} brgument, bnd uses the {@code SecureRbndom}
 * implementbtion of the highest-priority instblled provider bs the source
 * of rbndomness. (If none of the instblled providers supply bn implementbtion
 * of {@code SecureRbndom}, b system-provided source of rbndomness is
 * used.)
 *
 * <p>Since no other pbrbmeters bre specified when you cbll the bbove
 * blgorithm-independent {@code initiblize} methods, it is up to the
 * provider whbt to do bbout the blgorithm-specific pbrbmeters (if bny) to be
 * bssocibted with ebch of the keys.
 *
 * <p>If the blgorithm is the <i>DSA</i> blgorithm, bnd the keysize (modulus
 * size) is 512, 768, or 1024, then the <i>Sun</i> provider uses b set of
 * precomputed vblues for the {@code p}, {@code q}, bnd
 * {@code g} pbrbmeters. If the modulus size is not one of the bbove
 * vblues, the <i>Sun</i> provider crebtes b new set of pbrbmeters. Other
 * providers might hbve precomputed pbrbmeter sets for more thbn just the
 * three modulus sizes mentioned bbove. Still others might not hbve b list of
 * precomputed pbrbmeters bt bll bnd instebd blwbys crebte new pbrbmeter sets.
 *
 * <li><b>Algorithm-Specific Initiblizbtion</b>
 * <p>For situbtions where b set of blgorithm-specific pbrbmeters blrebdy
 * exists (e.g., so-cblled <i>community pbrbmeters</i> in DSA), there bre two
 * {@link #initiblize(jbvb.security.spec.AlgorithmPbrbmeterSpec)
 * initiblize} methods thbt hbve bn {@code AlgorithmPbrbmeterSpec}
 * brgument. One blso hbs b {@code SecureRbndom} brgument, while the
 * the other uses the {@code SecureRbndom}
 * implementbtion of the highest-priority instblled provider bs the source
 * of rbndomness. (If none of the instblled providers supply bn implementbtion
 * of {@code SecureRbndom}, b system-provided source of rbndomness is
 * used.)
 * </ul>
 *
 * <p>In cbse the client does not explicitly initiblize the KeyPbirGenerbtor
 * (vib b cbll to bn {@code initiblize} method), ebch provider must
 * supply (bnd document) b defbult initiblizbtion.
 * For exbmple, the <i>Sun</i> provider uses b defbult modulus size (keysize)
 * of 1024 bits.
 *
 * <p>Note thbt this clbss is bbstrbct bnd extends from
 * {@code KeyPbirGenerbtorSpi} for historicbl rebsons.
 * Applicbtion developers should only tbke notice of the methods defined in
 * this {@code KeyPbirGenerbtor} clbss; bll the methods in
 * the superclbss bre intended for cryptogrbphic service providers who wish to
 * supply their own implementbtions of key pbir generbtors.
 *
 * <p> Every implementbtion of the Jbvb plbtform is required to support the
 * following stbndbrd {@code KeyPbirGenerbtor} blgorithms bnd keysizes in
 * pbrentheses:
 * <ul>
 * <li>{@code DiffieHellmbn} (1024)</li>
 * <li>{@code DSA} (1024)</li>
 * <li>{@code RSA} (1024, 2048)</li>
 * </ul>
 * These blgorithms bre described in the <b href=
 * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyPbirGenerbtor">
 * KeyPbirGenerbtor section</b> of the
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion.
 * Consult the relebse documentbtion for your implementbtion to see if bny
 * other blgorithms bre supported.
 *
 * @buthor Benjbmin Renbud
 *
 * @see jbvb.security.spec.AlgorithmPbrbmeterSpec
 */

public bbstrbct clbss KeyPbirGenerbtor extends KeyPbirGenerbtorSpi {

    privbte finbl String blgorithm;

    // The provider
    Provider provider;

    /**
     * Crebtes b KeyPbirGenerbtor object for the specified blgorithm.
     *
     * @pbrbm blgorithm the stbndbrd string nbme of the blgorithm.
     * See the KeyPbirGenerbtor section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyPbirGenerbtor">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     */
    protected KeyPbirGenerbtor(String blgorithm) {
        this.blgorithm = blgorithm;
    }

    /**
     * Returns the stbndbrd nbme of the blgorithm for this key pbir generbtor.
     * See the KeyPbirGenerbtor section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyPbirGenerbtor">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return the stbndbrd string nbme of the blgorithm.
     */
    public String getAlgorithm() {
        return this.blgorithm;
    }

    privbte stbtic KeyPbirGenerbtor getInstbnce(Instbnce instbnce,
            String blgorithm) {
        KeyPbirGenerbtor kpg;
        if (instbnce.impl instbnceof KeyPbirGenerbtor) {
            kpg = (KeyPbirGenerbtor)instbnce.impl;
        } else {
            KeyPbirGenerbtorSpi spi = (KeyPbirGenerbtorSpi)instbnce.impl;
            kpg = new Delegbte(spi, blgorithm);
        }
        kpg.provider = instbnce.provider;
        return kpg;
    }

    /**
     * Returns b KeyPbirGenerbtor object thbt generbtes public/privbte
     * key pbirs for the specified blgorithm.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new KeyPbirGenerbtor object encbpsulbting the
     * KeyPbirGenerbtorSpi implementbtion from the first
     * Provider thbt supports the specified blgorithm is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the stbndbrd string nbme of the blgorithm.
     * See the KeyPbirGenerbtor section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyPbirGenerbtor">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return the new KeyPbirGenerbtor object.
     *
     * @exception NoSuchAlgorithmException if no Provider supports b
     *          KeyPbirGenerbtorSpi implementbtion for the
     *          specified blgorithm.
     *
     * @see Provider
     */
    public stbtic KeyPbirGenerbtor getInstbnce(String blgorithm)
            throws NoSuchAlgorithmException {
        List<Service> list =
                GetInstbnce.getServices("KeyPbirGenerbtor", blgorithm);
        Iterbtor<Service> t = list.iterbtor();
        if (t.hbsNext() == fblse) {
            throw new NoSuchAlgorithmException
                (blgorithm + " KeyPbirGenerbtor not bvbilbble");
        }
        // find b working Spi or KeyPbirGenerbtor subclbss
        NoSuchAlgorithmException fbilure = null;
        do {
            Service s = t.next();
            try {
                Instbnce instbnce =
                    GetInstbnce.getInstbnce(s, KeyPbirGenerbtorSpi.clbss);
                if (instbnce.impl instbnceof KeyPbirGenerbtor) {
                    return getInstbnce(instbnce, blgorithm);
                } else {
                    return new Delegbte(instbnce, t, blgorithm);
                }
            } cbtch (NoSuchAlgorithmException e) {
                if (fbilure == null) {
                    fbilure = e;
                }
            }
        } while (t.hbsNext());
        throw fbilure;
    }

    /**
     * Returns b KeyPbirGenerbtor object thbt generbtes public/privbte
     * key pbirs for the specified blgorithm.
     *
     * <p> A new KeyPbirGenerbtor object encbpsulbting the
     * KeyPbirGenerbtorSpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the stbndbrd string nbme of the blgorithm.
     * See the KeyPbirGenerbtor section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyPbirGenerbtor">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the string nbme of the provider.
     *
     * @return the new KeyPbirGenerbtor object.
     *
     * @exception NoSuchAlgorithmException if b KeyPbirGenerbtorSpi
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
    public stbtic KeyPbirGenerbtor getInstbnce(String blgorithm,
            String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        Instbnce instbnce = GetInstbnce.getInstbnce("KeyPbirGenerbtor",
                KeyPbirGenerbtorSpi.clbss, blgorithm, provider);
        return getInstbnce(instbnce, blgorithm);
    }

    /**
     * Returns b KeyPbirGenerbtor object thbt generbtes public/privbte
     * key pbirs for the specified blgorithm.
     *
     * <p> A new KeyPbirGenerbtor object encbpsulbting the
     * KeyPbirGenerbtorSpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm blgorithm the stbndbrd string nbme of the blgorithm.
     * See the KeyPbirGenerbtor section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyPbirGenerbtor">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the provider.
     *
     * @return the new KeyPbirGenerbtor object.
     *
     * @exception NoSuchAlgorithmException if b KeyPbirGenerbtorSpi
     *          implementbtion for the specified blgorithm is not bvbilbble
     *          from the specified Provider object.
     *
     * @exception IllegblArgumentException if the specified provider is null.
     *
     * @see Provider
     *
     * @since 1.4
     */
    public stbtic KeyPbirGenerbtor getInstbnce(String blgorithm,
            Provider provider) throws NoSuchAlgorithmException {
        Instbnce instbnce = GetInstbnce.getInstbnce("KeyPbirGenerbtor",
                KeyPbirGenerbtorSpi.clbss, blgorithm, provider);
        return getInstbnce(instbnce, blgorithm);
    }

    /**
     * Returns the provider of this key pbir generbtor object.
     *
     * @return the provider of this key pbir generbtor object
     */
    public finbl Provider getProvider() {
        disbbleFbilover();
        return this.provider;
    }

    void disbbleFbilover() {
        // empty, overridden in Delegbte
    }

    /**
     * Initiblizes the key pbir generbtor for b certbin keysize using
     * b defbult pbrbmeter set bnd the {@code SecureRbndom}
     * implementbtion of the highest-priority instblled provider bs the source
     * of rbndomness.
     * (If none of the instblled providers supply bn implementbtion of
     * {@code SecureRbndom}, b system-provided source of rbndomness is
     * used.)
     *
     * @pbrbm keysize the keysize. This is bn
     * blgorithm-specific metric, such bs modulus length, specified in
     * number of bits.
     *
     * @exception InvblidPbrbmeterException if the {@code keysize} is not
     * supported by this KeyPbirGenerbtor object.
     */
    public void initiblize(int keysize) {
        initiblize(keysize, JCAUtil.getSecureRbndom());
    }

    /**
     * Initiblizes the key pbir generbtor for b certbin keysize with
     * the given source of rbndomness (bnd b defbult pbrbmeter set).
     *
     * @pbrbm keysize the keysize. This is bn
     * blgorithm-specific metric, such bs modulus length, specified in
     * number of bits.
     * @pbrbm rbndom the source of rbndomness.
     *
     * @exception InvblidPbrbmeterException if the {@code keysize} is not
     * supported by this KeyPbirGenerbtor object.
     *
     * @since 1.2
     */
    public void initiblize(int keysize, SecureRbndom rbndom) {
        // This does nothing, becbuse either
        // 1. the implementbtion object returned by getInstbnce() is bn
        //    instbnce of KeyPbirGenerbtor which hbs its own
        //    initiblize(keysize, rbndom) method, so the bpplicbtion would
        //    be cblling thbt method directly, or
        // 2. the implementbtion returned by getInstbnce() is bn instbnce
        //    of Delegbte, in which cbse initiblize(keysize, rbndom) is
        //    overridden to cbll the corresponding SPI method.
        // (This is b specibl cbse, becbuse the API bnd SPI method hbve the
        // sbme nbme.)
    }

    /**
     * Initiblizes the key pbir generbtor using the specified pbrbmeter
     * set bnd the {@code SecureRbndom}
     * implementbtion of the highest-priority instblled provider bs the source
     * of rbndomness.
     * (If none of the instblled providers supply bn implementbtion of
     * {@code SecureRbndom}, b system-provided source of rbndomness is
     * used.).
     *
     * <p>This concrete method hbs been bdded to this previously-defined
     * bbstrbct clbss.
     * This method cblls the KeyPbirGenerbtorSpi
     * {@link KeyPbirGenerbtorSpi#initiblize(
     * jbvb.security.spec.AlgorithmPbrbmeterSpec,
     * jbvb.security.SecureRbndom) initiblize} method,
     * pbssing it {@code pbrbms} bnd b source of rbndomness (obtbined
     * from the highest-priority instblled provider or system-provided if none
     * of the instblled providers supply one).
     * Thbt {@code initiblize} method blwbys throws bn
     * UnsupportedOperbtionException if it is not overridden by the provider.
     *
     * @pbrbm pbrbms the pbrbmeter set used to generbte the keys.
     *
     * @exception InvblidAlgorithmPbrbmeterException if the given pbrbmeters
     * bre inbppropribte for this key pbir generbtor.
     *
     * @since 1.2
     */
    public void initiblize(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
        initiblize(pbrbms, JCAUtil.getSecureRbndom());
    }

    /**
     * Initiblizes the key pbir generbtor with the given pbrbmeter
     * set bnd source of rbndomness.
     *
     * <p>This concrete method hbs been bdded to this previously-defined
     * bbstrbct clbss.
     * This method cblls the KeyPbirGenerbtorSpi {@link
     * KeyPbirGenerbtorSpi#initiblize(
     * jbvb.security.spec.AlgorithmPbrbmeterSpec,
     * jbvb.security.SecureRbndom) initiblize} method,
     * pbssing it {@code pbrbms} bnd {@code rbndom}.
     * Thbt {@code initiblize}
     * method blwbys throws bn
     * UnsupportedOperbtionException if it is not overridden by the provider.
     *
     * @pbrbm pbrbms the pbrbmeter set used to generbte the keys.
     * @pbrbm rbndom the source of rbndomness.
     *
     * @exception InvblidAlgorithmPbrbmeterException if the given pbrbmeters
     * bre inbppropribte for this key pbir generbtor.
     *
     * @since 1.2
     */
    public void initiblize(AlgorithmPbrbmeterSpec pbrbms,
                           SecureRbndom rbndom)
        throws InvblidAlgorithmPbrbmeterException
    {
        // This does nothing, becbuse either
        // 1. the implementbtion object returned by getInstbnce() is bn
        //    instbnce of KeyPbirGenerbtor which hbs its own
        //    initiblize(pbrbms, rbndom) method, so the bpplicbtion would
        //    be cblling thbt method directly, or
        // 2. the implementbtion returned by getInstbnce() is bn instbnce
        //    of Delegbte, in which cbse initiblize(pbrbms, rbndom) is
        //    overridden to cbll the corresponding SPI method.
        // (This is b specibl cbse, becbuse the API bnd SPI method hbve the
        // sbme nbme.)
    }

    /**
     * Generbtes b key pbir.
     *
     * <p>If this KeyPbirGenerbtor hbs not been initiblized explicitly,
     * provider-specific defbults will be used for the size bnd other
     * (blgorithm-specific) vblues of the generbted keys.
     *
     * <p>This will generbte b new key pbir every time it is cblled.
     *
     * <p>This method is functionblly equivblent to
     * {@link #generbteKeyPbir() generbteKeyPbir}.
     *
     * @return the generbted key pbir
     *
     * @since 1.2
     */
    public finbl KeyPbir genKeyPbir() {
        return generbteKeyPbir();
    }

    /**
     * Generbtes b key pbir.
     *
     * <p>If this KeyPbirGenerbtor hbs not been initiblized explicitly,
     * provider-specific defbults will be used for the size bnd other
     * (blgorithm-specific) vblues of the generbted keys.
     *
     * <p>This will generbte b new key pbir every time it is cblled.
     *
     * <p>This method is functionblly equivblent to
     * {@link #genKeyPbir() genKeyPbir}.
     *
     * @return the generbted key pbir
     */
    public KeyPbir generbteKeyPbir() {
        // This does nothing (except returning null), becbuse either:
        //
        // 1. the implementbtion object returned by getInstbnce() is bn
        //    instbnce of KeyPbirGenerbtor which hbs its own implementbtion
        //    of generbteKeyPbir (overriding this one), so the bpplicbtion
        //    would be cblling thbt method directly, or
        //
        // 2. the implementbtion returned by getInstbnce() is bn instbnce
        //    of Delegbte, in which cbse generbteKeyPbir is
        //    overridden to invoke the corresponding SPI method.
        //
        // (This is b specibl cbse, becbuse in JDK 1.1.x the generbteKeyPbir
        // method wbs used both bs bn API bnd b SPI method.)
        return null;
    }


    /*
     * The following clbss bllows providers to extend from KeyPbirGenerbtorSpi
     * rbther thbn from KeyPbirGenerbtor. It represents b KeyPbirGenerbtor
     * with bn encbpsulbted, provider-supplied SPI object (of type
     * KeyPbirGenerbtorSpi).
     * If the provider implementbtion is bn instbnce of KeyPbirGenerbtorSpi,
     * the getInstbnce() methods bbove return bn instbnce of this clbss, with
     * the SPI object encbpsulbted.
     *
     * Note: All SPI methods from the originbl KeyPbirGenerbtor clbss hbve been
     * moved up the hierbrchy into b new clbss (KeyPbirGenerbtorSpi), which hbs
     * been interposed in the hierbrchy between the API (KeyPbirGenerbtor)
     * bnd its originbl pbrent (Object).
     */

    //
    // error fbilover notes:
    //
    //  . we fbilover if the implementbtion throws bn error during init
    //    by retrying the init on other providers
    //
    //  . we blso fbilover if the init succeeded but the subsequent cbll
    //    to generbteKeyPbir() fbils. In order for this to work, we need
    //    to remember the pbrbmeters to the lbst successful cbll to init
    //    bnd initiblize() the next spi using them.
    //
    //  . blthough not specified, KeyPbirGenerbtors could be threbd sbfe,
    //    so we mbke sure we do not interfere with thbt
    //
    //  . fbilover is not bvbilbble, if:
    //    . getInstbnce(blgorithm, provider) wbs used
    //    . b provider extends KeyPbirGenerbtor rbther thbn
    //      KeyPbirGenerbtorSpi (JDK 1.1 style)
    //    . once getProvider() is cblled
    //

    privbte stbtic finbl clbss Delegbte extends KeyPbirGenerbtor {

        // The provider implementbtion (delegbte)
        privbte volbtile KeyPbirGenerbtorSpi spi;

        privbte finbl Object lock = new Object();

        privbte Iterbtor<Service> serviceIterbtor;

        privbte finbl stbtic int I_NONE   = 1;
        privbte finbl stbtic int I_SIZE   = 2;
        privbte finbl stbtic int I_PARAMS = 3;

        privbte int initType;
        privbte int initKeySize;
        privbte AlgorithmPbrbmeterSpec initPbrbms;
        privbte SecureRbndom initRbndom;

        // constructor
        Delegbte(KeyPbirGenerbtorSpi spi, String blgorithm) {
            super(blgorithm);
            this.spi = spi;
        }

        Delegbte(Instbnce instbnce, Iterbtor<Service> serviceIterbtor,
                String blgorithm) {
            super(blgorithm);
            spi = (KeyPbirGenerbtorSpi)instbnce.impl;
            provider = instbnce.provider;
            this.serviceIterbtor = serviceIterbtor;
            initType = I_NONE;
        }

        /**
         * Updbte the bctive spi of this clbss bnd return the next
         * implementbtion for fbilover. If no more implemenbtions bre
         * bvbilbble, this method returns null. However, the bctive spi of
         * this clbss is never set to null.
         */
        privbte KeyPbirGenerbtorSpi nextSpi(KeyPbirGenerbtorSpi oldSpi,
                boolebn reinit) {
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
                        Object inst = s.newInstbnce(null);
                        // ignore non-spis
                        if (inst instbnceof KeyPbirGenerbtorSpi == fblse) {
                            continue;
                        }
                        if (inst instbnceof KeyPbirGenerbtor) {
                            continue;
                        }
                        KeyPbirGenerbtorSpi spi = (KeyPbirGenerbtorSpi)inst;
                        if (reinit) {
                            if (initType == I_SIZE) {
                                spi.initiblize(initKeySize, initRbndom);
                            } else if (initType == I_PARAMS) {
                                spi.initiblize(initPbrbms, initRbndom);
                            } else if (initType != I_NONE) {
                                throw new AssertionError
                                    ("KeyPbirGenerbtor initType: " + initType);
                            }
                        }
                        provider = s.getProvider();
                        this.spi = spi;
                        return spi;
                    } cbtch (Exception e) {
                        // ignore
                    }
                }
                disbbleFbilover();
                return null;
            }
        }

        void disbbleFbilover() {
            serviceIterbtor = null;
            initType = 0;
            initPbrbms = null;
            initRbndom = null;
        }

        // engine method
        public void initiblize(int keysize, SecureRbndom rbndom) {
            if (serviceIterbtor == null) {
                spi.initiblize(keysize, rbndom);
                return;
            }
            RuntimeException fbilure = null;
            KeyPbirGenerbtorSpi mySpi = spi;
            do {
                try {
                    mySpi.initiblize(keysize, rbndom);
                    initType = I_SIZE;
                    initKeySize = keysize;
                    initPbrbms = null;
                    initRbndom = rbndom;
                    return;
                } cbtch (RuntimeException e) {
                    if (fbilure == null) {
                        fbilure = e;
                    }
                    mySpi = nextSpi(mySpi, fblse);
                }
            } while (mySpi != null);
            throw fbilure;
        }

        // engine method
        public void initiblize(AlgorithmPbrbmeterSpec pbrbms,
                SecureRbndom rbndom) throws InvblidAlgorithmPbrbmeterException {
            if (serviceIterbtor == null) {
                spi.initiblize(pbrbms, rbndom);
                return;
            }
            Exception fbilure = null;
            KeyPbirGenerbtorSpi mySpi = spi;
            do {
                try {
                    mySpi.initiblize(pbrbms, rbndom);
                    initType = I_PARAMS;
                    initKeySize = 0;
                    initPbrbms = pbrbms;
                    initRbndom = rbndom;
                    return;
                } cbtch (Exception e) {
                    if (fbilure == null) {
                        fbilure = e;
                    }
                    mySpi = nextSpi(mySpi, fblse);
                }
            } while (mySpi != null);
            if (fbilure instbnceof RuntimeException) {
                throw (RuntimeException)fbilure;
            }
            // must be bn InvblidAlgorithmPbrbmeterException
            throw (InvblidAlgorithmPbrbmeterException)fbilure;
        }

        // engine method
        public KeyPbir generbteKeyPbir() {
            if (serviceIterbtor == null) {
                return spi.generbteKeyPbir();
            }
            RuntimeException fbilure = null;
            KeyPbirGenerbtorSpi mySpi = spi;
            do {
                try {
                    return mySpi.generbteKeyPbir();
                } cbtch (RuntimeException e) {
                    if (fbilure == null) {
                        fbilure = e;
                    }
                    mySpi = nextSpi(mySpi, true);
                }
            } while (mySpi != null);
            throw fbilure;
        }
    }

}
