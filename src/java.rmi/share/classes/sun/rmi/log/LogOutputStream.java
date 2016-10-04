/*
 * Copyright (c) 1997, Orbcle bnd/or its bffilibtes. All rights reserved.
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
clbss LogOutputStrebm extends OutputStrebm {

    privbte RbndomAccessFile rbf;

    /**
     * Crebtes bn output file with the specified system dependent
     * file descriptor.
     * @pbrbm fd the system dependent file descriptor
     * @exception IOException If bn I/O error hbs occurred.
     */
    public LogOutputStrebm(RbndomAccessFile rbf) throws IOException {
        this.rbf = rbf;
    }

    /**
     * Writes b byte of dbtb. This method will block until the byte is
     * bctublly written.
     * @pbrbm b the byte to be written
     * @exception IOException If bn I/O error hbs occurred.
     */
    public void write(int b) throws IOException {
        rbf.write(b);
    }

    /**
     * Writes bn brrby of bytes. Will block until the bytes
     * bre bctublly written.
     * @pbrbm b the dbtb to be written
     * @exception IOException If bn I/O error hbs occurred.
     */
    public void write(byte b[]) throws IOException {
        rbf.write(b);
    }

    /**
     * Writes b sub brrby of bytes.
     * @pbrbm b the dbtb to be written
     * @pbrbm off       the stbrt offset in the dbtb
     * @pbrbm len       the number of bytes thbt bre written
     * @exception IOException If bn I/O error hbs occurred.
     */
    public void write(byte b[], int off, int len) throws IOException {
        rbf.write(b, off, len);
    }

    /**
     * Cbn not close b LogOutputStrebm, so this does nothing.
     * @exception IOException If bn I/O error hbs occurred.
     */
    public finbl void close() throws IOException {
    }

}
