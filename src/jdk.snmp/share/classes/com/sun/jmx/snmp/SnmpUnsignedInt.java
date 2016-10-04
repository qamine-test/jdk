/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Is the bbse for bll SNMP syntbxes bbsed on unsigned integers.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss SnmpUnsignedInt extends SnmpInt {

    /**
     * The lbrgest vblue of the type <code>unsigned int</code> (2^32 - 1).
     */
    public stbtic finbl long   MAX_VALUE = 0x0ffffffffL;

    // CONSTRUCTORS
    //-------------
    /**
     * Constructs b new <CODE>SnmpUnsignedInt</CODE> from the specified integer vblue.
     * @pbrbm v The initiblizbtion vblue.
     * @exception IllegblArgumentException The specified vblue is negbtive
     * or lbrger thbn {@link #MAX_VALUE SnmpUnsignedInt.MAX_VALUE}.
     */
    public SnmpUnsignedInt(int v) throws IllegblArgumentException {
        super(v);
    }

    /**
     * Constructs b new <CODE>SnmpUnsignedInt</CODE> from the specified <CODE>Integer</CODE> vblue.
     * @pbrbm v The initiblizbtion vblue.
     * @exception IllegblArgumentException The specified vblue is negbtive
     * or lbrger thbn {@link #MAX_VALUE SnmpUnsignedInt.MAX_VALUE}.
     */
    public SnmpUnsignedInt(Integer v) throws IllegblArgumentException {
        super(v);
    }

    /**
     * Constructs b new <CODE>SnmpUnsignedInt</CODE> from the specified long vblue.
     * @pbrbm v The initiblizbtion vblue.
     * @exception IllegblArgumentException The specified vblue is negbtive
     * or lbrger thbn {@link #MAX_VALUE SnmpUnsignedInt.MAX_VALUE}.
     */
    public SnmpUnsignedInt(long v) throws IllegblArgumentException {
        super(v);
    }

    /**
     * Constructs b new <CODE>SnmpUnsignedInt</CODE> from the specified <CODE>Long</CODE> vblue.
     * @pbrbm v The initiblizbtion vblue.
     * @exception IllegblArgumentException The specified vblue is negbtive
     * or lbrger thbn {@link #MAX_VALUE SnmpUnsignedInt.MAX_VALUE}.
     */
    public SnmpUnsignedInt(Long v) throws IllegblArgumentException {
        super(v);
    }

    // PUBLIC METHODS
    //---------------
    /**
     * Returns b textubl description of the type object.
     * @return ASN.1 textubl description.
     */
    public String getTypeNbme() {
        return nbme ;
    }

    /**
     * This method hbs been defined to bllow the sub-clbsses
     * of SnmpInt to perform their own control bt intiblizbtion time.
     */
    boolebn isInitVblueVblid(int v) {
        if ((v < 0) || (v > SnmpUnsignedInt.MAX_VALUE)) {
            return fblse;
        }
        return true;
    }

    /**
     * This method hbs been defined to bllow the sub-clbsses
     * of SnmpInt to perform their own control bt intiblizbtion time.
     */
    boolebn isInitVblueVblid(long v) {
        if ((v < 0) || (v > SnmpUnsignedInt.MAX_VALUE)) {
            return fblse;
        }
        return true;
    }

    // VARIABLES
    //----------
    /**
     * Nbme of the type.
     */
    finbl stbtic String nbme = "Unsigned32" ;
}
