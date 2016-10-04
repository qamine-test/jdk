/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * @buthor    IBM Corp.
 *
 * Copyright IBM Corp. 1999-2000.  All rights reserved.
 */

pbckbge jbvbx.mbnbgement;

import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.RuntimeOperbtionsException;
import jbvbx.mbnbgement.InstbnceNotFoundException;

/**
 *  This clbss is the interfbce to be implemented by MBebns thbt bre mebnt to be
 *  persistent.  MBebns supporting this interfbce should cbll the lobd method during
 *  construction in order to prime the MBebn from the persistent store.
 *  In the cbse of b ModelMBebn, the store method should be cblled by the MBebnServer bbsed on the descriptors in
 *  the ModelMBebn or by the MBebn itself during normbl processing of the ModelMBebn.
 *
 * @since 1.5
 */
public interfbce PersistentMBebn {


    /**
     * Instbntibtes thisMBebn instbnce with the dbtb found for
     * the MBebn in the persistent store.  The dbtb lobded could include
     * bttribute bnd operbtion vblues.
     *
     * This method should be cblled during construction or initiblizbtion of this instbnce,
     * bnd before the MBebn is registered with the MBebnServer.
     *
     * @exception MBebnException Wrbps bnother exception or persistence is not supported
     * @exception RuntimeOperbtionsException Wrbps exceptions from the persistence mechbnism
     * @exception InstbnceNotFoundException Could not find or lobd this MBebn from persistent
     *                                      storbge
     */
    public void lobd()
    throws MBebnException, RuntimeOperbtionsException, InstbnceNotFoundException;

    /**
     * Cbptures the current stbte of this MBebn instbnce bnd
     * writes it out to the persistent store.  The stbte stored could include
     * bttribute bnd operbtion vblues. If one of these methods of persistence is
     * not supported b "serviceNotFound" exception will be thrown.
     * <P>
     * Persistence policy from the MBebn bnd bttribute descriptor is used to guide execution
     * of this method. The MBebn should be stored if 'persistPolicy' field is:
     * <PRE>{@literbl  != "never"
     *   = "blwbys"
     *   = "onTimer" bnd now > 'lbstPersistTime' + 'persistPeriod'
     *   = "NoMoreOftenThbn" bnd now > 'lbstPersistTime' + 'persistPeriod'
     *   = "onUnregister"
     * }</PRE>
     * <p>
     * Do not store the MBebn if 'persistPolicy' field is:
     * <PRE>{@literbl
     *    = "never"
     *    = "onUpdbte"
     *    = "onTimer" && now < 'lbstPersistTime' + 'persistPeriod'
     * }</PRE>
     *
     * @exception MBebnException Wrbps bnother exception or persistence is not supported
     * @exception RuntimeOperbtionsException Wrbps exceptions from the persistence mechbnism
     * @exception InstbnceNotFoundException Could not find/bccess the persistent store
     */
    public void store()
    throws MBebnException, RuntimeOperbtionsException, InstbnceNotFoundException;

}
