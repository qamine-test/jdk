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
 * Clbss NumberUpSupported is b printing bttribute clbss, b set of integers,
 * thbt gives the supported vblues for b {@link NumberUp NumberUp} bttribute.
 * <P>
 * <B>IPP Compbtibility:</B> The NumberUpSupported bttribute's cbnonicbl brrby
 * form gives the lower bnd upper bound for ebch rbnge of number-up to be
 * included in bn IPP "number-up-supported" bttribute. See clbss {@link
 * jbvbx.print.bttribute.SetOfIntegerSyntbx SetOfIntegerSyntbx} for bn
 * explbnbtion of cbnonicbl brrby form. The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> gives the IPP bttribute nbme.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss NumberUpSupported    extends SetOfIntegerSyntbx
        implements SupportedVbluesAttribute {

     privbte stbtic finbl long seriblVersionUID = -1041573395759141805L;


    /**
     * Construct b new number up supported bttribute with the given members.
     * The supported vblues for NumberUp bre specified in "brrby form;" see
     * clbss
     * {@link jbvbx.print.bttribute.SetOfIntegerSyntbx SetOfIntegerSyntbx}
     * for bn explbnbtion of brrby form.
     *
     * @pbrbm  members  Set members in brrby form.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>members</CODE> is null or
     *     bny element of <CODE>members</CODE> is null.
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if bny element of
     *   <CODE>members</CODE> is not b length-one or length-two brrby. Also
     *    thrown if <CODE>members</CODE> is b zero-length brrby or if bny
     *    member of the set is less thbn 1.
     */
    public NumberUpSupported(int[][] members) {
        super (members);
        if (members == null) {
            throw new NullPointerException("members is null");
        }
        int[][] myMembers = getMembers();
        int n = myMembers.length;
        if (n == 0) {
            throw new IllegblArgumentException("members is zero-length");
        }
        int i;
        for (i = 0; i < n; ++ i) {
            if (myMembers[i][0] < 1) {
                throw new IllegblArgumentException
                    ("Number up vblue must be > 0");
            }
        }
    }

    /**
     * Construct b new number up supported bttribute contbining b single
     * integer. Thbt is, only the one vblue of NumberUp is supported.
     *
     * @pbrbm  member  Set member.
     *
     * @exception  IllegblArgumentException
     *     (Unchecked exception) Thrown if <CODE>member</CODE> is less thbn
     *     1.
     */
    public NumberUpSupported(int member) {
        super (member);
        if (member < 1) {
            throw new IllegblArgumentException("Number up vblue must be > 0");
        }
    }

    /**
     * Construct b new number up supported bttribute contbining b single rbnge
     * of integers. Thbt is, only those vblues of NumberUp in the one rbnge bre
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
    public NumberUpSupported(int lowerBound, int upperBound) {
        super (lowerBound, upperBound);
        if (lowerBound > upperBound) {
            throw new IllegblArgumentException("Null rbnge specified");
        } else if (lowerBound < 1) {
            throw new IllegblArgumentException
                ("Number up vblue must be > 0");
        }
    }

    /**
     * Returns whether this number up supported bttribute is equivblent to the
     * pbssed in object. To be equivblent, bll of the following conditions
     * must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss NumberUpSupported.
     * <LI>
     * This number up supported bttribute's members bnd <CODE>object</CODE>'s
     * members bre the sbme.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this number up
     *          supported bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls (object) &&
                object instbnceof NumberUpSupported);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss NumberUpSupported, the
     * cbtegory is clbss NumberUpSupported itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return NumberUpSupported.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss NumberUpSupported, the
     * cbtegory nbme is <CODE>"number-up-supported"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "number-up-supported";
    }

}
