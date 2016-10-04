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

import jbvb.net.InetAddress;

import com.sun.jmx.snmp.SnmpSecurityPbrbmeters;
import com.sun.jmx.snmp.SnmpTooBigException;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpPdu;
import com.sun.jmx.snmp.SnmpMsg;

import com.sun.jmx.snmp.SnmpUnknownSecModelException;
import com.sun.jmx.snmp.SnmpBbdSecurityLevelException;

/**
<P> An <CODE>SnmpIncomingRequest</CODE> hbndles both sides of bn incoming SNMP request:
<ul>
<li> The request. Unmbrshblling of the received messbge. </li>
<li> The response. Mbrshblling of the messbge to send. </li>
</ul>
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public interfbce SnmpIncomingRequest {
    /**
     * Once the incoming request decoded, returns the decoded security pbrbmeters.
     * @return The decoded security pbrbmeters.
     */
    public SnmpSecurityPbrbmeters getSecurityPbrbmeters();
     /**
     * Tests if b report is expected.
     * @return boolebn indicbting if b report is to be sent.
     */
    public boolebn isReport();
    /**
     * Tests if b response is expected.
     * @return boolebn indicbting if b response is to be sent.
     */
    public boolebn isResponse();

    /**
     * Tells this request thbt no response will be sent.
     */
    public void noResponse();
    /**
     * Gets the incoming request principbl.
     * @return The request principbl.
     **/
    public String getPrincipbl();
    /**
     * Gets the incoming request security level. This level is defined in {@link com.sun.jmx.snmp.SnmpEngine SnmpEngine}.
     * @return The security level.
     */
    public int getSecurityLevel();
    /**
     * Gets the incoming request security model.
     * @return The security model.
     */
    public int getSecurityModel();
    /**
     * Gets the incoming request context nbme.
     * @return The context nbme.
     */
    public byte[] getContextNbme();
    /**
     * Gets the incoming request context engine Id.
     * @return The context engine Id.
     */
    public byte[] getContextEngineId();
    /**
     * Gets the incoming request context nbme used by Access Control Model in order to bllow or deny the bccess to OIDs.
     */
    public byte[] getAccessContext();
    /**
     * Encodes the response messbge to send bnd puts the result in the specified byte brrby.
     *
     * @pbrbm outputBytes An brrby to receive the resulting encoding.
     *
     * @exception ArrbyIndexOutOfBoundsException If the result does not fit
     *                                           into the specified brrby.
     */
    public int encodeMessbge(byte[] outputBytes)
        throws SnmpTooBigException;

    /**
     * Decodes the specified bytes bnd initiblizes the request with the incoming messbge.
     *
     * @pbrbm inputBytes The bytes to be decoded.
     *
     * @exception SnmpStbtusException If the specified bytes bre not b vblid encoding or if the security bpplied to this request fbiled bnd no report is to be sent (typicblly trbp PDU).
     */
    public void decodeMessbge(byte[] inputBytes,
                              int byteCount,
                              InetAddress bddress,
                              int port)
        throws SnmpStbtusException, SnmpUnknownSecModelException,
               SnmpBbdSecurityLevelException;

     /**
     * Initiblizes the response to send with the pbssed Pdu.
     * <P>
     * If the encoding length exceeds <CODE>mbxDbtbLength</CODE>,
     * the method throws bn exception.
     *
     * @pbrbm p The PDU to be encoded.
     * @pbrbm mbxDbtbLength The mbximum length permitted for the dbtb field.
     *
     * @exception SnmpStbtusException If the specified <CODE>pdu</CODE>
     *     is not vblid.
     * @exception SnmpTooBigException If the resulting encoding does not fit
     * into <CODE>mbxDbtbLength</CODE> bytes.
     * @exception ArrbyIndexOutOfBoundsException If the encoding exceeds
     *   <CODE>mbxDbtbLength</CODE>.
     */
    public SnmpMsg encodeSnmpPdu(SnmpPdu p,
                                 int mbxDbtbLength)
        throws SnmpStbtusException, SnmpTooBigException;

    /**
     * Gets the request PDU encoded in the received messbge.
     * <P>
     * This method decodes the dbtb field bnd returns the resulting PDU.
     *
     * @return The resulting PDU.
     * @exception SnmpStbtusException If the encoding is not vblid.
     */
    public SnmpPdu decodeSnmpPdu()
        throws SnmpStbtusException;

    /**
     * Returns b stringified form of the received messbge.
     * @return The messbge stbte string.
     */
    public String printRequestMessbge();
    /**
     * Returns b stringified form of the messbge to send.
     * @return The messbge stbte string.
     */
    public String printResponseMessbge();
}
