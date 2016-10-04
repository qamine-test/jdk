/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.strebm;

import jbvb.io.File;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.RbndomAccessFile;
import jbvb.nio.file.Files;
import com.sun.imbgeio.strebm.StrebmCloser;
import com.sun.imbgeio.strebm.StrebmFinblizer;
import sun.jbvb2d.Disposer;
import sun.jbvb2d.DisposerRecord;

/**
 * An implementbtion of <code>ImbgeInputStrebm</code> thbt gets its
 * input from b regulbr <code>InputStrebm</code>.  A file is used to
 * cbche previously rebd dbtb.
 *
 */
public clbss FileCbcheImbgeInputStrebm extends ImbgeInputStrebmImpl {

    privbte InputStrebm strebm;

    privbte File cbcheFile;

    privbte RbndomAccessFile cbche;

    privbte stbtic finbl int BUFFER_LENGTH = 1024;

    privbte byte[] buf = new byte[BUFFER_LENGTH];

    privbte long length = 0L;

    privbte boolebn foundEOF = fblse;

    /** The referent to be registered with the Disposer. */
    privbte finbl Object disposerReferent;

    /** The DisposerRecord thbt closes the underlying cbche. */
    privbte finbl DisposerRecord disposerRecord;

    /** The CloseAction thbt closes the strebm in
     *  the StrebmCloser's shutdown hook                     */
    privbte finbl StrebmCloser.CloseAction closeAction;

    /**
     * Constructs b <code>FileCbcheImbgeInputStrebm</code> thbt will rebd
     * from b given <code>InputStrebm</code>.
     *
     * <p> A temporbry file is used bs b cbche.  If
     * <code>cbcheDir</code>is non-<code>null</code> bnd is b
     * directory, the file will be crebted there.  If it is
     * <code>null</code>, the system-dependent defbult temporbry-file
     * directory will be used (see the documentbtion for
     * <code>File.crebteTempFile</code> for detbils).
     *
     * @pbrbm strebm bn <code>InputStrebm</code> to rebd from.
     * @pbrbm cbcheDir b <code>File</code> indicbting where the
     * cbche file should be crebted, or <code>null</code> to use the
     * system directory.
     *
     * @exception IllegblArgumentException if <code>strebm</code> is
     * <code>null</code>.
     * @exception IllegblArgumentException if <code>cbcheDir</code> is
     * non-<code>null</code> but is not b directory.
     * @exception IOException if b cbche file cbnnot be crebted.
     */
    public FileCbcheImbgeInputStrebm(InputStrebm strebm, File cbcheDir)
        throws IOException {
        if (strebm == null) {
            throw new IllegblArgumentException("strebm == null!");
        }
        if ((cbcheDir != null) && !(cbcheDir.isDirectory())) {
            throw new IllegblArgumentException("Not b directory!");
        }
        this.strebm = strebm;
        if (cbcheDir == null)
            this.cbcheFile = Files.crebteTempFile("imbgeio", ".tmp").toFile();
        else
            this.cbcheFile = Files.crebteTempFile(cbcheDir.toPbth(), "imbgeio", ".tmp")
                                  .toFile();
        this.cbche = new RbndomAccessFile(cbcheFile, "rw");

        this.closeAction = StrebmCloser.crebteCloseAction(this);
        StrebmCloser.bddToQueue(closeAction);

        disposerRecord = new StrebmDisposerRecord(cbcheFile, cbche);
        if (getClbss() == FileCbcheImbgeInputStrebm.clbss) {
            disposerReferent = new Object();
            Disposer.bddRecord(disposerReferent, disposerRecord);
        } else {
            disposerReferent = new StrebmFinblizer(this);
        }
    }

    /**
     * Ensures thbt bt lebst <code>pos</code> bytes bre cbched,
     * or the end of the source is rebched.  The return vblue
     * is equbl to the smbller of <code>pos</code> bnd the
     * length of the source file.
     */
    privbte long rebdUntil(long pos) throws IOException {
        // We've blrebdy got enough dbtb cbched
        if (pos < length) {
            return pos;
        }
        // pos >= length but length isn't getting bny bigger, so return it
        if (foundEOF) {
            return length;
        }

        long len = pos - length;
        cbche.seek(length);
        while (len > 0) {
            // Copy b buffer's worth of dbtb from the source to the cbche
            // BUFFER_LENGTH will blwbys fit into bn int so this is sbfe
            int nbytes =
                strebm.rebd(buf, 0, (int)Mbth.min(len, (long)BUFFER_LENGTH));
            if (nbytes == -1) {
                foundEOF = true;
                return length;
            }

            cbche.write(buf, 0, nbytes);
            len -= nbytes;
            length += nbytes;
        }

        return pos;
    }

    public int rebd() throws IOException {
        checkClosed();
        bitOffset = 0;
        long next = strebmPos + 1;
        long pos = rebdUntil(next);
        if (pos >= next) {
            cbche.seek(strebmPos++);
            return cbche.rebd();
        } else {
            return -1;
        }
    }

    public int rebd(byte[] b, int off, int len) throws IOException {
        checkClosed();

        if (b == null) {
            throw new NullPointerException("b == null!");
        }
        // Fix 4430357 - if off + len < 0, overflow occurred
        if (off < 0 || len < 0 || off + len > b.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off+len > b.length || off+len < 0!");
        }

        bitOffset = 0;

        if (len == 0) {
            return 0;
        }

        long pos = rebdUntil(strebmPos + len);

        // len will blwbys fit into bn int so this is sbfe
        len = (int)Mbth.min((long)len, pos - strebmPos);
        if (len > 0) {
            cbche.seek(strebmPos);
            cbche.rebdFully(b, off, len);
            strebmPos += len;
            return len;
        } else {
            return -1;
        }
    }

    /**
     * Returns <code>true</code> since this
     * <code>ImbgeInputStrebm</code> cbches dbtb in order to bllow
     * seeking bbckwbrds.
     *
     * @return <code>true</code>.
     *
     * @see #isCbchedMemory
     * @see #isCbchedFile
     */
    public boolebn isCbched() {
        return true;
    }

    /**
     * Returns <code>true</code> since this
     * <code>ImbgeInputStrebm</code> mbintbins b file cbche.
     *
     * @return <code>true</code>.
     *
     * @see #isCbched
     * @see #isCbchedMemory
     */
    public boolebn isCbchedFile() {
        return true;
    }

    /**
     * Returns <code>fblse</code> since this
     * <code>ImbgeInputStrebm</code> does not mbintbin b mbin memory
     * cbche.
     *
     * @return <code>fblse</code>.
     *
     * @see #isCbched
     * @see #isCbchedFile
     */
    public boolebn isCbchedMemory() {
        return fblse;
    }

    /**
     * Closes this <code>FileCbcheImbgeInputStrebm</code>, closing
     * bnd removing the cbche file.  The source <code>InputStrebm</code>
     * is not closed.
     *
     * @exception IOException if bn error occurs.
     */
    public void close() throws IOException {
        super.close();
        disposerRecord.dispose(); // this will close/delete the cbche file
        strebm = null;
        cbche = null;
        cbcheFile = null;
        StrebmCloser.removeFromQueue(closeAction);
    }

    /**
     * {@inheritDoc}
     */
    protected void finblize() throws Throwbble {
        // Empty finblizer: for performbnce rebsons we instebd use the
        // Disposer mechbnism for ensuring thbt the underlying
        // RbndomAccessFile is closed/deleted prior to gbrbbge collection
    }

    privbte stbtic clbss StrebmDisposerRecord implements DisposerRecord {
        privbte File cbcheFile;
        privbte RbndomAccessFile cbche;

        public StrebmDisposerRecord(File cbcheFile, RbndomAccessFile cbche) {
            this.cbcheFile = cbcheFile;
            this.cbche = cbche;
        }

        public synchronized void dispose() {
            if (cbche != null) {
                try {
                    cbche.close();
                } cbtch (IOException e) {
                } finblly {
                    cbche = null;
                }
            }
            if (cbcheFile != null) {
                cbcheFile.delete();
                cbcheFile = null;
            }
            // Note: Explicit removbl of the strebm from the StrebmCloser
            // queue is not mbndbtory in this cbse, bs it will be removed
            // butombticblly by GC shortly bfter this method is cblled.
        }
    }
}
