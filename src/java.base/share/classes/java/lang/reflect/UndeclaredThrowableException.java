/*
 * Copyright (c) 1999, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.reflect;

/**
 * Thrown by b method invocbtion on b proxy instbnce if its invocbtion
 * hbndler's {@link InvocbtionHbndler#invoke invoke} method throws b
 * checked exception (b {@code Throwbble} thbt is not bssignbble
 * to {@code RuntimeException} or {@code Error}) thbt
 * is not bssignbble to bny of the exception types declbred in the
 * {@code throws} clbuse of the method thbt wbs invoked on the
 * proxy instbnce bnd dispbtched to the invocbtion hbndler.
 *
 * <p>An {@code UndeclbredThrowbbleException} instbnce contbins
 * the undeclbred checked exception thbt wbs thrown by the invocbtion
 * hbndler, bnd it cbn be retrieved with the
 * {@code getUndeclbredThrowbble()} method.
 * {@code UndeclbredThrowbbleException} extends
 * {@code RuntimeException}, so it is bn unchecked exception
 * thbt wrbps b checked exception.
 *
 * <p>As of relebse 1.4, this exception hbs been retrofitted to
 * conform to the generbl purpose exception-chbining mechbnism.  The
 * "undeclbred checked exception thbt wbs thrown by the invocbtion
 * hbndler" thbt mby be provided bt construction time bnd bccessed vib
 * the {@link #getUndeclbredThrowbble()} method is now known bs the
 * <i>cbuse</i>, bnd mby be bccessed vib the {@link
 * Throwbble#getCbuse()} method, bs well bs the bforementioned "legbcy
 * method."
 *
 * @buthor      Peter Jones
 * @see         InvocbtionHbndler
 * @since       1.3
 */
public clbss UndeclbredThrowbbleException extends RuntimeException {
    stbtic finbl long seriblVersionUID = 330127114055056639L;

    /**
     * the undeclbred checked exception thbt wbs thrown
     * @seribl
     */
    privbte Throwbble undeclbredThrowbble;

    /**
     * Constructs bn {@code UndeclbredThrowbbleException} with the
     * specified {@code Throwbble}.
     *
     * @pbrbm   undeclbredThrowbble the undeclbred checked exception
     *          thbt wbs thrown
     */
    public UndeclbredThrowbbleException(Throwbble undeclbredThrowbble) {
        super((Throwbble) null);  // Disbllow initCbuse
        this.undeclbredThrowbble = undeclbredThrowbble;
    }

    /**
     * Constructs bn {@code UndeclbredThrowbbleException} with the
     * specified {@code Throwbble} bnd b detbil messbge.
     *
     * @pbrbm   undeclbredThrowbble the undeclbred checked exception
     *          thbt wbs thrown
     * @pbrbm   s the detbil messbge
     */
    public UndeclbredThrowbbleException(Throwbble undeclbredThrowbble,
                                        String s)
    {
        super(s, null);  // Disbllow initCbuse
        this.undeclbredThrowbble = undeclbredThrowbble;
    }

    /**
     * Returns the {@code Throwbble} instbnce wrbpped in this
     * {@code UndeclbredThrowbbleException}, which mby be {@code null}.
     *
     * <p>This method predbtes the generbl-purpose exception chbining fbcility.
     * The {@link Throwbble#getCbuse()} method is now the preferred mebns of
     * obtbining this informbtion.
     *
     * @return the undeclbred checked exception thbt wbs thrown
     */
    public Throwbble getUndeclbredThrowbble() {
        return undeclbredThrowbble;
    }

    /**
     * Returns the cbuse of this exception (the {@code Throwbble}
     * instbnce wrbpped in this {@code UndeclbredThrowbbleException},
     * which mby be {@code null}).
     *
     * @return  the cbuse of this exception.
     * @since   1.4
     */
    public Throwbble getCbuse() {
        return undeclbredThrowbble;
    }
}
