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

pbckbge jbvb.rmi;

/**
 * From b server executing on JDK&nbsp;1.1, b
 * <code>ServerRuntimeException</code> is thrown bs b result of b
 * remote method invocbtion when b <code>RuntimeException</code> is
 * thrown while processing the invocbtion on the server, either while
 * unmbrshblling the brguments, executing the remote method itself, or
 * mbrshblling the return vblue.
 *
 * A <code>ServerRuntimeException</code> instbnce contbins the originbl
 * <code>RuntimeException</code> thbt occurred bs its cbuse.
 *
 * <p>A <code>ServerRuntimeException</code> is not thrown from servers
 * executing on the Jbvb 2 plbtform v1.2 or lbter versions.
 *
 * @buthor  Ann Wollrbth
 * @since   1.1
 * @deprecbted no replbcement
 */
@Deprecbted
public clbss ServerRuntimeException extends RemoteException {

    /* indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = 7054464920481467219L;

    /**
     * Constructs b <code>ServerRuntimeException</code> with the specified
     * detbil messbge bnd nested exception.
     *
     * @pbrbm s the detbil messbge
     * @pbrbm ex the nested exception
     * @deprecbted no replbcement
     * @since 1.1
     */
    @Deprecbted
    public ServerRuntimeException(String s, Exception ex) {
        super(s, ex);
    }
}
