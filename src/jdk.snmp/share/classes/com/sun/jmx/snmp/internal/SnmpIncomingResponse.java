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
import com.sun.jmx.snmp.SnmpPduFbctory;
import com.sun.jmx.snmp.SnmpSecurityPbrbmeters;
import com.sun.jmx.snmp.SnmpSecurityException;
import com.sun.jmx.snmp.SnmpTooBigException;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpPdu;
import com.sun.jmx.snmp.SnmpMsg;

import com.sun.jmx.snmp.internbl.SnmpSecurityCbche;
import com.sun.jmx.snmp.SnmpBbdSecurityLevelException;
/**
 * <P> An <CODE>SnmpIncomingResponse</CODE> hbndles the unmbrshblling of the received response.</P>
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */

public interfbce SnmpIncomingResponse {
    /**
     * Returns the source bddress.
     * @return The source bddress.
     */
    public InetAddress getAddress();

    /**
     * Returns the source port.
     * @return The source port.
     */
    public int getPort();

    /**
     * Gets the incoming response security pbrbmeters.
     * @return The security pbrbmeters.
     **/
    public SnmpSecurityPbrbmeters getSecurityPbrbmeters();
    /**
     * Cbll this method in order to reuse <CODE>SnmpOutgoingRequest</CODE> cbche.
     * @pbrbm cbche The security cbche.
     */
    public void setSecurityCbche(SnmpSecurityCbche cbche);
    /**
     * Gets the incoming response security level. This level is defined in
     * {@link com.sun.jmx.snmp.SnmpEngine SnmpEngine}.
     * @return The security level.
     */
    public int getSecurityLevel();
    /**
     * Gets the incoming response security model.
     * @return The security model.
     */
    public int getSecurityModel();
    /**
     * Gets the incoming response context nbme.
     * @return The context nbme.
     */
    public byte[] getContextNbme();

    /**
     * Decodes the specified bytes bnd initiblizes itself with the received
     * response.
     *
     * @pbrbm inputBytes The bytes to be decoded.
     *
     * @exception SnmpStbtusException If the specified bytes bre not b vblid encoding.
     */
    public SnmpMsg decodeMessbge(byte[] inputBytes,
                                 int byteCount,
                                 InetAddress bddress,
                                 int port)
        throws SnmpStbtusException, SnmpSecurityException;

    /**
     * Gets the request PDU encoded in the received response.
     * <P>
     * This method decodes the dbtb field bnd returns the resulting PDU.
     *
     * @return The resulting PDU.
     * @exception SnmpStbtusException If the encoding is not vblid.
     */
    public SnmpPdu decodeSnmpPdu()
        throws SnmpStbtusException;

    /**
     * Returns the response request Id.
     * @pbrbm dbtb The flbt messbge.
     * @return The request Id.
     */
    public int getRequestId(byte[] dbtb) throws SnmpStbtusException;

    /**
     * Returns b stringified form of the messbge to send.
     * @return The messbge stbte string.
     */
    public String printMessbge();
}
