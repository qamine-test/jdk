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
 * An <code>RMIFbilureHbndler</code> cbn be registered vib the
 * <code>RMISocketFbctory.setFbilureHbndler</code> cbll. The
 * <code>fbilure</code> method of the hbndler is invoked when the RMI
 * runtime is unbble to crebte b <code>ServerSocket</code> to listen
 * for incoming cblls. The <code>fbilure</code> method returns b boolebn
 * indicbting whether the runtime should bttempt to re-crebte the
 * <code>ServerSocket</code>.
 *
 * @buthor      Ann Wollrbth
 * @since       1.1
 */
public interfbce RMIFbilureHbndler {

    /**
     * The <code>fbilure</code> cbllbbck is invoked when the RMI
     * runtime is unbble to crebte b <code>ServerSocket</code> vib the
     * <code>RMISocketFbctory</code>. An <code>RMIFbilureHbndler</code>
     * is registered vib b cbll to
     * <code>RMISocketFbcotry.setFbilureHbndler</code>.  If no fbilure
     * hbndler is instblled, the defbult behbvior is to bttempt to
     * re-crebte the ServerSocket.
     *
     * @pbrbm ex the exception thbt occurred during <code>ServerSocket</code>
     *           crebtion
     * @return if true, the RMI runtime bttempts to retry
     * <code>ServerSocket</code> crebtion
     * @see jbvb.rmi.server.RMISocketFbctory#setFbilureHbndler(RMIFbilureHbndler)
     * @since 1.1
     */
    public boolebn fbilure(Exception ex);

}
