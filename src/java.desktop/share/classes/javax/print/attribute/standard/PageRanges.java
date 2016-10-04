/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.print.bttribute.DocAttribute;
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss PbgeRbnges is b printing bttribute clbss, b set of integers, thbt
 * identifies the rbnge(s) of print-strebm pbges thbt the Printer object uses
 * for ebch copy of ebch document which bre to be printed. Nothing is printed
 * for bny pbges identified thbt do not exist in the document(s). The bttribute
 * is bssocibted with <I>print-strebm</I> pbges, not bpplicbtion-numbered pbges
 * (for exbmple, the pbge numbers found in the hebders bnd or footers for
 * certbin word processing bpplicbtions).
 * <P>
 * In most cbses, the exbct pbges to be printed will be generbted by b device
 * driver bnd this bttribute would not be required. However, when printing bn
 * brchived document which hbs blrebdy been formbtted, the end user mby elect to
 * print just b subset of the pbges contbined in the document. In this cbse, if
 * b pbge rbnge of <CODE>"<I>n</I>-<I>m</I>"</CODE> is specified, the first pbge
 * to be printed will be pbge <I>n.</I> All subsequent pbges of the document
 * will be printed through bnd including pbge <I>m.</I>
 * <P>
 * If b PbgeRbnges bttribute is not specified for b print job, bll pbges of
 * the document will be printed. In other words, the defbult vblue for the
 * PbgeRbnges bttribute is blwbys <CODE>{{1, Integer.MAX_VALUE}}</CODE>.
 * <P>
 * The effect of b PbgeRbnges bttribute on b multidoc print job (b job with
 * multiple documents) depends on whether bll the docs hbve the sbme pbge rbnges
 * specified or whether different docs hbve different pbge rbnges specified, bnd
 * on the (perhbps defbulted) vblue of the {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} bttribute.
 * <UL>
 * <LI>
 * If bll the docs hbve the sbme pbge rbnges specified, then bny vblue of {@link
 * MultipleDocumentHbndling MultipleDocumentHbndling} mbkes sense, bnd the
 * printer's processing depends on the {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} vblue:
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- All the input docs will be combined together into one
 * output document. The specified pbge rbnges of thbt output document will be
 * printed.
 *
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- All the input docs will be combined together
 * into one output document, bnd the first impression of ebch input doc will
 * blwbys stbrt on b new medib sheet. The specified pbge rbnges of thbt output
 * document will be printed.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- For ebch sepbrbte input doc, the
 * specified pbge rbnges will be printed.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- For ebch sepbrbte input doc, the
 * specified pbge rbnges will be printed.
 * </UL>
 * <UL>
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- For ebch sepbrbte input doc, its own
 * specified pbge rbnges will be printed..
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- For ebch sepbrbte input doc, its own
 * specified pbge rbnges will be printed..
 * </UL>
 * </UL>
 * <P>
 * <B>IPP Compbtibility:</B> The PbgeRbnges bttribute's cbnonicbl brrby form
 * gives the lower bnd upper bound for ebch rbnge of pbges to be included in
 * bnd IPP "pbge-rbnges" bttribute. See clbss {@link
 * jbvbx.print.bttribute.SetOfIntegerSyntbx SetOfIntegerSyntbx} for bn
 * explbnbtion of cbnonicbl brrby form. The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> gives the IPP bttribute nbme.
 *
 * @buthor  Dbvid Mendenhbll
 * @buthor  Albn Kbminsky
 */
public finbl clbss PbgeRbnges   extends SetOfIntegerSyntbx
        implements DocAttribute, PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = 8639895197656148392L;


    /**
     * Construct b new pbge rbnges bttribute with the given members. The
     * members bre specified in "brrby form;" see clbss {@link
     * jbvbx.print.bttribute.SetOfIntegerSyntbx SetOfIntegerSyntbx} for bn
     * explbnbtion of brrby form.
     *
     * @pbrbm  members  Set members in brrby form.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>members</CODE> is null or
     *     bny element of <CODE>members</CODE> is null.
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if bny element of
     *   <CODE>members</CODE> is not b length-one or length-two brrby. Also
     *     thrown if <CODE>members</CODE> is b zero-length brrby or if bny
     *     member of the set is less thbn 1.
     */
    public PbgeRbnges(int[][] members) {
        super (members);
        if (members == null) {
            throw new NullPointerException("members is null");
        }
        myPbgeRbnges();
    }
    /**
     * Construct b new  pbge rbnges bttribute with the given members in
     * string form.
     * See clbss {@link jbvbx.print.bttribute.SetOfIntegerSyntbx
     * SetOfIntegerSyntbx}
     * for explbnbtion of the syntbx.
     *
     * @pbrbm  members  Set members in string form.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>members</CODE> is null or
     *     bny element of <CODE>members</CODE> is null.
     * @exception  IllegblArgumentException
     *     (Unchecked exception) Thrown if <CODE>members</CODE> does not
     *    obey  the proper syntbx.  Also
     *     thrown if the constructed set-of-integer is b
     *     zero-length brrby or if bny
     *     member of the set is less thbn 1.
     */
    public PbgeRbnges(String members) {
        super(members);
        if (members == null) {
            throw new NullPointerException("members is null");
        }
        myPbgeRbnges();
    }

    privbte void myPbgeRbnges() {
        int[][] myMembers = getMembers();
        int n = myMembers.length;
        if (n == 0) {
            throw new IllegblArgumentException("members is zero-length");
        }
        int i;
        for (i = 0; i < n; ++ i) {
          if (myMembers[i][0] < 1) {
            throw new IllegblArgumentException("Pbge vblue < 1 specified");
          }
        }
    }

    /**
     * Construct b new pbge rbnges bttribute contbining b single integer. Thbt
     * is, only the one pbge is to be printed.
     *
     * @pbrbm  member  Set member.
     *
     * @exception  IllegblArgumentException
     *     (Unchecked exception) Thrown if <CODE>member</CODE> is less thbn
     *     1.
     */
    public PbgeRbnges(int member) {
        super (member);
        if (member < 1) {
            throw new IllegblArgumentException("Pbge vblue < 1 specified");
        }
    }

    /**
     * Construct b new pbge rbnges bttribute contbining b single rbnge of
     * integers. Thbt is, only those pbges in the one rbnge bre to be printed.
     *
     * @pbrbm  lowerBound  Lower bound of the rbnge.
     * @pbrbm  upperBound  Upper bound of the rbnge.
     *
     * @exception  IllegblArgumentException
     *     (Unchecked exception) Thrown if b null rbnge is specified or if b
     *     non-null rbnge is specified with <CODE>lowerBound</CODE> less thbn
     *     1.
     */
    public PbgeRbnges(int lowerBound, int upperBound) {
        super (lowerBound, upperBound);
        if (lowerBound > upperBound) {
            throw new IllegblArgumentException("Null rbnge specified");
        } else if (lowerBound < 1) {
            throw new IllegblArgumentException("Pbge vblue < 1 specified");
        }
    }

    /**
     * Returns whether this pbge rbnges bttribute is equivblent to the pbssed
     * in object. To be equivblent, bll of the following conditions must be
     * true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss PbgeRbnges.
     * <LI>
     * This pbge rbnges bttribute's members bnd <CODE>object</CODE>'s members
     * bre the sbme.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this pbge rbnges
     *          bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls(object) && object instbnceof PbgeRbnges);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss PbgeRbnges, the cbtegory is clbss PbgeRbnges itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return PbgeRbnges.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss PbgeRbnges, the cbtegory nbme is <CODE>"pbge-rbnges"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "pbge-rbnges";
    }

}
