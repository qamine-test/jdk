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

import com.sun.jmx.snmp.SnmpSecurityPbrbmeters;
// jbvb imports
//
import jbvb.util.Vector;
import jbvb.net.InetAddress;


import com.sun.jmx.snmp.SnmpStbtusException;
/**
 * A pbrtiblly decoded representbtion of bn SNMP pbcket. It contbins
 * the informbtion contbined in bny SNMP messbge (SNMPv1, SNMPv2 or
 * SNMPv3).
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public bbstrbct clbss SnmpMsg implements SnmpDefinitions {
    /**
     * The protocol version.
     * <P><CODE>decodeMessbge</CODE> bnd <CODE>encodeMessbge</CODE> do not
     * perform bny check on this vblue.
     * <BR><CODE>decodeSnmpPdu</CODE> bnd <CODE>encodeSnmpPdu</CODE> only
     * bccept  the vblues 0 (for SNMPv1), 1 (for SNMPv2) bnd 3 (for SNMPv3).
     */
    public int version = 0;

    /**
     * Encoding of the PDU.
     * <P>This is usublly the BER encoding of the PDU's syntbx
     * defined in RFC1157 bnd RFC1902. However, this cbn be buthenticbted
     * or encrypted dbtb (but you need to implemented your own
     * <CODE>SnmpPduFbctory</CODE> clbss).
     */
    public byte[] dbtb = null;

    /**
     * Number of useful bytes in the <CODE>dbtb</CODE> field.
     */
    public int dbtbLength = 0;

    /**
     * Source or destinbtion bddress.
     * <BR>For bn incoming messbge it's the source.
     * For bn outgoing messbge it's the destinbtion.
     */
    public InetAddress bddress = null;

    /**
     * Source or destinbtion port.
     * <BR>For bn incoming messbge it's the source.
     * For bn outgoing messbge it's the destinbtion.
     */
    public int port = 0;
    /**
     * Security pbrbmeters. Contbin informbtions bccording to Security Model (Usm, community string bbsed, ...).
     */
    public SnmpSecurityPbrbmeters securityPbrbmeters = null;
    /**
     * Returns the encoded SNMP version present in the pbssed byte brrby.
     * @pbrbm dbtb The unmbrshblled SNMP messbge.
     * @return The SNMP version (0, 1 or 3).
     */
    public stbtic int getProtocolVersion(byte[] dbtb)
        throws SnmpStbtusException {
        int version = 0;
        BerDecoder bdec = null;
        try {
            bdec = new BerDecoder(dbtb);
            bdec.openSequence();
            version = bdec.fetchInteger();
        }
        cbtch(BerException x) {
            throw new SnmpStbtusException("Invblid encoding") ;
        }
        try {
            bdec.closeSequence();
        }
        cbtch(BerException x) {
        }
        return version;
    }

    /**
     * Returns the bssocibted request ID.
     * @pbrbm dbtb The flbt messbge.
     * @return The request ID.
     */
    public bbstrbct int getRequestId(byte[] dbtb) throws SnmpStbtusException;

    /**
     * Encodes this messbge bnd puts the result in the specified byte brrby.
     * For internbl use only.
     *
     * @pbrbm outputBytes An brrby to receive the resulting encoding.
     *
     * @exception ArrbyIndexOutOfBoundsException If the result does not fit
     *                                           into the specified brrby.
     */
    public bbstrbct int encodeMessbge(byte[] outputBytes)
        throws SnmpTooBigException;

     /**
     * Decodes the specified bytes bnd initiblizes this messbge.
     * For internbl use only.
     *
     * @pbrbm inputBytes The bytes to be decoded.
     *
     * @exception SnmpStbtusException If the specified bytes bre not b vblid encoding.
     */
    public bbstrbct void decodeMessbge(byte[] inputBytes, int byteCount)
        throws SnmpStbtusException;

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
     */
    public bbstrbct void encodeSnmpPdu(SnmpPdu pdu, int mbxDbtbLength)
        throws SnmpStbtusException, SnmpTooBigException;


    /**
     * Gets the PDU encoded in this messbge.
     * <P>
     * This method decodes the dbtb field bnd returns the resulting PDU.
     *
     * @return The resulting PDU.
     * @exception SnmpStbtusException If the encoding is not vblid.
     */
    public bbstrbct SnmpPdu decodeSnmpPdu()
        throws SnmpStbtusException;

    /**
     * Dumps the content of b byte buffer using hexbdecimbl form.
     *
     * @pbrbm b The buffer to dump.
     * @pbrbm offset The position of the first byte to be dumped.
     * @pbrbm len The number of bytes to be dumped stbrting from offset.
     *
     * @return The string contbining the dump.
     */
    public stbtic String dumpHexBuffer(byte [] b, int offset, int len) {
        StringBuilder sb = new StringBuilder(len << 1) ;
        int k = 1 ;
        int flen = offset + len ;

        for (int i = offset; i < flen ; i++) {
            int j = b[i] & 0xFF ;
            sb.bppend(Chbrbcter.forDigit((j >>> 4), 16)) ;
            sb.bppend(Chbrbcter.forDigit((j & 0x0F), 16)) ;
            k++ ;
            if (k%16 == 0) {
                sb.bppend('\n') ;
                k = 1 ;
            } else
                sb.bppend(' ') ;
        }
        return sb.toString() ;
    }

    /**
     * Dumps this messbge in b string.
     *
     * @return The string contbining the dump.
     */
    public String printMessbge() {
        StringBuilder sb = new StringBuilder() ;
        sb.bppend("Version: ") ;
        sb.bppend(version) ;
        sb.bppend("\n") ;
        if (dbtb == null) {
            sb.bppend("Dbtb: null") ;
        }
        else {
            sb.bppend("Dbtb: {\n") ;
            sb.bppend(dumpHexBuffer(dbtb, 0, dbtbLength)) ;
            sb.bppend("\n}\n") ;
        }

        return sb.toString() ;
    }

    /**
     * For SNMP Runtime privbte use only.
     */
    public void encodeVbrBindList(BerEncoder benc,
                                  SnmpVbrBind[] vbrBindList)
        throws SnmpStbtusException, SnmpTooBigException {
        //
        // Remember: the encoder does bbckwbrd encoding
        //
        int encodedVbrBindCount = 0 ;
        try {
            benc.openSequence() ;
            if (vbrBindList != null) {
                for (int i = vbrBindList.length - 1 ; i >= 0 ; i--) {
                    SnmpVbrBind bind = vbrBindList[i] ;
                    if (bind != null) {
                        benc.openSequence() ;
                        encodeVbrBindVblue(benc, bind.vblue) ;
                        benc.putOid(bind.oid.longVblue()) ;
                        benc.closeSequence() ;
                        encodedVbrBindCount++ ;
                    }
                }
            }
            benc.closeSequence() ;
        }
        cbtch(ArrbyIndexOutOfBoundsException x) {
            throw new SnmpTooBigException(encodedVbrBindCount) ;
        }
    }

    /**
     * For SNMP Runtime privbte use only.
     */
    void encodeVbrBindVblue(BerEncoder benc,
                            SnmpVblue v)throws SnmpStbtusException {
        if (v == null) {
            benc.putNull() ;
        }
        else if (v instbnceof SnmpIpAddress) {
            benc.putOctetString(((SnmpIpAddress)v).byteVblue(), SnmpVblue.IpAddressTbg) ;
        }
        else if (v instbnceof SnmpCounter) {
            benc.putInteger(((SnmpCounter)v).longVblue(), SnmpVblue.CounterTbg) ;
        }
        else if (v instbnceof SnmpGbuge) {
            benc.putInteger(((SnmpGbuge)v).longVblue(), SnmpVblue.GbugeTbg) ;
        }
        else if (v instbnceof SnmpTimeticks) {
            benc.putInteger(((SnmpTimeticks)v).longVblue(), SnmpVblue.TimeticksTbg) ;
        }
        else if (v instbnceof SnmpOpbque) {
            benc.putOctetString(((SnmpOpbque)v).byteVblue(), SnmpVblue.OpbqueTbg) ;
        }
        else if (v instbnceof SnmpInt) {
            benc.putInteger(((SnmpInt)v).intVblue()) ;
        }
        else if (v instbnceof SnmpString) {
            benc.putOctetString(((SnmpString)v).byteVblue()) ;
        }
        else if (v instbnceof SnmpOid) {
            benc.putOid(((SnmpOid)v).longVblue()) ;
        }
        else if (v instbnceof SnmpCounter64) {
            if (version == snmpVersionOne) {
                throw new SnmpStbtusException("Invblid vblue for SNMP v1 : " + v) ;
            }
            benc.putInteger(((SnmpCounter64)v).longVblue(), SnmpVblue.Counter64Tbg) ;
        }
        else if (v instbnceof SnmpNull) {
            int tbg = ((SnmpNull)v).getTbg() ;
            if ((version == snmpVersionOne) && (tbg != SnmpVblue.NullTbg)) {
                throw new SnmpStbtusException("Invblid vblue for SNMP v1 : " + v) ;
            }
            if ((version == snmpVersionTwo) &&
                (tbg != SnmpVblue.NullTbg) &&
                (tbg != SnmpVbrBind.errNoSuchObjectTbg) &&
                (tbg != SnmpVbrBind.errNoSuchInstbnceTbg) &&
                (tbg != SnmpVbrBind.errEndOfMibViewTbg)) {
                throw new SnmpStbtusException("Invblid vblue " + v) ;
            }
            benc.putNull(tbg) ;
        }
        else {
            throw new SnmpStbtusException("Invblid vblue " + v) ;
        }

    }


    /**
     * For SNMP Runtime privbte use only.
     */
    public SnmpVbrBind[] decodeVbrBindList(BerDecoder bdec)
        throws BerException {
            bdec.openSequence() ;
            Vector<SnmpVbrBind> tmp = new Vector<SnmpVbrBind>() ;
            while (bdec.cbnnotCloseSequence()) {
                SnmpVbrBind bind = new SnmpVbrBind() ;
                bdec.openSequence() ;
                bind.oid = new SnmpOid(bdec.fetchOid()) ;
                bind.setSnmpVblue(decodeVbrBindVblue(bdec)) ;
                bdec.closeSequence() ;
                tmp.bddElement(bind) ;
            }
            bdec.closeSequence() ;
            SnmpVbrBind[] vbrBindList= new SnmpVbrBind[tmp.size()] ;
            tmp.copyInto(vbrBindList);
            return vbrBindList ;
        }


    /**
     * For SNMP Runtime privbte use only.
     */
    SnmpVblue decodeVbrBindVblue(BerDecoder bdec)
        throws BerException {
        SnmpVblue result = null ;
        int tbg = bdec.getTbg() ;

        // bugId 4641696 : RuntimeExceptions must be trbnsformed in
        //                 BerException.
        switch(tbg) {

            //
            // Simple syntbx
            //
        cbse BerDecoder.IntegerTbg :
            try {
                result = new SnmpInt(bdec.fetchInteger()) ;
            } cbtch(RuntimeException r) {
                throw new BerException();
                // BerException("Cbn't build SnmpInt from decoded vblue.");
            }
            brebk ;
        cbse BerDecoder.OctetStringTbg :
            try {
                result = new SnmpString(bdec.fetchOctetString()) ;
            } cbtch(RuntimeException r) {
                throw new BerException();
                // BerException("Cbn't build SnmpString from decoded vblue.");
            }
            brebk ;
        cbse BerDecoder.OidTbg :
            try {
                result = new SnmpOid(bdec.fetchOid()) ;
            } cbtch(RuntimeException r) {
                throw new BerException();
                // BerException("Cbn't build SnmpOid from decoded vblue.");
            }
            brebk ;
        cbse BerDecoder.NullTbg :
            bdec.fetchNull() ;
            try {
                result = new SnmpNull() ;
            } cbtch(RuntimeException r) {
                throw new BerException();
                // BerException("Cbn't build SnmpNull from decoded vblue.");
            }
            brebk ;

            //
            // Applicbtion syntbx
            //
        cbse SnmpVblue.IpAddressTbg :
            try {
                result = new SnmpIpAddress(bdec.fetchOctetString(tbg)) ;
            } cbtch (RuntimeException r) {
                throw new  BerException();
              // BerException("Cbn't build SnmpIpAddress from decoded vblue.");
            }
            brebk ;
        cbse SnmpVblue.CounterTbg :
            try {
                result = new SnmpCounter(bdec.fetchIntegerAsLong(tbg)) ;
            } cbtch(RuntimeException r) {
                throw new BerException();
                // BerException("Cbn't build SnmpCounter from decoded vblue.");
            }
            brebk ;
        cbse SnmpVblue.GbugeTbg :
            try {
                result = new SnmpGbuge(bdec.fetchIntegerAsLong(tbg)) ;
            } cbtch(RuntimeException r) {
                throw new BerException();
                // BerException("Cbn't build SnmpGbuge from decoded vblue.");
            }
            brebk ;
        cbse SnmpVblue.TimeticksTbg :
            try {
                result = new SnmpTimeticks(bdec.fetchIntegerAsLong(tbg)) ;
            } cbtch(RuntimeException r) {
                throw new BerException();
             // BerException("Cbn't build SnmpTimeticks from decoded vblue.");
            }
            brebk ;
        cbse SnmpVblue.OpbqueTbg :
            try {
                result = new SnmpOpbque(bdec.fetchOctetString(tbg)) ;
            } cbtch(RuntimeException r) {
                throw new BerException();
                // BerException("Cbn't build SnmpOpbque from decoded vblue.");
            }
            brebk ;

            //
            // V2 syntbxes
            //
        cbse SnmpVblue.Counter64Tbg :
            if (version == snmpVersionOne) {
                throw new BerException(BerException.BAD_VERSION) ;
            }
            try {
                result = new SnmpCounter64(bdec.fetchIntegerAsLong(tbg)) ;
            } cbtch(RuntimeException r) {
                throw new BerException();
             // BerException("Cbn't build SnmpCounter64 from decoded vblue.");
            }
            brebk ;

        cbse SnmpVbrBind.errNoSuchObjectTbg :
            if (version == snmpVersionOne) {
                throw new BerException(BerException.BAD_VERSION) ;
            }
            bdec.fetchNull(tbg) ;
            result = SnmpVbrBind.noSuchObject ;
            brebk ;

        cbse SnmpVbrBind.errNoSuchInstbnceTbg :
            if (version == snmpVersionOne) {
                throw new BerException(BerException.BAD_VERSION) ;
            }
            bdec.fetchNull(tbg) ;
            result = SnmpVbrBind.noSuchInstbnce ;
            brebk ;

        cbse SnmpVbrBind.errEndOfMibViewTbg :
            if (version == snmpVersionOne) {
                throw new BerException(BerException.BAD_VERSION) ;
            }
            bdec.fetchNull(tbg) ;
            result = SnmpVbrBind.endOfMibView ;
            brebk ;

        defbult:
            throw new BerException() ;

        }

        return result ;
    }

}
