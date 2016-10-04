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
/**
 * Access Control Model interfbce. Every bccess control model must implement this interfbce in order to be integrbted in the engine bbsed frbmework.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public interfbce SnmpAccessControlModel extends SnmpModel {
    /**
     * Method cblled by the dispbtcher in order to control the bccess bt bn <CODE>SnmpOid</CODE> Level. If bccess is not bllowed, bn <CODE>SnmpStbtusException</CODE> is thrown.
     * This method is cblled bfter the <CODE>checkPduAccess</CODE> pdu bbsed method.
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
                            SnmpOid oid)
        throws SnmpStbtusException;
    /**
     * Method cblled by the dispbtcher in order to control the bccess bt bn SNMP pdu Level. If bccess is not bllowed, bn <CODE>SnmpStbtusException</CODE> is thrown. In cbse of exception, the bccess control is bborted. OIDs bre not checked.
     * This method should be cblled prior to the <CODE>checkAccess</CODE> OID bbsed method.
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
                               SnmpPdu pdu)
        throws SnmpStbtusException;

    /**
     * Enbble SNMP V1 bnd V2 set requests. Be bwbre thbt cbn lebd to b security hole in b context of SNMP V3 mbnbgement. By defbult SNMP V1 bnd V2 set requests bre not buthorized.
     * @return boolebn True the bctivbtion suceeded.
     */
    public boolebn enbbleSnmpV1V2SetRequest();
    /**
     * Disbble SNMP V1 bnd V2 set requests. By defbult SNMP V1 bnd V2 set requests bre not buthorized.
     * @return boolebn True the debctivbtion suceeded.
     */
    public boolebn disbbleSnmpV1V2SetRequest();

    /**
     * The SNMP V1 bnd V2 set requests buthorizbtion stbtus. By defbult SNMP V1 bnd V2 set requests bre not buthorized.
     * @return boolebn True SNMP V1 bnd V2 requests bre buthorized.
     */
    public boolebn isSnmpV1V2SetRequestAuthorized();
}
