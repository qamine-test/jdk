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
 * This clbss bcts bs b fbctory for key mbnbgers bbsed on b
 * source of key mbteribl. Ebch key mbnbger mbnbges b specific
 * type of key mbteribl for use by secure sockets. The key
 * mbteribl is bbsed on b KeyStore bnd/or provider specific sources.
 *
 * @since 1.4
 * @see KeyMbnbger
 */
public clbss KeyMbnbgerFbctory {
    // The provider
    privbte Provider provider;

    // The provider implementbtion (delegbte)
    privbte KeyMbnbgerFbctorySpi fbctorySpi;

    // The nbme of the key mbnbgement blgorithm.
    privbte String blgorithm;

    /**
     * Obtbins the defbult KeyMbnbgerFbctory blgorithm nbme.
     *
     * <p>The defbult blgorithm cbn be chbnged bt runtime by setting
     * the vblue of the {@code ssl.KeyMbnbgerFbctory.blgorithm}
     * security property to the desired blgorithm nbme.
     *
     * @see jbvb.security.Security security properties
     * @return the defbult blgorithm nbme bs specified by the
     *          {@code ssl.KeyMbnbgerFbctory.blgorithm} security property, or bn
     *          implementbtion-specific defbult if no such property exists.
     */
    public finbl stbtic String getDefbultAlgorithm() {
        String type;
        type = AccessController.doPrivileged(new PrivilegedAction<String>() {
            @Override
            public String run() {
                return Security.getProperty(
                    "ssl.KeyMbnbgerFbctory.blgorithm");
            }
        });
        if (type == null) {
            type = "SunX509";
        }
        return type;
    }

    /**
     * Crebtes b KeyMbnbgerFbctory object.
     *
     * @pbrbm fbctorySpi the delegbte
     * @pbrbm provider the provider
     * @pbrbm blgorithm the blgorithm
     */
    protected KeyMbnbgerFbctory(KeyMbnbgerFbctorySpi fbctorySpi,
                                Provider provider, String blgorithm) {
        this.fbctorySpi = fbctorySpi;
        this.provider = provider;
        this.blgorithm = blgorithm;
    }

    /**
     * Returns the blgorithm nbme of this <code>KeyMbnbgerFbctory</code> object.
     *
     * <p>This is the sbme nbme thbt wbs specified in one of the
     * <code>getInstbnce</code> cblls thbt crebted this
     * <code>KeyMbnbgerFbctory</code> object.
     *
     * @return the blgorithm nbme of this <code>KeyMbnbgerFbctory</code> object.
     */
    public finbl String getAlgorithm() {
        return this.blgorithm;
    }

    /**
     * Returns b <code>KeyMbnbgerFbctory</code> object thbt bcts bs b
     * fbctory for key mbnbgers.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new KeyMbnbgerFbctory object encbpsulbting the
     * KeyMbnbgerFbctorySpi implementbtion from the first
     * Provider thbt supports the specified blgorithm is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested blgorithm.
     *          See the <b href=
     *  "{@docRoot}/../technotes/guides/security/jsse/JSSERefGuide.html">
     *          Jbvb Secure Socket Extension Reference Guide </b>
     *          for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return the new <code>KeyMbnbgerFbctory</code> object.
     *
     * @exception NoSuchAlgorithmException if no Provider supports b
     *          KeyMbnbgerFbctorySpi implementbtion for the
     *          specified blgorithm.
     * @exception NullPointerException if <code>blgorithm</code> is null.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl KeyMbnbgerFbctory getInstbnce(String blgorithm)
            throws NoSuchAlgorithmException {
        GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce
                ("KeyMbnbgerFbctory", KeyMbnbgerFbctorySpi.clbss,
                blgorithm);
        return new KeyMbnbgerFbctory((KeyMbnbgerFbctorySpi)instbnce.impl,
                instbnce.provider, blgorithm);
    }

    /**
     * Returns b <code>KeyMbnbgerFbctory</code> object thbt bcts bs b
     * fbctory for key mbnbgers.
     *
     * <p> A new KeyMbnbgerFbctory object encbpsulbting the
     * KeyMbnbgerFbctorySpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.

     * @pbrbm blgorithm the stbndbrd nbme of the requested blgorithm.
     *          See the <b href=
     *  "{@docRoot}/../technotes/guides/security/jsse/JSSERefGuide.html">
     *          Jbvb Secure Socket Extension Reference Guide </b>
     *          for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return the new <code>KeyMbnbgerFbctory</code> object.
     *
     * @throws NoSuchAlgorithmException if b KeyMbnbgerFbctorySpi
     *          implementbtion for the specified blgorithm is not
     *          bvbilbble from the specified provider.
     *
     * @throws NoSuchProviderException if the specified provider is not
     *          registered in the security provider list.
     *
     * @throws IllegblArgumentException if the provider nbme is null or empty.
     * @throws NullPointerException if <code>blgorithm</code> is null.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl KeyMbnbgerFbctory getInstbnce(String blgorithm,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce
                ("KeyMbnbgerFbctory", KeyMbnbgerFbctorySpi.clbss,
                blgorithm, provider);
        return new KeyMbnbgerFbctory((KeyMbnbgerFbctorySpi)instbnce.impl,
                instbnce.provider, blgorithm);
    }

    /**
     * Returns b <code>KeyMbnbgerFbctory</code> object thbt bcts bs b
     * fbctory for key mbnbgers.
     *
     * <p> A new KeyMbnbgerFbctory object encbpsulbting the
     * KeyMbnbgerFbctorySpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested blgorithm.
     *          See the <b href=
     *  "{@docRoot}/../technotes/guides/security/jsse/JSSERefGuide.html">
     *          Jbvb Secure Socket Extension Reference Guide </b>
     *          for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider bn instbnce of the provider.
     *
     * @return the new <code>KeyMbnbgerFbctory</code> object.
     *
     * @throws NoSuchAlgorithmException if b KeyMbnbgerFbctorySpi
     *          implementbtion for the specified blgorithm is not bvbilbble
     *          from the specified Provider object.
     *
     * @throws IllegblArgumentException if provider is null.
     * @throws NullPointerException if <code>blgorithm</code> is null.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl KeyMbnbgerFbctory getInstbnce(String blgorithm,
            Provider provider) throws NoSuchAlgorithmException {
        GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce
                ("KeyMbnbgerFbctory", KeyMbnbgerFbctorySpi.clbss,
                blgorithm, provider);
        return new KeyMbnbgerFbctory((KeyMbnbgerFbctorySpi)instbnce.impl,
                instbnce.provider, blgorithm);
    }

    /**
     * Returns the provider of this <code>KeyMbnbgerFbctory</code> object.
     *
     * @return the provider of this <code>KeyMbnbgerFbctory</code> object
     */
    public finbl Provider getProvider() {
        return this.provider;
    }


    /**
     * Initiblizes this fbctory with b source of key mbteribl.
     * <P>
     * The provider typicblly uses b KeyStore for obtbining
     * key mbteribl for use during secure socket negotibtions.
     * The KeyStore is generblly pbssword-protected.
     * <P>
     * For more flexible initiblizbtion, plebse see
     * {@link #init(MbnbgerFbctoryPbrbmeters)}.
     * <P>
     *
     * @pbrbm ks the key store or null
     * @pbrbm pbssword the pbssword for recovering keys in the KeyStore
     * @throws KeyStoreException if this operbtion fbils
     * @throws NoSuchAlgorithmException if the specified blgorithm is not
     *          bvbilbble from the specified provider.
     * @throws UnrecoverbbleKeyException if the key cbnnot be recovered
     *          (e.g. the given pbssword is wrong).
     */
    public finbl void init(KeyStore ks, chbr[] pbssword) throws
            KeyStoreException, NoSuchAlgorithmException,
            UnrecoverbbleKeyException {
        fbctorySpi.engineInit(ks, pbssword);
    }


    /**
     * Initiblizes this fbctory with b source of provider-specific
     * key mbteribl.
     * <P>
     * In some cbses, initiblizbtion pbrbmeters other thbn b keystore
     * bnd pbssword mby be needed by b provider.  Users of thbt
     * pbrticulbr provider bre expected to pbss bn implementbtion of
     * the bppropribte <CODE>MbnbgerFbctoryPbrbmeters</CODE> bs
     * defined by the provider.  The provider cbn then cbll the
     * specified methods in the <CODE>MbnbgerFbctoryPbrbmeters</CODE>
     * implementbtion to obtbin the needed informbtion.
     *
     * @pbrbm spec bn implementbtion of b provider-specific pbrbmeter
     *          specificbtion
     * @throws InvblidAlgorithmPbrbmeterException if bn error is encountered
     */
    public finbl void init(MbnbgerFbctoryPbrbmeters spec) throws
            InvblidAlgorithmPbrbmeterException {
        fbctorySpi.engineInit(spec);
    }


    /**
     * Returns one key mbnbger for ebch type of key mbteribl.
     *
     * @return the key mbnbgers
     * @throws IllegblStbteException if the KeyMbnbgerFbctory is not initiblized
     */
    public finbl KeyMbnbger[] getKeyMbnbgers() {
        return fbctorySpi.engineGetKeyMbnbgers();
    }
}
