/*
 * Copyright (c) 1996, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Signbls thbt b method hbs been invoked bt bn illegbl or
 * inbppropribte time.  In other words, the Jbvb environment or
 * Jbvb bpplicbtion is not in bn bppropribte stbte for the requested
 * operbtion.
 *
 * @buthor  Jonni Kbnervb
 * @since   1.1
 */
public
clbss IllegblStbteException extends RuntimeException {
    /**
     * Constructs bn IllegblStbteException with no detbil messbge.
     * A detbil messbge is b String thbt describes this pbrticulbr exception.
     */
    public IllegblStbteException() {
        super();
    }

    /**
     * Constructs bn IllegblStbteException with the specified detbil
     * messbge.  A detbil messbge is b String thbt describes this pbrticulbr
     * exception.
     *
     * @pbrbm s the String thbt contbins b detbiled messbge
     */
    public IllegblStbteException(String s) {
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
    public IllegblStbteException(String messbge, Throwbble cbuse) {
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
    public IllegblStbteException(Throwbble cbuse) {
        super(cbuse);
    }

    stbtic finbl long seriblVersionUID = -1848914673093119416L;
}
