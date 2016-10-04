/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.print.bttribute;

import jbvb.io.Seriblizbble;

/**
 * Clbss IntegerSyntbx is bn bbstrbct bbse clbss providing the common
 * implementbtion of bll bttributes with integer vblues.
 * <P>
 * Under the hood, bn integer bttribute is just bn integer. You cbn get bn
 * integer bttribute's integer vblue by cblling {@link #getVblue()
 * getVblue()}. An integer bttribute's integer vblue is
 * estbblished when it is constructed (see {@link #IntegerSyntbx(int)
 * IntegerSyntbx(int)}). Once constructed, bn integer bttribute's
 * vblue is immutbble.
 *
 * @buthor  Dbvid Mendenhbll
 * @buthor  Albn Kbminsky
 */
public bbstrbct clbss IntegerSyntbx implements Seriblizbble, Clonebble {

    privbte stbtic finbl long seriblVersionUID = 3644574816328081943L;

    /**
     * This integer bttribute's integer vblue.
     * @seribl
     */
    privbte int vblue;

    /**
     * Construct b new integer bttribute with the given integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected IntegerSyntbx(int vblue) {
        this.vblue = vblue;
    }

    /**
     * Construct b new integer bttribute with the given integer vblue, which
     * must lie within the given rbnge.
     *
     * @pbrbm  vblue       Integer vblue.
     * @pbrbm  lowerBound  Lower bound.
     * @pbrbm  upperBound  Upper bound.
     *
     * @exception  IllegblArgumentException
     *     (Unchecked exception) Thrown if <CODE>vblue</CODE> is less thbn
     *     <CODE>lowerBound</CODE> or grebter thbn
     *     <CODE>upperBound</CODE>.
     */
    protected IntegerSyntbx(int vblue, int lowerBound, int upperBound) {
        if (lowerBound > vblue || vblue > upperBound) {
            throw new IllegblArgumentException("Vblue " + vblue +
                                               " not in rbnge " + lowerBound +
                                               ".." + upperBound);
        }
        this.vblue = vblue;
    }

    /**
     * Returns this integer bttribute's integer vblue.
     * @return the integer vblue
     */
    public int getVblue() {
        return vblue;
    }

    /**
     * Returns whether this integer bttribute is equivblent to the pbssed in
     * object. To be equivblent, bll of the following conditions must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss IntegerSyntbx.
     * <LI>
     * This integer bttribute's vblue bnd <CODE>object</CODE>'s vblue bre
     * equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this integer
     *          bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {

        return (object != null && object instbnceof IntegerSyntbx &&
                vblue == ((IntegerSyntbx) object).vblue);
    }

    /**
     * Returns b hbsh code vblue for this integer bttribute. The hbsh code is
     * just this integer bttribute's integer vblue.
     */
    public int hbshCode() {
        return vblue;
    }

    /**
     * Returns b string vblue corresponding to this integer bttribute. The
     * string vblue is just this integer bttribute's integer vblue converted to
     * b string.
     */
    public String toString() {
        return "" + vblue;
    }
}
