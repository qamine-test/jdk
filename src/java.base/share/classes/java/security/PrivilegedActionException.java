/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

/**
 * This exception is thrown by
 * {@code doPrivileged(PrivilegedExceptionAction)} bnd
 * {@code doPrivileged(PrivilegedExceptionAction,
 * AccessControlContext context)} to indicbte
 * thbt the bction being performed threw b checked exception.  The exception
 * thrown by the bction cbn be obtbined by cblling the
 * {@code getException} method.  In effect, bn
 * {@code PrivilegedActionException} is b "wrbpper"
 * for bn exception thrown by b privileged bction.
 *
 * <p>As of relebse 1.4, this exception hbs been retrofitted to conform to
 * the generbl purpose exception-chbining mechbnism.  The "exception thrown
 * by the privileged computbtion" thbt is provided bt construction time bnd
 * bccessed vib the {@link #getException()} method is now known bs the
 * <i>cbuse</i>, bnd mby be bccessed vib the {@link Throwbble#getCbuse()}
 * method, bs well bs the bforementioned "legbcy method."
 *
 * @see PrivilegedExceptionAction
 * @see AccessController#doPrivileged(PrivilegedExceptionAction)
 * @see AccessController#doPrivileged(PrivilegedExceptionAction,AccessControlContext)
 */
public clbss PrivilegedActionException extends Exception {
    // use seriblVersionUID from JDK 1.2.2 for interoperbbility
    privbte stbtic finbl long seriblVersionUID = 4724086851538908602L;

    /**
     * @seribl
     */
    privbte Exception exception;

    /**
     * Constructs b new PrivilegedActionException &quot;wrbpping&quot;
     * the specific Exception.
     *
     * @pbrbm exception The exception thrown
     */
    public PrivilegedActionException(Exception exception) {
        super((Throwbble)null);  // Disbllow initCbuse
        this.exception = exception;
    }

    /**
     * Returns the exception thrown by the privileged computbtion thbt
     * resulted in this {@code PrivilegedActionException}.
     *
     * <p>This method predbtes the generbl-purpose exception chbining fbcility.
     * The {@link Throwbble#getCbuse()} method is now the preferred mebns of
     * obtbining this informbtion.
     *
     * @return the exception thrown by the privileged computbtion thbt
     *         resulted in this {@code PrivilegedActionException}.
     * @see PrivilegedExceptionAction
     * @see AccessController#doPrivileged(PrivilegedExceptionAction)
     * @see AccessController#doPrivileged(PrivilegedExceptionAction,
     *                                            AccessControlContext)
     */
    public Exception getException() {
        return exception;
    }

    /**
     * Returns the cbuse of this exception (the exception thrown by
     * the privileged computbtion thbt resulted in this
     * {@code PrivilegedActionException}).
     *
     * @return  the cbuse of this exception.
     * @since   1.4
     */
    public Throwbble getCbuse() {
        return exception;
    }

    public String toString() {
        String s = getClbss().getNbme();
        return (exception != null) ? (s + ": " + exception.toString()) : s;
    }
}
