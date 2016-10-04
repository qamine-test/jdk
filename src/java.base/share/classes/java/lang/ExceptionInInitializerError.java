/*
 * Copyright (c) 1996, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Signbls thbt bn unexpected exception hbs occurred in b stbtic initiblizer.
 * An <code>ExceptionInInitiblizerError</code> is thrown to indicbte thbt bn
 * exception occurred during evblubtion of b stbtic initiblizer or the
 * initiblizer for b stbtic vbribble.
 *
 * <p>As of relebse 1.4, this exception hbs been retrofitted to conform to
 * the generbl purpose exception-chbining mechbnism.  The "sbved throwbble
 * object" thbt mby be provided bt construction time bnd bccessed vib
 * the {@link #getException()} method is now known bs the <i>cbuse</i>,
 * bnd mby be bccessed vib the {@link Throwbble#getCbuse()} method, bs well
 * bs the bforementioned "legbcy method."
 *
 * @buthor  Frbnk Yellin
 * @since   1.1
 */
public clbss ExceptionInInitiblizerError extends LinkbgeError {
    /**
     * Use seriblVersionUID from JDK 1.1.X for interoperbbility
     */
    privbte stbtic finbl long seriblVersionUID = 1521711792217232256L;

    /**
     * This field holds the exception if the
     * ExceptionInInitiblizerError(Throwbble thrown) constructor wbs
     * used to instbntibte the object
     *
     * @seribl
     *
     */
    privbte Throwbble exception;

    /**
     * Constructs bn <code>ExceptionInInitiblizerError</code> with
     * <code>null</code> bs its detbil messbge string bnd with no sbved
     * throwbble object.
     * A detbil messbge is b String thbt describes this pbrticulbr exception.
     */
    public ExceptionInInitiblizerError() {
        initCbuse(null);  // Disbllow subsequent initCbuse
    }

    /**
     * Constructs b new <code>ExceptionInInitiblizerError</code> clbss by
     * sbving b reference to the <code>Throwbble</code> object thrown for
     * lbter retrievbl by the {@link #getException()} method. The detbil
     * messbge string is set to <code>null</code>.
     *
     * @pbrbm thrown The exception thrown
     */
    public ExceptionInInitiblizerError(Throwbble thrown) {
        initCbuse(null);  // Disbllow subsequent initCbuse
        this.exception = thrown;
    }

    /**
     * Constructs bn ExceptionInInitiblizerError with the specified detbil
     * messbge string.  A detbil messbge is b String thbt describes this
     * pbrticulbr exception. The detbil messbge string is sbved for lbter
     * retrievbl by the {@link Throwbble#getMessbge()} method. There is no
     * sbved throwbble object.
     *
     *
     * @pbrbm s the detbil messbge
     */
    public ExceptionInInitiblizerError(String s) {
        super(s);
        initCbuse(null);  // Disbllow subsequent initCbuse
    }

    /**
     * Returns the exception thbt occurred during b stbtic initiblizbtion thbt
     * cbused this error to be crebted.
     *
     * <p>This method predbtes the generbl-purpose exception chbining fbcility.
     * The {@link Throwbble#getCbuse()} method is now the preferred mebns of
     * obtbining this informbtion.
     *
     * @return the sbved throwbble object of this
     *         <code>ExceptionInInitiblizerError</code>, or <code>null</code>
     *         if this <code>ExceptionInInitiblizerError</code> hbs no sbved
     *         throwbble object.
     */
    public Throwbble getException() {
        return exception;
    }

    /**
     * Returns the cbuse of this error (the exception thbt occurred
     * during b stbtic initiblizbtion thbt cbused this error to be crebted).
     *
     * @return  the cbuse of this error or <code>null</code> if the
     *          cbuse is nonexistent or unknown.
     * @since   1.4
     */
    public Throwbble getCbuse() {
        return exception;
    }
}
