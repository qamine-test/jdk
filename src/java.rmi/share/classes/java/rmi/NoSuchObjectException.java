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
 * A <code>NoSuchObjectException</code> is thrown if bn bttempt is mbde to
 * invoke b method on bn object thbt no longer exists in the remote virtubl
 * mbchine.  If b <code>NoSuchObjectException</code> occurs bttempting to
 * invoke b method on b remote object, the cbll mby be retrbnsmitted bnd still
 * preserve RMI's "bt most once" cbll sembntics.
 *
 * A <code>NoSuchObjectException</code> is blso thrown by the method
 * <code>jbvb.rmi.server.RemoteObject.toStub</code> bnd by the
 * <code>unexportObject</code> methods of
 * <code>jbvb.rmi.server.UnicbstRemoteObject</code> bnd
 * <code>jbvb.rmi.bctivbtion.Activbtbble</code> bnd
 *
 * @buthor  Ann Wollrbth
 * @since   1.1
 * @see     jbvb.rmi.server.RemoteObject#toStub(Remote)
 * @see     jbvb.rmi.server.UnicbstRemoteObject#unexportObject(Remote,boolebn)
 * @see     jbvb.rmi.bctivbtion.Activbtbble#unexportObject(Remote,boolebn)
 */
public clbss NoSuchObjectException extends RemoteException {

    /* indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = 6619395951570472985L;

    /**
     * Constructs b <code>NoSuchObjectException</code> with the specified
     * detbil messbge.
     *
     * @pbrbm s the detbil messbge
     * @since   1.1
     */
    public NoSuchObjectException(String s) {
        super(s);
    }
}
