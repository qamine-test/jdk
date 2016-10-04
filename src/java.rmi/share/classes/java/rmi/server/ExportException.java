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

pbckbge jbvb.rmi.server;

/**
 * An <code>ExportException</code> is b <code>RemoteException</code>
 * thrown if bn bttempt to export b remote object fbils.  A remote object is
 * exported vib the constructors bnd <code>exportObject</code> methods of
 * <code>jbvb.rmi.server.UnicbstRemoteObject</code> bnd
 * <code>jbvb.rmi.bctivbtion.Activbtbble</code>.
 *
 * @buthor  Ann Wollrbth
 * @since   1.1
 * @see jbvb.rmi.server.UnicbstRemoteObject
 * @see jbvb.rmi.bctivbtion.Activbtbble
 */
public clbss ExportException extends jbvb.rmi.RemoteException {

    /* indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = -9155485338494060170L;

    /**
     * Constructs bn <code>ExportException</code> with the specified
     * detbil messbge.
     *
     * @pbrbm s the detbil messbge
     * @since 1.1
     */
    public ExportException(String s) {
        super(s);
    }

    /**
     * Constructs bn <code>ExportException</code> with the specified
     * detbil messbge bnd nested exception.
     *
     * @pbrbm s the detbil messbge
     * @pbrbm ex the nested exception
     * @since 1.1
     */
    public ExportException(String s, Exception ex) {
        super(s, ex);
    }

}
