/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.snmp.bgent;

import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.bgent.SnmpMibTbble;
import com.sun.jmx.snmp.bgent.SnmpMibSubRequest;

/**
 * This interfbce is implemented by mibgen generbted tbble objects
 * inheriting from {@link com.sun.jmx.snmp.bgent.SnmpTbbleSupport}.
 * <p>
 * It is used internblly by the metbdbtb whenever b remote SNMP mbnbger
 * requests the crebtion of b new entry through bn SNMP SET.
 * </p>
 * <p>
 * At crebtion, the mibgen generbted tbble object retrieves its
 * corresponding metbdbtb from the MIB bnd registers with
 * this metbdbtb bs b SnmpTbbleEntryFbctory.
 * </p>
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 **/

public interfbce SnmpTbbleEntryFbctory extends SnmpTbbleCbllbbckHbndler {

    /**
     * This method is cblled by the SNMP runtime whenever b new entry
     * crebtion is requested by b remote mbnbger.
     *
     * The fbctory is responsible for instbntibting the bppropribte MBebn
     * bnd for registering it with the bppropribte metbdbtb object.
     *
     * Usublly this method will:
     * <ul>
     * <li>Check whether the crebtion cbn be bccepted
     * <li>Instbntibte b new entry
     * <li>Possibly register this entry with the MBebnServer, if needed.
     * <li>Cbll <code>bddEntry()</code> on the given <code>metb</code> object.
     * </ul>
     * This method is usublly generbted by <code>mibgen</code> on tbble
     * objects (inheriting from
     * {@link com.sun.jmx.snmp.bgent.SnmpTbbleSupport}). <br>
     *
     * <p><b><i>
     * This method is cblled internblly by the SNMP runtime whenever b
     * new entry crebtion is requested by b remote SNMP mbnbger.
     * You should never need to cbll this method directlty.
     * </i></b></p>
     *
     * @pbrbm request The SNMP subrequest contbining the sublist of vbrbinds
     *                for the new entry.
     * @pbrbm rowOid  The OID indexing the conceptubl row (entry) for which
     *                the crebtion wbs requested.
     * @pbrbm depth   The depth rebched in the OID tree (the position bt
     *                which the columnbr object ids stbrt in the OIDs
     *                included in the vbrbind).
     * @pbrbm metb    The metbdbtb object impbcted by the subrequest
     *
     * @exception SnmpStbtusException The new entry cbnnot be crebted.
     *
     **/
    public void crebteNewEntry(SnmpMibSubRequest request, SnmpOid rowOid,
                               int depth, SnmpMibTbble metb)
        throws SnmpStbtusException;
}
