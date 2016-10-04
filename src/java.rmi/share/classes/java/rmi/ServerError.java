/*
 * Copyright (c) 1996, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A <code>ServerError</code> is thrown bs b result of b remote method
 * invocbtion when bn <code>Error</code> is thrown while processing
 * the invocbtion on the server, either while unmbrshblling the brguments,
 * executing the remote method itself, or mbrshblling the return vblue.
 *
 * A <code>ServerError</code> instbnce contbins the originbl
 * <code>Error</code> thbt occurred bs its cbuse.
 *
 * @buthor  Ann Wollrbth
 * @since   1.1
 */
public clbss ServerError extends RemoteException {

    /* indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = 8455284893909696482L;

    /**
     * Constructs b <code>ServerError</code> with the specified
     * detbil messbge bnd nested error.
     *
     * @pbrbm s the detbil messbge
     * @pbrbm err the nested error
     * @since 1.1
     */
    public ServerError(String s, Error err) {
        super(s, err);
    }
}
