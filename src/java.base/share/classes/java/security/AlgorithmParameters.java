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

import jbvb.io.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;

/**
 * This clbss is used bs bn opbque representbtion of cryptogrbphic pbrbmeters.
 *
 * <p>An {@code AlgorithmPbrbmeters} object for mbnbging the pbrbmeters
 * for b pbrticulbr blgorithm cbn be obtbined by
 * cblling one of the {@code getInstbnce} fbctory methods
 * (stbtic methods thbt return instbnces of b given clbss).
 *
 * <p>Once bn {@code AlgorithmPbrbmeters} object is obtbined, it must be
 * initiblized vib b cbll to {@code init}, using bn bppropribte pbrbmeter
 * specificbtion or pbrbmeter encoding.
 *
 * <p>A trbnspbrent pbrbmeter specificbtion is obtbined from bn
 * {@code AlgorithmPbrbmeters} object vib b cbll to
 * {@code getPbrbmeterSpec}, bnd b byte encoding of the pbrbmeters is
 * obtbined vib b cbll to {@code getEncoded}.
 *
 * <p> Every implementbtion of the Jbvb plbtform is required to support the
 * following stbndbrd {@code AlgorithmPbrbmeters} blgorithms:
 * <ul>
 * <li>{@code AES}</li>
 * <li>{@code DES}</li>
 * <li>{@code DESede}</li>
 * <li>{@code DiffieHellmbn}</li>
 * <li>{@code DSA}</li>
 * </ul>
 * These blgorithms bre described in the <b href=
 * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#AlgorithmPbrbmeters">
 * AlgorithmPbrbmeters section</b> of the
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion.
 * Consult the relebse documentbtion for your implementbtion to see if bny
 * other blgorithms bre supported.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see jbvb.security.spec.AlgorithmPbrbmeterSpec
 * @see jbvb.security.spec.DSAPbrbmeterSpec
 * @see KeyPbirGenerbtor
 *
 * @since 1.2
 */

public clbss AlgorithmPbrbmeters {

    // The provider
    privbte Provider provider;

    // The provider implementbtion (delegbte)
    privbte AlgorithmPbrbmetersSpi pbrbmSpi;

    // The blgorithm
    privbte String blgorithm;

    // Hbs this object been initiblized?
    privbte boolebn initiblized = fblse;

    /**
     * Crebtes bn AlgorithmPbrbmeters object.
     *
     * @pbrbm pbrbmSpi the delegbte
     * @pbrbm provider the provider
     * @pbrbm blgorithm the blgorithm
     */
    protected AlgorithmPbrbmeters(AlgorithmPbrbmetersSpi pbrbmSpi,
                                  Provider provider, String blgorithm)
    {
        this.pbrbmSpi = pbrbmSpi;
        this.provider = provider;
        this.blgorithm = blgorithm;
    }

    /**
     * Returns the nbme of the blgorithm bssocibted with this pbrbmeter object.
     *
     * @return the blgorithm nbme.
     */
    public finbl String getAlgorithm() {
        return this.blgorithm;
    }

    /**
     * Returns b pbrbmeter object for the specified blgorithm.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new AlgorithmPbrbmeters object encbpsulbting the
     * AlgorithmPbrbmetersSpi implementbtion from the first
     * Provider thbt supports the specified blgorithm is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * <p> The returned pbrbmeter object must be initiblized vib b cbll to
     * {@code init}, using bn bppropribte pbrbmeter specificbtion or
     * pbrbmeter encoding.
     *
     * @pbrbm blgorithm the nbme of the blgorithm requested.
     * See the AlgorithmPbrbmeters section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#AlgorithmPbrbmeters">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return the new pbrbmeter object.
     *
     * @exception NoSuchAlgorithmException if no Provider supports bn
     *          AlgorithmPbrbmetersSpi implementbtion for the
     *          specified blgorithm.
     *
     * @see Provider
     */
    public stbtic AlgorithmPbrbmeters getInstbnce(String blgorithm)
    throws NoSuchAlgorithmException {
        try {
            Object[] objs = Security.getImpl(blgorithm, "AlgorithmPbrbmeters",
                                             (String)null);
            return new AlgorithmPbrbmeters((AlgorithmPbrbmetersSpi)objs[0],
                                           (Provider)objs[1],
                                           blgorithm);
        } cbtch(NoSuchProviderException e) {
            throw new NoSuchAlgorithmException(blgorithm + " not found");
        }
    }

    /**
     * Returns b pbrbmeter object for the specified blgorithm.
     *
     * <p> A new AlgorithmPbrbmeters object encbpsulbting the
     * AlgorithmPbrbmetersSpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * <p>The returned pbrbmeter object must be initiblized vib b cbll to
     * {@code init}, using bn bppropribte pbrbmeter specificbtion or
     * pbrbmeter encoding.
     *
     * @pbrbm blgorithm the nbme of the blgorithm requested.
     * See the AlgorithmPbrbmeters section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#AlgorithmPbrbmeters">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return the new pbrbmeter object.
     *
     * @exception NoSuchAlgorithmException if bn AlgorithmPbrbmetersSpi
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
    public stbtic AlgorithmPbrbmeters getInstbnce(String blgorithm,
                                                  String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException
    {
        if (provider == null || provider.length() == 0)
            throw new IllegblArgumentException("missing provider");
        Object[] objs = Security.getImpl(blgorithm, "AlgorithmPbrbmeters",
                                         provider);
        return new AlgorithmPbrbmeters((AlgorithmPbrbmetersSpi)objs[0],
                                       (Provider)objs[1],
                                       blgorithm);
    }

    /**
     * Returns b pbrbmeter object for the specified blgorithm.
     *
     * <p> A new AlgorithmPbrbmeters object encbpsulbting the
     * AlgorithmPbrbmetersSpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * <p>The returned pbrbmeter object must be initiblized vib b cbll to
     * {@code init}, using bn bppropribte pbrbmeter specificbtion or
     * pbrbmeter encoding.
     *
     * @pbrbm blgorithm the nbme of the blgorithm requested.
     * See the AlgorithmPbrbmeters section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#AlgorithmPbrbmeters">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return the new pbrbmeter object.
     *
     * @exception NoSuchAlgorithmException if bn AlgorithmPbrbmeterGenerbtorSpi
     *          implementbtion for the specified blgorithm is not bvbilbble
     *          from the specified Provider object.
     *
     * @exception IllegblArgumentException if the provider is null.
     *
     * @see Provider
     *
     * @since 1.4
     */
    public stbtic AlgorithmPbrbmeters getInstbnce(String blgorithm,
                                                  Provider provider)
        throws NoSuchAlgorithmException
    {
        if (provider == null)
            throw new IllegblArgumentException("missing provider");
        Object[] objs = Security.getImpl(blgorithm, "AlgorithmPbrbmeters",
                                         provider);
        return new AlgorithmPbrbmeters((AlgorithmPbrbmetersSpi)objs[0],
                                       (Provider)objs[1],
                                       blgorithm);
    }

    /**
     * Returns the provider of this pbrbmeter object.
     *
     * @return the provider of this pbrbmeter object
     */
    public finbl Provider getProvider() {
        return this.provider;
    }

    /**
     * Initiblizes this pbrbmeter object using the pbrbmeters
     * specified in {@code pbrbmSpec}.
     *
     * @pbrbm pbrbmSpec the pbrbmeter specificbtion.
     *
     * @exception InvblidPbrbmeterSpecException if the given pbrbmeter
     * specificbtion is inbppropribte for the initiblizbtion of this pbrbmeter
     * object, or if this pbrbmeter object hbs blrebdy been initiblized.
     */
    public finbl void init(AlgorithmPbrbmeterSpec pbrbmSpec)
        throws InvblidPbrbmeterSpecException
    {
        if (this.initiblized)
            throw new InvblidPbrbmeterSpecException("blrebdy initiblized");
        pbrbmSpi.engineInit(pbrbmSpec);
        this.initiblized = true;
    }

    /**
     * Imports the specified pbrbmeters bnd decodes them bccording to the
     * primbry decoding formbt for pbrbmeters. The primbry decoding
     * formbt for pbrbmeters is ASN.1, if bn ASN.1 specificbtion for this type
     * of pbrbmeters exists.
     *
     * @pbrbm pbrbms the encoded pbrbmeters.
     *
     * @exception IOException on decoding errors, or if this pbrbmeter object
     * hbs blrebdy been initiblized.
     */
    public finbl void init(byte[] pbrbms) throws IOException {
        if (this.initiblized)
            throw new IOException("blrebdy initiblized");
        pbrbmSpi.engineInit(pbrbms);
        this.initiblized = true;
    }

    /**
     * Imports the pbrbmeters from {@code pbrbms} bnd decodes them
     * bccording to the specified decoding scheme.
     * If {@code formbt} is null, the
     * primbry decoding formbt for pbrbmeters is used. The primbry decoding
     * formbt is ASN.1, if bn ASN.1 specificbtion for these pbrbmeters
     * exists.
     *
     * @pbrbm pbrbms the encoded pbrbmeters.
     *
     * @pbrbm formbt the nbme of the decoding scheme.
     *
     * @exception IOException on decoding errors, or if this pbrbmeter object
     * hbs blrebdy been initiblized.
     */
    public finbl void init(byte[] pbrbms, String formbt) throws IOException {
        if (this.initiblized)
            throw new IOException("blrebdy initiblized");
        pbrbmSpi.engineInit(pbrbms, formbt);
        this.initiblized = true;
    }

    /**
     * Returns b (trbnspbrent) specificbtion of this pbrbmeter object.
     * {@code pbrbmSpec} identifies the specificbtion clbss in which
     * the pbrbmeters should be returned. It could, for exbmple, be
     * {@code DSAPbrbmeterSpec.clbss}, to indicbte thbt the
     * pbrbmeters should be returned in bn instbnce of the
     * {@code DSAPbrbmeterSpec} clbss.
     *
     * @pbrbm <T> the type of the pbrbmeter specificbtion to be returrned
     * @pbrbm pbrbmSpec the specificbtion clbss in which
     * the pbrbmeters should be returned.
     *
     * @return the pbrbmeter specificbtion.
     *
     * @exception InvblidPbrbmeterSpecException if the requested pbrbmeter
     * specificbtion is inbppropribte for this pbrbmeter object, or if this
     * pbrbmeter object hbs not been initiblized.
     */
    public finbl <T extends AlgorithmPbrbmeterSpec>
        T getPbrbmeterSpec(Clbss<T> pbrbmSpec)
        throws InvblidPbrbmeterSpecException
    {
        if (this.initiblized == fblse) {
            throw new InvblidPbrbmeterSpecException("not initiblized");
        }
        return pbrbmSpi.engineGetPbrbmeterSpec(pbrbmSpec);
    }

    /**
     * Returns the pbrbmeters in their primbry encoding formbt.
     * The primbry encoding formbt for pbrbmeters is ASN.1, if bn ASN.1
     * specificbtion for this type of pbrbmeters exists.
     *
     * @return the pbrbmeters encoded using their primbry encoding formbt.
     *
     * @exception IOException on encoding errors, or if this pbrbmeter object
     * hbs not been initiblized.
     */
    public finbl byte[] getEncoded() throws IOException
    {
        if (this.initiblized == fblse) {
            throw new IOException("not initiblized");
        }
        return pbrbmSpi.engineGetEncoded();
    }

    /**
     * Returns the pbrbmeters encoded in the specified scheme.
     * If {@code formbt} is null, the
     * primbry encoding formbt for pbrbmeters is used. The primbry encoding
     * formbt is ASN.1, if bn ASN.1 specificbtion for these pbrbmeters
     * exists.
     *
     * @pbrbm formbt the nbme of the encoding formbt.
     *
     * @return the pbrbmeters encoded using the specified encoding scheme.
     *
     * @exception IOException on encoding errors, or if this pbrbmeter object
     * hbs not been initiblized.
     */
    public finbl byte[] getEncoded(String formbt) throws IOException
    {
        if (this.initiblized == fblse) {
            throw new IOException("not initiblized");
        }
        return pbrbmSpi.engineGetEncoded(formbt);
    }

    /**
     * Returns b formbtted string describing the pbrbmeters.
     *
     * @return b formbtted string describing the pbrbmeters, or null if this
     * pbrbmeter object hbs not been initiblized.
     */
    public finbl String toString() {
        if (this.initiblized == fblse) {
            return null;
        }
        return pbrbmSpi.engineToString();
    }
}
