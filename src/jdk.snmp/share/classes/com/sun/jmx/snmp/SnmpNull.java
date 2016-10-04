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
 * Represents bn SNMP null vblue.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpNull extends SnmpVblue {
    privbte stbtic finbl long seriblVersionUID = 1783782515994279177L;

    // CONSTRUCTORS
    //-------------
    /**
     * Constructs b new <CODE>SnmpNull</CODE>.
     */
    public SnmpNull() {
        tbg = NullTbg ;
    }

    /**
     * Constructs b new <CODE>SnmpNull</CODE>.
     * <BR>For mibgen privbte use only.
     */
    public SnmpNull(String dummy) {
        this();
    }

    /**
     * Constructs b new <CODE>SnmpNull</CODE> from the specified tbg vblue.
     * @pbrbm t The initiblizbtion vblue.
     */
    public SnmpNull(int t) {
        tbg = t ;
    }

    // PUBLIC METHODS
    //---------------
    /**
     * Returns the tbg vblue of this <CODE>SnmpNull</CODE>.
     * @return The vblue.
     */
    public int getTbg() {
        return tbg ;
    }

    /**
     * Converts the <CODE>NULL</CODE> vblue to its ASN.1 <CODE>String</CODE> form.
     * When the tbg is not the universbl one, it is preprended
     * to the <CODE>String</CODE> form.
     * @return The <CODE>String</CODE> representbtion of the vblue.
     */
    public String toString() {
        String result = "" ;
        if (tbg != 5) {
            result += "[" + tbg + "] " ;
        }
        result += "NULL" ;
        switch(tbg) {
        cbse errNoSuchObjectTbg :
            result += " (noSuchObject)" ;
            brebk ;

        cbse errNoSuchInstbnceTbg :
            result += " (noSuchInstbnce)" ;
            brebk ;

        cbse errEndOfMibViewTbg :
            result += " (endOfMibView)" ;
            brebk ;
        }
        return result ;
    }

    /**
     * Converts the <CODE>NULL</CODE> vblue to its <CODE>SnmpOid</CODE> form.
     * Normblly, b <CODE>NULL</CODE> vblue cbnnot be used bs bn index vblue,
     * this method triggers bn exception.
     * @return The OID representbtion of the vblue.
     */
    public SnmpOid toOid() {
        throw new IllegblArgumentException() ;
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
     * Clones the <CODE>SnmpNull</CODE> object, mbking b copy of its dbtb.
     * @return The object clone.
     */
    finbl synchronized public Object clone() {
        SnmpNull  newclone = null ;
        try {
            newclone = (SnmpNull) super.clone() ;
            newclone.tbg = tbg ;
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

    /**
     * Checks if this <CODE>SnmpNull</CODE> object corresponds to b <CODE>noSuchObject</CODE> vblue.
     * @return <CODE>true</CODE> if the tbg equbls {@link com.sun.jmx.snmp.SnmpDbtbTypeEnums#errNoSuchObjectTbg},
     * <CODE>fblse</CODE> otherwise.
     */
    public boolebn isNoSuchObjectVblue() {
        return (tbg == SnmpDbtbTypeEnums.errNoSuchObjectTbg);
    }

    /**
     * Checks if this <CODE>SnmpNull</CODE> object corresponds to b <CODE>noSuchInstbnce</CODE> vblue.
     * @return <CODE>true</CODE> if the tbg equbls {@link com.sun.jmx.snmp.SnmpDbtbTypeEnums#errNoSuchInstbnceTbg},
     * <CODE>fblse</CODE> otherwise.
     */
    public boolebn isNoSuchInstbnceVblue() {
        return (tbg == SnmpDbtbTypeEnums.errNoSuchInstbnceTbg);
    }

    /**
     * Checks if this <CODE>SnmpNull</CODE> object corresponds to bn <CODE>endOfMibView</CODE> vblue.
     * @return <CODE>true</CODE> if the tbg equbls {@link com.sun.jmx.snmp.SnmpDbtbTypeEnums#errEndOfMibViewTbg},
     * <CODE>fblse</CODE> otherwise.
     */
    public boolebn isEndOfMibViewVblue() {
        return (tbg == SnmpDbtbTypeEnums.errEndOfMibViewTbg);
    }

    // VARIABLES
    //----------
    /**
     * Nbme of the type.
     */
    finbl stbtic String nbme = "Null" ;

    /**
     * This is the tbg of the NULL vblue. By defbult, it is the universbl tbg vblue.
     */
    privbte int tbg = 5 ;
}
