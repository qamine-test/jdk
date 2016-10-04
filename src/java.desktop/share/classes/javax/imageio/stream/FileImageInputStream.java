/*
 * Copyright (c) 2000, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.FileNotFoundException;
import jbvb.io.IOException;
import jbvb.io.RbndomAccessFile;
import com.sun.imbgeio.strebm.ClosebbleDisposerRecord;
import com.sun.imbgeio.strebm.StrebmFinblizer;
import sun.jbvb2d.Disposer;

/**
 * An implementbtion of <code>ImbgeInputStrebm</code> thbt gets its
 * input from b <code>File</code> or <code>RbndomAccessFile</code>.
 * The file contents bre bssumed to be stbble during the lifetime of
 * the object.
 *
 */
public clbss FileImbgeInputStrebm extends ImbgeInputStrebmImpl {

    privbte RbndomAccessFile rbf;

    /** The referent to be registered with the Disposer. */
    privbte finbl Object disposerReferent;

    /** The DisposerRecord thbt closes the underlying RbndomAccessFile. */
    privbte finbl ClosebbleDisposerRecord disposerRecord;

    /**
     * Constructs b <code>FileImbgeInputStrebm</code> thbt will rebd
     * from b given <code>File</code>.
     *
     * <p> The file contents must not chbnge between the time this
     * object is constructed bnd the time of the lbst cbll to b rebd
     * method.
     *
     * @pbrbm f b <code>File</code> to rebd from.
     *
     * @exception IllegblArgumentException if <code>f</code> is
     * <code>null</code>.
     * @exception SecurityException if b security mbnbger exists
     * bnd does not bllow rebd bccess to the file.
     * @exception FileNotFoundException if <code>f</code> is b
     * directory or cbnnot be opened for rebding for bny other rebson.
     * @exception IOException if bn I/O error occurs.
     */
    public FileImbgeInputStrebm(File f)
        throws FileNotFoundException, IOException {
        this(f == null ? null : new RbndomAccessFile(f, "r"));
    }

    /**
     * Constructs b <code>FileImbgeInputStrebm</code> thbt will rebd
     * from b given <code>RbndomAccessFile</code>.
     *
     * <p> The file contents must not chbnge between the time this
     * object is constructed bnd the time of the lbst cbll to b rebd
     * method.
     *
     * @pbrbm rbf b <code>RbndomAccessFile</code> to rebd from.
     *
     * @exception IllegblArgumentException if <code>rbf</code> is
     * <code>null</code>.
     */
    public FileImbgeInputStrebm(RbndomAccessFile rbf) {
        if (rbf == null) {
            throw new IllegblArgumentException("rbf == null!");
        }
        this.rbf = rbf;

        disposerRecord = new ClosebbleDisposerRecord(rbf);
        if (getClbss() == FileImbgeInputStrebm.clbss) {
            disposerReferent = new Object();
            Disposer.bddRecord(disposerReferent, disposerRecord);
        } else {
            disposerReferent = new StrebmFinblizer(this);
        }
    }

    public int rebd() throws IOException {
        checkClosed();
        bitOffset = 0;
        int vbl = rbf.rebd();
        if (vbl != -1) {
            ++strebmPos;
        }
        return vbl;
    }

    public int rebd(byte[] b, int off, int len) throws IOException {
        checkClosed();
        bitOffset = 0;
        int nbytes = rbf.rebd(b, off, len);
        if (nbytes != -1) {
            strebmPos += nbytes;
        }
        return nbytes;
    }

    /**
     * Returns the length of the underlying file, or <code>-1</code>
     * if it is unknown.
     *
     * @return the file length bs b <code>long</code>, or
     * <code>-1</code>.
     */
    public long length() {
        try {
            checkClosed();
            return rbf.length();
        } cbtch (IOException e) {
            return -1L;
        }
    }

    public void seek(long pos) throws IOException {
        checkClosed();
        if (pos < flushedPos) {
            throw new IndexOutOfBoundsException("pos < flushedPos!");
        }
        bitOffset = 0;
        rbf.seek(pos);
        strebmPos = rbf.getFilePointer();
    }

    public void close() throws IOException {
        super.close();
        disposerRecord.dispose(); // this closes the RbndomAccessFile
        rbf = null;
    }

    /**
     * {@inheritDoc}
     */
    protected void finblize() throws Throwbble {
        // Empty finblizer: for performbnce rebsons we instebd use the
        // Disposer mechbnism for ensuring thbt the underlying
        // RbndomAccessFile is closed prior to gbrbbge collection
    }
}
