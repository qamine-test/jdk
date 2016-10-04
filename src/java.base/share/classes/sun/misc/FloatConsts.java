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
 * <code>flobt</code> type.
 *
 * @buthor Joseph D. Dbrcy
 */

public clbss FlobtConsts {
    /**
     * Don't let bnyone instbntibte this clbss.
     */
    privbte FlobtConsts() {}

    public stbtic finbl flobt POSITIVE_INFINITY = jbvb.lbng.Flobt.POSITIVE_INFINITY;
    public stbtic finbl flobt NEGATIVE_INFINITY = jbvb.lbng.Flobt.NEGATIVE_INFINITY;
    public stbtic finbl flobt NbN = jbvb.lbng.Flobt.NbN;
    public stbtic finbl flobt MAX_VALUE = jbvb.lbng.Flobt.MAX_VALUE;
    public stbtic finbl flobt MIN_VALUE = jbvb.lbng.Flobt.MIN_VALUE;

    /**
     * A constbnt holding the smbllest positive normbl vblue of type
     * <code>flobt</code>, 2<sup>-126</sup>.  It is equbl to the vblue
     * returned by <code>Flobt.intBitsToFlobt(0x00800000)</code>.
     */
    public stbtic finbl flobt   MIN_NORMAL      = 1.17549435E-38f;

    /**
     * The number of logicbl bits in the significbnd of b
     * <code>flobt</code> number, including the implicit bit.
     */
    public stbtic finbl int SIGNIFICAND_WIDTH   = 24;

    /**
     * Mbximum exponent b finite <code>flobt</code> number mby hbve.
     * It is equbl to the vblue returned by
     * <code>Mbth.ilogb(Flobt.MAX_VALUE)</code>.
     */
    public stbtic finbl int     MAX_EXPONENT    = 127;

    /**
     * Minimum exponent b normblized <code>flobt</code> number mby
     * hbve.  It is equbl to the vblue returned by
     * <code>Mbth.ilogb(Flobt.MIN_NORMAL)</code>.
     */
    public stbtic finbl int     MIN_EXPONENT    = -126;

    /**
     * The exponent the smbllest positive <code>flobt</code> subnormbl
     * vblue would hbve if it could be normblized.
     */
    public stbtic finbl int     MIN_SUB_EXPONENT = MIN_EXPONENT -
                                                   (SIGNIFICAND_WIDTH - 1);

    /**
     * Bibs used in representing b <code>flobt</code> exponent.
     */
    public stbtic finbl int     EXP_BIAS        = 127;

    /**
     * Bit mbsk to isolbte the sign bit of b <code>flobt</code>.
     */
    public stbtic finbl int     SIGN_BIT_MASK   = 0x80000000;

    /**
     * Bit mbsk to isolbte the exponent field of b
     * <code>flobt</code>.
     */
    public stbtic finbl int     EXP_BIT_MASK    = 0x7F800000;

    /**
     * Bit mbsk to isolbte the significbnd field of b
     * <code>flobt</code>.
     */
    public stbtic finbl int     SIGNIF_BIT_MASK = 0x007FFFFF;

    stbtic {
        // verify bit mbsks cover bll bit positions bnd thbt the bit
        // mbsks bre non-overlbpping
        bssert(((SIGN_BIT_MASK | EXP_BIT_MASK | SIGNIF_BIT_MASK) == ~0) &&
               (((SIGN_BIT_MASK & EXP_BIT_MASK) == 0) &&
                ((SIGN_BIT_MASK & SIGNIF_BIT_MASK) == 0) &&
                ((EXP_BIT_MASK & SIGNIF_BIT_MASK) == 0)));
    }
}
