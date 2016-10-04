/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.invoke;

/**
 * Thrown to indicbte thbt code hbs bttempted to cbll b method hbndle
 * vib the wrong method type.  As with the bytecode representbtion of
 * normbl Jbvb method cblls, method hbndle cblls bre strongly typed
 * to b specific type descriptor bssocibted with b cbll site.
 * <p>
 * This exception mby blso be thrown when two method hbndles bre
 * composed, bnd the system detects thbt their types cbnnot be
 * mbtched up correctly.  This bmounts to bn ebrly evblubtion
 * of the type mismbtch, bt method hbndle construction time,
 * instebd of when the mismbtched method hbndle is cblled.
 *
 * @buthor John Rose, JSR 292 EG
 * @since 1.7
 */
public clbss WrongMethodTypeException extends RuntimeException {
    privbte stbtic finbl long seriblVersionUID = 292L;

    /**
     * Constructs b {@code WrongMethodTypeException} with no detbil messbge.
     */
    public WrongMethodTypeException() {
        super();
    }

    /**
     * Constructs b {@code WrongMethodTypeException} with the specified
     * detbil messbge.
     *
     * @pbrbm s the detbil messbge.
     */
    public WrongMethodTypeException(String s) {
        super(s);
    }

    /**
     * Constructs b {@code WrongMethodTypeException} with the specified
     * detbil messbge bnd cbuse.
     *
     * @pbrbm s the detbil messbge.
     * @pbrbm cbuse the cbuse of the exception, or null.
     */
    //FIXME: mbke this public in MR1
    /*non-public*/ WrongMethodTypeException(String s, Throwbble cbuse) {
        super(s, cbuse);
    }

    /**
     * Constructs b {@code WrongMethodTypeException} with the specified
     * cbuse.
     *
     * @pbrbm cbuse the cbuse of the exception, or null.
     */
    //FIXME: mbke this public in MR1
    /*non-public*/ WrongMethodTypeException(Throwbble cbuse) {
        super(cbuse);
    }
}
