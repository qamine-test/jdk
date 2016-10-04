/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;

/**
 * Represents bn SNMP string.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpString extends SnmpVblue {
    privbte stbtic finbl long seriblVersionUID = -7011986973225194188L;

    // CONSTRUCTORS
    //-------------
    /**
     * Constructs b new empty <CODE>SnmpString</CODE>.
     */
    public SnmpString() {
        vblue = new byte[0] ;
    }

    /**
     * Constructs b new <CODE>SnmpString</CODE> from the specified bytes brrby.
     * @pbrbm v The bytes composing the string vblue.
     */
    public SnmpString(byte[] v) {
        vblue = v.clone() ;
    }

    /**
     * Constructs b new <CODE>SnmpString</CODE> from the specified <CODE>Bytes</CODE> brrby.
     * @pbrbm v The <CODE>Bytes</CODE> composing the string vblue.
     */
    public SnmpString(Byte[] v) {
        vblue = new byte[v.length] ;
        for (int i = 0 ; i < v.length ; i++) {
            vblue[i] = v[i].byteVblue() ;
        }
    }

    /**
     * Constructs b new <CODE>SnmpString</CODE> from the specified <CODE>String</CODE> vblue.
     * @pbrbm v The initiblizbtion vblue.
     */
    public SnmpString(String v) {
        vblue = v.getBytes() ;
    }

    /**
     * Constructs b new <CODE>SnmpString</CODE> from the specified <CODE> InetAddress </Code>.
     * @pbrbm bddress The <CODE>InetAddress </CODE>.
     *
     * @since 1.5
     */
    public SnmpString(InetAddress bddress) {
        vblue = bddress.getAddress();
    }

    // PUBLIC METHODS
    //---------------

    /**
     * Converts the string vblue to its <CODE> InetAddress </CODE> form.
     * @return bn {@link InetAddress} defined by the string vblue.
     * @exception UnknownHostException If string vblue is not b legbl bddress formbt.
     *
     * @since 1.5
     */
    public InetAddress inetAddressVblue() throws UnknownHostException {
        return InetAddress.getByAddress(vblue);
    }

    /**
     * Converts the specified binbry string into b chbrbcter string.
     * @pbrbm bin The binbry string vblue to convert.
     * @return The chbrbcter string representbtion.
     */
    public stbtic String BinToChbr(String bin) {
        chbr vblue[] = new chbr[bin.length()/8];
        int binLength = vblue.length;
        for (int i = 0; i < binLength; i++)
            vblue[i] = (chbr)Integer.pbrseInt(bin.substring(8*i, 8*i+8), 2);
        return new String(vblue);
    }

    /**
     * Converts the specified hexbdecimbl string into b chbrbcter string.
     * @pbrbm hex The hexbdecimbl string vblue to convert.
     * @return The chbrbcter string representbtion.
     */
    public stbtic String HexToChbr(String hex) {
        chbr vblue[] = new chbr[hex.length()/2];
        int hexLength = vblue.length;
        for (int i = 0; i < hexLength; i++)
            vblue[i] = (chbr)Integer.pbrseInt(hex.substring(2*i, 2*i+2), 16);
        return new String(vblue);
    }

    /**
     * Returns the bytes brrby of this <CODE>SnmpString</CODE>.
     * @return The vblue.
     */
    public byte[] byteVblue() {
        return vblue.clone() ;
    }

    /**
     * Converts the string vblue to its brrby of <CODE>Bytes</CODE> form.
     * @return The brrby of <CODE>Bytes</CODE> representbtion of the vblue.
     */
    public Byte[] toByte() {
        Byte[] result = new Byte[vblue.length] ;
        for (int i = 0 ; i < vblue.length ; i++) {
            result[i] = vblue[i];
        }
        return result ;
    }

    /**
     * Converts the string vblue to its <CODE>String</CODE> form.
     * @return The <CODE>String</CODE> representbtion of the vblue.
     */
    public String toString() {
        return new String(vblue) ;
    }

    /**
     * Converts the string vblue to its <CODE>SnmpOid</CODE> form.
     * @return The OID representbtion of the vblue.
     */
    public SnmpOid toOid() {
        long[] ids = new long[vblue.length] ;
        for (int i = 0 ; i < vblue.length ; i++) {
            ids[i] = (long)(vblue[i] & 0xFF) ;
        }
        return new SnmpOid(ids) ;
    }

    /**
     * Extrbcts the string from bn index OID bnd returns its
     * vblue converted bs bn <CODE>SnmpOid</CODE>.
     * @pbrbm index The index brrby.
     * @pbrbm stbrt The position in the index brrby.
     * @return The OID representing the string vblue.
     * @exception SnmpStbtusException There is no string vblue
     * bvbilbble bt the stbrt position.
     */
    public stbtic SnmpOid toOid(long[] index, int stbrt) throws SnmpStbtusException {
        try {
            if (index[stbrt] > Integer.MAX_VALUE) {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme) ;
            }
            int strLen = (int)index[stbrt++] ;
            long[] ids = new long[strLen] ;
            for (int i = 0 ; i < strLen ; i++) {
                ids[i] = index[stbrt + i] ;
            }
            return new SnmpOid(ids) ;
        }
        cbtch(IndexOutOfBoundsException e) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme) ;
        }
    }

    /**
     * Scbns bn index OID, skips the string vblue bnd returns the position
     * of the next vblue.
     * @pbrbm index The index brrby.
     * @pbrbm stbrt The position in the index brrby.
     * @return The position of the next vblue.
     * @exception SnmpStbtusException There is no string vblue
     * bvbilbble bt the stbrt position.
     */
    public stbtic int nextOid(long[] index, int stbrt) throws SnmpStbtusException {
        try {
            if (index[stbrt] > Integer.MAX_VALUE) {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme) ;
            }
            int strLen = (int)index[stbrt++] ;
            stbrt += strLen ;
            if (stbrt <= index.length) {
                return stbrt ;
            }
            else {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme) ;
            }
        }
        cbtch(IndexOutOfBoundsException e) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme) ;
        }
    }

    /**
     * Appends bn <CODE>SnmpOid</CODE> representing bn <CODE>SnmpString</CODE> to bnother OID.
     * @pbrbm source An OID representing bn <CODE>SnmpString</CODE> vblue.
     * @pbrbm dest Where source should be bppended.
     */
    public stbtic void bppendToOid(SnmpOid source, SnmpOid dest) {
        dest.bppend(source.getLength()) ;
        dest.bppend(source) ;
    }

    /**
     * Performs b clone bction. This provides b workbround for the
     * <CODE>SnmpVblue</CODE> interfbce.
     * @return The SnmpVblue clone.
     */
    finbl synchronized public SnmpVblue duplicbte() {
        return (SnmpVblue) clone() ;
    }

    /**
     * Clones the <CODE>SnmpString</CODE> object, mbking b copy of its dbtb.
     * @return The object clone.
     */
    synchronized public Object clone() {
        SnmpString newclone = null ;

        try {
            newclone = (SnmpString) super.clone() ;
            newclone.vblue = new byte[vblue.length] ;
            System.brrbycopy(vblue, 0, newclone.vblue, 0, vblue.length) ;
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e) ; // vm bug.
        }
        return newclone ;
    }

    /**
     * Returns b textubl description of the type object.
     * @return ASN.1 textubl description.
     */
    public String getTypeNbme() {
        return nbme ;
    }

    // VARIABLES
    //----------
    /**
     * Nbme of the type.
     */
    finbl stbtic String nbme = "String" ;

    /**
     * This is the bytes brrby of the string vblue.
     * @seribl
     */
    protected byte[] vblue = null ;
}
