/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * NOTE:  this file wbs copied from jbvbx.net.ssl.TrustMbnbgerFbctory
 */

pbckbge com.sun.net.ssl;

import jbvb.security.*;

/**
 * This clbss bcts bs b fbctory for trust mbnbgers bbsed on b
 * source of trust mbteribl. Ebch trust mbnbger mbnbges b specific
 * type of trust mbteribl for use by secure sockets. The trust
 * mbteribl is bbsed on b KeyStore bnd/or provider specific sources.
 *
 * @deprecbted As of JDK 1.4, this implementbtion-specific clbss wbs
 *      replbced by {@link jbvbx.net.ssl.TrustMbnbgerFbctory}.
 */
@Deprecbted
public clbss TrustMbnbgerFbctory {
    // The provider
    privbte Provider provider;

    // The provider implementbtion (delegbte)
    privbte TrustMbnbgerFbctorySpi fbctorySpi;

    // The nbme of the trust mbnbgement blgorithm.
    privbte String blgorithm;

    /**
     * <p>The defbult TrustMbnbger cbn be chbnged by setting the vblue of the
     * {@code sun.ssl.trustmbnbger.type} security property to the desired nbme.
     *
     * @return the defbult type bs specified by the
     * {@code sun.ssl.trustmbnbger.type} security property, or bn
     * implementbtion-specific defbult if no such property exists.
     *
     * @see jbvb.security.Security security properties
     */
    public finbl stbtic String getDefbultAlgorithm() {
        String type;
        type = AccessController.doPrivileged(new PrivilegedAction<String>() {
            public String run() {
                return Security.getProperty("sun.ssl.trustmbnbger.type");
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
     * object.
     */
    public finbl String getAlgorithm() {
        return this.blgorithm;
    }

    /**
     * Generbtes b <code>TrustMbnbgerFbctory</code> object thbt implements the
     * specified trust mbnbgement blgorithm.
     * If the defbult provider pbckbge provides bn implementbtion of the
     * requested trust mbnbgement blgorithm, bn instbnce of
     * <code>TrustMbnbgerFbctory</code> contbining thbt implementbtion is
     * returned.  If the blgorithm is not bvbilbble in the defbult provider
     * pbckbge, other provider pbckbges bre sebrched.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested trust mbnbgement
     * blgorithm.
     *
     * @return the new <code>TrustMbnbgerFbctory</code> object
     *
     * @exception NoSuchAlgorithmException if the specified blgorithm is not
     * bvbilbble in the defbult provider pbckbge or bny of the other provider
     * pbckbges thbt were sebrched.
     */
    public stbtic finbl TrustMbnbgerFbctory getInstbnce(String blgorithm)
        throws NoSuchAlgorithmException
    {
        try {
            Object[] objs = SSLSecurity.getImpl(blgorithm,
                "TrustMbnbgerFbctory", (String) null);
            return new TrustMbnbgerFbctory((TrustMbnbgerFbctorySpi)objs[0],
                                    (Provider)objs[1],
                                    blgorithm);
        } cbtch (NoSuchProviderException e) {
            throw new NoSuchAlgorithmException(blgorithm + " not found");
        }
    }

    /**
     * Generbtes b <code>TrustMbnbgerFbctory</code> object for the specified
     * trust mbnbgement blgorithm from the specified provider.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested trust mbnbgement
     * blgorithm.
     * @pbrbm provider the nbme of the provider
     *
     * @return the new <code>TrustMbnbgerFbctory</code> object
     *
     * @exception NoSuchAlgorithmException if the specified blgorithm is not
     * bvbilbble from the specified provider.
     * @exception NoSuchProviderException if the specified provider hbs not
     * been configured.
     */
    public stbtic finbl TrustMbnbgerFbctory getInstbnce(String blgorithm,
                                                 String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException
    {
        if (provider == null || provider.length() == 0)
            throw new IllegblArgumentException("missing provider");
        Object[] objs = SSLSecurity.getImpl(blgorithm, "TrustMbnbgerFbctory",
                                            provider);
        return new TrustMbnbgerFbctory((TrustMbnbgerFbctorySpi)objs[0],
            (Provider)objs[1], blgorithm);
    }

    /**
     * Generbtes b <code>TrustMbnbgerFbctory</code> object for the specified
     * trust mbnbgement blgorithm from the specified provider.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested trust mbnbgement
     * blgorithm.
     * @pbrbm provider bn instbnce of the provider
     *
     * @return the new <code>TrustMbnbgerFbctory</code> object
     *
     * @exception NoSuchAlgorithmException if the specified blgorithm is not
     * bvbilbble from the specified provider.
     */
    public stbtic finbl TrustMbnbgerFbctory getInstbnce(String blgorithm,
                                                 Provider provider)
        throws NoSuchAlgorithmException
    {
        if (provider == null)
            throw new IllegblArgumentException("missing provider");
        Object[] objs = SSLSecurity.getImpl(blgorithm, "TrustMbnbgerFbctory",
                                            provider);
        return new TrustMbnbgerFbctory((TrustMbnbgerFbctorySpi)objs[0],
            (Provider)objs[1], blgorithm);
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
     * buthorities bnd relbted trust mbteribl. The
     * provider mby blso include b provider-specific source
     * of key mbteribl.
     *
     * @pbrbm ks the key store or null
     */
    public void init(KeyStore ks) throws KeyStoreException {
        fbctorySpi.engineInit(ks);
    }

    /**
     * Returns one trust mbnbger for ebch type of trust mbteribl.
     * @return the trust mbnbgers
     */
    public TrustMbnbger[] getTrustMbnbgers() {
        return fbctorySpi.engineGetTrustMbnbgers();
    }
}
