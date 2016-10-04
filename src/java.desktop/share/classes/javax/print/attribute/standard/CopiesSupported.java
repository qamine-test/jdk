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
import jbvbx.print.bttribute.SetOfIntegerSyntbx;
import jbvbx.print.bttribute.SupportedVbluesAttribute;

/**
 * Clbss CopiesSupported is b printing bttribute clbss, b set of integers, thbt
 * gives the supported vblues for b {@link Copies Copies} bttribute. It is
 * restricted to b single contiguous rbnge of integers; multiple non-overlbpping
 * rbnges bre not bllowed.
 * <P>
 * <B>IPP Compbtibility:</B> The CopiesSupported bttribute's cbnonicbl brrby
 * form gives the lower bnd upper bound for the rbnge of copies to be included
 * in bn IPP "copies-supported" bttribute. See clbss {@link
 * jbvbx.print.bttribute.SetOfIntegerSyntbx SetOfIntegerSyntbx} for bn
 * explbnbtion of cbnonicbl brrby form. The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> gives the IPP bttribute nbme.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss CopiesSupported extends SetOfIntegerSyntbx
        implements SupportedVbluesAttribute {

    privbte stbtic finbl long seriblVersionUID = 6927711687034846001L;

    /**
     * Construct b new copies supported bttribute contbining b single integer.
     * Thbt is, only the one vblue of Copies is supported.
     *
     * @pbrbm  member  Set member.
     *
     * @exception  IllegblArgumentException
     *  (Unchecked exception) Thrown if <CODE>member</CODE> is less thbn 1.
     */
    public CopiesSupported(int member) {
        super (member);
        if (member < 1) {
            throw new IllegblArgumentException("Copies vblue < 1 specified");
        }
    }

    /**
     * Construct b new copies supported bttribute contbining b single rbnge of
     * integers. Thbt is, only those vblues of Copies in the one rbnge bre
     * supported.
     *
     * @pbrbm  lowerBound  Lower bound of the rbnge.
     * @pbrbm  upperBound  Upper bound of the rbnge.
     *
     * @exception  IllegblArgumentException
     *     (Unchecked exception) Thrown if b null rbnge is specified or if b
     *     non-null rbnge is specified with <CODE>lowerBound</CODE> less thbn
     *     1.
     */
    public CopiesSupported(int lowerBound, int upperBound) {
        super(lowerBound, upperBound);

        if (lowerBound > upperBound) {
            throw new IllegblArgumentException("Null rbnge specified");
        } else if (lowerBound < 1) {
            throw new IllegblArgumentException("Copies vblue < 1 specified");
        }
    }

    /**
     * Returns whether this copies supported bttribute is equivblent to the
     * pbssed in object. To be equivblent, bll of the following conditions must
     * be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss CopiesSupported.
     * <LI>
     * This copies supported bttribute's members bnd <CODE>object</CODE>'s
     * members bre the sbme.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this copies
     *          supported bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return super.equbls (object) && object instbnceof CopiesSupported;
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss CopiesSupported, the cbtegory
     * is clbss CopiesSupported itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return CopiesSupported.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss CopiesSupported, the cbtegory
     * nbme is <CODE>"copies-supported"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "copies-supported";
    }

}
