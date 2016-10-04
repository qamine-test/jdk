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

pbckbge jbvb.rmi.server;

import jbvb.rmi.RemoteException;

/**
 * This exception is thrown when b cbll is received thbt does not
 * mbtch the bvbilbble skeleton.  It indicbtes either thbt the
 * remote method nbmes or signbtures in this interfbce hbve chbnged or
 * thbt the stub clbss used to mbke the cbll bnd the skeleton
 * receiving the cbll were not generbted by the sbme version of
 * the stub compiler (<code>rmic</code>).
 *
 * @buthor  Roger Riggs
 * @since   1.1
 * @deprecbted no replbcement.  Skeletons bre no longer required for remote
 * method cblls in the Jbvb 2 plbtform v1.2 bnd grebter.
 */
@Deprecbted
public clbss SkeletonMismbtchException extends RemoteException {

    /* indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = -7780460454818859281L;

    /**
     * Constructs b new <code>SkeletonMismbtchException</code> with
     * b specified detbil messbge.
     *
     * @pbrbm s the detbil messbge
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    public SkeletonMismbtchException(String s) {
        super(s);
    }

}
