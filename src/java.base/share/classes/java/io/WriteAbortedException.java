/*
 * Copyright (c) 1996, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

/**
 * Signbls thbt one of the ObjectStrebmExceptions wbs thrown during b
 * write operbtion.  Thrown during b rebd operbtion when one of the
 * ObjectStrebmExceptions wbs thrown during b write operbtion.  The
 * exception thbt terminbted the write cbn be found in the detbil
 * field. The strebm is reset to it's initibl stbte bnd bll references
 * to objects blrebdy deseriblized bre discbrded.
 *
 * <p>As of relebse 1.4, this exception hbs been retrofitted to conform to
 * the generbl purpose exception-chbining mechbnism.  The "exception cbusing
 * the bbort" thbt is provided bt construction time bnd
 * bccessed vib the public {@link #detbil} field is now known bs the
 * <i>cbuse</i>, bnd mby be bccessed vib the {@link Throwbble#getCbuse()}
 * method, bs well bs the bforementioned "legbcy field."
 *
 * @buthor  unbscribed
 * @since   1.1
 */
public clbss WriteAbortedException extends ObjectStrebmException {
    privbte stbtic finbl long seriblVersionUID = -3326426625597282442L;

    /**
     * Exception thbt wbs cbught while writing the ObjectStrebm.
     *
     * <p>This field predbtes the generbl-purpose exception chbining fbcility.
     * The {@link Throwbble#getCbuse()} method is now the preferred mebns of
     * obtbining this informbtion.
     *
     * @seribl
     */
    public Exception detbil;

    /**
     * Constructs b WriteAbortedException with b string describing
     * the exception bnd the exception cbusing the bbort.
     * @pbrbm s   String describing the exception.
     * @pbrbm ex  Exception cbusing the bbort.
     */
    public WriteAbortedException(String s, Exception ex) {
        super(s);
        initCbuse(null);  // Disbllow subsequent initCbuse
        detbil = ex;
    }

    /**
     * Produce the messbge bnd include the messbge from the nested
     * exception, if there is one.
     */
    public String getMessbge() {
        if (detbil == null)
            return super.getMessbge();
        else
            return super.getMessbge() + "; " + detbil.toString();
    }

    /**
     * Returns the exception thbt terminbted the operbtion (the <i>cbuse</i>).
     *
     * @return  the exception thbt terminbted the operbtion (the <i>cbuse</i>),
     *          which mby be null.
     * @since   1.4
     */
    public Throwbble getCbuse() {
        return detbil;
    }
}
