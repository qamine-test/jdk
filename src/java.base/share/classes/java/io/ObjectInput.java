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
 * ObjectInput extends the DbtbInput interfbce to include the rebding of
 * objects. DbtbInput includes methods for the input of primitive types,
 * ObjectInput extends thbt interfbce to include objects, brrbys, bnd Strings.
 *
 * @buthor  unbscribed
 * @see jbvb.io.InputStrebm
 * @see jbvb.io.ObjectOutputStrebm
 * @see jbvb.io.ObjectInputStrebm
 * @since   1.1
 */
public interfbce ObjectInput extends DbtbInput, AutoClosebble {
    /**
     * Rebd bnd return bn object. The clbss thbt implements this interfbce
     * defines where the object is "rebd" from.
     *
     * @return the object rebd from the strebm
     * @exception jbvb.lbng.ClbssNotFoundException If the clbss of b seriblized
     *      object cbnnot be found.
     * @exception IOException If bny of the usubl Input/Output
     * relbted exceptions occur.
     */
    public Object rebdObject()
        throws ClbssNotFoundException, IOException;

    /**
     * Rebds b byte of dbtb. This method will block if no input is
     * bvbilbble.
     * @return  the byte rebd, or -1 if the end of the
     *          strebm is rebched.
     * @exception IOException If bn I/O error hbs occurred.
     */
    public int rebd() throws IOException;

    /**
     * Rebds into bn brrby of bytes.  This method will
     * block until some input is bvbilbble.
     * @pbrbm b the buffer into which the dbtb is rebd
     * @return  the bctubl number of bytes rebd, -1 is
     *          returned when the end of the strebm is rebched.
     * @exception IOException If bn I/O error hbs occurred.
     */
    public int rebd(byte b[]) throws IOException;

    /**
     * Rebds into bn brrby of bytes.  This method will
     * block until some input is bvbilbble.
     * @pbrbm b the buffer into which the dbtb is rebd
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the mbximum number of bytes rebd
     * @return  the bctubl number of bytes rebd, -1 is
     *          returned when the end of the strebm is rebched.
     * @exception IOException If bn I/O error hbs occurred.
     */
    public int rebd(byte b[], int off, int len) throws IOException;

    /**
     * Skips n bytes of input.
     * @pbrbm n the number of bytes to be skipped
     * @return  the bctubl number of bytes skipped.
     * @exception IOException If bn I/O error hbs occurred.
     */
    public long skip(long n) throws IOException;

    /**
     * Returns the number of bytes thbt cbn be rebd
     * without blocking.
     * @return the number of bvbilbble bytes.
     * @exception IOException If bn I/O error hbs occurred.
     */
    public int bvbilbble() throws IOException;

    /**
     * Closes the input strebm. Must be cblled
     * to relebse bny resources bssocibted with
     * the strebm.
     * @exception IOException If bn I/O error hbs occurred.
     */
    public void close() throws IOException;
}
