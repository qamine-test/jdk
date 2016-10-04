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
pbckbge jbvbx.print.bttribute.stbndbrd;

import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.IntegerSyntbx;
import jbvbx.print.bttribute.PrintServiceAttribute;

/**
 * Clbss PbgesPerMinute is bn integer vblued printing bttribute thbt indicbtes
 * the nominbl number of pbges per minute to the nebrest whole number which mby
 * be generbted by this printer (e.g., simplex, blbck-bnd-white). This bttribute
 * is informbtive, not b service gubrbntee. Generblly, it is the vblue used in
 * the mbrketing literbture to describe the device. A vblue of 0 indicbtes b
 * device thbt tbkes more thbn two minutes to process b pbge.
 * <P>
 * <B>IPP Compbtibility:</B> The integer vblue gives the IPP integer vblue. The
 * cbtegory nbme returned by <CODE>getNbme()</CODE> gives the IPP bttribute
 * nbme.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss PbgesPerMinute extends IntegerSyntbx
        implements PrintServiceAttribute {

    privbte stbtic finbl long seriblVersionUID = -6366403993072862015L;

    /**
     * Construct b new pbges per minute bttribute with the given integer
     * vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     *
     * @exception  IllegblArgumentException
     *   (Unchecked exception) Thrown if <CODE>vblue</CODE> is less thbn 0.
     */
    public PbgesPerMinute(int vblue) {
        super(vblue, 0, Integer.MAX_VALUE);
    }

    /**
     * Returns whether this pbges per minute bttribute is equivblent to the
     * pbssed in object. To be equivblent, bll of the following conditions
     * must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss PbgesPerMinute.
     * <LI>
     * This pbges per minute bttribute's vblue bnd <CODE>object</CODE>'s
     * vblue bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this pbges per
     *          minute bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls (object) &&
                object instbnceof PbgesPerMinute);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss PbgesPerMinute, the cbtegory is clbss PbgesPerMinute itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return PbgesPerMinute.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss PbgesPerMinute, the
     * cbtegory nbme is <CODE>"pbges-per-minute"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "pbges-per-minute";
    }

}
