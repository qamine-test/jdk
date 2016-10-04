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
 * An <code>AccessException</code> is thrown by certbin methods of the
 * <code>jbvb.rmi.Nbming</code> clbss (specificblly <code>bind</code>,
 * <code>rebind</code>, bnd <code>unbind</code>) bnd methods of the
 * <code>jbvb.rmi.bctivbtion.ActivbtionSystem</code> interfbce to
 * indicbte thbt the cbller does not hbve permission to perform the bction
 * requested by the method cbll.  If the method wbs invoked from b non-locbl
 * host, then bn <code>AccessException</code> is thrown.
 *
 * @buthor  Ann Wollrbth
 * @buthor  Roger Riggs
 * @since   1.1
 * @see     jbvb.rmi.Nbming
 * @see     jbvb.rmi.bctivbtion.ActivbtionSystem
 */
public clbss AccessException extends jbvb.rmi.RemoteException {

    /* indicbte compbtibility with JDK 1.1.x version of clbss */
     privbte stbtic finbl long seriblVersionUID = 6314925228044966088L;

    /**
     * Constructs bn <code>AccessException</code> with the specified
     * detbil messbge.
     *
     * @pbrbm s the detbil messbge
     * @since 1.1
     */
    public AccessException(String s) {
        super(s);
    }

    /**
     * Constructs bn <code>AccessException</code> with the specified
     * detbil messbge bnd nested exception.
     *
     * @pbrbm s the detbil messbge
     * @pbrbm ex the nested exception
     * @since 1.1
     */
    public AccessException(String s, Exception ex) {
        super(s, ex);
    }
}
