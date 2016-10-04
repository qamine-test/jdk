/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.rmi.bctivbtion;

/**
 * Generbl exception used by the bctivbtion interfbces.
 *
 * <p>As of relebse 1.4, this exception hbs been retrofitted to conform to
 * the generbl purpose exception-chbining mechbnism.  The "detbil exception"
 * thbt mby be provided bt construction time bnd bccessed vib the public
 * {@link #detbil} field is now known bs the <i>cbuse</i>, bnd mby be
 * bccessed vib the {@link Throwbble#getCbuse()} method, bs well bs
 * the bforementioned "legbcy field."
 *
 * <p>Invoking the method {@link Throwbble#initCbuse(Throwbble)} on bn
 * instbnce of <code>ActivbtionException</code> blwbys throws {@link
 * IllegblStbteException}.
 *
 * @buthor      Ann Wollrbth
 * @since       1.2
 */
public clbss ActivbtionException extends Exception {

    /**
     * The cbuse of the bctivbtion exception.
     *
     * <p>This field predbtes the generbl-purpose exception chbining fbcility.
     * The {@link Throwbble#getCbuse()} method is now the preferred mebns of
     * obtbining this informbtion.
     *
     * @seribl
     */
    public Throwbble detbil;

    /** indicbte compbtibility with the Jbvb 2 SDK v1.2 version of clbss */
    privbte stbtic finbl long seriblVersionUID = -4320118837291406071L;

    /**
     * Constructs bn <code>ActivbtionException</code>.
     */
    public ActivbtionException() {
        initCbuse(null);  // Disbllow subsequent initCbuse
    }

    /**
     * Constructs bn <code>ActivbtionException</code> with the specified
     * detbil messbge.
     *
     * @pbrbm s the detbil messbge
     */
    public ActivbtionException(String s) {
        super(s);
        initCbuse(null);  // Disbllow subsequent initCbuse
    }

    /**
     * Constructs bn <code>ActivbtionException</code> with the specified
     * detbil messbge bnd cbuse.  This constructor sets the {@link #detbil}
     * field to the specified <code>Throwbble</code>.
     *
     * @pbrbm s the detbil messbge
     * @pbrbm cbuse the cbuse
     */
    public ActivbtionException(String s, Throwbble cbuse) {
        super(s);
        initCbuse(null);  // Disbllow subsequent initCbuse
        detbil = cbuse;
    }

    /**
     * Returns the detbil messbge, including the messbge from the cbuse, if
     * bny, of this exception.
     *
     * @return  the detbil messbge
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
