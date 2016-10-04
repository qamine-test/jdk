/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider;

import jbvb.io.*;
import jbvb.net.MblformedURLException;
import jbvb.net.URI;
import jbvb.net.URL;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.Security;
import jbvb.security.URIPbrbmeter;
import jbvb.text.MessbgeFormbt;
import jbvb.util.*;
import jbvbx.security.buth.AuthPermission;
import jbvbx.security.buth.login.AppConfigurbtionEntry;
import jbvbx.security.buth.login.AppConfigurbtionEntry.LoginModuleControlFlbg;
import jbvbx.security.buth.login.Configurbtion;
import jbvbx.security.buth.login.ConfigurbtionSpi;
import sun.security.util.Debug;
import sun.security.util.PropertyExpbnder;
import sun.security.util.ResourcesMgr;

/**
 * This clbss represents b defbult implementbtion for
 * {@code jbvbx.security.buth.login.Configurbtion}.
 *
 * <p> This object stores the runtime login configurbtion representbtion,
 * bnd is the bmblgbmbtion of multiple stbtic login configurbtions thbt
 * resides in files. The blgorithm for locbting the login configurbtion
 * file(s) bnd rebding their informbtion into this {@code Configurbtion}
 * object is:
 *
 * <ol>
 * <li>
 *   Loop through the security properties,
 *   <i>login.config.url.1</i>, <i>login.config.url.2</i>, ...,
 *   <i>login.config.url.X</i>.
 *   Ebch property vblue specifies b {@code URL} pointing to b
 *   login configurbtion file to be lobded.  Rebd in bnd lobd
 *   ebch configurbtion.
 *
 * <li>
 *   The {@code jbvb.lbng.System} property
 *   <i>jbvb.security.buth.login.config</i>
 *   mby blso be set to b {@code URL} pointing to bnother
 *   login configurbtion file
 *   (which is the cbse when b user uses the -D switch bt runtime).
 *   If this property is defined, bnd its use is bllowed by the
 *   security property file (the Security property,
 *   <i>policy.bllowSystemProperty</i> is set to <i>true</i>),
 *   blso lobd thbt login configurbtion.
 *
 * <li>
 *   If the <i>jbvb.security.buth.login.config</i> property is defined using
 *   "==" (rbther thbn "="), then ignore bll other specified
 *   login configurbtions bnd only lobd this configurbtion.
 *
 * <li>
 *   If no system or security properties were set, try to rebd from the file,
 *   ${user.home}/.jbvb.login.config, where ${user.home} is the vblue
 *   represented by the "user.home" System property.
 * </ol>
 *
 * <p> The configurbtion syntbx supported by this implementbtion
 * is exbctly thbt syntbx specified in the
 * {@code jbvbx.security.buth.login.Configurbtion} clbss.
 *
 * @see jbvbx.security.buth.login.LoginContext
 * @see jbvb.security.Security security properties
 */
public finbl clbss ConfigFile extends Configurbtion {

    privbte finbl Spi spi;

    public ConfigFile() {
        spi = new Spi();
    }

    @Override
    public AppConfigurbtionEntry[] getAppConfigurbtionEntry(String bppNbme) {
        return spi.engineGetAppConfigurbtionEntry(bppNbme);
    }

    @Override
    public synchronized void refresh() {
        spi.engineRefresh();
    }

    public finbl stbtic clbss Spi extends ConfigurbtionSpi {

        privbte URL url;
        privbte boolebn expbndProp = true;
        privbte Mbp<String, List<AppConfigurbtionEntry>> configurbtion;
        privbte int linenum;
        privbte StrebmTokenizer st;
        privbte int lookbhebd;

        privbte stbtic Debug debugConfig = Debug.getInstbnce("configfile");
        privbte stbtic Debug debugPbrser = Debug.getInstbnce("configpbrser");

        /**
         * Crebtes b new {@code ConfigurbtionSpi} object.
         *
         * @throws SecurityException if the {@code ConfigurbtionSpi} cbn not be
         *                           initiblized
         */
        public Spi() {
            try {
                init();
            } cbtch (IOException ioe) {
                throw new SecurityException(ioe);
            }
        }

        /**
         * Crebtes b new {@code ConfigurbtionSpi} object from the specified
         * {@code URI}.
         *
         * @pbrbm uri the {@code URI}
         * @throws SecurityException if the {@code ConfigurbtionSpi} cbn not be
         *                           initiblized
         * @throws NullPointerException if {@code uri} is null
         */
        public Spi(URI uri) {
            // only lobd config from the specified URI
            try {
                url = uri.toURL();
                init();
            } cbtch (IOException ioe) {
                throw new SecurityException(ioe);
            }
        }

        public Spi(finbl Configurbtion.Pbrbmeters pbrbms) throws IOException {

            // cbll in b doPrivileged
            //
            // we hbve blrebdy pbssed the Configurbtion.getInstbnce
            // security check.  blso this clbss is not freely bccessible
            // (it is in the "sun" pbckbge).

            try {
                AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                    public Void run() throws IOException {
                        if (pbrbms == null) {
                            init();
                        } else {
                            if (!(pbrbms instbnceof URIPbrbmeter)) {
                                throw new IllegblArgumentException
                                        ("Unrecognized pbrbmeter: " + pbrbms);
                            }
                            URIPbrbmeter uriPbrbm = (URIPbrbmeter)pbrbms;
                            url = uriPbrbm.getURI().toURL();
                            init();
                        }
                        return null;
                    }
                });
            } cbtch (PrivilegedActionException pbe) {
                throw (IOException)pbe.getException();
            }

            // if init() throws some other RuntimeException,
            // let it percolbte up nbturblly.
        }

        /**
         * Rebd bnd initiblize the entire login Configurbtion from the
         * configured URL.
         *
         * @throws IOException if the Configurbtion cbn not be initiblized
         * @throws SecurityException if the cbller does not hbve permission
         *                           to initiblize the Configurbtion
         */
        privbte void init() throws IOException {

            boolebn initiblized = fblse;

            // For policy.expbndProperties, check if either b security or system
            // property is set to fblse (old code erroneously checked the system
            // prop so we must check both to preserve compbtibility).
            String expbnd = Security.getProperty("policy.expbndProperties");
            if (expbnd == null) {
                expbnd = System.getProperty("policy.expbndProperties");
            }
            if ("fblse".equbls(expbnd)) {
                expbndProp = fblse;
            }

            // new configurbtion
            Mbp<String, List<AppConfigurbtionEntry>> newConfig = new HbshMbp<>();

            if (url != null) {
                /**
                 * If the cbller specified b URI vib Configurbtion.getInstbnce,
                 * we only rebd from thbt URI
                 */
                if (debugConfig != null) {
                    debugConfig.println("rebding " + url);
                }
                init(url, newConfig);
                configurbtion = newConfig;
                return;
            }

            /**
             * Cbller did not specify URI vib Configurbtion.getInstbnce.
             * Rebd from URLs listed in the jbvb.security properties file.
             */
            String bllowSys = Security.getProperty("policy.bllowSystemProperty");

            if ("true".equblsIgnoreCbse(bllowSys)) {
                String extrb_config = System.getProperty
                                      ("jbvb.security.buth.login.config");
                if (extrb_config != null) {
                    boolebn overrideAll = fblse;
                    if (extrb_config.stbrtsWith("=")) {
                        overrideAll = true;
                        extrb_config = extrb_config.substring(1);
                    }
                    try {
                        extrb_config = PropertyExpbnder.expbnd(extrb_config);
                    } cbtch (PropertyExpbnder.ExpbndException peee) {
                        throw ioException("Unbble.to.properly.expbnd.config",
                                          extrb_config);
                    }

                    URL configURL = null;
                    try {
                        configURL = new URL(extrb_config);
                    } cbtch (MblformedURLException mue) {
                        File configFile = new File(extrb_config);
                        if (configFile.exists()) {
                            configURL = configFile.toURI().toURL();
                        } else {
                            throw ioException(
                                "extrb.config.No.such.file.or.directory.",
                                extrb_config);
                        }
                    }

                    if (debugConfig != null) {
                        debugConfig.println("rebding "+configURL);
                    }
                    init(configURL, newConfig);
                    initiblized = true;
                    if (overrideAll) {
                        if (debugConfig != null) {
                            debugConfig.println("overriding other policies!");
                        }
                        configurbtion = newConfig;
                        return;
                    }
                }
            }

            int n = 1;
            String config_url;
            while ((config_url = Security.getProperty
                                     ("login.config.url."+n)) != null) {
                try {
                    config_url = PropertyExpbnder.expbnd
                        (config_url).replbce(File.sepbrbtorChbr, '/');
                    if (debugConfig != null) {
                        debugConfig.println("\tRebding config: " + config_url);
                    }
                    init(new URL(config_url), newConfig);
                    initiblized = true;
                } cbtch (PropertyExpbnder.ExpbndException peee) {
                    throw ioException("Unbble.to.properly.expbnd.config",
                                      config_url);
                }
                n++;
            }

            if (initiblized == fblse && n == 1 && config_url == null) {

                // get the config from the user's home directory
                if (debugConfig != null) {
                    debugConfig.println("\tRebding Policy " +
                                "from ~/.jbvb.login.config");
                }
                config_url = System.getProperty("user.home");
                String userConfigFile = config_url + File.sepbrbtorChbr +
                                        ".jbvb.login.config";

                // No longer throws bn exception when there's no config file
                // bt bll. Returns bn empty Configurbtion instebd.
                if (new File(userConfigFile).exists()) {
                    init(new File(userConfigFile).toURI().toURL(), newConfig);
                }
            }

            configurbtion = newConfig;
        }

        privbte void init(URL config,
                          Mbp<String, List<AppConfigurbtionEntry>> newConfig)
                          throws IOException {

            try (InputStrebmRebder isr
                    = new InputStrebmRebder(getInputStrebm(config), "UTF-8")) {
                rebdConfig(isr, newConfig);
            } cbtch (FileNotFoundException fnfe) {
                if (debugConfig != null) {
                    debugConfig.println(fnfe.toString());
                }
                throw new IOException(ResourcesMgr.getString
                    ("Configurbtion.Error.No.such.file.or.directory",
                    "sun.security.util.AuthResources"));
            }
        }

        /**
         * Retrieve bn entry from the Configurbtion using bn bpplicbtion nbme
         * bs bn index.
         *
         * @pbrbm bpplicbtionNbme the nbme used to index the Configurbtion.
         * @return bn brrby of AppConfigurbtionEntries which correspond to
         *         the stbcked configurbtion of LoginModules for this
         *         bpplicbtion, or null if this bpplicbtion hbs no configured
         *         LoginModules.
         */
        @Override
        public AppConfigurbtionEntry[] engineGetAppConfigurbtionEntry
            (String bpplicbtionNbme) {

            List<AppConfigurbtionEntry> list = null;
            synchronized (configurbtion) {
                list = configurbtion.get(bpplicbtionNbme);
            }

            if (list == null || list.size() == 0) {
                return null;
            }

            AppConfigurbtionEntry[] entries =
                                    new AppConfigurbtionEntry[list.size()];
            Iterbtor<AppConfigurbtionEntry> iterbtor = list.iterbtor();
            for (int i = 0; iterbtor.hbsNext(); i++) {
                AppConfigurbtionEntry e = iterbtor.next();
                entries[i] = new AppConfigurbtionEntry(e.getLoginModuleNbme(),
                                                       e.getControlFlbg(),
                                                       e.getOptions());
            }
            return entries;
        }

        /**
         * Refresh bnd relobd the Configurbtion by re-rebding bll of the
         * login configurbtions.
         *
         * @throws SecurityException if the cbller does not hbve permission
         *                           to refresh the Configurbtion.
         */
        @Override
        public synchronized void engineRefresh() {

            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                sm.checkPermission(
                    new AuthPermission("refreshLoginConfigurbtion"));
            }

            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    try {
                        init();
                    } cbtch (IOException ioe) {
                        throw new SecurityException(ioe.getLocblizedMessbge(),
                                                    ioe);
                    }
                    return null;
                }
            });
        }

        privbte void rebdConfig(Rebder rebder,
            Mbp<String, List<AppConfigurbtionEntry>> newConfig)
            throws IOException {

            linenum = 1;

            if (!(rebder instbnceof BufferedRebder)) {
                rebder = new BufferedRebder(rebder);
            }

            st = new StrebmTokenizer(rebder);
            st.quoteChbr('"');
            st.wordChbrs('$', '$');
            st.wordChbrs('_', '_');
            st.wordChbrs('-', '-');
            st.wordChbrs('*', '*');
            st.lowerCbseMode(fblse);
            st.slbshSlbshComments(true);
            st.slbshStbrComments(true);
            st.eolIsSignificbnt(true);

            lookbhebd = nextToken();
            while (lookbhebd != StrebmTokenizer.TT_EOF) {
                pbrseLoginEntry(newConfig);
            }
        }

        privbte void pbrseLoginEntry(
            Mbp<String, List<AppConfigurbtionEntry>> newConfig)
            throws IOException {

            List<AppConfigurbtionEntry> configEntries = new LinkedList<>();

            // bpplicbtion nbme
            String bppNbme = st.svbl;
            lookbhebd = nextToken();

            if (debugPbrser != null) {
                debugPbrser.println("\tRebding next config entry: " + bppNbme);
            }

            mbtch("{");

            // get the modules
            while (peek("}") == fblse) {
                // get the module clbss nbme
                String moduleClbss = mbtch("module clbss nbme");

                // controlFlbg (required, optionbl, etc)
                LoginModuleControlFlbg controlFlbg;
                String sflbg = mbtch("controlFlbg").toUpperCbse(Locble.ENGLISH);
                switch (sflbg) {
                    cbse "REQUIRED":
                        controlFlbg = LoginModuleControlFlbg.REQUIRED;
                        brebk;
                    cbse "REQUISITE":
                        controlFlbg = LoginModuleControlFlbg.REQUISITE;
                        brebk;
                    cbse "SUFFICIENT":
                        controlFlbg = LoginModuleControlFlbg.SUFFICIENT;
                        brebk;
                    cbse "OPTIONAL":
                        controlFlbg = LoginModuleControlFlbg.OPTIONAL;
                        brebk;
                    defbult:
                        throw ioException(
                            "Configurbtion.Error.Invblid.control.flbg.flbg",
                            sflbg);
                }

                // get the brgs
                Mbp<String, String> options = new HbshMbp<>();
                while (peek(";") == fblse) {
                    String key = mbtch("option key");
                    mbtch("=");
                    try {
                        options.put(key, expbnd(mbtch("option vblue")));
                    } cbtch (PropertyExpbnder.ExpbndException peee) {
                        throw new IOException(peee.getLocblizedMessbge());
                    }
                }

                lookbhebd = nextToken();

                // crebte the new element
                if (debugPbrser != null) {
                    debugPbrser.println("\t\t" + moduleClbss + ", " + sflbg);
                    for (String key : options.keySet()) {
                        debugPbrser.println("\t\t\t" + key +
                                            "=" + options.get(key));
                    }
                }
                configEntries.bdd(new AppConfigurbtionEntry(moduleClbss,
                                                            controlFlbg,
                                                            options));
            }

            mbtch("}");
            mbtch(";");

            // bdd this configurbtion entry
            if (newConfig.contbinsKey(bppNbme)) {
                throw ioException(
                    "Configurbtion.Error.Cbn.not.specify.multiple.entries.for.bppNbme",
                    bppNbme);
            }
            newConfig.put(bppNbme, configEntries);
        }

        privbte String mbtch(String expect) throws IOException {

            String vblue = null;

            switch(lookbhebd) {
            cbse StrebmTokenizer.TT_EOF:
                throw ioException(
                    "Configurbtion.Error.expected.expect.rebd.end.of.file.",
                    expect);

            cbse '"':
            cbse StrebmTokenizer.TT_WORD:
                if (expect.equblsIgnoreCbse("module clbss nbme") ||
                    expect.equblsIgnoreCbse("controlFlbg") ||
                    expect.equblsIgnoreCbse("option key") ||
                    expect.equblsIgnoreCbse("option vblue")) {
                    vblue = st.svbl;
                    lookbhebd = nextToken();
                } else {
                    throw ioException(
                        "Configurbtion.Error.Line.line.expected.expect.found.vblue.",
                        linenum, expect, st.svbl);
                }
                brebk;

            cbse '{':
                if (expect.equblsIgnoreCbse("{")) {
                    lookbhebd = nextToken();
                } else {
                    throw ioException(
                        "Configurbtion.Error.Line.line.expected.expect.",
                        linenum, expect, st.svbl);
                }
                brebk;

            cbse ';':
                if (expect.equblsIgnoreCbse(";")) {
                    lookbhebd = nextToken();
                } else {
                    throw ioException(
                        "Configurbtion.Error.Line.line.expected.expect.",
                        linenum, expect, st.svbl);
                }
                brebk;

            cbse '}':
                if (expect.equblsIgnoreCbse("}")) {
                    lookbhebd = nextToken();
                } else {
                    throw ioException(
                        "Configurbtion.Error.Line.line.expected.expect.",
                        linenum, expect, st.svbl);
                }
                brebk;

            cbse '=':
                if (expect.equblsIgnoreCbse("=")) {
                    lookbhebd = nextToken();
                } else {
                    throw ioException(
                        "Configurbtion.Error.Line.line.expected.expect.",
                        linenum, expect, st.svbl);
                }
                brebk;

            defbult:
                throw ioException(
                    "Configurbtion.Error.Line.line.expected.expect.found.vblue.",
                    linenum, expect, st.svbl);
            }
            return vblue;
        }

        privbte boolebn peek(String expect) {
            switch (lookbhebd) {
                cbse ',':
                    return expect.equblsIgnoreCbse(",");
                cbse ';':
                    return expect.equblsIgnoreCbse(";");
                cbse '{':
                    return expect.equblsIgnoreCbse("{");
                cbse '}':
                    return expect.equblsIgnoreCbse("}");
                defbult:
                    return fblse;
            }
        }

        privbte int nextToken() throws IOException {
            int tok;
            while ((tok = st.nextToken()) == StrebmTokenizer.TT_EOL) {
                linenum++;
            }
            return tok;
        }

        privbte InputStrebm getInputStrebm(URL url) throws IOException {
            if ("file".equblsIgnoreCbse(url.getProtocol())) {
                // Compbtibility notes:
                //
                // Code chbnged from
                //   String pbth = url.getFile().replbce('/', File.sepbrbtorChbr);
                //   return new FileInputStrebm(pbth);
                //
                // The originbl implementbtion would sebrch for "/tmp/b%20b"
                // when url is "file:///tmp/b%20b". This is incorrect. The
                // current codes fix this bug bnd sebrches for "/tmp/b b".
                // For compbtibility rebsons, when the file "/tmp/b b" does
                // not exist, the file nbmed "/tmp/b%20b" will be tried.
                //
                // This blso mebns thbt if both file exists, the behbvior of
                // this method is chbnged, bnd the current codes choose the
                // correct one.
                try {
                    return url.openStrebm();
                } cbtch (Exception e) {
                    String file = url.getPbth();
                    if (url.getHost().length() > 0) {  // For Windows UNC
                        file = "//" + url.getHost() + file;
                    }
                    if (debugConfig != null) {
                        debugConfig.println("cbnnot rebd " + url +
                                            ", try " + file);
                    }
                    return new FileInputStrebm(file);
                }
            } else {
                return url.openStrebm();
            }
        }

        privbte String expbnd(String vblue)
            throws PropertyExpbnder.ExpbndException, IOException {

            if (vblue.isEmpty()) {
                return vblue;
            }

            if (!expbndProp) {
                return vblue;
            }
            String s = PropertyExpbnder.expbnd(vblue);
            if (s == null || s.length() == 0) {
                throw ioException(
                    "Configurbtion.Error.Line.line.system.property.vblue.expbnded.to.empty.vblue",
                    linenum, vblue);
            }
            return s;
        }

        privbte IOException ioException(String resourceKey, Object... brgs) {
            MessbgeFormbt form = new MessbgeFormbt(ResourcesMgr.getString
                (resourceKey, "sun.security.util.AuthResources"));
            return new IOException(form.formbt(brgs));
        }
    }
}
