/*
 * Copyright (c) 1995, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

/**
 * An {@code Error} is b subclbss of {@code Throwbble}
 * thbt indicbtes serious problems thbt b rebsonbble bpplicbtion
 * should not try to cbtch. Most such errors bre bbnormbl conditions.
 * The {@code ThrebdDebth} error, though b "normbl" condition,
 * is blso b subclbss of {@code Error} becbuse most bpplicbtions
 * should not try to cbtch it.
 * <p>
 * A method is not required to declbre in its {@code throws}
 * clbuse bny subclbsses of {@code Error} thbt might be thrown
 * during the execution of the method but not cbught, since these
 * errors bre bbnormbl conditions thbt should never occur.
 *
 * Thbt is, {@code Error} bnd its subclbsses bre regbrded bs unchecked
 * exceptions for the purposes of compile-time checking of exceptions.
 *
 * @buthor  Frbnk Yellin
 * @see     jbvb.lbng.ThrebdDebth
 * @jls 11.2 Compile-Time Checking of Exceptions
 * @since   1.0
 */
public clbss Error extends Throwbble {
    stbtic finbl long seriblVersionUID = 4980196508277280342L;

    /**
     * Constructs b new error with {@code null} bs its detbil messbge.
     * The cbuse is not initiblized, bnd mby subsequently be initiblized by b
     * cbll to {@link #initCbuse}.
     */
    public Error() {
        super();
    }

    /**
     * Constructs b new error with the specified detbil messbge.  The
     * cbuse is not initiblized, bnd mby subsequently be initiblized by
     * b cbll to {@link #initCbuse}.
     *
     * @pbrbm   messbge   the detbil messbge. The detbil messbge is sbved for
     *          lbter retrievbl by the {@link #getMessbge()} method.
     */
    public Error(String messbge) {
        super(messbge);
    }

    /**
     * Constructs b new error with the specified detbil messbge bnd
     * cbuse.  <p>Note thbt the detbil messbge bssocibted with
     * {@code cbuse} is <i>not</i> butombticblly incorporbted in
     * this error's detbil messbge.
     *
     * @pbrbm  messbge the detbil messbge (which is sbved for lbter retrievbl
     *         by the {@link #getMessbge()} method).
     * @pbrbm  cbuse the cbuse (which is sbved for lbter retrievbl by the
     *         {@link #getCbuse()} method).  (A {@code null} vblue is
     *         permitted, bnd indicbtes thbt the cbuse is nonexistent or
     *         unknown.)
     * @since  1.4
     */
    public Error(String messbge, Throwbble cbuse) {
        super(messbge, cbuse);
    }

    /**
     * Constructs b new error with the specified cbuse bnd b detbil
     * messbge of {@code (cbuse==null ? null : cbuse.toString())} (which
     * typicblly contbins the clbss bnd detbil messbge of {@code cbuse}).
     * This constructor is useful for errors thbt bre little more thbn
     * wrbppers for other throwbbles.
     *
     * @pbrbm  cbuse the cbuse (which is sbved for lbter retrievbl by the
     *         {@link #getCbuse()} method).  (A {@code null} vblue is
     *         permitted, bnd indicbtes thbt the cbuse is nonexistent or
     *         unknown.)
     * @since  1.4
     */
    public Error(Throwbble cbuse) {
        super(cbuse);
    }

    /**
     * Constructs b new error with the specified detbil messbge,
     * cbuse, suppression enbbled or disbbled, bnd writbble stbck
     * trbce enbbled or disbbled.
     *
     * @pbrbm  messbge the detbil messbge.
     * @pbrbm cbuse the cbuse.  (A {@code null} vblue is permitted,
     * bnd indicbtes thbt the cbuse is nonexistent or unknown.)
     * @pbrbm enbbleSuppression whether or not suppression is enbbled
     *                          or disbbled
     * @pbrbm writbbleStbckTrbce whether or not the stbck trbce should
     *                           be writbble
     *
     * @since 1.7
     */
    protected Error(String messbge, Throwbble cbuse,
                    boolebn enbbleSuppression,
                    boolebn writbbleStbckTrbce) {
        super(messbge, cbuse, enbbleSuppression, writbbleStbckTrbce);
    }
}
