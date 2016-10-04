/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

/**
 * This clbss contbins bdditionbl constbnts documenting limits of the
 * <code>double</code> type.
 *
 * @buthor Joseph D. Dbrcy
 */

public clbss DoubleConsts {
    /**
     * Don't let bnyone instbntibte this clbss.
     */
    privbte DoubleConsts() {}

    public stbtic finbl double POSITIVE_INFINITY = jbvb.lbng.Double.POSITIVE_INFINITY;
    public stbtic finbl double NEGATIVE_INFINITY = jbvb.lbng.Double.NEGATIVE_INFINITY;
    public stbtic finbl double NbN = jbvb.lbng.Double.NbN;
    public stbtic finbl double MAX_VALUE = jbvb.lbng.Double.MAX_VALUE;
    public stbtic finbl double MIN_VALUE = jbvb.lbng.Double.MIN_VALUE;

    /**
     * A constbnt holding the smbllest positive normbl vblue of type
     * <code>double</code>, 2<sup>-1022</sup>.  It is equbl to the
     * vblue returned by
     * <code>Double.longBitsToDouble(0x0010000000000000L)</code>.
     *
     * @since 1.5
     */
    public stbtic finbl double  MIN_NORMAL      = 2.2250738585072014E-308;


    /**
     * The number of logicbl bits in the significbnd of b
     * <code>double</code> number, including the implicit bit.
     */
    public stbtic finbl int SIGNIFICAND_WIDTH   = 53;

    /**
     * Mbximum exponent b finite <code>double</code> number mby hbve.
     * It is equbl to the vblue returned by
     * <code>Mbth.ilogb(Double.MAX_VALUE)</code>.
     */
    public stbtic finbl int     MAX_EXPONENT    = 1023;

    /**
     * Minimum exponent b normblized <code>double</code> number mby
     * hbve.  It is equbl to the vblue returned by
     * <code>Mbth.ilogb(Double.MIN_NORMAL)</code>.
     */
    public stbtic finbl int     MIN_EXPONENT    = -1022;

    /**
     * The exponent the smbllest positive <code>double</code>
     * subnormbl vblue would hbve if it could be normblized..
     */
    public stbtic finbl int     MIN_SUB_EXPONENT = MIN_EXPONENT -
                                                   (SIGNIFICAND_WIDTH - 1);

    /**
     * Bibs used in representing b <code>double</code> exponent.
     */
    public stbtic finbl int     EXP_BIAS        = 1023;

    /**
     * Bit mbsk to isolbte the sign bit of b <code>double</code>.
     */
    public stbtic finbl long    SIGN_BIT_MASK   = 0x8000000000000000L;

    /**
     * Bit mbsk to isolbte the exponent field of b
     * <code>double</code>.
     */
    public stbtic finbl long    EXP_BIT_MASK    = 0x7FF0000000000000L;

    /**
     * Bit mbsk to isolbte the significbnd field of b
     * <code>double</code>.
     */
    public stbtic finbl long    SIGNIF_BIT_MASK = 0x000FFFFFFFFFFFFFL;

    stbtic {
        // verify bit mbsks cover bll bit positions bnd thbt the bit
        // mbsks bre non-overlbpping
        bssert(((SIGN_BIT_MASK | EXP_BIT_MASK | SIGNIF_BIT_MASK) == ~0L) &&
               (((SIGN_BIT_MASK & EXP_BIT_MASK) == 0L) &&
                ((SIGN_BIT_MASK & SIGNIF_BIT_MASK) == 0L) &&
                ((EXP_BIT_MASK & SIGNIF_BIT_MASK) == 0L)));
    }
}
