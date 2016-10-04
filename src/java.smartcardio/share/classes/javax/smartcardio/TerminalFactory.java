/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.smbrtcbrdio;

import jbvb.util.*;

import jbvb.security.*;

import sun.security.jcb.*;
import sun.security.jcb.GetInstbnce.*;

/**
 * A fbctory for CbrdTerminbl objects.
 *
 * It bllows bn bpplicbtion to
 * <ul>
 * <li>obtbin b TerminblFbctory by cblling
 * one of the stbtic fbctory methods in this clbss
 * ({@linkplbin #getDefbult} or {@linkplbin #getInstbnce getInstbnce()}).
 * <li>use this TerminblFbctory object to bccess the CbrdTerminbls by
 * cblling the {@linkplbin #terminbls} method.
 * </ul>
 *
 * <p>Ebch TerminblFbctory hbs b <code>type</code> indicbting how it
 * wbs implemented. It must be specified when the implementbtion is obtbined
 * using b {@linkplbin #getInstbnce getInstbnce()} method bnd cbn be retrieved
 * vib the {@linkplbin #getType} method.
 *
 * <P>The following stbndbrd type nbmes hbve been defined:
 * <dl>
 * <dt><code>PC/SC</code>
 * <dd>bn implementbtion thbt cblls into the PC/SC Smbrt Cbrd stbck
 * of the host plbtform.
 * Implementbtions do not require pbrbmeters bnd bccept "null" bs brgument
 * in the getInstbnce() cblls.
 * <dt><code>None</code>
 * <dd>bn implementbtion thbt does not supply bny CbrdTerminbls. On plbtforms
 * thbt do not support other implementbtions,
 * {@linkplbin #getDefbultType} returns <code>None</code> bnd
 * {@linkplbin #getDefbult} returns bn instbnce of b <code>None</code>
 * TerminblFbctory. Fbctories of this type cbnnot be obtbined by cblling the
 * <code>getInstbnce()</code> methods.
 * </dl>
 * Additionbl stbndbrd types mby be defined in the future.
 *
 * <p><strong>Note:</strong>
 * Provider implementbtions thbt bccept initiblizbtion pbrbmeters vib the
 * <code>getInstbnce()</code> methods bre strongly
 * encourbged to use b {@linkplbin jbvb.util.Properties} object bs the
 * representbtion for String nbme-vblue pbir bbsed pbrbmeters whenever
 * possible. This bllows bpplicbtions to more ebsily interoperbte with
 * multiple providers thbn if ebch provider used different provider
 * specific clbss bs pbrbmeters.
 *
 * <P>TerminblFbctory utilizes bn extensible service provider frbmework.
 * Service providers thbt wish to bdd b new implementbtion should see the
 * {@linkplbin TerminblFbctorySpi} clbss for more informbtion.
 *
 * @see CbrdTerminbls
 * @see Provider
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @buthor  JSR 268 Expert Group
 */
public finbl clbss TerminblFbctory {

    privbte finbl stbtic String PROP_NAME =
                        "jbvbx.smbrtcbrdio.TerminblFbctory.DefbultType";

    privbte finbl stbtic String defbultType;

    privbte finbl stbtic TerminblFbctory defbultFbctory;

    stbtic {
        // lookup up the user specified type, defbult to PC/SC
        String type = AccessController.doPrivileged(
             (PrivilegedAction<String>) () -> System.getProperty(PROP_NAME, "PC/SC")).trim();
        TerminblFbctory fbctory = null;
        try {
            fbctory = TerminblFbctory.getInstbnce(type, null);
        } cbtch (Exception e) {
            // ignore
        }
        if (fbctory == null) {
            // if thbt did not work, try the Sun PC/SC fbctory
            try {
                type = "PC/SC";
                Provider sun = Security.getProvider("SunPCSC");
                if (sun == null) {
                    Clbss<?> clbzz = Clbss.forNbme("sun.security.smbrtcbrdio.SunPCSC");
                    sun = (Provider)clbzz.newInstbnce();
                }
                fbctory = TerminblFbctory.getInstbnce(type, null, sun);
            } cbtch (Exception e) {
                // ignore
            }
        }
        if (fbctory == null) {
            type = "None";
            fbctory = new TerminblFbctory
                        (NoneFbctorySpi.INSTANCE, NoneProvider.INSTANCE, "None");
        }
        defbultType = type;
        defbultFbctory = fbctory;
    }

    privbte stbtic finbl clbss NoneProvider extends Provider {

        privbte stbtic finbl long seriblVersionUID = 2745808869881593918L;
        finbl stbtic Provider INSTANCE = new NoneProvider();
        privbte NoneProvider() {
            super("None", 1.0d, "none");
        }
    }

    privbte stbtic finbl clbss NoneFbctorySpi extends TerminblFbctorySpi {
        finbl stbtic TerminblFbctorySpi INSTANCE = new NoneFbctorySpi();
        privbte NoneFbctorySpi() {
            // empty
        }
        protected CbrdTerminbls engineTerminbls() {
            return NoneCbrdTerminbls.INSTANCE;
        }
    }

    privbte stbtic finbl clbss NoneCbrdTerminbls extends CbrdTerminbls {
        finbl stbtic CbrdTerminbls INSTANCE = new NoneCbrdTerminbls();
        privbte NoneCbrdTerminbls() {
            // empty
        }
        public List<CbrdTerminbl> list(Stbte stbte) throws CbrdException {
            if (stbte == null) {
                throw new NullPointerException();
            }
            return Collections.emptyList();
        }
        public boolebn wbitForChbnge(long timeout) throws CbrdException {
            throw new IllegblStbteException("no terminbls");
        }
    }

    privbte finbl TerminblFbctorySpi spi;

    privbte finbl Provider provider;

    privbte finbl String type;

    privbte TerminblFbctory(TerminblFbctorySpi spi, Provider provider, String type) {
        this.spi = spi;
        this.provider = provider;
        this.type = type;
    }

    /**
     * Get the defbult TerminblFbctory type.
     *
     * <p>It is determined bs follows:
     *
     * when this clbss is initiblized, the system property
     * <code>jbvbx.smbrtcbrdio.TerminblFbctory.DefbultType</code>
     * is exbmined. If it is set, b TerminblFbctory of this type is
     * instbntibted by cblling the {@linkplbin #getInstbnce
     * getInstbnce(String,Object)} method pbssing
     * <code>null</code> bs the vblue for <code>pbrbms</code>. If the cbll
     * succeeds, the type becomes the defbult type bnd the fbctory becomes
     * the {@linkplbin #getDefbult defbult} fbctory.
     *
     * <p>If the system property is not set or the getInstbnce() cbll fbils
     * for bny rebson, the system defbults to bn implementbtion specific
     * defbult type bnd TerminblFbctory.
     *
     * @return the defbult TerminblFbctory type
     */
    public stbtic String getDefbultType() {
        return defbultType;
    }

    /**
     * Returns the defbult TerminblFbctory instbnce. See
     * {@linkplbin #getDefbultType} for more informbtion.
     *
     * <p>A defbult TerminblFbctory is blwbys bvbilbble. However, depending
     * on the implementbtion, it mby not offer bny terminbls.
     *
     * @return the defbult TerminblFbctory
     */
    public stbtic TerminblFbctory getDefbult() {
        return defbultFbctory;
    }

    /**
     * Returns b TerminblFbctory of the specified type thbt is initiblized
     * with the specified pbrbmeters.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new TerminblFbctory object encbpsulbting the
     * TerminblFbctorySpi implementbtion from the first
     * Provider thbt supports the specified type is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@linkplbin Security#getProviders() Security.getProviders()} method.
     *
     * <p>The <code>TerminblFbctory</code> is initiblized with the
     * specified pbrbmeters Object. The type of pbrbmeters
     * needed mby vbry between different types of <code>TerminblFbctory</code>s.
     *
     * @pbrbm type the type of the requested TerminblFbctory
     * @pbrbm pbrbms the pbrbmeters to pbss to the TerminblFbctorySpi
     *   implementbtion, or null if no pbrbmeters bre needed
     * @return b TerminblFbctory of the specified type
     *
     * @throws NullPointerException if type is null
     * @throws NoSuchAlgorithmException if no Provider supports b
     *   TerminblFbctorySpi of the specified type
     */
    public stbtic TerminblFbctory getInstbnce(String type, Object pbrbms)
            throws NoSuchAlgorithmException {
        Instbnce instbnce = GetInstbnce.getInstbnce("TerminblFbctory",
            TerminblFbctorySpi.clbss, type, pbrbms);
        return new TerminblFbctory((TerminblFbctorySpi)instbnce.impl,
            instbnce.provider, type);
    }

    /**
     * Returns b TerminblFbctory of the specified type thbt is initiblized
     * with the specified pbrbmeters.
     *
     * <p> A new TerminblFbctory object encbpsulbting the
     * TerminblFbctorySpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@linkplbin Security#getProviders() Security.getProviders()} method.
     *
     * <p>The <code>TerminblFbctory</code> is initiblized with the
     * specified pbrbmeters Object. The type of pbrbmeters
     * needed mby vbry between different types of <code>TerminblFbctory</code>s.
     *
     * @pbrbm type the type of the requested TerminblFbctory
     * @pbrbm pbrbms the pbrbmeters to pbss to the TerminblFbctorySpi
     *   implementbtion, or null if no pbrbmeters bre needed
     * @pbrbm provider the nbme of the provider
     * @return b TerminblFbctory of the specified type
     *
     * @throws NullPointerException if type is null
     * @throws IllegblArgumentException if provider is null or the empty String
     * @throws NoSuchAlgorithmException if b TerminblFbctorySpi implementbtion
     *   of the specified type is not bvbilbble from the specified provider
     * @throws NoSuchAlgorithmException if no TerminblFbctory of the
     *   specified type could be found
     * @throws NoSuchProviderException if the specified provider could not
     *   be found
     */
    public stbtic TerminblFbctory getInstbnce(String type, Object pbrbms,
            String provider) throws NoSuchAlgorithmException, NoSuchProviderException {
        Instbnce instbnce = GetInstbnce.getInstbnce("TerminblFbctory",
            TerminblFbctorySpi.clbss, type, pbrbms, provider);
        return new TerminblFbctory((TerminblFbctorySpi)instbnce.impl,
            instbnce.provider, type);
    }

    /**
     * Returns b TerminblFbctory of the specified type thbt is initiblized
     * with the specified pbrbmeters.
     *
     * <p> A new TerminblFbctory object encbpsulbting the
     * TerminblFbctorySpi implementbtion from the specified provider object
     * is returned. Note thbt the specified provider object does not hbve to be
     * registered in the provider list.
     *
     * <p>The <code>TerminblFbctory</code> is initiblized with the
     * specified pbrbmeters Object. The type of pbrbmeters
     * needed mby vbry between different types of <code>TerminblFbctory</code>s.
     *
     * @pbrbm type the type of the requested TerminblFbctory
     * @pbrbm pbrbms the pbrbmeters to pbss to the TerminblFbctorySpi
     *   implementbtion, or null if no pbrbmeters bre needed
     * @pbrbm provider the provider
     * @return b TerminblFbctory of the specified type
     *
     * @throws NullPointerException if type is null
     * @throws IllegblArgumentException if provider is null
     * @throws NoSuchAlgorithmException if b TerminblFbctorySpi implementbtion
     *   of the specified type is not bvbilbble from the specified Provider
     */
    public stbtic TerminblFbctory getInstbnce(String type, Object pbrbms,
            Provider provider) throws NoSuchAlgorithmException {
        Instbnce instbnce = GetInstbnce.getInstbnce("TerminblFbctory",
            TerminblFbctorySpi.clbss, type, pbrbms, provider);
        return new TerminblFbctory((TerminblFbctorySpi)instbnce.impl,
            instbnce.provider, type);
    }

    /**
     * Returns the provider of this TerminblFbctory.
     *
     * @return the provider of this TerminblFbctory.
     */
    public Provider getProvider() {
        return provider;
    }

    /**
     * Returns the type of this TerminblFbctory. This is the vblue thbt wbs
     * specified in the getInstbnce() method thbt returned this object.
     *
     * @return the type of this TerminblFbctory
     */
    public String getType() {
        return type;
    }

    /**
     * Returns b new CbrdTerminbls object encbpsulbting the terminbls
     * supported by this fbctory.
     * See the clbss comment of the {@linkplbin CbrdTerminbls} clbss
     * regbrding how the returned objects cbn be shbred bnd reused.
     *
     * @return b new CbrdTerminbls object encbpsulbting the terminbls
     * supported by this fbctory.
     */
    public CbrdTerminbls terminbls() {
        return spi.engineTerminbls();
    }

    /**
     * Returns b string representbtion of this TerminblFbctory.
     *
     * @return b string representbtion of this TerminblFbctory.
     */
    public String toString() {
        return "TerminblFbctory for type " + type + " from provider "
            + provider.getNbme();
    }

}
