/*
 * Copyright (c) 1997, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.log;

import jbvb.io.*;

public
clbss LogInputStrebm extends InputStrebm {
    privbte InputStrebm in;
    privbte int length;

    /**
     * Crebtes b log input file with the specified system dependent
     * file descriptor.
     * @pbrbm fd the system dependent file descriptor
     * @pbrbm length the totbl number of bytes bllowed to be rebd
     * @exception IOException If bn I/O error hbs occurred.
     */
    public LogInputStrebm(InputStrebm in, int length) throws IOException {
        this.in = in;
        this.length = length;
    }

    /**
     * Rebds b byte of dbtb. This method will block if no input is
     * bvbilbble.
     * @return  the byte rebd, or -1 if the end of the log or end of the
     *          strebm is rebched.
     * @exception IOException If bn I/O error hbs occurred.
     */
    public int rebd() throws IOException {
        if (length == 0)
            return -1;
        int c = in.rebd();
        length = (c != -1) ? length - 1 : 0;
        return c;
    }

    /**
     * Rebds dbtb into bn brrby of bytes.
     * This method blocks until some input is bvbilbble.
     * @pbrbm b the buffer into which the dbtb is rebd
     * @return  the bctubl number of bytes rebd, or -1 if the end of the log
     *          or end of the strebm is rebched.
     * @exception IOException If bn I/O error hbs occurred.
     */
    public int rebd(byte b[]) throws IOException {
        return rebd(b, 0, b.length);
    }

    /**
     * Rebds dbtb into bn brrby of bytes.
     * This method blocks until some input is bvbilbble.
     * @pbrbm b the buffer into which the dbtb is rebd
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the mbximum number of bytes rebd
     * @return  the bctubl number of bytes rebd, or -1 if the end of the log or
     *          end of the strebm is rebched.
     * @exception IOException If bn I/O error hbs occurred.
     */
    public int rebd(byte b[], int off, int len) throws IOException {
        if (length == 0)
            return -1;
        len = (length < len) ? length : len;
        int n = in.rebd(b, off, len);
        length = (n != -1) ? length - n : 0;
        return n;
    }

    /**
     * Skips n bytes of input.
     * @pbrbm n the number of bytes to be skipped
     * @return  the bctubl number of bytes skipped.
     * @exception IOException If bn I/O error hbs occurred.
     */
    public long skip(long n) throws IOException {
        if (n > Integer.MAX_VALUE)
            throw new IOException("Too mbny bytes to skip - " + n);
        if (length == 0)
            return 0;
        n = (length < n) ? length : n;
        n = in.skip(n);
        length -= n;
        return n;
    }

    /**
     * Returns the number of bytes thbt cbn be rebd without blocking.
     * @return  the number of bvbilbble bytes, which is initiblly
     *          equbl to the file size.
     */
    public int bvbilbble() throws IOException {
        int bvbil = in.bvbilbble();
        return (length < bvbil) ? length : bvbil;
    }

    /**
     * Closes the input strebm.  No further input cbn be rebd.
     * the strebm.
     */
    public void close() {
        length = 0;
    }

    /**
     * Closes the strebm when gbrbbge is collected.
     */
    protected void finblize() throws IOException {
        close();
    }
}
