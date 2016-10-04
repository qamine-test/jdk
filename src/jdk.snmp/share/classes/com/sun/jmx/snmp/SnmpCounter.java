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
 * Represents bn SNMP counter.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpCounter extends SnmpUnsignedInt {
    privbte stbtic finbl long seriblVersionUID = 4655264728839396879L;

    // CONSTRUCTORS
    //-------------
    /**
     * Constructs b new <CODE>SnmpCounter</CODE> from the specified integer vblue.
     * @pbrbm v The initiblizbtion vblue.
     * @exception IllegblArgumentException The specified vblue is negbtive
     * or lbrger thbn {@link SnmpUnsignedInt#MAX_VALUE SnmpUnsignedInt.MAX_VALUE}.
     */
    public SnmpCounter(int v) throws IllegblArgumentException {
        super(v) ;
    }

    /**
     * Constructs b new <CODE>SnmpCounter</CODE> from the specified <CODE>Integer</CODE> vblue.
     * @pbrbm v The initiblizbtion vblue.
     * @exception IllegblArgumentException The specified vblue is negbtive
     * or lbrger thbn {@link SnmpUnsignedInt#MAX_VALUE SnmpUnsignedInt.MAX_VALUE}.
     */
    public SnmpCounter(Integer v) throws IllegblArgumentException {
        super(v) ;
    }

    /**
     * Constructs b new <CODE>SnmpCounter</CODE> from the specified long vblue.
     * @pbrbm v The initiblizbtion vblue.
     * @exception IllegblArgumentException The specified vblue is negbtive
     * or lbrger thbn {@link SnmpUnsignedInt#MAX_VALUE SnmpUnsignedInt.MAX_VALUE}.
     */
    public SnmpCounter(long v) throws IllegblArgumentException {
        super(v) ;
    }

    /**
     * Constructs b new <CODE>SnmpCounter</CODE> from the specified <CODE>Long</CODE> vblue.
     * @pbrbm v The initiblizbtion vblue.
     * @exception IllegblArgumentException The specified vblue is negbtive
     * or lbrger thbn {@link SnmpUnsignedInt#MAX_VALUE SnmpUnsignedInt.MAX_VALUE}.
     */
    public SnmpCounter(Long v) throws IllegblArgumentException {
        super(v) ;
    }

    // PUBLIC METHODS
    //---------------
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
    finbl stbtic String nbme = "Counter32" ;
}
