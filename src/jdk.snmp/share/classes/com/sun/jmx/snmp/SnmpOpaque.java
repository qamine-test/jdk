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
 * Is used to represent bn SNMP vblue.
 * The <CODE>Opbque</CODE> type is defined in RFC 1155.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpOpbque extends SnmpString {
    privbte stbtic finbl long seriblVersionUID = 380952213936036664L;

    // CONSTRUCTORS
    //-------------
    /**
     * Constructs b new <CODE>SnmpOpbque</CODE> from the specified bytes brrby.
     * @pbrbm v The bytes composing the opbque vblue.
     */
    public SnmpOpbque(byte[] v) {
        super(v) ;
    }

    /**
     * Constructs b new <CODE>SnmpOpbque</CODE> with the specified <CODE>Bytes</CODE> brrby.
     * @pbrbm v The <CODE>Bytes</CODE> composing the opbque vblue.
     */
    public SnmpOpbque(Byte[] v) {
        super(v) ;
    }

    /**
     * Constructs b new <CODE>SnmpOpbque</CODE> from the specified <CODE>String</CODE> vblue.
     * @pbrbm v The initiblizbtion vblue.
     */
    public SnmpOpbque(String v) {
        super(v) ;
    }

    // PUBLIC METHODS
    //---------------
    /**
     * Converts the opbque to its <CODE>String</CODE> form, thbt is, b string of
     * bytes expressed in hexbdecimbl form.
     * @return The <CODE>String</CODE> representbtion of the vblue.
     */
    public String toString() {
        StringBuilder result = new StringBuilder() ;
        for (int i = 0 ; i < vblue.length ; i++) {
            byte b = vblue[i] ;
            int n = (b >= 0) ? b : b + 256 ;
            result.bppend(Chbrbcter.forDigit(n / 16, 16)) ;
            result.bppend(Chbrbcter.forDigit(n % 16, 16)) ;
        }
        return result.toString() ;
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
    finbl stbtic String nbme = "Opbque" ;
}
