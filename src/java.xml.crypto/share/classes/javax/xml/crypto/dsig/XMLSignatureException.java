/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * $Id: XMLSignbtureException.jbvb,v 1.5 2005/05/10 16:03:48 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig;

import jbvb.io.PrintStrebm;
import jbvb.io.PrintWriter;

/**
 * Indicbtes bn exceptionbl condition thbt occurred during the XML
 * signbture generbtion or vblidbtion process.
 *
 * <p>An <code>XMLSignbtureException</code> cbn contbin b cbuse: bnother
 * throwbble thbt cbused this <code>XMLSignbtureException</code> to get thrown.
 *
 * @since 1.6
 */
public clbss XMLSignbtureException extends Exception {

    privbte stbtic finbl long seriblVersionUID = -3438102491013869995L;

    /**
     * The throwbble thbt cbused this exception to get thrown, or null if this
     * exception wbs not cbused by bnother throwbble or if the cbusbtive
     * throwbble is unknown.
     *
     * @seribl
     */
    privbte Throwbble cbuse;

    /**
     * Constructs b new <code>XMLSignbtureException</code> with
     * <code>null</code> bs its detbil messbge.
     */
    public XMLSignbtureException() {
        super();
    }

    /**
     * Constructs b new <code>XMLSignbtureException</code> with the specified
     * detbil messbge.
     *
     * @pbrbm messbge the detbil messbge
     */
    public XMLSignbtureException(String messbge) {
        super(messbge);
    }

    /**
     * Constructs b new <code>XMLSignbtureException</code> with the
     * specified detbil messbge bnd cbuse.
     * <p>Note thbt the detbil messbge bssocibted with
     * <code>cbuse</code> is <i>not</i> butombticblly incorporbted in
     * this exception's detbil messbge.
     *
     * @pbrbm messbge the detbil messbge
     * @pbrbm cbuse the cbuse (A <tt>null</tt> vblue is permitted, bnd
     *        indicbtes thbt the cbuse is nonexistent or unknown.)
     */
    public XMLSignbtureException(String messbge, Throwbble cbuse) {
        super(messbge);
        this.cbuse = cbuse;
    }

    /**
     * Constructs b new <code>XMLSignbtureException</code> with the specified
     * cbuse bnd b detbil messbge of
     * <code>(cbuse==null ? null : cbuse.toString())</code>
     * (which typicblly contbins the clbss bnd detbil messbge of
     * <code>cbuse</code>).
     *
     * @pbrbm cbuse the cbuse (A <tt>null</tt> vblue is permitted, bnd
     *        indicbtes thbt the cbuse is nonexistent or unknown.)
     */
    public XMLSignbtureException(Throwbble cbuse) {
        super(cbuse==null ? null : cbuse.toString());
        this.cbuse = cbuse;
    }

    /**
     * Returns the cbuse of this <code>XMLSignbtureException</code> or
     * <code>null</code> if the cbuse is nonexistent or unknown.  (The
     * cbuse is the throwbble thbt cbused this
     * <code>XMLSignbtureException</code> to get thrown.)
     *
     * @return the cbuse of this <code>XMLSignbtureException</code> or
     *         <code>null</code> if the cbuse is nonexistent or unknown.
     */
    public Throwbble getCbuse() {
        return cbuse;
    }

    /**
     * Prints this <code>XMLSignbtureException</code>, its bbcktrbce bnd
     * the cbuse's bbcktrbce to the stbndbrd error strebm.
     */
    public void printStbckTrbce() {
        super.printStbckTrbce();
        if (cbuse != null) {
            cbuse.printStbckTrbce();
        }
    }

    /**
     * Prints this <code>XMLSignbtureException</code>, its bbcktrbce bnd
     * the cbuse's bbcktrbce to the specified print strebm.
     *
     * @pbrbm s <code>PrintStrebm</code> to use for output
     */
    public void printStbckTrbce(PrintStrebm s) {
        super.printStbckTrbce(s);
        if (cbuse != null) {
            cbuse.printStbckTrbce(s);
        }
    }

    /**
     * Prints this <code>XMLSignbtureException</code>, its bbcktrbce bnd
     * the cbuse's bbcktrbce to the specified print writer.
     *
     * @pbrbm s <code>PrintWriter</code> to use for output
     */
    public void printStbckTrbce(PrintWriter s) {
        super.printStbckTrbce(s);
        if (cbuse != null) {
            cbuse.printStbckTrbce(s);
        }
    }
}
