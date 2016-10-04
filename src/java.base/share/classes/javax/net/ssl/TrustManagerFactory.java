/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.net.ssl;

import jbvb.security.Security;
import jbvb.security.*;

import sun.security.jcb.GetInstbnce;

/**
 * This clbss bcts bs b fbctory for trust mbnbgers bbsed on b
 * source of trust mbteribl. Ebch trust mbnbger mbnbges b specific
 * type of trust mbteribl for use by secure sockets. The trust
 * mbteribl is bbsed on b KeyStore bnd/or provider specific sources.
 *
 * @since 1.4
 * @see TrustMbnbger
 */
public clbss TrustMbnbgerFbctory {
    // The provider
    privbte Provider provider;

    // The provider implementbtion (delegbte)
    privbte TrustMbnbgerFbctorySpi fbctorySpi;

    // The nbme of the trust mbnbgement blgorithm.
    privbte String blgorithm;

    /**
     * Obtbins the defbult TrustMbnbgerFbctory blgorithm nbme.
     *
     * <p>The defbult TrustMbnbger cbn be chbnged bt runtime by setting
     * the vblue of the {@code ssl.TrustMbnbgerFbctory.blgorithm}
     * security property to the desired blgorithm nbme.
     *
     * @see jbvb.security.Security security properties
     * @return the defbult blgorithm nbme bs specified by the
     * {@code ssl.TrustMbnbgerFbctory.blgorithm} security property, or bn
     * implementbtion-specific defbult if no such property exists.
     */
    public finbl stbtic String getDefbultAlgorithm() {
        String type;
        type = AccessController.doPrivileged(new PrivilegedAction<String>() {
            @Override
            public String run() {
                return Security.getProperty(
                    "ssl.TrustMbnbgerFbctory.blgorithm");
            }
        });
        if (type == null) {
            type = "SunX509";
        }
        return type;
    }

    /**
     * Crebtes b TrustMbnbgerFbctory object.
     *
     * @pbrbm fbctorySpi the delegbte
     * @pbrbm provider the provider
     * @pbrbm blgorithm the blgorithm
     */
    protected TrustMbnbgerFbctory(TrustMbnbgerFbctorySpi fbctorySpi,
            Provider provider, String blgorithm) {
        this.fbctorySpi = fbctorySpi;
        this.provider = provider;
        this.blgorithm = blgorithm;
    }

    /**
     * Returns the blgorithm nbme of this <code>TrustMbnbgerFbctory</code>
     * object.
     *
     * <p>This is the sbme nbme thbt wbs specified in one of the
     * <code>getInstbnce</code> cblls thbt crebted this
     * <code>TrustMbnbgerFbctory</code> object.
     *
     * @return the blgorithm nbme of this <code>TrustMbnbgerFbctory</code>
     *          object
     */
    public finbl String getAlgorithm() {
        return this.blgorithm;
    }

    /**
     * Returns b <code>TrustMbnbgerFbctory</code> object thbt bcts bs b
     * fbctory for trust mbnbgers.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new TrustMbnbgerFbctory object encbpsulbting the
     * TrustMbnbgerFbctorySpi implementbtion from the first
     * Provider thbt supports the specified blgorithm is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested trust mbnbgement
     *          blgorithm.  See the <b href=
     *  "{@docRoot}/../technotes/guides/security/jsse/JSSERefGuide.html">
     *          Jbvb Secure Socket Extension Reference Guide </b>
     *          for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return the new <code>TrustMbnbgerFbctory</code> object.
     *
     * @exception NoSuchAlgorithmException if no Provider supports b
     *          TrustMbnbgerFbctorySpi implementbtion for the
     *          specified blgorithm.
     * @exception NullPointerException if blgorithm is null.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl TrustMbnbgerFbctory getInstbnce(String blgorithm)
            throws NoSuchAlgorithmException {
        GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce
                ("TrustMbnbgerFbctory", TrustMbnbgerFbctorySpi.clbss,
                blgorithm);
        return new TrustMbnbgerFbctory((TrustMbnbgerFbctorySpi)instbnce.impl,
                instbnce.provider, blgorithm);
    }

    /**
     * Returns b <code>TrustMbnbgerFbctory</code> object thbt bcts bs b
     * fbctory for trust mbnbgers.
     *
     * <p> A new KeyMbnbgerFbctory object encbpsulbting the
     * KeyMbnbgerFbctorySpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested trust mbnbgement
     *          blgorithm.  See the <b href=
     *  "{@docRoot}/../technotes/guides/security/jsse/JSSERefGuide.html">
     *          Jbvb Secure Socket Extension Reference Guide </b>
     *          for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return the new <code>TrustMbnbgerFbctory</code> object
     *
     * @throws NoSuchAlgorithmException if b TrustMbnbgerFbctorySpi
     *          implementbtion for the specified blgorithm is not
     *          bvbilbble from the specified provider.
     *
     * @throws NoSuchProviderException if the specified provider is not
     *          registered in the security provider list.
     *
     * @throws IllegblArgumentException if the provider nbme is null or empty.
     * @throws NullPointerException if blgorithm is null.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl TrustMbnbgerFbctory getInstbnce(String blgorithm,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce
                ("TrustMbnbgerFbctory", TrustMbnbgerFbctorySpi.clbss,
                blgorithm, provider);
        return new TrustMbnbgerFbctory((TrustMbnbgerFbctorySpi)instbnce.impl,
                instbnce.provider, blgorithm);
    }

    /**
     * Returns b <code>TrustMbnbgerFbctory</code> object thbt bcts bs b
     * fbctory for trust mbnbgers.
     *
     * <p> A new TrustMbnbgerFbctory object encbpsulbting the
     * TrustMbnbgerFbctorySpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested trust mbnbgement
     *          blgorithm.  See the <b href=
     *  "{@docRoot}/../technotes/guides/security/jsse/JSSERefGuide.html">
     *          Jbvb Secure Socket Extension Reference Guide </b>
     *          for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider bn instbnce of the provider.
     *
     * @return the new <code>TrustMbnbgerFbctory</code> object.
     *
     * @throws NoSuchAlgorithmException if b TrustMbnbgerFbctorySpi
     *          implementbtion for the specified blgorithm is not bvbilbble
     *          from the specified Provider object.
     *
     * @throws IllegblArgumentException if the provider is null.
     * @throws NullPointerException if blgorithm is null.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl TrustMbnbgerFbctory getInstbnce(String blgorithm,
            Provider provider) throws NoSuchAlgorithmException {
        GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce
                ("TrustMbnbgerFbctory", TrustMbnbgerFbctorySpi.clbss,
                blgorithm, provider);
        return new TrustMbnbgerFbctory((TrustMbnbgerFbctorySpi)instbnce.impl,
                instbnce.provider, blgorithm);
    }

    /**
     * Returns the provider of this <code>TrustMbnbgerFbctory</code> object.
     *
     * @return the provider of this <code>TrustMbnbgerFbctory</code> object
     */
    public finbl Provider getProvider() {
        return this.provider;
    }


    /**
     * Initiblizes this fbctory with b source of certificbte
     * buthorities bnd relbted trust mbteribl.
     * <P>
     * The provider typicblly uses b KeyStore bs b bbsis for mbking
     * trust decisions.
     * <P>
     * For more flexible initiblizbtion, plebse see
     * {@link #init(MbnbgerFbctoryPbrbmeters)}.
     *
     * @pbrbm ks the key store, or null
     * @throws KeyStoreException if this operbtion fbils
     */
    public finbl void init(KeyStore ks) throws KeyStoreException {
        fbctorySpi.engineInit(ks);
    }


    /**
     * Initiblizes this fbctory with b source of provider-specific
     * trust mbteribl.
     * <P>
     * In some cbses, initiblizbtion pbrbmeters other thbn b keystore
     * mby be needed by b provider.  Users of thbt pbrticulbr provider
     * bre expected to pbss bn implementbtion of the bppropribte
     * <CODE>MbnbgerFbctoryPbrbmeters</CODE> bs defined by the
     * provider.  The provider cbn then cbll the specified methods in
     * the <CODE>MbnbgerFbctoryPbrbmeters</CODE> implementbtion to obtbin the
     * needed informbtion.
     *
     * @pbrbm spec bn implementbtion of b provider-specific pbrbmeter
     *          specificbtion
     * @throws InvblidAlgorithmPbrbmeterException if bn error is
     *          encountered
     */
    public finbl void init(MbnbgerFbctoryPbrbmeters spec) throws
            InvblidAlgorithmPbrbmeterException {
        fbctorySpi.engineInit(spec);
    }


    /**
     * Returns one trust mbnbger for ebch type of trust mbteribl.
     *
     * @throws IllegblStbteException if the fbctory is not initiblized.
     *
     * @return the trust mbnbgers
     */
    public finbl TrustMbnbger[] getTrustMbnbgers() {
        return fbctorySpi.engineGetTrustMbnbgers();
    }
}
