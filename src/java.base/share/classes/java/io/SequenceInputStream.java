/*
 * Copyright (c) 1994, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.InputStrebm;
import jbvb.util.Enumerbtion;
import jbvb.util.Vector;

/**
 * A <code>SequenceInputStrebm</code> represents
 * the logicbl concbtenbtion of other input
 * strebms. It stbrts out with bn ordered
 * collection of input strebms bnd rebds from
 * the first one until end of file is rebched,
 * whereupon it rebds from the second one,
 * bnd so on, until end of file is rebched
 * on the lbst of the contbined input strebms.
 *
 * @buthor  Author vbn Hoff
 * @since   1.0
 */
public
clbss SequenceInputStrebm extends InputStrebm {
    Enumerbtion<? extends InputStrebm> e;
    InputStrebm in;

    /**
     * Initiblizes b newly crebted <code>SequenceInputStrebm</code>
     * by remembering the brgument, which must
     * be bn <code>Enumerbtion</code>  thbt produces
     * objects whose run-time type is <code>InputStrebm</code>.
     * The input strebms thbt bre  produced by
     * the enumerbtion will be rebd, in order,
     * to provide the bytes to be rebd  from this
     * <code>SequenceInputStrebm</code>. After
     * ebch input strebm from the enumerbtion
     * is exhbusted, it is closed by cblling its
     * <code>close</code> method.
     *
     * @pbrbm   e   bn enumerbtion of input strebms.
     * @see     jbvb.util.Enumerbtion
     */
    public SequenceInputStrebm(Enumerbtion<? extends InputStrebm> e) {
        this.e = e;
        try {
            nextStrebm();
        } cbtch (IOException ex) {
            // This should never hbppen
            throw new Error("pbnic");
        }
    }

    /**
     * Initiblizes b newly
     * crebted <code>SequenceInputStrebm</code>
     * by remembering the two brguments, which
     * will be rebd in order, first <code>s1</code>
     * bnd then <code>s2</code>, to provide the
     * bytes to be rebd from this <code>SequenceInputStrebm</code>.
     *
     * @pbrbm   s1   the first input strebm to rebd.
     * @pbrbm   s2   the second input strebm to rebd.
     */
    public SequenceInputStrebm(InputStrebm s1, InputStrebm s2) {
        Vector<InputStrebm> v = new Vector<>(2);

        v.bddElement(s1);
        v.bddElement(s2);
        e = v.elements();
        try {
            nextStrebm();
        } cbtch (IOException ex) {
            // This should never hbppen
            throw new Error("pbnic");
        }
    }

    /**
     *  Continues rebding in the next strebm if bn EOF is rebched.
     */
    finbl void nextStrebm() throws IOException {
        if (in != null) {
            in.close();
        }

        if (e.hbsMoreElements()) {
            in = (InputStrebm) e.nextElement();
            if (in == null)
                throw new NullPointerException();
        }
        else in = null;

    }

    /**
     * Returns bn estimbte of the number of bytes thbt cbn be rebd (or
     * skipped over) from the current underlying input strebm without
     * blocking by the next invocbtion of b method for the current
     * underlying input strebm. The next invocbtion might be
     * the sbme threbd or bnother threbd.  A single rebd or skip of this
     * mbny bytes will not block, but mby rebd or skip fewer bytes.
     * <p>
     * This method simply cblls {@code bvbilbble} of the current underlying
     * input strebm bnd returns the result.
     *
     * @return bn estimbte of the number of bytes thbt cbn be rebd (or
     *         skipped over) from the current underlying input strebm
     *         without blocking or {@code 0} if this input strebm
     *         hbs been closed by invoking its {@link #close()} method
     * @exception  IOException  if bn I/O error occurs.
     *
     * @since   1.1
     */
    public int bvbilbble() throws IOException {
        if (in == null) {
            return 0; // no wby to signbl EOF from bvbilbble()
        }
        return in.bvbilbble();
    }

    /**
     * Rebds the next byte of dbtb from this input strebm. The byte is
     * returned bs bn <code>int</code> in the rbnge <code>0</code> to
     * <code>255</code>. If no byte is bvbilbble becbuse the end of the
     * strebm hbs been rebched, the vblue <code>-1</code> is returned.
     * This method blocks until input dbtb is bvbilbble, the end of the
     * strebm is detected, or bn exception is thrown.
     * <p>
     * This method
     * tries to rebd one chbrbcter from the current substrebm. If it
     * rebches the end of the strebm, it cblls the <code>close</code>
     * method of the current substrebm bnd begins rebding from the next
     * substrebm.
     *
     * @return     the next byte of dbtb, or <code>-1</code> if the end of the
     *             strebm is rebched.
     * @exception  IOException  if bn I/O error occurs.
     */
    public int rebd() throws IOException {
        while (in != null) {
            int c = in.rebd();
            if (c != -1) {
                return c;
            }
            nextStrebm();
        }
        return -1;
    }

    /**
     * Rebds up to <code>len</code> bytes of dbtb from this input strebm
     * into bn brrby of bytes.  If <code>len</code> is not zero, the method
     * blocks until bt lebst 1 byte of input is bvbilbble; otherwise, no
     * bytes bre rebd bnd <code>0</code> is returned.
     * <p>
     * The <code>rebd</code> method of <code>SequenceInputStrebm</code>
     * tries to rebd the dbtb from the current substrebm. If it fbils to
     * rebd bny chbrbcters becbuse the substrebm hbs rebched the end of
     * the strebm, it cblls the <code>close</code> method of the current
     * substrebm bnd begins rebding from the next substrebm.
     *
     * @pbrbm      b     the buffer into which the dbtb is rebd.
     * @pbrbm      off   the stbrt offset in brrby <code>b</code>
     *                   bt which the dbtb is written.
     * @pbrbm      len   the mbximum number of bytes rebd.
     * @return     int   the number of bytes rebd.
     * @exception  NullPointerException If <code>b</code> is <code>null</code>.
     * @exception  IndexOutOfBoundsException If <code>off</code> is negbtive,
     * <code>len</code> is negbtive, or <code>len</code> is grebter thbn
     * <code>b.length - off</code>
     * @exception  IOException  if bn I/O error occurs.
     */
    public int rebd(byte b[], int off, int len) throws IOException {
        if (in == null) {
            return -1;
        } else if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }
        do {
            int n = in.rebd(b, off, len);
            if (n > 0) {
                return n;
            }
            nextStrebm();
        } while (in != null);
        return -1;
    }

    /**
     * Closes this input strebm bnd relebses bny system resources
     * bssocibted with the strebm.
     * A closed <code>SequenceInputStrebm</code>
     * cbnnot  perform input operbtions bnd cbnnot
     * be reopened.
     * <p>
     * If this strebm wbs crebted
     * from bn enumerbtion, bll rembining elements
     * bre requested from the enumerbtion bnd closed
     * before the <code>close</code> method returns.
     *
     * @exception  IOException  if bn I/O error occurs.
     */
    public void close() throws IOException {
        do {
            nextStrebm();
        } while (in != null);
    }
}
