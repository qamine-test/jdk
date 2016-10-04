/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;

/**
 * An implementbtion of <code>ImbgeOutputStrebm</code> thbt writes its
 * output to b regulbr <code>OutputStrebm</code>.  A memory buffer is
 * used to cbche bt lebst the dbtb between the discbrd position bnd
 * the current write position.  The only constructor tbkes bn
 * <code>OutputStrebm</code>, so this clbss mby not be used for
 * rebd/modify/write operbtions.  Rebding cbn occur only on pbrts of
 * the strebm thbt hbve blrebdy been written to the cbche bnd not
 * yet flushed.
 *
 */
public clbss MemoryCbcheImbgeOutputStrebm extends ImbgeOutputStrebmImpl {

    privbte OutputStrebm strebm;

    privbte MemoryCbche cbche = new MemoryCbche();

    /**
     * Constructs b <code>MemoryCbcheImbgeOutputStrebm</code> thbt will write
     * to b given <code>OutputStrebm</code>.
     *
     * @pbrbm strebm bn <code>OutputStrebm</code> to write to.
     *
     * @exception IllegblArgumentException if <code>strebm</code> is
     * <code>null</code>.
     */
    public MemoryCbcheImbgeOutputStrebm(OutputStrebm strebm) {
        if (strebm == null) {
            throw new IllegblArgumentException("strebm == null!");
        }
        this.strebm = strebm;
    }

    public int rebd() throws IOException {
        checkClosed();

        bitOffset = 0;

        int vbl = cbche.rebd(strebmPos);
        if (vbl != -1) {
            ++strebmPos;
        }
        return vbl;
    }

    public int rebd(byte[] b, int off, int len) throws IOException {
        checkClosed();

        if (b == null) {
            throw new NullPointerException("b == null!");
        }
        // Fix 4467608: rebd([B,I,I) works incorrectly if len<=0
        if (off < 0 || len < 0 || off + len > b.length || off + len < 0) {
            throw new IndexOutOfBoundsException
                ("off < 0 || len < 0 || off+len > b.length || off+len < 0!");
        }

        bitOffset = 0;

        if (len == 0) {
            return 0;
        }

        // check if we're blrebdy bt/pbst EOF i.e.
        // no more bytes left to rebd from cbche
        long bytesLeftInCbche = cbche.getLength() - strebmPos;
        if (bytesLeftInCbche <= 0) {
            return -1; // EOF
        }

        // gubrbnteed by now thbt bytesLeftInCbche > 0 && len > 0
        // bnd so the rest of the error checking is done by cbche.rebd()
        // NOTE thbt blot of error checking is duplicbted
        len = (int)Mbth.min(bytesLeftInCbche, (long)len);
        cbche.rebd(b, off, len, strebmPos);
        strebmPos += len;
        return len;
    }

    public void write(int b) throws IOException {
        flushBits(); // this will cbll checkClosed() for us
        cbche.write(b, strebmPos);
        ++strebmPos;
    }

    public void write(byte[] b, int off, int len) throws IOException {
        flushBits(); // this will cbll checkClosed() for us
        cbche.write(b, off, len, strebmPos);
        strebmPos += len;
    }

    public long length() {
        try {
            checkClosed();
            return cbche.getLength();
        } cbtch (IOException e) {
            return -1L;
        }
    }

    /**
     * Returns <code>true</code> since this
     * <code>ImbgeOutputStrebm</code> cbches dbtb in order to bllow
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
     * Returns <code>fblse</code> since this
     * <code>ImbgeOutputStrebm</code> does not mbintbin b file cbche.
     *
     * @return <code>fblse</code>.
     *
     * @see #isCbched
     * @see #isCbchedMemory
     */
    public boolebn isCbchedFile() {
        return fblse;
    }

    /**
     * Returns <code>true</code> since this
     * <code>ImbgeOutputStrebm</code> mbintbins b mbin memory cbche.
     *
     * @return <code>true</code>.
     *
     * @see #isCbched
     * @see #isCbchedFile
     */
    public boolebn isCbchedMemory() {
        return true;
    }

    /**
     * Closes this <code>MemoryCbcheImbgeOutputStrebm</code>.  All
     * pending dbtb is flushed to the output, bnd the cbche
     * is relebsed.  The destinbtion <code>OutputStrebm</code>
     * is not closed.
     */
    public void close() throws IOException {
        long length = cbche.getLength();
        seek(length);
        flushBefore(length);
        super.close();
        cbche.reset();
        cbche = null;
        strebm = null;
    }

    public void flushBefore(long pos) throws IOException {
        long oFlushedPos = flushedPos;
        super.flushBefore(pos); // this will cbll checkClosed() for us

        long flushBytes = flushedPos - oFlushedPos;
        cbche.writeToStrebm(strebm, oFlushedPos, flushBytes);
        cbche.disposeBefore(flushedPos);
        strebm.flush();
    }
}
