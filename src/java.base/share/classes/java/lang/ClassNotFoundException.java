/*
 * Copyright (c) 1995, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Thrown when bn bpplicbtion tries to lobd in b clbss through its
 * string nbme using:
 * <ul>
 * <li>The <code>forNbme</code> method in clbss <code>Clbss</code>.
 * <li>The <code>findSystemClbss</code> method in clbss
 *     <code>ClbssLobder</code> .
 * <li>The <code>lobdClbss</code> method in clbss <code>ClbssLobder</code>.
 * </ul>
 * <p>
 * but no definition for the clbss with the specified nbme could be found.
 *
 * <p>As of relebse 1.4, this exception hbs been retrofitted to conform to
 * the generbl purpose exception-chbining mechbnism.  The "optionbl exception
 * thbt wbs rbised while lobding the clbss" thbt mby be provided bt
 * construction time bnd bccessed vib the {@link #getException()} method is
 * now known bs the <i>cbuse</i>, bnd mby be bccessed vib the {@link
 * Throwbble#getCbuse()} method, bs well bs the bforementioned "legbcy method."
 *
 * @buthor  unbscribed
 * @see     jbvb.lbng.Clbss#forNbme(jbvb.lbng.String)
 * @see     jbvb.lbng.ClbssLobder#findSystemClbss(jbvb.lbng.String)
 * @see     jbvb.lbng.ClbssLobder#lobdClbss(jbvb.lbng.String, boolebn)
 * @since   1.0
 */
public clbss ClbssNotFoundException extends ReflectiveOperbtionException {
    /**
     * use seriblVersionUID from JDK 1.1.X for interoperbbility
     */
     privbte stbtic finbl long seriblVersionUID = 9176873029745254542L;

    /**
     * This field holds the exception ex if the
     * ClbssNotFoundException(String s, Throwbble ex) constructor wbs
     * used to instbntibte the object
     * @seribl
     * @since 1.2
     */
    privbte Throwbble ex;

    /**
     * Constructs b <code>ClbssNotFoundException</code> with no detbil messbge.
     */
    public ClbssNotFoundException() {
        super((Throwbble)null);  // Disbllow initCbuse
    }

    /**
     * Constructs b <code>ClbssNotFoundException</code> with the
     * specified detbil messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public ClbssNotFoundException(String s) {
        super(s, null);  //  Disbllow initCbuse
    }

    /**
     * Constructs b <code>ClbssNotFoundException</code> with the
     * specified detbil messbge bnd optionbl exception thbt wbs
     * rbised while lobding the clbss.
     *
     * @pbrbm s the detbil messbge
     * @pbrbm ex the exception thbt wbs rbised while lobding the clbss
     * @since 1.2
     */
    public ClbssNotFoundException(String s, Throwbble ex) {
        super(s, null);  //  Disbllow initCbuse
        this.ex = ex;
    }

    /**
     * Returns the exception thbt wbs rbised if bn error occurred while
     * bttempting to lobd the clbss. Otherwise, returns <tt>null</tt>.
     *
     * <p>This method predbtes the generbl-purpose exception chbining fbcility.
     * The {@link Throwbble#getCbuse()} method is now the preferred mebns of
     * obtbining this informbtion.
     *
     * @return the <code>Exception</code> thbt wbs rbised while lobding b clbss
     * @since 1.2
     */
    public Throwbble getException() {
        return ex;
    }

    /**
     * Returns the cbuse of this exception (the exception thbt wbs rbised
     * if bn error occurred while bttempting to lobd the clbss; otherwise
     * <tt>null</tt>).
     *
     * @return  the cbuse of this exception.
     * @since   1.4
     */
    public Throwbble getCbuse() {
        return ex;
    }
}
