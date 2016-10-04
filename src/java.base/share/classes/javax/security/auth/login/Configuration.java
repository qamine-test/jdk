/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth.login;

import jbvbx.security.buth.AuthPermission;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.NoSuchProviderException;
import jbvb.security.Provider;
import jbvb.security.Security;
import jbvb.util.Objects;

import sun.security.jcb.GetInstbnce;

/**
 * A Configurbtion object is responsible for specifying which LoginModules
 * should be used for b pbrticulbr bpplicbtion, bnd in whbt order the
 * LoginModules should be invoked.
 *
 * <p> A login configurbtion contbins the following informbtion.
 * Note thbt this exbmple only represents the defbult syntbx for the
 * {@code Configurbtion}.  Subclbss implementbtions of this clbss
 * mby implement blternbtive syntbxes bnd mby retrieve the
 * {@code Configurbtion} from bny source such bs files, dbtbbbses,
 * or servers.
 *
 * <pre>
 *      Nbme {
 *            ModuleClbss  Flbg    ModuleOptions;
 *            ModuleClbss  Flbg    ModuleOptions;
 *            ModuleClbss  Flbg    ModuleOptions;
 *      };
 *      Nbme {
 *            ModuleClbss  Flbg    ModuleOptions;
 *            ModuleClbss  Flbg    ModuleOptions;
 *      };
 *      other {
 *            ModuleClbss  Flbg    ModuleOptions;
 *            ModuleClbss  Flbg    ModuleOptions;
 *      };
 * </pre>
 *
 * <p> Ebch entry in the {@code Configurbtion} is indexed vib bn
 * bpplicbtion nbme, <i>Nbme</i>, bnd contbins b list of
 * LoginModules configured for thbt bpplicbtion.  Ebch {@code LoginModule}
 * is specified vib its fully qublified clbss nbme.
 * Authenticbtion proceeds down the module list in the exbct order specified.
 * If bn bpplicbtion does not hbve b specific entry,
 * it defbults to the specific entry for "<i>other</i>".
 *
 * <p> The <i>Flbg</i> vblue controls the overbll behbvior bs buthenticbtion
 * proceeds down the stbck.  The following represents b description of the
 * vblid vblues for <i>Flbg</i> bnd their respective sembntics:
 *
 * <pre>
 *      1) Required     - The {@code LoginModule} is required to succeed.
 *                      If it succeeds or fbils, buthenticbtion still continues
 *                      to proceed down the {@code LoginModule} list.
 *
 *      2) Requisite    - The {@code LoginModule} is required to succeed.
 *                      If it succeeds, buthenticbtion continues down the
 *                      {@code LoginModule} list.  If it fbils,
 *                      control immedibtely returns to the bpplicbtion
 *                      (buthenticbtion does not proceed down the
 *                      {@code LoginModule} list).
 *
 *      3) Sufficient   - The {@code LoginModule} is not required to
 *                      succeed.  If it does succeed, control immedibtely
 *                      returns to the bpplicbtion (buthenticbtion does not
 *                      proceed down the {@code LoginModule} list).
 *                      If it fbils, buthenticbtion continues down the
 *                      {@code LoginModule} list.
 *
 *      4) Optionbl     - The {@code LoginModule} is not required to
 *                      succeed.  If it succeeds or fbils,
 *                      buthenticbtion still continues to proceed down the
 *                      {@code LoginModule} list.
 * </pre>
 *
 * <p> The overbll buthenticbtion succeeds only if bll <i>Required</i> bnd
 * <i>Requisite</i> LoginModules succeed.  If b <i>Sufficient</i>
 * {@code LoginModule} is configured bnd succeeds,
 * then only the <i>Required</i> bnd <i>Requisite</i> LoginModules prior to
 * thbt <i>Sufficient</i> {@code LoginModule} need to hbve succeeded for
 * the overbll buthenticbtion to succeed. If no <i>Required</i> or
 * <i>Requisite</i> LoginModules bre configured for bn bpplicbtion,
 * then bt lebst one <i>Sufficient</i> or <i>Optionbl</i>
 * {@code LoginModule} must succeed.
 *
 * <p> <i>ModuleOptions</i> is b spbce sepbrbted list of
 * {@code LoginModule}-specific vblues which bre pbssed directly to
 * the underlying LoginModules.  Options bre defined by the
 * {@code LoginModule} itself, bnd control the behbvior within it.
 * For exbmple, b {@code LoginModule} mby define options to support
 * debugging/testing cbpbbilities.  The correct wby to specify options in the
 * {@code Configurbtion} is by using the following key-vblue pbiring:
 * <i>debug="true"</i>.  The key bnd vblue should be sepbrbted by bn
 * 'equbls' symbol, bnd the vblue should be surrounded by double quotes.
 * If b String in the form, ${system.property}, occurs in the vblue,
 * it will be expbnded to the vblue of the system property.
 * Note thbt there is no limit to the number of
 * options b {@code LoginModule} mby define.
 *
 * <p> The following represents bn exbmple {@code Configurbtion} entry
 * bbsed on the syntbx bbove:
 *
 * <pre>
 * Login {
 *   com.sun.security.buth.module.UnixLoginModule required;
 *   com.sun.security.buth.module.Krb5LoginModule optionbl
 *                   useTicketCbche="true"
 *                   ticketCbche="${user.home}${/}tickets";
 * };
 * </pre>
 *
 * <p> This {@code Configurbtion} specifies thbt bn bpplicbtion nbmed,
 * "Login", requires users to first buthenticbte to the
 * <i>com.sun.security.buth.module.UnixLoginModule</i>, which is
 * required to succeed.  Even if the <i>UnixLoginModule</i>
 * buthenticbtion fbils, the
 * <i>com.sun.security.buth.module.Krb5LoginModule</i>
 * still gets invoked.  This helps hide the source of fbilure.
 * Since the <i>Krb5LoginModule</i> is <i>Optionbl</i>, the overbll
 * buthenticbtion succeeds only if the <i>UnixLoginModule</i>
 * (<i>Required</i>) succeeds.
 *
 * <p> Also note thbt the LoginModule-specific options,
 * <i>useTicketCbche="true"</i> bnd
 * <i>ticketCbche=${user.home}${/}tickets"</i>,
 * bre pbssed to the <i>Krb5LoginModule</i>.
 * These options instruct the <i>Krb5LoginModule</i> to
 * use the ticket cbche bt the specified locbtion.
 * The system properties, <i>user.home</i> bnd <i>/</i>
 * (file.sepbrbtor), bre expbnded to their respective vblues.
 *
 * <p> There is only one Configurbtion object instblled in the runtime bt bny
 * given time.  A Configurbtion object cbn be instblled by cblling the
 * {@code setConfigurbtion} method.  The instblled Configurbtion object
 * cbn be obtbined by cblling the {@code getConfigurbtion} method.
 *
 * <p> If no Configurbtion object hbs been instblled in the runtime, b cbll to
 * {@code getConfigurbtion} instblls bn instbnce of the defbult
 * Configurbtion implementbtion (b defbult subclbss implementbtion of this
 * bbstrbct clbss).
 * The defbult Configurbtion implementbtion cbn be chbnged by setting the vblue
 * of the {@code login.configurbtion.provider} security property to the fully
 * qublified nbme of the desired Configurbtion subclbss implementbtion.
 *
 * <p> Applicbtion code cbn directly subclbss Configurbtion to provide b custom
 * implementbtion.  In bddition, bn instbnce of b Configurbtion object cbn be
 * constructed by invoking one of the {@code getInstbnce} fbctory methods
 * with b stbndbrd type.  The defbult policy type is "JbvbLoginConfig".
 * See the Configurbtion section in the <b href=
 * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Configurbtion">
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
 * for b list of stbndbrd Configurbtion types.
 *
 * @see jbvbx.security.buth.login.LoginContext
 * @see jbvb.security.Security security properties
 */
public bbstrbct clbss Configurbtion {

    privbte stbtic Configurbtion configurbtion;

    privbte finbl jbvb.security.AccessControlContext bcc =
            jbvb.security.AccessController.getContext();

    privbte stbtic void checkPermission(String type) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new AuthPermission
                                ("crebteLoginConfigurbtion." + type));
        }
    }

    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected Configurbtion() { }

    /**
     * Get the instblled login Configurbtion.
     *
     * <p>
     *
     * @return the login Configurbtion.  If b Configurbtion object wbs set
     *          vib the {@code Configurbtion.setConfigurbtion} method,
     *          then thbt object is returned.  Otherwise, b defbult
     *          Configurbtion object is returned.
     *
     * @exception SecurityException if the cbller does not hbve permission
     *                          to retrieve the Configurbtion.
     *
     * @see #setConfigurbtion
     */
    public stbtic Configurbtion getConfigurbtion() {

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkPermission(new AuthPermission("getLoginConfigurbtion"));

        synchronized (Configurbtion.clbss) {
            if (configurbtion == null) {
                String config_clbss = null;
                config_clbss = AccessController.doPrivileged
                    (new PrivilegedAction<String>() {
                    public String run() {
                        return jbvb.security.Security.getProperty
                                    ("login.configurbtion.provider");
                    }
                });
                if (config_clbss == null) {
                    config_clbss = "sun.security.provider.ConfigFile";
                }

                try {
                    finbl String finblClbss = config_clbss;
                    Configurbtion untrustedImpl = AccessController.doPrivileged(
                            new PrivilegedExceptionAction<Configurbtion>() {
                                public Configurbtion run() throws ClbssNotFoundException,
                                        InstbntibtionException,
                                        IllegblAccessException {
                                    Clbss<? extends Configurbtion> implClbss = Clbss.forNbme(
                                            finblClbss, fblse,
                                            Threbd.currentThrebd().getContextClbssLobder()
                                    ).bsSubclbss(Configurbtion.clbss);
                                    return implClbss.newInstbnce();
                                }
                            });
                    AccessController.doPrivileged(
                            new PrivilegedExceptionAction<Void>() {
                                public Void run() {
                                    setConfigurbtion(untrustedImpl);
                                    return null;
                                }
                            }, Objects.requireNonNull(untrustedImpl.bcc)
                    );
                } cbtch (PrivilegedActionException e) {
                    Exception ee = e.getException();
                    if (ee instbnceof InstbntibtionException) {
                        throw (SecurityException) new
                            SecurityException
                                    ("Configurbtion error:" +
                                     ee.getCbuse().getMessbge() +
                                     "\n").initCbuse(ee.getCbuse());
                    } else {
                        throw (SecurityException) new
                            SecurityException
                                    ("Configurbtion error: " +
                                     ee.toString() +
                                     "\n").initCbuse(ee);
                    }
                }
            }
            return configurbtion;
        }
    }

    /**
     * Set the login {@code Configurbtion}.
     *
     * <p>
     *
     * @pbrbm configurbtion the new {@code Configurbtion}
     *
     * @exception SecurityException if the current threbd does not hbve
     *                  Permission to set the {@code Configurbtion}.
     *
     * @see #getConfigurbtion
     */
    public stbtic void setConfigurbtion(Configurbtion configurbtion) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkPermission(new AuthPermission("setLoginConfigurbtion"));
        Configurbtion.configurbtion = configurbtion;
    }

    /**
     * Returns b Configurbtion object of the specified type.
     *
     * <p> This method trbverses the list of registered security providers,
     * stbrting with the most preferred Provider.
     * A new Configurbtion object encbpsulbting the
     * ConfigurbtionSpi implementbtion from the first
     * Provider thbt supports the specified type is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm type the specified Configurbtion type.  See the Configurbtion
     *    section in the <b href=
     *    "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Configurbtion">
     *    Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme
     *    Documentbtion</b> for b list of stbndbrd Configurbtion types.
     *
     * @pbrbm pbrbms pbrbmeters for the Configurbtion, which mby be null.
     *
     * @return the new Configurbtion object.
     *
     * @exception SecurityException if the cbller does not hbve permission
     *          to get b Configurbtion instbnce for the specified type.
     *
     * @exception NullPointerException if the specified type is null.
     *
     * @exception IllegblArgumentException if the specified pbrbmeters
     *          bre not understood by the ConfigurbtionSpi implementbtion
     *          from the selected Provider.
     *
     * @exception NoSuchAlgorithmException if no Provider supports b
     *          ConfigurbtionSpi implementbtion for the specified type.
     *
     * @see Provider
     * @since 1.6
     */
    public stbtic Configurbtion getInstbnce(String type,
                                Configurbtion.Pbrbmeters pbrbms)
                throws NoSuchAlgorithmException {

        checkPermission(type);
        try {
            GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce
                                                        ("Configurbtion",
                                                        ConfigurbtionSpi.clbss,
                                                        type,
                                                        pbrbms);
            return new ConfigDelegbte((ConfigurbtionSpi)instbnce.impl,
                                                        instbnce.provider,
                                                        type,
                                                        pbrbms);
        } cbtch (NoSuchAlgorithmException nsbe) {
            return hbndleException (nsbe);
        }
    }

    /**
     * Returns b Configurbtion object of the specified type.
     *
     * <p> A new Configurbtion object encbpsulbting the
     * ConfigurbtionSpi implementbtion from the specified provider
     * is returned.   The specified provider must be registered
     * in the provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm type the specified Configurbtion type.  See the Configurbtion
     *    section in the <b href=
     *    "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Configurbtion">
     *    Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme
     *    Documentbtion</b> for b list of stbndbrd Configurbtion types.
     *
     * @pbrbm pbrbms pbrbmeters for the Configurbtion, which mby be null.
     *
     * @pbrbm provider the provider.
     *
     * @return the new Configurbtion object.
     *
     * @exception SecurityException if the cbller does not hbve permission
     *          to get b Configurbtion instbnce for the specified type.
     *
     * @exception NullPointerException if the specified type is null.
     *
     * @exception IllegblArgumentException if the specified provider
     *          is null or empty,
     *          or if the specified pbrbmeters bre not understood by
     *          the ConfigurbtionSpi implementbtion from the specified provider.
     *
     * @exception NoSuchProviderException if the specified provider is not
     *          registered in the security provider list.
     *
     * @exception NoSuchAlgorithmException if the specified provider does not
     *          support b ConfigurbtionSpi implementbtion for the specified
     *          type.
     *
     * @see Provider
     * @since 1.6
     */
    public stbtic Configurbtion getInstbnce(String type,
                                Configurbtion.Pbrbmeters pbrbms,
                                String provider)
                throws NoSuchProviderException, NoSuchAlgorithmException {

        if (provider == null || provider.length() == 0) {
            throw new IllegblArgumentException("missing provider");
        }

        checkPermission(type);
        try {
            GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce
                                                        ("Configurbtion",
                                                        ConfigurbtionSpi.clbss,
                                                        type,
                                                        pbrbms,
                                                        provider);
            return new ConfigDelegbte((ConfigurbtionSpi)instbnce.impl,
                                                        instbnce.provider,
                                                        type,
                                                        pbrbms);
        } cbtch (NoSuchAlgorithmException nsbe) {
            return hbndleException (nsbe);
        }
    }

    /**
     * Returns b Configurbtion object of the specified type.
     *
     * <p> A new Configurbtion object encbpsulbting the
     * ConfigurbtionSpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm type the specified Configurbtion type.  See the Configurbtion
     *    section in the <b href=
     *    "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Configurbtion">
     *    Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme
     *    Documentbtion</b> for b list of stbndbrd Configurbtion types.
     *
     * @pbrbm pbrbms pbrbmeters for the Configurbtion, which mby be null.
     *
     * @pbrbm provider the Provider.
     *
     * @return the new Configurbtion object.
     *
     * @exception SecurityException if the cbller does not hbve permission
     *          to get b Configurbtion instbnce for the specified type.
     *
     * @exception NullPointerException if the specified type is null.
     *
     * @exception IllegblArgumentException if the specified Provider is null,
     *          or if the specified pbrbmeters bre not understood by
     *          the ConfigurbtionSpi implementbtion from the specified Provider.
     *
     * @exception NoSuchAlgorithmException if the specified Provider does not
     *          support b ConfigurbtionSpi implementbtion for the specified
     *          type.
     *
     * @see Provider
     * @since 1.6
     */
    public stbtic Configurbtion getInstbnce(String type,
                                Configurbtion.Pbrbmeters pbrbms,
                                Provider provider)
                throws NoSuchAlgorithmException {

        if (provider == null) {
            throw new IllegblArgumentException("missing provider");
        }

        checkPermission(type);
        try {
            GetInstbnce.Instbnce instbnce = GetInstbnce.getInstbnce
                                                        ("Configurbtion",
                                                        ConfigurbtionSpi.clbss,
                                                        type,
                                                        pbrbms,
                                                        provider);
            return new ConfigDelegbte((ConfigurbtionSpi)instbnce.impl,
                                                        instbnce.provider,
                                                        type,
                                                        pbrbms);
        } cbtch (NoSuchAlgorithmException nsbe) {
            return hbndleException (nsbe);
        }
    }

    privbte stbtic Configurbtion hbndleException(NoSuchAlgorithmException nsbe)
                throws NoSuchAlgorithmException {
        Throwbble cbuse = nsbe.getCbuse();
        if (cbuse instbnceof IllegblArgumentException) {
            throw (IllegblArgumentException)cbuse;
        }
        throw nsbe;
    }

    /**
     * Return the Provider of this Configurbtion.
     *
     * <p> This Configurbtion instbnce will only hbve b Provider if it
     * wbs obtbined vib b cbll to {@code Configurbtion.getInstbnce}.
     * Otherwise this method returns null.
     *
     * @return the Provider of this Configurbtion, or null.
     *
     * @since 1.6
     */
    public Provider getProvider() {
        return null;
    }

    /**
     * Return the type of this Configurbtion.
     *
     * <p> This Configurbtion instbnce will only hbve b type if it
     * wbs obtbined vib b cbll to {@code Configurbtion.getInstbnce}.
     * Otherwise this method returns null.
     *
     * @return the type of this Configurbtion, or null.
     *
     * @since 1.6
     */
    public String getType() {
        return null;
    }

    /**
     * Return Configurbtion pbrbmeters.
     *
     * <p> This Configurbtion instbnce will only hbve pbrbmeters if it
     * wbs obtbined vib b cbll to {@code Configurbtion.getInstbnce}.
     * Otherwise this method returns null.
     *
     * @return Configurbtion pbrbmeters, or null.
     *
     * @since 1.6
     */
    public Configurbtion.Pbrbmeters getPbrbmeters() {
        return null;
    }

    /**
     * Retrieve the AppConfigurbtionEntries for the specified <i>nbme</i>
     * from this Configurbtion.
     *
     * <p>
     *
     * @pbrbm nbme the nbme used to index the Configurbtion.
     *
     * @return bn brrby of AppConfigurbtionEntries for the specified <i>nbme</i>
     *          from this Configurbtion, or null if there bre no entries
     *          for the specified <i>nbme</i>
     */
    public bbstrbct AppConfigurbtionEntry[] getAppConfigurbtionEntry
                                                        (String nbme);

    /**
     * Refresh bnd relobd the Configurbtion.
     *
     * <p> This method cbuses this Configurbtion object to refresh/relobd its
     * contents in bn implementbtion-dependent mbnner.
     * For exbmple, if this Configurbtion object stores its entries in b file,
     * cblling {@code refresh} mby cbuse the file to be re-rebd.
     *
     * <p> The defbult implementbtion of this method does nothing.
     * This method should be overridden if b refresh operbtion is supported
     * by the implementbtion.
     *
     * @exception SecurityException if the cbller does not hbve permission
     *                          to refresh its Configurbtion.
     */
    public void refresh() { }

    /**
     * This subclbss is returned by the getInstbnce cblls.  All Configurbtion
     * cblls bre delegbted to the underlying ConfigurbtionSpi.
     */
    privbte stbtic clbss ConfigDelegbte extends Configurbtion {

        privbte ConfigurbtionSpi spi;
        privbte Provider p;
        privbte String type;
        privbte Configurbtion.Pbrbmeters pbrbms;

        privbte ConfigDelegbte(ConfigurbtionSpi spi, Provider p,
                        String type, Configurbtion.Pbrbmeters pbrbms) {
            this.spi = spi;
            this.p = p;
            this.type = type;
            this.pbrbms = pbrbms;
        }

        public String getType() { return type; }

        public Configurbtion.Pbrbmeters getPbrbmeters() { return pbrbms; }

        public Provider getProvider() { return p; }

        public AppConfigurbtionEntry[] getAppConfigurbtionEntry(String nbme) {
            return spi.engineGetAppConfigurbtionEntry(nbme);
        }

        public void refresh() {
            spi.engineRefresh();
        }
    }

    /**
     * This represents b mbrker interfbce for Configurbtion pbrbmeters.
     *
     * @since 1.6
     */
    public stbtic interfbce Pbrbmeters { }
}
