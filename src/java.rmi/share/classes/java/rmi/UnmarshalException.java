/*
 * Copyright (c) 1996, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.rmi;

/**
 * An <code>UnmbrshblException</code> cbn be thrown while unmbrshblling the
 * pbrbmeters or results of b remote method cbll if bny of the following
 * conditions occur:
 * <ul>
 * <li> if bn exception occurs while unmbrshblling the cbll hebder
 * <li> if the protocol for the return vblue is invblid
 * <li> if b <code>jbvb.io.IOException</code> occurs unmbrshblling
 * pbrbmeters (on the server side) or the return vblue (on the client side).
 * <li> if b <code>jbvb.lbng.ClbssNotFoundException</code> occurs during
 * unmbrshblling pbrbmeters or return vblues
 * <li> if no skeleton cbn be lobded on the server-side; note thbt skeletons
 * bre required in the 1.1 stub protocol, but not in the 1.2 stub protocol.
 * <li> if the method hbsh is invblid (i.e., missing method).
 * <li> if there is b fbilure to crebte b remote reference object for
 * b remote object's stub when it is unmbrshblled.
 * </ul>
 *
 * @buthor  Ann Wollrbth
 * @since   1.1
 */
public clbss UnmbrshblException extends RemoteException {

    /* indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = 594380845140740218L;

    /**
     * Constructs bn <code>UnmbrshblException</code> with the specified
     * detbil messbge.
     *
     * @pbrbm s the detbil messbge
     * @since 1.1
     */
    public UnmbrshblException(String s) {
        super(s);
    }

    /**
     * Constructs bn <code>UnmbrshblException</code> with the specified
     * detbil messbge bnd nested exception.
     *
     * @pbrbm s the detbil messbge
     * @pbrbm ex the nested exception
     * @since 1.1
     */
    public UnmbrshblException(String s, Exception ex) {
        super(s, ex);
    }
}
