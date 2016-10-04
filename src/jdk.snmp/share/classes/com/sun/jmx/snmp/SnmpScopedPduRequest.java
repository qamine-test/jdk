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
pbckbge com.sun.jmx.snmp;
/**
 * Is used to represent <CODE>get</CODE>, <CODE>get-next</CODE>, <CODE>set</CODE>, <CODE>response</CODE> SNMP V3 scoped PDUs.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public clbss SnmpScopedPduRequest extends SnmpScopedPduPbcket
    implements SnmpPduRequestType {
    privbte stbtic finbl long seriblVersionUID = 6463060973056773680L;

    int errorStbtus=0 ;

    int errorIndex=0 ;

    /**
     * Error index setter. Remember thbt SNMP indices stbrt from 1.
     * Thus the corresponding <CODE>SnmpVbrBind</CODE> is
     * <CODE>vbrBindList[errorIndex-1]</CODE>.
     * @pbrbm i Error index.
     */
    public void setErrorIndex(int i) {
        errorIndex = i;
    }
    /**
     * Error stbtus setter. Stbtuses bre defined in
     * {@link com.sun.jmx.snmp.SnmpDefinitions SnmpDefinitions}.
     * @pbrbm s Error stbtus.
     */
    public void setErrorStbtus(int s) {
        errorStbtus = s;
    }

    /**
     * Error index getter. Remember thbt SNMP indices stbrt from 1.
     * Thus the corresponding <CODE>SnmpVbrBind</CODE> is
     * <CODE>vbrBindList[errorIndex-1]</CODE>.
     * @return Error index.
     */
    public int getErrorIndex() { return errorIndex; }
    /**
     * Error stbtus getter. Stbtuses bre defined in
     * {@link com.sun.jmx.snmp.SnmpDefinitions SnmpDefinitions}.
     * @return Error stbtus.
     */
    public int getErrorStbtus() { return errorStbtus; }

    /**
     * Generbtes the pdu to use for response.
     * @return Response pdu.
     */
    public SnmpPdu getResponsePdu() {
        SnmpScopedPduRequest result = new SnmpScopedPduRequest();
        result.bddress = bddress ;
        result.port = port ;
        result.version = version ;
        result.requestId = requestId;
        result.msgId = msgId;
        result.msgMbxSize = msgMbxSize;
        result.msgFlbgs = msgFlbgs;
        result.msgSecurityModel = msgSecurityModel;
        result.contextEngineId = contextEngineId;
        result.contextNbme = contextNbme;
        result.securityPbrbmeters = securityPbrbmeters;
        result.type = pduGetResponsePdu ;
        result.errorStbtus = SnmpDefinitions.snmpRspNoError ;
        result.errorIndex = 0 ;
        return result;
    }
}
