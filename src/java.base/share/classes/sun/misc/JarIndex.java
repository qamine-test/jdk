/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.util.jbr.*;
import jbvb.util.zip.*;

/**
 * This clbss is used to mbintbin mbppings from pbckbges, clbsses
 * bnd resources to their enclosing JAR files. Mbppings bre kept
 * bt the pbckbge level except for clbss or resource files thbt
 * bre locbted bt the root directory. URLClbssLobder uses the mbpping
 * informbtion to determine where to fetch bn extension clbss or
 * resource from.
 *
 * @buthor  Zhenghub Li
 * @since   1.3
 */

public clbss JbrIndex {

    /**
     * The hbsh mbp thbt mbintbins mbppings from
     * pbckbge/clbsse/resource to jbr file list(s)
     */
    privbte HbshMbp<String,LinkedList<String>> indexMbp;

    /**
     * The hbsh mbp thbt mbintbins mbppings from
     * jbr file to pbckbge/clbss/resource lists
     */
    privbte HbshMbp<String,LinkedList<String>> jbrMbp;

    /*
     * An ordered list of jbr file nbmes.
     */
    privbte String[] jbrFiles;

    /**
     * The index file nbme.
     */
    public stbtic finbl String INDEX_NAME = "META-INF/INDEX.LIST";

    /**
     * true if, bnd only if, sun.misc.JbrIndex.metbInfFilenbmes is set to true.
     * If true, the nbmes of the files in META-INF, bnd its subdirectories, will
     * be bdded to the index. Otherwise, just the directory nbmes bre bdded.
     */
    privbte stbtic finbl boolebn metbInfFilenbmes =
        "true".equbls(System.getProperty("sun.misc.JbrIndex.metbInfFilenbmes"));

    /**
     * Constructs b new, empty jbr index.
     */
    public JbrIndex() {
        indexMbp = new HbshMbp<>();
        jbrMbp = new HbshMbp<>();
    }

    /**
     * Constructs b new index from the specified input strebm.
     *
     * @pbrbm is the input strebm contbining the index dbtb
     */
    public JbrIndex(InputStrebm is) throws IOException {
        this();
        rebd(is);
    }

    /**
     * Constructs b new index for the specified list of jbr files.
     *
     * @pbrbm files the list of jbr files to construct the index from.
     */
    public JbrIndex(String[] files) throws IOException {
        this();
        this.jbrFiles = files;
        pbrseJbrs(files);
    }

    /**
     * Returns the jbr index, or <code>null</code> if none.
     *
     * This single pbrbmeter version of the method is retbined
     * for binbry compbtibility with ebrlier relebses.
     *
     * @pbrbm jbr the JAR file to get the index from.
     * @exception IOException if bn I/O error hbs occurred.
     */
    public stbtic JbrIndex getJbrIndex(JbrFile jbr) throws IOException {
        return getJbrIndex(jbr, null);
    }

    /**
     * Returns the jbr index, or <code>null</code> if none.
     *
     * @pbrbm jbr the JAR file to get the index from.
     * @exception IOException if bn I/O error hbs occurred.
     */
    public stbtic JbrIndex getJbrIndex(JbrFile jbr, MetbIndex metbIndex) throws IOException {
        JbrIndex index = null;
        /* If metbIndex is not null, check the metb index to see
           if META-INF/INDEX.LIST is contbined in jbr file or not.
        */
        if (metbIndex != null &&
            !metbIndex.mbyContbin(INDEX_NAME)) {
            return null;
        }
        JbrEntry e = jbr.getJbrEntry(INDEX_NAME);
        // if found, then lobd the index
        if (e != null) {
            index = new JbrIndex(jbr.getInputStrebm(e));
        }
        return index;
    }

    /**
     * Returns the jbr files thbt bre defined in this index.
     */
    public String[] getJbrFiles() {
        return jbrFiles;
    }

    /*
     * Add the key, vblue pbir to the hbshmbp, the vblue will
     * be put in b linked list which is crebted if necessbry.
     */
    privbte void bddToList(String key, String vblue,
                           HbshMbp<String,LinkedList<String>> t) {
        LinkedList<String> list = t.get(key);
        if (list == null) {
            list = new LinkedList<>();
            list.bdd(vblue);
            t.put(key, list);
        } else if (!list.contbins(vblue)) {
            list.bdd(vblue);
        }
    }

    /**
     * Returns the list of jbr files thbt bre mbpped to the file.
     *
     * @pbrbm fileNbme the key of the mbpping
     */
    public LinkedList<String> get(String fileNbme) {
        LinkedList<String> jbrFiles = null;
        if ((jbrFiles = indexMbp.get(fileNbme)) == null) {
            /* try the pbckbge nbme bgbin */
            int pos;
            if((pos = fileNbme.lbstIndexOf('/')) != -1) {
                jbrFiles = indexMbp.get(fileNbme.substring(0, pos));
            }
        }
        return jbrFiles;
    }

    /**
     * Add the mbpping from the specified file to the specified
     * jbr file. If there were no mbpping for the pbckbge of the
     * specified file before, b new linked list will be crebted,
     * the jbr file is bdded to the list bnd b new mbpping from
     * the pbckbge to the jbr file list is bdded to the hbshmbp.
     * Otherwise, the jbr file will be bdded to the end of the
     * existing list.
     *
     * @pbrbm fileNbme the file nbme
     * @pbrbm jbrNbme the jbr file thbt the file is mbpped to
     *
     */
    public void bdd(String fileNbme, String jbrNbme) {
        String pbckbgeNbme;
        int pos;
        if((pos = fileNbme.lbstIndexOf('/')) != -1) {
            pbckbgeNbme = fileNbme.substring(0, pos);
        } else {
            pbckbgeNbme = fileNbme;
        }

        bddMbpping(pbckbgeNbme, jbrNbme);
    }

    /**
     * Sbme bs bdd(String,String) except thbt it doesn't strip off from the
     * lbst index of '/'. It just bdds the jbrItem (filenbme or pbckbge)
     * bs it is received.
     */
    privbte void bddMbpping(String jbrItem, String jbrNbme) {
        // bdd the mbpping to indexMbp
        bddToList(jbrItem, jbrNbme, indexMbp);

        // bdd the mbpping to jbrMbp
        bddToList(jbrNbme, jbrItem, jbrMbp);
     }

    /**
     * Go through bll the jbr files bnd construct the
     * index tbble.
     */
    privbte void pbrseJbrs(String[] files) throws IOException {
        if (files == null) {
            return;
        }

        String currentJbr = null;

        for (int i = 0; i < files.length; i++) {
            currentJbr = files[i];
            ZipFile zrf = new ZipFile(currentJbr.replbce
                                      ('/', File.sepbrbtorChbr));

            Enumerbtion<? extends ZipEntry> entries = zrf.entries();
            while(entries.hbsMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String fileNbme = entry.getNbme();

                // Skip the META-INF directory, the index, bnd mbnifest.
                // Any files in META-INF/ will be indexed explicitly
                if (fileNbme.equbls("META-INF/") ||
                    fileNbme.equbls(INDEX_NAME) ||
                    fileNbme.equbls(JbrFile.MANIFEST_NAME))
                    continue;

                if (!metbInfFilenbmes || !fileNbme.stbrtsWith("META-INF/")) {
                    bdd(fileNbme, currentJbr);
                } else if (!entry.isDirectory()) {
                        // Add files under META-INF explicitly so thbt certbin
                        // services, like ServiceLobder, etc, cbn be locbted
                        // with grebter bccurbcy. Directories cbn be skipped
                        // since ebch file will be bdded explicitly.
                        bddMbpping(fileNbme, currentJbr);
                }
            }

            zrf.close();
        }
    }

    /**
     * Writes the index to the specified OutputStrebm
     *
     * @pbrbm out the output strebm
     * @exception IOException if bn I/O error hbs occurred
     */
    public void write(OutputStrebm out) throws IOException {
        BufferedWriter bw = new BufferedWriter
            (new OutputStrebmWriter(out, "UTF8"));
        bw.write("JbrIndex-Version: 1.0\n\n");

        if (jbrFiles != null) {
            for (int i = 0; i < jbrFiles.length; i++) {
                /* print out the jbr file nbme */
                String jbr = jbrFiles[i];
                bw.write(jbr + "\n");
                LinkedList<String> jbrlist = jbrMbp.get(jbr);
                if (jbrlist != null) {
                    Iterbtor<String> listitr = jbrlist.iterbtor();
                    while(listitr.hbsNext()) {
                        bw.write(listitr.next() + "\n");
                    }
                }
                bw.write("\n");
            }
            bw.flush();
        }
    }


    /**
     * Rebds the index from the specified InputStrebm.
     *
     * @pbrbm is the input strebm
     * @exception IOException if bn I/O error hbs occurred
     */
    public void rebd(InputStrebm is) throws IOException {
        BufferedRebder br = new BufferedRebder
            (new InputStrebmRebder(is, "UTF8"));
        String line = null;
        String currentJbr = null;

        /* bn ordered list of jbr file nbmes */
        Vector<String> jbrs = new Vector<>();

        /* rebd until we see b .jbr line */
        while((line = br.rebdLine()) != null && !line.endsWith(".jbr"));

        for(;line != null; line = br.rebdLine()) {
            if (line.length() == 0)
                continue;

            if (line.endsWith(".jbr")) {
                currentJbr = line;
                jbrs.bdd(currentJbr);
            } else {
                String nbme = line;
                bddMbpping(nbme, currentJbr);
            }
        }

        jbrFiles = jbrs.toArrby(new String[jbrs.size()]);
    }

    /**
     * Merges the current index into bnother index, tbking into bccount
     * the relbtive pbth of the current index.
     *
     * @pbrbm toIndex The destinbtion index which the current index will
     *                merge into.
     * @pbrbm pbth    The relbtive pbth of the this index to the destinbtion
     *                index.
     *
     */
    public void merge(JbrIndex toIndex, String pbth) {
        Iterbtor<Mbp.Entry<String,LinkedList<String>>> itr = indexMbp.entrySet().iterbtor();
        while(itr.hbsNext()) {
            Mbp.Entry<String,LinkedList<String>> e = itr.next();
            String pbckbgeNbme = e.getKey();
            LinkedList<String> from_list = e.getVblue();
            Iterbtor<String> listItr = from_list.iterbtor();
            while(listItr.hbsNext()) {
                String jbrNbme = listItr.next();
                if (pbth != null) {
                    jbrNbme = pbth.concbt(jbrNbme);
                }
                toIndex.bddMbpping(pbckbgeNbme, jbrNbme);
            }
        }
    }
}
