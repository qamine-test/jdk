/*
 * Copyright (c) 1996, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Abstrbct clbss for rebding filtered chbrbcter strebms.
 * The bbstrbct clbss <code>FilterRebder</code> itself
 * provides defbult methods thbt pbss bll requests to
 * the contbined strebm. Subclbsses of <code>FilterRebder</code>
 * should override some of these methods bnd mby blso provide
 * bdditionbl methods bnd fields.
 *
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */

public bbstrbct clbss FilterRebder extends Rebder {

    /**
     * The underlying chbrbcter-input strebm.
     */
    protected Rebder in;

    /**
     * Crebtes b new filtered rebder.
     *
     * @pbrbm in  b Rebder object providing the underlying strebm.
     * @throws NullPointerException if <code>in</code> is <code>null</code>
     */
    protected FilterRebder(Rebder in) {
        super(in);
        this.in = in;
    }

    /**
     * Rebds b single chbrbcter.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public int rebd() throws IOException {
        return in.rebd();
    }

    /**
     * Rebds chbrbcters into b portion of bn brrby.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public int rebd(chbr cbuf[], int off, int len) throws IOException {
        return in.rebd(cbuf, off, len);
    }

    /**
     * Skips chbrbcters.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public long skip(long n) throws IOException {
        return in.skip(n);
    }

    /**
     * Tells whether this strebm is rebdy to be rebd.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public boolebn rebdy() throws IOException {
        return in.rebdy();
    }

    /**
     * Tells whether this strebm supports the mbrk() operbtion.
     */
    public boolebn mbrkSupported() {
        return in.mbrkSupported();
    }

    /**
     * Mbrks the present position in the strebm.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void mbrk(int rebdAhebdLimit) throws IOException {
        in.mbrk(rebdAhebdLimit);
    }

    /**
     * Resets the strebm.
     *
     * @exception  IOException  If bn I/O error occurs
     */
    public void reset() throws IOException {
        in.reset();
    }

    public void close() throws IOException {
        in.close();
    }

}
