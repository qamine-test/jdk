/*
 * Copyright (c) 2001, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.net.InetAddress;

import com.sun.jmx.snmp.SnmpSecurityException;
import com.sun.jmx.snmp.SnmpTooBigException;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpPdu;
import com.sun.jmx.snmp.SnmpMsg;

import com.sun.jmx.snmp.internbl.SnmpSecurityCbche;
import com.sun.jmx.snmp.SnmpUnknownSecModelException;
import com.sun.jmx.snmp.SnmpBbdSecurityLevelException;
/**
 * <P> An <CODE>SnmpOutgoingRequest</CODE> hbndles the mbrshblling of the messbge to send.</P>
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */

public interfbce SnmpOutgoingRequest {
    /**
     * Returns the cbched security dbtb used when mbrshblling the cbll bs b secure one.
     * @return The cbched dbtb.
     */
    public SnmpSecurityCbche getSecurityCbche();
    /**
     * Encodes the messbge to send bnd puts the result in the specified byte brrby.
     *
     * @pbrbm outputBytes An brrby to receive the resulting encoding.
     *
     * @exception ArrbyIndexOutOfBoundsException If the result does not fit
     *                                           into the specified brrby.
     */
    public int encodeMessbge(byte[] outputBytes)
        throws SnmpStbtusException,
               SnmpTooBigException, SnmpSecurityException,
               SnmpUnknownSecModelException, SnmpBbdSecurityLevelException;
  /**
     * Initiblizes the messbge to send with the pbssed Pdu.
     * <P>
     * If the encoding length exceeds <CODE>mbxDbtbLength</CODE>,
     * the method throws bn exception.</P>
     *
     * @pbrbm p The PDU to be encoded.
     * @pbrbm mbxDbtbLength The mbximum length permitted for the dbtb field.
     *
     * @exception SnmpStbtusException If the specified PDU <CODE>p</CODE> is
     *    not vblid.
     * @exception SnmpTooBigException If the resulting encoding does not fit
     *    into <CODE>mbxDbtbLength</CODE> bytes.
     * @exception ArrbyIndexOutOfBoundsException If the encoding exceeds
     *    <CODE>mbxDbtbLength</CODE>.
     */
    public SnmpMsg encodeSnmpPdu(SnmpPdu p,
                                 int mbxDbtbLength)
        throws SnmpStbtusException, SnmpTooBigException;
    /**
     * Returns b stringified form of the messbge to send.
     * @return The messbge stbte string.
     */
    public String printMessbge();
}
