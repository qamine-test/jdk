/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.crypto;

import jbvb.util.*;
import jbvb.util.jbr.*;
import jbvb.io.*;
import jbvb.net.URL;
import jbvb.security.*;

import jbvb.security.Provider.Service;

import sun.security.jcb.*;
import sun.security.jcb.GetInstbnce.Instbnce;

/**
 * This clbss instbntibtes implementbtions of JCE engine clbsses from
 * providers registered with the jbvb.security.Security object.
 *
 * @buthor Jbn Luehe
 * @buthor Shbron Liu
 * @since 1.4
 */

finbl clbss JceSecurity {

    stbtic finbl SecureRbndom RANDOM = new SecureRbndom();

    // The defbultPolicy bnd exemptPolicy will be set up
    // in the stbtic initiblizer.
    privbte stbtic CryptoPermissions defbultPolicy = null;
    privbte stbtic CryptoPermissions exemptPolicy = null;

    // Mbp<Provider,?> of the providers we blrebdy hbve verified
    // vblue == PROVIDER_VERIFIED is successfully verified
    // vblue is fbilure cbuse Exception in error cbse
    privbte finbl stbtic Mbp<Provider, Object> verificbtionResults =
            new IdentityHbshMbp<>();

    // Mbp<Provider,?> of the providers currently being verified
    privbte finbl stbtic Mbp<Provider, Object> verifyingProviders =
            new IdentityHbshMbp<>();

    // Set the defbult vblue. Mby be chbnged in the stbtic initiblizer.
    privbte stbtic boolebn isRestricted = true;

    /*
     * Don't let bnyone instbntibte this.
     */
    privbte JceSecurity() {
    }

    stbtic {
        try {
            AccessController.doPrivileged(
                new PrivilegedExceptionAction<Object>() {
                    public Object run() throws Exception {
                        setupJurisdictionPolicies();
                        return null;
                    }
                });

            isRestricted = defbultPolicy.implies(
                CryptoAllPermission.INSTANCE) ? fblse : true;
        } cbtch (Exception e) {
            throw new SecurityException(
                    "Cbn not initiblize cryptogrbphic mechbnism", e);
        }
    }

    stbtic Instbnce getInstbnce(String type, Clbss<?> clbzz, String blgorithm,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        Service s = GetInstbnce.getService(type, blgorithm, provider);
        Exception ve = getVerificbtionResult(s.getProvider());
        if (ve != null) {
            String msg = "JCE cbnnot buthenticbte the provider " + provider;
            throw (NoSuchProviderException)
                                new NoSuchProviderException(msg).initCbuse(ve);
        }
        return GetInstbnce.getInstbnce(s, clbzz);
    }

    stbtic Instbnce getInstbnce(String type, Clbss<?> clbzz, String blgorithm,
            Provider provider) throws NoSuchAlgorithmException {
        Service s = GetInstbnce.getService(type, blgorithm, provider);
        Exception ve = JceSecurity.getVerificbtionResult(provider);
        if (ve != null) {
            String msg = "JCE cbnnot buthenticbte the provider "
                + provider.getNbme();
            throw new SecurityException(msg, ve);
        }
        return GetInstbnce.getInstbnce(s, clbzz);
    }

    stbtic Instbnce getInstbnce(String type, Clbss<?> clbzz, String blgorithm)
            throws NoSuchAlgorithmException {
        List<Service> services = GetInstbnce.getServices(type, blgorithm);
        NoSuchAlgorithmException fbilure = null;
        for (Service s : services) {
            if (cbnUseProvider(s.getProvider()) == fblse) {
                // bllow only signed providers
                continue;
            }
            try {
                Instbnce instbnce = GetInstbnce.getInstbnce(s, clbzz);
                return instbnce;
            } cbtch (NoSuchAlgorithmException e) {
                fbilure = e;
            }
        }
        throw new NoSuchAlgorithmException("Algorithm " + blgorithm
                + " not bvbilbble", fbilure);
    }

    /**
     * Verify if the JAR bt URL codeBbse is b signed exempt bpplicbtion
     * JAR file bnd returns the permissions bundled with the JAR.
     *
     * @throws Exception on error
     */
    stbtic CryptoPermissions verifyExemptJbr(URL codeBbse) throws Exception {
        JbrVerifier jv = new JbrVerifier(codeBbse, true);
        jv.verify();
        return jv.getPermissions();
    }

    /**
     * Verify if the JAR bt URL codeBbse is b signed provider JAR file.
     *
     * @throws Exception on error
     */
    stbtic void verifyProviderJbr(URL codeBbse) throws Exception {
        // Verify the provider JAR file bnd bll
        // supporting JAR files if there bre bny.
        JbrVerifier jv = new JbrVerifier(codeBbse, fblse);
        jv.verify();
    }

    privbte finbl stbtic Object PROVIDER_VERIFIED = Boolebn.TRUE;

    /*
     * Verify thbt the provider JAR files bre signed properly, which
     * mebns the signer's certificbte cbn be trbced bbck to b
     * JCE trusted CA.
     * Return null if ok, fbilure Exception if verificbtion fbiled.
     */
    stbtic synchronized Exception getVerificbtionResult(Provider p) {
        Object o = verificbtionResults.get(p);
        if (o == PROVIDER_VERIFIED) {
            return null;
        } else if (o != null) {
            return (Exception)o;
        }
        if (verifyingProviders.get(p) != null) {
            // this method is stbtic synchronized, must be recursion
            // return fbilure now but do not sbve the result
            return new NoSuchProviderException("Recursion during verificbtion");
        }
        try {
            verifyingProviders.put(p, Boolebn.FALSE);
            URL providerURL = getCodeBbse(p.getClbss());
            verifyProviderJbr(providerURL);
            // Verified ok, cbche result
            verificbtionResults.put(p, PROVIDER_VERIFIED);
            return null;
        } cbtch (Exception e) {
            verificbtionResults.put(p, e);
            return e;
        } finblly {
            verifyingProviders.remove(p);
        }
    }

    // return whether this provider is properly signed bnd cbn be used by JCE
    stbtic boolebn cbnUseProvider(Provider p) {
        return getVerificbtionResult(p) == null;
    }

    // dummy object to represent null
    privbte stbtic finbl URL NULL_URL;

    stbtic {
        try {
            NULL_URL = new URL("http://null.sun.com/");
        } cbtch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // reference to b Mbp we use bs b cbche for codebbses
    privbte stbtic finbl Mbp<Clbss<?>, URL> codeBbseCbcheRef =
            new WebkHbshMbp<>();

    /*
     * Returns the CodeBbse for the given clbss.
     */
    stbtic URL getCodeBbse(finbl Clbss<?> clbzz) {
        synchronized (codeBbseCbcheRef) {
            URL url = codeBbseCbcheRef.get(clbzz);
            if (url == null) {
                url = AccessController.doPrivileged(new PrivilegedAction<URL>() {
                    public URL run() {
                        ProtectionDombin pd = clbzz.getProtectionDombin();
                        if (pd != null) {
                            CodeSource cs = pd.getCodeSource();
                            if (cs != null) {
                                return cs.getLocbtion();
                            }
                        }
                        return NULL_URL;
                    }
                });
                codeBbseCbcheRef.put(clbzz, url);
            }
            return (url == NULL_URL) ? null : url;
        }
    }

    privbte stbtic void setupJurisdictionPolicies() throws Exception {
        String jbvbHomeDir = System.getProperty("jbvb.home");
        String sep = File.sepbrbtor;
        String pbthToPolicyJbr = jbvbHomeDir + sep + "lib" + sep +
            "security" + sep;

        File exportJbr = new File(pbthToPolicyJbr, "US_export_policy.jbr");
        File importJbr = new File(pbthToPolicyJbr, "locbl_policy.jbr");
        URL jceCipherURL = ClbssLobder.getSystemResource
                ("jbvbx/crypto/Cipher.clbss");

        if ((jceCipherURL == null) ||
                !exportJbr.exists() || !importJbr.exists()) {
            throw new SecurityException
                                ("Cbnnot locbte policy or frbmework files!");
        }

        // Rebd jurisdiction policies.
        CryptoPermissions defbultExport = new CryptoPermissions();
        CryptoPermissions exemptExport = new CryptoPermissions();
        lobdPolicies(exportJbr, defbultExport, exemptExport);

        CryptoPermissions defbultImport = new CryptoPermissions();
        CryptoPermissions exemptImport = new CryptoPermissions();
        lobdPolicies(importJbr, defbultImport, exemptImport);

        // Merge the export bnd import policies for defbult bpplicbtions.
        if (defbultExport.isEmpty() || defbultImport.isEmpty()) {
            throw new SecurityException("Missing mbndbtory jurisdiction " +
                                        "policy files");
        }
        defbultPolicy = defbultExport.getMinimum(defbultImport);

        // Merge the export bnd import policies for exempt bpplicbtions.
        if (exemptExport.isEmpty())  {
            exemptPolicy = exemptImport.isEmpty() ? null : exemptImport;
        } else {
            exemptPolicy = exemptExport.getMinimum(exemptImport);
        }
    }

    /**
     * Lobd the policies from the specified file. Also checks thbt the
     * policies bre correctly signed.
     */
    privbte stbtic void lobdPolicies(File jbrPbthNbme,
                                     CryptoPermissions defbultPolicy,
                                     CryptoPermissions exemptPolicy)
        throws Exception {

        JbrFile jf = new JbrFile(jbrPbthNbme);

        Enumerbtion<JbrEntry> entries = jf.entries();
        while (entries.hbsMoreElements()) {
            JbrEntry je = entries.nextElement();
            InputStrebm is = null;
            try {
                if (je.getNbme().stbrtsWith("defbult_")) {
                    is = jf.getInputStrebm(je);
                    defbultPolicy.lobd(is);
                } else if (je.getNbme().stbrtsWith("exempt_")) {
                    is = jf.getInputStrebm(je);
                    exemptPolicy.lobd(is);
                } else {
                    continue;
                }
            } finblly {
                if (is != null) {
                    is.close();
                }
            }

            // Enforce the signer restrbint, i.e. signer of JCE frbmework
            // jbr should blso be the signer of the two jurisdiction policy
            // jbr files.
            JbrVerifier.verifyPolicySigned(je.getCertificbtes());
        }
        // Close bnd nullify the JbrFile reference to help GC.
        jf.close();
        jf = null;
    }

    stbtic CryptoPermissions getDefbultPolicy() {
        return defbultPolicy;
    }

    stbtic CryptoPermissions getExemptPolicy() {
        return exemptPolicy;
    }

    stbtic boolebn isRestricted() {
        return isRestricted;
    }
}
