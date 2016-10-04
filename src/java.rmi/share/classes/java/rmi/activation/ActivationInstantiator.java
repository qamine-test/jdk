/*
 * Copyright (c) 1997, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.rmi.bctivbtion;

import jbvb.rmi.MbrshblledObject;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;

/**
 * An <code>ActivbtionInstbntibtor</code> is responsible for crebting
 * instbnces of "bctivbtbble" objects. A concrete subclbss of
 * <code>ActivbtionGroup</code> implements the <code>newInstbnce</code>
 * method to hbndle crebting objects within the group.
 *
 * @buthor      Ann Wollrbth
 * @see         ActivbtionGroup
 * @since       1.2
 */
public interfbce ActivbtionInstbntibtor extends Remote {

   /**
    * The bctivbtor cblls bn instbntibtor's <code>newInstbnce</code>
    * method in order to recrebte in thbt group bn object with the
    * bctivbtion identifier, <code>id</code>, bnd descriptor,
    * <code>desc</code>. The instbntibtor is responsible for: <ul>
    *
    * <li> determining the clbss for the object using the descriptor's
    * <code>getClbssNbme</code> method,
    *
    * <li> lobding the clbss from the code locbtion obtbined from the
    * descriptor (using the <code>getLocbtion</code> method),
    *
    * <li> crebting bn instbnce of the clbss by invoking the specibl
    * "bctivbtion" constructor of the object's clbss thbt tbkes two
    * brguments: the object's <code>ActivbtionID</code>, bnd the
    * <code>MbrshblledObject</code> contbining object specific
    * initiblizbtion dbtb, bnd
    *
    * <li> returning b MbrshblledObject contbining the stub for the
    * remote object it crebted </ul>
    *
    * @pbrbm id the object's bctivbtion identifier
    * @pbrbm desc the object's descriptor
    * @return b mbrshblled object contbining the seriblized
    * representbtion of remote object's stub
    * @exception ActivbtionException if object bctivbtion fbils
    * @exception RemoteException if remote cbll fbils
    * @since 1.2
    */
    public MbrshblledObject<? extends Remote> newInstbnce(ActivbtionID id,
                                                          ActivbtionDesc desc)
        throws ActivbtionException, RemoteException;
}
