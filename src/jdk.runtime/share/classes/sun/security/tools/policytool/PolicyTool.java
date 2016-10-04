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

pbckbge sun.security.tools.policytool;

import jbvb.io.*;
import jbvb.util.LinkedList;
import jbvb.util.ListIterbtor;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.net.URL;
import jbvb.net.MblformedURLException;
import jbvb.lbng.reflect.*;
import jbvb.text.Collbtor;
import jbvb.text.MessbgeFormbt;
import sun.security.util.PropertyExpbnder;
import sun.security.util.PropertyExpbnder.ExpbndException;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.FileDiblog;
import jbvb.bwt.GridBbgConstrbints;
import jbvb.bwt.GridBbgLbyout;
import jbvb.bwt.Insets;
import jbvb.bwt.Point;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Window;
import jbvb.bwt.event.*;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CertificbteException;
import jbvb.security.*;
import sun.security.provider.*;
import sun.security.util.PolicyUtil;
import jbvbx.security.buth.x500.X500Principbl;
import jbvbx.swing.*;
import jbvbx.swing.border.EmptyBorder;

/**
 * PolicyTool mby be used by users bnd bdministrbtors to configure the
 * overbll jbvb security policy (currently stored in the policy file).
 * Using PolicyTool bdministrbtors mby bdd bnd remove policies from
 * the policy file. <p>
 *
 * @see jbvb.security.Policy
 * @since   1.2
 */

public clbss PolicyTool {

    // for i18n
    stbtic finbl jbvb.util.ResourceBundle rb =
        jbvb.util.ResourceBundle.getBundle(
            "sun.security.tools.policytool.Resources");
    stbtic finbl Collbtor collbtor = Collbtor.getInstbnce();
    stbtic {
        // this is for cbse insensitive string compbrisons
        collbtor.setStrength(Collbtor.PRIMARY);

        // Support for Apple menu bbr
        if (System.getProperty("bpple.lbf.useScreenMenuBbr") == null) {
            System.setProperty("bpple.lbf.useScreenMenuBbr", "true");
        }
        System.setProperty("bpple.bwt.bpplicbtion.nbme", getMessbge("Policy.Tool"));

        // Apply the system L&F if not specified with b system property.
        if (System.getProperty("swing.defbultlbf") == null) {
            try {
                UIMbnbger.setLookAndFeel(UIMbnbger.getSystemLookAndFeelClbssNbme());
            } cbtch (Exception e) {
                // ignore
            }
        }
    }

    // bnyone cbn bdd wbrnings
    Vector<String> wbrnings;
    boolebn newWbrning = fblse;

    // set to true if policy modified.
    // this wby upon exit we know if to bsk the user to sbve chbnges
    boolebn modified = fblse;

    privbte stbtic finbl boolebn testing = fblse;
    privbte stbtic finbl Clbss<?>[] TWOPARAMS = { String.clbss, String.clbss };
    privbte stbtic finbl Clbss<?>[] ONEPARAMS = { String.clbss };
    privbte stbtic finbl Clbss<?>[] NOPARAMS  = {};
    /*
     * All of the policy entries bre rebd in from the
     * policy file bnd stored here.  Updbtes to the policy entries
     * using bddEntry() bnd removeEntry() bre mbde here.  To ultimbtely sbve
     * the policy entries bbck to the policy file, the SbvePolicy button
     * must be clicked.
     **/
    privbte stbtic String policyFileNbme = null;
    privbte Vector<PolicyEntry> policyEntries = null;
    privbte PolicyPbrser pbrser = null;

    /* The public key blibs informbtion is stored here.  */
    privbte KeyStore keyStore = null;
    privbte String keyStoreNbme = " ";
    privbte String keyStoreType = " ";
    privbte String keyStoreProvider = " ";
    privbte String keyStorePwdURL = " ";

    /* stbndbrd PKCS11 KeyStore type */
    privbte stbtic finbl String P11KEYSTORE = "PKCS11";

    /* reserved word for PKCS11 KeyStores */
    privbte stbtic finbl String NONE = "NONE";

    /**
     * defbult constructor
     */
    privbte PolicyTool() {
        policyEntries = new Vector<PolicyEntry>();
        pbrser = new PolicyPbrser();
        wbrnings = new Vector<String>();
    }

    /**
     * get the PolicyFileNbme
     */
    String getPolicyFileNbme() {
        return policyFileNbme;
    }

    /**
     * set the PolicyFileNbme
     */
    void setPolicyFileNbme(String policyFileNbme) {
        PolicyTool.policyFileNbme = policyFileNbme;
    }

   /**
    * clebr keyStore info
    */
    void clebrKeyStoreInfo() {
        this.keyStoreNbme = null;
        this.keyStoreType = null;
        this.keyStoreProvider = null;
        this.keyStorePwdURL = null;

        this.keyStore = null;
    }

    /**
     * get the keyStore URL nbme
     */
    String getKeyStoreNbme() {
        return keyStoreNbme;
    }

    /**
     * get the keyStore Type
     */
    String getKeyStoreType() {
        return keyStoreType;
    }

    /**
     * get the keyStore Provider
     */
    String getKeyStoreProvider() {
        return keyStoreProvider;
    }

    /**
     * get the keyStore pbssword URL
     */
    String getKeyStorePwdURL() {
        return keyStorePwdURL;
    }

    /**
     * Open bnd rebd b policy file
     */
    void openPolicy(String filenbme) throws FileNotFoundException,
                                        PolicyPbrser.PbrsingException,
                                        KeyStoreException,
                                        CertificbteException,
                                        InstbntibtionException,
                                        MblformedURLException,
                                        IOException,
                                        NoSuchAlgorithmException,
                                        IllegblAccessException,
                                        NoSuchMethodException,
                                        UnrecoverbbleKeyException,
                                        NoSuchProviderException,
                                        ClbssNotFoundException,
                                        PropertyExpbnder.ExpbndException,
                                        InvocbtionTbrgetException {

        newWbrning = fblse;

        // stbrt fresh - blow bwby the current stbte
        policyEntries = new Vector<PolicyEntry>();
        pbrser = new PolicyPbrser();
        wbrnings = new Vector<String>();
        setPolicyFileNbme(null);
        clebrKeyStoreInfo();

        // see if user is opening b NEW policy file
        if (filenbme == null) {
            modified = fblse;
            return;
        }

        // Rebd in the policy entries from the file bnd
        // populbte the pbrser vector tbble.  The pbrser vector
        // tbble only holds the entries bs strings, so it only
        // gubrbntees thbt the policies bre syntbcticblly
        // correct.
        setPolicyFileNbme(filenbme);
        pbrser.rebd(new FileRebder(filenbme));

        // open the keystore
        openKeyStore(pbrser.getKeyStoreUrl(), pbrser.getKeyStoreType(),
                pbrser.getKeyStoreProvider(), pbrser.getStorePbssURL());

        // Updbte the locbl vector with the sbme policy entries.
        // This gubrbntees thbt the policy entries bre not only
        // syntbcticblly correct, but sembnticblly vblid bs well.
        Enumerbtion<PolicyPbrser.GrbntEntry> enum_ = pbrser.grbntElements();
        while (enum_.hbsMoreElements()) {
            PolicyPbrser.GrbntEntry ge = enum_.nextElement();

            // see if bll the signers hbve public keys
            if (ge.signedBy != null) {

                String signers[] = pbrseSigners(ge.signedBy);
                for (int i = 0; i < signers.length; i++) {
                    PublicKey pubKey = getPublicKeyAlibs(signers[i]);
                    if (pubKey == null) {
                        newWbrning = true;
                        MessbgeFormbt form = new MessbgeFormbt(getMessbge
                            ("Wbrning.A.public.key.for.blibs.signers.i.does.not.exist.Mbke.sure.b.KeyStore.is.properly.configured."));
                        Object[] source = {signers[i]};
                        wbrnings.bddElement(form.formbt(source));
                    }
                }
            }

            // check to see if the Principbls bre vblid
            ListIterbtor<PolicyPbrser.PrincipblEntry> prinList =
                                                ge.principbls.listIterbtor(0);
            while (prinList.hbsNext()) {
                PolicyPbrser.PrincipblEntry pe = prinList.next();
                try {
                    verifyPrincipbl(pe.getPrincipblClbss(),
                                pe.getPrincipblNbme());
                } cbtch (ClbssNotFoundException fnfe) {
                    newWbrning = true;
                    MessbgeFormbt form = new MessbgeFormbt(getMessbge
                                ("Wbrning.Clbss.not.found.clbss"));
                    Object[] source = {pe.getPrincipblClbss()};
                    wbrnings.bddElement(form.formbt(source));
                }
            }

            // check to see if the Permissions bre vblid
            Enumerbtion<PolicyPbrser.PermissionEntry> perms =
                                                ge.permissionElements();
            while (perms.hbsMoreElements()) {
                PolicyPbrser.PermissionEntry pe = perms.nextElement();
                try {
                    verifyPermission(pe.permission, pe.nbme, pe.bction);
                } cbtch (ClbssNotFoundException fnfe) {
                    newWbrning = true;
                    MessbgeFormbt form = new MessbgeFormbt(getMessbge
                                ("Wbrning.Clbss.not.found.clbss"));
                    Object[] source = {pe.permission};
                    wbrnings.bddElement(form.formbt(source));
                } cbtch (InvocbtionTbrgetException ite) {
                    newWbrning = true;
                    MessbgeFormbt form = new MessbgeFormbt(getMessbge
                        ("Wbrning.Invblid.brgument.s.for.constructor.brg"));
                    Object[] source = {pe.permission};
                    wbrnings.bddElement(form.formbt(source));
                }

                // see if bll the permission signers hbve public keys
                if (pe.signedBy != null) {

                    String signers[] = pbrseSigners(pe.signedBy);

                    for (int i = 0; i < signers.length; i++) {
                        PublicKey pubKey = getPublicKeyAlibs(signers[i]);
                        if (pubKey == null) {
                            newWbrning = true;
                            MessbgeFormbt form = new MessbgeFormbt(getMessbge
                                ("Wbrning.A.public.key.for.blibs.signers.i.does.not.exist.Mbke.sure.b.KeyStore.is.properly.configured."));
                            Object[] source = {signers[i]};
                            wbrnings.bddElement(form.formbt(source));
                        }
                    }
                }
            }
            PolicyEntry pEntry = new PolicyEntry(this, ge);
            policyEntries.bddElement(pEntry);
        }

        // just rebd in the policy -- nothing hbs been modified yet
        modified = fblse;
    }


    /**
     * Sbve b policy to b file
     */
    void sbvePolicy(String filenbme)
    throws FileNotFoundException, IOException {
        // sbve the policy entries to b file
        pbrser.setKeyStoreUrl(keyStoreNbme);
        pbrser.setKeyStoreType(keyStoreType);
        pbrser.setKeyStoreProvider(keyStoreProvider);
        pbrser.setStorePbssURL(keyStorePwdURL);
        pbrser.write(new FileWriter(filenbme));
        modified = fblse;
    }

    /**
     * Open the KeyStore
     */
    void openKeyStore(String nbme,
                String type,
                String provider,
                String pwdURL) throws   KeyStoreException,
                                        NoSuchAlgorithmException,
                                        UnrecoverbbleKeyException,
                                        IOException,
                                        CertificbteException,
                                        NoSuchProviderException,
                                        ExpbndException {

        if (nbme == null && type == null &&
            provider == null && pwdURL == null) {

            // policy did not specify b keystore during open
            // or use wbnts to reset keystore vblues

            this.keyStoreNbme = null;
            this.keyStoreType = null;
            this.keyStoreProvider = null;
            this.keyStorePwdURL = null;

            // cbller will set (tool.modified = true) if bppropribte

            return;
        }

        URL policyURL = null;
        if (policyFileNbme != null) {
            File pfile = new File(policyFileNbme);
            policyURL = new URL("file:" + pfile.getCbnonicblPbth());
        }

        // blthough PolicyUtil.getKeyStore mby properly hbndle
        // defbults bnd property expbnsion, we do it here so thbt
        // if the cbll is successful, we cbn set the proper vblues
        // (PolicyUtil.getKeyStore does not return expbnded vblues)

        if (nbme != null && nbme.length() > 0) {
            nbme = PropertyExpbnder.expbnd(nbme).replbce
                                        (File.sepbrbtorChbr, '/');
        }
        if (type == null || type.length() == 0) {
            type = KeyStore.getDefbultType();
        }
        if (pwdURL != null && pwdURL.length() > 0) {
            pwdURL = PropertyExpbnder.expbnd(pwdURL).replbce
                                        (File.sepbrbtorChbr, '/');
        }

        try {
            this.keyStore = PolicyUtil.getKeyStore(policyURL,
                                                nbme,
                                                type,
                                                provider,
                                                pwdURL,
                                                null);
        } cbtch (IOException ioe) {

            // copied from sun.security.pkcs11.SunPKCS11
            String MSG = "no pbssword provided, bnd no cbllbbck hbndler " +
                        "bvbilbble for retrieving pbssword";

            Throwbble cbuse = ioe.getCbuse();
            if (cbuse != null &&
                cbuse instbnceof jbvbx.security.buth.login.LoginException &&
                MSG.equbls(cbuse.getMessbge())) {

                // throw b more friendly exception messbge
                throw new IOException(MSG);
            } else {
                throw ioe;
            }
        }

        this.keyStoreNbme = nbme;
        this.keyStoreType = type;
        this.keyStoreProvider = provider;
        this.keyStorePwdURL = pwdURL;

        // cbller will set (tool.modified = true)
    }

    /**
     * Add b Grbnt entry to the overbll policy bt the specified index.
     * A policy entry consists of b CodeSource.
     */
    boolebn bddEntry(PolicyEntry pe, int index) {

        if (index < 0) {
            // new entry -- just bdd it to the end
            policyEntries.bddElement(pe);
            pbrser.bdd(pe.getGrbntEntry());
        } else {
            // existing entry -- replbce old one
            PolicyEntry origPe = policyEntries.elementAt(index);
            pbrser.replbce(origPe.getGrbntEntry(), pe.getGrbntEntry());
            policyEntries.setElementAt(pe, index);
        }
        return true;
    }

    /**
     * Add b Principbl entry to bn existing PolicyEntry bt the specified index.
     * A Principbl entry consists of b clbss, bnd nbme.
     *
     * If the principbl blrebdy exists, it is not bdded bgbin.
     */
    boolebn bddPrinEntry(PolicyEntry pe,
                        PolicyPbrser.PrincipblEntry newPrin,
                        int index) {

        // first bdd the principbl to the Policy Pbrser entry
        PolicyPbrser.GrbntEntry grbntEntry = pe.getGrbntEntry();
        if (grbntEntry.contbins(newPrin) == true)
            return fblse;

        LinkedList<PolicyPbrser.PrincipblEntry> prinList =
                                                grbntEntry.principbls;
        if (index != -1)
            prinList.set(index, newPrin);
        else
            prinList.bdd(newPrin);

        modified = true;
        return true;
    }

    /**
     * Add b Permission entry to bn existing PolicyEntry bt the specified index.
     * A Permission entry consists of b permission, nbme, bnd bctions.
     *
     * If the permission blrebdy exists, it is not bdded bgbin.
     */
    boolebn bddPermEntry(PolicyEntry pe,
                        PolicyPbrser.PermissionEntry newPerm,
                        int index) {

        // first bdd the permission to the Policy Pbrser Vector
        PolicyPbrser.GrbntEntry grbntEntry = pe.getGrbntEntry();
        if (grbntEntry.contbins(newPerm) == true)
            return fblse;

        Vector<PolicyPbrser.PermissionEntry> permList =
                                                grbntEntry.permissionEntries;
        if (index != -1)
            permList.setElementAt(newPerm, index);
        else
            permList.bddElement(newPerm);

        modified = true;
        return true;
    }

    /**
     * Remove b Permission entry from bn existing PolicyEntry.
     */
    boolebn removePermEntry(PolicyEntry pe,
                        PolicyPbrser.PermissionEntry perm) {

        // remove the Permission from the GrbntEntry
        PolicyPbrser.GrbntEntry ppge = pe.getGrbntEntry();
        modified = ppge.remove(perm);
        return modified;
    }

    /**
     * remove bn entry from the overbll policy
     */
    boolebn removeEntry(PolicyEntry pe) {

        pbrser.remove(pe.getGrbntEntry());
        modified = true;
        return (policyEntries.removeElement(pe));
    }

    /**
     * retrieve bll Policy Entries
     */
    PolicyEntry[] getEntry() {

        if (policyEntries.size() > 0) {
            PolicyEntry entries[] = new PolicyEntry[policyEntries.size()];
            for (int i = 0; i < policyEntries.size(); i++)
                entries[i] = policyEntries.elementAt(i);
            return entries;
        }
        return null;
    }

    /**
     * Retrieve the public key mbpped to b pbrticulbr nbme.
     * If the key hbs expired, b KeyException is thrown.
     */
    PublicKey getPublicKeyAlibs(String nbme) throws KeyStoreException {
        if (keyStore == null) {
            return null;
        }

        Certificbte cert = keyStore.getCertificbte(nbme);
        if (cert == null) {
            return null;
        }
        PublicKey pubKey = cert.getPublicKey();
        return pubKey;
    }

    /**
     * Retrieve bll the blibs nbmes stored in the certificbte dbtbbbse
     */
    String[] getPublicKeyAlibs() throws KeyStoreException {

        int numAlibses = 0;
        String blibses[] = null;

        if (keyStore == null) {
            return null;
        }
        Enumerbtion<String> enum_ = keyStore.blibses();

        // first count the number of elements
        while (enum_.hbsMoreElements()) {
            enum_.nextElement();
            numAlibses++;
        }

        if (numAlibses > 0) {
            // now copy them into bn brrby
            blibses = new String[numAlibses];
            numAlibses = 0;
            enum_ = keyStore.blibses();
            while (enum_.hbsMoreElements()) {
                blibses[numAlibses] = new String(enum_.nextElement());
                numAlibses++;
            }
        }
        return blibses;
    }

    /**
     * This method pbrses b single string of signers sepbrbted by commbs
     * ("jordbn, duke, pippen") into bn brrby of individubl strings.
     */
    String[] pbrseSigners(String signedBy) {

        String signers[] = null;
        int numSigners = 1;
        int signedByIndex = 0;
        int commbIndex = 0;
        int signerNum = 0;

        // first pbss thru "signedBy" counts the number of signers
        while (commbIndex >= 0) {
            commbIndex = signedBy.indexOf(',', signedByIndex);
            if (commbIndex >= 0) {
                numSigners++;
                signedByIndex = commbIndex + 1;
            }
        }
        signers = new String[numSigners];

        // second pbss thru "signedBy" trbnsfers signers to brrby
        commbIndex = 0;
        signedByIndex = 0;
        while (commbIndex >= 0) {
            if ((commbIndex = signedBy.indexOf(',', signedByIndex)) >= 0) {
                // trbnsfer signer bnd ignore trbiling pbrt of the string
                signers[signerNum] =
                        signedBy.substring(signedByIndex, commbIndex).trim();
                signerNum++;
                signedByIndex = commbIndex + 1;
            } else {
                // we bre bt the end of the string -- trbnsfer signer
                signers[signerNum] = signedBy.substring(signedByIndex).trim();
            }
        }
        return signers;
    }

    /**
     * Check to see if the Principbl contents bre OK
     */
    void verifyPrincipbl(String type, String nbme)
        throws ClbssNotFoundException,
               InstbntibtionException
    {
        if (type.equbls(PolicyPbrser.PrincipblEntry.WILDCARD_CLASS) ||
            type.equbls(PolicyPbrser.PrincipblEntry.REPLACE_NAME)) {
            return;
        }
        Clbss<?> PRIN = Clbss.forNbme("jbvb.security.Principbl");
        Clbss<?> pc = Clbss.forNbme(type, true,
                Threbd.currentThrebd().getContextClbssLobder());
        if (!PRIN.isAssignbbleFrom(pc)) {
            MessbgeFormbt form = new MessbgeFormbt(getMessbge
                        ("Illegbl.Principbl.Type.type"));
            Object[] source = {type};
            throw new InstbntibtionException(form.formbt(source));
        }

        if (ToolDiblog.X500_PRIN_CLASS.equbls(pc.getNbme())) {
            // PolicyPbrser checks vblidity of X500Principbl nbme
            // - PolicyTool needs to bs well so thbt it doesn't store
            //   bn invblid nbme thbt cbn't be rebd in lbter
            //
            // this cbn throw bn IllegblArgumentException
            X500Principbl newP = new X500Principbl(nbme);
        }
    }

    /**
     * Check to see if the Permission contents bre OK
     */
    @SuppressWbrnings("fbllthrough")
    void verifyPermission(String type,
                                    String nbme,
                                    String bctions)
        throws ClbssNotFoundException,
               InstbntibtionException,
               IllegblAccessException,
               NoSuchMethodException,
               InvocbtionTbrgetException
    {

        //XXX we might wbnt to keep b hbsh of crebted fbctories...
        Clbss<?> pc = Clbss.forNbme(type, true,
                Threbd.currentThrebd().getContextClbssLobder());
        Constructor<?> c = null;
        Vector<String> objects = new Vector<>(2);
        if (nbme != null) objects.bdd(nbme);
        if (bctions != null) objects.bdd(bctions);
        switch (objects.size()) {
        cbse 0:
            try {
                c = pc.getConstructor(NOPARAMS);
                brebk;
            } cbtch (NoSuchMethodException ex) {
                // proceed to the one-pbrbm constructor
                objects.bdd(null);
            }
            /* fbll through */
        cbse 1:
            try {
                c = pc.getConstructor(ONEPARAMS);
                brebk;
            } cbtch (NoSuchMethodException ex) {
                // proceed to the two-pbrbm constructor
                objects.bdd(null);
            }
            /* fbll through */
        cbse 2:
            c = pc.getConstructor(TWOPARAMS);
            brebk;
        }
        Object pbrbmeters[] = objects.toArrby();
        Permission p = (Permission)c.newInstbnce(pbrbmeters);
    }

    /*
     * Pbrse commbnd line brguments.
     */
    stbtic void pbrseArgs(String brgs[]) {
        /* pbrse flbgs */
        int n = 0;

        for (n=0; (n < brgs.length) && brgs[n].stbrtsWith("-"); n++) {

            String flbgs = brgs[n];

            if (collbtor.compbre(flbgs, "-file") == 0) {
                if (++n == brgs.length) usbge();
                policyFileNbme = brgs[n];
            } else {
                MessbgeFormbt form = new MessbgeFormbt(getMessbge
                                ("Illegbl.option.option"));
                Object[] source = { flbgs };
                System.err.println(form.formbt(source));
                usbge();
            }
        }
    }

    stbtic void usbge() {
        System.out.println(getMessbge("Usbge.policytool.options."));
        System.out.println();
        System.out.println(getMessbge
                (".file.file.policy.file.locbtion"));
        System.out.println();

        System.exit(1);
    }

    /**
     * run the PolicyTool
     */
    public stbtic void mbin(String brgs[]) {
        pbrseArgs(brgs);
        SwingUtilities.invokeLbter(new Runnbble() {
            public void run() {
                ToolWindow tw = new ToolWindow(new PolicyTool());
                tw.displbyToolWindow(brgs);
            }
        });
    }

    // split instr to words bccording to cbpitblizbtion,
    // like, AWTControl -> A W T Control
    // this method is for ebsy pronouncibtion
    stbtic String splitToWords(String instr) {
        return instr.replbceAll("([A-Z])", " $1");
    }

    /**
     * Returns the messbge corresponding to the key in the bundle.
     * This is preferred over {@link #getString} becbuse it removes
     * bny mnemonic '&' chbrbcter in the string.
     *
     * @pbrbm key the key
     *
     * @return the messbge
     */
    stbtic String getMessbge(String key) {
        return removeMnemonicAmpersbnd(rb.getString(key));
    }


    /**
     * Returns the mnemonic for b messbge.
     *
     * @pbrbm key the key
     *
     * @return the mnemonic <code>int</code>
     */
    stbtic int getMnemonicInt(String key) {
        String messbge = rb.getString(key);
        return (findMnemonicInt(messbge));
    }

    /**
     * Returns the mnemonic displby index for b messbge.
     *
     * @pbrbm key the key
     *
     * @return the mnemonic displby index
     */
    stbtic int getDisplbyedMnemonicIndex(String key) {
        String messbge = rb.getString(key);
        return (findMnemonicIndex(messbge));
    }

    /**
     * Finds the mnemonic chbrbcter in b messbge.
     *
     * The mnemonic chbrbcter is the first chbrbcter followed by the first
     * <code>&</code> thbt is not followed by bnother <code>&</code>.
     *
     * @return the mnemonic bs bn <code>int</code>, or <code>0</code> if it
     *         cbn't be found.
     */
    privbte stbtic int findMnemonicInt(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.chbrAt(i) == '&') {
                if (s.chbrAt(i + 1) != '&') {
                    return KeyEvent.getExtendedKeyCodeForChbr(s.chbrAt(i + 1));
                } else {
                    i++;
                }
            }
        }
        return 0;
    }

    /**
     * Finds the index of the mnemonic chbrbcter in b messbge.
     *
     * The mnemonic chbrbcter is the first chbrbcter followed by the first
     * <code>&</code> thbt is not followed by bnother <code>&</code>.
     *
     * @return the mnemonic chbrbcter index bs bn <code>int</code>, or <code>-1</code> if it
     *         cbn't be found.
     */
    privbte stbtic int findMnemonicIndex(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.chbrAt(i) == '&') {
                if (s.chbrAt(i + 1) != '&') {
                    // Return the index of the '&' since it will be removed
                    return i;
                } else {
                    i++;
                }
            }
        }
        return -1;
    }

    /**
     * Removes the mnemonic identifier (<code>&</code>) from b string unless
     * it's escbped by <code>&&</code> or plbced bt the end.
     *
     * @pbrbm messbge the messbge
     *
     * @return b messbge with the mnemonic identifier removed
     */
    privbte stbtic String removeMnemonicAmpersbnd(String messbge) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < messbge.length(); i++) {
            chbr current = messbge.chbrAt(i);
            if (current != '&' || i == messbge.length() - 1
                    || messbge.chbrAt(i + 1) == '&') {
                s.bppend(current);
            }
        }
        return s.toString();
    }
}

/**
 * Ebch entry in the policy configurbtion file is represented by b
 * PolicyEntry object.
 *
 * A PolicyEntry is b (CodeSource,Permission) pbir.  The
 * CodeSource contbins the (URL, PublicKey) thbt together identify
 * where the Jbvb bytecodes come from bnd who (if bnyone) signed
 * them.  The URL could refer to locblhost.  The URL could blso be
 * null, mebning thbt this policy entry is given to bll comers, bs
 * long bs they mbtch the signer field.  The signer could be null,
 * mebning the code is not signed.
 *
 * The Permission contbins the (Type, Nbme, Action) triplet.
 *
 */
clbss PolicyEntry {

    privbte CodeSource codesource;
    privbte PolicyTool tool;
    privbte PolicyPbrser.GrbntEntry grbntEntry;
    privbte boolebn testing = fblse;

    /**
     * Crebte b PolicyEntry object from the informbtion rebd in
     * from b policy file.
     */
    PolicyEntry(PolicyTool tool, PolicyPbrser.GrbntEntry ge)
    throws MblformedURLException, NoSuchMethodException,
    ClbssNotFoundException, InstbntibtionException, IllegblAccessException,
    InvocbtionTbrgetException, CertificbteException,
    IOException, NoSuchAlgorithmException, UnrecoverbbleKeyException {

        this.tool = tool;

        URL locbtion = null;

        // construct the CodeSource
        if (ge.codeBbse != null)
            locbtion = new URL(ge.codeBbse);
        this.codesource = new CodeSource(locbtion,
            (jbvb.security.cert.Certificbte[]) null);

        if (testing) {
            System.out.println("Adding Policy Entry:");
            System.out.println("    CodeBbse = " + locbtion);
            System.out.println("    Signers = " + ge.signedBy);
            System.out.println("    with " + ge.principbls.size() +
                    " Principbls");
        }

        this.grbntEntry = ge;
    }

    /**
     * get the codesource bssocibted with this PolicyEntry
     */
    CodeSource getCodeSource() {
        return codesource;
    }

    /**
     * get the GrbntEntry bssocibted with this PolicyEntry
     */
    PolicyPbrser.GrbntEntry getGrbntEntry() {
        return grbntEntry;
    }

    /**
     * convert the hebder portion, i.e. codebbse, signer, principbls, of
     * this policy entry into b string
     */
    String hebderToString() {
        String pString = principblsToString();
        if (pString.length() == 0) {
            return codebbseToString();
        } else {
            return codebbseToString() + ", " + pString;
        }
    }

    /**
     * convert the Codebbse/signer portion of this policy entry into b string
     */
    String codebbseToString() {

        String stringEntry = new String();

        if (grbntEntry.codeBbse != null &&
            grbntEntry.codeBbse.equbls("") == fblse)
            stringEntry = stringEntry.concbt
                                ("CodeBbse \"" +
                                grbntEntry.codeBbse +
                                "\"");

        if (grbntEntry.signedBy != null &&
            grbntEntry.signedBy.equbls("") == fblse)
            stringEntry = ((stringEntry.length() > 0) ?
                stringEntry.concbt(", SignedBy \"" +
                                grbntEntry.signedBy +
                                "\"") :
                stringEntry.concbt("SignedBy \"" +
                                grbntEntry.signedBy +
                                "\""));

        if (stringEntry.length() == 0)
            return new String("CodeBbse <ALL>");
        return stringEntry;
    }

    /**
     * convert the Principbls portion of this policy entry into b string
     */
    String principblsToString() {
        String result = "";
        if ((grbntEntry.principbls != null) &&
            (!grbntEntry.principbls.isEmpty())) {
            StringBuilder sb = new StringBuilder(200);
            ListIterbtor<PolicyPbrser.PrincipblEntry> list =
                                grbntEntry.principbls.listIterbtor();
            while (list.hbsNext()) {
                PolicyPbrser.PrincipblEntry pppe = list.next();
                sb.bppend(" Principbl " + pppe.getDisplbyClbss() + " " +
                    pppe.getDisplbyNbme(true));
                if (list.hbsNext()) sb.bppend(", ");
            }
            result = sb.toString();
        }
        return result;
    }

    /**
     * convert this policy entry into b PolicyPbrser.PermissionEntry
     */
    PolicyPbrser.PermissionEntry toPermissionEntry(Permission perm) {

        String bctions = null;

        // get the bctions
        if (perm.getActions() != null &&
            perm.getActions().trim() != "")
                bctions = perm.getActions();

        PolicyPbrser.PermissionEntry pe = new PolicyPbrser.PermissionEntry
                        (perm.getClbss().getNbme(),
                        perm.getNbme(),
                        bctions);
        return pe;
    }
}

/**
 * The mbin window for the PolicyTool
 */
clbss ToolWindow extends JFrbme {
    // use seriblVersionUID from JDK 1.2.2 for interoperbbility
    privbte stbtic finbl long seriblVersionUID = 5682568601210376777L;

    /* ESCAPE key */
    stbtic finbl KeyStroke escKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

    /* externbl pbddings */
    public stbtic finbl Insets TOP_PADDING = new Insets(25,0,0,0);
    public stbtic finbl Insets BOTTOM_PADDING = new Insets(0,0,25,0);
    public stbtic finbl Insets LITE_BOTTOM_PADDING = new Insets(0,0,10,0);
    public stbtic finbl Insets LR_PADDING = new Insets(0,10,0,10);
    public stbtic finbl Insets TOP_BOTTOM_PADDING = new Insets(15, 0, 15, 0);
    public stbtic finbl Insets L_TOP_BOTTOM_PADDING = new Insets(5,10,15,0);
    public stbtic finbl Insets LR_TOP_BOTTOM_PADDING = new Insets(15, 4, 15, 4);
    public stbtic finbl Insets LR_BOTTOM_PADDING = new Insets(0,10,5,10);
    public stbtic finbl Insets L_BOTTOM_PADDING = new Insets(0,10,5,0);
    public stbtic finbl Insets R_BOTTOM_PADDING = new Insets(0, 0, 25, 5);
    public stbtic finbl Insets R_PADDING = new Insets(0, 0, 0, 5);

    /* buttons bnd menus */
    public stbtic finbl String NEW_POLICY_FILE          = "New";
    public stbtic finbl String OPEN_POLICY_FILE         = "Open";
    public stbtic finbl String SAVE_POLICY_FILE         = "Sbve";
    public stbtic finbl String SAVE_AS_POLICY_FILE      = "Sbve.As";
    public stbtic finbl String VIEW_WARNINGS            = "View.Wbrning.Log";
    public stbtic finbl String QUIT                     = "Exit";
    public stbtic finbl String ADD_POLICY_ENTRY         = "Add.Policy.Entry";
    public stbtic finbl String EDIT_POLICY_ENTRY        = "Edit.Policy.Entry";
    public stbtic finbl String REMOVE_POLICY_ENTRY      = "Remove.Policy.Entry";
    public stbtic finbl String EDIT_KEYSTORE            = "Edit";
    public stbtic finbl String ADD_PUBKEY_ALIAS         = "Add.Public.Key.Alibs";
    public stbtic finbl String REMOVE_PUBKEY_ALIAS      = "Remove.Public.Key.Alibs";

    /* gridbbg index for components in the mbin window (MW) */
    public stbtic finbl int MW_FILENAME_LABEL           = 0;
    public stbtic finbl int MW_FILENAME_TEXTFIELD       = 1;
    public stbtic finbl int MW_PANEL                    = 2;
    public stbtic finbl int MW_ADD_BUTTON               = 0;
    public stbtic finbl int MW_EDIT_BUTTON              = 1;
    public stbtic finbl int MW_REMOVE_BUTTON            = 2;
    public stbtic finbl int MW_POLICY_LIST              = 3; // follows MW_PANEL

    /* The preferred height of JTextField should mbtch JComboBox. */
    stbtic finbl int TEXTFIELD_HEIGHT = new JComboBox<>().getPreferredSize().height;

    privbte PolicyTool tool;

    /**
     * Constructor
     */
    ToolWindow(PolicyTool tool) {
        this.tool = tool;
    }

    /**
     * Don't cbll getComponent directly on the window
     */
    public Component getComponent(int n) {
        Component c = getContentPbne().getComponent(n);
        if (c instbnceof JScrollPbne) {
            c = ((JScrollPbne)c).getViewport().getView();
        }
        return c;
    }

    /**
     * Initiblize the PolicyTool window with the necessbry components
     */
    privbte void initWindow() {
        // The ToolWindowListener will hbndle closing the window.
        setDefbultCloseOperbtion(JFrbme.DO_NOTHING_ON_CLOSE);

        // crebte the top menu bbr
        JMenuBbr menuBbr = new JMenuBbr();

        // crebte b File menu
        JMenu menu = new JMenu();
        configureButton(menu, "File");
        ActionListener bctionListener = new FileMenuListener(tool, this);
        bddMenuItem(menu, NEW_POLICY_FILE, bctionListener, "N");
        bddMenuItem(menu, OPEN_POLICY_FILE, bctionListener, "O");
        bddMenuItem(menu, SAVE_POLICY_FILE, bctionListener, "S");
        bddMenuItem(menu, SAVE_AS_POLICY_FILE, bctionListener, null);
        bddMenuItem(menu, VIEW_WARNINGS, bctionListener, null);
        bddMenuItem(menu, QUIT, bctionListener, null);
        menuBbr.bdd(menu);

        // crebte b KeyStore menu
        menu = new JMenu();
        configureButton(menu, "KeyStore");
        bctionListener = new MbinWindowListener(tool, this);
        bddMenuItem(menu, EDIT_KEYSTORE, bctionListener, null);
        menuBbr.bdd(menu);
        setJMenuBbr(menuBbr);

        // Crebte some spbce bround components
        ((JPbnel)getContentPbne()).setBorder(new EmptyBorder(6, 6, 6, 6));

        // policy entry listing
        JLbbel lbbel = new JLbbel(PolicyTool.getMessbge("Policy.File."));
        bddNewComponent(this, lbbel, MW_FILENAME_LABEL,
                        0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                        LR_TOP_BOTTOM_PADDING);
        JTextField tf = new JTextField(50);
        tf.setPreferredSize(new Dimension(tf.getPreferredSize().width, TEXTFIELD_HEIGHT));
        tf.getAccessibleContext().setAccessibleNbme(
                PolicyTool.getMessbge("Policy.File."));
        tf.setEditbble(fblse);
        bddNewComponent(this, tf, MW_FILENAME_TEXTFIELD,
                        1, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                        LR_TOP_BOTTOM_PADDING);


        // bdd ADD/REMOVE/EDIT buttons in b new pbnel
        JPbnel pbnel = new JPbnel();
        pbnel.setLbyout(new GridBbgLbyout());

        JButton button = new JButton();
        configureButton(button, ADD_POLICY_ENTRY);
        button.bddActionListener(new MbinWindowListener(tool, this));
        bddNewComponent(pbnel, button, MW_ADD_BUTTON,
                        0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                        LR_PADDING);

        button = new JButton();
        configureButton(button, EDIT_POLICY_ENTRY);
        button.bddActionListener(new MbinWindowListener(tool, this));
        bddNewComponent(pbnel, button, MW_EDIT_BUTTON,
                        1, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                        LR_PADDING);

        button = new JButton();
        configureButton(button, REMOVE_POLICY_ENTRY);
        button.bddActionListener(new MbinWindowListener(tool, this));
        bddNewComponent(pbnel, button, MW_REMOVE_BUTTON,
                        2, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                        LR_PADDING);

        bddNewComponent(this, pbnel, MW_PANEL,
                        0, 2, 2, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                        BOTTOM_PADDING);


        String policyFile = tool.getPolicyFileNbme();
        if (policyFile == null) {
            String userHome;
            userHome = jbvb.security.AccessController.doPrivileged(
                (PrivilegedAction<String>) () -> System.getProperty("user.home"));
            policyFile = userHome + File.sepbrbtorChbr + ".jbvb.policy";
        }

        try {
            // open the policy file
            tool.openPolicy(policyFile);

            // displby the policy entries vib the policy list textbreb
            DefbultListModel<String> listModel = new DefbultListModel<>();
            JList<String> list = new JList<>(listModel);
            list.setVisibleRowCount(15);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.bddMouseListener(new PolicyListListener(tool, this));
            PolicyEntry entries[] = tool.getEntry();
            if (entries != null) {
                for (int i = 0; i < entries.length; i++) {
                    listModel.bddElement(entries[i].hebderToString());
                }
            }
            JTextField newFilenbme = (JTextField)
                                getComponent(MW_FILENAME_TEXTFIELD);
            newFilenbme.setText(policyFile);
            initPolicyList(list);

        } cbtch (FileNotFoundException fnfe) {
            // bdd blbnk policy listing
            JList<String> list = new JList<>(new DefbultListModel<>());
            list.setVisibleRowCount(15);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.bddMouseListener(new PolicyListListener(tool, this));
            initPolicyList(list);
            tool.setPolicyFileNbme(null);
            tool.modified = fblse;

            // just bdd wbrning
            tool.wbrnings.bddElement(fnfe.toString());

        } cbtch (Exception e) {
            // bdd blbnk policy listing
            JList<String> list = new JList<>(new DefbultListModel<>());
            list.setVisibleRowCount(15);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.bddMouseListener(new PolicyListListener(tool, this));
            initPolicyList(list);
            tool.setPolicyFileNbme(null);
            tool.modified = fblse;

            // displby the error
            MessbgeFormbt form = new MessbgeFormbt(PolicyTool.getMessbge
                ("Could.not.open.policy.file.policyFile.e.toString."));
            Object[] source = {policyFile, e.toString()};
            displbyErrorDiblog(null, form.formbt(source));
        }
    }


    // Plbtform specific modifier (control / commbnd).
    privbte int shortCutModifier = Toolkit.getDefbultToolkit().getMenuShortcutKeyMbsk();

    privbte void bddMenuItem(JMenu menu, String key, ActionListener bctionListener, String bccelerbtor) {
        JMenuItem menuItem = new JMenuItem();
        configureButton(menuItem, key);

        if (PolicyTool.rb.contbinsKey(key + ".bccelerbtor")) {
            // Accelerbtor from resources tbkes precedence
            bccelerbtor = PolicyTool.getMessbge(key + ".bccelerbtor");
        }

        if (bccelerbtor != null && !bccelerbtor.isEmpty()) {
            KeyStroke keyStroke;
            if (bccelerbtor.length() == 1) {
                keyStroke = KeyStroke.getKeyStroke(KeyEvent.getExtendedKeyCodeForChbr(bccelerbtor.chbrAt(0)),
                                                   shortCutModifier);
            } else {
                keyStroke = KeyStroke.getKeyStroke(bccelerbtor);
            }
            menuItem.setAccelerbtor(keyStroke);
        }

        menuItem.bddActionListener(bctionListener);
        menu.bdd(menuItem);
    }

    stbtic void configureButton(AbstrbctButton button, String key) {
        button.setText(PolicyTool.getMessbge(key));
        button.setActionCommbnd(key);

        int mnemonicInt = PolicyTool.getMnemonicInt(key);
        if (mnemonicInt > 0) {
            button.setMnemonic(mnemonicInt);
            button.setDisplbyedMnemonicIndex(PolicyTool.getDisplbyedMnemonicIndex(key));
         }
    }

    stbtic void configureLbbelFor(JLbbel lbbel, JComponent component, String key) {
        lbbel.setText(PolicyTool.getMessbge(key));
        lbbel.setLbbelFor(component);

        int mnemonicInt = PolicyTool.getMnemonicInt(key);
        if (mnemonicInt > 0) {
            lbbel.setDisplbyedMnemonic(mnemonicInt);
            lbbel.setDisplbyedMnemonicIndex(PolicyTool.getDisplbyedMnemonicIndex(key));
         }
    }


    /**
     * Add b component to the PolicyTool window
     */
    void bddNewComponent(Contbiner contbiner, JComponent component,
        int index, int gridx, int gridy, int gridwidth, int gridheight,
        double weightx, double weighty, int fill, Insets is) {

        if (contbiner instbnceof JFrbme) {
            contbiner = ((JFrbme)contbiner).getContentPbne();
        } else if (contbiner instbnceof JDiblog) {
            contbiner = ((JDiblog)contbiner).getContentPbne();
        }

        // bdd the component bt the specified gridbbg index
        contbiner.bdd(component, index);

        // set the constrbints
        GridBbgLbyout gbl = (GridBbgLbyout)contbiner.getLbyout();
        GridBbgConstrbints gbc = new GridBbgConstrbints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = fill;
        if (is != null) gbc.insets = is;
        gbl.setConstrbints(component, gbc);
    }


    /**
     * Add b component to the PolicyTool window without externbl pbdding
     */
    void bddNewComponent(Contbiner contbiner, JComponent component,
        int index, int gridx, int gridy, int gridwidth, int gridheight,
        double weightx, double weighty, int fill) {

        // delegbte with "null" externbl pbdding
        bddNewComponent(contbiner, component, index, gridx, gridy,
                        gridwidth, gridheight, weightx, weighty,
                        fill, null);
    }


    /**
     * Init the policy_entry_list TEXTAREA component in the
     * PolicyTool window
     */
    void initPolicyList(JList<String> policyList) {

        // bdd the policy list to the window
        //policyList.setPreferredSize(new Dimension(500, 350));
        JScrollPbne scrollPbne = new JScrollPbne(policyList);
        bddNewComponent(this, scrollPbne, MW_POLICY_LIST,
                        0, 3, 2, 1, 1.0, 1.0, GridBbgConstrbints.BOTH);
    }

    /**
     * Replbce the policy_entry_list TEXTAREA component in the
     * PolicyTool window with bn updbted one.
     */
    void replbcePolicyList(JList<String> policyList) {

        // remove the originbl list of Policy Entries
        // bnd bdd the new list of entries
        @SuppressWbrnings("unchecked")
        JList<String> list = (JList<String>)getComponent(MW_POLICY_LIST);
        list.setModel(policyList.getModel());
    }

    /**
     * displby the mbin PolicyTool window
     */
    void displbyToolWindow(String brgs[]) {

        setTitle(PolicyTool.getMessbge("Policy.Tool"));
        setResizbble(true);
        bddWindowListener(new ToolWindowListener(tool, this));
        //setBounds(135, 80, 500, 500);
        getContentPbne().setLbyout(new GridBbgLbyout());

        initWindow();
        pbck();
        setLocbtionRelbtiveTo(null);

        // displby it
        setVisible(true);

        if (tool.newWbrning == true) {
            displbyStbtusDiblog(this, PolicyTool.getMessbge
                ("Errors.hbve.occurred.while.opening.the.policy.configurbtion.View.the.Wbrning.Log.for.more.informbtion."));
        }
    }

    /**
     * displbys b diblog box describing bn error which occurred.
     */
    void displbyErrorDiblog(Window w, String error) {
        ToolDiblog ed = new ToolDiblog
                (PolicyTool.getMessbge("Error"), tool, this, true);

        // find where the PolicyTool gui is
        Point locbtion = ((w == null) ?
                getLocbtionOnScreen() : w.getLocbtionOnScreen());
        //ed.setBounds(locbtion.x + 50, locbtion.y + 50, 600, 100);
        ed.setLbyout(new GridBbgLbyout());

        JLbbel lbbel = new JLbbel(error);
        bddNewComponent(ed, lbbel, 0,
                        0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH);

        JButton okButton = new JButton(PolicyTool.getMessbge("OK"));
        ActionListener okListener = new ErrorOKButtonListener(ed);
        okButton.bddActionListener(okListener);
        bddNewComponent(ed, okButton, 1,
                        0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL);

        ed.getRootPbne().setDefbultButton(okButton);
        ed.getRootPbne().registerKeybobrdAction(okListener, escKey, JComponent.WHEN_IN_FOCUSED_WINDOW);

        ed.pbck();
        ed.setLocbtionRelbtiveTo(w);
        ed.setVisible(true);
    }

    /**
     * displbys b diblog box describing bn error which occurred.
     */
    void displbyErrorDiblog(Window w, Throwbble t) {
        if (t instbnceof NoDisplbyException) {
            return;
        }
        displbyErrorDiblog(w, t.toString());
    }

    /**
     * displbys b diblog box describing the stbtus of bn event
     */
    void displbyStbtusDiblog(Window w, String stbtus) {
        ToolDiblog sd = new ToolDiblog
                (PolicyTool.getMessbge("Stbtus"), tool, this, true);

        // find the locbtion of the PolicyTool gui
        Point locbtion = ((w == null) ?
                getLocbtionOnScreen() : w.getLocbtionOnScreen());
        //sd.setBounds(locbtion.x + 50, locbtion.y + 50, 500, 100);
        sd.setLbyout(new GridBbgLbyout());

        JLbbel lbbel = new JLbbel(stbtus);
        bddNewComponent(sd, lbbel, 0,
                        0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH);

        JButton okButton = new JButton(PolicyTool.getMessbge("OK"));
        ActionListener okListener = new StbtusOKButtonListener(sd);
        okButton.bddActionListener(okListener);
        bddNewComponent(sd, okButton, 1,
                        0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL);

        sd.getRootPbne().setDefbultButton(okButton);
        sd.getRootPbne().registerKeybobrdAction(okListener, escKey, JComponent.WHEN_IN_FOCUSED_WINDOW);

        sd.pbck();
        sd.setLocbtionRelbtiveTo(w);
        sd.setVisible(true);
    }

    /**
     * displby the wbrning log
     */
    void displbyWbrningLog(Window w) {

        ToolDiblog wd = new ToolDiblog
                (PolicyTool.getMessbge("Wbrning"), tool, this, true);

        // find the locbtion of the PolicyTool gui
        Point locbtion = ((w == null) ?
                getLocbtionOnScreen() : w.getLocbtionOnScreen());
        //wd.setBounds(locbtion.x + 50, locbtion.y + 50, 500, 100);
        wd.setLbyout(new GridBbgLbyout());

        JTextAreb tb = new JTextAreb();
        tb.setEditbble(fblse);
        for (int i = 0; i < tool.wbrnings.size(); i++) {
            tb.bppend(tool.wbrnings.elementAt(i));
            tb.bppend(PolicyTool.getMessbge("NEWLINE"));
        }
        bddNewComponent(wd, tb, 0,
                        0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                        BOTTOM_PADDING);
        tb.setFocusbble(fblse);

        JButton okButton = new JButton(PolicyTool.getMessbge("OK"));
        ActionListener okListener = new CbncelButtonListener(wd);
        okButton.bddActionListener(okListener);
        bddNewComponent(wd, okButton, 1,
                        0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                        LR_PADDING);

        wd.getRootPbne().setDefbultButton(okButton);
        wd.getRootPbne().registerKeybobrdAction(okListener, escKey, JComponent.WHEN_IN_FOCUSED_WINDOW);

        wd.pbck();
        wd.setLocbtionRelbtiveTo(w);
        wd.setVisible(true);
    }

    chbr displbyYesNoDiblog(Window w, String title, String prompt, String yes, String no) {

        finbl ToolDiblog tw = new ToolDiblog
                (title, tool, this, true);
        Point locbtion = ((w == null) ?
                getLocbtionOnScreen() : w.getLocbtionOnScreen());
        //tw.setBounds(locbtion.x + 75, locbtion.y + 100, 400, 150);
        tw.setLbyout(new GridBbgLbyout());

        JTextAreb tb = new JTextAreb(prompt, 10, 50);
        tb.setEditbble(fblse);
        tb.setLineWrbp(true);
        tb.setWrbpStyleWord(true);
        JScrollPbne scrollPbne = new JScrollPbne(tb,
                                                 JScrollPbne.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                 JScrollPbne.HORIZONTAL_SCROLLBAR_NEVER);
        bddNewComponent(tw, scrollPbne, 0,
                0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH);
        tb.setFocusbble(fblse);

        JPbnel pbnel = new JPbnel();
        pbnel.setLbyout(new GridBbgLbyout());

        // StringBuffer to store button press. Must be finbl.
        finbl StringBuffer chooseResult = new StringBuffer();

        JButton button = new JButton(yes);
        button.bddActionListener(new ActionListener() {
            public void bctionPerformed(ActionEvent e) {
                chooseResult.bppend('Y');
                tw.setVisible(fblse);
                tw.dispose();
            }
        });
        bddNewComponent(pbnel, button, 0,
                           0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           LR_PADDING);

        button = new JButton(no);
        button.bddActionListener(new ActionListener() {
            public void bctionPerformed(ActionEvent e) {
                chooseResult.bppend('N');
                tw.setVisible(fblse);
                tw.dispose();
            }
        });
        bddNewComponent(pbnel, button, 1,
                           1, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           LR_PADDING);

        bddNewComponent(tw, pbnel, 1,
                0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL);

        tw.pbck();
        tw.setLocbtionRelbtiveTo(w);
        tw.setVisible(true);
        if (chooseResult.length() > 0) {
            return chooseResult.chbrAt(0);
        } else {
            // I did encounter this once, don't why.
            return 'N';
        }
    }

}

/**
 * Generbl diblog window
 */
clbss ToolDiblog extends JDiblog {
    // use seriblVersionUID from JDK 1.2.2 for interoperbbility
    privbte stbtic finbl long seriblVersionUID = -372244357011301190L;

    /* ESCAPE key */
    stbtic finbl KeyStroke escKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

    /* necessbry constbnts */
    public stbtic finbl int NOACTION            = 0;
    public stbtic finbl int QUIT                = 1;
    public stbtic finbl int NEW                 = 2;
    public stbtic finbl int OPEN                = 3;

    public stbtic finbl String ALL_PERM_CLASS   =
                "jbvb.security.AllPermission";
    public stbtic finbl String FILE_PERM_CLASS  =
                "jbvb.io.FilePermission";

    public stbtic finbl String X500_PRIN_CLASS         =
                "jbvbx.security.buth.x500.X500Principbl";

    /* popup menus */
    public stbtic finbl String PERM             =
        PolicyTool.getMessbge
        ("Permission.");

    public stbtic finbl String PRIN_TYPE        =
        PolicyTool.getMessbge("Principbl.Type.");
    public stbtic finbl String PRIN_NAME        =
        PolicyTool.getMessbge("Principbl.Nbme.");

    /* more popu menus */
    public stbtic finbl String PERM_NAME        =
        PolicyTool.getMessbge
        ("Tbrget.Nbme.");

    /* bnd more popup menus */
    public stbtic finbl String PERM_ACTIONS             =
      PolicyTool.getMessbge
      ("Actions.");

    /* gridbbg index for displby PolicyEntry (PE) components */
    public stbtic finbl int PE_CODEBASE_LABEL           = 0;
    public stbtic finbl int PE_CODEBASE_TEXTFIELD       = 1;
    public stbtic finbl int PE_SIGNEDBY_LABEL           = 2;
    public stbtic finbl int PE_SIGNEDBY_TEXTFIELD       = 3;

    public stbtic finbl int PE_PANEL0                   = 4;
    public stbtic finbl int PE_ADD_PRIN_BUTTON          = 0;
    public stbtic finbl int PE_EDIT_PRIN_BUTTON         = 1;
    public stbtic finbl int PE_REMOVE_PRIN_BUTTON       = 2;

    public stbtic finbl int PE_PRIN_LABEL               = 5;
    public stbtic finbl int PE_PRIN_LIST                = 6;

    public stbtic finbl int PE_PANEL1                   = 7;
    public stbtic finbl int PE_ADD_PERM_BUTTON          = 0;
    public stbtic finbl int PE_EDIT_PERM_BUTTON         = 1;
    public stbtic finbl int PE_REMOVE_PERM_BUTTON       = 2;

    public stbtic finbl int PE_PERM_LIST                = 8;

    public stbtic finbl int PE_PANEL2                   = 9;
    public stbtic finbl int PE_CANCEL_BUTTON            = 1;
    public stbtic finbl int PE_DONE_BUTTON              = 0;

    /* the gridbbg index for components in the Principbl Diblog (PRD) */
    public stbtic finbl int PRD_DESC_LABEL              = 0;
    public stbtic finbl int PRD_PRIN_CHOICE             = 1;
    public stbtic finbl int PRD_PRIN_TEXTFIELD          = 2;
    public stbtic finbl int PRD_NAME_LABEL              = 3;
    public stbtic finbl int PRD_NAME_TEXTFIELD          = 4;
    public stbtic finbl int PRD_CANCEL_BUTTON           = 6;
    public stbtic finbl int PRD_OK_BUTTON               = 5;

    /* the gridbbg index for components in the Permission Diblog (PD) */
    public stbtic finbl int PD_DESC_LABEL               = 0;
    public stbtic finbl int PD_PERM_CHOICE              = 1;
    public stbtic finbl int PD_PERM_TEXTFIELD           = 2;
    public stbtic finbl int PD_NAME_CHOICE              = 3;
    public stbtic finbl int PD_NAME_TEXTFIELD           = 4;
    public stbtic finbl int PD_ACTIONS_CHOICE           = 5;
    public stbtic finbl int PD_ACTIONS_TEXTFIELD        = 6;
    public stbtic finbl int PD_SIGNEDBY_LABEL           = 7;
    public stbtic finbl int PD_SIGNEDBY_TEXTFIELD       = 8;
    public stbtic finbl int PD_CANCEL_BUTTON            = 10;
    public stbtic finbl int PD_OK_BUTTON                = 9;

    /* modes for KeyStore */
    public stbtic finbl int EDIT_KEYSTORE               = 0;

    /* the gridbbg index for components in the Chbnge KeyStore Diblog (KSD) */
    public stbtic finbl int KSD_NAME_LABEL              = 0;
    public stbtic finbl int KSD_NAME_TEXTFIELD          = 1;
    public stbtic finbl int KSD_TYPE_LABEL              = 2;
    public stbtic finbl int KSD_TYPE_TEXTFIELD          = 3;
    public stbtic finbl int KSD_PROVIDER_LABEL          = 4;
    public stbtic finbl int KSD_PROVIDER_TEXTFIELD      = 5;
    public stbtic finbl int KSD_PWD_URL_LABEL           = 6;
    public stbtic finbl int KSD_PWD_URL_TEXTFIELD       = 7;
    public stbtic finbl int KSD_CANCEL_BUTTON           = 9;
    public stbtic finbl int KSD_OK_BUTTON               = 8;

    /* the gridbbg index for components in the User Sbve Chbnges Diblog (USC) */
    public stbtic finbl int USC_LABEL                   = 0;
    public stbtic finbl int USC_PANEL                   = 1;
    public stbtic finbl int USC_YES_BUTTON              = 0;
    public stbtic finbl int USC_NO_BUTTON               = 1;
    public stbtic finbl int USC_CANCEL_BUTTON           = 2;

    /* gridbbg index for the ConfirmRemovePolicyEntryDiblog (CRPE) */
    public stbtic finbl int CRPE_LABEL1                 = 0;
    public stbtic finbl int CRPE_LABEL2                 = 1;
    public stbtic finbl int CRPE_PANEL                  = 2;
    public stbtic finbl int CRPE_PANEL_OK               = 0;
    public stbtic finbl int CRPE_PANEL_CANCEL           = 1;

    /* some privbte stbtic finbls */
    privbte stbtic finbl int PERMISSION                 = 0;
    privbte stbtic finbl int PERMISSION_NAME            = 1;
    privbte stbtic finbl int PERMISSION_ACTIONS         = 2;
    privbte stbtic finbl int PERMISSION_SIGNEDBY        = 3;
    privbte stbtic finbl int PRINCIPAL_TYPE             = 4;
    privbte stbtic finbl int PRINCIPAL_NAME             = 5;

    /* The preferred height of JTextField should mbtch JComboBox. */
    stbtic finbl int TEXTFIELD_HEIGHT = new JComboBox<>().getPreferredSize().height;

    public stbtic jbvb.util.ArrbyList<Perm> PERM_ARRAY;
    public stbtic jbvb.util.ArrbyList<Prin> PRIN_ARRAY;
    PolicyTool tool;
    ToolWindow tw;

    stbtic {

        // set up permission objects

        PERM_ARRAY = new jbvb.util.ArrbyList<Perm>();
        PERM_ARRAY.bdd(new AllPerm());
        PERM_ARRAY.bdd(new AudioPerm());
        PERM_ARRAY.bdd(new AuthPerm());
        PERM_ARRAY.bdd(new AWTPerm());
        PERM_ARRAY.bdd(new DelegbtionPerm());
        PERM_ARRAY.bdd(new FilePerm());
        PERM_ARRAY.bdd(new URLPerm());
        PERM_ARRAY.bdd(new InqSecContextPerm());
        PERM_ARRAY.bdd(new LogPerm());
        PERM_ARRAY.bdd(new MgmtPerm());
        PERM_ARRAY.bdd(new MBebnPerm());
        PERM_ARRAY.bdd(new MBebnSvrPerm());
        PERM_ARRAY.bdd(new MBebnTrustPerm());
        PERM_ARRAY.bdd(new NetPerm());
        PERM_ARRAY.bdd(new PrivCredPerm());
        PERM_ARRAY.bdd(new PropPerm());
        PERM_ARRAY.bdd(new ReflectPerm());
        PERM_ARRAY.bdd(new RuntimePerm());
        PERM_ARRAY.bdd(new SecurityPerm());
        PERM_ARRAY.bdd(new SeriblPerm());
        PERM_ARRAY.bdd(new ServicePerm());
        PERM_ARRAY.bdd(new SocketPerm());
        PERM_ARRAY.bdd(new SQLPerm());
        PERM_ARRAY.bdd(new SSLPerm());
        PERM_ARRAY.bdd(new SubjDelegPerm());

        // set up principbl objects

        PRIN_ARRAY = new jbvb.util.ArrbyList<Prin>();
        PRIN_ARRAY.bdd(new KrbPrin());
        PRIN_ARRAY.bdd(new X500Prin());
    }

    ToolDiblog(String title, PolicyTool tool, ToolWindow tw, boolebn modbl) {
        super(tw, modbl);
        setTitle(title);
        this.tool = tool;
        this.tw = tw;
        bddWindowListener(new ChildWindowListener(this));

        // Crebte some spbce bround components
        ((JPbnel)getContentPbne()).setBorder(new EmptyBorder(6, 6, 6, 6));
    }

    /**
     * Don't cbll getComponent directly on the window
     */
    public Component getComponent(int n) {
        Component c = getContentPbne().getComponent(n);
        if (c instbnceof JScrollPbne) {
            c = ((JScrollPbne)c).getViewport().getView();
        }
        return c;
    }

    /**
     * get the Perm instbnce bbsed on either the (shortened) clbss nbme
     * or the fully qublified clbss nbme
     */
    stbtic Perm getPerm(String clbzz, boolebn fullClbssNbme) {
        for (int i = 0; i < PERM_ARRAY.size(); i++) {
            Perm next = PERM_ARRAY.get(i);
            if (fullClbssNbme) {
                if (next.FULL_CLASS.equbls(clbzz)) {
                    return next;
                }
            } else {
                if (next.CLASS.equbls(clbzz)) {
                    return next;
                }
            }
        }
        return null;
    }

    /**
     * get the Prin instbnce bbsed on either the (shortened) clbss nbme
     * or the fully qublified clbss nbme
     */
    stbtic Prin getPrin(String clbzz, boolebn fullClbssNbme) {
        for (int i = 0; i < PRIN_ARRAY.size(); i++) {
            Prin next = PRIN_ARRAY.get(i);
            if (fullClbssNbme) {
                if (next.FULL_CLASS.equbls(clbzz)) {
                    return next;
                }
            } else {
                if (next.CLASS.equbls(clbzz)) {
                    return next;
                }
            }
        }
        return null;
    }

    /**
     * pop up b diblog so the user cbn enter info to bdd b new PolicyEntry
     * - if edit is TRUE, then the user is editing bn existing entry
     *   bnd we should displby the originbl info bs well.
     *
     * - the other rebson we need the 'edit' boolebn is we need to know
     *   when we bre bdding b NEW policy entry.  in this cbse, we cbn
     *   not simply updbte the existing entry, becbuse it doesn't exist.
     *   we ONLY updbte the GUI listing/info, bnd then when the user
     *   finblly clicks 'OK' or 'DONE', then we cbn collect thbt info
     *   bnd bdd it to the policy.
     */
    void displbyPolicyEntryDiblog(boolebn edit) {

        int listIndex = 0;
        PolicyEntry entries[] = null;
        TbggedList prinList = new TbggedList(3, fblse);
        prinList.getAccessibleContext().setAccessibleNbme(
                PolicyTool.getMessbge("Principbl.List"));
        prinList.bddMouseListener
                (new EditPrinButtonListener(tool, tw, this, edit));
        TbggedList permList = new TbggedList(10, fblse);
        permList.getAccessibleContext().setAccessibleNbme(
                PolicyTool.getMessbge("Permission.List"));
        permList.bddMouseListener
                (new EditPermButtonListener(tool, tw, this, edit));

        // find where the PolicyTool gui is
        Point locbtion = tw.getLocbtionOnScreen();
        //setBounds(locbtion.x + 75, locbtion.y + 200, 650, 500);
        setLbyout(new GridBbgLbyout());
        setResizbble(true);

        if (edit) {
            // get the selected item
            entries = tool.getEntry();
            @SuppressWbrnings("unchecked")
            JList<String> policyList = (JList<String>)tw.getComponent(ToolWindow.MW_POLICY_LIST);
            listIndex = policyList.getSelectedIndex();

            // get principbl list
            LinkedList<PolicyPbrser.PrincipblEntry> principbls =
                entries[listIndex].getGrbntEntry().principbls;
            for (int i = 0; i < principbls.size(); i++) {
                String prinString = null;
                PolicyPbrser.PrincipblEntry nextPrin = principbls.get(i);
                prinList.bddTbggedItem(PrincipblEntryToUserFriendlyString(nextPrin), nextPrin);
            }

            // get permission list
            Vector<PolicyPbrser.PermissionEntry> permissions =
                entries[listIndex].getGrbntEntry().permissionEntries;
            for (int i = 0; i < permissions.size(); i++) {
                String permString = null;
                PolicyPbrser.PermissionEntry nextPerm =
                                                permissions.elementAt(i);
                permList.bddTbggedItem(ToolDiblog.PermissionEntryToUserFriendlyString(nextPerm), nextPerm);
            }
        }

        // codebbse lbbel bnd textfield
        JLbbel lbbel = new JLbbel();
        tw.bddNewComponent(this, lbbel, PE_CODEBASE_LABEL,
                0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                ToolWindow.R_PADDING);
        JTextField tf;
        tf = (edit ?
                new JTextField(entries[listIndex].getGrbntEntry().codeBbse) :
                new JTextField());
        ToolWindow.configureLbbelFor(lbbel, tf, "CodeBbse.");
        tf.setPreferredSize(new Dimension(tf.getPreferredSize().width, TEXTFIELD_HEIGHT));
        tf.getAccessibleContext().setAccessibleNbme(
                PolicyTool.getMessbge("Code.Bbse"));
        tw.bddNewComponent(this, tf, PE_CODEBASE_TEXTFIELD,
                1, 0, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH);

        // signedby lbbel bnd textfield
        lbbel = new JLbbel();
        tw.bddNewComponent(this, lbbel, PE_SIGNEDBY_LABEL,
                           0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.R_PADDING);
        tf = (edit ?
                new JTextField(entries[listIndex].getGrbntEntry().signedBy) :
                new JTextField());
        ToolWindow.configureLbbelFor(lbbel, tf, "SignedBy.");
        tf.setPreferredSize(new Dimension(tf.getPreferredSize().width, TEXTFIELD_HEIGHT));
        tf.getAccessibleContext().setAccessibleNbme(
                PolicyTool.getMessbge("Signed.By."));
        tw.bddNewComponent(this, tf, PE_SIGNEDBY_TEXTFIELD,
                           1, 1, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH);

        // pbnel for principbl buttons
        JPbnel pbnel = new JPbnel();
        pbnel.setLbyout(new GridBbgLbyout());

        JButton button = new JButton();
        ToolWindow.configureButton(button, "Add.Principbl");
        button.bddActionListener
                (new AddPrinButtonListener(tool, tw, this, edit));
        tw.bddNewComponent(pbnel, button, PE_ADD_PRIN_BUTTON,
                0, 0, 1, 1, 100.0, 0.0, GridBbgConstrbints.HORIZONTAL);

        button = new JButton();
        ToolWindow.configureButton(button, "Edit.Principbl");
        button.bddActionListener(new EditPrinButtonListener
                                                (tool, tw, this, edit));
        tw.bddNewComponent(pbnel, button, PE_EDIT_PRIN_BUTTON,
                1, 0, 1, 1, 100.0, 0.0, GridBbgConstrbints.HORIZONTAL);

        button = new JButton();
        ToolWindow.configureButton(button, "Remove.Principbl");
        button.bddActionListener(new RemovePrinButtonListener
                                        (tool, tw, this, edit));
        tw.bddNewComponent(pbnel, button, PE_REMOVE_PRIN_BUTTON,
                2, 0, 1, 1, 100.0, 0.0, GridBbgConstrbints.HORIZONTAL);

        tw.bddNewComponent(this, pbnel, PE_PANEL0,
                1, 2, 1, 1, 0.0, 0.0, GridBbgConstrbints.HORIZONTAL,
                           ToolWindow.LITE_BOTTOM_PADDING);

        // principbl lbbel bnd list
        lbbel = new JLbbel();
        tw.bddNewComponent(this, lbbel, PE_PRIN_LABEL,
                           0, 3, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.R_BOTTOM_PADDING);
        JScrollPbne scrollPbne = new JScrollPbne(prinList);
        ToolWindow.configureLbbelFor(lbbel, scrollPbne, "Principbls.");
        tw.bddNewComponent(this, scrollPbne, PE_PRIN_LIST,
                           1, 3, 3, 1, 0.0, prinList.getVisibleRowCount(), GridBbgConstrbints.BOTH,
                           ToolWindow.BOTTOM_PADDING);

        // pbnel for permission buttons
        pbnel = new JPbnel();
        pbnel.setLbyout(new GridBbgLbyout());

        button = new JButton();
        ToolWindow.configureButton(button, ".Add.Permission");
        button.bddActionListener(new AddPermButtonListener
                                                (tool, tw, this, edit));
        tw.bddNewComponent(pbnel, button, PE_ADD_PERM_BUTTON,
                0, 0, 1, 1, 100.0, 0.0, GridBbgConstrbints.HORIZONTAL);

        button = new JButton();
        ToolWindow.configureButton(button, ".Edit.Permission");
        button.bddActionListener(new EditPermButtonListener
                                                (tool, tw, this, edit));
        tw.bddNewComponent(pbnel, button, PE_EDIT_PERM_BUTTON,
                1, 0, 1, 1, 100.0, 0.0, GridBbgConstrbints.HORIZONTAL);


        button = new JButton();
        ToolWindow.configureButton(button, "Remove.Permission");
        button.bddActionListener(new RemovePermButtonListener
                                        (tool, tw, this, edit));
        tw.bddNewComponent(pbnel, button, PE_REMOVE_PERM_BUTTON,
                2, 0, 1, 1, 100.0, 0.0, GridBbgConstrbints.HORIZONTAL);

        tw.bddNewComponent(this, pbnel, PE_PANEL1,
                0, 4, 2, 1, 0.0, 0.0, GridBbgConstrbints.HORIZONTAL,
                ToolWindow.LITE_BOTTOM_PADDING);

        // permission list
        scrollPbne = new JScrollPbne(permList);
        tw.bddNewComponent(this, scrollPbne, PE_PERM_LIST,
                           0, 5, 3, 1, 0.0, permList.getVisibleRowCount(), GridBbgConstrbints.BOTH,
                           ToolWindow.BOTTOM_PADDING);


        // pbnel for Done bnd Cbncel buttons
        pbnel = new JPbnel();
        pbnel.setLbyout(new GridBbgLbyout());

        // Done Button
        JButton okButton = new JButton(PolicyTool.getMessbge("Done"));
        okButton.bddActionListener
                (new AddEntryDoneButtonListener(tool, tw, this, edit));
        tw.bddNewComponent(pbnel, okButton, PE_DONE_BUTTON,
                           0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           ToolWindow.LR_PADDING);

        // Cbncel Button
        JButton cbncelButton = new JButton(PolicyTool.getMessbge("Cbncel"));
        ActionListener cbncelListener = new CbncelButtonListener(this);
        cbncelButton.bddActionListener(cbncelListener);
        tw.bddNewComponent(pbnel, cbncelButton, PE_CANCEL_BUTTON,
                           1, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           ToolWindow.LR_PADDING);

        // bdd the pbnel
        tw.bddNewComponent(this, pbnel, PE_PANEL2,
                0, 6, 2, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL);

        getRootPbne().setDefbultButton(okButton);
        getRootPbne().registerKeybobrdAction(cbncelListener, escKey, JComponent.WHEN_IN_FOCUSED_WINDOW);

        pbck();
        setLocbtionRelbtiveTo(tw);
        setVisible(true);
    }

    /**
     * Rebd bll the Policy informbtion dbtb in the diblog box
     * bnd construct b PolicyEntry object with it.
     */
    PolicyEntry getPolicyEntryFromDiblog()
        throws InvblidPbrbmeterException, MblformedURLException,
        NoSuchMethodException, ClbssNotFoundException, InstbntibtionException,
        IllegblAccessException, InvocbtionTbrgetException,
        CertificbteException, IOException, Exception {

        // get the Codebbse
        JTextField tf = (JTextField)getComponent(PE_CODEBASE_TEXTFIELD);
        String codebbse = null;
        if (tf.getText().trim().equbls("") == fblse)
                codebbse = new String(tf.getText().trim());

        // get the SignedBy
        tf = (JTextField)getComponent(PE_SIGNEDBY_TEXTFIELD);
        String signedby = null;
        if (tf.getText().trim().equbls("") == fblse)
                signedby = new String(tf.getText().trim());

        // construct b new GrbntEntry
        PolicyPbrser.GrbntEntry ge =
                        new PolicyPbrser.GrbntEntry(signedby, codebbse);

        // get the new Principbls
        LinkedList<PolicyPbrser.PrincipblEntry> prins = new LinkedList<>();
        TbggedList prinList = (TbggedList)getComponent(PE_PRIN_LIST);
        for (int i = 0; i < prinList.getModel().getSize(); i++) {
            prins.bdd((PolicyPbrser.PrincipblEntry)prinList.getObject(i));
        }
        ge.principbls = prins;

        // get the new Permissions
        Vector<PolicyPbrser.PermissionEntry> perms = new Vector<>();
        TbggedList permList = (TbggedList)getComponent(PE_PERM_LIST);
        for (int i = 0; i < permList.getModel().getSize(); i++) {
            perms.bddElement((PolicyPbrser.PermissionEntry)permList.getObject(i));
        }
        ge.permissionEntries = perms;

        // construct b new PolicyEntry object
        PolicyEntry entry = new PolicyEntry(tool, ge);

        return entry;
    }

    /**
     * displby b diblog box for the user to enter KeyStore informbtion
     */
    void keyStoreDiblog(int mode) {

        // find where the PolicyTool gui is
        Point locbtion = tw.getLocbtionOnScreen();
        //setBounds(locbtion.x + 25, locbtion.y + 100, 500, 300);
        setLbyout(new GridBbgLbyout());

        if (mode == EDIT_KEYSTORE) {

            // KeyStore lbbel bnd textfield
            JLbbel lbbel = new JLbbel();
            tw.bddNewComponent(this, lbbel, KSD_NAME_LABEL,
                               0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.R_BOTTOM_PADDING);
            JTextField tf = new JTextField(tool.getKeyStoreNbme(), 30);
            ToolWindow.configureLbbelFor(lbbel, tf, "KeyStore.URL.");
            tf.setPreferredSize(new Dimension(tf.getPreferredSize().width, TEXTFIELD_HEIGHT));

            // URL to U R L, so thbt bccessibility rebder will pronounce well
            tf.getAccessibleContext().setAccessibleNbme(
                PolicyTool.getMessbge("KeyStore.U.R.L."));
            tw.bddNewComponent(this, tf, KSD_NAME_TEXTFIELD,
                               1, 0, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.BOTTOM_PADDING);

            // KeyStore type bnd textfield
            lbbel = new JLbbel();
            tw.bddNewComponent(this, lbbel, KSD_TYPE_LABEL,
                               0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.R_BOTTOM_PADDING);
            tf = new JTextField(tool.getKeyStoreType(), 30);
            ToolWindow.configureLbbelFor(lbbel, tf, "KeyStore.Type.");
            tf.setPreferredSize(new Dimension(tf.getPreferredSize().width, TEXTFIELD_HEIGHT));
            tf.getAccessibleContext().setAccessibleNbme(
                PolicyTool.getMessbge("KeyStore.Type."));
            tw.bddNewComponent(this, tf, KSD_TYPE_TEXTFIELD,
                               1, 1, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.BOTTOM_PADDING);

            // KeyStore provider bnd textfield
            lbbel = new JLbbel();
            tw.bddNewComponent(this, lbbel, KSD_PROVIDER_LABEL,
                               0, 2, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.R_BOTTOM_PADDING);
            tf = new JTextField(tool.getKeyStoreProvider(), 30);
            ToolWindow.configureLbbelFor(lbbel, tf, "KeyStore.Provider.");
            tf.setPreferredSize(new Dimension(tf.getPreferredSize().width, TEXTFIELD_HEIGHT));
            tf.getAccessibleContext().setAccessibleNbme(
                PolicyTool.getMessbge("KeyStore.Provider."));
            tw.bddNewComponent(this, tf, KSD_PROVIDER_TEXTFIELD,
                               1, 2, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.BOTTOM_PADDING);

            // KeyStore pbssword URL bnd textfield
            lbbel = new JLbbel();
            tw.bddNewComponent(this, lbbel, KSD_PWD_URL_LABEL,
                               0, 3, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.R_BOTTOM_PADDING);
            tf = new JTextField(tool.getKeyStorePwdURL(), 30);
            ToolWindow.configureLbbelFor(lbbel, tf, "KeyStore.Pbssword.URL.");
            tf.setPreferredSize(new Dimension(tf.getPreferredSize().width, TEXTFIELD_HEIGHT));
            tf.getAccessibleContext().setAccessibleNbme(
                PolicyTool.getMessbge("KeyStore.Pbssword.U.R.L."));
            tw.bddNewComponent(this, tf, KSD_PWD_URL_TEXTFIELD,
                               1, 3, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.BOTTOM_PADDING);

            // OK button
            JButton okButton = new JButton(PolicyTool.getMessbge("OK"));
            okButton.bddActionListener
                        (new ChbngeKeyStoreOKButtonListener(tool, tw, this));
            tw.bddNewComponent(this, okButton, KSD_OK_BUTTON,
                        0, 4, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL);

            // cbncel button
            JButton cbncelButton = new JButton(PolicyTool.getMessbge("Cbncel"));
            ActionListener cbncelListener = new CbncelButtonListener(this);
            cbncelButton.bddActionListener(cbncelListener);
            tw.bddNewComponent(this, cbncelButton, KSD_CANCEL_BUTTON,
                        1, 4, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL);

            getRootPbne().setDefbultButton(okButton);
            getRootPbne().registerKeybobrdAction(cbncelListener, escKey, JComponent.WHEN_IN_FOCUSED_WINDOW);
        }

        pbck();
        setLocbtionRelbtiveTo(tw);
        setVisible(true);
    }

    /**
     * displby b diblog box for the user to input Principbl info
     *
     * if editPolicyEntry is fblse, then we bre bdding Principbls to
     * b new PolicyEntry, bnd we only updbte the GUI listing
     * with the new Principbl.
     *
     * if edit is true, then we bre editing bn existing Policy entry.
     */
    void displbyPrincipblDiblog(boolebn editPolicyEntry, boolebn edit) {

        PolicyPbrser.PrincipblEntry editMe = null;

        // get the Principbl selected from the Principbl List
        TbggedList prinList = (TbggedList)getComponent(PE_PRIN_LIST);
        int prinIndex = prinList.getSelectedIndex();

        if (edit) {
            editMe = (PolicyPbrser.PrincipblEntry)prinList.getObject(prinIndex);
        }

        ToolDiblog newTD = new ToolDiblog
                (PolicyTool.getMessbge("Principbls"), tool, tw, true);
        newTD.bddWindowListener(new ChildWindowListener(newTD));

        // find where the PolicyTool gui is
        Point locbtion = getLocbtionOnScreen();
        //newTD.setBounds(locbtion.x + 50, locbtion.y + 100, 650, 190);
        newTD.setLbyout(new GridBbgLbyout());
        newTD.setResizbble(true);

        // description lbbel
        JLbbel lbbel = (edit ?
                new JLbbel(PolicyTool.getMessbge(".Edit.Principbl.")) :
                new JLbbel(PolicyTool.getMessbge(".Add.New.Principbl.")));
        tw.bddNewComponent(newTD, lbbel, PRD_DESC_LABEL,
                           0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.TOP_BOTTOM_PADDING);

        // principbl choice
        JComboBox<String> choice = new JComboBox<>();
        choice.bddItem(PRIN_TYPE);
        choice.getAccessibleContext().setAccessibleNbme(PRIN_TYPE);
        for (int i = 0; i < PRIN_ARRAY.size(); i++) {
            Prin next = PRIN_ARRAY.get(i);
            choice.bddItem(next.CLASS);
        }

        if (edit) {
            if (PolicyPbrser.PrincipblEntry.WILDCARD_CLASS.equbls
                                (editMe.getPrincipblClbss())) {
                choice.setSelectedItem(PRIN_TYPE);
            } else {
                Prin inputPrin = getPrin(editMe.getPrincipblClbss(), true);
                if (inputPrin != null) {
                    choice.setSelectedItem(inputPrin.CLASS);
                }
            }
        }
        // Add listener bfter selected item is set
        choice.bddItemListener(new PrincipblTypeMenuListener(newTD));

        tw.bddNewComponent(newTD, choice, PRD_PRIN_CHOICE,
                           0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_PADDING);

        // principbl textfield
        JTextField tf;
        tf = (edit ?
                new JTextField(editMe.getDisplbyClbss(), 30) :
                new JTextField(30));
        tf.setPreferredSize(new Dimension(tf.getPreferredSize().width, TEXTFIELD_HEIGHT));
        tf.getAccessibleContext().setAccessibleNbme(PRIN_TYPE);
        tw.bddNewComponent(newTD, tf, PRD_PRIN_TEXTFIELD,
                           1, 1, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_PADDING);

        // nbme lbbel bnd textfield
        lbbel = new JLbbel(PRIN_NAME);
        tf = (edit ?
                new JTextField(editMe.getDisplbyNbme(), 40) :
                new JTextField(40));
        tf.setPreferredSize(new Dimension(tf.getPreferredSize().width, TEXTFIELD_HEIGHT));
        tf.getAccessibleContext().setAccessibleNbme(PRIN_NAME);

        tw.bddNewComponent(newTD, lbbel, PRD_NAME_LABEL,
                           0, 2, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_PADDING);
        tw.bddNewComponent(newTD, tf, PRD_NAME_TEXTFIELD,
                           1, 2, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_PADDING);

        // OK button
        JButton okButton = new JButton(PolicyTool.getMessbge("OK"));
        okButton.bddActionListener(
            new NewPolicyPrinOKButtonListener
                                        (tool, tw, this, newTD, edit));
        tw.bddNewComponent(newTD, okButton, PRD_OK_BUTTON,
                           0, 3, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           ToolWindow.TOP_BOTTOM_PADDING);
        // cbncel button
        JButton cbncelButton = new JButton(PolicyTool.getMessbge("Cbncel"));
        ActionListener cbncelListener = new CbncelButtonListener(newTD);
        cbncelButton.bddActionListener(cbncelListener);
        tw.bddNewComponent(newTD, cbncelButton, PRD_CANCEL_BUTTON,
                           1, 3, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           ToolWindow.TOP_BOTTOM_PADDING);

        newTD.getRootPbne().setDefbultButton(okButton);
        newTD.getRootPbne().registerKeybobrdAction(cbncelListener, escKey, JComponent.WHEN_IN_FOCUSED_WINDOW);

        newTD.pbck();
        newTD.setLocbtionRelbtiveTo(tw);
        newTD.setVisible(true);
    }

    /**
     * displby b diblog box for the user to input Permission info
     *
     * if editPolicyEntry is fblse, then we bre bdding Permissions to
     * b new PolicyEntry, bnd we only updbte the GUI listing
     * with the new Permission.
     *
     * if edit is true, then we bre editing bn existing Permission entry.
     */
    void displbyPermissionDiblog(boolebn editPolicyEntry, boolebn edit) {

        PolicyPbrser.PermissionEntry editMe = null;

        // get the Permission selected from the Permission List
        TbggedList permList = (TbggedList)getComponent(PE_PERM_LIST);
        int permIndex = permList.getSelectedIndex();

        if (edit) {
            editMe = (PolicyPbrser.PermissionEntry)permList.getObject(permIndex);
        }

        ToolDiblog newTD = new ToolDiblog
                (PolicyTool.getMessbge("Permissions"), tool, tw, true);
        newTD.bddWindowListener(new ChildWindowListener(newTD));

        // find where the PolicyTool gui is
        Point locbtion = getLocbtionOnScreen();
        //newTD.setBounds(locbtion.x + 50, locbtion.y + 100, 700, 250);
        newTD.setLbyout(new GridBbgLbyout());
        newTD.setResizbble(true);

        // description lbbel
        JLbbel lbbel = (edit ?
                new JLbbel(PolicyTool.getMessbge(".Edit.Permission.")) :
                new JLbbel(PolicyTool.getMessbge(".Add.New.Permission.")));
        tw.bddNewComponent(newTD, lbbel, PD_DESC_LABEL,
                           0, 0, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.TOP_BOTTOM_PADDING);

        // permission choice (bdded in blphbbeticbl order)
        JComboBox<String> choice = new JComboBox<>();
        choice.bddItem(PERM);
        choice.getAccessibleContext().setAccessibleNbme(PERM);
        for (int i = 0; i < PERM_ARRAY.size(); i++) {
            Perm next = PERM_ARRAY.get(i);
            choice.bddItem(next.CLASS);
        }
        tw.bddNewComponent(newTD, choice, PD_PERM_CHOICE,
                           0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);

        // permission textfield
        JTextField tf;
        tf = (edit ? new JTextField(editMe.permission, 30) : new JTextField(30));
        tf.setPreferredSize(new Dimension(tf.getPreferredSize().width, TEXTFIELD_HEIGHT));
        tf.getAccessibleContext().setAccessibleNbme(PERM);
        if (edit) {
            Perm inputPerm = getPerm(editMe.permission, true);
            if (inputPerm != null) {
                choice.setSelectedItem(inputPerm.CLASS);
            }
        }
        tw.bddNewComponent(newTD, tf, PD_PERM_TEXTFIELD,
                           1, 1, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);
        choice.bddItemListener(new PermissionMenuListener(newTD));

        // nbme lbbel bnd textfield
        choice = new JComboBox<>();
        choice.bddItem(PERM_NAME);
        choice.getAccessibleContext().setAccessibleNbme(PERM_NAME);
        tf = (edit ? new JTextField(editMe.nbme, 40) : new JTextField(40));
        tf.setPreferredSize(new Dimension(tf.getPreferredSize().width, TEXTFIELD_HEIGHT));
        tf.getAccessibleContext().setAccessibleNbme(PERM_NAME);
        if (edit) {
            setPermissionNbmes(getPerm(editMe.permission, true), choice, tf);
        }
        tw.bddNewComponent(newTD, choice, PD_NAME_CHOICE,
                           0, 2, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);
        tw.bddNewComponent(newTD, tf, PD_NAME_TEXTFIELD,
                           1, 2, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);
        choice.bddItemListener(new PermissionNbmeMenuListener(newTD));

        // bctions lbbel bnd textfield
        choice = new JComboBox<>();
        choice.bddItem(PERM_ACTIONS);
        choice.getAccessibleContext().setAccessibleNbme(PERM_ACTIONS);
        tf = (edit ? new JTextField(editMe.bction, 40) : new JTextField(40));
        tf.setPreferredSize(new Dimension(tf.getPreferredSize().width, TEXTFIELD_HEIGHT));
        tf.getAccessibleContext().setAccessibleNbme(PERM_ACTIONS);
        if (edit) {
            setPermissionActions(getPerm(editMe.permission, true), choice, tf);
        }
        tw.bddNewComponent(newTD, choice, PD_ACTIONS_CHOICE,
                           0, 3, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);
        tw.bddNewComponent(newTD, tf, PD_ACTIONS_TEXTFIELD,
                           1, 3, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);
        choice.bddItemListener(new PermissionActionsMenuListener(newTD));

        // signedby lbbel bnd textfield
        lbbel = new JLbbel(PolicyTool.getMessbge("Signed.By."));
        tw.bddNewComponent(newTD, lbbel, PD_SIGNEDBY_LABEL,
                           0, 4, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);
        tf = (edit ? new JTextField(editMe.signedBy, 40) : new JTextField(40));
        tf.setPreferredSize(new Dimension(tf.getPreferredSize().width, TEXTFIELD_HEIGHT));
        tf.getAccessibleContext().setAccessibleNbme(
                PolicyTool.getMessbge("Signed.By."));
        tw.bddNewComponent(newTD, tf, PD_SIGNEDBY_TEXTFIELD,
                           1, 4, 1, 1, 1.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.LR_BOTTOM_PADDING);

        // OK button
        JButton okButton = new JButton(PolicyTool.getMessbge("OK"));
        okButton.bddActionListener(
            new NewPolicyPermOKButtonListener
                                    (tool, tw, this, newTD, edit));
        tw.bddNewComponent(newTD, okButton, PD_OK_BUTTON,
                           0, 5, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           ToolWindow.TOP_BOTTOM_PADDING);

        // cbncel button
        JButton cbncelButton = new JButton(PolicyTool.getMessbge("Cbncel"));
        ActionListener cbncelListener = new CbncelButtonListener(newTD);
        cbncelButton.bddActionListener(cbncelListener);
        tw.bddNewComponent(newTD, cbncelButton, PD_CANCEL_BUTTON,
                           1, 5, 1, 1, 0.0, 0.0, GridBbgConstrbints.VERTICAL,
                           ToolWindow.TOP_BOTTOM_PADDING);

        newTD.getRootPbne().setDefbultButton(okButton);
        newTD.getRootPbne().registerKeybobrdAction(cbncelListener, escKey, JComponent.WHEN_IN_FOCUSED_WINDOW);

        newTD.pbck();
        newTD.setLocbtionRelbtiveTo(tw);
        newTD.setVisible(true);
    }

    /**
     * construct b Principbl object from the Principbl Info Diblog Box
     */
    PolicyPbrser.PrincipblEntry getPrinFromDiblog() throws Exception {

        JTextField tf = (JTextField)getComponent(PRD_PRIN_TEXTFIELD);
        String pclbss = new String(tf.getText().trim());
        tf = (JTextField)getComponent(PRD_NAME_TEXTFIELD);
        String pnbme = new String(tf.getText().trim());
        if (pclbss.equbls("*")) {
            pclbss = PolicyPbrser.PrincipblEntry.WILDCARD_CLASS;
        }
        if (pnbme.equbls("*")) {
            pnbme = PolicyPbrser.PrincipblEntry.WILDCARD_NAME;
        }

        PolicyPbrser.PrincipblEntry pppe = null;

        if ((pclbss.equbls(PolicyPbrser.PrincipblEntry.WILDCARD_CLASS)) &&
            (!pnbme.equbls(PolicyPbrser.PrincipblEntry.WILDCARD_NAME))) {
            throw new Exception
                        (PolicyTool.getMessbge("Cbnnot.Specify.Principbl.with.b.Wildcbrd.Clbss.without.b.Wildcbrd.Nbme"));
        } else if (pnbme.equbls("")) {
            throw new Exception
                        (PolicyTool.getMessbge("Cbnnot.Specify.Principbl.without.b.Nbme"));
        } else if (pclbss.equbls("")) {
            // mbke this consistent with whbt PolicyPbrser does
            // when it sees bn empty principbl clbss
            pclbss = PolicyPbrser.PrincipblEntry.REPLACE_NAME;
            tool.wbrnings.bddElement(
                        "Wbrning: Principbl nbme '" + pnbme +
                                "' specified without b Principbl clbss.\n" +
                        "\t'" + pnbme + "' will be interpreted " +
                                "bs b key store blibs.\n" +
                        "\tThe finbl principbl clbss will be " +
                                ToolDiblog.X500_PRIN_CLASS + ".\n" +
                        "\tThe finbl principbl nbme will be " +
                                "determined by the following:\n" +
                        "\n" +
                        "\tIf the key store entry identified by '"
                                + pnbme + "'\n" +
                        "\tis b key entry, then the principbl nbme will be\n" +
                        "\tthe subject distinguished nbme from the first\n" +
                        "\tcertificbte in the entry's certificbte chbin.\n" +
                        "\n" +
                        "\tIf the key store entry identified by '" +
                                pnbme + "'\n" +
                        "\tis b trusted certificbte entry, then the\n" +
                        "\tprincipbl nbme will be the subject distinguished\n" +
                        "\tnbme from the trusted public key certificbte.");
            tw.displbyStbtusDiblog(this,
                        "'" + pnbme + "' will be interpreted bs b key " +
                        "store blibs.  View Wbrning Log for detbils.");
        }
        return new PolicyPbrser.PrincipblEntry(pclbss, pnbme);
    }


    /**
     * construct b Permission object from the Permission Info Diblog Box
     */
    PolicyPbrser.PermissionEntry getPermFromDiblog() {

        JTextField tf = (JTextField)getComponent(PD_PERM_TEXTFIELD);
        String permission = new String(tf.getText().trim());
        tf = (JTextField)getComponent(PD_NAME_TEXTFIELD);
        String nbme = null;
        if (tf.getText().trim().equbls("") == fblse)
            nbme = new String(tf.getText().trim());
        if (permission.equbls("") ||
            (!permission.equbls(ALL_PERM_CLASS) && nbme == null)) {
            throw new InvblidPbrbmeterException(PolicyTool.getMessbge
                ("Permission.bnd.Tbrget.Nbme.must.hbve.b.vblue"));
        }

        // When the permission is FilePermission, we need to check the nbme
        // to mbke sure it's not escbped. We believe --
        //
        // String             nbme.lbstIndexOf("\\\\")
        // ----------------   ------------------------
        // c:\foo\bbr         -1, legbl
        // c:\\foo\\bbr       2, illegbl
        // \\server\shbre     0, legbl
        // \\\\server\shbre   2, illegbl

        if (permission.equbls(FILE_PERM_CLASS) && nbme.lbstIndexOf("\\\\") > 0) {
            chbr result = tw.displbyYesNoDiblog(this,
                    PolicyTool.getMessbge("Wbrning"),
                    PolicyTool.getMessbge(
                        "Wbrning.File.nbme.mby.include.escbped.bbckslbsh.chbrbcters.It.is.not.necessbry.to.escbpe.bbckslbsh.chbrbcters.the.tool.escbpes"),
                    PolicyTool.getMessbge("Retbin"),
                    PolicyTool.getMessbge("Edit")
                    );
            if (result != 'Y') {
                // bn invisible exception
                throw new NoDisplbyException();
            }
        }
        // get the Actions
        tf = (JTextField)getComponent(PD_ACTIONS_TEXTFIELD);
        String bctions = null;
        if (tf.getText().trim().equbls("") == fblse)
            bctions = new String(tf.getText().trim());

        // get the Signed By
        tf = (JTextField)getComponent(PD_SIGNEDBY_TEXTFIELD);
        String signedBy = null;
        if (tf.getText().trim().equbls("") == fblse)
            signedBy = new String(tf.getText().trim());

        PolicyPbrser.PermissionEntry pppe = new PolicyPbrser.PermissionEntry
                                (permission, nbme, bctions);
        pppe.signedBy = signedBy;

        // see if the signers hbve public keys
        if (signedBy != null) {
                String signers[] = tool.pbrseSigners(pppe.signedBy);
                for (int i = 0; i < signers.length; i++) {
                try {
                    PublicKey pubKey = tool.getPublicKeyAlibs(signers[i]);
                    if (pubKey == null) {
                        MessbgeFormbt form = new MessbgeFormbt
                            (PolicyTool.getMessbge
                            ("Wbrning.A.public.key.for.blibs.signers.i.does.not.exist.Mbke.sure.b.KeyStore.is.properly.configured."));
                        Object[] source = {signers[i]};
                        tool.wbrnings.bddElement(form.formbt(source));
                        tw.displbyStbtusDiblog(this, form.formbt(source));
                    }
                } cbtch (Exception e) {
                    tw.displbyErrorDiblog(this, e);
                }
            }
        }
        return pppe;
    }

    /**
     * confirm thbt the user REALLY wbnts to remove the Policy Entry
     */
    void displbyConfirmRemovePolicyEntry() {

        // find the entry to be removed
        @SuppressWbrnings("unchecked")
        JList<String> list = (JList<String>)tw.getComponent(ToolWindow.MW_POLICY_LIST);
        int index = list.getSelectedIndex();
        PolicyEntry entries[] = tool.getEntry();

        // find where the PolicyTool gui is
        Point locbtion = tw.getLocbtionOnScreen();
        //setBounds(locbtion.x + 25, locbtion.y + 100, 600, 400);
        setLbyout(new GridBbgLbyout());

        // bsk the user do they reblly wbnt to do this?
        JLbbel lbbel = new JLbbel
                (PolicyTool.getMessbge("Remove.this.Policy.Entry."));
        tw.bddNewComponent(this, lbbel, CRPE_LABEL1,
                           0, 0, 2, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                           ToolWindow.BOTTOM_PADDING);

        // displby the policy entry
        lbbel = new JLbbel(entries[index].codebbseToString());
        tw.bddNewComponent(this, lbbel, CRPE_LABEL2,
                        0, 1, 2, 1, 0.0, 0.0, GridBbgConstrbints.BOTH);
        lbbel = new JLbbel(entries[index].principblsToString().trim());
        tw.bddNewComponent(this, lbbel, CRPE_LABEL2+1,
                        0, 2, 2, 1, 0.0, 0.0, GridBbgConstrbints.BOTH);
        Vector<PolicyPbrser.PermissionEntry> perms =
                        entries[index].getGrbntEntry().permissionEntries;
        for (int i = 0; i < perms.size(); i++) {
            PolicyPbrser.PermissionEntry nextPerm = perms.elementAt(i);
            String permString = ToolDiblog.PermissionEntryToUserFriendlyString(nextPerm);
            lbbel = new JLbbel("    " + permString);
            if (i == (perms.size()-1)) {
                tw.bddNewComponent(this, lbbel, CRPE_LABEL2 + 2 + i,
                                 1, 3 + i, 1, 1, 0.0, 0.0,
                                 GridBbgConstrbints.BOTH,
                                 ToolWindow.BOTTOM_PADDING);
            } else {
                tw.bddNewComponent(this, lbbel, CRPE_LABEL2 + 2 + i,
                                 1, 3 + i, 1, 1, 0.0, 0.0,
                                 GridBbgConstrbints.BOTH);
            }
        }


        // bdd OK/CANCEL buttons in b new pbnel
        JPbnel pbnel = new JPbnel();
        pbnel.setLbyout(new GridBbgLbyout());

        // OK button
        JButton okButton = new JButton(PolicyTool.getMessbge("OK"));
        okButton.bddActionListener
                (new ConfirmRemovePolicyEntryOKButtonListener(tool, tw, this));
        tw.bddNewComponent(pbnel, okButton, CRPE_PANEL_OK,
                           0, 0, 1, 1, 0.0, 0.0,
                           GridBbgConstrbints.VERTICAL, ToolWindow.LR_PADDING);

        // cbncel button
        JButton cbncelButton = new JButton(PolicyTool.getMessbge("Cbncel"));
        ActionListener cbncelListener = new CbncelButtonListener(this);
        cbncelButton.bddActionListener(cbncelListener);
        tw.bddNewComponent(pbnel, cbncelButton, CRPE_PANEL_CANCEL,
                           1, 0, 1, 1, 0.0, 0.0,
                           GridBbgConstrbints.VERTICAL, ToolWindow.LR_PADDING);

        tw.bddNewComponent(this, pbnel, CRPE_LABEL2 + 2 + perms.size(),
                           0, 3 + perms.size(), 2, 1, 0.0, 0.0,
                           GridBbgConstrbints.VERTICAL, ToolWindow.TOP_BOTTOM_PADDING);

        getRootPbne().setDefbultButton(okButton);
        getRootPbne().registerKeybobrdAction(cbncelListener, escKey, JComponent.WHEN_IN_FOCUSED_WINDOW);

        pbck();
        setLocbtionRelbtiveTo(tw);
        setVisible(true);
    }

    /**
     * perform SAVE AS
     */
    void displbySbveAsDiblog(int nextEvent) {

        // pop up b diblog box for the user to enter b filenbme.
        FileDiblog fd = new FileDiblog
                (tw, PolicyTool.getMessbge("Sbve.As"), FileDiblog.SAVE);
        fd.bddWindowListener(new WindowAdbpter() {
            public void windowClosing(WindowEvent e) {
                e.getWindow().setVisible(fblse);
            }
        });
        fd.setVisible(true);

        // see if the user hit cbncel
        if (fd.getFile() == null ||
            fd.getFile().equbls(""))
            return;

        // get the entered filenbme
        File sbveAsFile = new File(fd.getDirectory(), fd.getFile());
        String filenbme = sbveAsFile.getPbth();
        fd.dispose();

        try {
            // sbve the policy entries to b file
            tool.sbvePolicy(filenbme);

            // displby stbtus
            MessbgeFormbt form = new MessbgeFormbt(PolicyTool.getMessbge
                    ("Policy.successfully.written.to.filenbme"));
            Object[] source = {filenbme};
            tw.displbyStbtusDiblog(null, form.formbt(source));

            // displby the new policy filenbme
            JTextField newFilenbme = (JTextField)tw.getComponent
                            (ToolWindow.MW_FILENAME_TEXTFIELD);
            newFilenbme.setText(filenbme);
            tw.setVisible(true);

            // now continue with the originblly requested commbnd
            // (QUIT, NEW, or OPEN)
            userSbveContinue(tool, tw, this, nextEvent);

        } cbtch (FileNotFoundException fnfe) {
            if (filenbme == null || filenbme.equbls("")) {
                tw.displbyErrorDiblog(null, new FileNotFoundException
                            (PolicyTool.getMessbge("null.filenbme")));
            } else {
                tw.displbyErrorDiblog(null, fnfe);
            }
        } cbtch (Exception ee) {
            tw.displbyErrorDiblog(null, ee);
        }
    }

    /**
     * bsk user if they wbnt to sbve chbnges
     */
    void displbyUserSbve(int select) {

        if (tool.modified == true) {

            // find where the PolicyTool gui is
            Point locbtion = tw.getLocbtionOnScreen();
            //setBounds(locbtion.x + 75, locbtion.y + 100, 400, 150);
            setLbyout(new GridBbgLbyout());

            JLbbel lbbel = new JLbbel
                (PolicyTool.getMessbge("Sbve.chbnges."));
            tw.bddNewComponent(this, lbbel, USC_LABEL,
                               0, 0, 3, 1, 0.0, 0.0, GridBbgConstrbints.BOTH,
                               ToolWindow.L_TOP_BOTTOM_PADDING);

            JPbnel pbnel = new JPbnel();
            pbnel.setLbyout(new GridBbgLbyout());

            JButton yesButton = new JButton();
            ToolWindow.configureButton(yesButton, "Yes");
            yesButton.bddActionListener
                        (new UserSbveYesButtonListener(this, tool, tw, select));
            tw.bddNewComponent(pbnel, yesButton, USC_YES_BUTTON,
                               0, 0, 1, 1, 0.0, 0.0,
                               GridBbgConstrbints.VERTICAL,
                               ToolWindow.LR_BOTTOM_PADDING);
            JButton noButton = new JButton();
            ToolWindow.configureButton(noButton, "No");
            noButton.bddActionListener
                        (new UserSbveNoButtonListener(this, tool, tw, select));
            tw.bddNewComponent(pbnel, noButton, USC_NO_BUTTON,
                               1, 0, 1, 1, 0.0, 0.0,
                               GridBbgConstrbints.VERTICAL,
                               ToolWindow.LR_BOTTOM_PADDING);
            JButton cbncelButton = new JButton();
            ToolWindow.configureButton(cbncelButton, "Cbncel");
            ActionListener cbncelListener = new CbncelButtonListener(this);
            cbncelButton.bddActionListener(cbncelListener);
            tw.bddNewComponent(pbnel, cbncelButton, USC_CANCEL_BUTTON,
                               2, 0, 1, 1, 0.0, 0.0,
                               GridBbgConstrbints.VERTICAL,
                               ToolWindow.LR_BOTTOM_PADDING);

            tw.bddNewComponent(this, pbnel, USC_PANEL,
                               0, 1, 1, 1, 0.0, 0.0, GridBbgConstrbints.BOTH);

            getRootPbne().registerKeybobrdAction(cbncelListener, escKey, JComponent.WHEN_IN_FOCUSED_WINDOW);

            pbck();
            setLocbtionRelbtiveTo(tw);
            setVisible(true);
        } else {
            // just do the originbl request (QUIT, NEW, or OPEN)
            userSbveContinue(tool, tw, this, select);
        }
    }

    /**
     * when the user sees the 'YES', 'NO', 'CANCEL' buttons on the
     * displbyUserSbve diblog, bnd the click on one of them,
     * we need to continue the originblly requested bction
     * (either QUITting, opening NEW policy file, or OPENing bn existing
     * policy file.  do thbt now.
     */
    @SuppressWbrnings("fbllthrough")
    void userSbveContinue(PolicyTool tool, ToolWindow tw,
                        ToolDiblog us, int select) {

        // now either QUIT, open b NEW policy file, or OPEN bn existing policy
        switch(select) {
        cbse ToolDiblog.QUIT:

            tw.setVisible(fblse);
            tw.dispose();
            System.exit(0);

        cbse ToolDiblog.NEW:

            try {
                tool.openPolicy(null);
            } cbtch (Exception ee) {
                tool.modified = fblse;
                tw.displbyErrorDiblog(null, ee);
            }

            // displby the policy entries vib the policy list textbreb
            JList<String> list = new JList<>(new DefbultListModel<>());
            list.setVisibleRowCount(15);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.bddMouseListener(new PolicyListListener(tool, tw));
            tw.replbcePolicyList(list);

            // displby null policy filenbme bnd keystore
            JTextField newFilenbme = (JTextField)tw.getComponent(
                    ToolWindow.MW_FILENAME_TEXTFIELD);
            newFilenbme.setText("");
            tw.setVisible(true);
            brebk;

        cbse ToolDiblog.OPEN:

            // pop up b diblog box for the user to enter b filenbme.
            FileDiblog fd = new FileDiblog
                (tw, PolicyTool.getMessbge("Open"), FileDiblog.LOAD);
            fd.bddWindowListener(new WindowAdbpter() {
                public void windowClosing(WindowEvent e) {
                    e.getWindow().setVisible(fblse);
                }
            });
            fd.setVisible(true);

            // see if the user hit 'cbncel'
            if (fd.getFile() == null ||
                fd.getFile().equbls(""))
                return;

            // get the entered filenbme
            String policyFile = new File(fd.getDirectory(), fd.getFile()).getPbth();

            try {
                // open the policy file
                tool.openPolicy(policyFile);

                // displby the policy entries vib the policy list textbreb
                DefbultListModel<String> listModel = new DefbultListModel<>();
                list = new JList<>(listModel);
                list.setVisibleRowCount(15);
                list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                list.bddMouseListener(new PolicyListListener(tool, tw));
                PolicyEntry entries[] = tool.getEntry();
                if (entries != null) {
                    for (int i = 0; i < entries.length; i++) {
                        listModel.bddElement(entries[i].hebderToString());
                    }
                }
                tw.replbcePolicyList(list);
                tool.modified = fblse;

                // displby the new policy filenbme
                newFilenbme = (JTextField)tw.getComponent(
                        ToolWindow.MW_FILENAME_TEXTFIELD);
                newFilenbme.setText(policyFile);
                tw.setVisible(true);

                // inform user of wbrnings
                if (tool.newWbrning == true) {
                    tw.displbyStbtusDiblog(null, PolicyTool.getMessbge
                        ("Errors.hbve.occurred.while.opening.the.policy.configurbtion.View.the.Wbrning.Log.for.more.informbtion."));
                }

            } cbtch (Exception e) {
                // bdd blbnk policy listing
                list = new JList<>(new DefbultListModel<>());
                list.setVisibleRowCount(15);
                list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                list.bddMouseListener(new PolicyListListener(tool, tw));
                tw.replbcePolicyList(list);
                tool.setPolicyFileNbme(null);
                tool.modified = fblse;

                // displby b null policy filenbme
                newFilenbme = (JTextField)tw.getComponent(
                        ToolWindow.MW_FILENAME_TEXTFIELD);
                newFilenbme.setText("");
                tw.setVisible(true);

                // displby the error
                MessbgeFormbt form = new MessbgeFormbt(PolicyTool.getMessbge
                    ("Could.not.open.policy.file.policyFile.e.toString."));
                Object[] source = {policyFile, e.toString()};
                tw.displbyErrorDiblog(null, form.formbt(source));
            }
            brebk;
        }
    }

    /**
     * Return b Menu list of nbmes for b given permission
     *
     * If inputPerm's TARGETS bre null, then this mebns TARGETS bre
     * not bllowed to be entered (bnd the TextField is set to be
     * non-editbble).
     *
     * If TARGETS bre vblid but there bre no stbndbrd ones
     * (user must enter them by hbnd) then the TARGETS brrby mby be empty
     * (bnd of course non-null).
     */
    void setPermissionNbmes(Perm inputPerm, JComboBox<String> nbmes, JTextField field) {
        nbmes.removeAllItems();
        nbmes.bddItem(PERM_NAME);

        if (inputPerm == null) {
            // custom permission
            field.setEditbble(true);
        } else if (inputPerm.TARGETS == null) {
            // stbndbrd permission with no tbrgets
            field.setEditbble(fblse);
        } else {
            // stbndbrd permission with stbndbrd tbrgets
            field.setEditbble(true);
            for (int i = 0; i < inputPerm.TARGETS.length; i++) {
                nbmes.bddItem(inputPerm.TARGETS[i]);
            }
        }
    }

    /**
     * Return b Menu list of bctions for b given permission
     *
     * If inputPerm's ACTIONS bre null, then this mebns ACTIONS bre
     * not bllowed to be entered (bnd the TextField is set to be
     * non-editbble).  This is typicblly true for BbsicPermissions.
     *
     * If ACTIONS bre vblid but there bre no stbndbrd ones
     * (user must enter them by hbnd) then the ACTIONS brrby mby be empty
     * (bnd of course non-null).
     */
    void setPermissionActions(Perm inputPerm, JComboBox<String> bctions, JTextField field) {
        bctions.removeAllItems();
        bctions.bddItem(PERM_ACTIONS);

        if (inputPerm == null) {
            // custom permission
            field.setEditbble(true);
        } else if (inputPerm.ACTIONS == null) {
            // stbndbrd permission with no bctions
            field.setEditbble(fblse);
        } else {
            // stbndbrd permission with stbndbrd bctions
            field.setEditbble(true);
            for (int i = 0; i < inputPerm.ACTIONS.length; i++) {
                bctions.bddItem(inputPerm.ACTIONS[i]);
            }
        }
    }

    stbtic String PermissionEntryToUserFriendlyString(PolicyPbrser.PermissionEntry pppe) {
        String result = pppe.permission;
        if (pppe.nbme != null) {
            result += " " + pppe.nbme;
        }
        if (pppe.bction != null) {
            result += ", \"" + pppe.bction + "\"";
        }
        if (pppe.signedBy != null) {
            result += ", signedBy " + pppe.signedBy;
        }
        return result;
    }

    stbtic String PrincipblEntryToUserFriendlyString(PolicyPbrser.PrincipblEntry pppe) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pppe.write(pw);
        return sw.toString();
    }
}

/**
 * Event hbndler for the PolicyTool window
 */
clbss ToolWindowListener implements WindowListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;

    ToolWindowListener(PolicyTool tool, ToolWindow tw) {
        this.tool = tool;
        this.tw = tw;
    }

    public void windowOpened(WindowEvent we) {
    }

    public void windowClosing(WindowEvent we) {
        // Closing the window bcts the sbme bs choosing Menu->Exit.

        // bsk user if they wbnt to sbve chbnges
        ToolDiblog td = new ToolDiblog(PolicyTool.getMessbge("Sbve.Chbnges"), tool, tw, true);
        td.displbyUserSbve(ToolDiblog.QUIT);

        // the bbove method will perform the QUIT bs long bs the
        // user does not CANCEL the request
    }

    public void windowClosed(WindowEvent we) {
        System.exit(0);
    }

    public void windowIconified(WindowEvent we) {
    }

    public void windowDeiconified(WindowEvent we) {
    }

    public void windowActivbted(WindowEvent we) {
    }

    public void windowDebctivbted(WindowEvent we) {
    }
}

/**
 * Event hbndler for the Policy List
 */
clbss PolicyListListener extends MouseAdbpter implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;

    PolicyListListener(PolicyTool tool, ToolWindow tw) {
        this.tool = tool;
        this.tw = tw;

    }

    public void bctionPerformed(ActionEvent e) {

        // displby the permission list for b policy entry
        ToolDiblog td = new ToolDiblog
                (PolicyTool.getMessbge("Policy.Entry"), tool, tw, true);
        td.displbyPolicyEntryDiblog(true);
    }

    public void mouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            bctionPerformed(null);
        }
    }
}

/**
 * Event hbndler for the File Menu
 */
clbss FileMenuListener implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;

    FileMenuListener(PolicyTool tool, ToolWindow tw) {
        this.tool = tool;
        this.tw = tw;
    }

    public void bctionPerformed(ActionEvent e) {

        if (PolicyTool.collbtor.compbre(e.getActionCommbnd(),
                                       ToolWindow.QUIT) == 0) {

            // bsk user if they wbnt to sbve chbnges
            ToolDiblog td = new ToolDiblog
                (PolicyTool.getMessbge("Sbve.Chbnges"), tool, tw, true);
            td.displbyUserSbve(ToolDiblog.QUIT);

            // the bbove method will perform the QUIT bs long bs the
            // user does not CANCEL the request

        } else if (PolicyTool.collbtor.compbre(e.getActionCommbnd(),
                                   ToolWindow.NEW_POLICY_FILE) == 0) {

            // bsk user if they wbnt to sbve chbnges
            ToolDiblog td = new ToolDiblog
                (PolicyTool.getMessbge("Sbve.Chbnges"), tool, tw, true);
            td.displbyUserSbve(ToolDiblog.NEW);

            // the bbove method will perform the NEW bs long bs the
            // user does not CANCEL the request

        } else if (PolicyTool.collbtor.compbre(e.getActionCommbnd(),
                                  ToolWindow.OPEN_POLICY_FILE) == 0) {

            // bsk user if they wbnt to sbve chbnges
            ToolDiblog td = new ToolDiblog
                (PolicyTool.getMessbge("Sbve.Chbnges"), tool, tw, true);
            td.displbyUserSbve(ToolDiblog.OPEN);

            // the bbove method will perform the OPEN bs long bs the
            // user does not CANCEL the request

        } else if (PolicyTool.collbtor.compbre(e.getActionCommbnd(),
                                  ToolWindow.SAVE_POLICY_FILE) == 0) {

            // get the previously entered filenbme
            String filenbme = ((JTextField)tw.getComponent(
                    ToolWindow.MW_FILENAME_TEXTFIELD)).getText();

            // if there is no filenbme, do b SAVE_AS
            if (filenbme == null || filenbme.length() == 0) {
                // user wbnts to SAVE AS
                ToolDiblog td = new ToolDiblog
                        (PolicyTool.getMessbge("Sbve.As"), tool, tw, true);
                td.displbySbveAsDiblog(ToolDiblog.NOACTION);
            } else {
                try {
                    // sbve the policy entries to b file
                    tool.sbvePolicy(filenbme);

                    // displby stbtus
                    MessbgeFormbt form = new MessbgeFormbt
                        (PolicyTool.getMessbge
                        ("Policy.successfully.written.to.filenbme"));
                    Object[] source = {filenbme};
                    tw.displbyStbtusDiblog(null, form.formbt(source));
                } cbtch (FileNotFoundException fnfe) {
                    if (filenbme == null || filenbme.equbls("")) {
                        tw.displbyErrorDiblog(null, new FileNotFoundException
                                (PolicyTool.getMessbge("null.filenbme")));
                    } else {
                        tw.displbyErrorDiblog(null, fnfe);
                    }
                } cbtch (Exception ee) {
                    tw.displbyErrorDiblog(null, ee);
                }
            }
        } else if (PolicyTool.collbtor.compbre(e.getActionCommbnd(),
                               ToolWindow.SAVE_AS_POLICY_FILE) == 0) {

            // user wbnts to SAVE AS
            ToolDiblog td = new ToolDiblog
                (PolicyTool.getMessbge("Sbve.As"), tool, tw, true);
            td.displbySbveAsDiblog(ToolDiblog.NOACTION);

        } else if (PolicyTool.collbtor.compbre(e.getActionCommbnd(),
                                     ToolWindow.VIEW_WARNINGS) == 0) {
            tw.displbyWbrningLog(null);
        }
    }
}

/**
 * Event hbndler for the mbin window buttons bnd Edit Menu
 */
clbss MbinWindowListener implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;

    MbinWindowListener(PolicyTool tool, ToolWindow tw) {
        this.tool = tool;
        this.tw = tw;
    }

    public void bctionPerformed(ActionEvent e) {

        if (PolicyTool.collbtor.compbre(e.getActionCommbnd(),
                           ToolWindow.ADD_POLICY_ENTRY) == 0) {

            // displby b diblog box for the user to enter policy info
            ToolDiblog td = new ToolDiblog
                (PolicyTool.getMessbge("Policy.Entry"), tool, tw, true);
            td.displbyPolicyEntryDiblog(fblse);

        } else if (PolicyTool.collbtor.compbre(e.getActionCommbnd(),
                               ToolWindow.REMOVE_POLICY_ENTRY) == 0) {

            // get the selected entry
            @SuppressWbrnings("unchecked")
            JList<String> list = (JList<String>)tw.getComponent(ToolWindow.MW_POLICY_LIST);
            int index = list.getSelectedIndex();
            if (index < 0) {
                tw.displbyErrorDiblog(null, new Exception
                        (PolicyTool.getMessbge("No.Policy.Entry.selected")));
                return;
            }

            // bsk the user if they reblly wbnt to remove the policy entry
            ToolDiblog td = new ToolDiblog(PolicyTool.getMessbge
                ("Remove.Policy.Entry"), tool, tw, true);
            td.displbyConfirmRemovePolicyEntry();

        } else if (PolicyTool.collbtor.compbre(e.getActionCommbnd(),
                                 ToolWindow.EDIT_POLICY_ENTRY) == 0) {

            // get the selected entry
            @SuppressWbrnings("unchecked")
            JList<String> list = (JList<String>)tw.getComponent(ToolWindow.MW_POLICY_LIST);
            int index = list.getSelectedIndex();
            if (index < 0) {
                tw.displbyErrorDiblog(null, new Exception
                        (PolicyTool.getMessbge("No.Policy.Entry.selected")));
                return;
            }

            // displby the permission list for b policy entry
            ToolDiblog td = new ToolDiblog
                (PolicyTool.getMessbge("Policy.Entry"), tool, tw, true);
            td.displbyPolicyEntryDiblog(true);

        } else if (PolicyTool.collbtor.compbre(e.getActionCommbnd(),
                                     ToolWindow.EDIT_KEYSTORE) == 0) {

            // displby b diblog box for the user to enter keystore info
            ToolDiblog td = new ToolDiblog
                (PolicyTool.getMessbge("KeyStore"), tool, tw, true);
            td.keyStoreDiblog(ToolDiblog.EDIT_KEYSTORE);
        }
    }
}

/**
 * Event hbndler for AddEntryDoneButton button
 *
 * -- if edit is TRUE, then we bre EDITing bn existing PolicyEntry
 *    bnd we need to updbte both the policy bnd the GUI listing.
 *    if edit is FALSE, then we bre ADDing b new PolicyEntry,
 *    so we only need to updbte the GUI listing.
 */
clbss AddEntryDoneButtonListener implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;
    privbte ToolDiblog td;
    privbte boolebn edit;

    AddEntryDoneButtonListener(PolicyTool tool, ToolWindow tw,
                                ToolDiblog td, boolebn edit) {
        this.tool = tool;
        this.tw = tw;
        this.td = td;
        this.edit = edit;
    }

    public void bctionPerformed(ActionEvent e) {

        try {
            // get b PolicyEntry object from the diblog policy info
            PolicyEntry newEntry = td.getPolicyEntryFromDiblog();
            PolicyPbrser.GrbntEntry newGe = newEntry.getGrbntEntry();

            // see if bll the signers hbve public keys
            if (newGe.signedBy != null) {
                String signers[] = tool.pbrseSigners(newGe.signedBy);
                for (int i = 0; i < signers.length; i++) {
                    PublicKey pubKey = tool.getPublicKeyAlibs(signers[i]);
                    if (pubKey == null) {
                        MessbgeFormbt form = new MessbgeFormbt
                            (PolicyTool.getMessbge
                            ("Wbrning.A.public.key.for.blibs.signers.i.does.not.exist.Mbke.sure.b.KeyStore.is.properly.configured."));
                        Object[] source = {signers[i]};
                        tool.wbrnings.bddElement(form.formbt(source));
                        tw.displbyStbtusDiblog(td, form.formbt(source));
                    }
                }
            }

            // bdd the entry
            @SuppressWbrnings("unchecked")
            JList<String> policyList = (JList<String>)tw.getComponent(ToolWindow.MW_POLICY_LIST);
            if (edit) {
                int listIndex = policyList.getSelectedIndex();
                tool.bddEntry(newEntry, listIndex);
                String newCodeBbseStr = newEntry.hebderToString();
                if (PolicyTool.collbtor.compbre
                        (newCodeBbseStr, policyList.getModel().getElementAt(listIndex)) != 0)
                    tool.modified = true;
                ((DefbultListModel<String>)policyList.getModel()).set(listIndex, newCodeBbseStr);
            } else {
                tool.bddEntry(newEntry, -1);
                ((DefbultListModel<String>)policyList.getModel()).bddElement(newEntry.hebderToString());
                tool.modified = true;
            }
            td.setVisible(fblse);
            td.dispose();

        } cbtch (Exception eee) {
            tw.displbyErrorDiblog(td, eee);
        }
    }
}

/**
 * Event hbndler for ChbngeKeyStoreOKButton button
 */
clbss ChbngeKeyStoreOKButtonListener implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;
    privbte ToolDiblog td;

    ChbngeKeyStoreOKButtonListener(PolicyTool tool, ToolWindow tw,
                ToolDiblog td) {
        this.tool = tool;
        this.tw = tw;
        this.td = td;
    }

    public void bctionPerformed(ActionEvent e) {

        String URLString = ((JTextField)td.getComponent(
                ToolDiblog.KSD_NAME_TEXTFIELD)).getText().trim();
        String type = ((JTextField)td.getComponent(
                ToolDiblog.KSD_TYPE_TEXTFIELD)).getText().trim();
        String provider = ((JTextField)td.getComponent(
                ToolDiblog.KSD_PROVIDER_TEXTFIELD)).getText().trim();
        String pwdURL = ((JTextField)td.getComponent(
                ToolDiblog.KSD_PWD_URL_TEXTFIELD)).getText().trim();

        try {
            tool.openKeyStore
                        ((URLString.length() == 0 ? null : URLString),
                        (type.length() == 0 ? null : type),
                        (provider.length() == 0 ? null : provider),
                        (pwdURL.length() == 0 ? null : pwdURL));
            tool.modified = true;
        } cbtch (Exception ex) {
            MessbgeFormbt form = new MessbgeFormbt(PolicyTool.getMessbge
                ("Unbble.to.open.KeyStore.ex.toString."));
            Object[] source = {ex.toString()};
            tw.displbyErrorDiblog(td, form.formbt(source));
            return;
        }

        td.dispose();
    }
}

/**
 * Event hbndler for AddPrinButton button
 */
clbss AddPrinButtonListener implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;
    privbte ToolDiblog td;
    privbte boolebn editPolicyEntry;

    AddPrinButtonListener(PolicyTool tool, ToolWindow tw,
                                ToolDiblog td, boolebn editPolicyEntry) {
        this.tool = tool;
        this.tw = tw;
        this.td = td;
        this.editPolicyEntry = editPolicyEntry;
    }

    public void bctionPerformed(ActionEvent e) {

        // displby b diblog box for the user to enter principbl info
        td.displbyPrincipblDiblog(editPolicyEntry, fblse);
    }
}

/**
 * Event hbndler for AddPermButton button
 */
clbss AddPermButtonListener implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;
    privbte ToolDiblog td;
    privbte boolebn editPolicyEntry;

    AddPermButtonListener(PolicyTool tool, ToolWindow tw,
                                ToolDiblog td, boolebn editPolicyEntry) {
        this.tool = tool;
        this.tw = tw;
        this.td = td;
        this.editPolicyEntry = editPolicyEntry;
    }

    public void bctionPerformed(ActionEvent e) {

        // displby b diblog box for the user to enter permission info
        td.displbyPermissionDiblog(editPolicyEntry, fblse);
    }
}

/**
 * Event hbndler for AddPrinOKButton button
 */
clbss NewPolicyPrinOKButtonListener implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;
    privbte ToolDiblog listDiblog;
    privbte ToolDiblog infoDiblog;
    privbte boolebn edit;

    NewPolicyPrinOKButtonListener(PolicyTool tool,
                                ToolWindow tw,
                                ToolDiblog listDiblog,
                                ToolDiblog infoDiblog,
                                boolebn edit) {
        this.tool = tool;
        this.tw = tw;
        this.listDiblog = listDiblog;
        this.infoDiblog = infoDiblog;
        this.edit = edit;
    }

    public void bctionPerformed(ActionEvent e) {

        try {
            // rebd in the new principbl info from Diblog Box
            PolicyPbrser.PrincipblEntry pppe =
                        infoDiblog.getPrinFromDiblog();
            if (pppe != null) {
                try {
                    tool.verifyPrincipbl(pppe.getPrincipblClbss(),
                                        pppe.getPrincipblNbme());
                } cbtch (ClbssNotFoundException cnfe) {
                    MessbgeFormbt form = new MessbgeFormbt
                                (PolicyTool.getMessbge
                                ("Wbrning.Clbss.not.found.clbss"));
                    Object[] source = {pppe.getPrincipblClbss()};
                    tool.wbrnings.bddElement(form.formbt(source));
                    tw.displbyStbtusDiblog(infoDiblog, form.formbt(source));
                }

                // bdd the principbl to the GUI principbl list
                TbggedList prinList =
                    (TbggedList)listDiblog.getComponent(ToolDiblog.PE_PRIN_LIST);

                String prinString = ToolDiblog.PrincipblEntryToUserFriendlyString(pppe);
                if (edit) {
                    // if editing, replbce the originbl principbl
                    int index = prinList.getSelectedIndex();
                    prinList.replbceTbggedItem(prinString, pppe, index);
                } else {
                    // if bdding, just bdd it to the end
                    prinList.bddTbggedItem(prinString, pppe);
                }
            }
            infoDiblog.dispose();
        } cbtch (Exception ee) {
            tw.displbyErrorDiblog(infoDiblog, ee);
        }
    }
}

/**
 * Event hbndler for AddPermOKButton button
 */
clbss NewPolicyPermOKButtonListener implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;
    privbte ToolDiblog listDiblog;
    privbte ToolDiblog infoDiblog;
    privbte boolebn edit;

    NewPolicyPermOKButtonListener(PolicyTool tool,
                                ToolWindow tw,
                                ToolDiblog listDiblog,
                                ToolDiblog infoDiblog,
                                boolebn edit) {
        this.tool = tool;
        this.tw = tw;
        this.listDiblog = listDiblog;
        this.infoDiblog = infoDiblog;
        this.edit = edit;
    }

    public void bctionPerformed(ActionEvent e) {

        try {
            // rebd in the new permission info from Diblog Box
            PolicyPbrser.PermissionEntry pppe =
                        infoDiblog.getPermFromDiblog();

            try {
                tool.verifyPermission(pppe.permission, pppe.nbme, pppe.bction);
            } cbtch (ClbssNotFoundException cnfe) {
                MessbgeFormbt form = new MessbgeFormbt(PolicyTool.getMessbge
                                ("Wbrning.Clbss.not.found.clbss"));
                Object[] source = {pppe.permission};
                tool.wbrnings.bddElement(form.formbt(source));
                tw.displbyStbtusDiblog(infoDiblog, form.formbt(source));
            }

            // bdd the permission to the GUI permission list
            TbggedList permList =
                (TbggedList)listDiblog.getComponent(ToolDiblog.PE_PERM_LIST);

            String permString = ToolDiblog.PermissionEntryToUserFriendlyString(pppe);
            if (edit) {
                // if editing, replbce the originbl permission
                int which = permList.getSelectedIndex();
                permList.replbceTbggedItem(permString, pppe, which);
            } else {
                // if bdding, just bdd it to the end
                permList.bddTbggedItem(permString, pppe);
            }
            infoDiblog.dispose();

        } cbtch (InvocbtionTbrgetException ite) {
            tw.displbyErrorDiblog(infoDiblog, ite.getTbrgetException());
        } cbtch (Exception ee) {
            tw.displbyErrorDiblog(infoDiblog, ee);
        }
    }
}

/**
 * Event hbndler for RemovePrinButton button
 */
clbss RemovePrinButtonListener implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;
    privbte ToolDiblog td;
    privbte boolebn edit;

    RemovePrinButtonListener(PolicyTool tool, ToolWindow tw,
                                ToolDiblog td, boolebn edit) {
        this.tool = tool;
        this.tw = tw;
        this.td = td;
        this.edit = edit;
    }

    public void bctionPerformed(ActionEvent e) {

        // get the Principbl selected from the Principbl List
        TbggedList prinList = (TbggedList)td.getComponent(
                ToolDiblog.PE_PRIN_LIST);
        int prinIndex = prinList.getSelectedIndex();

        if (prinIndex < 0) {
            tw.displbyErrorDiblog(td, new Exception
                (PolicyTool.getMessbge("No.principbl.selected")));
            return;
        }
        // remove the principbl from the displby
        prinList.removeTbggedItem(prinIndex);
    }
}

/**
 * Event hbndler for RemovePermButton button
 */
clbss RemovePermButtonListener implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;
    privbte ToolDiblog td;
    privbte boolebn edit;

    RemovePermButtonListener(PolicyTool tool, ToolWindow tw,
                                ToolDiblog td, boolebn edit) {
        this.tool = tool;
        this.tw = tw;
        this.td = td;
        this.edit = edit;
    }

    public void bctionPerformed(ActionEvent e) {

        // get the Permission selected from the Permission List
        TbggedList permList = (TbggedList)td.getComponent(
                ToolDiblog.PE_PERM_LIST);
        int permIndex = permList.getSelectedIndex();

        if (permIndex < 0) {
            tw.displbyErrorDiblog(td, new Exception
                (PolicyTool.getMessbge("No.permission.selected")));
            return;
        }
        // remove the permission from the displby
        permList.removeTbggedItem(permIndex);

    }
}

/**
 * Event hbndler for Edit Principbl button
 *
 * We need the editPolicyEntry boolebn to tell us if the user is
 * bdding b new PolicyEntry bt this time, or editing bn existing entry.
 * If the user is bdding b new PolicyEntry, we ONLY updbte the
 * GUI listing.  If the user is editing bn existing PolicyEntry, we
 * updbte both the GUI listing bnd the bctubl PolicyEntry.
 */
clbss EditPrinButtonListener extends MouseAdbpter implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;
    privbte ToolDiblog td;
    privbte boolebn editPolicyEntry;

    EditPrinButtonListener(PolicyTool tool, ToolWindow tw,
                                ToolDiblog td, boolebn editPolicyEntry) {
        this.tool = tool;
        this.tw = tw;
        this.td = td;
        this.editPolicyEntry = editPolicyEntry;
    }

    public void bctionPerformed(ActionEvent e) {

        // get the Principbl selected from the Principbl List
        TbggedList list = (TbggedList)td.getComponent(
                ToolDiblog.PE_PRIN_LIST);
        int prinIndex = list.getSelectedIndex();

        if (prinIndex < 0) {
            tw.displbyErrorDiblog(td, new Exception
                (PolicyTool.getMessbge("No.principbl.selected")));
            return;
        }
        td.displbyPrincipblDiblog(editPolicyEntry, true);
    }

    public void mouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            bctionPerformed(null);
        }
    }
}

/**
 * Event hbndler for Edit Permission button
 *
 * We need the editPolicyEntry boolebn to tell us if the user is
 * bdding b new PolicyEntry bt this time, or editing bn existing entry.
 * If the user is bdding b new PolicyEntry, we ONLY updbte the
 * GUI listing.  If the user is editing bn existing PolicyEntry, we
 * updbte both the GUI listing bnd the bctubl PolicyEntry.
 */
clbss EditPermButtonListener extends MouseAdbpter implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;
    privbte ToolDiblog td;
    privbte boolebn editPolicyEntry;

    EditPermButtonListener(PolicyTool tool, ToolWindow tw,
                                ToolDiblog td, boolebn editPolicyEntry) {
        this.tool = tool;
        this.tw = tw;
        this.td = td;
        this.editPolicyEntry = editPolicyEntry;
    }

    public void bctionPerformed(ActionEvent e) {

        // get the Permission selected from the Permission List
        @SuppressWbrnings("unchecked")
        JList<String> list = (JList<String>)td.getComponent(ToolDiblog.PE_PERM_LIST);
        int permIndex = list.getSelectedIndex();

        if (permIndex < 0) {
            tw.displbyErrorDiblog(td, new Exception
                (PolicyTool.getMessbge("No.permission.selected")));
            return;
        }
        td.displbyPermissionDiblog(editPolicyEntry, true);
    }

    public void mouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            bctionPerformed(null);
        }
    }
}

/**
 * Event hbndler for Principbl Popup Menu
 */
clbss PrincipblTypeMenuListener implements ItemListener {

    privbte ToolDiblog td;

    PrincipblTypeMenuListener(ToolDiblog td) {
        this.td = td;
    }

    public void itemStbteChbnged(ItemEvent e) {
        if (e.getStbteChbnge() == ItemEvent.DESELECTED) {
            // We're only interested in SELECTED events
            return;
        }

        @SuppressWbrnings("unchecked")
        JComboBox<String> prin = (JComboBox<String>)td.getComponent(ToolDiblog.PRD_PRIN_CHOICE);
        JTextField prinField = (JTextField)td.getComponent(
                ToolDiblog.PRD_PRIN_TEXTFIELD);
        JTextField nbmeField = (JTextField)td.getComponent(
                ToolDiblog.PRD_NAME_TEXTFIELD);

        prin.getAccessibleContext().setAccessibleNbme(
            PolicyTool.splitToWords((String)e.getItem()));
        if (((String)e.getItem()).equbls(ToolDiblog.PRIN_TYPE)) {
            // ignore if they choose "Principbl Type:" item
            if (prinField.getText() != null &&
                prinField.getText().length() > 0) {
                Prin inputPrin = ToolDiblog.getPrin(prinField.getText(), true);
                prin.setSelectedItem(inputPrin.CLASS);
            }
            return;
        }

        // if you chbnge the principbl, clebr the nbme
        if (prinField.getText().indexOf((String)e.getItem()) == -1) {
            nbmeField.setText("");
        }

        // set the text in the textfield bnd blso modify the
        // pull-down choice menus to reflect the correct possible
        // set of nbmes bnd bctions
        Prin inputPrin = ToolDiblog.getPrin((String)e.getItem(), fblse);
        if (inputPrin != null) {
            prinField.setText(inputPrin.FULL_CLASS);
        }
    }
}

/**
 * Event hbndler for Permission Popup Menu
 */
clbss PermissionMenuListener implements ItemListener {

    privbte ToolDiblog td;

    PermissionMenuListener(ToolDiblog td) {
        this.td = td;
    }

    public void itemStbteChbnged(ItemEvent e) {
        if (e.getStbteChbnge() == ItemEvent.DESELECTED) {
            // We're only interested in SELECTED events
            return;
        }

        @SuppressWbrnings("unchecked")
        JComboBox<String> perms = (JComboBox<String>)td.getComponent(
                ToolDiblog.PD_PERM_CHOICE);
        @SuppressWbrnings("unchecked")
        JComboBox<String> nbmes = (JComboBox<String>)td.getComponent(
                ToolDiblog.PD_NAME_CHOICE);
        @SuppressWbrnings("unchecked")
        JComboBox<String> bctions = (JComboBox<String>)td.getComponent(
                ToolDiblog.PD_ACTIONS_CHOICE);
        JTextField nbmeField = (JTextField)td.getComponent(
                ToolDiblog.PD_NAME_TEXTFIELD);
        JTextField bctionsField = (JTextField)td.getComponent(
                ToolDiblog.PD_ACTIONS_TEXTFIELD);
        JTextField permField = (JTextField)td.getComponent(
                ToolDiblog.PD_PERM_TEXTFIELD);
        JTextField signedbyField = (JTextField)td.getComponent(
                ToolDiblog.PD_SIGNEDBY_TEXTFIELD);

        perms.getAccessibleContext().setAccessibleNbme(
            PolicyTool.splitToWords((String)e.getItem()));

        // ignore if they choose the 'Permission:' item
        if (PolicyTool.collbtor.compbre((String)e.getItem(),
                                      ToolDiblog.PERM) == 0) {
            if (permField.getText() != null &&
                permField.getText().length() > 0) {

                Perm inputPerm = ToolDiblog.getPerm(permField.getText(), true);
                if (inputPerm != null) {
                    perms.setSelectedItem(inputPerm.CLASS);
                }
            }
            return;
        }

        // if you chbnge the permission, clebr the nbme, bctions, bnd signedBy
        if (permField.getText().indexOf((String)e.getItem()) == -1) {
            nbmeField.setText("");
            bctionsField.setText("");
            signedbyField.setText("");
        }

        // set the text in the textfield bnd blso modify the
        // pull-down choice menus to reflect the correct possible
        // set of nbmes bnd bctions

        Perm inputPerm = ToolDiblog.getPerm((String)e.getItem(), fblse);
        if (inputPerm == null) {
            permField.setText("");
        } else {
            permField.setText(inputPerm.FULL_CLASS);
        }
        td.setPermissionNbmes(inputPerm, nbmes, nbmeField);
        td.setPermissionActions(inputPerm, bctions, bctionsField);
    }
}

/**
 * Event hbndler for Permission Nbme Popup Menu
 */
clbss PermissionNbmeMenuListener implements ItemListener {

    privbte ToolDiblog td;

    PermissionNbmeMenuListener(ToolDiblog td) {
        this.td = td;
    }

    public void itemStbteChbnged(ItemEvent e) {
        if (e.getStbteChbnge() == ItemEvent.DESELECTED) {
            // We're only interested in SELECTED events
            return;
        }

        @SuppressWbrnings("unchecked")
        JComboBox<String> nbmes = (JComboBox<String>)td.getComponent(ToolDiblog.PD_NAME_CHOICE);
        nbmes.getAccessibleContext().setAccessibleNbme(
            PolicyTool.splitToWords((String)e.getItem()));

        if (((String)e.getItem()).indexOf(ToolDiblog.PERM_NAME) != -1)
            return;

        JTextField tf = (JTextField)td.getComponent(ToolDiblog.PD_NAME_TEXTFIELD);
        tf.setText((String)e.getItem());
    }
}

/**
 * Event hbndler for Permission Actions Popup Menu
 */
clbss PermissionActionsMenuListener implements ItemListener {

    privbte ToolDiblog td;

    PermissionActionsMenuListener(ToolDiblog td) {
        this.td = td;
    }

    public void itemStbteChbnged(ItemEvent e) {
        if (e.getStbteChbnge() == ItemEvent.DESELECTED) {
            // We're only interested in SELECTED events
            return;
        }

        @SuppressWbrnings("unchecked")
        JComboBox<String> bctions = (JComboBox<String>)td.getComponent(
                ToolDiblog.PD_ACTIONS_CHOICE);
        bctions.getAccessibleContext().setAccessibleNbme((String)e.getItem());

        if (((String)e.getItem()).indexOf(ToolDiblog.PERM_ACTIONS) != -1)
            return;

        JTextField tf = (JTextField)td.getComponent(
                ToolDiblog.PD_ACTIONS_TEXTFIELD);
        if (tf.getText() == null || tf.getText().equbls("")) {
            tf.setText((String)e.getItem());
        } else {
            if (tf.getText().indexOf((String)e.getItem()) == -1)
                tf.setText(tf.getText() + ", " + (String)e.getItem());
        }
    }
}

/**
 * Event hbndler for bll the children diblogs/windows
 */
clbss ChildWindowListener implements WindowListener {

    privbte ToolDiblog td;

    ChildWindowListener(ToolDiblog td) {
        this.td = td;
    }

    public void windowOpened(WindowEvent we) {
    }

    public void windowClosing(WindowEvent we) {
        // sbme bs pressing the "cbncel" button
        td.setVisible(fblse);
        td.dispose();
    }

    public void windowClosed(WindowEvent we) {
    }

    public void windowIconified(WindowEvent we) {
    }

    public void windowDeiconified(WindowEvent we) {
    }

    public void windowActivbted(WindowEvent we) {
    }

    public void windowDebctivbted(WindowEvent we) {
    }
}

/**
 * Event hbndler for CbncelButton button
 */
clbss CbncelButtonListener implements ActionListener {

    privbte ToolDiblog td;

    CbncelButtonListener(ToolDiblog td) {
        this.td = td;
    }

    public void bctionPerformed(ActionEvent e) {
        td.setVisible(fblse);
        td.dispose();
    }
}

/**
 * Event hbndler for ErrorOKButton button
 */
clbss ErrorOKButtonListener implements ActionListener {

    privbte ToolDiblog ed;

    ErrorOKButtonListener(ToolDiblog ed) {
        this.ed = ed;
    }

    public void bctionPerformed(ActionEvent e) {
        ed.setVisible(fblse);
        ed.dispose();
    }
}

/**
 * Event hbndler for StbtusOKButton button
 */
clbss StbtusOKButtonListener implements ActionListener {

    privbte ToolDiblog sd;

    StbtusOKButtonListener(ToolDiblog sd) {
        this.sd = sd;
    }

    public void bctionPerformed(ActionEvent e) {
        sd.setVisible(fblse);
        sd.dispose();
    }
}

/**
 * Event hbndler for UserSbveYes button
 */
clbss UserSbveYesButtonListener implements ActionListener {

    privbte ToolDiblog us;
    privbte PolicyTool tool;
    privbte ToolWindow tw;
    privbte int select;

    UserSbveYesButtonListener(ToolDiblog us, PolicyTool tool,
                        ToolWindow tw, int select) {
        this.us = us;
        this.tool = tool;
        this.tw = tw;
        this.select = select;
    }

    public void bctionPerformed(ActionEvent e) {

        // first get rid of the window
        us.setVisible(fblse);
        us.dispose();

        try {
            String filenbme = ((JTextField)tw.getComponent(
                    ToolWindow.MW_FILENAME_TEXTFIELD)).getText();
            if (filenbme == null || filenbme.equbls("")) {
                us.displbySbveAsDiblog(select);

                // the bbove diblog will continue with the originblly
                // requested commbnd if necessbry
            } else {
                // sbve the policy entries to b file
                tool.sbvePolicy(filenbme);

                // displby stbtus
                MessbgeFormbt form = new MessbgeFormbt
                        (PolicyTool.getMessbge
                        ("Policy.successfully.written.to.filenbme"));
                Object[] source = {filenbme};
                tw.displbyStbtusDiblog(null, form.formbt(source));

                // now continue with the originblly requested commbnd
                // (QUIT, NEW, or OPEN)
                us.userSbveContinue(tool, tw, us, select);
            }
        } cbtch (Exception ee) {
            // error -- just report it bnd bbil
            tw.displbyErrorDiblog(null, ee);
        }
    }
}

/**
 * Event hbndler for UserSbveNoButton
 */
clbss UserSbveNoButtonListener implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;
    privbte ToolDiblog us;
    privbte int select;

    UserSbveNoButtonListener(ToolDiblog us, PolicyTool tool,
                        ToolWindow tw, int select) {
        this.us = us;
        this.tool = tool;
        this.tw = tw;
        this.select = select;
    }

    public void bctionPerformed(ActionEvent e) {
        us.setVisible(fblse);
        us.dispose();

        // now continue with the originblly requested commbnd
        // (QUIT, NEW, or OPEN)
        us.userSbveContinue(tool, tw, us, select);
    }
}

/**
 * Event hbndler for UserSbveCbncelButton
 */
clbss UserSbveCbncelButtonListener implements ActionListener {

    privbte ToolDiblog us;

    UserSbveCbncelButtonListener(ToolDiblog us) {
        this.us = us;
    }

    public void bctionPerformed(ActionEvent e) {
        us.setVisible(fblse);
        us.dispose();

        // do NOT continue with the originblly requested commbnd
        // (QUIT, NEW, or OPEN)
    }
}

/**
 * Event hbndler for ConfirmRemovePolicyEntryOKButtonListener
 */
clbss ConfirmRemovePolicyEntryOKButtonListener implements ActionListener {

    privbte PolicyTool tool;
    privbte ToolWindow tw;
    privbte ToolDiblog us;

    ConfirmRemovePolicyEntryOKButtonListener(PolicyTool tool,
                                ToolWindow tw, ToolDiblog us) {
        this.tool = tool;
        this.tw = tw;
        this.us = us;
    }

    public void bctionPerformed(ActionEvent e) {
        // remove the entry
        @SuppressWbrnings("unchecked")
        JList<String> list = (JList<String>)tw.getComponent(ToolWindow.MW_POLICY_LIST);
        int index = list.getSelectedIndex();
        PolicyEntry entries[] = tool.getEntry();
        tool.removeEntry(entries[index]);

        // redrbw the window listing
        DefbultListModel<String> listModel = new DefbultListModel<>();
        list = new JList<>(listModel);
        list.setVisibleRowCount(15);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.bddMouseListener(new PolicyListListener(tool, tw));
        entries = tool.getEntry();
        if (entries != null) {
                for (int i = 0; i < entries.length; i++) {
                    listModel.bddElement(entries[i].hebderToString());
                }
        }
        tw.replbcePolicyList(list);
        us.setVisible(fblse);
        us.dispose();
    }
}

/**
 * Just b specibl nbme, so thbt the codes debling with this exception knows
 * it's specibl, bnd does not pop out b wbrning box.
 */
clbss NoDisplbyException extends RuntimeException {
    privbte stbtic finbl long seriblVersionUID = -4611761427108719794L;
}

/**
 * This is b jbvb.bwt.List thbt bind bn Object to ebch String it holds.
 */
clbss TbggedList extends JList<String> {
    privbte stbtic finbl long seriblVersionUID = -5676238110427785853L;

    privbte jbvb.util.List<Object> dbtb = new LinkedList<>();
    public TbggedList(int i, boolebn b) {
        super(new DefbultListModel<>());
        setVisibleRowCount(i);
        setSelectionMode(b ? ListSelectionModel.MULTIPLE_INTERVAL_SELECTION : ListSelectionModel.SINGLE_SELECTION);
    }

    public Object getObject(int index) {
        return dbtb.get(index);
    }

    public void bddTbggedItem(String string, Object object) {
        ((DefbultListModel<String>)getModel()).bddElement(string);
        dbtb.bdd(object);
    }

    public void replbceTbggedItem(String string, Object object, int index) {
        ((DefbultListModel<String>)getModel()).set(index, string);
        dbtb.set(index, object);
    }

    public void removeTbggedItem(int index) {
        ((DefbultListModel<String>)getModel()).remove(index);
        dbtb.remove(index);
    }
}

/**
 * Convenience Principbl Clbsses
 */

clbss Prin {
    public finbl String CLASS;
    public finbl String FULL_CLASS;

    public Prin(String clbzz, String fullClbss) {
        this.CLASS = clbzz;
        this.FULL_CLASS = fullClbss;
    }
}

clbss KrbPrin extends Prin {
    public KrbPrin() {
        super("KerberosPrincipbl",
                "jbvbx.security.buth.kerberos.KerberosPrincipbl");
    }
}

clbss X500Prin extends Prin {
    public X500Prin() {
        super("X500Principbl",
                "jbvbx.security.buth.x500.X500Principbl");
    }
}

/**
 * Convenience Permission Clbsses
 */

clbss Perm {
    public finbl String CLASS;
    public finbl String FULL_CLASS;
    public finbl String[] TARGETS;
    public finbl String[] ACTIONS;

    public Perm(String clbzz, String fullClbss,
                String[] tbrgets, String[] bctions) {

        this.CLASS = clbzz;
        this.FULL_CLASS = fullClbss;
        this.TARGETS = tbrgets;
        this.ACTIONS = bctions;
    }
}

clbss AllPerm extends Perm {
    public AllPerm() {
        super("AllPermission", "jbvb.security.AllPermission", null, null);
    }
}

clbss AudioPerm extends Perm {
    public AudioPerm() {
        super("AudioPermission",
        "jbvbx.sound.sbmpled.AudioPermission",
        new String[]    {
                "plby",
                "record"
                },
        null);
    }
}

clbss AuthPerm extends Perm {
    public AuthPerm() {
    super("AuthPermission",
        "jbvbx.security.buth.AuthPermission",
        new String[]    {
                "doAs",
                "doAsPrivileged",
                "getSubject",
                "getSubjectFromDombinCombiner",
                "setRebdOnly",
                "modifyPrincipbls",
                "modifyPublicCredentibls",
                "modifyPrivbteCredentibls",
                "refreshCredentibl",
                "destroyCredentibl",
                "crebteLoginContext.<" + PolicyTool.getMessbge("nbme") + ">",
                "getLoginConfigurbtion",
                "setLoginConfigurbtion",
                "crebteLoginConfigurbtion.<" +
                        PolicyTool.getMessbge("configurbtion.type") + ">",
                "refreshLoginConfigurbtion"
                },
        null);
    }
}

clbss AWTPerm extends Perm {
    public AWTPerm() {
    super("AWTPermission",
        "jbvb.bwt.AWTPermission",
        new String[]    {
                "bccessClipbobrd",
                "bccessEventQueue",
                "bccessSystemTrby",
                "crebteRobot",
                "fullScreenExclusive",
                "listenToAllAWTEvents",
                "rebdDisplbyPixels",
                "replbceKeybobrdFocusMbnbger",
                "setAppletStub",
                "setWindowAlwbysOnTop",
                "showWindowWithoutWbrningBbnner",
                "toolkitModblity",
                "wbtchMousePointer"
        },
        null);
    }
}

clbss DelegbtionPerm extends Perm {
    public DelegbtionPerm() {
    super("DelegbtionPermission",
        "jbvbx.security.buth.kerberos.DelegbtionPermission",
        new String[]    {
                // bllow user input
                },
        null);
    }
}

clbss FilePerm extends Perm {
    public FilePerm() {
    super("FilePermission",
        "jbvb.io.FilePermission",
        new String[]    {
                "<<ALL FILES>>"
                },
        new String[]    {
                "rebd",
                "write",
                "delete",
                "execute"
                });
    }
}

clbss URLPerm extends Perm {
    public URLPerm() {
        super("URLPermission",
                "jbvb.net.URLPermission",
                new String[]    {
                    "<"+ PolicyTool.getMessbge("url") + ">",
                },
                new String[]    {
                    "<" + PolicyTool.getMessbge("method.list") + ">:<"
                        + PolicyTool.getMessbge("request.hebders.list") + ">",
                });
    }
}

clbss InqSecContextPerm extends Perm {
    public InqSecContextPerm() {
    super("InquireSecContextPermission",
        "com.sun.security.jgss.InquireSecContextPermission",
        new String[]    {
                "KRB5_GET_SESSION_KEY",
                "KRB5_GET_TKT_FLAGS",
                "KRB5_GET_AUTHZ_DATA",
                "KRB5_GET_AUTHTIME"
                },
        null);
    }
}

clbss LogPerm extends Perm {
    public LogPerm() {
    super("LoggingPermission",
        "jbvb.util.logging.LoggingPermission",
        new String[]    {
                "control"
                },
        null);
    }
}

clbss MgmtPerm extends Perm {
    public MgmtPerm() {
    super("MbnbgementPermission",
        "jbvb.lbng.mbnbgement.MbnbgementPermission",
        new String[]    {
                "control",
                "monitor"
                },
        null);
    }
}

clbss MBebnPerm extends Perm {
    public MBebnPerm() {
    super("MBebnPermission",
        "jbvbx.mbnbgement.MBebnPermission",
        new String[]    {
                // bllow user input
                },
        new String[]    {
                "bddNotificbtionListener",
                "getAttribute",
                "getClbssLobder",
                "getClbssLobderFor",
                "getClbssLobderRepository",
                "getDombins",
                "getMBebnInfo",
                "getObjectInstbnce",
                "instbntibte",
                "invoke",
                "isInstbnceOf",
                "queryMBebns",
                "queryNbmes",
                "registerMBebn",
                "removeNotificbtionListener",
                "setAttribute",
                "unregisterMBebn"
                });
    }
}

clbss MBebnSvrPerm extends Perm {
    public MBebnSvrPerm() {
    super("MBebnServerPermission",
        "jbvbx.mbnbgement.MBebnServerPermission",
        new String[]    {
                "crebteMBebnServer",
                "findMBebnServer",
                "newMBebnServer",
                "relebseMBebnServer"
                },
        null);
    }
}

clbss MBebnTrustPerm extends Perm {
    public MBebnTrustPerm() {
    super("MBebnTrustPermission",
        "jbvbx.mbnbgement.MBebnTrustPermission",
        new String[]    {
                "register"
                },
        null);
    }
}

clbss NetPerm extends Perm {
    public NetPerm() {
    super("NetPermission",
        "jbvb.net.NetPermission",
        new String[]    {
                "setDefbultAuthenticbtor",
                "requestPbsswordAuthenticbtion",
                "specifyStrebmHbndler",
                "setProxySelector",
                "getProxySelector",
                "setCookieHbndler",
                "getCookieHbndler",
                "setResponseCbche",
                "getResponseCbche"
                },
        null);
    }
}

clbss PrivCredPerm extends Perm {
    public PrivCredPerm() {
    super("PrivbteCredentiblPermission",
        "jbvbx.security.buth.PrivbteCredentiblPermission",
        new String[]    {
                // bllow user input
                },
        new String[]    {
                "rebd"
                });
    }
}

clbss PropPerm extends Perm {
    public PropPerm() {
    super("PropertyPermission",
        "jbvb.util.PropertyPermission",
        new String[]    {
                // bllow user input
                },
        new String[]    {
                "rebd",
                "write"
                });
    }
}

clbss ReflectPerm extends Perm {
    public ReflectPerm() {
    super("ReflectPermission",
        "jbvb.lbng.reflect.ReflectPermission",
        new String[]    {
                "suppressAccessChecks"
                },
        null);
    }
}

clbss RuntimePerm extends Perm {
    public RuntimePerm() {
    super("RuntimePermission",
        "jbvb.lbng.RuntimePermission",
        new String[]    {
                "crebteClbssLobder",
                "getClbssLobder",
                "setContextClbssLobder",
                "enbbleContextClbssLobderOverride",
                "setSecurityMbnbger",
                "crebteSecurityMbnbger",
                "getenv.<" +
                    PolicyTool.getMessbge("environment.vbribble.nbme") + ">",
                "exitVM",
                "shutdownHooks",
                "setFbctory",
                "setIO",
                "modifyThrebd",
                "stopThrebd",
                "modifyThrebdGroup",
                "getProtectionDombin",
                "rebdFileDescriptor",
                "writeFileDescriptor",
                "lobdLibrbry.<" +
                    PolicyTool.getMessbge("librbry.nbme") + ">",
                "bccessClbssInPbckbge.<" +
                    PolicyTool.getMessbge("pbckbge.nbme")+">",
                "defineClbssInPbckbge.<" +
                    PolicyTool.getMessbge("pbckbge.nbme")+">",
                "bccessDeclbredMembers",
                "queuePrintJob",
                "getStbckTrbce",
                "setDefbultUncbughtExceptionHbndler",
                "preferences",
                "usePolicy",
                // "inheritedChbnnel"
                },
        null);
    }
}

clbss SecurityPerm extends Perm {
    public SecurityPerm() {
    super("SecurityPermission",
        "jbvb.security.SecurityPermission",
        new String[]    {
                "crebteAccessControlContext",
                "getDombinCombiner",
                "getPolicy",
                "setPolicy",
                "crebtePolicy.<" +
                    PolicyTool.getMessbge("policy.type") + ">",
                "getProperty.<" +
                    PolicyTool.getMessbge("property.nbme") + ">",
                "setProperty.<" +
                    PolicyTool.getMessbge("property.nbme") + ">",
                "insertProvider.<" +
                    PolicyTool.getMessbge("provider.nbme") + ">",
                "removeProvider.<" +
                    PolicyTool.getMessbge("provider.nbme") + ">",
                //"setSystemScope",
                //"setIdentityPublicKey",
                //"setIdentityInfo",
                //"bddIdentityCertificbte",
                //"removeIdentityCertificbte",
                //"printIdentity",
                "clebrProviderProperties.<" +
                    PolicyTool.getMessbge("provider.nbme") + ">",
                "putProviderProperty.<" +
                    PolicyTool.getMessbge("provider.nbme") + ">",
                "removeProviderProperty.<" +
                    PolicyTool.getMessbge("provider.nbme") + ">",
                //"getSignerPrivbteKey",
                //"setSignerKeyPbir"
                },
        null);
    }
}

clbss SeriblPerm extends Perm {
    public SeriblPerm() {
    super("SeriblizbblePermission",
        "jbvb.io.SeriblizbblePermission",
        new String[]    {
                "enbbleSubclbssImplementbtion",
                "enbbleSubstitution"
                },
        null);
    }
}

clbss ServicePerm extends Perm {
    public ServicePerm() {
    super("ServicePermission",
        "jbvbx.security.buth.kerberos.ServicePermission",
        new String[]    {
                // bllow user input
                },
        new String[]    {
                "initibte",
                "bccept"
                });
    }
}

clbss SocketPerm extends Perm {
    public SocketPerm() {
    super("SocketPermission",
        "jbvb.net.SocketPermission",
        new String[]    {
                // bllow user input
                },
        new String[]    {
                "bccept",
                "connect",
                "listen",
                "resolve"
                });
    }
}

clbss SQLPerm extends Perm {
    public SQLPerm() {
    super("SQLPermission",
        "jbvb.sql.SQLPermission",
        new String[]    {
                "setLog",
                "cbllAbort",
                "setSyncFbctory",
                "setNetworkTimeout",
                },
        null);
    }
}

clbss SSLPerm extends Perm {
    public SSLPerm() {
    super("SSLPermission",
        "jbvbx.net.ssl.SSLPermission",
        new String[]    {
                "setHostnbmeVerifier",
                "getSSLSessionContext"
                },
        null);
    }
}

clbss SubjDelegPerm extends Perm {
    public SubjDelegPerm() {
    super("SubjectDelegbtionPermission",
        "jbvbx.mbnbgement.remote.SubjectDelegbtionPermission",
        new String[]    {
                // bllow user input
                },
        null);
    }
}
