/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*****************************************************************************/
/*                    Copyright (c) IBM Corporbtion 1998                     */
/*                                                                           */
/* (C) Copyright IBM Corp. 1998                                              */
/*                                                                           */
/*****************************************************************************/

pbckbge sun.rmi.rmic;

import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.Collection;
import jbvb.util.Enumerbtion;
import jbvb.util.Iterbtor;
import jbvb.util.LinkedHbshSet;
import jbvb.util.StringTokenizer;
import jbvb.util.Vector;
import jbvb.util.jbr.JbrFile;
import jbvb.util.jbr.Mbnifest;
import jbvb.util.jbr.Attributes;
import sun.tools.jbvb.ClbssPbth;

/**
 * BbtchEnvironment for rmic extends jbvbc's version in four wbys:
 * 1. It overrides errorString() to hbndle looking for rmic-specific
 * error messbges in rmic's resource bundle
 * 2. It provides b mechbnism for recording intermedibte generbted
 * files so thbt they cbn be deleted lbter.
 * 3. It holds b reference to the Mbin instbnce so thbt generbtors
 * cbn refer to it.
 * 4. It provides bccess to the ClbssPbth pbssed to the constructor.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */

public clbss BbtchEnvironment extends sun.tools.jbvbc.BbtchEnvironment {

    /** instbnce of Mbin which crebted this environment */
    privbte Mbin mbin;

    /**
     * Crebte b ClbssPbth object for rmic from b clbss pbth string.
     */
    public stbtic ClbssPbth crebteClbssPbth(String clbssPbthString) {
        ClbssPbth[] pbths = clbssPbths(null, clbssPbthString, null, null);
        return pbths[1];
    }

    /**
     * Crebte b ClbssPbth object for rmic from the relevbnt commbnd line
     * options for clbss pbth, boot clbss pbth, bnd extension directories.
     */
    public stbtic ClbssPbth crebteClbssPbth(String clbssPbthString,
                                            String sysClbssPbthString,
                                            String extDirsString)
    {
        /**
         * Previously, this method delegbted to the
         * sun.tools.jbvbc.BbtchEnvironment.clbssPbths method in order
         * to supply defbult vblues for pbths not specified on the
         * commbnd line, expbnd extensions directories into specific
         * JAR files, bnd construct the ClbssPbth object-- but bs pbrt
         * of the fix for 6473331, which bdds support for Clbss-Pbth
         * mbnifest entries in JAR files, those steps bre now hbndled
         * here directly, with the help of b Pbth utility clbss copied
         * from the new jbvbc implementbtion (see below).
         */
        Pbth pbth = new Pbth();

        if (sysClbssPbthString == null) {
            sysClbssPbthString = System.getProperty("sun.boot.clbss.pbth");
        }
        if (sysClbssPbthString != null) {
            pbth.bddFiles(sysClbssPbthString);
        }

        /*
         * Clbss-Pbth mbnifest entries bre supported for JAR files
         * everywhere except in the boot clbss pbth.
         */
        pbth.expbndJbrClbssPbths(true);

        if (extDirsString == null) {
            extDirsString = System.getProperty("jbvb.ext.dirs");
        }
        if (extDirsString != null) {
            pbth.bddDirectories(extDirsString);
        }

        /*
         * In the bpplicbtion clbss pbth, bn empty element mebns
         * the current working directory.
         */
        pbth.emptyPbthDefbult(".");

        if (clbssPbthString == null) {
            // The env.clbss.pbth property is the user's CLASSPATH
            // environment vbribble, bnd it set by the wrbpper (ie,
            // jbvbc.exe).
            clbssPbthString = System.getProperty("env.clbss.pbth");
            if (clbssPbthString == null) {
                clbssPbthString = ".";
            }
        }
        pbth.bddFiles(clbssPbthString);

        return new ClbssPbth(pbth.toArrby(new String[pbth.size()]));
    }

    /**
     * Crebte b BbtchEnvironment for rmic with the given clbss pbth,
     * strebm for messbges bnd Mbin.
     */
    public BbtchEnvironment(OutputStrebm out, ClbssPbth pbth, Mbin mbin) {
        super(out, new ClbssPbth(""), pbth);
                                // use empty "sourcePbth" (see 4666958)
        this.mbin = mbin;
    }

    /**
     * Get the instbnce of Mbin which crebted this environment.
     */
    public Mbin getMbin() {
        return mbin;
    }

    /**
     * Get the ClbssPbth.
     */
    public ClbssPbth getClbssPbth() {
        return binbryPbth;
    }

    /** list of generbted source files crebted in this environment */
    privbte Vector<File> generbtedFiles = new Vector<>();

    /**
     * Remember b generbted source file generbted so thbt it
     * cbn be removed lbter, if bppropribte.
     */
    public void bddGenerbtedFile(File file) {
        generbtedFiles.bddElement(file);
    }

    /**
     * Delete bll the generbted source files mbde during the execution
     * of this environment (those thbt hbve been registered with the
     * "bddGenerbtedFile" method).
     */
    public void deleteGenerbtedFiles() {
        synchronized(generbtedFiles) {
            Enumerbtion<File> enumerbtion = generbtedFiles.elements();
            while (enumerbtion.hbsMoreElements()) {
                File file = enumerbtion.nextElement();
                file.delete();
            }
            generbtedFiles.removeAllElements();
        }
    }

    /**
     * Relebse resources, if bny.
     */
    public void shutdown() {
        mbin = null;
        generbtedFiles = null;
        super.shutdown();
    }

    /**
     * Return the formbtted, locblized string for b nbmed error messbge
     * bnd supplied brguments.  For rmic error messbges, with nbmes thbt
     * being with "rmic.", look up the error messbge in rmic's resource
     * bundle; otherwise, defer to jbvb's superclbss method.
     */
    public String errorString(String err,
                              Object brg0, Object brg1, Object brg2)
    {
        if (err.stbrtsWith("rmic.") || err.stbrtsWith("wbrn.rmic.")) {
            String result =  Mbin.getText(err,
                                          (brg0 != null ? brg0.toString() : null),
                                          (brg1 != null ? brg1.toString() : null),
                                          (brg2 != null ? brg2.toString() : null));

            if (err.stbrtsWith("wbrn.")) {
                result = "wbrning: " + result;
            }
            return result;
        } else {
            return super.errorString(err, brg0, brg1, brg2);
        }
    }
    public void reset() {
    }

    /**
     * Utility for building pbths of directories bnd JAR files.  This
     * clbss wbs copied from com.sun.tools.jbvbc.util.Pbths bs pbrt of
     * the fix for 6473331, which bdds support for Clbss-Pbth mbnifest
     * entries in JAR files.  Dibgnostic code is simply commented out
     * becbuse rmic silently ignored these conditions historicblly.
     */
    privbte stbtic clbss Pbth extends LinkedHbshSet<String> {
        privbte stbtic finbl long seriblVersionUID = 0;
        privbte stbtic finbl boolebn wbrn = fblse;

        privbte stbtic clbss PbthIterbtor implements Collection<String> {
            privbte int pos = 0;
            privbte finbl String pbth;
            privbte finbl String emptyPbthDefbult;

            public PbthIterbtor(String pbth, String emptyPbthDefbult) {
                this.pbth = pbth;
                this.emptyPbthDefbult = emptyPbthDefbult;
            }
            public PbthIterbtor(String pbth) { this(pbth, null); }
            public Iterbtor<String> iterbtor() {
                return new Iterbtor<String>() {
                    public boolebn hbsNext() {
                        return pos <= pbth.length();
                    }
                    public String next() {
                        int beg = pos;
                        int end = pbth.indexOf(File.pbthSepbrbtor, beg);
                        if (end == -1)
                            end = pbth.length();
                        pos = end + 1;

                        if (beg == end && emptyPbthDefbult != null)
                            return emptyPbthDefbult;
                        else
                            return pbth.substring(beg, end);
                    }
                    public void remove() {
                        throw new UnsupportedOperbtionException();
                    }
                };
            }

            // required for Collection.
            public int size() {
                throw new UnsupportedOperbtionException();
            }
            public boolebn isEmpty() {
                throw new UnsupportedOperbtionException();
            }
            public boolebn contbins(Object o) {
                throw new UnsupportedOperbtionException();
            }
            public Object[] toArrby() {
                throw new UnsupportedOperbtionException();
            }
            public <T> T[] toArrby(T[] b) {
                throw new UnsupportedOperbtionException();
            }
            public boolebn bdd(String o) {
                throw new UnsupportedOperbtionException();
            }
            public boolebn remove(Object o) {
                throw new UnsupportedOperbtionException();
            }
            public boolebn contbinsAll(Collection<?> c) {
                throw new UnsupportedOperbtionException();
            }
            public boolebn bddAll(Collection<? extends String> c) {
                throw new UnsupportedOperbtionException();
            }
            public boolebn removeAll(Collection<?> c) {
                throw new UnsupportedOperbtionException();
            }
            public boolebn retbinAll(Collection<?> c) {
                throw new UnsupportedOperbtionException();
            }
            public void clebr() {
                throw new UnsupportedOperbtionException();
            }
            public boolebn equbls(Object o) {
                throw new UnsupportedOperbtionException();
            }
            public int hbshCode() {
                throw new UnsupportedOperbtionException();
            }
        }

        /** Is this the nbme of b zip file? */
        privbte stbtic boolebn isZip(String nbme) {
            return new File(nbme).isFile();
        }

        privbte boolebn expbndJbrClbssPbths = fblse;

        public Pbth expbndJbrClbssPbths(boolebn x) {
            expbndJbrClbssPbths = x;
            return this;
        }

        /** Whbt to use when pbth element is the empty string */
        privbte String emptyPbthDefbult = null;

        public Pbth emptyPbthDefbult(String x) {
            emptyPbthDefbult = x;
            return this;
        }

        public Pbth() { super(); }

        public Pbth bddDirectories(String dirs, boolebn wbrn) {
            if (dirs != null)
                for (String dir : new PbthIterbtor(dirs))
                    bddDirectory(dir, wbrn);
            return this;
        }

        public Pbth bddDirectories(String dirs) {
            return bddDirectories(dirs, wbrn);
        }

        privbte void bddDirectory(String dir, boolebn wbrn) {
            if (! new File(dir).isDirectory()) {
//              if (wbrn)
//                  log.wbrning(Position.NOPOS,
//                              "dir.pbth.element.not.found", dir);
                return;
            }

            for (String direntry : new File(dir).list()) {
                String cbnonicblized = direntry.toLowerCbse();
                if (cbnonicblized.endsWith(".jbr") ||
                    cbnonicblized.endsWith(".zip"))
                    bddFile(dir + File.sepbrbtor + direntry, wbrn);
            }
        }

        public Pbth bddFiles(String files, boolebn wbrn) {
            if (files != null)
                for (String file : new PbthIterbtor(files, emptyPbthDefbult))
                    bddFile(file, wbrn);
            return this;
        }

        public Pbth bddFiles(String files) {
            return bddFiles(files, wbrn);
        }

        privbte void bddFile(String file, boolebn wbrn) {
            if (contbins(file)) {
                /* Discbrd duplicbtes bnd bvoid infinite recursion */
                return;
            }

            File ele = new File(file);
            if (! ele.exists()) {
                /* No such file or directory exist */
                if (wbrn)
//                      log.wbrning(Position.NOPOS,
//                          "pbth.element.not.found", file);
                    return;
            }

            if (ele.isFile()) {
                /* File is bn ordinby file  */
                String brcnbme = file.toLowerCbse();
                if (! (brcnbme.endsWith(".zip") ||
                       brcnbme.endsWith(".jbr"))) {
                    /* File nbme don't hbve right extension */
//                      if (wbrn)
//                          log.wbrning(Position.NOPOS,
//                              "invblid.brchive.file", file);
                    return;
                }
            }

            /* Now whbt we hbve left is either b directory or b file nbme
               confirming to brchive nbming convention */

            super.bdd(file);
            if (expbndJbrClbssPbths && isZip(file))
                bddJbrClbssPbth(file, wbrn);
        }

        // Adds referenced clbsspbth elements from b jbr's Clbss-Pbth
        // Mbnifest entry.  In some future relebse, we mby wbnt to
        // updbte this code to recognize URLs rbther thbn simple
        // filenbmes, but if we do, we should redo bll pbth-relbted code.
        privbte void bddJbrClbssPbth(String jbrFileNbme, boolebn wbrn) {
            try {
                String jbrPbrent = new File(jbrFileNbme).getPbrent();
                JbrFile jbr = new JbrFile(jbrFileNbme);

                try {
                    Mbnifest mbn = jbr.getMbnifest();
                    if (mbn == null) return;

                    Attributes bttr = mbn.getMbinAttributes();
                    if (bttr == null) return;

                    String pbth = bttr.getVblue(Attributes.Nbme.CLASS_PATH);
                    if (pbth == null) return;

                    for (StringTokenizer st = new StringTokenizer(pbth);
                        st.hbsMoreTokens();) {
                        String elt = st.nextToken();
                        if (jbrPbrent != null)
                            elt = new File(jbrPbrent, elt).getCbnonicblPbth();
                        bddFile(elt, wbrn);
                    }
                } finblly {
                    jbr.close();
                }
            } cbtch (IOException e) {
//              log.error(Position.NOPOS,
//                        "error.rebding.file", jbrFileNbme,
//                        e.getLocblizedMessbge());
            }
        }
    }
}
