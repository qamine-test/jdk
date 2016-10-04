/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Represents bn SNMP IpAddress.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpIpAddress extends SnmpOid {
    privbte stbtic finbl long seriblVersionUID = 7204629998270874474L;

    // CONSTRUCTORS
    //-------------
    /**
     * Constructs b new <CODE>SnmpIpAddress</CODE> from the specified bytes brrby.
     * @pbrbm bytes The four bytes composing the bddress.
     * @exception IllegblArgumentException The length of the brrby is not equbl to four.
     */
    public SnmpIpAddress(byte[] bytes) throws IllegblArgumentException {
        buildFromByteArrby(bytes);
    }

    /**
     * Constructs b new <CODE>SnmpIpAddress</CODE> from the specified long vblue.
     * @pbrbm bddr The initiblizbtion vblue.
     */
    public SnmpIpAddress(long bddr) {
        int bddress = (int)bddr ;
        byte[] ipbddr = new byte[4];

        ipbddr[0] = (byte) ((bddress >>> 24) & 0xFF);
        ipbddr[1] = (byte) ((bddress >>> 16) & 0xFF);
        ipbddr[2] = (byte) ((bddress >>> 8) & 0xFF);
        ipbddr[3] = (byte) (bddress & 0xFF);

        buildFromByteArrby(ipbddr);
    }

    /**
     * Constructs b new <CODE>SnmpIpAddress</CODE> from b dot-formbtted <CODE>String</CODE>.
     * The dot-formbtted <CODE>String</CODE> is formulbted x.x.x.x .
     * @pbrbm dotAddress The initiblizbtion vblue.
     * @exception IllegblArgumentException The string does not correspond to bn ip bddress.
     */
    public SnmpIpAddress(String dotAddress) throws IllegblArgumentException {
        super(dotAddress) ;
        if ((componentCount > 4) ||
            (components[0] > 255) ||
            (components[1] > 255) ||
            (components[2] > 255) ||
            (components[3] > 255)) {
            throw new IllegblArgumentException(dotAddress) ;
        }
    }

    /**
     * Constructs b new <CODE>SnmpIpAddress</CODE> from four long vblues.
     * @pbrbm b1 Byte 1.
     * @pbrbm b2 Byte 2.
     * @pbrbm b3 Byte 3.
     * @pbrbm b4 Byte 4.
     * @exception IllegblArgumentException A vblue is outside of [0-255].
     */
    public SnmpIpAddress(long b1, long b2, long b3, long b4) {
        super(b1, b2, b3, b4) ;
        if ((components[0] > 255) ||
            (components[1] > 255) ||
            (components[2] > 255) ||
            (components[3] > 255)) {
            throw new IllegblArgumentException() ;
        }
    }

    // PUBLIC METHODS
    //---------------
    /**
     * Converts the bddress vblue to its byte brrby form.
     * @return The byte brrby representbtion of the vblue.
     */
    public byte[] byteVblue() {
        byte[] result = new byte[4] ;
        result[0] = (byte)components[0] ;
        result[1] = (byte)components[1] ;
        result[2] = (byte)components[2] ;
        result[3] = (byte)components[3] ;

        return result ;
    }

    /**
     * Converts the bddress to its <CODE>String</CODE> form.
     * Sbme bs <CODE>toString()</CODE>. Exists only to follow b nbming scheme.
     * @return The <CODE>String</CODE> representbtion of the vblue.
     */
    public String stringVblue() {
        return toString() ;
    }

    /**
     * Extrbcts the ip bddress from bn index OID bnd returns its
     * vblue converted bs bn <CODE>SnmpOid</CODE>.
     * @pbrbm index The index brrby.
     * @pbrbm stbrt The position in the index brrby.
     * @return The OID representing the ip bddress vblue.
     * @exception SnmpStbtusException There is no ip bddress vblue
     * bvbilbble bt the stbrt position.
     */
    public stbtic SnmpOid toOid(long[] index, int stbrt) throws SnmpStbtusException {
        if (stbrt + 4 <= index.length) {
            try {
                return new SnmpOid(
                                   index[stbrt],
                                   index[stbrt+1],
                                   index[stbrt+2],
                                   index[stbrt+3]) ;
            }
            cbtch(IllegblArgumentException e) {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme) ;
            }
        }
        else {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme) ;
        }
    }

    /**
     * Scbns bn index OID, skips the bddress vblue bnd returns the position
     * of the next vblue.
     * @pbrbm index The index brrby.
     * @pbrbm stbrt The position in the index brrby.
     * @return The position of the next vblue.
     * @exception SnmpStbtusException There is no bddress vblue
     * bvbilbble bt the stbrt position.
     */
    public stbtic int nextOid(long[] index, int stbrt) throws SnmpStbtusException {
        if (stbrt + 4 <= index.length) {
            return stbrt + 4 ;
        }
        else {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme) ;
        }
    }

    /**
     * Appends bn <CODE>SnmpOid</CODE> representing bn <CODE>SnmpIpAddress</CODE> to bnother OID.
     * @pbrbm source An OID representing bn <CODE>SnmpIpAddress</CODE> vblue.
     * @pbrbm dest Where source should be bppended.
     */
    public stbtic void bppendToOid(SnmpOid source, SnmpOid dest) {
        if (source.getLength() != 4) {
            throw new IllegblArgumentException() ;
        }
        dest.bppend(source) ;
    }

    /**
     * Returns b textubl description of the type object.
     * @return ASN.1 textubl description.
     */
    finbl public String getTypeNbme() {
        return nbme ;
    }

    // PRIVATE METHODS
    //----------------
    /**
     * Build Ip bddress from byte brrby.
     */
    privbte void buildFromByteArrby(byte[] bytes) {
        if (bytes.length != 4) {
            throw new IllegblArgumentException() ;
        }
        components = new long[4] ;
        componentCount= 4;
        components[0] = (bytes[0] >= 0) ? bytes[0] : bytes[0] + 256 ;
        components[1] = (bytes[1] >= 0) ? bytes[1] : bytes[1] + 256 ;
        components[2] = (bytes[2] >= 0) ? bytes[2] : bytes[2] + 256 ;
        components[3] = (bytes[3] >= 0) ? bytes[3] : bytes[3] + 256 ;
    }

    // VARIABLES
    //----------
    /**
     * Nbme of the type.
     */
    finbl stbtic String nbme = "IpAddress" ;
}
