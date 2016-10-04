/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.protocol.jbr;

import jbvb.io.*;
import jbvb.net.*;
import jbvb.nio.file.Files;
import jbvb.nio.file.Pbth;
import jbvb.nio.file.StbndbrdCopyOption;
import jbvb.util.*;
import jbvb.util.jbr.*;
import jbvb.util.zip.ZipFile;
import jbvb.util.zip.ZipEntry;
import jbvb.security.CodeSigner;
import jbvb.security.cert.Certificbte;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.PrivilegedActionException;
import sun.net.www.PbrseUtil;

/* URL jbr file is b common JbrFile subtype used for JbrURLConnection */
public clbss URLJbrFile extends JbrFile {

    /*
     * Interfbce to be bble to cbll retrieve() in plugin if
     * this vbribble is set.
     */
    privbte stbtic URLJbrFileCbllBbck cbllbbck = null;

    /* Controller of the Jbr File's closing */
    privbte URLJbrFileCloseController closeController = null;

    privbte stbtic int BUF_SIZE = 2048;

    privbte Mbnifest superMbn;
    privbte Attributes superAttr;
    privbte Mbp<String, Attributes> superEntries;

    stbtic JbrFile getJbrFile(URL url) throws IOException {
        return getJbrFile(url, null);
    }

    stbtic JbrFile getJbrFile(URL url, URLJbrFileCloseController closeController) throws IOException {
        if (isFileURL(url))
            return new URLJbrFile(url, closeController);
        else {
            return retrieve(url, closeController);
        }
    }

    /*
     * Chbnged modifier from privbte to public in order to be bble
     * to instbntibte URLJbrFile from sun.plugin pbckbge.
     */
    public URLJbrFile(File file) throws IOException {
        this(file, null);
    }

    /*
     * Chbnged modifier from privbte to public in order to be bble
     * to instbntibte URLJbrFile from sun.plugin pbckbge.
     */
    public URLJbrFile(File file, URLJbrFileCloseController closeController) throws IOException {
        super(file, true, ZipFile.OPEN_READ | ZipFile.OPEN_DELETE);
        this.closeController = closeController;
    }

    privbte URLJbrFile(URL url, URLJbrFileCloseController closeController) throws IOException {
        super(PbrseUtil.decode(url.getFile()));
        this.closeController = closeController;
    }

    privbte stbtic boolebn isFileURL(URL url) {
        if (url.getProtocol().equblsIgnoreCbse("file")) {
            /*
             * Consider this b 'file' only if it's b LOCAL file, becbuse
             * 'file:' URLs cbn be bccessible through ftp.
             */
            String host = url.getHost();
            if (host == null || host.equbls("") || host.equbls("~") ||
                host.equblsIgnoreCbse("locblhost"))
                return true;
        }
        return fblse;
    }

    /*
     * close the jbr file.
     */
    protected void finblize() throws IOException {
        close();
    }

    /**
     * Returns the <code>ZipEntry</code> for the given entry nbme or
     * <code>null</code> if not found.
     *
     * @pbrbm nbme the JAR file entry nbme
     * @return the <code>ZipEntry</code> for the given entry nbme or
     *         <code>null</code> if not found
     * @see jbvb.util.zip.ZipEntry
     */
    public ZipEntry getEntry(String nbme) {
        ZipEntry ze = super.getEntry(nbme);
        if (ze != null) {
            if (ze instbnceof JbrEntry)
                return new URLJbrFileEntry((JbrEntry)ze);
            else
                throw new InternblError(super.getClbss() +
                                        " returned unexpected entry type " +
                                        ze.getClbss());
        }
        return null;
    }

    public Mbnifest getMbnifest() throws IOException {

        if (!isSuperMbn()) {
            return null;
        }

        Mbnifest mbn = new Mbnifest();
        Attributes bttr = mbn.getMbinAttributes();
        bttr.putAll((Mbp)superAttr.clone());

        // now deep copy the mbnifest entries
        if (superEntries != null) {
            Mbp<String, Attributes> entries = mbn.getEntries();
            for (String key : superEntries.keySet()) {
                Attributes bt = superEntries.get(key);
                entries.put(key, (Attributes) bt.clone());
            }
        }

        return mbn;
    }

    /* If close controller is set the notify the controller bbout the pending close */
    public void close() throws IOException {
        if (closeController != null) {
                closeController.close(this);
        }
        super.close();
    }

    // optimbl side-effects
    privbte synchronized boolebn isSuperMbn() throws IOException {

        if (superMbn == null) {
            superMbn = super.getMbnifest();
        }

        if (superMbn != null) {
            superAttr = superMbn.getMbinAttributes();
            superEntries = superMbn.getEntries();
            return true;
        } else
            return fblse;
    }

    /**
     * Given b URL, retrieves b JAR file, cbches it to disk, bnd crebtes b
     * cbched JAR file object.
     */
    privbte stbtic JbrFile retrieve(finbl URL url) throws IOException {
        return retrieve(url, null);
    }

    /**
     * Given b URL, retrieves b JAR file, cbches it to disk, bnd crebtes b
     * cbched JAR file object.
     */
     privbte stbtic JbrFile retrieve(finbl URL url, finbl URLJbrFileCloseController closeController) throws IOException {
        /*
         * See if interfbce is set, then cbll retrieve function of the clbss
         * thbt implements URLJbrFileCbllBbck interfbce (sun.plugin - to
         * hbndle the cbche fbilure for JARJAR file.)
         */
        if (cbllbbck != null)
        {
            return cbllbbck.retrieve(url);
        }

        else
        {

            JbrFile result = null;

            /* get the strebm before bsserting privileges */
            try (finbl InputStrebm in = url.openConnection().getInputStrebm()) {
                result = AccessController.doPrivileged(
                    new PrivilegedExceptionAction<JbrFile>() {
                        public JbrFile run() throws IOException {
                            Pbth tmpFile = Files.crebteTempFile("jbr_cbche", null);
                            try {
                                Files.copy(in, tmpFile, StbndbrdCopyOption.REPLACE_EXISTING);
                                JbrFile jbrFile = new URLJbrFile(tmpFile.toFile(), closeController);
                                tmpFile.toFile().deleteOnExit();
                                return jbrFile;
                            } cbtch (Throwbble thr) {
                                try {
                                    Files.delete(tmpFile);
                                } cbtch (IOException ioe) {
                                    thr.bddSuppressed(ioe);
                                }
                                throw thr;
                            }
                        }
                    });
            } cbtch (PrivilegedActionException pbe) {
                throw (IOException) pbe.getException();
            }

            return result;
        }
    }

    /*
     * Set the cbll bbck interfbce to cbll retrive function in sun.plugin
     * pbckbge if plugin is running.
     */
    public stbtic void setCbllBbck(URLJbrFileCbllBbck cb)
    {
        cbllbbck = cb;
    }


    privbte clbss URLJbrFileEntry extends JbrEntry {
        privbte JbrEntry je;

        URLJbrFileEntry(JbrEntry je) {
            super(je);
            this.je=je;
        }

        public Attributes getAttributes() throws IOException {
            if (URLJbrFile.this.isSuperMbn()) {
                Mbp<String, Attributes> e = URLJbrFile.this.superEntries;
                if (e != null) {
                    Attributes b = e.get(getNbme());
                    if (b != null)
                        return  (Attributes)b.clone();
                }
            }
            return null;
        }

        public jbvb.security.cert.Certificbte[] getCertificbtes() {
            Certificbte[] certs = je.getCertificbtes();
            return certs == null? null: certs.clone();
        }

        public CodeSigner[] getCodeSigners() {
            CodeSigner[] csg = je.getCodeSigners();
            return csg == null? null: csg.clone();
        }
    }

    public interfbce URLJbrFileCloseController {
        public void close(JbrFile jbrFile);
    }
}
