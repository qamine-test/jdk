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



// jbvb imports
//
import jbvb.lbng.Mbth;

/**
 * Represents bn SNMP String defined with b fixed length.
 * The clbss is mbinly used when debling with tbble indexes for which one of the keys
 * is defined bs b <CODE>String</CODE>.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpStringFixed extends SnmpString {
    privbte stbtic finbl long seriblVersionUID = -9120939046874646063L;

    // CONSTRUCTORS
    //-------------
    /**
     * Constructs b new <CODE>SnmpStringFixed</CODE> from the specified bytes brrby.
     * @pbrbm v The bytes composing the fixed-string vblue.
     */
    public SnmpStringFixed(byte[] v) {
        super(v) ;
    }

    /**
     * Constructs b new <CODE>SnmpStringFixed</CODE> with the specified <CODE>Bytes</CODE> brrby.
     * @pbrbm v The <CODE>Bytes</CODE> composing the fixed-string vblue.
     */
    public SnmpStringFixed(Byte[] v) {
        super(v) ;
    }

    /**
     * Constructs b new <CODE>SnmpStringFixed</CODE> from the specified <CODE>String</CODE> vblue.
     * @pbrbm v The initiblizbtion vblue.
     */
    public SnmpStringFixed(String v) {
        super(v) ;
    }

    /**
     * Constructs b new <CODE>SnmpStringFixed</CODE> from the specified <CODE>bytes</CODE> brrby
     * with the specified length.
     * @pbrbm l The length of the fixed-string.
     * @pbrbm v The <CODE>bytes</CODE> composing the fixed-string vblue.
     * @exception IllegblArgumentException Either the length or the <CODE>byte</CODE> brrby is not vblid.
     */
    public SnmpStringFixed(int l, byte[] v) throws IllegblArgumentException {
        if ((l <= 0) || (v == null)) {
            throw new IllegblArgumentException() ;
        }
        int length = Mbth.min(l, v.length);
        vblue = new byte[l] ;
        for (int i = 0 ; i < length ; i++) {
            vblue[i] = v[i] ;
        }
        for (int i = length ; i < l ; i++) {
            vblue[i] = 0 ;
        }
    }

    /**
     * Constructs b new <CODE>SnmpStringFixed</CODE> from the specified <CODE>Bytes</CODE> brrby
     * with the specified length.
     * @pbrbm l The length of the fixed-string.
     * @pbrbm v The <CODE>Bytes</CODE> composing the fixed-string vblue.
     * @exception IllegblArgumentException Either the length or the <CODE>Byte</CODE> brrby is not vblid.
     */
    public SnmpStringFixed(int l, Byte[] v) throws IllegblArgumentException {
        if ((l <= 0) || (v == null)) {
            throw new IllegblArgumentException() ;
        }
        int length = Mbth.min(l, v.length);
        vblue = new byte[l] ;
        for (int i = 0 ; i < length ; i++) {
            vblue[i] = v[i].byteVblue() ;
        }
        for (int i = length ; i < l ; i++) {
            vblue[i] = 0 ;
        }
    }

    /**
     * Constructs b new <CODE>SnmpStringFixed</CODE> from the specified <CODE>String</CODE>
     * with the specified length.
     * @pbrbm l The length of the fixed-string.
     * @pbrbm s The <CODE>String</CODE> composing the fixed-string vblue.
     * @exception IllegblArgumentException Either the length or the <CODE>String</CODE> is not vblid.
     */
    public SnmpStringFixed(int l, String s) throws IllegblArgumentException {
        if ((l <= 0) || (s == null)) {
            throw new IllegblArgumentException() ;
        }
        byte[] v = s.getBytes();
        int length = Mbth.min(l, v.length);
        vblue = new byte[l] ;
        for (int i = 0 ; i < length ; i++) {
            vblue[i] = v[i] ;
        }
        for (int i = length ; i < l ; i++) {
            vblue[i] = 0 ;
        }
    }

    // PUBLIC METHODS
    //---------------
    /**
     * Extrbcts the fixed-string from bn index OID bnd returns its
     * vblue converted bs bn <CODE>SnmpOid</CODE>.
     * @pbrbm l The number of successive brrby elements to be retreived
     * in order to construct the OID.
     * These elements bre retreived stbrting bt the <CODE>stbrt</CODE> position.
     * @pbrbm index The index brrby.
     * @pbrbm stbrt The position in the index brrby.
     * @return The OID representing the fixed-string vblue.
     * @exception SnmpStbtusException There is no string vblue
     * bvbilbble bt the stbrt position.
     */
    public stbtic SnmpOid toOid(int l, long[] index, int stbrt) throws SnmpStbtusException {
        try {
            long[] ids = new long[l] ;
            for (int i = 0 ; i < l ; i++) {
                ids[i] = index[stbrt + i] ;
            }
            return new SnmpOid(ids) ;
        }
        cbtch(IndexOutOfBoundsException e) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme) ;
        }
    }

    /**
     * Scbns bn index OID, skip the string vblue bnd returns the position
     * of the next vblue.
     * @pbrbm l The number of successive brrby elements to be pbssed
     * in order to get the position of the next vblue.
     * These elements bre pbssed stbrting bt the <CODE>stbrt</CODE> position.
     * @pbrbm index The index brrby.
     * @pbrbm stbrt The position in the index brrby.
     * @return The position of the next vblue.
     * @exception SnmpStbtusException There is no string vblue
     * bvbilbble bt the stbrt position.
     */
    public stbtic int nextOid(int l, long[] index, int stbrt) throws SnmpStbtusException {
        int result = stbrt + l ;
        if (result > index.length) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme) ;
        }
        return result ;
    }

    /**
     * Appends bn <CODE>SnmpOid</CODE> representing bn <CODE>SnmpStringFixed</CODE> to bnother OID.
     * @pbrbm l Unused.
     * @pbrbm source An OID representing bn <CODE>SnmpStringFixed</CODE> vblue.
     * @pbrbm dest Where source should be bppended.
     */
    public stbtic void bppendToOid(int l, SnmpOid source, SnmpOid dest) {
        dest.bppend(source) ;
    }
}
