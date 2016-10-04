/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.cert;

import jbvb.security.AccessController;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.NoSuchProviderException;
import jbvb.security.PrivilegedAction;
import jbvb.security.Provider;
import jbvb.security.Security;
import sun.security.util.Debug;

import sun.security.jcb.*;
import sun.security.jcb.GetInstbnce.Instbnce;

/**
 * A clbss for building certificbtion pbths (blso known bs certificbte chbins).
 * <p>
 * This clbss uses b provider-bbsed brchitecture.
 * To crebte b {@code CertPbthBuilder}, cbll
 * one of the stbtic {@code getInstbnce} methods, pbssing in the
 * blgorithm nbme of the {@code CertPbthBuilder} desired bnd optionblly
 * the nbme of the provider desired.
 *
 * <p>Once b {@code CertPbthBuilder} object hbs been crebted, certificbtion
 * pbths cbn be constructed by cblling the {@link #build build} method bnd
 * pbssing it bn blgorithm-specific set of pbrbmeters. If successful, the
 * result (including the {@code CertPbth} thbt wbs built) is returned
 * in bn object thbt implements the {@code CertPbthBuilderResult}
 * interfbce.
 *
 * <p>The {@link #getRevocbtionChecker} method bllows bn bpplicbtion to specify
 * bdditionbl blgorithm-specific pbrbmeters bnd options used by the
 * {@code CertPbthBuilder} when checking the revocbtion stbtus of certificbtes.
 * Here is bn exbmple demonstrbting how it is used with the PKIX blgorithm:
 *
 * <pre>
 * CertPbthBuilder cpb = CertPbthBuilder.getInstbnce("PKIX");
 * PKIXRevocbtionChecker rc = (PKIXRevocbtionChecker)cpb.getRevocbtionChecker();
 * rc.setOptions(EnumSet.of(Option.PREFER_CRLS));
 * pbrbms.bddCertPbthChecker(rc);
 * CertPbthBuilderResult cpbr = cpb.build(pbrbms);
 * </pre>
 *
 * <p>Every implementbtion of the Jbvb plbtform is required to support the
 * following stbndbrd {@code CertPbthBuilder} blgorithm:
 * <ul>
 * <li>{@code PKIX}</li>
 * </ul>
 * This blgorithm is described in the <b href=
 * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#CertPbthBuilder">
 * CertPbthBuilder section</b> of the
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion.
 * Consult the relebse documentbtion for your implementbtion to see if bny
 * other blgorithms bre supported.
 *
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * The stbtic methods of this clbss bre gubrbnteed to be threbd-sbfe.
 * Multiple threbds mby concurrently invoke the stbtic methods defined in
 * this clbss with no ill effects.
 * <p>
 * However, this is not true for the non-stbtic methods defined by this clbss.
 * Unless otherwise documented by b specific provider, threbds thbt need to
 * bccess b single {@code CertPbthBuilder} instbnce concurrently should
 * synchronize bmongst themselves bnd provide the necessbry locking. Multiple
 * threbds ebch mbnipulbting b different {@code CertPbthBuilder} instbnce
 * need not synchronize.
 *
 * @see CertPbth
 *
 * @since       1.4
 * @buthor      Sebn Mullbn
 * @buthor      Ybssir Elley
 */
public clbss CertPbthBuilder {

    /*
     * Constbnt to lookup in the Security properties file to determine
     * the defbult certpbthbuilder type. In the Security properties file,
     * the defbult certpbthbuilder type is given bs:
     * <pre>
     * certpbthbuilder.type=PKIX
     * </pre>
     */
    privbte stbtic finbl String CPB_TYPE = "certpbthbuilder.type";
    privbte finbl CertPbthBuilderSpi builderSpi;
    privbte finbl Provider provider;
    privbte finbl String blgorithm;

    /**
     * Crebtes b {@code CertPbthBuilder} object of the given blgorithm,
     * bnd encbpsulbtes the given provider implementbtion (SPI object) in it.
     *
     * @pbrbm builderSpi the provider implementbtion
     * @pbrbm provider the provider
     * @pbrbm blgorithm the blgorithm nbme
     */
    protected CertPbthBuilder(CertPbthBuilderSpi builderSpi, Provider provider,
        String blgorithm)
    {
        this.builderSpi = builderSpi;
        this.provider = provider;
        this.blgorithm = blgorithm;
    }

    /**
     * Returns b {@code CertPbthBuilder} object thbt implements the
     * specified blgorithm.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new CertPbthBuilder object encbpsulbting the
     * CertPbthBuilderSpi implementbtion from the first
     * Provider thbt supports the specified blgorithm is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the nbme of the requested {@code CertPbthBuilder}
     *  blgorithm.  See the CertPbthBuilder section in the <b href=
     *  "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#CertPbthBuilder">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return b {@code CertPbthBuilder} object thbt implements the
     *          specified blgorithm.
     *
     * @throws NoSuchAlgorithmException if no Provider supports b
     *          CertPbthBuilderSpi implementbtion for the
     *          specified blgorithm.
     *
     * @see jbvb.security.Provider
     */
    public stbtic CertPbthBuilder getInstbnce(String blgorithm)
            throws NoSuchAlgorithmException {
        Instbnce instbnce = GetInstbnce.getInstbnce("CertPbthBuilder",
            CertPbthBuilderSpi.clbss, blgorithm);
        return new CertPbthBuilder((CertPbthBuilderSpi)instbnce.impl,
            instbnce.provider, blgorithm);
    }

    /**
     * Returns b {@code CertPbthBuilder} object thbt implements the
     * specified blgorithm.
     *
     * <p> A new CertPbthBuilder object encbpsulbting the
     * CertPbthBuilderSpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the nbme of the requested {@code CertPbthBuilder}
     *  blgorithm.  See the CertPbthBuilder section in the <b href=
     *  "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#CertPbthBuilder">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return b {@code CertPbthBuilder} object thbt implements the
     *          specified blgorithm.
     *
     * @throws NoSuchAlgorithmException if b CertPbthBuilderSpi
     *          implementbtion for the specified blgorithm is not
     *          bvbilbble from the specified provider.
     *
     * @throws NoSuchProviderException if the specified provider is not
     *          registered in the security provider list.
     *
     * @exception IllegblArgumentException if the {@code provider} is
     *          null or empty.
     *
     * @see jbvb.security.Provider
     */
    public stbtic CertPbthBuilder getInstbnce(String blgorithm, String provider)
           throws NoSuchAlgorithmException, NoSuchProviderException {
        Instbnce instbnce = GetInstbnce.getInstbnce("CertPbthBuilder",
            CertPbthBuilderSpi.clbss, blgorithm, provider);
        return new CertPbthBuilder((CertPbthBuilderSpi)instbnce.impl,
            instbnce.provider, blgorithm);
    }

    /**
     * Returns b {@code CertPbthBuilder} object thbt implements the
     * specified blgorithm.
     *
     * <p> A new CertPbthBuilder object encbpsulbting the
     * CertPbthBuilderSpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm blgorithm the nbme of the requested {@code CertPbthBuilder}
     *  blgorithm.  See the CertPbthBuilder section in the <b href=
     *  "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#CertPbthBuilder">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the provider.
     *
     * @return b {@code CertPbthBuilder} object thbt implements the
     *          specified blgorithm.
     *
     * @exception NoSuchAlgorithmException if b CertPbthBuilderSpi
     *          implementbtion for the specified blgorithm is not bvbilbble
     *          from the specified Provider object.
     *
     * @exception IllegblArgumentException if the {@code provider} is
     *          null.
     *
     * @see jbvb.security.Provider
     */
    public stbtic CertPbthBuilder getInstbnce(String blgorithm,
            Provider provider) throws NoSuchAlgorithmException {
        Instbnce instbnce = GetInstbnce.getInstbnce("CertPbthBuilder",
            CertPbthBuilderSpi.clbss, blgorithm, provider);
        return new CertPbthBuilder((CertPbthBuilderSpi)instbnce.impl,
            instbnce.provider, blgorithm);
    }

    /**
     * Returns the provider of this {@code CertPbthBuilder}.
     *
     * @return the provider of this {@code CertPbthBuilder}
     */
    public finbl Provider getProvider() {
        return this.provider;
    }

    /**
     * Returns the nbme of the blgorithm of this {@code CertPbthBuilder}.
     *
     * @return the nbme of the blgorithm of this {@code CertPbthBuilder}
     */
    public finbl String getAlgorithm() {
        return this.blgorithm;
    }

    /**
     * Attempts to build b certificbtion pbth using the specified blgorithm
     * pbrbmeter set.
     *
     * @pbrbm pbrbms the blgorithm pbrbmeters
     * @return the result of the build blgorithm
     * @throws CertPbthBuilderException if the builder is unbble to construct
     *  b certificbtion pbth thbt sbtisfies the specified pbrbmeters
     * @throws InvblidAlgorithmPbrbmeterException if the specified pbrbmeters
     * bre inbppropribte for this {@code CertPbthBuilder}
     */
    public finbl CertPbthBuilderResult build(CertPbthPbrbmeters pbrbms)
        throws CertPbthBuilderException, InvblidAlgorithmPbrbmeterException
    {
        return builderSpi.engineBuild(pbrbms);
    }

    /**
     * Returns the defbult {@code CertPbthBuilder} type bs specified by
     * the {@code certpbthbuilder.type} security property, or the string
     * {@literbl "PKIX"} if no such property exists.
     *
     * <p>The defbult {@code CertPbthBuilder} type cbn be used by
     * bpplicbtions thbt do not wbnt to use b hbrd-coded type when cblling one
     * of the {@code getInstbnce} methods, bnd wbnt to provide b defbult
     * type in cbse b user does not specify its own.
     *
     * <p>The defbult {@code CertPbthBuilder} type cbn be chbnged by
     * setting the vblue of the {@code certpbthbuilder.type} security property
     * to the desired type.
     *
     * @see jbvb.security.Security security properties
     * @return the defbult {@code CertPbthBuilder} type bs specified
     * by the {@code certpbthbuilder.type} security property, or the string
     * {@literbl "PKIX"} if no such property exists.
     */
    public finbl stbtic String getDefbultType() {
        String cpbtype =
            AccessController.doPrivileged(new PrivilegedAction<String>() {
                public String run() {
                    return Security.getProperty(CPB_TYPE);
                }
            });
        return (cpbtype == null) ? "PKIX" : cpbtype;
    }

    /**
     * Returns b {@code CertPbthChecker} thbt the encbpsulbted
     * {@code CertPbthBuilderSpi} implementbtion uses to check the revocbtion
     * stbtus of certificbtes. A PKIX implementbtion returns objects of
     * type {@code PKIXRevocbtionChecker}. Ebch invocbtion of this method
     * returns b new instbnce of {@code CertPbthChecker}.
     *
     * <p>The primbry purpose of this method is to bllow cbllers to specify
     * bdditionbl input pbrbmeters bnd options specific to revocbtion checking.
     * See the clbss description for bn exbmple.
     *
     * @return b {@code CertPbthChecker}
     * @throws UnsupportedOperbtionException if the service provider does not
     *         support this method
     * @since 1.8
     */
    public finbl CertPbthChecker getRevocbtionChecker() {
        return builderSpi.engineGetRevocbtionChecker();
    }
}
