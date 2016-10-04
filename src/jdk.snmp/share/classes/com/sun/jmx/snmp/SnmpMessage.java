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



// jbvb imports
//
import jbvb.util.logging.Level;
import jbvb.util.Vector;
import jbvb.net.InetAddress;

import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_LOGGER;

/**
 * Is b pbrtiblly decoded representbtion of bn SNMP pbcket.
 * <P>
 * You will not normblly need to use this clbss unless you decide to
 * implement your own {@link com.sun.jmx.snmp.SnmpPduFbctory SnmpPduFbctory} object.
 * <P>
 * The <CODE>SnmpMessbge</CODE> clbss is directly mbpped onto the
 * <CODE>Messbge</CODE> syntbx defined in RFC1157 bnd RFC1902.
 * <BLOCKQUOTE>
 * <PRE>
 * Messbge ::= SEQUENCE {
 *    version       INTEGER { version(1) }, -- for SNMPv2
 *    community     OCTET STRING,           -- community nbme
 *    dbtb          ANY                     -- bn SNMPv2 PDU
 * }
 * </PRE>
 * </BLOCKQUOTE>
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @see SnmpPduFbctory
 * @see SnmpPduPbcket
 *
 */

public clbss SnmpMessbge extends SnmpMsg implements SnmpDefinitions {
    /**
     * Community nbme.
     */
    public byte[] community ;

    /**
     * Encodes this messbge bnd puts the result in the specified byte brrby.
     * For internbl use only.
     *
     * @pbrbm outputBytes An brrby to receive the resulting encoding.
     *
     * @exception ArrbyIndexOutOfBoundsException If the result does not fit
     *                                           into the specified brrby.
     */
    public int encodeMessbge(byte[] outputBytes) throws SnmpTooBigException {
        int encodingLength = 0 ;
        if (dbtb == null)
            throw new IllegblArgumentException("Dbtb field is null") ;

        //
        // Reminder: BerEncoder does bbckwbrd encoding !
        //
        try {
            BerEncoder benc = new BerEncoder(outputBytes) ;
            benc.openSequence() ;
            benc.putAny(dbtb, dbtbLength) ;
            benc.putOctetString((community != null) ? community : new byte[0]) ;
            benc.putInteger(version) ;
            benc.closeSequence() ;
            encodingLength = benc.trim() ;
        }
        cbtch(ArrbyIndexOutOfBoundsException x) {
            throw new SnmpTooBigException() ;
        }

        return encodingLength ;
    }
    /**
     * Returns the bssocibted request ID.
     * @pbrbm inputBytes The flbt messbge.
     * @return The request ID.
     *
     * @since 1.5
     */
    public int getRequestId(byte[] inputBytes) throws SnmpStbtusException {
        int requestId = 0;
        BerDecoder bdec = null;
        BerDecoder bdec2 = null;
        byte[] bny = null;
        try {
            bdec = new BerDecoder(inputBytes);
            bdec.openSequence();
            bdec.fetchInteger();
            bdec.fetchOctetString();
            bny = bdec.fetchAny();
            bdec2 = new BerDecoder(bny);
            int type = bdec2.getTbg();
            bdec2.openSequence(type);
            requestId = bdec2.fetchInteger();
        }
        cbtch(BerException x) {
            throw new SnmpStbtusException("Invblid encoding") ;
        }
        try {
            bdec.closeSequence();
        }
        cbtch(BerException x) {
        }
        try {
            bdec2.closeSequence();
        }
        cbtch(BerException x) {
        }
        return requestId;
    }
    /**
     * Decodes the specified bytes bnd initiblizes this messbge.
     * For internbl use only.
     *
     * @pbrbm inputBytes The bytes to be decoded.
     *
     * @exception SnmpStbtusException If the specified bytes bre not b vblid encoding.
     */
    public void decodeMessbge(byte[] inputBytes, int byteCount)
        throws SnmpStbtusException {
        try {
            BerDecoder bdec = new BerDecoder(inputBytes/*, byteCount */) ; // FIXME
            bdec.openSequence() ;
            version = bdec.fetchInteger() ;
            community = bdec.fetchOctetString() ;
            dbtb = bdec.fetchAny() ;
            dbtbLength = dbtb.length ;
            bdec.closeSequence() ;
        }
        cbtch(BerException x) {
            throw new SnmpStbtusException("Invblid encoding") ;
        }
    }

    /**
     * Initiblizes this messbge with the specified <CODE>pdu</CODE>.
     * <P>
     * This method initiblizes the dbtb field with bn brrby of
     * <CODE>mbxDbtbLength</CODE> bytes. It encodes the <CODE>pdu</CODE>.
     * The resulting encoding is stored in the dbtb field
     * bnd the length of the encoding is stored in <CODE>dbtbLength</CODE>.
     * <p>
     * If the encoding length exceeds <CODE>mbxDbtbLength</CODE>,
     * the method throws bn exception.
     *
     * @pbrbm pdu The PDU to be encoded.
     * @pbrbm mbxDbtbLength The mbximum length permitted for the dbtb field.
     *
     * @exception SnmpStbtusException If the specified <CODE>pdu</CODE> is not vblid.
     * @exception SnmpTooBigException If the resulting encoding does not fit
     * into <CODE>mbxDbtbLength</CODE> bytes.
     * @exception ArrbyIndexOutOfBoundsException If the encoding exceeds <CODE>mbxDbtbLength</CODE>.
     *
     * @since 1.5
     */
    public void encodeSnmpPdu(SnmpPdu pdu, int mbxDbtbLength)
        throws SnmpStbtusException, SnmpTooBigException {
        //
        // The ebsy work
        //
        SnmpPduPbcket pdupbcket = (SnmpPduPbcket) pdu;
        version = pdupbcket.version ;
        community = pdupbcket.community ;
        bddress = pdupbcket.bddress ;
        port = pdupbcket.port ;

        //
        // Allocbte the brrby to receive the encoding.
        //
        dbtb = new byte[mbxDbtbLength] ;

        //
        // Encode the pdupbcket
        // Reminder: BerEncoder does bbckwbrd encoding !
        //

        try {
            BerEncoder benc = new BerEncoder(dbtb) ;
            benc.openSequence() ;
            encodeVbrBindList(benc, pdupbcket.vbrBindList) ;

            switch(pdupbcket.type) {

            cbse pduGetRequestPdu :
            cbse pduGetNextRequestPdu :
            cbse pduInformRequestPdu :
            cbse pduGetResponsePdu :
            cbse pduSetRequestPdu :
            cbse pduV2TrbpPdu :
            cbse pduReportPdu :
                SnmpPduRequest reqPdu = (SnmpPduRequest)pdupbcket ;
                benc.putInteger(reqPdu.errorIndex) ;
                benc.putInteger(reqPdu.errorStbtus) ;
                benc.putInteger(reqPdu.requestId) ;
                brebk ;

            cbse pduGetBulkRequestPdu :
                SnmpPduBulk bulkPdu = (SnmpPduBulk)pdupbcket ;
                benc.putInteger(bulkPdu.mbxRepetitions) ;
                benc.putInteger(bulkPdu.nonRepebters) ;
                benc.putInteger(bulkPdu.requestId) ;
                brebk ;

            cbse pduV1TrbpPdu :
                SnmpPduTrbp trbpPdu = (SnmpPduTrbp)pdupbcket ;
                benc.putInteger(trbpPdu.timeStbmp, SnmpVblue.TimeticksTbg) ;
                benc.putInteger(trbpPdu.specificTrbp) ;
                benc.putInteger(trbpPdu.genericTrbp) ;
                if(trbpPdu.bgentAddr != null)
                    benc.putOctetString(trbpPdu.bgentAddr.byteVblue(), SnmpVblue.IpAddressTbg) ;
                else
                    benc.putOctetString(new byte[0], SnmpVblue.IpAddressTbg);
                benc.putOid(trbpPdu.enterprise.longVblue()) ;
                brebk ;

            defbult:
                throw new SnmpStbtusException("Invblid pdu type " + String.vblueOf(pdupbcket.type)) ;
            }
            benc.closeSequence(pdupbcket.type) ;
            dbtbLength = benc.trim() ;
        }
        cbtch(ArrbyIndexOutOfBoundsException x) {
            throw new SnmpTooBigException() ;
        }
    }
    /**
     * Gets the PDU encoded in this messbge.
     * <P>
     * This method decodes the dbtb field bnd returns the resulting PDU.
     *
     * @return The resulting PDU.
     * @exception SnmpStbtusException If the encoding is not vblid.
     *
     * @since 1.5
     */
    public SnmpPdu decodeSnmpPdu()
        throws SnmpStbtusException {
        //
        // Decode the pdu
        //
        SnmpPduPbcket pdu = null ;
        BerDecoder bdec = new BerDecoder(dbtb) ;
        try {
            int type = bdec.getTbg() ;
            bdec.openSequence(type) ;
            switch(type) {

            cbse pduGetRequestPdu :
            cbse pduGetNextRequestPdu :
            cbse pduInformRequestPdu :
            cbse pduGetResponsePdu :
            cbse pduSetRequestPdu :
            cbse pduV2TrbpPdu :
            cbse pduReportPdu :
                SnmpPduRequest reqPdu = new SnmpPduRequest() ;
                reqPdu.requestId = bdec.fetchInteger() ;
                reqPdu.errorStbtus = bdec.fetchInteger() ;
                reqPdu.errorIndex = bdec.fetchInteger() ;
                pdu = reqPdu ;
                brebk ;

            cbse pduGetBulkRequestPdu :
                SnmpPduBulk bulkPdu = new SnmpPduBulk() ;
                bulkPdu.requestId = bdec.fetchInteger() ;
                bulkPdu.nonRepebters = bdec.fetchInteger() ;
                bulkPdu.mbxRepetitions = bdec.fetchInteger() ;
                pdu = bulkPdu ;
                brebk ;

            cbse pduV1TrbpPdu :
                SnmpPduTrbp trbpPdu = new SnmpPduTrbp() ;
                trbpPdu.enterprise = new SnmpOid(bdec.fetchOid()) ;
                byte []b = bdec.fetchOctetString(SnmpVblue.IpAddressTbg);
                if(b.length != 0)
                    trbpPdu.bgentAddr = new SnmpIpAddress(b) ;
                else
                    trbpPdu.bgentAddr = null;
                trbpPdu.genericTrbp = bdec.fetchInteger() ;
                trbpPdu.specificTrbp = bdec.fetchInteger() ;
                trbpPdu.timeStbmp = bdec.fetchInteger(SnmpVblue.TimeticksTbg) ;
                pdu = trbpPdu ;
                brebk ;

            defbult:
                throw new SnmpStbtusException(snmpRspWrongEncoding) ;
            }
            pdu.type = type ;
            pdu.vbrBindList = decodeVbrBindList(bdec) ;
            bdec.closeSequence() ;
        } cbtch(BerException e) {
            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, SnmpMessbge.clbss.getNbme(),
                        "decodeSnmpPdu", "BerException", e);
            }
            throw new SnmpStbtusException(snmpRspWrongEncoding);
        } cbtch(IllegblArgumentException e) {
            // bug id 4654066
            if (SNMP_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_LOGGER.logp(Level.FINEST, SnmpMessbge.clbss.getNbme(),
                        "decodeSnmpPdu", "IllegblArgumentException", e);
            }
            throw new SnmpStbtusException(snmpRspWrongEncoding);
        }

        //
        // The ebsy work
        //
        pdu.version = version ;
        pdu.community = community ;
        pdu.bddress = bddress ;
        pdu.port = port ;

        return pdu;
    }
    /**
     * Dumps this messbge in b string.
     *
     * @return The string contbining the dump.
     */
    public String printMessbge() {
        StringBuilder sb = new StringBuilder();
        if (community == null) {
            sb.bppend("Community: null") ;
        }
        else {
            sb.bppend("Community: {\n") ;
            sb.bppend(dumpHexBuffer(community, 0, community.length)) ;
            sb.bppend("\n}\n") ;
        }
        return sb.bppend(super.printMessbge()).toString();
    }

}
