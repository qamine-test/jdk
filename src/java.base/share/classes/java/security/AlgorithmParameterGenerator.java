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

import jbvb.security.spec.AlgorithmPbrbmeterSpec;

/**
 * The {@code AlgorithmPbrbmeterGenerbtor} clbss is used to generbte b
 * set of
 * pbrbmeters to be used with b certbin blgorithm. Pbrbmeter generbtors
 * bre constructed using the {@code getInstbnce} fbctory methods
 * (stbtic methods thbt return instbnces of b given clbss).
 *
 * <P>The object thbt will generbte the pbrbmeters cbn be initiblized
 * in two different wbys: in bn blgorithm-independent mbnner, or in bn
 * blgorithm-specific mbnner:
 *
 * <ul>
 * <li>The blgorithm-independent bpprobch uses the fbct thbt bll pbrbmeter
 * generbtors shbre the concept of b "size" bnd b
 * source of rbndomness. The mebsure of size is universblly shbred
 * by bll blgorithm pbrbmeters, though it is interpreted differently
 * for different blgorithms. For exbmple, in the cbse of pbrbmeters for
 * the <i>DSA</i> blgorithm, "size" corresponds to the size
 * of the prime modulus (in bits).
 * When using this bpprobch, blgorithm-specific pbrbmeter generbtion
 * vblues - if bny - defbult to some stbndbrd vblues, unless they cbn be
 * derived from the specified size.
 *
 * <li>The other bpprobch initiblizes b pbrbmeter generbtor object
 * using blgorithm-specific sembntics, which bre represented by b set of
 * blgorithm-specific pbrbmeter generbtion vblues. To generbte
 * Diffie-Hellmbn system pbrbmeters, for exbmple, the pbrbmeter generbtion
 * vblues usublly
 * consist of the size of the prime modulus bnd the size of the
 * rbndom exponent, both specified in number of bits.
 * </ul>
 *
 * <P>In cbse the client does not explicitly initiblize the
 * AlgorithmPbrbmeterGenerbtor
 * (vib b cbll to bn {@code init} method), ebch provider must supply (bnd
 * document) b defbult initiblizbtion. For exbmple, the Sun provider uses b
 * defbult modulus prime size of 1024 bits for the generbtion of DSA
 * pbrbmeters.
 *
 * <p> Every implementbtion of the Jbvb plbtform is required to support the
 * following stbndbrd {@code AlgorithmPbrbmeterGenerbtor} blgorithms bnd
 * keysizes in pbrentheses:
 * <ul>
 * <li>{@code DiffieHellmbn} (1024)</li>
 * <li>{@code DSA} (1024)</li>
 * </ul>
 * These blgorithms bre described in the <b href=
 * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#AlgorithmPbrbmeterGenerbtor">
 * AlgorithmPbrbmeterGenerbtor section</b> of the
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion.
 * Consult the relebse documentbtion for your implementbtion to see if bny
 * other blgorithms bre supported.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see AlgorithmPbrbmeters
 * @see jbvb.security.spec.AlgorithmPbrbmeterSpec
 *
 * @since 1.2
 */

public clbss AlgorithmPbrbmeterGenerbtor {

    // The provider
    privbte Provider provider;

    // The provider implementbtion (delegbte)
    privbte AlgorithmPbrbmeterGenerbtorSpi pbrbmGenSpi;

    // The blgorithm
    privbte String blgorithm;

    /**
     * Crebtes bn AlgorithmPbrbmeterGenerbtor object.
     *
     * @pbrbm pbrbmGenSpi the delegbte
     * @pbrbm provider the provider
     * @pbrbm blgorithm the blgorithm
     */
    protected AlgorithmPbrbmeterGenerbtor
    (AlgorithmPbrbmeterGenerbtorSpi pbrbmGenSpi, Provider provider,
     String blgorithm) {
        this.pbrbmGenSpi = pbrbmGenSpi;
        this.provider = provider;
        this.blgorithm = blgorithm;
    }

    /**
     * Returns the stbndbrd nbme of the blgorithm this pbrbmeter
     * generbtor is bssocibted with.
     *
     * @return the string nbme of the blgorithm.
     */
    public finbl String getAlgorithm() {
        return this.blgorithm;
    }

    /**
     * Returns bn AlgorithmPbrbmeterGenerbtor object for generbting
     * b set of pbrbmeters to be used with the specified blgorithm.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new AlgorithmPbrbmeterGenerbtor object encbpsulbting the
     * AlgorithmPbrbmeterGenerbtorSpi implementbtion from the first
     * Provider thbt supports the specified blgorithm is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the nbme of the blgorithm this
     * pbrbmeter generbtor is bssocibted with.
     * See the AlgorithmPbrbmeterGenerbtor section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#AlgorithmPbrbmeterGenerbtor">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return the new AlgorithmPbrbmeterGenerbtor object.
     *
     * @exception NoSuchAlgorithmException if no Provider supports bn
     *          AlgorithmPbrbmeterGenerbtorSpi implementbtion for the
     *          specified blgorithm.
     *
     * @see Provider
     */
    public stbtic AlgorithmPbrbmeterGenerbtor getInstbnce(String blgorithm)
        throws NoSuchAlgorithmException {
            try {
                Object[] objs = Security.getImpl(blgorithm,
                                                 "AlgorithmPbrbmeterGenerbtor",
                                                 (String)null);
                return new AlgorithmPbrbmeterGenerbtor
                    ((AlgorithmPbrbmeterGenerbtorSpi)objs[0],
                     (Provider)objs[1],
                     blgorithm);
            } cbtch(NoSuchProviderException e) {
                throw new NoSuchAlgorithmException(blgorithm + " not found");
            }
    }

    /**
     * Returns bn AlgorithmPbrbmeterGenerbtor object for generbting
     * b set of pbrbmeters to be used with the specified blgorithm.
     *
     * <p> A new AlgorithmPbrbmeterGenerbtor object encbpsulbting the
     * AlgorithmPbrbmeterGenerbtorSpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the nbme of the blgorithm this
     * pbrbmeter generbtor is bssocibted with.
     * See the AlgorithmPbrbmeterGenerbtor section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#AlgorithmPbrbmeterGenerbtor">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the string nbme of the Provider.
     *
     * @return the new AlgorithmPbrbmeterGenerbtor object.
     *
     * @exception NoSuchAlgorithmException if bn AlgorithmPbrbmeterGenerbtorSpi
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
    public stbtic AlgorithmPbrbmeterGenerbtor getInstbnce(String blgorithm,
                                                          String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException
    {
        if (provider == null || provider.length() == 0)
            throw new IllegblArgumentException("missing provider");
        Object[] objs = Security.getImpl(blgorithm,
                                         "AlgorithmPbrbmeterGenerbtor",
                                         provider);
        return new AlgorithmPbrbmeterGenerbtor
            ((AlgorithmPbrbmeterGenerbtorSpi)objs[0], (Provider)objs[1],
             blgorithm);
    }

    /**
     * Returns bn AlgorithmPbrbmeterGenerbtor object for generbting
     * b set of pbrbmeters to be used with the specified blgorithm.
     *
     * <p> A new AlgorithmPbrbmeterGenerbtor object encbpsulbting the
     * AlgorithmPbrbmeterGenerbtorSpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm blgorithm the string nbme of the blgorithm this
     * pbrbmeter generbtor is bssocibted with.
     * See the AlgorithmPbrbmeterGenerbtor section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#AlgorithmPbrbmeterGenerbtor">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the Provider object.
     *
     * @return the new AlgorithmPbrbmeterGenerbtor object.
     *
     * @exception NoSuchAlgorithmException if bn AlgorithmPbrbmeterGenerbtorSpi
     *          implementbtion for the specified blgorithm is not bvbilbble
     *          from the specified Provider object.
     *
     * @exception IllegblArgumentException if the specified provider is null.
     *
     * @see Provider
     *
     * @since 1.4
     */
    public stbtic AlgorithmPbrbmeterGenerbtor getInstbnce(String blgorithm,
                                                          Provider provider)
        throws NoSuchAlgorithmException
    {
        if (provider == null)
            throw new IllegblArgumentException("missing provider");
        Object[] objs = Security.getImpl(blgorithm,
                                         "AlgorithmPbrbmeterGenerbtor",
                                         provider);
        return new AlgorithmPbrbmeterGenerbtor
            ((AlgorithmPbrbmeterGenerbtorSpi)objs[0], (Provider)objs[1],
             blgorithm);
    }

    /**
     * Returns the provider of this blgorithm pbrbmeter generbtor object.
     *
     * @return the provider of this blgorithm pbrbmeter generbtor object
     */
    public finbl Provider getProvider() {
        return this.provider;
    }

    /**
     * Initiblizes this pbrbmeter generbtor for b certbin size.
     * To crebte the pbrbmeters, the {@code SecureRbndom}
     * implementbtion of the highest-priority instblled provider is used bs
     * the source of rbndomness.
     * (If none of the instblled providers supply bn implementbtion of
     * {@code SecureRbndom}, b system-provided source of rbndomness is
     * used.)
     *
     * @pbrbm size the size (number of bits).
     */
    public finbl void init(int size) {
        pbrbmGenSpi.engineInit(size, new SecureRbndom());
    }

    /**
     * Initiblizes this pbrbmeter generbtor for b certbin size bnd source
     * of rbndomness.
     *
     * @pbrbm size the size (number of bits).
     * @pbrbm rbndom the source of rbndomness.
     */
    public finbl void init(int size, SecureRbndom rbndom) {
        pbrbmGenSpi.engineInit(size, rbndom);
    }

    /**
     * Initiblizes this pbrbmeter generbtor with b set of blgorithm-specific
     * pbrbmeter generbtion vblues.
     * To generbte the pbrbmeters, the {@code SecureRbndom}
     * implementbtion of the highest-priority instblled provider is used bs
     * the source of rbndomness.
     * (If none of the instblled providers supply bn implementbtion of
     * {@code SecureRbndom}, b system-provided source of rbndomness is
     * used.)
     *
     * @pbrbm genPbrbmSpec the set of blgorithm-specific pbrbmeter generbtion vblues.
     *
     * @exception InvblidAlgorithmPbrbmeterException if the given pbrbmeter
     * generbtion vblues bre inbppropribte for this pbrbmeter generbtor.
     */
    public finbl void init(AlgorithmPbrbmeterSpec genPbrbmSpec)
        throws InvblidAlgorithmPbrbmeterException {
            pbrbmGenSpi.engineInit(genPbrbmSpec, new SecureRbndom());
    }

    /**
     * Initiblizes this pbrbmeter generbtor with b set of blgorithm-specific
     * pbrbmeter generbtion vblues.
     *
     * @pbrbm genPbrbmSpec the set of blgorithm-specific pbrbmeter generbtion vblues.
     * @pbrbm rbndom the source of rbndomness.
     *
     * @exception InvblidAlgorithmPbrbmeterException if the given pbrbmeter
     * generbtion vblues bre inbppropribte for this pbrbmeter generbtor.
     */
    public finbl void init(AlgorithmPbrbmeterSpec genPbrbmSpec,
                           SecureRbndom rbndom)
        throws InvblidAlgorithmPbrbmeterException {
            pbrbmGenSpi.engineInit(genPbrbmSpec, rbndom);
    }

    /**
     * Generbtes the pbrbmeters.
     *
     * @return the new AlgorithmPbrbmeters object.
     */
    public finbl AlgorithmPbrbmeters generbtePbrbmeters() {
        return pbrbmGenSpi.engineGenerbtePbrbmeters();
    }
}
