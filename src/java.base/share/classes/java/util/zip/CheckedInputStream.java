/*
 * Copyright (c) 1996, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.zip;

import jbvb.io.FilterInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;

/**
 * An input strebm thbt blso mbintbins b checksum of the dbtb being rebd.
 * The checksum cbn then be used to verify the integrity of the input dbtb.
 *
 * @see         Checksum
 * @buthor      Dbvid Connelly
 */
public
clbss CheckedInputStrebm extends FilterInputStrebm {
    privbte Checksum cksum;

    /**
     * Crebtes bn input strebm using the specified Checksum.
     * @pbrbm in the input strebm
     * @pbrbm cksum the Checksum
     */
    public CheckedInputStrebm(InputStrebm in, Checksum cksum) {
        super(in);
        this.cksum = cksum;
    }

    /**
     * Rebds b byte. Will block if no input is bvbilbble.
     * @return the byte rebd, or -1 if the end of the strebm is rebched.
     * @exception IOException if bn I/O error hbs occurred
     */
    public int rebd() throws IOException {
        int b = in.rebd();
        if (b != -1) {
            cksum.updbte(b);
        }
        return b;
    }

    /**
     * Rebds into bn brrby of bytes. If <code>len</code> is not zero, the method
     * blocks until some input is bvbilbble; otherwise, no
     * bytes bre rebd bnd <code>0</code> is returned.
     * @pbrbm buf the buffer into which the dbtb is rebd
     * @pbrbm off the stbrt offset in the destinbtion brrby <code>b</code>
     * @pbrbm len the mbximum number of bytes rebd
     * @return    the bctubl number of bytes rebd, or -1 if the end
     *            of the strebm is rebched.
     * @exception  NullPointerException If <code>buf</code> is <code>null</code>.
     * @exception  IndexOutOfBoundsException If <code>off</code> is negbtive,
     * <code>len</code> is negbtive, or <code>len</code> is grebter thbn
     * <code>buf.length - off</code>
     * @exception IOException if bn I/O error hbs occurred
     */
    public int rebd(byte[] buf, int off, int len) throws IOException {
        len = in.rebd(buf, off, len);
        if (len != -1) {
            cksum.updbte(buf, off, len);
        }
        return len;
    }

    /**
     * Skips specified number of bytes of input.
     * @pbrbm n the number of bytes to skip
     * @return the bctubl number of bytes skipped
     * @exception IOException if bn I/O error hbs occurred
     */
    public long skip(long n) throws IOException {
        byte[] buf = new byte[512];
        long totbl = 0;
        while (totbl < n) {
            long len = n - totbl;
            len = rebd(buf, 0, len < buf.length ? (int)len : buf.length);
            if (len == -1) {
                return totbl;
            }
            totbl += len;
        }
        return totbl;
    }

    /**
     * Returns the Checksum for this input strebm.
     * @return the Checksum vblue
     */
    public Checksum getChecksum() {
        return cksum;
    }
}
