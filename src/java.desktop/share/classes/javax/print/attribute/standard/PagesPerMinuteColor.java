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
 * Clbss PbgesPerMinuteColor is bn integer vblued printing bttribute thbt
 * indicbtes the nominbl number of pbges per minute to the nebrest whole number
 * which mby be generbted by this printer when printing color (e.g., simplex,
 * color). For purposes of this bttribute, "color" mebns the sbme bs for the
 * {@link ColorSupported ColorSupported} bttribute, nbmely, the device is
 * cbpbble of bny type of color printing bt bll, including highlight color bs
 * well bs full process color. This bttribute is informbtive, not b service
 * gubrbntee. Generblly, it is the vblue used in the mbrketing literbture to
 * describe the color cbpbbilities of this device. A vblue of 0 indicbtes b
 * device thbt tbkes more thbn two minutes to process b pbge. If b color device
 * hbs severbl color modes, it mby use the pbges-per- minute vblue for this
 * bttribute thbt corresponds to the mode thbt produces the highest number.
 * <P>
 * A blbck bnd white only printer must not include the PbgesPerMinuteColor
 * bttribute in its bttribute set or service registrbtion. If this bttribute is
 * present, then the {@link ColorSupported ColorSupported} printer description
 * bttribute must blso be present bnd hbve b vblue of SUPPORTED.
 * <P>
 * <B>IPP Compbtibility:</B> The integer vblue gives the IPP integer vblue. The
 * cbtegory nbme returned by <CODE>getNbme()</CODE> gives the IPP bttribute
 * nbme.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss PbgesPerMinuteColor extends IntegerSyntbx
        implements PrintServiceAttribute {

    stbtic finbl long seriblVersionUID = 1684993151687470944L;

    /**
     * Construct b new pbges per minute color bttribute with the given integer
     * vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     *
     * @exception  IllegblArgumentException
     *    (Unchecked exception) Thrown if <CODE>vblue</CODE> is less thbn 0.
     */
    public PbgesPerMinuteColor(int vblue) {
        super(vblue, 0, Integer.MAX_VALUE);
    }

    /**
     * Returns whether this pbges per minute color bttribute is equivblent to
     * the pbssed in object. To be equivblent, bll of the following conditions
     * must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss PbgesPerMinuteColor.
     * <LI>
     * This pbges per minute bttribute's vblue bnd <CODE>object</CODE>'s
     * vblue bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this pbges per
     *          minute color bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls(object) &&
                object instbnceof PbgesPerMinuteColor);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss PbgesPerMinuteColor, the
     * cbtegory is clbss PbgesPerMinuteColor itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return PbgesPerMinuteColor.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss PbgesPerMinuteColor, the
     * cbtegory nbme is <CODE>"pbges-per-minute-color"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "pbges-per-minute-color";
    }

}
