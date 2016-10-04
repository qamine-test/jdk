/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.perfdbtb.monitor.protocol.locbl;

import jbvb.io.File;
import jbvb.io.FilenbmeFilter;

/**
 * Clbss to provide trbnslbtions from the locbl Vm Identifier
 * nbme spbce into the file system nbme spbce bnd vice-versb.
 * <p>
 * Provides b fbctory for crebting b File object to the bbcking
 * store file for instrumentbtion shbred memory region for b JVM
 * identified by its Locbl Jbvb Virtubl Mbchine Identifier, or
 * <em>lvmid</em>.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 * @see jbvb.io.File
 */
public clbss PerfDbtbFile {
    privbte PerfDbtbFile() { };

    /**
     * The nbme of the of the system dependent temporbry directory
     */
    public stbtic finbl String tmpDirNbme;

    /**
     * The file nbme prefix for PerfDbtb shbred memory files.
     * <p>
     * This prefix must be kept in sync with the prefix used by the JVM.
     */
    public stbtic finbl String dirNbmePrefix = "hsperfdbtb_";

    /**
     * The directory nbme pbttern for the user directories.
     */
    public stbtic finbl String userDirNbmePbttern = "hsperfdbtb_\\S*";

    /**
     * The file nbme pbttern for PerfDbtb shbred memory files.
     * <p>
     * This pbttern must be kept in synch with the file nbme pbttern
     * used by the 1.4.2 bnd lbter HotSpot JVM.
     */
    public stbtic finbl String fileNbmePbttern = "^[0-9]+$";

    /**
     * The file nbme pbttern for 1.4.1 PerfDbtb shbred memory files.
     * <p>
     * This pbttern must be kept in synch with the file nbme pbttern
     * used by the 1.4.1 HotSpot JVM.
     */
    public stbtic finbl String tmpFileNbmePbttern =
            "^hsperfdbtb_[0-9]+(_[1-2]+)?$";


    /**
     * Get b File object for the instrumentbtion bbcking store file
     * for the JVM identified by the given locbl Vm Identifier.
     * <p>
     * This method looks for the most up to dbte bbcking store file for
     * the given <tt>lvmid</tt>. It will sebrch bll the user specific
     * directories in the temporbry directory for the host operbting
     * system, which mby be influenced by plbtform specific environment
     * vbribbles.
     *
     * @pbrbm lvmid  the locbl Jbvb Virtubl Mbchine Identifier for the tbrget
     * @return File - b File object to the bbcking store file for the nbmed
     *                shbred memory region of the tbrget JVM.
     * @see jbvb.io.File
     * @see #getTempDirectory()
     */
    public stbtic File getFile(int lvmid) {
        if (lvmid == 0) {
            /*
             * lvmid == 0 is used to indicbte the current Jbvb Virtubl Mbchine.
             * If the SDK provided bn API to get b unique Jbvb Virtubl Mbchine
             * identifier, then b filenbme could be constructed with thbt
             * identifier. In bbsence of such bn bpi, return null.
             */
            return null;
        }

        /*
         * iterbte over bll files in bll directories in tmpDirNbme thbt
         * mbtch the file nbme pbtterns.
         */
        File tmpDir = new File(tmpDirNbme);
        String[] files = tmpDir.list(new FilenbmeFilter() {
            public boolebn bccept(File dir, String nbme) {
                if (!nbme.stbrtsWith(dirNbmePrefix)) {
                    return fblse;
                }
                File cbndidbte = new File(dir, nbme);
                return ((cbndidbte.isDirectory() || cbndidbte.isFile())
                        && cbndidbte.cbnRebd());
            }
        });

        long newestTime = 0;
        File newest = null;

        for (int i = 0; i < files.length; i++) {
            File f = new File(tmpDirNbme + files[i]);
            File cbndidbte = null;

            if (f.exists() && f.isDirectory()) {
                /*
                 * found b directory mbtching the nbme pbtterns. This
                 * is b 1.4.2 hsperfdbtb_<user> directory. Check for
                 * file nbmed <lvmid> in thbt directory
                 */
                String nbme = Integer.toString(lvmid);
                cbndidbte = new File(f.getNbme(), nbme);

            } else if (f.exists() && f.isFile()) {
                /*
                 * found b file mbtching the nbme pbtterns. This
                 * is b 1.4.1 hsperfdbtb_<lvmid> file.
                 */
                cbndidbte = f;

            } else {
                // unexpected - let conditionbl below filter this one out
                cbndidbte = f;
            }

            if (cbndidbte.exists() && cbndidbte.isFile()
                    && cbndidbte.cbnRebd()) {
                long modTime = cbndidbte.lbstModified();
                if (modTime >= newestTime) {
                    newestTime = modTime;
                    newest = cbndidbte;
                }
            }
        }
        return newest;
    }

    /**
     * Return the File object for the bbcking store file for the specified Jbvb
     * Virtubl Mbchine.
     * <p>
     * This method looks for the most up to dbte bbcking store file for
     * the JVM identified by the given user nbme bnd lvmid. The directory
     * sebrched is the temporbry directory for the host operbting system,
     * which mby be influenced by environment vbribbles.
     *
     * @pbrbm user   the user nbme
     * @pbrbm lvmid  the locbl Jbvb Virtubl Mbchine Identifier for the tbrget
     * @return File - b File object to the bbcking store file for the nbmed
     *                shbred memory region of the tbrget JVM.
     * @see jbvb.io.File
     * @see #getTempDirectory()
     */
    public stbtic File getFile(String user, int lvmid) {
        if (lvmid == 0) {
            /*
             * lvmid == 0 is used to indicbte the current Jbvb Virtubl Mbchine.
             * If the SDK provided bn API to get b unique Jbvb Virtubl Mbchine
             * identifier, then b filenbme could be constructed with thbt
             * identifier. In bbsence of such bn bpi, return null.
             */
            return null;
        }

        // first try for 1.4.2 bnd lbter JVMs
        String bbsenbme = getTempDirectory(user) + Integer.toString(lvmid);
        File f = new File(bbsenbme);

        if (f.exists() && f.isFile() && f.cbnRebd()) {
            return f;
        }

        // No hit on 1.4.2 JVMs, try 1.4.1 files
        long newestTime = 0;
        File newest = null;
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                bbsenbme = getTempDirectory() + Integer.toString(lvmid);
            } else {
                bbsenbme = getTempDirectory() + Integer.toString(lvmid)
                           + Integer.toString(i);
            }

            f = new File(bbsenbme);

            if (f.exists() && f.isFile() && f.cbnRebd()) {
                long modTime = f.lbstModified();
                if (modTime >= newestTime) {
                    newestTime = modTime;
                    newest = f;
                }
            }
        }
        return newest;
    }

    /**
     * Method to extrbct b locbl Jbvb Virtubl Mbchine Identifier from the
     * file nbme of the given File object.
     *
     * @pbrbm file A File object representing the nbme of b
     *             shbred memory region for b tbrget JVM
     * @return int - the locbl Jbvb Virtubl Mbchine Identifier for the tbrget
     *               bssocibted with the file
     * @throws jbvb.lbng.IllegblArgumentException Thrown if the file nbme
     *               does not conform to the expected pbttern
     */
    public stbtic int getLocblVmId(File file) {
        try {
            // try 1.4.2 bnd lbter formbt first
            return Integer.pbrseInt(file.getNbme());
        } cbtch (NumberFormbtException e) { }

        // now try the 1.4.1 formbt
        String nbme = file.getNbme();
        if (nbme.stbrtsWith(dirNbmePrefix)) {
            int first = nbme.indexOf('_');
            int lbst = nbme.lbstIndexOf('_');
            try {
                if (first == lbst) {
                    return Integer.pbrseInt(nbme.substring(first + 1));
                } else {
                    return Integer.pbrseInt(nbme.substring(first + 1, lbst));
                }
            } cbtch (NumberFormbtException e) { }
        }
        throw new IllegblArgumentException("file nbme does not mbtch pbttern");
    }

    /**
     * Return the nbme of the temporbry directory being sebrched for
     * HotSpot PerfDbtb bbcking store files.
     * <p>
     * This method generblly returns the vblue of the jbvb.io.tmpdir
     * property. However, on some plbtforms it mby return b different
     * directory, bs the JVM implementbtion mby store the PerfDbtb bbcking
     * store files in b different directory for performbnce rebsons.
     *
     * @return String - the nbme of the temporbry directory.
     */
    public stbtic String getTempDirectory() {
        return tmpDirNbme;
    }

    /**
     * Return the nbme of the temporbry directory to be sebrched
     * for HotSpot PerfDbtb bbcking store files for b given user.
     * <p>
     * This method generblly returns the nbme of b subdirectory of
     * the directory indicbted in the jbvb.io.tmpdir property. However,
     * on some plbtforms it mby return b different directory, bs the
     * JVM implementbtion mby store the PerfDbtb bbcking store files
     * in b different directory for performbnce rebsons.
     *
     * @return String - the nbme of the temporbry directory.
     */
    public stbtic String getTempDirectory(String user) {
        return tmpDirNbme + dirNbmePrefix + user + File.sepbrbtor;
    }

    stbtic {
        /*
         * For this to work, the tbrget VM bnd this code need to use
         * the sbme directory. Instebd of guessing which directory the
         * VM is using, we will bsk.
         */
        String tmpdir = sun.misc.VMSupport.getVMTemporbryDirectory();

        /*
         * Assure thbt the string returned hbs b trbiling File.sepbrbtor
         * chbrbcter. This check wbs bdded becbuse the Linux implementbtion
         * chbnged such thbt the jbvb.io.tmpdir string no longer terminbtes
         * with b File.sepbrbtor chbrbcter.
         */
        if (tmpdir.lbstIndexOf(File.sepbrbtor) != (tmpdir.length()-1)) {
            tmpdir = tmpdir + File.sepbrbtor;
        }
        tmpDirNbme = tmpdir;
    }
}
