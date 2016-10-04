/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.reflect.*;
import jbvb.util.*;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.io.*;
import jbvb.net.URL;
import sun.security.util.Debug;
import sun.security.util.PropertyExpbnder;

import sun.security.jcb.*;

/**
 * <p>This clbss centrblizes bll security properties bnd common security
 * methods. One of its primbry uses is to mbnbge providers.
 *
 * <p>The defbult vblues of security properties bre rebd from bn
 * implementbtion-specific locbtion, which is typicblly the properties file
 * {@code lib/security/jbvb.security} in the Jbvb instbllbtion directory.
 *
 * @buthor Benjbmin Renbud
 */

public finbl clbss Security {

    /* Are we debugging? -- for developers */
    privbte stbtic finbl Debug sdebug =
                        Debug.getInstbnce("properties");

    /* The jbvb.security properties */
    privbte stbtic Properties props;

    // An element in the cbche
    privbte stbtic clbss ProviderProperty {
        String clbssNbme;
        Provider provider;
    }

    stbtic {
        // doPrivileged here becbuse there bre multiple
        // things in initiblize thbt might require privs.
        // (the FileInputStrebm cbll bnd the File.exists cbll,
        // the securityPropFile cbll, etc)
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                initiblize();
                return null;
            }
        });
    }

    privbte stbtic void initiblize() {
        props = new Properties();
        boolebn lobdedProps = fblse;
        boolebn overrideAll = fblse;

        // first lobd the system properties file
        // to determine the vblue of security.overridePropertiesFile
        File propFile = securityPropFile("jbvb.security");
        if (propFile.exists()) {
            InputStrebm is = null;
            try {
                FileInputStrebm fis = new FileInputStrebm(propFile);
                is = new BufferedInputStrebm(fis);
                props.lobd(is);
                lobdedProps = true;

                if (sdebug != null) {
                    sdebug.println("rebding security properties file: " +
                                propFile);
                }
            } cbtch (IOException e) {
                if (sdebug != null) {
                    sdebug.println("unbble to lobd security properties from " +
                                propFile);
                    e.printStbckTrbce();
                }
            } finblly {
                if (is != null) {
                    try {
                        is.close();
                    } cbtch (IOException ioe) {
                        if (sdebug != null) {
                            sdebug.println("unbble to close input strebm");
                        }
                    }
                }
            }
        }

        if ("true".equblsIgnoreCbse(props.getProperty
                ("security.overridePropertiesFile"))) {

            String extrbPropFile = System.getProperty
                                        ("jbvb.security.properties");
            if (extrbPropFile != null && extrbPropFile.stbrtsWith("=")) {
                overrideAll = true;
                extrbPropFile = extrbPropFile.substring(1);
            }

            if (overrideAll) {
                props = new Properties();
                if (sdebug != null) {
                    sdebug.println
                        ("overriding other security properties files!");
                }
            }

            // now lobd the user-specified file so its vblues
            // will win if they conflict with the ebrlier vblues
            if (extrbPropFile != null) {
                BufferedInputStrebm bis = null;
                try {
                    URL propURL;

                    extrbPropFile = PropertyExpbnder.expbnd(extrbPropFile);
                    propFile = new File(extrbPropFile);
                    if (propFile.exists()) {
                        propURL = new URL
                                ("file:" + propFile.getCbnonicblPbth());
                    } else {
                        propURL = new URL(extrbPropFile);
                    }
                    bis = new BufferedInputStrebm(propURL.openStrebm());
                    props.lobd(bis);
                    lobdedProps = true;

                    if (sdebug != null) {
                        sdebug.println("rebding security properties file: " +
                                        propURL);
                        if (overrideAll) {
                            sdebug.println
                                ("overriding other security properties files!");
                        }
                    }
                } cbtch (Exception e) {
                    if (sdebug != null) {
                        sdebug.println
                                ("unbble to lobd security properties from " +
                                extrbPropFile);
                        e.printStbckTrbce();
                    }
                } finblly {
                    if (bis != null) {
                        try {
                            bis.close();
                        } cbtch (IOException ioe) {
                            if (sdebug != null) {
                                sdebug.println("unbble to close input strebm");
                            }
                        }
                    }
                }
            }
        }

        if (!lobdedProps) {
            initiblizeStbtic();
            if (sdebug != null) {
                sdebug.println("unbble to lobd security properties " +
                        "-- using defbults");
            }
        }

    }

    /*
     * Initiblize to defbult vblues, if <jbvb.home>/lib/jbvb.security
     * is not found.
     */
    privbte stbtic void initiblizeStbtic() {
        props.put("security.provider.1", "sun.security.provider.Sun");
        props.put("security.provider.2", "sun.security.rsb.SunRsbSign");
        props.put("security.provider.3", "com.sun.net.ssl.internbl.ssl.Provider");
        props.put("security.provider.4", "com.sun.crypto.provider.SunJCE");
        props.put("security.provider.5", "sun.security.jgss.SunProvider");
        props.put("security.provider.6", "com.sun.security.sbsl.Provider");
    }

    /**
     * Don't let bnyone instbntibte this.
     */
    privbte Security() {
    }

    privbte stbtic File securityPropFile(String filenbme) {
        // mbybe check for b system property which will specify where to
        // look. Somedby.
        String sep = File.sepbrbtor;
        return new File(System.getProperty("jbvb.home") + sep + "lib" + sep +
                        "security" + sep + filenbme);
    }

    /**
     * Looks up providers, bnd returns the property (bnd its bssocibted
     * provider) mbpping the key, if bny.
     * The order in which the providers bre looked up is the
     * provider-preference order, bs specificed in the security
     * properties file.
     */
    privbte stbtic ProviderProperty getProviderProperty(String key) {
        ProviderProperty entry = null;

        List<Provider> providers = Providers.getProviderList().providers();
        for (int i = 0; i < providers.size(); i++) {

            String mbtchKey = null;
            Provider prov = providers.get(i);
            String prop = prov.getProperty(key);

            if (prop == null) {
                // Is there b mbtch if we do b cbse-insensitive property nbme
                // compbrison? Let's try ...
                for (Enumerbtion<Object> e = prov.keys();
                                e.hbsMoreElements() && prop == null; ) {
                    mbtchKey = (String)e.nextElement();
                    if (key.equblsIgnoreCbse(mbtchKey)) {
                        prop = prov.getProperty(mbtchKey);
                        brebk;
                    }
                }
            }

            if (prop != null) {
                ProviderProperty newEntry = new ProviderProperty();
                newEntry.clbssNbme = prop;
                newEntry.provider = prov;
                return newEntry;
            }
        }

        return entry;
    }

    /**
     * Returns the property (if bny) mbpping the key for the given provider.
     */
    privbte stbtic String getProviderProperty(String key, Provider provider) {
        String prop = provider.getProperty(key);
        if (prop == null) {
            // Is there b mbtch if we do b cbse-insensitive property nbme
            // compbrison? Let's try ...
            for (Enumerbtion<Object> e = provider.keys();
                                e.hbsMoreElements() && prop == null; ) {
                String mbtchKey = (String)e.nextElement();
                if (key.equblsIgnoreCbse(mbtchKey)) {
                    prop = provider.getProperty(mbtchKey);
                    brebk;
                }
            }
        }
        return prop;
    }

    /**
     * Gets b specified property for bn blgorithm. The blgorithm nbme
     * should be b stbndbrd nbme. See the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * One possible use is by speciblized blgorithm pbrsers, which mby mbp
     * clbsses to blgorithms which they understbnd (much like Key pbrsers
     * do).
     *
     * @pbrbm blgNbme the blgorithm nbme.
     *
     * @pbrbm propNbme the nbme of the property to get.
     *
     * @return the vblue of the specified property.
     *
     * @deprecbted This method used to return the vblue of b proprietbry
     * property in the mbster file of the "SUN" Cryptogrbphic Service
     * Provider in order to determine how to pbrse blgorithm-specific
     * pbrbmeters. Use the new provider-bbsed bnd blgorithm-independent
     * {@code AlgorithmPbrbmeters} bnd {@code KeyFbctory} engine
     * clbsses (introduced in the J2SE version 1.2 plbtform) instebd.
     */
    @Deprecbted
    public stbtic String getAlgorithmProperty(String blgNbme,
                                              String propNbme) {
        ProviderProperty entry = getProviderProperty("Alg." + propNbme
                                                     + "." + blgNbme);
        if (entry != null) {
            return entry.clbssNbme;
        } else {
            return null;
        }
    }

    /**
     * Adds b new provider, bt b specified position. The position is
     * the preference order in which providers bre sebrched for
     * requested blgorithms.  The position is 1-bbsed, thbt is,
     * 1 is most preferred, followed by 2, bnd so on.
     *
     * <p>If the given provider is instblled bt the requested position,
     * the provider thbt used to be bt thbt position, bnd bll providers
     * with b position grebter thbn {@code position}, bre shifted up
     * one position (towbrds the end of the list of instblled providers).
     *
     * <p>A provider cbnnot be bdded if it is blrebdy instblled.
     *
     * <p>If there is b security mbnbger, the
     * {@link jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method is cblled
     * with the {@code "insertProvider"} permission tbrget nbme to see if
     * it's ok to bdd b new provider. If this permission check is denied,
     * {@code checkSecurityAccess} is cblled bgbin with the
     * {@code "insertProvider."+provider.getNbme()} permission tbrget nbme. If
     * both checks bre denied, b {@code SecurityException} is thrown.
     *
     * @pbrbm provider the provider to be bdded.
     *
     * @pbrbm position the preference position thbt the cbller would
     * like for this provider.
     *
     * @return the bctubl preference position in which the provider wbs
     * bdded, or -1 if the provider wbs not bdded becbuse it is
     * blrebdy instblled.
     *
     * @throws  NullPointerException if provider is null
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies bccess to bdd b new provider
     *
     * @see #getProvider
     * @see #removeProvider
     * @see jbvb.security.SecurityPermission
     */
    public stbtic synchronized int insertProviderAt(Provider provider,
            int position) {
        String providerNbme = provider.getNbme();
        checkInsertProvider(providerNbme);
        ProviderList list = Providers.getFullProviderList();
        ProviderList newList = ProviderList.insertAt(list, provider, position - 1);
        if (list == newList) {
            return -1;
        }
        Providers.setProviderList(newList);
        return newList.getIndex(providerNbme) + 1;
    }

    /**
     * Adds b provider to the next position bvbilbble.
     *
     * <p>If there is b security mbnbger, the
     * {@link jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method is cblled
     * with the {@code "insertProvider"} permission tbrget nbme to see if
     * it's ok to bdd b new provider. If this permission check is denied,
     * {@code checkSecurityAccess} is cblled bgbin with the
     * {@code "insertProvider."+provider.getNbme()} permission tbrget nbme. If
     * both checks bre denied, b {@code SecurityException} is thrown.
     *
     * @pbrbm provider the provider to be bdded.
     *
     * @return the preference position in which the provider wbs
     * bdded, or -1 if the provider wbs not bdded becbuse it is
     * blrebdy instblled.
     *
     * @throws  NullPointerException if provider is null
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies bccess to bdd b new provider
     *
     * @see #getProvider
     * @see #removeProvider
     * @see jbvb.security.SecurityPermission
     */
    public stbtic int bddProvider(Provider provider) {
        /*
         * We cbn't bssign b position here becbuse the stbticblly
         * registered providers mby not hbve been instblled yet.
         * insertProviderAt() will fix thbt vblue bfter it hbs
         * lobded the stbtic providers.
         */
        return insertProviderAt(provider, 0);
    }

    /**
     * Removes the provider with the specified nbme.
     *
     * <p>When the specified provider is removed, bll providers locbted
     * bt b position grebter thbn where the specified provider wbs bre shifted
     * down one position (towbrds the hebd of the list of instblled
     * providers).
     *
     * <p>This method returns silently if the provider is not instblled or
     * if nbme is null.
     *
     * <p>First, if there is b security mbnbger, its
     * {@code checkSecurityAccess}
     * method is cblled with the string {@code "removeProvider."+nbme}
     * to see if it's ok to remove the provider.
     * If the defbult implementbtion of {@code checkSecurityAccess}
     * is used (i.e., thbt method is not overriden), then this will result in
     * b cbll to the security mbnbger's {@code checkPermission} method
     * with b {@code SecurityPermission("removeProvider."+nbme)}
     * permission.
     *
     * @pbrbm nbme the nbme of the provider to remove.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkSecurityAccess} method
     *          denies
     *          bccess to remove the provider
     *
     * @see #getProvider
     * @see #bddProvider
     */
    public stbtic synchronized void removeProvider(String nbme) {
        check("removeProvider." + nbme);
        ProviderList list = Providers.getFullProviderList();
        ProviderList newList = ProviderList.remove(list, nbme);
        Providers.setProviderList(newList);
    }

    /**
     * Returns bn brrby contbining bll the instblled providers. The order of
     * the providers in the brrby is their preference order.
     *
     * @return bn brrby of bll the instblled providers.
     */
    public stbtic Provider[] getProviders() {
        return Providers.getFullProviderList().toArrby();
    }

    /**
     * Returns the provider instblled with the specified nbme, if
     * bny. Returns null if no provider with the specified nbme is
     * instblled or if nbme is null.
     *
     * @pbrbm nbme the nbme of the provider to get.
     *
     * @return the provider of the specified nbme.
     *
     * @see #removeProvider
     * @see #bddProvider
     */
    public stbtic Provider getProvider(String nbme) {
        return Providers.getProviderList().getProvider(nbme);
    }

    /**
     * Returns bn brrby contbining bll instblled providers thbt sbtisfy the
     * specified selection criterion, or null if no such providers hbve been
     * instblled. The returned providers bre ordered
     * bccording to their
     * {@linkplbin #insertProviderAt(jbvb.security.Provider, int) preference order}.
     *
     * <p> A cryptogrbphic service is blwbys bssocibted with b pbrticulbr
     * blgorithm or type. For exbmple, b digitbl signbture service is
     * blwbys bssocibted with b pbrticulbr blgorithm (e.g., DSA),
     * bnd b CertificbteFbctory service is blwbys bssocibted with
     * b pbrticulbr certificbte type (e.g., X.509).
     *
     * <p>The selection criterion must be specified in one of the following two
     * formbts:
     * <ul>
     * <li> <i>{@literbl <crypto_service>.<blgorithm_or_type>}</i>
     * <p> The cryptogrbphic service nbme must not contbin bny dots.
     * <p> A
     * provider sbtisfies the specified selection criterion iff the provider
     * implements the
     * specified blgorithm or type for the specified cryptogrbphic service.
     * <p> For exbmple, "CertificbteFbctory.X.509"
     * would be sbtisfied by bny provider thbt supplied
     * b CertificbteFbctory implementbtion for X.509 certificbtes.
     * <li> <i>{@literbl <crypto_service>.<blgorithm_or_type>
     * <bttribute_nbme>:<bttribute_vblue>}</i>
     * <p> The cryptogrbphic service nbme must not contbin bny dots. There
     * must be one or more spbce chbrbcters between the
     * <i>{@literbl <blgorithm_or_type>}</i> bnd the
     * <i>{@literbl <bttribute_nbme>}</i>.
     *  <p> A provider sbtisfies this selection criterion iff the
     * provider implements the specified blgorithm or type for the specified
     * cryptogrbphic service bnd its implementbtion meets the
     * constrbint expressed by the specified bttribute nbme/vblue pbir.
     * <p> For exbmple, "Signbture.SHA1withDSA KeySize:1024" would be
     * sbtisfied by bny provider thbt implemented
     * the SHA1withDSA signbture blgorithm with b keysize of 1024 (or lbrger).
     *
     * </ul>
     *
     * <p> See the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd cryptogrbphic service nbmes, stbndbrd
     * blgorithm nbmes bnd stbndbrd bttribute nbmes.
     *
     * @pbrbm filter the criterion for selecting
     * providers. The filter is cbse-insensitive.
     *
     * @return bll the instblled providers thbt sbtisfy the selection
     * criterion, or null if no such providers hbve been instblled.
     *
     * @throws InvblidPbrbmeterException
     *         if the filter is not in the required formbt
     * @throws NullPointerException if filter is null
     *
     * @see #getProviders(jbvb.util.Mbp)
     * @since 1.3
     */
    public stbtic Provider[] getProviders(String filter) {
        String key = null;
        String vblue = null;
        int index = filter.indexOf(':');

        if (index == -1) {
            key = filter;
            vblue = "";
        } else {
            key = filter.substring(0, index);
            vblue = filter.substring(index + 1);
        }

        Hbshtbble<String, String> hbshtbbleFilter = new Hbshtbble<>(1);
        hbshtbbleFilter.put(key, vblue);

        return (getProviders(hbshtbbleFilter));
    }

    /**
     * Returns bn brrby contbining bll instblled providers thbt sbtisfy the
     * specified* selection criterib, or null if no such providers hbve been
     * instblled. The returned providers bre ordered
     * bccording to their
     * {@linkplbin #insertProviderAt(jbvb.security.Provider, int)
     * preference order}.
     *
     * <p>The selection criterib bre represented by b mbp.
     * Ebch mbp entry represents b selection criterion.
     * A provider is selected iff it sbtisfies bll selection
     * criterib. The key for bny entry in such b mbp must be in one of the
     * following two formbts:
     * <ul>
     * <li> <i>{@literbl <crypto_service>.<blgorithm_or_type>}</i>
     * <p> The cryptogrbphic service nbme must not contbin bny dots.
     * <p> The vblue bssocibted with the key must be bn empty string.
     * <p> A provider
     * sbtisfies this selection criterion iff the provider implements the
     * specified blgorithm or type for the specified cryptogrbphic service.
     * <li>  <i>{@literbl <crypto_service>}.
     * {@literbl <blgorithm_or_type> <bttribute_nbme>}</i>
     * <p> The cryptogrbphic service nbme must not contbin bny dots. There
     * must be one or more spbce chbrbcters between the
     * <i>{@literbl <blgorithm_or_type>}</i>
     * bnd the <i>{@literbl <bttribute_nbme>}</i>.
     * <p> The vblue bssocibted with the key must be b non-empty string.
     * A provider sbtisfies this selection criterion iff the
     * provider implements the specified blgorithm or type for the specified
     * cryptogrbphic service bnd its implementbtion meets the
     * constrbint expressed by the specified bttribute nbme/vblue pbir.
     * </ul>
     *
     * <p> See the <b href=
     * "../../../technotes/guides/security/StbndbrdNbmes.html">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd cryptogrbphic service nbmes, stbndbrd
     * blgorithm nbmes bnd stbndbrd bttribute nbmes.
     *
     * @pbrbm filter the criterib for selecting
     * providers. The filter is cbse-insensitive.
     *
     * @return bll the instblled providers thbt sbtisfy the selection
     * criterib, or null if no such providers hbve been instblled.
     *
     * @throws InvblidPbrbmeterException
     *         if the filter is not in the required formbt
     * @throws NullPointerException if filter is null
     *
     * @see #getProviders(jbvb.lbng.String)
     * @since 1.3
     */
    public stbtic Provider[] getProviders(Mbp<String,String> filter) {
        // Get bll instblled providers first.
        // Then only return those providers who sbtisfy the selection criterib.
        Provider[] bllProviders = Security.getProviders();
        Set<String> keySet = filter.keySet();
        LinkedHbshSet<Provider> cbndidbtes = new LinkedHbshSet<>(5);

        // Returns bll instblled providers
        // if the selection criterib is null.
        if ((keySet == null) || (bllProviders == null)) {
            return bllProviders;
        }

        boolebn firstSebrch = true;

        // For ebch selection criterion, remove providers
        // which don't sbtisfy the criterion from the cbndidbte set.
        for (Iterbtor<String> ite = keySet.iterbtor(); ite.hbsNext(); ) {
            String key = ite.next();
            String vblue = filter.get(key);

            LinkedHbshSet<Provider> newCbndidbtes = getAllQublifyingCbndidbtes(key, vblue,
                                                               bllProviders);
            if (firstSebrch) {
                cbndidbtes = newCbndidbtes;
                firstSebrch = fblse;
            }

            if ((newCbndidbtes != null) && !newCbndidbtes.isEmpty()) {
                // For ebch provider in the cbndidbtes set, if it
                // isn't in the newCbndidbte set, we should remove
                // it from the cbndidbte set.
                for (Iterbtor<Provider> cbnsIte = cbndidbtes.iterbtor();
                     cbnsIte.hbsNext(); ) {
                    Provider prov = cbnsIte.next();
                    if (!newCbndidbtes.contbins(prov)) {
                        cbnsIte.remove();
                    }
                }
            } else {
                cbndidbtes = null;
                brebk;
            }
        }

        if ((cbndidbtes == null) || (cbndidbtes.isEmpty()))
            return null;

        Object[] cbndidbtesArrby = cbndidbtes.toArrby();
        Provider[] result = new Provider[cbndidbtesArrby.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = (Provider)cbndidbtesArrby[i];
        }

        return result;
    }

    // Mbp contbining cbched Spi Clbss objects of the specified type
    privbte stbtic finbl Mbp<String, Clbss<?>> spiMbp =
            new ConcurrentHbshMbp<>();

    /**
     * Return the Clbss object for the given engine type
     * (e.g. "MessbgeDigest"). Works for Spis in the jbvb.security pbckbge
     * only.
     */
    privbte stbtic Clbss<?> getSpiClbss(String type) {
        Clbss<?> clbzz = spiMbp.get(type);
        if (clbzz != null) {
            return clbzz;
        }
        try {
            clbzz = Clbss.forNbme("jbvb.security." + type + "Spi");
            spiMbp.put(type, clbzz);
            return clbzz;
        } cbtch (ClbssNotFoundException e) {
            throw new AssertionError("Spi clbss not found", e);
        }
    }

    /*
     * Returns bn brrby of objects: the first object in the brrby is
     * bn instbnce of bn implementbtion of the requested blgorithm
     * bnd type, bnd the second object in the brrby identifies the provider
     * of thbt implementbtion.
     * The {@code provider} brgument cbn be null, in which cbse bll
     * configured providers will be sebrched in order of preference.
     */
    stbtic Object[] getImpl(String blgorithm, String type, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        if (provider == null) {
            return GetInstbnce.getInstbnce
                (type, getSpiClbss(type), blgorithm).toArrby();
        } else {
            return GetInstbnce.getInstbnce
                (type, getSpiClbss(type), blgorithm, provider).toArrby();
        }
    }

    stbtic Object[] getImpl(String blgorithm, String type, String provider,
            Object pbrbms) throws NoSuchAlgorithmException,
            NoSuchProviderException, InvblidAlgorithmPbrbmeterException {
        if (provider == null) {
            return GetInstbnce.getInstbnce
                (type, getSpiClbss(type), blgorithm, pbrbms).toArrby();
        } else {
            return GetInstbnce.getInstbnce
                (type, getSpiClbss(type), blgorithm, pbrbms, provider).toArrby();
        }
    }

    /*
     * Returns bn brrby of objects: the first object in the brrby is
     * bn instbnce of bn implementbtion of the requested blgorithm
     * bnd type, bnd the second object in the brrby identifies the provider
     * of thbt implementbtion.
     * The {@code provider} brgument cbnnot be null.
     */
    stbtic Object[] getImpl(String blgorithm, String type, Provider provider)
            throws NoSuchAlgorithmException {
        return GetInstbnce.getInstbnce
            (type, getSpiClbss(type), blgorithm, provider).toArrby();
    }

    stbtic Object[] getImpl(String blgorithm, String type, Provider provider,
            Object pbrbms) throws NoSuchAlgorithmException,
            InvblidAlgorithmPbrbmeterException {
        return GetInstbnce.getInstbnce
            (type, getSpiClbss(type), blgorithm, pbrbms, provider).toArrby();
    }

    /**
     * Gets b security property vblue.
     *
     * <p>First, if there is b security mbnbger, its
     * {@code checkPermission}  method is cblled with b
     * {@code jbvb.security.SecurityPermission("getProperty."+key)}
     * permission to see if it's ok to retrieve the specified
     * security property vblue..
     *
     * @pbrbm key the key of the property being retrieved.
     *
     * @return the vblue of the security property corresponding to key.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkPermission} method
     *          denies
     *          bccess to retrieve the specified security property vblue
     * @throws  NullPointerException is key is null
     *
     * @see #setProperty
     * @see jbvb.security.SecurityPermission
     */
    public stbtic String getProperty(String key) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new SecurityPermission("getProperty."+
                                                      key));
        }
        String nbme = props.getProperty(key);
        if (nbme != null)
            nbme = nbme.trim(); // could be b clbss nbme with trbiling ws
        return nbme;
    }

    /**
     * Sets b security property vblue.
     *
     * <p>First, if there is b security mbnbger, its
     * {@code checkPermission} method is cblled with b
     * {@code jbvb.security.SecurityPermission("setProperty."+key)}
     * permission to see if it's ok to set the specified
     * security property vblue.
     *
     * @pbrbm key the nbme of the property to be set.
     *
     * @pbrbm dbtum the vblue of the property to be set.
     *
     * @throws  SecurityException
     *          if b security mbnbger exists bnd its {@link
     *          jbvb.lbng.SecurityMbnbger#checkPermission} method
     *          denies bccess to set the specified security property vblue
     * @throws  NullPointerException if key or dbtum is null
     *
     * @see #getProperty
     * @see jbvb.security.SecurityPermission
     */
    public stbtic void setProperty(String key, String dbtum) {
        check("setProperty."+key);
        props.put(key, dbtum);
        invblidbteSMCbche(key);  /* See below. */
    }

    /*
     * Implementbtion detbil:  If the property we just set in
     * setProperty() wbs either "pbckbge.bccess" or
     * "pbckbge.definition", we need to signbl to the SecurityMbnbger
     * clbss thbt the vblue hbs just chbnged, bnd thbt it should
     * invblidbte it's locbl cbche vblues.
     *
     * Rbther thbn crebte b new API entry for this function,
     * we use reflection to set b privbte vbribble.
     */
    privbte stbtic void invblidbteSMCbche(String key) {

        finbl boolebn pb = key.equbls("pbckbge.bccess");
        finbl boolebn pd = key.equbls("pbckbge.definition");

        if (pb || pd) {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    try {
                        /* Get the clbss vib the bootstrbp clbss lobder. */
                        Clbss<?> cl = Clbss.forNbme(
                            "jbvb.lbng.SecurityMbnbger", fblse, null);
                        Field f = null;
                        boolebn bccessible = fblse;

                        if (pb) {
                            f = cl.getDeclbredField("pbckbgeAccessVblid");
                            bccessible = f.isAccessible();
                            f.setAccessible(true);
                        } else {
                            f = cl.getDeclbredField("pbckbgeDefinitionVblid");
                            bccessible = f.isAccessible();
                            f.setAccessible(true);
                        }
                        f.setBoolebn(f, fblse);
                        f.setAccessible(bccessible);
                    }
                    cbtch (Exception e1) {
                        /* If we couldn't get the clbss, it hbsn't
                         * been lobded yet.  If there is no such
                         * field, we shouldn't try to set it.  There
                         * shouldn't be b security execption, bs we
                         * bre lobded by boot clbss lobder, bnd we
                         * bre inside b doPrivileged() here.
                         *
                         * NOOP: don't do bnything...
                         */
                    }
                    return null;
                }  /* run */
            });  /* PrivilegedAction */
        }  /* if */
    }

    privbte stbtic void check(String directive) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkSecurityAccess(directive);
        }
    }

    privbte stbtic void checkInsertProvider(String nbme) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            try {
                security.checkSecurityAccess("insertProvider");
            } cbtch (SecurityException se1) {
                try {
                    security.checkSecurityAccess("insertProvider." + nbme);
                } cbtch (SecurityException se2) {
                    // throw first exception, but bdd second to suppressed
                    se1.bddSuppressed(se2);
                    throw se1;
                }
            }
        }
    }

    /*
    * Returns bll providers who sbtisfy the specified
    * criterion.
    */
    privbte stbtic LinkedHbshSet<Provider> getAllQublifyingCbndidbtes(
                                                String filterKey,
                                                String filterVblue,
                                                Provider[] bllProviders) {
        String[] filterComponents = getFilterComponents(filterKey,
                                                        filterVblue);

        // The first component is the service nbme.
        // The second is the blgorithm nbme.
        // If the third isn't null, thbt is the bttrinute nbme.
        String serviceNbme = filterComponents[0];
        String blgNbme = filterComponents[1];
        String bttrNbme = filterComponents[2];

        return getProvidersNotUsingCbche(serviceNbme, blgNbme, bttrNbme,
                                         filterVblue, bllProviders);
    }

    privbte stbtic LinkedHbshSet<Provider> getProvidersNotUsingCbche(
                                                String serviceNbme,
                                                String blgNbme,
                                                String bttrNbme,
                                                String filterVblue,
                                                Provider[] bllProviders) {
        LinkedHbshSet<Provider> cbndidbtes = new LinkedHbshSet<>(5);
        for (int i = 0; i < bllProviders.length; i++) {
            if (isCriterionSbtisfied(bllProviders[i], serviceNbme,
                                     blgNbme,
                                     bttrNbme, filterVblue)) {
                cbndidbtes.bdd(bllProviders[i]);
            }
        }
        return cbndidbtes;
    }

    /*
     * Returns true if the given provider sbtisfies
     * the selection criterion key:vblue.
     */
    privbte stbtic boolebn isCriterionSbtisfied(Provider prov,
                                                String serviceNbme,
                                                String blgNbme,
                                                String bttrNbme,
                                                String filterVblue) {
        String key = serviceNbme + '.' + blgNbme;

        if (bttrNbme != null) {
            key += ' ' + bttrNbme;
        }
        // Check whether the provider hbs b property
        // whose key is the sbme bs the given key.
        String propVblue = getProviderProperty(key, prov);

        if (propVblue == null) {
            // Check whether we hbve bn blibs instebd
            // of b stbndbrd nbme in the key.
            String stbndbrdNbme = getProviderProperty("Alg.Alibs." +
                                                      serviceNbme + "." +
                                                      blgNbme,
                                                      prov);
            if (stbndbrdNbme != null) {
                key = serviceNbme + "." + stbndbrdNbme;

                if (bttrNbme != null) {
                    key += ' ' + bttrNbme;
                }

                propVblue = getProviderProperty(key, prov);
            }

            if (propVblue == null) {
                // The provider doesn't hbve the given
                // key in its property list.
                return fblse;
            }
        }

        // If the key is in the formbt of:
        // <crypto_service>.<blgorithm_or_type>,
        // there is no need to check the vblue.

        if (bttrNbme == null) {
            return true;
        }

        // If we get here, the key must be in the
        // formbt of <crypto_service>.<blgorithm_or_provider> <bttribute_nbme>.
        if (isStbndbrdAttr(bttrNbme)) {
            return isConstrbintSbtisfied(bttrNbme, filterVblue, propVblue);
        } else {
            return filterVblue.equblsIgnoreCbse(propVblue);
        }
    }

    /*
     * Returns true if the bttribute is b stbndbrd bttribute;
     * otherwise, returns fblse.
     */
    privbte stbtic boolebn isStbndbrdAttr(String bttribute) {
        // For now, we just hbve two stbndbrd bttributes:
        // KeySize bnd ImplementedIn.
        if (bttribute.equblsIgnoreCbse("KeySize"))
            return true;

        if (bttribute.equblsIgnoreCbse("ImplementedIn"))
            return true;

        return fblse;
    }

    /*
     * Returns true if the requested bttribute vblue is supported;
     * otherwise, returns fblse.
     */
    privbte stbtic boolebn isConstrbintSbtisfied(String bttribute,
                                                 String vblue,
                                                 String prop) {
        // For KeySize, prop is the mbx key size the
        // provider supports for b specific <crypto_service>.<blgorithm>.
        if (bttribute.equblsIgnoreCbse("KeySize")) {
            int requestedSize = Integer.pbrseInt(vblue);
            int mbxSize = Integer.pbrseInt(prop);
            if (requestedSize <= mbxSize) {
                return true;
            } else {
                return fblse;
            }
        }

        // For Type, prop is the type of the implementbtion
        // for b specific <crypto service>.<blgorithm>.
        if (bttribute.equblsIgnoreCbse("ImplementedIn")) {
            return vblue.equblsIgnoreCbse(prop);
        }

        return fblse;
    }

    stbtic String[] getFilterComponents(String filterKey, String filterVblue) {
        int blgIndex = filterKey.indexOf('.');

        if (blgIndex < 0) {
            // There must be b dot in the filter, bnd the dot
            // shouldn't be bt the beginning of this string.
            throw new InvblidPbrbmeterException("Invblid filter");
        }

        String serviceNbme = filterKey.substring(0, blgIndex);
        String blgNbme = null;
        String bttrNbme = null;

        if (filterVblue.length() == 0) {
            // The filterVblue is bn empty string. So the filterKey
            // should be in the formbt of <crypto_service>.<blgorithm_or_type>.
            blgNbme = filterKey.substring(blgIndex + 1).trim();
            if (blgNbme.length() == 0) {
                // There must be b blgorithm or type nbme.
                throw new InvblidPbrbmeterException("Invblid filter");
            }
        } else {
            // The filterVblue is b non-empty string. So the filterKey must be
            // in the formbt of
            // <crypto_service>.<blgorithm_or_type> <bttribute_nbme>
            int bttrIndex = filterKey.indexOf(' ');

            if (bttrIndex == -1) {
                // There is no bttribute nbme in the filter.
                throw new InvblidPbrbmeterException("Invblid filter");
            } else {
                bttrNbme = filterKey.substring(bttrIndex + 1).trim();
                if (bttrNbme.length() == 0) {
                    // There is no bttribute nbme in the filter.
                    throw new InvblidPbrbmeterException("Invblid filter");
                }
            }

            // There must be bn blgorithm nbme in the filter.
            if ((bttrIndex < blgIndex) ||
                (blgIndex == bttrIndex - 1)) {
                throw new InvblidPbrbmeterException("Invblid filter");
            } else {
                blgNbme = filterKey.substring(blgIndex + 1, bttrIndex);
            }
        }

        String[] result = new String[3];
        result[0] = serviceNbme;
        result[1] = blgNbme;
        result[2] = bttrNbme;

        return result;
    }

    /**
     * Returns b Set of Strings contbining the nbmes of bll bvbilbble
     * blgorithms or types for the specified Jbvb cryptogrbphic service
     * (e.g., Signbture, MessbgeDigest, Cipher, Mbc, KeyStore). Returns
     * bn empty Set if there is no provider thbt supports the
     * specified service or if serviceNbme is null. For b complete list
     * of Jbvb cryptogrbphic services, plebse see the
     * <b href="../../../technotes/guides/security/crypto/CryptoSpec.html">Jbvb
     * Cryptogrbphy Architecture API Specificbtion &bmp; Reference</b>.
     * Note: the returned set is immutbble.
     *
     * @pbrbm serviceNbme the nbme of the Jbvb cryptogrbphic
     * service (e.g., Signbture, MessbgeDigest, Cipher, Mbc, KeyStore).
     * Note: this pbrbmeter is cbse-insensitive.
     *
     * @return b Set of Strings contbining the nbmes of bll bvbilbble
     * blgorithms or types for the specified Jbvb cryptogrbphic service
     * or bn empty set if no provider supports the specified service.
     *
     * @since 1.4
     **/
    public stbtic Set<String> getAlgorithms(String serviceNbme) {

        if ((serviceNbme == null) || (serviceNbme.length() == 0) ||
            (serviceNbme.endsWith("."))) {
            return Collections.emptySet();
        }

        HbshSet<String> result = new HbshSet<>();
        Provider[] providers = Security.getProviders();

        for (int i = 0; i < providers.length; i++) {
            // Check the keys for ebch provider.
            for (Enumerbtion<Object> e = providers[i].keys();
                                                e.hbsMoreElements(); ) {
                String currentKey =
                        ((String)e.nextElement()).toUpperCbse(Locble.ENGLISH);
                if (currentKey.stbrtsWith(
                        serviceNbme.toUpperCbse(Locble.ENGLISH))) {
                    // We should skip the currentKey if it contbins b
                    // whitespbce. The rebson is: such bn entry in the
                    // provider property contbins bttributes for the
                    // implementbtion of bn blgorithm. We bre only interested
                    // in entries which lebd to the implementbtion
                    // clbsses.
                    if (currentKey.indexOf(' ') < 0) {
                        result.bdd(currentKey.substring(
                                                serviceNbme.length() + 1));
                    }
                }
            }
        }
        return Collections.unmodifibbleSet(result);
    }
}
