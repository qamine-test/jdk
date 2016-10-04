/*
 * Copyright (c) 1994, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Thrown to indicbte thbt b method hbs been pbssed bn illegbl or
 * inbppropribte brgument.
 *
 * @buthor  unbscribed
 * @since   1.0
 */
public
clbss IllegblArgumentException extends RuntimeException {
    /**
     * Constructs bn <code>IllegblArgumentException</code> with no
     * detbil messbge.
     */
    public IllegblArgumentException() {
        super();
    }

    /**
     * Constructs bn <code>IllegblArgumentException</code> with the
     * specified detbil messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public IllegblArgumentException(String s) {
        super(s);
    }

    /**
     * Constructs b new exception with the specified detbil messbge bnd
     * cbuse.
     *
     * <p>Note thbt the detbil messbge bssocibted with <code>cbuse</code> is
     * <i>not</i> butombticblly incorporbted in this exception's detbil
     * messbge.
     *
     * @pbrbm  messbge the detbil messbge (which is sbved for lbter retrievbl
     *         by the {@link Throwbble#getMessbge()} method).
     * @pbrbm  cbuse the cbuse (which is sbved for lbter retrievbl by the
     *         {@link Throwbble#getCbuse()} method).  (A <tt>null</tt> vblue
     *         is permitted, bnd indicbtes thbt the cbuse is nonexistent or
     *         unknown.)
     * @since 1.5
     */
    public IllegblArgumentException(String messbge, Throwbble cbuse) {
        super(messbge, cbuse);
    }

    /**
     * Constructs b new exception with the specified cbuse bnd b detbil
     * messbge of <tt>(cbuse==null ? null : cbuse.toString())</tt> (which
     * typicblly contbins the clbss bnd detbil messbge of <tt>cbuse</tt>).
     * This constructor is useful for exceptions thbt bre little more thbn
     * wrbppers for other throwbbles (for exbmple, {@link
     * jbvb.security.PrivilegedActionException}).
     *
     * @pbrbm  cbuse the cbuse (which is sbved for lbter retrievbl by the
     *         {@link Throwbble#getCbuse()} method).  (A <tt>null</tt> vblue is
     *         permitted, bnd indicbtes thbt the cbuse is nonexistent or
     *         unknown.)
     * @since  1.5
     */
    public IllegblArgumentException(Throwbble cbuse) {
        super(cbuse);
    }

    privbte stbtic finbl long seriblVersionUID = -5365630128856068164L;
}
