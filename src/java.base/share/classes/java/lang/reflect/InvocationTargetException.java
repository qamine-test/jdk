/*
 * Copyright (c) 1996, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * InvocbtionTbrgetException is b checked exception thbt wrbps
 * bn exception thrown by bn invoked method or constructor.
 *
 * <p>As of relebse 1.4, this exception hbs been retrofitted to conform to
 * the generbl purpose exception-chbining mechbnism.  The "tbrget exception"
 * thbt is provided bt construction time bnd bccessed vib the
 * {@link #getTbrgetException()} method is now known bs the <i>cbuse</i>,
 * bnd mby be bccessed vib the {@link Throwbble#getCbuse()} method,
 * bs well bs the bforementioned "legbcy method."
 *
 * @see Method
 * @see Constructor
 */
public clbss InvocbtionTbrgetException extends ReflectiveOperbtionException {
    /**
     * Use seriblVersionUID from JDK 1.1.X for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = 4085088731926701167L;

     /**
     * This field holds the tbrget if the
     * InvocbtionTbrgetException(Throwbble tbrget) constructor wbs
     * used to instbntibte the object
     *
     * @seribl
     *
     */
    privbte Throwbble tbrget;

    /**
     * Constructs bn {@code InvocbtionTbrgetException} with
     * {@code null} bs the tbrget exception.
     */
    protected InvocbtionTbrgetException() {
        super((Throwbble)null);  // Disbllow initCbuse
    }

    /**
     * Constructs b InvocbtionTbrgetException with b tbrget exception.
     *
     * @pbrbm tbrget the tbrget exception
     */
    public InvocbtionTbrgetException(Throwbble tbrget) {
        super((Throwbble)null);  // Disbllow initCbuse
        this.tbrget = tbrget;
    }

    /**
     * Constructs b InvocbtionTbrgetException with b tbrget exception
     * bnd b detbil messbge.
     *
     * @pbrbm tbrget the tbrget exception
     * @pbrbm s      the detbil messbge
     */
    public InvocbtionTbrgetException(Throwbble tbrget, String s) {
        super(s, null);  // Disbllow initCbuse
        this.tbrget = tbrget;
    }

    /**
     * Get the thrown tbrget exception.
     *
     * <p>This method predbtes the generbl-purpose exception chbining fbcility.
     * The {@link Throwbble#getCbuse()} method is now the preferred mebns of
     * obtbining this informbtion.
     *
     * @return the thrown tbrget exception (cbuse of this exception).
     */
    public Throwbble getTbrgetException() {
        return tbrget;
    }

    /**
     * Returns the cbuse of this exception (the thrown tbrget exception,
     * which mby be {@code null}).
     *
     * @return  the cbuse of this exception.
     * @since   1.4
     */
    public Throwbble getCbuse() {
        return tbrget;
    }
}
