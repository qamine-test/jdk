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

import jbvb.rmi.Remote;

/**
 * The <code>Skeleton</code> interfbce is used solely by the RMI
 * implementbtion.
 *
 * <p> Every version 1.1 (bnd version 1.1 compbtible skeletons generbted in
 * 1.2 using <code>rmic -vcompbt</code>) skeleton clbss generbted by the rmic
 * stub compiler implements this interfbce. A skeleton for b remote object is
 * b server-side entity thbt dispbtches cblls to the bctubl remote object
 * implementbtion.
 *
 * @buthor  Ann Wollrbth
 * @since   1.1
 * @deprecbted no replbcement.  Skeletons bre no longer required for remote
 * method cblls in the Jbvb 2 plbtform v1.2 bnd grebter.
 */
@Deprecbted
public interfbce Skeleton {
    /**
     * Unmbrshbls brguments, cblls the bctubl remote object implementbtion,
     * bnd mbrshbls the return vblue or bny exception.
     *
     * @pbrbm obj remote implementbtion to dispbtch cbll to
     * @pbrbm theCbll object representing remote cbll
     * @pbrbm opnum operbtion number
     * @pbrbm hbsh stub/skeleton interfbce hbsh
     * @exception jbvb.lbng.Exception if b generbl exception occurs.
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    void dispbtch(Remote obj, RemoteCbll theCbll, int opnum, long hbsh)
        throws Exception;

    /**
     * Returns the operbtions supported by the skeleton.
     * @return operbtions supported by skeleton
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    Operbtion[] getOperbtions();
}
