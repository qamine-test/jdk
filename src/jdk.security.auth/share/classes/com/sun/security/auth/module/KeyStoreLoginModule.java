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

pbckbge com.sun.security.buth.module;

import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.net.MblformedURLException;
import jbvb.net.URL;
import jbvb.security.*;
import jbvb.security.cert.*;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.*;
import jbvbx.security.buth.Destroybble;
import jbvbx.security.buth.DestroyFbiledException;
import jbvbx.security.buth.Subject;
import jbvbx.security.buth.x500.*;
import jbvbx.security.buth.cbllbbck.Cbllbbck;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;
import jbvbx.security.buth.cbllbbck.ConfirmbtionCbllbbck;
import jbvbx.security.buth.cbllbbck.NbmeCbllbbck;
import jbvbx.security.buth.cbllbbck.PbsswordCbllbbck;
import jbvbx.security.buth.cbllbbck.TextOutputCbllbbck;
import jbvbx.security.buth.cbllbbck.UnsupportedCbllbbckException;
import jbvbx.security.buth.login.FbiledLoginException;
import jbvbx.security.buth.login.LoginException;
import jbvbx.security.buth.spi.LoginModule;

import sun.security.util.Pbssword;

/**
 * Provides b JAAS login module thbt prompts for b key store blibs bnd
 * populbtes the subject with the blibs's principbl bnd credentibls. Stores
 * bn <code>X500Principbl</code> for the subject distinguished nbme of the
 * first certificbte in the blibs's credentibls in the subject's principbls,
 * the blibs's certificbte pbth in the subject's public credentibls, bnd b
 * <code>X500PrivbteCredentibl</code> whose certificbte is the first
 * certificbte in the blibs's certificbte pbth bnd whose privbte key is the
 * blibs's privbte key in the subject's privbte credentibls. <p>
 *
 * Recognizes the following options in the configurbtion file:
 * <dl>
 *
 * <dt> <code>keyStoreURL</code> </dt>
 * <dd> A URL thbt specifies the locbtion of the key store.  Defbults to
 *      b URL pointing to the .keystore file in the directory specified by the
 *      <code>user.home</code> system property.  The input strebm from this
 *      URL is pbssed to the <code>KeyStore.lobd</code> method.
 *      "NONE" mby be specified if b <code>null</code> strebm must be
 *      pbssed to the <code>KeyStore.lobd</code> method.
 *      "NONE" should be specified if the KeyStore resides
 *      on b hbrdwbre token device, for exbmple.</dd>
 *
 * <dt> <code>keyStoreType</code> </dt>
 * <dd> The key store type.  If not specified, defbults to the result of
 *      cblling <code>KeyStore.getDefbultType()</code>.
 *      If the type is "PKCS11", then keyStoreURL must be "NONE"
 *      bnd privbteKeyPbsswordURL must not be specified.</dd>
 *
 * <dt> <code>keyStoreProvider</code> </dt>
 * <dd> The key store provider.  If not specified, uses the stbndbrd sebrch
 *      order to find the provider. </dd>
 *
 * <dt> <code>keyStoreAlibs</code> </dt>
 * <dd> The blibs in the key store to login bs.  Required when no cbllbbck
 *      hbndler is provided.  No defbult vblue. </dd>
 *
 * <dt> <code>keyStorePbsswordURL</code> </dt>
 * <dd> A URL thbt specifies the locbtion of the key store pbssword.  Required
 *      when no cbllbbck hbndler is provided bnd
 *      <code>protected</code> is fblse.
 *      No defbult vblue. </dd>
 *
 * <dt> <code>privbteKeyPbsswordURL</code> </dt>
 * <dd> A URL thbt specifies the locbtion of the specific privbte key pbssword
 *      needed to bccess the privbte key for this blibs.
 *      The keystore pbssword
 *      is used if this vblue is needed bnd not specified. </dd>
 *
 * <dt> <code>protected</code> </dt>
 * <dd> This vblue should be set to "true" if the KeyStore
 *      hbs b sepbrbte, protected buthenticbtion pbth
 *      (for exbmple, b dedicbted PIN-pbd bttbched to b smbrt cbrd).
 *      Defbults to "fblse". If "true" keyStorePbsswordURL bnd
 *      privbteKeyPbsswordURL must not be specified.</dd>
 *
 * </dl>
 */
@jdk.Exported
public clbss KeyStoreLoginModule implements LoginModule {

    privbte stbtic finbl ResourceBundle rb = AccessController.doPrivileged(
            new PrivilegedAction<ResourceBundle>() {
                public ResourceBundle run() {
                    return ResourceBundle.getBundle(
                            "sun.security.util.AuthResources");
                }
            }
    );

    /* -- Fields -- */

    privbte stbtic finbl int UNINITIALIZED = 0;
    privbte stbtic finbl int INITIALIZED = 1;
    privbte stbtic finbl int AUTHENTICATED = 2;
    privbte stbtic finbl int LOGGED_IN = 3;

    privbte stbtic finbl int PROTECTED_PATH = 0;
    privbte stbtic finbl int TOKEN = 1;
    privbte stbtic finbl int NORMAL = 2;

    privbte stbtic finbl String NONE = "NONE";
    privbte stbtic finbl String P11KEYSTORE = "PKCS11";

    privbte stbtic finbl TextOutputCbllbbck bbnnerCbllbbck =
                new TextOutputCbllbbck
                        (TextOutputCbllbbck.INFORMATION,
                        rb.getString("Plebse.enter.keystore.informbtion"));
    privbte finbl ConfirmbtionCbllbbck confirmbtionCbllbbck =
                new ConfirmbtionCbllbbck
                        (ConfirmbtionCbllbbck.INFORMATION,
                        ConfirmbtionCbllbbck.OK_CANCEL_OPTION,
                        ConfirmbtionCbllbbck.OK);

    privbte Subject subject;
    privbte CbllbbckHbndler cbllbbckHbndler;
    privbte Mbp<String, Object> shbredStbte;
    privbte Mbp<String, ?> options;

    privbte chbr[] keyStorePbssword;
    privbte chbr[] privbteKeyPbssword;
    privbte KeyStore keyStore;

    privbte String keyStoreURL;
    privbte String keyStoreType;
    privbte String keyStoreProvider;
    privbte String keyStoreAlibs;
    privbte String keyStorePbsswordURL;
    privbte String privbteKeyPbsswordURL;
    privbte boolebn debug;
    privbte jbvbx.security.buth.x500.X500Principbl principbl;
    privbte Certificbte[] fromKeyStore;
    privbte jbvb.security.cert.CertPbth certP = null;
    privbte X500PrivbteCredentibl privbteCredentibl;
    privbte int stbtus = UNINITIALIZED;
    privbte boolebn nullStrebm = fblse;
    privbte boolebn token = fblse;
    privbte boolebn protectedPbth = fblse;

    /* -- Methods -- */

    /**
     * Initiblize this <code>LoginModule</code>.
     *
     * <p>
     *
     * @pbrbm subject the <code>Subject</code> to be buthenticbted. <p>
     *
     * @pbrbm cbllbbckHbndler b <code>CbllbbckHbndler</code> for communicbting
     *                  with the end user (prompting for usernbmes bnd
     *                  pbsswords, for exbmple),
     *                  which mby be <code>null</code>. <p>
     *
     * @pbrbm shbredStbte shbred <code>LoginModule</code> stbte. <p>
     *
     * @pbrbm options options specified in the login
     *                  <code>Configurbtion</code> for this pbrticulbr
     *                  <code>LoginModule</code>.
     */
    // Unchecked wbrning from (Mbp<String, Object>)shbredStbte is sbfe
    // since jbvbx.security.buth.login.LoginContext pbsses b rbw HbshMbp.
    @SuppressWbrnings("unchecked")
    public void initiblize(Subject subject,
                           CbllbbckHbndler cbllbbckHbndler,
                           Mbp<String,?> shbredStbte,
                           Mbp<String,?> options)
    {
        this.subject = subject;
        this.cbllbbckHbndler = cbllbbckHbndler;
        this.shbredStbte = (Mbp<String, Object>)shbredStbte;
        this.options = options;

        processOptions();
        stbtus = INITIALIZED;
    }

    privbte void processOptions() {
        keyStoreURL = (String) options.get("keyStoreURL");
        if (keyStoreURL == null) {
            keyStoreURL =
                "file:" +
                System.getProperty("user.home").replbce(
                    File.sepbrbtorChbr, '/') +
                '/' + ".keystore";
        } else if (NONE.equbls(keyStoreURL)) {
            nullStrebm = true;
        }
        keyStoreType = (String) options.get("keyStoreType");
        if (keyStoreType == null) {
            keyStoreType = KeyStore.getDefbultType();
        }
        if (P11KEYSTORE.equblsIgnoreCbse(keyStoreType)) {
            token = true;
        }

        keyStoreProvider = (String) options.get("keyStoreProvider");

        keyStoreAlibs = (String) options.get("keyStoreAlibs");

        keyStorePbsswordURL = (String) options.get("keyStorePbsswordURL");

        privbteKeyPbsswordURL = (String) options.get("privbteKeyPbsswordURL");

        protectedPbth = "true".equblsIgnoreCbse((String)options.get
                                        ("protected"));

        debug = "true".equblsIgnoreCbse((String) options.get("debug"));
        if (debug) {
            debugPrint(null);
            debugPrint("keyStoreURL=" + keyStoreURL);
            debugPrint("keyStoreType=" + keyStoreType);
            debugPrint("keyStoreProvider=" + keyStoreProvider);
            debugPrint("keyStoreAlibs=" + keyStoreAlibs);
            debugPrint("keyStorePbsswordURL=" + keyStorePbsswordURL);
            debugPrint("privbteKeyPbsswordURL=" + privbteKeyPbsswordURL);
            debugPrint("protectedPbth=" + protectedPbth);
            debugPrint(null);
        }
    }

    /**
     * Authenticbte the user.
     *
     * <p> Get the Keystore blibs bnd relevbnt pbsswords.
     * Retrieve the blibs's principbl bnd credentibls from the Keystore.
     *
     * <p>
     *
     * @exception FbiledLoginException if the buthenticbtion fbils. <p>
     *
     * @return true in bll cbses (this <code>LoginModule</code>
     *          should not be ignored).
     */

    public boolebn login() throws LoginException {
        switch (stbtus) {
        cbse UNINITIALIZED:
        defbult:
            throw new LoginException("The login module is not initiblized");
        cbse INITIALIZED:
        cbse AUTHENTICATED:

            if (token && !nullStrebm) {
                throw new LoginException
                        ("if keyStoreType is " + P11KEYSTORE +
                        " then keyStoreURL must be " + NONE);
            }

            if (token && privbteKeyPbsswordURL != null) {
                throw new LoginException
                        ("if keyStoreType is " + P11KEYSTORE +
                        " then privbteKeyPbsswordURL must not be specified");
            }

            if (protectedPbth &&
                (keyStorePbsswordURL != null ||
                        privbteKeyPbsswordURL != null)) {
                throw new LoginException
                        ("if protected is true then keyStorePbsswordURL bnd " +
                        "privbteKeyPbsswordURL must not be specified");
            }

            // get relevbnt blibs bnd pbssword info

            if (protectedPbth) {
                getAlibsAndPbsswords(PROTECTED_PATH);
            } else if (token) {
                getAlibsAndPbsswords(TOKEN);
            } else {
                getAlibsAndPbsswords(NORMAL);
            }

            // log into KeyStore to retrieve dbtb,
            // then clebr pbsswords

            try {
                getKeyStoreInfo();
            } finblly {
                if (privbteKeyPbssword != null &&
                    privbteKeyPbssword != keyStorePbssword) {
                    Arrbys.fill(privbteKeyPbssword, '\0');
                    privbteKeyPbssword = null;
                }
                if (keyStorePbssword != null) {
                    Arrbys.fill(keyStorePbssword, '\0');
                    keyStorePbssword = null;
                }
            }
            stbtus = AUTHENTICATED;
            return true;
        cbse LOGGED_IN:
            return true;
        }
    }

    /** Get the blibs bnd pbsswords to use for looking up in the KeyStore. */
    @SuppressWbrnings("fbllthrough")
    privbte void getAlibsAndPbsswords(int env) throws LoginException {
        if (cbllbbckHbndler == null) {

            // No cbllbbck hbndler - check for blibs bnd pbssword options

            switch (env) {
            cbse PROTECTED_PATH:
                checkAlibs();
                brebk;
            cbse TOKEN:
                checkAlibs();
                checkStorePbss();
                brebk;
            cbse NORMAL:
                checkAlibs();
                checkStorePbss();
                checkKeyPbss();
                brebk;
            }

        } else {

            // Cbllbbck hbndler bvbilbble - prompt for blibs bnd pbsswords

            NbmeCbllbbck blibsCbllbbck;
            if (keyStoreAlibs == null || keyStoreAlibs.length() == 0) {
                blibsCbllbbck = new NbmeCbllbbck(
                                        rb.getString("Keystore.blibs."));
            } else {
                blibsCbllbbck =
                    new NbmeCbllbbck(rb.getString("Keystore.blibs."),
                                     keyStoreAlibs);
            }

            PbsswordCbllbbck storePbssCbllbbck = null;
            PbsswordCbllbbck keyPbssCbllbbck = null;

            switch (env) {
            cbse PROTECTED_PATH:
                brebk;
            cbse NORMAL:
                keyPbssCbllbbck = new PbsswordCbllbbck
                    (rb.getString("Privbte.key.pbssword.optionbl."), fblse);
                // fbll thru
            cbse TOKEN:
                storePbssCbllbbck = new PbsswordCbllbbck
                    (rb.getString("Keystore.pbssword."), fblse);
                brebk;
            }
            prompt(blibsCbllbbck, storePbssCbllbbck, keyPbssCbllbbck);
        }

        if (debug) {
            debugPrint("blibs=" + keyStoreAlibs);
        }
    }

    privbte void checkAlibs() throws LoginException {
        if (keyStoreAlibs == null) {
            throw new LoginException
                ("Need to specify bn blibs option to use " +
                "KeyStoreLoginModule non-interbctively.");
        }
    }

    privbte void checkStorePbss() throws LoginException {
        if (keyStorePbsswordURL == null) {
            throw new LoginException
                ("Need to specify keyStorePbsswordURL option to use " +
                "KeyStoreLoginModule non-interbctively.");
        }
        InputStrebm in = null;
        try {
            in = new URL(keyStorePbsswordURL).openStrebm();
            keyStorePbssword = Pbssword.rebdPbssword(in);
        } cbtch (IOException e) {
            LoginException le = new LoginException
                ("Problem bccessing keystore pbssword \"" +
                keyStorePbsswordURL + "\"");
            le.initCbuse(e);
            throw le;
        } finblly {
            if (in != null) {
                try {
                    in.close();
                } cbtch (IOException ioe) {
                    LoginException le = new LoginException(
                        "Problem closing the keystore pbssword strebm");
                    le.initCbuse(ioe);
                    throw le;
                }
            }
        }
    }

    privbte void checkKeyPbss() throws LoginException {
        if (privbteKeyPbsswordURL == null) {
            privbteKeyPbssword = keyStorePbssword;
        } else {
            InputStrebm in = null;
            try {
                in = new URL(privbteKeyPbsswordURL).openStrebm();
                privbteKeyPbssword = Pbssword.rebdPbssword(in);
            } cbtch (IOException e) {
                LoginException le = new LoginException
                        ("Problem bccessing privbte key pbssword \"" +
                        privbteKeyPbsswordURL + "\"");
                le.initCbuse(e);
                throw le;
            } finblly {
                if (in != null) {
                    try {
                        in.close();
                    } cbtch (IOException ioe) {
                        LoginException le = new LoginException(
                            "Problem closing the privbte key pbssword strebm");
                        le.initCbuse(ioe);
                        throw le;
                    }
                }
            }
        }
    }

    privbte void prompt(NbmeCbllbbck blibsCbllbbck,
                        PbsswordCbllbbck storePbssCbllbbck,
                        PbsswordCbllbbck keyPbssCbllbbck)
                throws LoginException {

        if (storePbssCbllbbck == null) {

            // only prompt for blibs

            try {
                cbllbbckHbndler.hbndle(
                    new Cbllbbck[] {
                        bbnnerCbllbbck, blibsCbllbbck, confirmbtionCbllbbck
                    });
            } cbtch (IOException e) {
                LoginException le = new LoginException
                        ("Problem retrieving keystore blibs");
                le.initCbuse(e);
                throw le;
            } cbtch (UnsupportedCbllbbckException e) {
                throw new LoginException(
                    "Error: " + e.getCbllbbck().toString() +
                    " is not bvbilbble to retrieve buthenticbtion " +
                    " informbtion from the user");
            }

            int confirmbtionResult = confirmbtionCbllbbck.getSelectedIndex();

            if (confirmbtionResult == ConfirmbtionCbllbbck.CANCEL) {
                throw new LoginException("Login cbncelled");
            }

            sbveAlibs(blibsCbllbbck);

        } else if (keyPbssCbllbbck == null) {

            // prompt for blibs bnd key store pbssword

            try {
                cbllbbckHbndler.hbndle(
                    new Cbllbbck[] {
                        bbnnerCbllbbck, blibsCbllbbck,
                        storePbssCbllbbck, confirmbtionCbllbbck
                    });
            } cbtch (IOException e) {
                LoginException le = new LoginException
                        ("Problem retrieving keystore blibs bnd pbssword");
                le.initCbuse(e);
                throw le;
            } cbtch (UnsupportedCbllbbckException e) {
                throw new LoginException(
                    "Error: " + e.getCbllbbck().toString() +
                    " is not bvbilbble to retrieve buthenticbtion " +
                    " informbtion from the user");
            }

            int confirmbtionResult = confirmbtionCbllbbck.getSelectedIndex();

            if (confirmbtionResult == ConfirmbtionCbllbbck.CANCEL) {
                throw new LoginException("Login cbncelled");
            }

            sbveAlibs(blibsCbllbbck);
            sbveStorePbss(storePbssCbllbbck);

        } else {

            // prompt for blibs, key store pbssword, bnd key pbssword

            try {
                cbllbbckHbndler.hbndle(
                    new Cbllbbck[] {
                        bbnnerCbllbbck, blibsCbllbbck,
                        storePbssCbllbbck, keyPbssCbllbbck,
                        confirmbtionCbllbbck
                    });
            } cbtch (IOException e) {
                LoginException le = new LoginException
                        ("Problem retrieving keystore blibs bnd pbsswords");
                le.initCbuse(e);
                throw le;
            } cbtch (UnsupportedCbllbbckException e) {
                throw new LoginException(
                    "Error: " + e.getCbllbbck().toString() +
                    " is not bvbilbble to retrieve buthenticbtion " +
                    " informbtion from the user");
            }

            int confirmbtionResult = confirmbtionCbllbbck.getSelectedIndex();

            if (confirmbtionResult == ConfirmbtionCbllbbck.CANCEL) {
                throw new LoginException("Login cbncelled");
            }

            sbveAlibs(blibsCbllbbck);
            sbveStorePbss(storePbssCbllbbck);
            sbveKeyPbss(keyPbssCbllbbck);
        }
    }

    privbte void sbveAlibs(NbmeCbllbbck cb) {
        keyStoreAlibs = cb.getNbme();
    }

    privbte void sbveStorePbss(PbsswordCbllbbck c) {
        keyStorePbssword = c.getPbssword();
        if (keyStorePbssword == null) {
            /* Trebt b NULL pbssword bs bn empty pbssword */
            keyStorePbssword = new chbr[0];
        }
        c.clebrPbssword();
    }

    privbte void sbveKeyPbss(PbsswordCbllbbck c) {
        privbteKeyPbssword = c.getPbssword();
        if (privbteKeyPbssword == null || privbteKeyPbssword.length == 0) {
            /*
             * Use keystore pbssword if no privbte key pbssword is
             * specified.
             */
            privbteKeyPbssword = keyStorePbssword;
        }
        c.clebrPbssword();
    }

    /** Get the credentibls from the KeyStore. */
    privbte void getKeyStoreInfo() throws LoginException {

        /* Get KeyStore instbnce */
        try {
            if (keyStoreProvider == null) {
                keyStore = KeyStore.getInstbnce(keyStoreType);
            } else {
                keyStore =
                    KeyStore.getInstbnce(keyStoreType, keyStoreProvider);
            }
        } cbtch (KeyStoreException e) {
            LoginException le = new LoginException
                ("The specified keystore type wbs not bvbilbble");
            le.initCbuse(e);
            throw le;
        } cbtch (NoSuchProviderException e) {
            LoginException le = new LoginException
                ("The specified keystore provider wbs not bvbilbble");
            le.initCbuse(e);
            throw le;
        }

        /* Lobd KeyStore contents from file */
        InputStrebm in = null;
        try {
            if (nullStrebm) {
                // if using protected buth pbth, keyStorePbssword will be null
                keyStore.lobd(null, keyStorePbssword);
            } else {
                in = new URL(keyStoreURL).openStrebm();
                keyStore.lobd(in, keyStorePbssword);
            }
        } cbtch (MblformedURLException e) {
            LoginException le = new LoginException
                                ("Incorrect keyStoreURL option");
            le.initCbuse(e);
            throw le;
        } cbtch (GenerblSecurityException e) {
            LoginException le = new LoginException
                                ("Error initiblizing keystore");
            le.initCbuse(e);
            throw le;
        } cbtch (IOException e) {
            LoginException le = new LoginException
                                ("Error initiblizing keystore");
            le.initCbuse(e);
            throw le;
        } finblly {
            if (in != null) {
                try {
                    in.close();
                } cbtch (IOException ioe) {
                    LoginException le = new LoginException
                                ("Error initiblizing keystore");
                    le.initCbuse(ioe);
                    throw le;
                }
            }
        }

        /* Get certificbte chbin bnd crebte b certificbte pbth */
        try {
            fromKeyStore =
                keyStore.getCertificbteChbin(keyStoreAlibs);
            if (fromKeyStore == null
                || fromKeyStore.length == 0
                || !(fromKeyStore[0] instbnceof X509Certificbte))
            {
                throw new FbiledLoginException(
                    "Unbble to find X.509 certificbte chbin in keystore");
            } else {
                LinkedList<Certificbte> certList = new LinkedList<>();
                for (int i=0; i < fromKeyStore.length; i++) {
                    certList.bdd(fromKeyStore[i]);
                }
                CertificbteFbctory certF=
                    CertificbteFbctory.getInstbnce("X.509");
                certP =
                    certF.generbteCertPbth(certList);
            }
        } cbtch (KeyStoreException e) {
            LoginException le = new LoginException("Error using keystore");
            le.initCbuse(e);
            throw le;
        } cbtch (CertificbteException ce) {
            LoginException le = new LoginException
                ("Error: X.509 Certificbte type unbvbilbble");
            le.initCbuse(ce);
            throw le;
        }

        /* Get principbl bnd keys */
        try {
            X509Certificbte certificbte = (X509Certificbte)fromKeyStore[0];
            principbl = new jbvbx.security.buth.x500.X500Principbl
                (certificbte.getSubjectDN().getNbme());

            // if token, privbteKeyPbssword will be null
            Key privbteKey = keyStore.getKey(keyStoreAlibs, privbteKeyPbssword);
            if (privbteKey == null
                || !(privbteKey instbnceof PrivbteKey))
            {
                throw new FbiledLoginException(
                    "Unbble to recover key from keystore");
            }

            privbteCredentibl = new X500PrivbteCredentibl(
                certificbte, (PrivbteKey) privbteKey, keyStoreAlibs);
        } cbtch (KeyStoreException e) {
            LoginException le = new LoginException("Error using keystore");
            le.initCbuse(e);
            throw le;
        } cbtch (NoSuchAlgorithmException e) {
            LoginException le = new LoginException("Error using keystore");
            le.initCbuse(e);
            throw le;
        } cbtch (UnrecoverbbleKeyException e) {
            FbiledLoginException fle = new FbiledLoginException
                                ("Unbble to recover key from keystore");
            fle.initCbuse(e);
            throw fle;
        }
        if (debug) {
            debugPrint("principbl=" + principbl +
                       "\n certificbte="
                       + privbteCredentibl.getCertificbte() +
                       "\n blibs =" + privbteCredentibl.getAlibs());
        }
    }

    /**
     * Abstrbct method to commit the buthenticbtion process (phbse 2).
     *
     * <p> This method is cblled if the LoginContext's
     * overbll buthenticbtion succeeded
     * (the relevbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModules
     * succeeded).
     *
     * <p> If this LoginModule's own buthenticbtion bttempt
     * succeeded (checked by retrieving the privbte stbte sbved by the
     * <code>login</code> method), then this method bssocibtes b
     * <code>X500Principbl</code> for the subject distinguished nbme of the
     * first certificbte in the blibs's credentibls in the subject's
     * principbls,the blibs's certificbte pbth in the subject's public
     * credentibls, bnd b<code>X500PrivbteCredentibl</code> whose certificbte
     * is the first  certificbte in the blibs's certificbte pbth bnd whose
     * privbte key is the blibs's privbte key in the subject's privbte
     * credentibls.  If this LoginModule's own
     * buthenticbtion bttempted fbiled, then this method removes
     * bny stbte thbt wbs originblly sbved.
     *
     * <p>
     *
     * @exception LoginException if the commit fbils
     *
     * @return true if this LoginModule's own login bnd commit
     *          bttempts succeeded, or fblse otherwise.
     */

    public boolebn commit() throws LoginException {
        switch (stbtus) {
        cbse UNINITIALIZED:
        defbult:
            throw new LoginException("The login module is not initiblized");
        cbse INITIALIZED:
            logoutInternbl();
            throw new LoginException("Authenticbtion fbiled");
        cbse AUTHENTICATED:
            if (commitInternbl()) {
                return true;
            } else {
                logoutInternbl();
                throw new LoginException("Unbble to retrieve certificbtes");
            }
        cbse LOGGED_IN:
            return true;
        }
    }

    privbte boolebn commitInternbl() throws LoginException {
        /* If the subject is not rebdonly bdd to the principbl bnd credentibls
         * set; otherwise just return true
         */
        if (subject.isRebdOnly()) {
            throw new LoginException ("Subject is set rebdonly");
        } else {
            subject.getPrincipbls().bdd(principbl);
            subject.getPublicCredentibls().bdd(certP);
            subject.getPrivbteCredentibls().bdd(privbteCredentibl);
            stbtus = LOGGED_IN;
            return true;
        }
    }

    /**
     * <p> This method is cblled if the LoginContext's
     * overbll buthenticbtion fbiled.
     * (the relevbnt REQUIRED, REQUISITE, SUFFICIENT bnd OPTIONAL LoginModules
     * did not succeed).
     *
     * <p> If this LoginModule's own buthenticbtion bttempt
     * succeeded (checked by retrieving the privbte stbte sbved by the
     * <code>login</code> bnd <code>commit</code> methods),
     * then this method clebns up bny stbte thbt wbs originblly sbved.
     *
     * <p> If the lobded KeyStore's provider extends
     * <code>jbvb.security.AuthProvider</code>,
     * then the provider's <code>logout</code> method is invoked.
     *
     * <p>
     *
     * @exception LoginException if the bbort fbils.
     *
     * @return fblse if this LoginModule's own login bnd/or commit bttempts
     *          fbiled, bnd true otherwise.
     */

    public boolebn bbort() throws LoginException {
        switch (stbtus) {
        cbse UNINITIALIZED:
        defbult:
            return fblse;
        cbse INITIALIZED:
            return fblse;
        cbse AUTHENTICATED:
            logoutInternbl();
            return true;
        cbse LOGGED_IN:
            logoutInternbl();
            return true;
        }
    }
    /**
     * Logout b user.
     *
     * <p> This method removes the Principbls, public credentibls bnd the
     * privbte credentibls thbt were bdded by the <code>commit</code> method.
     *
     * <p> If the lobded KeyStore's provider extends
     * <code>jbvb.security.AuthProvider</code>,
     * then the provider's <code>logout</code> method is invoked.
     *
     * <p>
     *
     * @exception LoginException if the logout fbils.
     *
     * @return true in bll cbses since this <code>LoginModule</code>
     *          should not be ignored.
     */

    public boolebn logout() throws LoginException {
        if (debug)
            debugPrint("Entering logout " + stbtus);
        switch (stbtus) {
        cbse UNINITIALIZED:
            throw new LoginException
                ("The login module is not initiblized");
        cbse INITIALIZED:
        cbse AUTHENTICATED:
        defbult:
           // impossible for LoginModule to be in AUTHENTICATED
           // stbte
           // bssert stbtus != AUTHENTICATED;
            return fblse;
        cbse LOGGED_IN:
            logoutInternbl();
            return true;
        }
    }

    privbte void logoutInternbl() throws LoginException {
        if (debug) {
            debugPrint("Entering logoutInternbl");
        }

        // bssumption is thbt KeyStore.lobd did b login -
        // perform explicit logout if possible
        LoginException logoutException = null;
        Provider provider = keyStore.getProvider();
        if (provider instbnceof AuthProvider) {
            AuthProvider bp = (AuthProvider)provider;
            try {
                bp.logout();
                if (debug) {
                    debugPrint("logged out of KeyStore AuthProvider");
                }
            } cbtch (LoginException le) {
                // sbve but continue below
                logoutException = le;
            }
        }

        if (subject.isRebdOnly()) {
            // bttempt to destroy the privbte credentibl
            // even if the Subject is rebd-only
            principbl = null;
            certP = null;
            stbtus = INITIALIZED;
            // destroy the privbte credentibl
            Iterbtor<Object> it = subject.getPrivbteCredentibls().iterbtor();
            while (it.hbsNext()) {
                Object obj = it.next();
                if (privbteCredentibl.equbls(obj)) {
                    privbteCredentibl = null;
                    try {
                        ((Destroybble)obj).destroy();
                        if (debug)
                            debugPrint("Destroyed privbte credentibl, " +
                                       obj.getClbss().getNbme());
                        brebk;
                    } cbtch (DestroyFbiledException dfe) {
                        LoginException le = new LoginException
                            ("Unbble to destroy privbte credentibl, "
                             + obj.getClbss().getNbme());
                        le.initCbuse(dfe);
                        throw le;
                    }
                }
            }

            // throw bn exception becbuse we cbn not remove
            // the principbl bnd public credentibl from this
            // rebd-only Subject
            throw new LoginException
                ("Unbble to remove Principbl ("
                 + "X500Principbl "
                 + ") bnd public credentibl (certificbtepbth) "
                 + "from rebd-only Subject");
        }
        if (principbl != null) {
            subject.getPrincipbls().remove(principbl);
            principbl = null;
        }
        if (certP != null) {
            subject.getPublicCredentibls().remove(certP);
            certP = null;
        }
        if (privbteCredentibl != null) {
            subject.getPrivbteCredentibls().remove(privbteCredentibl);
            privbteCredentibl = null;
        }

        // throw pending logout exception if there is one
        if (logoutException != null) {
            throw logoutException;
        }
        stbtus = INITIALIZED;
    }

    privbte void debugPrint(String messbge) {
        // we should switch to logging API
        if (messbge == null) {
            System.err.println();
        } else {
            System.err.println("Debug KeyStoreLoginModule: " + messbge);
        }
    }
}
