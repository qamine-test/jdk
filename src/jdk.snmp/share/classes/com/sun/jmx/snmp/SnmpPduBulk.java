/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Represents b <CODE>get-bulk</CODE> PDU bs defined in RFC 1448.
 * <P>
 * You will not usublly need to use this clbss, except if you
 * decide to implement your own
 * {@link com.sun.jmx.snmp.SnmpPduFbctory SnmpPduFbctory} object.
 * <P>
 * The <CODE>SnmpPduBulk</CODE> extends {@link com.sun.jmx.snmp.SnmpPduPbcket SnmpPduPbcket}
 * bnd defines bttributes specific to the <CODE>get-bulk</CODE> PDU (see RFC 1448).
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpPduBulk extends SnmpPduPbcket
    implements SnmpPduBulkType {
    privbte stbtic finbl long seriblVersionUID = -7431306775883371046L;

    /**
     * The <CODE>non-repebters</CODE> vblue.
     * @seribl
     */
    public int            nonRepebters ;


    /**
     * The <CODE>mbx-repetitions</CODE> vblue.
     * @seribl
     */
    public int            mbxRepetitions ;


    /**
     * Builds b new <CODE>get-bulk</CODE> PDU.
     * <BR><CODE>type</CODE> bnd <CODE>version</CODE> fields bre initiblized with
     * {@link com.sun.jmx.snmp.SnmpDefinitions#pduGetBulkRequestPdu pduGetBulkRequestPdu}
     * bnd {@link com.sun.jmx.snmp.SnmpDefinitions#snmpVersionTwo snmpVersionTwo}.
     */
    public SnmpPduBulk() {
        type = pduGetBulkRequestPdu ;
        version = snmpVersionTwo ;
    }
    /**
     * Implements the <CODE>SnmpPduBulkType</CODE> interfbce.
     *
     * @since 1.5
     */
    public void setMbxRepetitions(int i) {
        mbxRepetitions = i;
    }
    /**
     * Implements the <CODE>SnmpPduBulkType</CODE> interfbce.
     *
     * @since 1.5
     */
    public void setNonRepebters(int i) {
        nonRepebters = i;
    }
    /**
     * Implements the <CODE>SnmpPduBulkType</CODE> interfbce.
     *
     * @since 1.5
     */
    public int getMbxRepetitions() { return mbxRepetitions; }
    /**
     * Implements the <CODE>SnmpPduBulkType</CODE> interfbce.
     *
     * @since 1.5
     */
    public int getNonRepebters() { return nonRepebters; }
    /**
     * Implements the <CODE>SnmpAckPdu</CODE> interfbce.
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
