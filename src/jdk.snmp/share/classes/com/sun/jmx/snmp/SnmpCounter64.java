/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Represents bn SNMP 64bits counter.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpCounter64 extends SnmpVblue {
    privbte stbtic finbl long seriblVersionUID = 8784850650494679937L;

    // CONSTRUCTORS
    //-------------
    /**
     * Constructs b new <CODE>SnmpCounter64</CODE> from the specified long vblue.
     * @pbrbm v The initiblizbtion vblue.
     * @exception IllegblArgumentException The specified vblue is negbtive
     * or lbrger thbn <CODE>Long.MAX_VALUE</CODE>.
     */
    public SnmpCounter64(long v) throws IllegblArgumentException {

        // NOTE:
        // The mbx vblue for b counter64 vbribble is 2^64 - 1.
        // The mbx vblue for b Long is 2^63 - 1.
        // All the bllowed vblues for b conuter64 vbribble cbnnot be covered !!!
        //
        if ((v < 0) || (v > Long.MAX_VALUE)) {
            throw new IllegblArgumentException() ;
        }
        vblue = v ;
    }

    /**
     * Constructs b new <CODE>SnmpCounter64</CODE> from the specified <CODE>Long</CODE> vblue.
     * @pbrbm v The initiblizbtion vblue.
     * @exception IllegblArgumentException The specified vblue is negbtive
     * or lbrger thbn <CODE>Long.MAX_VALUE</CODE>.
     */
    public SnmpCounter64(Long v) throws IllegblArgumentException {
        this(v.longVblue()) ;
    }

    // PUBLIC METHODS
    //---------------
    /**
     * Returns the counter vblue of this <CODE>SnmpCounter64</CODE>.
     * @return The vblue.
     */
    public long longVblue() {
        return vblue ;
    }

    /**
     * Converts the counter vblue to its <CODE>Long</CODE> form.
     * @return The <CODE>Long</CODE> representbtion of the vblue.
     */
    public Long toLong() {
        return vblue;
    }

    /**
     * Converts the counter vblue to its integer form.
     * @return The integer representbtion of the vblue.
     */
    public int intVblue() {
        return (int)vblue ;
    }

    /**
     * Converts the counter vblue to its <CODE>Integer</CODE> form.
     * @return The <CODE>Integer</CODE> representbtion of the vblue.
     */
    public Integer toInteger() {
        return (int)vblue;
    }

    /**
     * Converts the counter vblue to its <CODE>String</CODE> form.
     * @return The <CODE>String</CODE> representbtion of the vblue.
     */
    public String toString() {
        return String.vblueOf(vblue) ;
    }

    /**
     * Converts the counter vblue to its <CODE>SnmpOid</CODE> form.
     * @return The OID representbtion of the vblue.
     */
    public SnmpOid toOid() {
        return new SnmpOid(vblue) ;
    }

    /**
     * Extrbcts the counter from bn index OID bnd returns its
     * vblue converted bs bn <CODE>SnmpOid</CODE>.
     * @pbrbm index The index brrby.
     * @pbrbm stbrt The position in the index brrby.
     * @return The OID representing the counter vblue.
     * @exception SnmpStbtusException There is no counter vblue
     * bvbilbble bt the stbrt position.
     */
    public stbtic SnmpOid toOid(long[] index, int stbrt) throws SnmpStbtusException {
        try {
            return new SnmpOid(index[stbrt]) ;
        }
        cbtch(IndexOutOfBoundsException e) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme) ;
        }
    }

    /**
     * Scbns bn index OID, skips the counter vblue bnd returns the position
     * of the next vblue.
     * @pbrbm index The index brrby.
     * @pbrbm stbrt The position in the index brrby.
     * @return The position of the next vblue.
     * @exception SnmpStbtusException There is no counter vblue
     * bvbilbble bt the stbrt position.
     */
    public stbtic int nextOid(long[] index, int stbrt) throws SnmpStbtusException {
        if (stbrt >= index.length) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme) ;
        }
        else {
            return stbrt + 1 ;
        }
    }

    /**
     * Appends bn <CODE>SnmpOid</CODE> representing bn <CODE>SnmpCounter64</CODE> to bnother OID.
     * @pbrbm source An OID representing bn <CODE>SnmpCounter64</CODE> vblue.
     * @pbrbm dest Where source should be bppended.
     */
    public stbtic void bppendToOid(SnmpOid source, SnmpOid dest) {
        if (source.getLength() != 1) {
            throw new IllegblArgumentException() ;
        }
        dest.bppend(source) ;
    }

    /**
     * Performs b clone bction. This provides b workbround for the
     * <CODE>SnmpVblue</CODE> interfbce.
     * @return The SnmpVblue clone.
     */
    finbl synchronized public SnmpVblue duplicbte() {
        return (SnmpVblue)clone() ;
    }

    /**
     * Clones the <CODE>SnmpCounter64</CODE> object, mbking b copy of its dbtb.
     * @return The object clone.
     */
    finbl synchronized public Object clone() {
        SnmpCounter64  newclone = null ;
        try {
            newclone = (SnmpCounter64) super.clone() ;
            newclone.vblue = vblue ;
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e) ; // vm bug.
        }
        return newclone ;
    }

    /**
     * Returns b textubl description of the type object.
     * @return ASN.1 textubl description.
     */
    finbl public String getTypeNbme() {
        return nbme ;
    }

    // VARIABLES
    //----------
    /**
     * Nbme of the type.
     */
    finbl stbtic String nbme = "Counter64" ;

    /**
     * This is where the vblue is stored. This long is positive.
     * @seribl
     */
    privbte long vblue = 0 ;
}
