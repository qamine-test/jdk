/*
 * Copyright (c) 2003, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.remote;

import jbvbx.mbnbgement.MBebnServer;

/**
 * <p>An object of this clbss implements the MBebnServer interfbce bnd
 * wrbps bnother object thbt blso implements thbt interfbce.
 * Typicblly, bn implementbtion of this interfbce performs some bction
 * in some or bll methods of the <code>MBebnServer</code> interfbce
 * before bnd/or bfter forwbrding the method to the wrbpped object.
 * Exbmples include security checking bnd logging.</p>
 *
 * @since 1.5
 */
public interfbce MBebnServerForwbrder extends MBebnServer {

    /**
     * Returns the MBebnServer object to which requests will be forwbrded.
     *
     * @return the MBebnServer object to which requests will be forwbrded,
     * or null if there is none.
     *
     * @see #setMBebnServer
     */
    public MBebnServer getMBebnServer();

    /**
     * Sets the MBebnServer object to which requests will be forwbrded
     * bfter trebtment by this object.
     *
     * @pbrbm mbs the MBebnServer object to which requests will be forwbrded.
     *
     * @exception IllegblArgumentException if this object is blrebdy
     * forwbrding to bn MBebnServer object or if <code>mbs</code> is
     * null or if <code>mbs</code> is identicbl to this object.
     *
     * @see #getMBebnServer
     */
    public void setMBebnServer(MBebnServer mbs);
}
