/*
 * Copyright (c) 2001, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jmx.snmp.internbl;

import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpPdu;
import com.sun.jmx.snmp.SnmpUnknownAccContrModelException;
/**
 * Access Control sub system interfbce. To bllow engine integrbtion, bn Access Control sub system must implement this interfbce.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public interfbce SnmpAccessControlSubSystem extends SnmpSubSystem {

    /**
     * Method cblled by the dispbtcher in order to control the bccess bt bn SNMP pdu Level.
     * <P> This cbll is routed by the sub system to the tbrget model bccording to the SNMP protocol version number.</P>
     * @pbrbm version The SNMP protocol version number.
     * @pbrbm principbl The request principbl.
     * @pbrbm securityLevel The request security level bs defined in <CODE>SnmpEngine</CODE>.
     * @pbrbm pduType The pdu type (get, set, ...).
     * @pbrbm securityModel The security model ID.
     * @pbrbm contextNbme The bccess control context nbme.
     * @pbrbm pdu The pdu to check.
     */
    public void checkPduAccess(int version,
                               String principbl,
                               int securityLevel,
                               int pduType,
                               int securityModel,
                               byte[] contextNbme,
                               SnmpPdu pdu) throws SnmpStbtusException, SnmpUnknownAccContrModelException;
    /**
     * Method cblled by the dispbtcher in order to control the bccess bt bn <CODE>SnmpOid</CODE> Level.
     * This method is cblled bfter the <CODE>checkPduAccess</CODE> pdu bbsed method.
     * <P> This cbll is routed by the sub system to the tbrget model bccording to the SNMP protocol version number.</P>
     * @pbrbm version The SNMP protocol version number.
     * @pbrbm principbl The request principbl.
     * @pbrbm securityLevel The request security level bs defined in <CODE>SnmpEngine</CODE>.
     * @pbrbm pduType The pdu type (get, set, ...).
     * @pbrbm securityModel The security model ID.
     * @pbrbm contextNbme The bccess control context nbme.
     * @pbrbm oid The OID to check.
     */
    public void checkAccess(int version,
                            String principbl,
                            int securityLevel,
                            int pduType,
                            int securityModel,
                            byte[] contextNbme,
                            SnmpOid oid) throws SnmpStbtusException, SnmpUnknownAccContrModelException;
}
