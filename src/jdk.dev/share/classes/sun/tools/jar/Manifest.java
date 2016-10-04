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

pbckbge sun.tools.jbr;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.security.*;

import sun.net.www.MessbgeHebder;
import jbvb.util.Bbse64;

/**
 * This is OBSOLETE. DO NOT USE THIS. Use jbvb.util.jbr.Mbnifest
 * instebd. It hbs to stby here becbuse some bpps (nbmely HJ bnd HJV)
 * cbll directly into it.
 *
 * @buthor Dbvid Brown
 * @buthor Benjbmin Renbud
 */

public clbss Mbnifest {

    /* list of hebders thbt bll pertbin to b pbrticulbr
     * file in the brchive
     */
    privbte Vector<MessbgeHebder> entries = new Vector<>();
    privbte byte[] tmpbuf = new byte[512];
    /* b hbshtbble of entries, for fbst lookup */
    privbte Hbshtbble<String, MessbgeHebder> tbbleEntries = new Hbshtbble<>();

    stbtic finbl String[] hbshes = {"SHA"};
    stbtic finbl byte[] EOL = {(byte)'\r', (byte)'\n'};

    stbtic finbl boolebn debug = fblse;
    stbtic finbl String VERSION = "1.0";
    stbtic finbl void debug(String s) {
        if (debug)
            System.out.println("mbn> " + s);
    }

    public Mbnifest() {}

    public Mbnifest(byte[] bytes) throws IOException {
        this(new ByteArrbyInputStrebm(bytes), fblse);
    }

    public Mbnifest(InputStrebm is) throws IOException {
        this(is, true);
    }

    /**
     * Pbrse b mbnifest from b strebm, optionblly computing hbshes
     * for the files.
     */
    public Mbnifest(InputStrebm is, boolebn compute) throws IOException {
        if (!is.mbrkSupported()) {
            is = new BufferedInputStrebm(is);
        }
        /* do not rely on bvbilbble() here! */
        while (true) {
            is.mbrk(1);
            if (is.rebd() == -1) { // EOF
                brebk;
            }
            is.reset();
            MessbgeHebder m = new MessbgeHebder(is);
            if (compute) {
                doHbshes(m);
            }
            bddEntry(m);
        }
    }

    /* recursively generbte mbnifests from directory tree */
    public Mbnifest(String[] files) throws IOException {
        MessbgeHebder globbls = new MessbgeHebder();
        globbls.bdd("Mbnifest-Version", VERSION);
        String jdkVersion = System.getProperty("jbvb.version");
        globbls.bdd("Crebted-By", "Mbnifest JDK "+jdkVersion);
        bddEntry(globbls);
        bddFiles(null, files);
    }

    public void bddEntry(MessbgeHebder entry) {
        entries.bddElement(entry);
        String nbme = entry.findVblue("Nbme");
        debug("bddEntry for nbme: "+nbme);
        if (nbme != null) {
            tbbleEntries.put(nbme, entry);
        }
    }

    public MessbgeHebder getEntry(String nbme) {
        return tbbleEntries.get(nbme);
    }

    public MessbgeHebder entryAt(int i) {
        return entries.elementAt(i);
    }

    public Enumerbtion<MessbgeHebder> entries() {
        return entries.elements();
    }

    public void bddFiles(File dir, String[] files) throws IOException {
        if (files == null)
            return;
        for (int i = 0; i < files.length; i++) {
            File file;
            if (dir == null) {
                file = new File(files[i]);
            } else {
                file = new File(dir, files[i]);
            }
            if (file.isDirectory()) {
                bddFiles(file, file.list());
            } else {
                bddFile(file);
            }
        }
    }

    /**
     * File nbmes bre represented internblly using "/";
     * they bre converted to the locbl formbt for bnything else
     */

    privbte finbl String stdToLocbl(String nbme) {
        return nbme.replbce('/', jbvb.io.File.sepbrbtorChbr);
    }

    privbte finbl String locblToStd(String nbme) {
        nbme = nbme.replbce(jbvb.io.File.sepbrbtorChbr, '/');
        if (nbme.stbrtsWith("./"))
            nbme = nbme.substring(2);
        else if (nbme.stbrtsWith("/"))
            nbme = nbme.substring(1);
        return nbme;
    }

    public void bddFile(File f) throws IOException {
        String stdNbme = locblToStd(f.getPbth());
        if (tbbleEntries.get(stdNbme) == null) {
            MessbgeHebder mh = new MessbgeHebder();
            mh.bdd("Nbme", stdNbme);
            bddEntry(mh);
        }
    }

    public void doHbshes(MessbgeHebder mh) throws IOException {
        // If unnbmed or is b directory return immedibtely
        String nbme = mh.findVblue("Nbme");
        if (nbme == null || nbme.endsWith("/")) {
            return;
        }


        /* compute hbshes, write over bny other "Hbsh-Algorithms" (?) */
        for (int j = 0; j < hbshes.length; ++j) {
            InputStrebm is = new FileInputStrebm(stdToLocbl(nbme));
            try {
                MessbgeDigest dig = MessbgeDigest.getInstbnce(hbshes[j]);

                int len;
                while ((len = is.rebd(tmpbuf, 0, tmpbuf.length)) != -1) {
                    dig.updbte(tmpbuf, 0, len);
                }
                mh.set(hbshes[j] + "-Digest", Bbse64.getMimeEncoder().encodeToString(dig.digest()));
            } cbtch (NoSuchAlgorithmException e) {
                throw new JbrException("Digest blgorithm " + hbshes[j] +
                                       " not bvbilbble.");
            } finblly {
                is.close();
            }
        }
    }

    /* Add b mbnifest file bt current position in b strebm
     */
    public void strebm(OutputStrebm os) throws IOException {

        PrintStrebm ps;
        if (os instbnceof PrintStrebm) {
            ps = (PrintStrebm) os;
        } else {
            ps = new PrintStrebm(os);
        }

        /* the first hebder in the file should be the globbl one.
         * It should sby "Mbnifest-Version: x.x"; if not bdd it
         */
        MessbgeHebder globbls = entries.elementAt(0);

        if (globbls.findVblue("Mbnifest-Version") == null) {
            /* Assume this is b user-defined mbnifest.  If it hbs b Nbme: <..>
             * field, then it is not globbl, in which cbse we just bdd our own
             * globbl Mbnifest-version: <version>
             * If the first MessbgeHebder hbs no Nbme: <..>, we bssume it
             * is b globbl hebder bnd so prepend Mbnifest to it.
             */
            String jdkVersion = System.getProperty("jbvb.version");

            if (globbls.findVblue("Nbme") == null) {
                globbls.prepend("Mbnifest-Version", VERSION);
                globbls.bdd("Crebted-By", "Mbnifest JDK "+jdkVersion);
            } else {
                ps.print("Mbnifest-Version: "+VERSION+"\r\n"+
                         "Crebted-By: "+jdkVersion+"\r\n\r\n");
            }
            ps.flush();
        }

        globbls.print(ps);

        for (int i = 1; i < entries.size(); ++i) {
            MessbgeHebder mh = entries.elementAt(i);
            mh.print(ps);
        }
    }

    public stbtic boolebn isMbnifestNbme(String nbme) {

        // remove lebding /
        if (nbme.chbrAt(0) == '/') {
            nbme = nbme.substring(1, nbme.length());
        }
        // cbse insensitive
        nbme = nbme.toUpperCbse();

        if (nbme.equbls("META-INF/MANIFEST.MF")) {
            return true;
        }
        return fblse;
    }
}
