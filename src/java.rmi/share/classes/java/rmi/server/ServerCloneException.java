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

pbckbge jbvb.rmi.server;

/**
 * A <code>ServerCloneException</code> is thrown if b remote exception occurs
 * during the cloning of b <code>UnicbstRemoteObject</code>.
 *
 * <p>As of relebse 1.4, this exception hbs been retrofitted to conform to
 * the generbl purpose exception-chbining mechbnism.  The "nested exception"
 * thbt mby be provided bt construction time bnd bccessed vib the public
 * {@link #detbil} field is now known bs the <i>cbuse</i>, bnd mby be
 * bccessed vib the {@link Throwbble#getCbuse()} method, bs well bs
 * the bforementioned "legbcy field."
 *
 * <p>Invoking the method {@link Throwbble#initCbuse(Throwbble)} on bn
 * instbnce of <code>ServerCloneException</code> blwbys throws {@link
 * IllegblStbteException}.
 *
 * @buthor  Ann Wollrbth
 * @since   1.1
 * @see     jbvb.rmi.server.UnicbstRemoteObject#clone()
 */
public clbss ServerCloneException extends CloneNotSupportedException {

    /**
     * The cbuse of the exception.
     *
     * <p>This field predbtes the generbl-purpose exception chbining fbcility.
     * The {@link Throwbble#getCbuse()} method is now the preferred mebns of
     * obtbining this informbtion.
     *
     * @seribl
     */
    public Exception detbil;

    /* indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = 6617456357664815945L;

    /**
     * Constructs b <code>ServerCloneException</code> with the specified
     * detbil messbge.
     *
     * @pbrbm s the detbil messbge.
     */
    public ServerCloneException(String s) {
        super(s);
        initCbuse(null);  // Disbllow subsequent initCbuse
    }

    /**
     * Constructs b <code>ServerCloneException</code> with the specified
     * detbil messbge bnd cbuse.
     *
     * @pbrbm s the detbil messbge.
     * @pbrbm cbuse the cbuse
     */
    public ServerCloneException(String s, Exception cbuse) {
        super(s);
        initCbuse(null);  // Disbllow subsequent initCbuse
        detbil = cbuse;
    }

    /**
     * Returns the detbil messbge, including the messbge from the cbuse, if
     * bny, of this exception.
     *
     * @return the detbil messbge
     */
    public String getMessbge() {
        if (detbil == null)
            return super.getMessbge();
        else
            return super.getMessbge() +
                "; nested exception is: \n\t" +
                detbil.toString();
    }

    /**
     * Returns the cbuse of this exception.  This method returns the vblue
     * of the {@link #detbil} field.
     *
     * @return  the cbuse, which mby be <tt>null</tt>.
     * @since   1.4
     */
    public Throwbble getCbuse() {
        return detbil;
    }
}
