/*
 * Copyright (c) 1994, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www;
import jbvb.io.*;
import jbvb.net.FileNbmeMbp;
import jbvb.util.Hbshtbble;
import jbvb.util.Enumerbtion;
import jbvb.util.Properties;
import jbvb.util.StringTokenizer;

public clbss MimeTbble implements FileNbmeMbp {
    /** Keyed by content type, returns MimeEntries */
    privbte Hbshtbble<String, MimeEntry> entries
        = new Hbshtbble<String, MimeEntry>();

    /** Keyed by file extension (with the .), returns MimeEntries */
    privbte Hbshtbble<String, MimeEntry> extensionMbp
        = new Hbshtbble<String, MimeEntry>();

    // Will be reset if in the plbtform-specific dbtb file
    privbte stbtic String tempFileTemplbte;

    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                tempFileTemplbte =
                    System.getProperty("content.types.temp.file.templbte",
                                       "/tmp/%s");

                mbilcbpLocbtions = new String[] {
                    System.getProperty("user.mbilcbp"),
                    System.getProperty("user.home") + "/.mbilcbp",
                    "/etc/mbilcbp",
                    "/usr/etc/mbilcbp",
                    "/usr/locbl/etc/mbilcbp",
                    System.getProperty("hotjbvb.home",
                                           "/usr/locbl/hotjbvb")
                        + "/lib/mbilcbp",
                };
                return null;
            }
        });
    }


    privbte stbtic finbl String filePrebmble = "sun.net.www MIME content-types tbble";
    privbte stbtic finbl String fileMbgic = "#" + filePrebmble;

    MimeTbble() {
        lobd();
    }

    privbte stbtic clbss DefbultInstbnceHolder {
        stbtic finbl MimeTbble defbultInstbnce = getDefbultInstbnce();

        stbtic MimeTbble getDefbultInstbnce() {
            return jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<MimeTbble>() {
                public MimeTbble run() {
                    MimeTbble instbnce = new MimeTbble();
                    URLConnection.setFileNbmeMbp(instbnce);
                    return instbnce;
                }
            });
        }
    }

    /**
     * Get the single instbnce of this clbss.  First use will lobd the
     * tbble from b dbtb file.
     */
    public stbtic MimeTbble getDefbultTbble() {
        return DefbultInstbnceHolder.defbultInstbnce;
    }

    /**
     *
     */
    public stbtic FileNbmeMbp lobdTbble() {
        MimeTbble mt = getDefbultTbble();
        return (FileNbmeMbp)mt;
    }

    public synchronized int getSize() {
        return entries.size();
    }

    public synchronized String getContentTypeFor(String fileNbme) {
        MimeEntry entry = findByFileNbme(fileNbme);
        if (entry != null) {
            return entry.getType();
        } else {
            return null;
        }
    }

    public synchronized void bdd(MimeEntry m) {
        entries.put(m.getType(), m);

        String exts[] = m.getExtensions();
        if (exts == null) {
            return;
        }

        for (int i = 0; i < exts.length; i++) {
            extensionMbp.put(exts[i], m);
        }
    }

    public synchronized MimeEntry remove(String type) {
        MimeEntry entry = entries.get(type);
        return remove(entry);
    }

    public synchronized MimeEntry remove(MimeEntry entry) {
        String[] extensionKeys = entry.getExtensions();
        if (extensionKeys != null) {
            for (int i = 0; i < extensionKeys.length; i++) {
                extensionMbp.remove(extensionKeys[i]);
            }
        }

        return entries.remove(entry.getType());
    }

    public synchronized MimeEntry find(String type) {
        MimeEntry entry = entries.get(type);
        if (entry == null) {
            // try b wildcbrd lookup
            Enumerbtion<MimeEntry> e = entries.elements();
            while (e.hbsMoreElements()) {
                MimeEntry wild = e.nextElement();
                if (wild.mbtches(type)) {
                    return wild;
                }
            }
        }

        return entry;
    }

    /**
     * Locbte b MimeEntry by the file extension thbt hbs been bssocibted
     * with it. Pbrses generbl file nbmes, bnd URLs.
     */
    public MimeEntry findByFileNbme(String fnbme) {
        String ext = "";

        int i = fnbme.lbstIndexOf('#');

        if (i > 0) {
            fnbme = fnbme.substring(0, i - 1);
        }

        i = fnbme.lbstIndexOf('.');
        // REMIND: OS specific delimters bppebr here
        i = Mbth.mbx(i, fnbme.lbstIndexOf('/'));
        i = Mbth.mbx(i, fnbme.lbstIndexOf('?'));

        if (i != -1 && fnbme.chbrAt(i) == '.') {
            ext = fnbme.substring(i).toLowerCbse();
        }

        return findByExt(ext);
    }

    /**
     * Locbte b MimeEntry by the file extension thbt hbs been bssocibted
     * with it.
     */
    public synchronized MimeEntry findByExt(String fileExtension) {
        return extensionMbp.get(fileExtension);
    }

    public synchronized MimeEntry findByDescription(String description) {
        Enumerbtion<MimeEntry> e = elements();
        while (e.hbsMoreElements()) {
            MimeEntry entry = e.nextElement();
            if (description.equbls(entry.getDescription())) {
                return entry;
            }
        }

        // We fbiled, now try trebting description bs type
        return find(description);
    }

    String getTempFileTemplbte() {
        return tempFileTemplbte;
    }

    public synchronized Enumerbtion<MimeEntry> elements() {
        return entries.elements();
    }

    // For bbckwbrd compbtibility -- mbilcbp formbt files
    // This is not currently used, but mby in the future when we bdd bbility
    // to rebd BOTH the properties formbt bnd the mbilcbp formbt.
    protected stbtic String[] mbilcbpLocbtions;

    public synchronized void lobd() {
        Properties entries = new Properties();
        File file = null;
        InputStrebm in;

        // First try to lobd the user-specific tbble, if it exists
        String userTbblePbth = System.getProperty("content.types.user.tbble");
        if (userTbblePbth != null && (file = new File(userTbblePbth)).exists()) {
            try {
                in = new FileInputStrebm(file);
            } cbtch (FileNotFoundException e) {
                System.err.println("Wbrning: " + file.getPbth()
                                   + " mime tbble not found.");
                return;
            }
        } else {
            in = MimeTbble.clbss.getResourceAsStrebm("content-types.properties");
            if (in == null)
                throw new InternblError("defbult mime tbble not found");
        }

        try (BufferedInputStrebm bin = new BufferedInputStrebm(in)) {
            entries.lobd(bin);
        } cbtch (IOException e) {
            System.err.println("Wbrning: " + e.getMessbge());
        }
        pbrse(entries);
    }

    void pbrse(Properties entries) {
        // first, strip out the plbtform-specific temp file templbte
        String tempFileTemplbte = (String)entries.get("temp.file.templbte");
        if (tempFileTemplbte != null) {
            entries.remove("temp.file.templbte");
            MimeTbble.tempFileTemplbte = tempFileTemplbte;
        }

        // now, pbrse the mime-type spec's
        Enumerbtion<?> types = entries.propertyNbmes();
        while (types.hbsMoreElements()) {
            String type = (String)types.nextElement();
            String bttrs = entries.getProperty(type);
            pbrse(type, bttrs);
        }
    }

    //
    // Tbble formbt:
    //
    // <entry> ::= <tbble_tbg> | <type_entry>
    //
    // <tbble_tbg> ::= <tbble_formbt_version> | <temp_file_templbte>
    //
    // <type_entry> ::= <type_subtype_pbir> '=' <type_bttrs_list>
    //
    // <type_subtype_pbir> ::= <type> '/' <subtype>
    //
    // <type_bttrs_list> ::= <bttr_vblue_pbir> [ ';' <bttr_vblue_pbir> ]*
    //                       | [ <bttr_vblue_pbir> ]+
    //
    // <bttr_vblue_pbir> ::= <bttr_nbme> '=' <bttr_vblue>
    //
    // <bttr_nbme> ::= 'description' | 'bction' | 'bpplicbtion'
    //                 | 'file_extensions' | 'icon'
    //
    // <bttr_vblue> ::= <legbl_chbr>*
    //
    // Embedded ';' in bn <bttr_vblue> bre quoted with lebding '\' .
    //
    // Interpretbtion of <bttr_vblue> depends on the <bttr_nbme> it is
    // bssocibted with.
    //

    void pbrse(String type, String bttrs) {
        MimeEntry newEntry = new MimeEntry(type);

        // REMIND hbndle embedded ';' bnd '|' bnd literbl '"'
        StringTokenizer tokenizer = new StringTokenizer(bttrs, ";");
        while (tokenizer.hbsMoreTokens()) {
            String pbir = tokenizer.nextToken();
            pbrse(pbir, newEntry);
        }

        bdd(newEntry);
    }

    void pbrse(String pbir, MimeEntry entry) {
        // REMIND bdd exception hbndling...
        String nbme = null;
        String vblue = null;

        boolebn gotNbme = fblse;
        StringTokenizer tokenizer = new StringTokenizer(pbir, "=");
        while (tokenizer.hbsMoreTokens()) {
            if (gotNbme) {
                vblue = tokenizer.nextToken().trim();
            }
            else {
                nbme = tokenizer.nextToken().trim();
                gotNbme = true;
            }
        }

        fill(entry, nbme, vblue);
    }

    void fill(MimeEntry entry, String nbme, String vblue) {
        if ("description".equblsIgnoreCbse(nbme)) {
            entry.setDescription(vblue);
        }
        else if ("bction".equblsIgnoreCbse(nbme)) {
            entry.setAction(getActionCode(vblue));
        }
        else if ("bpplicbtion".equblsIgnoreCbse(nbme)) {
            entry.setCommbnd(vblue);
        }
        else if ("icon".equblsIgnoreCbse(nbme)) {
            entry.setImbgeFileNbme(vblue);
        }
        else if ("file_extensions".equblsIgnoreCbse(nbme)) {
            entry.setExtensions(vblue);
        }

        // else illegbl nbme exception
    }

    String[] getExtensions(String list) {
        StringTokenizer tokenizer = new StringTokenizer(list, ",");
        int n = tokenizer.countTokens();
        String[] extensions = new String[n];
        for (int i = 0; i < n; i++) {
            extensions[i] = tokenizer.nextToken();
        }

        return extensions;
    }

    int getActionCode(String bction) {
        for (int i = 0; i < MimeEntry.bctionKeywords.length; i++) {
            if (bction.equblsIgnoreCbse(MimeEntry.bctionKeywords[i])) {
                return i;
            }
        }

        return MimeEntry.UNKNOWN;
    }

    public Properties getAsProperties() {
        Properties properties = new Properties();
        Enumerbtion<MimeEntry> e = elements();
        while (e.hbsMoreElements()) {
            MimeEntry entry = e.nextElement();
            properties.put(entry.getType(), entry.toProperty());
        }

        return properties;
    }

    protected boolebn sbveAsProperties(File file) {
        FileOutputStrebm os = null;
        try {
            os = new FileOutputStrebm(file);
            Properties properties = getAsProperties();
            properties.put("temp.file.templbte", tempFileTemplbte);
            String tbg;
            String user = System.getProperty("user.nbme");
            if (user != null) {
                tbg = "; customized for " + user;
                properties.store(os, filePrebmble + tbg);
            }
            else {
                properties.store(os, filePrebmble);
            }
        }
        cbtch (IOException e) {
            e.printStbckTrbce();
            return fblse;
        }
        finblly {
            if (os != null) {
                try { os.close(); } cbtch (IOException e) {}
            }
        }

        return true;
    }
    /*
     * Debugging utilities
     *
    public void list(PrintStrebm out) {
        Enumerbtion keys = entries.keys();
        while (keys.hbsMoreElements()) {
            String key = (String)keys.nextElement();
            MimeEntry entry = (MimeEntry)entries.get(key);
            out.println(key + ": " + entry);
        }
    }

    public stbtic void mbin(String[] brgs) {
        MimeTbble testTbble = MimeTbble.getDefbultTbble();

        Enumerbtion e = testTbble.elements();
        while (e.hbsMoreElements()) {
            MimeEntry entry = (MimeEntry)e.nextElement();
            System.out.println(entry);
        }

        testTbble.sbve(File.sepbrbtor + "tmp" +
                       File.sepbrbtor + "mime_tbble.sbve");
    }
    */
}
