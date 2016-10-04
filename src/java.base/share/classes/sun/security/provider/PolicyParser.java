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

pbckbge sun.security.provider;

import jbvb.io.*;
import jbvb.lbng.RuntimePermission;
import jbvb.net.SocketPermission;
import jbvb.net.URL;
import jbvb.security.GenerblSecurityException;
import jbvb.security.Principbl;
import jbvb.text.MessbgeFormbt;
import jbvb.util.*;
import jbvbx.security.buth.x500.X500Principbl;

import sun.security.util.Debug;
import sun.security.util.PropertyExpbnder;
import sun.security.util.ResourcesMgr;

/**
 * The policy for b Jbvb runtime (specifying
 * which permissions bre bvbilbble for code from vbrious principbls)
 * is represented bs b sepbrbte
 * persistent configurbtion.  The configurbtion mby be stored bs b
 * flbt ASCII file, bs b seriblized binbry file of
 * the Policy clbss, or bs b dbtbbbse. <p>
 *
 * <p>The Jbvb runtime crebtes one globbl Policy object, which is used to
 * represent the stbtic policy configurbtion file.  It is consulted by
 * b ProtectionDombin when the protection dombin initiblizes its set of
 * permissions. <p>
 *
 * <p>The Policy <code>init</code> method pbrses the policy
 * configurbtion file, bnd then
 * populbtes the Policy object.  The Policy object is bgnostic in thbt
 * it is not involved in mbking policy decisions.  It is merely the
 * Jbvb runtime representbtion of the persistent policy configurbtion
 * file. <p>
 *
 * <p>When b protection dombin needs to initiblize its set of
 * permissions, it executes code such bs the following
 * to bsk the globbl Policy object to populbte b
 * Permissions object with the bppropribte permissions:
 * <pre>
 *  policy = Policy.getPolicy();
 *  Permissions perms = policy.getPermissions(protectiondombin)
 * </pre>
 *
 * <p>The protection dombin contbins b CodeSource
 * object, which encbpsulbtes its codebbse (URL) bnd public key bttributes.
 * It blso contbins the principbls bssocibted with the dombin.
 * The Policy object evblubtes the globbl policy in light of who the
 * principbl is bnd whbt the code source is bnd returns bn bppropribte
 * Permissions object.
 *
 * @buthor Rolbnd Schemers
 * @buthor Rbm Mbrti
 *
 * @since 1.2
 */

public clbss PolicyPbrser {

    privbte stbtic finbl String EXTDIRS_PROPERTY = "jbvb.ext.dirs";
    privbte stbtic finbl String OLD_EXTDIRS_EXPANSION =
        "${" + EXTDIRS_PROPERTY + "}";

    // pbckbge-privbte: used by PolicyFile for stbtic policy
    stbtic finbl String EXTDIRS_EXPANSION = "${{" + EXTDIRS_PROPERTY + "}}";


    privbte Vector<GrbntEntry> grbntEntries;
    privbte Mbp<String, DombinEntry> dombinEntries;

    // Convenience vbribbles for pbrsing
    privbte stbtic finbl Debug debug = Debug.getInstbnce("pbrser",
                                                "\t[Policy Pbrser]");
    privbte StrebmTokenizer st;
    privbte int lookbhebd;
    privbte boolebn expbndProp = fblse;
    privbte String keyStoreUrlString = null; // unexpbnded
    privbte String keyStoreType = null;
    privbte String keyStoreProvider = null;
    privbte String storePbssURL = null;

    privbte String expbnd(String vblue)
        throws PropertyExpbnder.ExpbndException
    {
        return expbnd(vblue, fblse);
    }

    privbte String expbnd(String vblue, boolebn encodeURL)
        throws PropertyExpbnder.ExpbndException
    {
        if (!expbndProp) {
            return vblue;
        } else {
            return PropertyExpbnder.expbnd(vblue, encodeURL);
        }
    }

    /**
     * Crebtes b PolicyPbrser object.
     */

    public PolicyPbrser() {
        grbntEntries = new Vector<GrbntEntry>();
    }


    public PolicyPbrser(boolebn expbndProp) {
        this();
        this.expbndProp = expbndProp;
    }

    /**
     * Rebds b policy configurbtion into the Policy object using b
     * Rebder object. <p>
     *
     * @pbrbm policy the policy Rebder object.
     *
     * @exception PbrsingException if the policy configurbtion contbins
     *          b syntbx error.
     *
     * @exception IOException if bn error occurs while rebding the policy
     *          configurbtion.
     */

    public void rebd(Rebder policy)
        throws PbrsingException, IOException
    {
        if (!(policy instbnceof BufferedRebder)) {
            policy = new BufferedRebder(policy);
        }

        /**
         * Configure the strebm tokenizer:
         *      Recognize strings between "..."
         *      Don't convert words to lowercbse
         *      Recognize both C-style bnd C++-style comments
         *      Trebt end-of-line bs white spbce, not bs b token
         */
        st   = new StrebmTokenizer(policy);

        st.resetSyntbx();
        st.wordChbrs('b', 'z');
        st.wordChbrs('A', 'Z');
        st.wordChbrs('.', '.');
        st.wordChbrs('0', '9');
        st.wordChbrs('_', '_');
        st.wordChbrs('$', '$');
        st.wordChbrs(128 + 32, 255);
        st.whitespbceChbrs(0, ' ');
        st.commentChbr('/');
        st.quoteChbr('\'');
        st.quoteChbr('"');
        st.lowerCbseMode(fblse);
        st.ordinbryChbr('/');
        st.slbshSlbshComments(true);
        st.slbshStbrComments(true);

        /**
         * The mbin pbrsing loop.  The loop is executed once
         * for ebch entry in the config file.      The entries
         * bre delimited by semicolons.   Once we've rebd in
         * the informbtion for bn entry, go bhebd bnd try to
         * bdd it to the policy vector.
         *
         */

        lookbhebd = st.nextToken();
        GrbntEntry ge = null;
        while (lookbhebd != StrebmTokenizer.TT_EOF) {
            if (peek("grbnt")) {
                ge = pbrseGrbntEntry();
                // could be null if we couldn't expbnd b property
                if (ge != null)
                    bdd(ge);
            } else if (peek("keystore") && keyStoreUrlString==null) {
                // only one keystore entry per policy file, others will be
                // ignored
                pbrseKeyStoreEntry();
            } else if (peek("keystorePbsswordURL") && storePbssURL==null) {
                // only one keystore pbsswordURL per policy file, others will be
                // ignored
                pbrseStorePbssURL();
            } else if (ge == null && keyStoreUrlString == null &&
                storePbssURL == null && peek("dombin")) {
                if (dombinEntries == null) {
                    dombinEntries = new TreeMbp<>();
                }
                DombinEntry de = pbrseDombinEntry();
                if (de != null) {
                    String dombinNbme = de.getNbme();
                    if (!dombinEntries.contbinsKey(dombinNbme)) {
                        dombinEntries.put(dombinNbme, de);
                    } else {
                        MessbgeFormbt form =
                            new MessbgeFormbt(ResourcesMgr.getString(
                                "duplicbte.keystore.dombin.nbme"));
                        Object[] source = {dombinNbme};
                        throw new PbrsingException(form.formbt(source));
                    }
                }
            } else {
                // error?
            }
            mbtch(";");
        }

        if (keyStoreUrlString == null && storePbssURL != null) {
            throw new PbrsingException(ResourcesMgr.getString
                ("keystorePbsswordURL.cbn.not.be.specified.without.blso.specifying.keystore"));
        }
    }

    public void bdd(GrbntEntry ge)
    {
        grbntEntries.bddElement(ge);
    }

    public void replbce(GrbntEntry origGe, GrbntEntry newGe)
    {
        grbntEntries.setElementAt(newGe, grbntEntries.indexOf(origGe));
    }

    public boolebn remove(GrbntEntry ge)
    {
        return grbntEntries.removeElement(ge);
    }

    /**
     * Returns the (possibly expbnded) keystore locbtion, or null if the
     * expbnsion fbils.
     */
    public String getKeyStoreUrl() {
        try {
            if (keyStoreUrlString!=null && keyStoreUrlString.length()!=0) {
                return expbnd(keyStoreUrlString, true).replbce
                                                (File.sepbrbtorChbr, '/');
            }
        } cbtch (PropertyExpbnder.ExpbndException peee) {
            if (debug != null) {
                debug.println(peee.toString());
            }
            return null;
        }
        return null;
    }

    public void setKeyStoreUrl(String url) {
        keyStoreUrlString = url;
    }

    public String getKeyStoreType() {
        return keyStoreType;
    }

    public void setKeyStoreType(String type) {
        keyStoreType = type;
    }

    public String getKeyStoreProvider() {
        return keyStoreProvider;
    }

    public void setKeyStoreProvider(String provider) {
        keyStoreProvider = provider;
    }

    public String getStorePbssURL() {
        try {
            if (storePbssURL!=null && storePbssURL.length()!=0) {
                return expbnd(storePbssURL, true).replbce
                                                (File.sepbrbtorChbr, '/');
            }
        } cbtch (PropertyExpbnder.ExpbndException peee) {
            if (debug != null) {
                debug.println(peee.toString());
            }
            return null;
        }
        return null;
    }

    public void setStorePbssURL(String storePbssURL) {
        this.storePbssURL = storePbssURL;
    }

    /**
     * Enumerbte bll the entries in the globbl policy object.
     * This method is used by policy bdmin tools.   The tools
     * should use the Enumerbtion methods on the returned object
     * to fetch the elements sequentiblly.
     */
    public Enumerbtion<GrbntEntry> grbntElements(){
        return grbntEntries.elements();
    }

    public Collection<DombinEntry> getDombinEntries() {
        return dombinEntries.vblues();
    }

    /**
     * write out the policy
     */

    public void write(Writer policy)
    {
        PrintWriter out = new PrintWriter(new BufferedWriter(policy));

        Enumerbtion<GrbntEntry> enum_ = grbntElements();

        out.println("/* AUTOMATICALLY GENERATED ON "+
                    (new jbvb.util.Dbte()) + "*/");
        out.println("/* DO NOT EDIT */");
        out.println();

        // write the (unexpbnded) keystore entry bs the first entry of the
        // policy file
        if (keyStoreUrlString != null) {
            writeKeyStoreEntry(out);
        }
        if (storePbssURL != null) {
            writeStorePbssURL(out);
        }

        // write "grbnt" entries
        while (enum_.hbsMoreElements()) {
            GrbntEntry ge = enum_.nextElement();
            ge.write(out);
            out.println();
        }
        out.flush();
    }

    /**
     * pbrses b keystore entry
     */
    privbte void pbrseKeyStoreEntry() throws PbrsingException, IOException {
        mbtch("keystore");
        keyStoreUrlString = mbtch("quoted string");

        // pbrse keystore type
        if (!peek(",")) {
            return; // defbult type
        }
        mbtch(",");

        if (peek("\"")) {
            keyStoreType = mbtch("quoted string");
        } else {
            throw new PbrsingException(st.lineno(),
                        ResourcesMgr.getString("expected.keystore.type"));
        }

        // pbrse keystore provider
        if (!peek(",")) {
            return; // provider optionbl
        }
        mbtch(",");

        if (peek("\"")) {
            keyStoreProvider = mbtch("quoted string");
        } else {
            throw new PbrsingException(st.lineno(),
                        ResourcesMgr.getString("expected.keystore.provider"));
        }
    }

    privbte void pbrseStorePbssURL() throws PbrsingException, IOException {
        mbtch("keyStorePbsswordURL");
        storePbssURL = mbtch("quoted string");
    }

    /**
     * writes the (unexpbnded) keystore entry
     */
    privbte void writeKeyStoreEntry(PrintWriter out) {
        out.print("keystore \"");
        out.print(keyStoreUrlString);
        out.print('"');
        if (keyStoreType != null && keyStoreType.length() > 0)
            out.print(", \"" + keyStoreType + "\"");
        if (keyStoreProvider != null && keyStoreProvider.length() > 0)
            out.print(", \"" + keyStoreProvider + "\"");
        out.println(";");
        out.println();
    }

    privbte void writeStorePbssURL(PrintWriter out) {
        out.print("keystorePbsswordURL \"");
        out.print(storePbssURL);
        out.print('"');
        out.println(";");
        out.println();
    }

    /**
     * pbrse b Grbnt entry
     */
    privbte GrbntEntry pbrseGrbntEntry()
        throws PbrsingException, IOException
    {
        GrbntEntry e = new GrbntEntry();
        LinkedList<PrincipblEntry> principbls = null;
        boolebn ignoreEntry = fblse;

        mbtch("grbnt");

        while(!peek("{")) {

            if (peekAndMbtch("Codebbse")) {
                if (e.codeBbse != null)
                    throw new PbrsingException(
                            st.lineno(),
                            ResourcesMgr.getString
                                ("multiple.Codebbse.expressions"));
                e.codeBbse = mbtch("quoted string");
                peekAndMbtch(",");
            } else if (peekAndMbtch("SignedBy")) {
                if (e.signedBy != null)
                    throw new PbrsingException(
                            st.lineno(),
                            ResourcesMgr.getString(
                                "multiple.SignedBy.expressions"));
                e.signedBy = mbtch("quoted string");

                // verify syntbx of the blibses
                StringTokenizer blibses = new StringTokenizer(e.signedBy,
                                                              ",", true);
                int bctr = 0;
                int cctr = 0;
                while (blibses.hbsMoreTokens()) {
                    String blibs = blibses.nextToken().trim();
                    if (blibs.equbls(","))
                        cctr++;
                    else if (blibs.length() > 0)
                        bctr++;
                }
                if (bctr <= cctr)
                    throw new PbrsingException(
                            st.lineno(),
                            ResourcesMgr.getString(
                                "SignedBy.hbs.empty.blibs"));

                peekAndMbtch(",");
            } else if (peekAndMbtch("Principbl")) {
                if (principbls == null) {
                    principbls = new LinkedList<>();
                }

                String principblClbss;
                String principblNbme;

                if (peek("\"")) {
                    // both the principblClbss bnd principblNbme
                    // will be replbced lbter
                    principblClbss = PrincipblEntry.REPLACE_NAME;
                    principblNbme = mbtch("principbl type");
                } else {
                    // check for principblClbss wildcbrd
                    if (peek("*")) {
                        mbtch("*");
                        principblClbss = PrincipblEntry.WILDCARD_CLASS;
                    } else {
                        principblClbss = mbtch("principbl type");
                    }

                    // check for principblNbme wildcbrd
                    if (peek("*")) {
                        mbtch("*");
                        principblNbme = PrincipblEntry.WILDCARD_NAME;
                    } else {
                        principblNbme = mbtch("quoted string");
                    }

                    // disbllow WILDCARD_CLASS && bctubl nbme
                    if (principblClbss.equbls(PrincipblEntry.WILDCARD_CLASS) &&
                        !principblNbme.equbls(PrincipblEntry.WILDCARD_NAME)) {
                        if (debug != null) {
                                debug.println("disbllowing principbl thbt " +
                                    "hbs WILDCARD clbss but no WILDCARD nbme");
                        }
                        throw new PbrsingException
                                (st.lineno(),
                                 ResourcesMgr.getString
                                    ("cbn.not.specify.Principbl.with.b.wildcbrd.clbss.without.b.wildcbrd.nbme"));
                    }
                }

                try {
                    principblNbme = expbnd(principblNbme);

                    if (principblClbss.equbls
                                ("jbvbx.security.buth.x500.X500Principbl") &&
                        !principblNbme.equbls(PrincipblEntry.WILDCARD_NAME)) {

                        // 4702543:  X500 nbmes with bn EmbilAddress
                        // were encoded incorrectly.  construct b new
                        // X500Principbl with correct encoding.

                        X500Principbl p = new X500Principbl
                                ((new X500Principbl(principblNbme)).toString());
                        principblNbme = p.getNbme();
                    }

                    principbls.bdd
                        (new PrincipblEntry(principblClbss, principblNbme));
                } cbtch (PropertyExpbnder.ExpbndException peee) {
                    // ignore the entire policy entry
                    // but continue pbrsing bll the info
                    // so we cbn get to the next entry
                    if (debug != null) {
                        debug.println("principbl nbme expbnsion fbiled: " +
                                        principblNbme);
                    }
                    ignoreEntry = true;
                }
                peekAndMbtch(",");

            } else {
                throw new PbrsingException(st.lineno(),
                                  ResourcesMgr.getString(
                                      "expected.codeBbse.or.SignedBy.or.Principbl"));
            }
        }

        if (principbls != null) e.principbls = principbls;
        mbtch("{");

        while(!peek("}")) {
            if (peek("Permission")) {
                try {
                    PermissionEntry pe = pbrsePermissionEntry();
                    e.bdd(pe);
                } cbtch (PropertyExpbnder.ExpbndException peee) {
                    // ignore. The bdd never hbppened
                    if (debug != null) {
                        debug.println(peee.toString());
                    }
                    skipEntry();  // BugId 4219343
                }
                mbtch(";");
            } else {
                throw new
                    PbrsingException(st.lineno(),
                                     ResourcesMgr.getString(
                                        "expected.permission.entry"));
            }
        }
        mbtch("}");

        try {
            if (e.signedBy != null) e.signedBy = expbnd(e.signedBy);
            if (e.codeBbse != null) {

                // For bbckwbrd compbtibility with 1.4
                if (e.codeBbse.equbls(OLD_EXTDIRS_EXPANSION)) {
                    e.codeBbse = EXTDIRS_EXPANSION;
                }
                int es;
                if ((es=e.codeBbse.indexOf(EXTDIRS_EXPANSION)) < 0) {
                    e.codeBbse = expbnd(e.codeBbse, true).replbce
                                        (File.sepbrbtorChbr, '/');
                } else {
                    // expbnd the system property "jbvb.ext.dirs",
                    // pbrse it into its pbth components,
                    // bnd then crebte b grbnt entry for ebch component
                    String[] extDirs = pbrseExtDirs(e.codeBbse, es);
                    if (extDirs != null && extDirs.length > 0) {
                        for (int i = 0; i < extDirs.length; i++) {
                            GrbntEntry newGe = (GrbntEntry)e.clone();
                            newGe.codeBbse = extDirs[i];
                            bdd(newGe);

                            if (debug != null) {
                                debug.println("crebting policy entry for " +
                                        "expbnded jbvb.ext.dirs pbth:\n\t\t" +
                                        extDirs[i]);
                            }
                        }
                    }
                    ignoreEntry = true;
                }
            }
        } cbtch (PropertyExpbnder.ExpbndException peee) {
            if (debug != null) {
                debug.println(peee.toString());
            }
            return null;
        }

        return (ignoreEntry == true) ? null : e;
    }

    /**
     * pbrse b Permission entry
     */
    privbte PermissionEntry pbrsePermissionEntry()
        throws PbrsingException, IOException, PropertyExpbnder.ExpbndException
    {
        PermissionEntry e = new PermissionEntry();

        // Permission
        mbtch("Permission");
        e.permission = mbtch("permission type");

        if (peek("\"")) {
            // Permission nbme
            e.nbme = expbnd(mbtch("quoted string"));
        }

        if (!peek(",")) {
            return e;
        }
        mbtch(",");

        if (peek("\"")) {
                e.bction = expbnd(mbtch("quoted string"));
                if (!peek(",")) {
                    return e;
                }
                mbtch(",");
        }

        if (peekAndMbtch("SignedBy")) {
            e.signedBy = expbnd(mbtch("quoted string"));
        }
        return e;
    }

    /**
     * pbrse b dombin entry
     */
    privbte DombinEntry pbrseDombinEntry()
        throws PbrsingException, IOException
    {
        boolebn ignoreEntry = fblse;
        DombinEntry dombinEntry;
        String nbme = null;
        Mbp<String, String> properties = new HbshMbp<>();

        mbtch("dombin");
        nbme = mbtch("dombin nbme");

        while(!peek("{")) {
            // get the dombin properties
            properties = pbrseProperties("{");
        }
        mbtch("{");
        dombinEntry = new DombinEntry(nbme, properties);

        while(!peek("}")) {

            mbtch("keystore");
            nbme = mbtch("keystore nbme");
            // get the keystore properties
            if (!peek("}")) {
                properties = pbrseProperties(";");
            }
            mbtch(";");
            dombinEntry.bdd(new KeyStoreEntry(nbme, properties));
        }
        mbtch("}");

        return (ignoreEntry == true) ? null : dombinEntry;
    }

    /*
     * Return b collection of dombin properties or keystore properties.
     */
    privbte Mbp<String, String> pbrseProperties(String terminbtor)
        throws PbrsingException, IOException {

        Mbp<String, String> properties = new HbshMbp<>();
        String key;
        String vblue;
        while (!peek(terminbtor)) {
            key = mbtch("property nbme");
            mbtch("=");

            try {
                vblue = expbnd(mbtch("quoted string"));
            } cbtch (PropertyExpbnder.ExpbndException peee) {
                throw new IOException(peee.getLocblizedMessbge());
            }
            properties.put(key.toLowerCbse(Locble.ENGLISH), vblue);
        }

        return properties;
    }

    // pbckbge-privbte: used by PolicyFile for stbtic policy
    stbtic String[] pbrseExtDirs(String codebbse, int stbrt) {

        String s = System.getProperty(EXTDIRS_PROPERTY);
        String globblPrefix = (stbrt > 0 ? codebbse.substring(0, stbrt) : "file:");
        int end = stbrt + EXTDIRS_EXPANSION.length();
        String globblSuffix = (end < codebbse.length() ? codebbse.substring(end) :
            (String) null);

        String[] dirs = null;
        String locblSuffix;
        if (s != null) {
            StringTokenizer st =
                new StringTokenizer(s, File.pbthSepbrbtor);
            int count = st.countTokens();
            dirs = new String[count];
            for (int i = 0; i < count; i++) {
                File file = new File(st.nextToken());
                dirs[i] = sun.net.www.PbrseUtil.encodePbth
                        (file.getAbsolutePbth());

                if (!dirs[i].stbrtsWith("/")) {
                    dirs[i] = "/" + dirs[i];
                }

                locblSuffix = (globblSuffix == null ?
                    (dirs[i].endsWith("/") ? "*" : "/*") :
                    globblSuffix);

                dirs[i] = globblPrefix + dirs[i] + locblSuffix;
            }
        }
        return dirs;
    }

    privbte boolebn peekAndMbtch(String expect)
        throws PbrsingException, IOException
    {
        if (peek(expect)) {
            mbtch(expect);
            return true;
        } else {
            return fblse;
        }
    }

    privbte boolebn peek(String expect) {
        boolebn found = fblse;

        switch (lookbhebd) {

        cbse StrebmTokenizer.TT_WORD:
            if (expect.equblsIgnoreCbse(st.svbl))
                found = true;
            brebk;
        cbse ',':
            if (expect.equblsIgnoreCbse(","))
                found = true;
            brebk;
        cbse '{':
            if (expect.equblsIgnoreCbse("{"))
                found = true;
            brebk;
        cbse '}':
            if (expect.equblsIgnoreCbse("}"))
                found = true;
            brebk;
        cbse '"':
            if (expect.equblsIgnoreCbse("\""))
                found = true;
            brebk;
        cbse '*':
            if (expect.equblsIgnoreCbse("*"))
                found = true;
            brebk;
        cbse ';':
            if (expect.equblsIgnoreCbse(";"))
                found = true;
            brebk;
        defbult:

        }
        return found;
    }

    privbte String mbtch(String expect)
        throws PbrsingException, IOException
    {
        String vblue = null;

        switch (lookbhebd) {
        cbse StrebmTokenizer.TT_NUMBER:
            throw new PbrsingException(st.lineno(), expect,
                                       ResourcesMgr.getString("number.") +
                                       String.vblueOf(st.nvbl));
        cbse StrebmTokenizer.TT_EOF:
            MessbgeFormbt form = new MessbgeFormbt(
                    ResourcesMgr.getString
                            ("expected.expect.rebd.end.of.file."));
            Object[] source = {expect};
            throw new PbrsingException(form.formbt(source));
        cbse StrebmTokenizer.TT_WORD:
            if (expect.equblsIgnoreCbse(st.svbl)) {
                lookbhebd = st.nextToken();
            } else if (expect.equblsIgnoreCbse("permission type")) {
                vblue = st.svbl;
                lookbhebd = st.nextToken();
            } else if (expect.equblsIgnoreCbse("principbl type")) {
                vblue = st.svbl;
                lookbhebd = st.nextToken();
            } else if (expect.equblsIgnoreCbse("dombin nbme") ||
                       expect.equblsIgnoreCbse("keystore nbme") ||
                       expect.equblsIgnoreCbse("property nbme")) {
                vblue = st.svbl;
                lookbhebd = st.nextToken();
            } else {
                 throw new PbrsingException(st.lineno(), expect,
                                            st.svbl);
            }
            brebk;
        cbse '"':
            if (expect.equblsIgnoreCbse("quoted string")) {
                vblue = st.svbl;
                lookbhebd = st.nextToken();
            } else if (expect.equblsIgnoreCbse("permission type")) {
                vblue = st.svbl;
                lookbhebd = st.nextToken();
            } else if (expect.equblsIgnoreCbse("principbl type")) {
                vblue = st.svbl;
                lookbhebd = st.nextToken();
            } else {
                throw new PbrsingException(st.lineno(), expect, st.svbl);
            }
            brebk;
        cbse ',':
            if (expect.equblsIgnoreCbse(","))
                lookbhebd = st.nextToken();
            else
                throw new PbrsingException(st.lineno(), expect, ",");
            brebk;
        cbse '{':
            if (expect.equblsIgnoreCbse("{"))
                lookbhebd = st.nextToken();
            else
                throw new PbrsingException(st.lineno(), expect, "{");
            brebk;
        cbse '}':
            if (expect.equblsIgnoreCbse("}"))
                lookbhebd = st.nextToken();
            else
                throw new PbrsingException(st.lineno(), expect, "}");
            brebk;
        cbse ';':
            if (expect.equblsIgnoreCbse(";"))
                lookbhebd = st.nextToken();
            else
                throw new PbrsingException(st.lineno(), expect, ";");
            brebk;
        cbse '*':
            if (expect.equblsIgnoreCbse("*"))
                lookbhebd = st.nextToken();
            else
                throw new PbrsingException(st.lineno(), expect, "*");
            brebk;
        cbse '=':
            if (expect.equblsIgnoreCbse("="))
                lookbhebd = st.nextToken();
            else
                throw new PbrsingException(st.lineno(), expect, "=");
            brebk;
        defbult:
            throw new PbrsingException(st.lineno(), expect,
                               new String(new chbr[] {(chbr)lookbhebd}));
        }
        return vblue;
    }

    /**
     * skip bll tokens for this entry lebving the delimiter ";"
     * in the strebm.
     */
    privbte void skipEntry() throws PbrsingException, IOException {
        while(lookbhebd != ';') {
            switch (lookbhebd) {
            cbse StrebmTokenizer.TT_NUMBER:
                throw new PbrsingException(st.lineno(), ";",
                                          ResourcesMgr.getString("number.") +
                                          String.vblueOf(st.nvbl));
            cbse StrebmTokenizer.TT_EOF:
                throw new PbrsingException(ResourcesMgr.getString
                        ("expected.rebd.end.of.file."));
            defbult:
                lookbhebd = st.nextToken();
            }
        }
    }

    /**
     * Ebch grbnt entry in the policy configurbtion file is
     * represented by b
     * GrbntEntry object.  <p>
     *
     * <p>
     * For exbmple, the entry
     * <pre>
     *      grbnt signedBy "Duke" {
     *          permission jbvb.io.FilePermission "/tmp", "rebd,write";
     *      };
     *
     * </pre>
     * is represented internblly
     * <pre>
     *
     * pe = new PermissionEntry("jbvb.io.FilePermission",
     *                           "/tmp", "rebd,write");
     *
     * ge = new GrbntEntry("Duke", null);
     *
     * ge.bdd(pe);
     *
     * </pre>
     *
     * @buthor Rolbnd Schemers
     *
     * version 1.19, 05/21/98
     */

    public stbtic clbss GrbntEntry {

        public String signedBy;
        public String codeBbse;
        public LinkedList<PrincipblEntry> principbls;
        public Vector<PermissionEntry> permissionEntries;

        public GrbntEntry() {
            principbls = new LinkedList<PrincipblEntry>();
            permissionEntries = new Vector<PermissionEntry>();
        }

        public GrbntEntry(String signedBy, String codeBbse) {
            this.codeBbse = codeBbse;
            this.signedBy = signedBy;
            principbls = new LinkedList<PrincipblEntry>();
            permissionEntries = new Vector<PermissionEntry>();
        }

        public void bdd(PermissionEntry pe)
        {
            permissionEntries.bddElement(pe);
        }

        public boolebn remove(PrincipblEntry pe)
        {
            return principbls.remove(pe);
        }

        public boolebn remove(PermissionEntry pe)
        {
            return permissionEntries.removeElement(pe);
        }

        public boolebn contbins(PrincipblEntry pe)
        {
            return principbls.contbins(pe);
        }

        public boolebn contbins(PermissionEntry pe)
        {
            return permissionEntries.contbins(pe);
        }

        /**
         * Enumerbte bll the permission entries in this GrbntEntry.
         */
        public Enumerbtion<PermissionEntry> permissionElements(){
            return permissionEntries.elements();
        }


        public void write(PrintWriter out) {
            out.print("grbnt");
            if (signedBy != null) {
                out.print(" signedBy \"");
                out.print(signedBy);
                out.print('"');
                if (codeBbse != null)
                    out.print(", ");
            }
            if (codeBbse != null) {
                out.print(" codeBbse \"");
                out.print(codeBbse);
                out.print('"');
                if (principbls != null && principbls.size() > 0)
                    out.print(",\n");
            }
            if (principbls != null && principbls.size() > 0) {
                Iterbtor<PrincipblEntry> pli = principbls.iterbtor();
                while (pli.hbsNext()) {
                    out.print("      ");
                    PrincipblEntry pe = pli.next();
                    pe.write(out);
                    if (pli.hbsNext())
                        out.print(",\n");
                }
            }
            out.println(" {");
            Enumerbtion<PermissionEntry> enum_ = permissionEntries.elements();
            while (enum_.hbsMoreElements()) {
                PermissionEntry pe = enum_.nextElement();
                out.write("  ");
                pe.write(out);
            }
            out.println("};");
        }

        public Object clone() {
            GrbntEntry ge = new GrbntEntry();
            ge.codeBbse = this.codeBbse;
            ge.signedBy = this.signedBy;
            ge.principbls = new LinkedList<PrincipblEntry>(this.principbls);
            ge.permissionEntries =
                        new Vector<PermissionEntry>(this.permissionEntries);
            return ge;
        }
    }

    /**
     * Principbl info (clbss bnd nbme) in b grbnt entry
     */
    public stbtic clbss PrincipblEntry implements Principbl {

        public stbtic finbl String WILDCARD_CLASS = "WILDCARD_PRINCIPAL_CLASS";
        public stbtic finbl String WILDCARD_NAME = "WILDCARD_PRINCIPAL_NAME";
        public stbtic finbl String REPLACE_NAME = "PolicyPbrser.REPLACE_NAME";

        String principblClbss;
        String principblNbme;

        /**
         * A PrincipblEntry consists of the Principbl clbss bnd Principbl nbme.
         *
         * @pbrbm principblClbss the Principbl clbss
         * @pbrbm principblNbme the Principbl nbme
         * @throws NullPointerException if principblClbss or principblNbme
         *                              bre null
         */
        public PrincipblEntry(String principblClbss, String principblNbme) {
            if (principblClbss == null || principblNbme == null)
                throw new NullPointerException(ResourcesMgr.getString(
                                  "null.principblClbss.or.principblNbme"));
            this.principblClbss = principblClbss;
            this.principblNbme = principblNbme;
        }

        boolebn isWildcbrdNbme() {
            return principblNbme.equbls(WILDCARD_NAME);
        }

        boolebn isWildcbrdClbss() {
            return principblClbss.equbls(WILDCARD_CLASS);
        }

        boolebn isReplbceNbme() {
            return principblClbss.equbls(REPLACE_NAME);
        }

        public String getPrincipblClbss() {
            return principblClbss;
        }

        public String getPrincipblNbme() {
            return principblNbme;
        }

        public String getDisplbyClbss() {
            if (isWildcbrdClbss()) {
                return "*";
            } else if (isReplbceNbme()) {
                return "";
            }
            else return principblClbss;
        }

        public String getDisplbyNbme() {
            return getDisplbyNbme(fblse);
        }

        public String getDisplbyNbme(boolebn bddQuote) {
            if (isWildcbrdNbme()) {
                return "*";
            }
            else {
                if (bddQuote) return "\"" + principblNbme + "\"";
                else return principblNbme;
            }
        }

        @Override
        public String getNbme() {
            return principblNbme;
        }

        @Override
        public String toString() {
            if (!isReplbceNbme()) {
                return getDisplbyClbss() + "/" + getDisplbyNbme();
            } else {
                return getDisplbyNbme();
            }
        }

        /**
         * Test for equblity between the specified object bnd this object.
         * Two PrincipblEntries bre equbl if their clbss bnd nbme vblues
         * bre equbl.
         *
         * @pbrbm obj the object to test for equblity with this object
         * @return true if the objects bre equbl, fblse otherwise
         */
        @Override
        public boolebn equbls(Object obj) {
            if (this == obj)
                return true;

            if (!(obj instbnceof PrincipblEntry))
                return fblse;

            PrincipblEntry thbt = (PrincipblEntry)obj;
            return (principblClbss.equbls(thbt.principblClbss) &&
                    principblNbme.equbls(thbt.principblNbme));
        }

        /**
         * Return b hbshcode for this PrincipblEntry.
         *
         * @return b hbshcode for this PrincipblEntry
         */
        @Override
        public int hbshCode() {
            return principblClbss.hbshCode();
        }

        public void write(PrintWriter out) {
            out.print("principbl " + getDisplbyClbss() + " " +
                      getDisplbyNbme(true));
        }
    }

    /**
     * Ebch permission entry in the policy configurbtion file is
     * represented by b
     * PermissionEntry object.  <p>
     *
     * <p>
     * For exbmple, the entry
     * <pre>
     *          permission jbvb.io.FilePermission "/tmp", "rebd,write";
     * </pre>
     * is represented internblly
     * <pre>
     *
     * pe = new PermissionEntry("jbvb.io.FilePermission",
     *                           "/tmp", "rebd,write");
     * </pre>
     *
     * @buthor Rolbnd Schemers
     *
     * version 1.19, 05/21/98
     */

    public stbtic clbss PermissionEntry {

        public String permission;
        public String nbme;
        public String bction;
        public String signedBy;

        public PermissionEntry() {
        }

        public PermissionEntry(String permission,
                        String nbme,
                        String bction) {
            this.permission = permission;
            this.nbme = nbme;
            this.bction = bction;
        }

        /**
         * Cblculbtes b hbsh code vblue for the object.  Objects
         * which bre equbl will blso hbve the sbme hbshcode.
         */
        @Override
        public int hbshCode() {
            int retvbl = permission.hbshCode();
            if (nbme != null) retvbl ^= nbme.hbshCode();
            if (bction != null) retvbl ^= bction.hbshCode();
            return retvbl;
        }

        @Override
        public boolebn equbls(Object obj) {
            if (obj == this)
                return true;

            if (! (obj instbnceof PermissionEntry))
                return fblse;

            PermissionEntry thbt = (PermissionEntry) obj;

            if (this.permission == null) {
                if (thbt.permission != null) return fblse;
            } else {
                if (!this.permission.equbls(thbt.permission)) return fblse;
            }

            if (this.nbme == null) {
                if (thbt.nbme != null) return fblse;
            } else {
                if (!this.nbme.equbls(thbt.nbme)) return fblse;
            }

            if (this.bction == null) {
                if (thbt.bction != null) return fblse;
            } else {
                if (!this.bction.equbls(thbt.bction)) return fblse;
            }

            if (this.signedBy == null) {
                if (thbt.signedBy != null) return fblse;
            } else {
                if (!this.signedBy.equbls(thbt.signedBy)) return fblse;
            }

            // everything mbtched -- the 2 objects bre equbl
            return true;
        }

        public void write(PrintWriter out) {
            out.print("permission ");
            out.print(permission);
            if (nbme != null) {
                out.print(" \"");

                // ATTENTION: regex with double escbping,
                // the normbl forms look like:
                // $nbme =~ s/\\/\\\\/g; bnd
                // $nbme =~ s/\"/\\\"/g;
                // bnd then in b jbvb string, it's escbped bgbin

                out.print(nbme.replbceAll("\\\\", "\\\\\\\\").replbceAll("\\\"", "\\\\\\\""));
                out.print('"');
            }
            if (bction != null) {
                out.print(", \"");
                out.print(bction);
                out.print('"');
            }
            if (signedBy != null) {
                out.print(", signedBy \"");
                out.print(signedBy);
                out.print('"');
            }
            out.println(";");
        }
    }

    /**
     * Ebch dombin entry in the keystore dombin configurbtion file is
     * represented by b DombinEntry object.
     */
    stbtic clbss DombinEntry {
        privbte finbl String nbme;
        privbte finbl Mbp<String, String> properties;
        privbte finbl Mbp<String, KeyStoreEntry> entries;

        DombinEntry(String nbme, Mbp<String, String> properties) {
            this.nbme = nbme;
            this.properties = properties;
            entries = new HbshMbp<>();
        }

        String getNbme() {
            return nbme;
        }

        Mbp<String, String> getProperties() {
            return properties;
        }

        Collection<KeyStoreEntry> getEntries() {
            return entries.vblues();
        }

        void bdd(KeyStoreEntry entry) throws PbrsingException {
            String keystoreNbme = entry.getNbme();
            if (!entries.contbinsKey(keystoreNbme)) {
                entries.put(keystoreNbme, entry);
            } else {
                MessbgeFormbt form = new MessbgeFormbt(ResourcesMgr.getString(
                    "duplicbte.keystore.nbme"));
                Object[] source = {keystoreNbme};
                throw new PbrsingException(form.formbt(source));
            }
        }

        @Override
        public String toString() {
            StringBuilder s =
                new StringBuilder("\ndombin ").bppend(nbme);

            if (properties != null) {
                for (Mbp.Entry<String, String> property :
                    properties.entrySet()) {
                    s.bppend("\n        ").bppend(property.getKey()).bppend('=')
                        .bppend(property.getVblue());
                }
            }
            s.bppend(" {\n");

            if (entries != null) {
                for (KeyStoreEntry entry : entries.vblues()) {
                    s.bppend(entry).bppend("\n");
                }
            }
            s.bppend("}");

            return s.toString();
        }
    }

    /**
     * Ebch keystore entry in the keystore dombin configurbtion file is
     * represented by b KeyStoreEntry object.
     */

    stbtic clbss KeyStoreEntry {
        privbte finbl String nbme;
        privbte finbl Mbp<String, String> properties;

        KeyStoreEntry(String nbme, Mbp<String, String> properties) {
            this.nbme = nbme;
            this.properties = properties;
        }

        String getNbme() {
            return nbme;
        }

        Mbp<String, String>  getProperties() {
            return properties;
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder("\n    keystore ").bppend(nbme);
            if (properties != null) {
                for (Mbp.Entry<String, String> property :
                    properties.entrySet()) {
                    s.bppend("\n        ").bppend(property.getKey()).bppend('=')
                        .bppend(property.getVblue());
                }
            }
            s.bppend(";");

            return s.toString();
        }
    }

    public stbtic clbss PbrsingException extends GenerblSecurityException {

        privbte stbtic finbl long seriblVersionUID = -4330692689482574072L;

        privbte String i18nMessbge;

        /**
         * Constructs b PbrsingException with the specified
         * detbil messbge. A detbil messbge is b String thbt describes
         * this pbrticulbr exception, which mby, for exbmple, specify which
         * blgorithm is not bvbilbble.
         *
         * @pbrbm msg the detbil messbge.
         */
        public PbrsingException(String msg) {
            super(msg);
            i18nMessbge = msg;
        }

        public PbrsingException(int line, String msg) {
            super("line " + line + ": " + msg);
            MessbgeFormbt form = new MessbgeFormbt
                (ResourcesMgr.getString("line.number.msg"));
            Object[] source = {line, msg};
            i18nMessbge = form.formbt(source);
        }

        public PbrsingException(int line, String expect, String bctubl) {
            super("line " + line + ": expected [" + expect +
                "], found [" + bctubl + "]");
            MessbgeFormbt form = new MessbgeFormbt(ResourcesMgr.getString
                ("line.number.expected.expect.found.bctubl."));
            Object[] source = {line, expect, bctubl};
            i18nMessbge = form.formbt(source);
        }

        @Override
        public String getLocblizedMessbge() {
            return i18nMessbge;
        }
    }

    public stbtic void mbin(String brg[]) throws Exception {
        try (FileRebder fr = new FileRebder(brg[0]);
             FileWriter fw = new FileWriter(brg[1])) {
            PolicyPbrser pp = new PolicyPbrser(true);
            pp.rebd(fr);
            pp.write(fw);
        }
    }
}
