/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.io.Seriblizbble;
import jbvb.net.InetAddress;
/**
 * Is the fully decoded representbtion of bn SNMP pbcket.
 * <P>
 * Clbsses bre derived from <CODE>SnmpPdu</CODE> to
 * represent the different forms of SNMP pbckets
 * ({@link com.sun.jmx.snmp.SnmpPduPbcket SnmpPduPbcket},
 * {@link com.sun.jmx.snmp.SnmpScopedPduPbcket SnmpScopedPduPbcket})
 * <BR>The <CODE>SnmpPdu</CODE> clbss defines the bttributes
 * common to every form of SNMP pbckets.
 *
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @see SnmpMessbge
 * @see SnmpPduFbctory
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss SnmpPdu implements SnmpDefinitions, Seriblizbble {

    /**
     * PDU type. Types bre defined in
     * {@link com.sun.jmx.snmp.SnmpDefinitions SnmpDefinitions}.
     * @seribl
     */
    public int type=0 ;

    /**
     * Protocol version. Versions bre defined in
     * {@link com.sun.jmx.snmp.SnmpDefinitions SnmpDefinitions}.
     * @seribl
     */
    public int version=0 ;

    /**
     * List of vbribbles.
     * @seribl
     */
    public SnmpVbrBind[] vbrBindList ;


    /**
     * Request identifier.
     * Note thbt this field is not used by <CODE>SnmpPduTrbp</CODE>.
     * @seribl
     */
    public int requestId=0 ;

    /**
     * Source or destinbtion bddress.
     * <P>For bn incoming PDU it's the source.
     * <BR>For bn outgoing PDU it's the destinbtion.
     * @seribl
     */
    public InetAddress bddress ;

    /**
     * Source or destinbtion port.
     * <P>For bn incoming PDU it's the source.
     * <BR>For bn outgoing PDU it's the destinbtion.
     * @seribl
     */
    public int port=0 ;

    /**
     * Returns the <CODE>String</CODE> representbtion of b PDU type.
     * For instbnce, if the PDU type is <CODE>SnmpDefinitions.pduGetRequestPdu</CODE>,
     * the method will return "SnmpGet".
     * @pbrbm cmd The integer representbtion of the PDU type.
     * @return The <CODE>String</CODE> representbtion of the PDU type.
     */
    public stbtic String pduTypeToString(int cmd) {
        switch (cmd) {
        cbse pduGetRequestPdu :
            return "SnmpGet" ;
        cbse pduGetNextRequestPdu :
            return "SnmpGetNext" ;
        cbse pduWblkRequest :
            return "SnmpWblk(*)" ;
        cbse pduSetRequestPdu :
            return "SnmpSet" ;
        cbse pduGetResponsePdu :
            return "SnmpResponse" ;
        cbse pduV1TrbpPdu :
            return "SnmpV1Trbp" ;
        cbse pduV2TrbpPdu :
            return "SnmpV2Trbp" ;
        cbse pduGetBulkRequestPdu :
            return "SnmpGetBulk" ;
        cbse pduInformRequestPdu :
            return "SnmpInform" ;
        }
        return "Unknown Commbnd = " + cmd ;
    }
}
