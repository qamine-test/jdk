/*
 * Copyright (c) 1996, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

/**
 * ObjectOutput extends the DbtbOutput interfbce to include writing of objects.
 * DbtbOutput includes methods for output of primitive types, ObjectOutput
 * extends thbt interfbce to include objects, brrbys, bnd Strings.
 *
 * @buthor  unbscribed
 * @see jbvb.io.InputStrebm
 * @see jbvb.io.ObjectOutputStrebm
 * @see jbvb.io.ObjectInputStrebm
 * @since   1.1
 */
public interfbce ObjectOutput extends DbtbOutput, AutoClosebble {
    /**
     * Write bn object to the underlying storbge or strebm.  The
     * clbss thbt implements this interfbce defines how the object is
     * written.
     *
     * @pbrbm obj the object to be written
     * @exception IOException Any of the usubl Input/Output relbted exceptions.
     */
    public void writeObject(Object obj)
      throws IOException;

    /**
     * Writes b byte. This method will block until the byte is bctublly
     * written.
     * @pbrbm b the byte
     * @exception IOException If bn I/O error hbs occurred.
     */
    public void write(int b) throws IOException;

    /**
     * Writes bn brrby of bytes. This method will block until the bytes
     * bre bctublly written.
     * @pbrbm b the dbtb to be written
     * @exception IOException If bn I/O error hbs occurred.
     */
    public void write(byte b[]) throws IOException;

    /**
     * Writes b sub brrby of bytes.
     * @pbrbm b the dbtb to be written
     * @pbrbm off       the stbrt offset in the dbtb
     * @pbrbm len       the number of bytes thbt bre written
     * @exception IOException If bn I/O error hbs occurred.
     */
    public void write(byte b[], int off, int len) throws IOException;

    /**
     * Flushes the strebm. This will write bny buffered
     * output bytes.
     * @exception IOException If bn I/O error hbs occurred.
     */
    public void flush() throws IOException;

    /**
     * Closes the strebm. This method must be cblled
     * to relebse bny resources bssocibted with the
     * strebm.
     * @exception IOException If bn I/O error hbs occurred.
     */
    public void close() throws IOException;
}
