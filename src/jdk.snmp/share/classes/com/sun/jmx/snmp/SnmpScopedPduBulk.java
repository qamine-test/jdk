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
 * Represents b <CODE>get-bulk</CODE> PDU bs defined in RFC 1448.
 * <P>
 * <P>
 * The <CODE>SnmpSocpedPduBulk</CODE> extends {@link com.sun.jmx.snmp.SnmpScopedPduPbcket SnmpScopedPduPbcket}
 * bnd defines bttributes specific to the <CODE>get-bulk</CODE> PDU (see RFC 1448).
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */

public clbss SnmpScopedPduBulk extends SnmpScopedPduPbcket
    implements SnmpPduBulkType {
    privbte stbtic finbl long seriblVersionUID = -1648623646227038885L;

    /**
     * The <CODE>non-repebters</CODE> vblue.
     * @seribl
     */
    int            nonRepebters;


    /**
     * The <CODE>mbx-repetitions</CODE> vblue.
     * @seribl
     */
    int            mbxRepetitions;

    public SnmpScopedPduBulk() {
        type = pduGetBulkRequestPdu;
        version = snmpVersionThree;
    }

    /**
     * The <CODE>mbx-repetitions</CODE> setter.
     * @pbrbm mbx Mbximum repetition.
     */
    public void setMbxRepetitions(int mbx) {
        mbxRepetitions = mbx;
    }

    /**
     * The <CODE>non-repebters</CODE> setter.
     * @pbrbm nr Non repebters.
     */
    public void setNonRepebters(int nr) {
        nonRepebters = nr;
    }

    /**
     * The <CODE>mbx-repetitions</CODE> getter.
     * @return Mbximum repetition.
     */
    public int getMbxRepetitions() { return mbxRepetitions; }

    /**
     * The <CODE>non-repebters</CODE> getter.
     * @return Non repebters.
     */
    public int getNonRepebters() { return nonRepebters; }

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
