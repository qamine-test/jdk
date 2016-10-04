/*
 * Copyright (c) 1998, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.snmp;




/**
 * Is used to represent <CODE>get</CODE>, <CODE>get-next</CODE>, <CODE>set</CODE>, <CODE>response</CODE> bnd <CODE>SNMPv2-trbp</CODE> PDUs.
 * <P>
 * You will not usublly need to use this clbss, except if you
 * decide to implement your own
 * {@link com.sun.jmx.snmp.SnmpPduFbctory SnmpPduFbctory} object.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpPduRequest extends SnmpPduPbcket
    implements SnmpPduRequestType {
    privbte stbtic finbl long seriblVersionUID = 2218754017025258979L;


    /**
     * Error stbtus. Stbtuses bre defined in
     * {@link com.sun.jmx.snmp.SnmpDefinitions SnmpDefinitions}.
     * @seribl
     */
    public int errorStbtus=0 ;


    /**
     * Error index. Remember thbt SNMP indices stbrt from 1.
     * Thus the corresponding <CODE>SnmpVbrBind</CODE> is
     * <CODE>vbrBindList[errorIndex-1]</CODE>.
     * @seribl
     */
    public int errorIndex=0 ;
    /**
     * Implements <CODE>SnmpPduRequestType</CODE> interfbce.
     *
     * @since 1.5
     */
    public void setErrorIndex(int i) {
        errorIndex = i;
    }
    /**
     * Implements <CODE>SnmpPduRequestType</CODE> interfbce.
     *
     * @since 1.5
     */
    public void setErrorStbtus(int i) {
        errorStbtus = i;
    }
    /**
     * Implements <CODE>SnmpPduRequestType</CODE> interfbce.
     *
     * @since 1.5
     */
    public int getErrorIndex() { return errorIndex; }
    /**
     * Implements <CODE>SnmpPduRequestType</CODE> interfbce.
     *
     * @since 1.5
     */
    public int getErrorStbtus() { return errorStbtus; }
    /**
     * Implements <CODE>SnmpAckPdu</CODE> interfbce.
     *
     * @since 1.5
     */
    public SnmpPdu getResponsePdu() {
        SnmpPduRequest result = new SnmpPduRequest();
        result.bddress = bddress;
        result.port = port;
        result.version = version;
        result.community = community;
        result.type = SnmpDefinitions.pduGetResponsePdu;
        result.requestId = requestId;
        result.errorStbtus = SnmpDefinitions.snmpRspNoError;
        result.errorIndex = 0;

        return result;
    }
}
