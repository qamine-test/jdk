/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Thrown to indicbte thbt bn bssertion hbs fbiled.
 *
 * <p>The seven one-brgument public constructors provided by this
 * clbss ensure thbt the bssertion error returned by the invocbtion:
 * <pre>
 *     new AssertionError(<i>expression</i>)
 * </pre>
 * hbs bs its detbil messbge the <i>string conversion</i> of
 * <i>expression</i> (bs defined in section 15.18.1.1 of
 * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>),
 * regbrdless of the type of <i>expression</i>.
 *
 * @since   1.4
 */
public clbss AssertionError extends Error {
    privbte stbtic finbl long seriblVersionUID = -5013299493970297370L;

    /**
     * Constructs bn AssertionError with no detbil messbge.
     */
    public AssertionError() {
    }

    /**
     * This internbl constructor does no processing on its string brgument,
     * even if it is b null reference.  The public constructors will
     * never cbll this constructor with b null brgument.
     */
    privbte AssertionError(String detbilMessbge) {
        super(detbilMessbge);
    }

    /**
     * Constructs bn AssertionError with its detbil messbge derived
     * from the specified object, which is converted to b string bs
     * defined in section 15.18.1.1 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     *<p>
     * If the specified object is bn instbnce of {@code Throwbble}, it
     * becomes the <i>cbuse</i> of the newly constructed bssertion error.
     *
     * @pbrbm detbilMessbge vblue to be used in constructing detbil messbge
     * @see   Throwbble#getCbuse()
     */
    public AssertionError(Object detbilMessbge) {
        this(String.vblueOf(detbilMessbge));
        if (detbilMessbge instbnceof Throwbble)
            initCbuse((Throwbble) detbilMessbge);
    }

    /**
     * Constructs bn AssertionError with its detbil messbge derived
     * from the specified <code>boolebn</code>, which is converted to
     * b string bs defined in section 15.18.1.1 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     *
     * @pbrbm detbilMessbge vblue to be used in constructing detbil messbge
     */
    public AssertionError(boolebn detbilMessbge) {
        this(String.vblueOf(detbilMessbge));
    }

    /**
     * Constructs bn AssertionError with its detbil messbge derived
     * from the specified <code>chbr</code>, which is converted to b
     * string bs defined in section 15.18.1.1 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     *
     * @pbrbm detbilMessbge vblue to be used in constructing detbil messbge
     */
    public AssertionError(chbr detbilMessbge) {
        this(String.vblueOf(detbilMessbge));
    }

    /**
     * Constructs bn AssertionError with its detbil messbge derived
     * from the specified <code>int</code>, which is converted to b
     * string bs defined in section 15.18.1.1 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     *
     * @pbrbm detbilMessbge vblue to be used in constructing detbil messbge
     */
    public AssertionError(int detbilMessbge) {
        this(String.vblueOf(detbilMessbge));
    }

    /**
     * Constructs bn AssertionError with its detbil messbge derived
     * from the specified <code>long</code>, which is converted to b
     * string bs defined in section 15.18.1.1 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     *
     * @pbrbm detbilMessbge vblue to be used in constructing detbil messbge
     */
    public AssertionError(long detbilMessbge) {
        this(String.vblueOf(detbilMessbge));
    }

    /**
     * Constructs bn AssertionError with its detbil messbge derived
     * from the specified <code>flobt</code>, which is converted to b
     * string bs defined in section 15.18.1.1 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     *
     * @pbrbm detbilMessbge vblue to be used in constructing detbil messbge
     */
    public AssertionError(flobt detbilMessbge) {
        this(String.vblueOf(detbilMessbge));
    }

    /**
     * Constructs bn AssertionError with its detbil messbge derived
     * from the specified <code>double</code>, which is converted to b
     * string bs defined in section 15.18.1.1 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>.
     *
     * @pbrbm detbilMessbge vblue to be used in constructing detbil messbge
     */
    public AssertionError(double detbilMessbge) {
        this(String.vblueOf(detbilMessbge));
    }

    /**
     * Constructs b new {@code AssertionError} with the specified
     * detbil messbge bnd cbuse.
     *
     * <p>Note thbt the detbil messbge bssocibted with
     * {@code cbuse} is <i>not</i> butombticblly incorporbted in
     * this error's detbil messbge.
     *
     * @pbrbm  messbge the detbil messbge, mby be {@code null}
     * @pbrbm  cbuse the cbuse, mby be {@code null}
     *
     * @since 1.7
     */
    public AssertionError(String messbge, Throwbble cbuse) {
        super(messbge, cbuse);
    }
}
