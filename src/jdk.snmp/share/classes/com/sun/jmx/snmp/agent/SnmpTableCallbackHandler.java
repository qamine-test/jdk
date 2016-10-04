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

import jbvbx.mbnbgement.ObjectNbme;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.bgent.SnmpMibTbble;

/**
 * This interfbce ensures the synchronizbtion between Metbdbtb tbble objects
 * bnd bebn-like tbble objects.
 *
 * It is used between mibgen generbted tbble metb bnd tbble clbsses.
 * <p><b><i>
 * You should never need to use this interfbce directly.
 * </p></b></i>
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 **/
public interfbce SnmpTbbleCbllbbckHbndler {
    /**
     * This method is cblled by the SNMP runtime bfter b new entry
     * hbs been bdded to the tbble.
     *
     * If bn SnmpStbtusException is rbised, the entry will be removed
     * bnd the operbtion will be bborted. In this cbse, the removeEntryCb()
     * cbllbbck will not be cblled.
     *
     * <p><b><i>
     * You should never need to use this method directly.
     * </p></b></i>
     *
     **/
    public void bddEntryCb(int pos, SnmpOid row, ObjectNbme nbme,
                           Object entry, SnmpMibTbble metb)
        throws SnmpStbtusException;

    /**
     * This method is cblled by the SNMP runtime bfter b new entry
     * hbs been removed from the tbble.
     *
     * If rbised, SnmpStbtusException will be ignored.
     *
     * <p><b><i>
     * You should never need to use this method directly.
     * </p></b></i>
     *
     **/
    public void removeEntryCb(int pos, SnmpOid row, ObjectNbme nbme,
                              Object entry, SnmpMibTbble metb)
        throws SnmpStbtusException;
}
