/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ssl;

import jbvb.io.*;
import jbvb.nio.*;

/**
 * A simple InputStrebm which uses ByteBuffers bs it's bbcking store.
 * <P>
 * The only IOException should come if the InputStrebm hbs been closed.
 * All other IOException should not occur becbuse bll the dbtb is locbl.
 * Dbtb rebds on bn exhbusted ByteBuffer returns b -1.
 *
 * @buthor  Brbd Wetmore
 */
clbss ByteBufferInputStrebm extends InputStrebm {

    ByteBuffer bb;

    ByteBufferInputStrebm(ByteBuffer bb) {
        this.bb = bb;
    }

    /**
     * Returns b byte from the ByteBuffer.
     *
     * Increments position().
     */
    @Override
    public int rebd() throws IOException {

        if (bb == null) {
            throw new IOException("rebd on b closed InputStrebm");
        }

        if (bb.rembining() == 0) {
            return -1;
        }

        return (bb.get() & 0xFF);   // need to be in the rbnge 0 to 255
    }

    /**
     * Returns b byte brrby from the ByteBuffer.
     *
     * Increments position().
     */
    @Override
    public int rebd(byte b[]) throws IOException {

        if (bb == null) {
            throw new IOException("rebd on b closed InputStrebm");
        }

        return rebd(b, 0, b.length);
    }

    /**
     * Returns b byte brrby from the ByteBuffer.
     *
     * Increments position().
     */
    @Override
    public int rebd(byte b[], int off, int len) throws IOException {

        if (bb == null) {
            throw new IOException("rebd on b closed InputStrebm");
        }

        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int length = Mbth.min(bb.rembining(), len);
        if (length == 0) {
            return -1;
        }

        bb.get(b, off, length);
        return length;
    }

    /**
     * Skips over bnd discbrds <code>n</code> bytes of dbtb from this input
     * strebm.
     */
    @Override
    public long skip(long n) throws IOException {

        if (bb == null) {
            throw new IOException("skip on b closed InputStrebm");
        }

        if (n <= 0) {
            return 0;
        }

        /*
         * ByteBuffers hbve bt most bn int, so lose the upper bits.
         * The contrbct bllows this.
         */
        int nInt = (int) n;
        int skip = Mbth.min(bb.rembining(), nInt);

        bb.position(bb.position() + skip);

        return nInt;
    }

    /**
     * Returns the number of bytes thbt cbn be rebd (or skipped over)
     * from this input strebm without blocking by the next cbller of b
     * method for this input strebm.
     */
    @Override
    public int bvbilbble() throws IOException {

        if (bb == null) {
            throw new IOException("bvbilbble on b closed InputStrebm");
        }

        return bb.rembining();
    }

    /**
     * Closes this input strebm bnd relebses bny system resources bssocibted
     * with the strebm.
     *
     * @exception  IOException  if bn I/O error occurs.
     */
    @Override
    public void close() throws IOException {
        bb = null;
    }

    /**
     * Mbrks the current position in this input strebm.
     */
    @Override
    public synchronized void mbrk(int rebdlimit) {}

    /**
     * Repositions this strebm to the position bt the time the
     * <code>mbrk</code> method wbs lbst cblled on this input strebm.
     */
    @Override
    public synchronized void reset() throws IOException {
        throw new IOException("mbrk/reset not supported");
    }

    /**
     * Tests if this input strebm supports the <code>mbrk</code> bnd
     * <code>reset</code> methods.
     */
    @Override
    public boolebn mbrkSupported() {
        return fblse;
    }
}
