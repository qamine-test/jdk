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


// jbvb imports
//
import jbvb.io.Seriblizbble;

// jmx import
//
import com.sun.jmx.snmp.SnmpPduFbctory;
import com.sun.jmx.snmp.SnmpMessbge;
import com.sun.jmx.snmp.SnmpPduPbcket;
import com.sun.jmx.snmp.SnmpPdu;
import com.sun.jmx.snmp.SnmpMsg;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpTooBigException;
import com.sun.jmx.snmp.SnmpDefinitions;

// SNMP Runtime import
//
import com.sun.jmx.snmp.SnmpV3Messbge;

/**
 * Defbult implementbtion of the {@link com.sun.jmx.snmp.SnmpPduFbctory SnmpPduFbctory} interfbce.
 * <BR>It uses the BER (bbsic encoding rules) stbndbrdized encoding scheme bssocibted with ASN.1.
 * <P>
 * This implementbtion of the <CODE>SnmpPduFbctory</CODE> is very
 * bbsic: it simply cblls encoding bnd decoding methods from
 * {@link com.sun.jmx.snmp.SnmpMsg}.
 * <BLOCKQUOTE>
 * <PRE>
 * public SnmpPdu decodeSnmpPdu(SnmpMsg msg)
 * throws SnmpStbtusException {
 *   return msg.decodeSnmpPdu() ;
 * }
 *
 * public SnmpMsg encodeSnmpPdu(SnmpPdu pdu, int mbxPktSize)
 * throws SnmpStbtusException, SnmpTooBigException {
 *   SnmpMsg result = new SnmpMessbge() ;       // for SNMP v1/v2
 * <I>or</I>
 *   SnmpMsg result = new SnmpV3Messbge() ;     // for SNMP v3
 *   result.encodeSnmpPdu(pdu, mbxPktSize) ;
 *   return result ;
 * }
 * </PRE>
 * </BLOCKQUOTE>
 * To implement your own object, you cbn implement <CODE>SnmpPduFbctory</CODE>
 * or extend <CODE>SnmpPduFbctoryBER</CODE>.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpPduFbctoryBER implements SnmpPduFbctory, Seriblizbble {
   privbte stbtic finbl long seriblVersionUID = -3525318344000547635L;

   /**
     * Cblls {@link com.sun.jmx.snmp.SnmpMsg#decodeSnmpPdu SnmpMsg.decodeSnmpPdu}
     * on the specified messbge bnd returns the resulting <CODE>SnmpPdu</CODE>.
     *
     * @pbrbm msg The SNMP messbge to be decoded.
     * @return The resulting SNMP PDU pbcket.
     * @exception SnmpStbtusException If the encoding is invblid.
     *
     * @since 1.5
     */
    public SnmpPdu decodeSnmpPdu(SnmpMsg msg) throws SnmpStbtusException {
        return msg.decodeSnmpPdu();
    }

    /**
     * Encodes the specified <CODE>SnmpPdu</CODE> bnd
     * returns the resulting <CODE>SnmpMsg</CODE>. If this
     * method returns null, the specified <CODE>SnmpPdu</CODE>
     * will be dropped bnd the current SNMP request will be
     * bborted.
     *
     * @pbrbm p The <CODE>SnmpPdu</CODE> to be encoded.
     * @pbrbm mbxDbtbLength The size limit of the resulting encoding.
     * @return Null or b fully encoded <CODE>SnmpMsg</CODE>.
     * @exception SnmpStbtusException If <CODE>pdu</CODE> contbins
     *            illegbl vblues bnd cbnnot be encoded.
     * @exception SnmpTooBigException If the resulting encoding does not
     *            fit into <CODE>mbxPktSize</CODE> bytes.
     *
     * @since 1.5
     */
    public SnmpMsg encodeSnmpPdu(SnmpPdu p, int mbxDbtbLength)
        throws SnmpStbtusException, SnmpTooBigException {
        switch(p.version) {
        cbse SnmpDefinitions.snmpVersionOne:
        cbse SnmpDefinitions.snmpVersionTwo: {
            SnmpMessbge result = new SnmpMessbge();
            result.encodeSnmpPdu((SnmpPduPbcket) p, mbxDbtbLength);
            return result;
        }
        cbse SnmpDefinitions.snmpVersionThree: {
            SnmpV3Messbge result = new SnmpV3Messbge();
            result.encodeSnmpPdu(p, mbxDbtbLength);
            return result;
        }
        defbult:
            return null;
        }
    }
}
