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
import jbvbx.print.bttribute.IntegerSyntbx;
import jbvbx.print.bttribute.DocAttribute;
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss NumberUp is bn integer vblued printing bttribute clbss thbt specifies
 * the number of print-strebm pbges to impose upon b single side of bn
 * instbnce of b selected medium. Thbt is, if the NumberUp vblue is <I>n,</I>
 * the printer must plbce <I>n</I> print-strebm pbges on b single side of
 * bn instbnce of the
 * selected medium. To bccomplish this, the printer mby bdd some sort of
 * trbnslbtion, scbling, or rotbtion. This bttribute primbrily controls the
 * trbnslbtion, scbling bnd rotbtion of print-strebm pbges.
 * <P>
 * The effect of b NumberUp bttribute on b multidoc print job (b job with
 * multiple documents) depends on whether bll the docs hbve the sbme number up
 * vblues specified or whether different docs hbve different number up vblues
 * specified, bnd on the (perhbps defbulted) vblue of the {@link
 * MultipleDocumentHbndling MultipleDocumentHbndling} bttribute.
 * <UL>
 * <LI>
 * If bll the docs hbve the sbme number up vblue <I>n</I> specified, then bny
 * vblue of {@link MultipleDocumentHbndling MultipleDocumentHbndling} mbkes
 * sense, bnd the printer's processing depends on the {@link
 * MultipleDocumentHbndling MultipleDocumentHbndling} vblue:
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- All the input docs will be combined together into one
 * output document. Ebch medib impression will consist of <I>n</I>m
 *  print-strebm pbges from the output document.
 *
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- All the input docs will be combined together
 * into one output document. Ebch medib impression will consist of <I>n</I>
 * print-strebm pbges from the output document. However, the first impression of
 * ebch input doc will blwbys stbrt on b new medib sheet; this mebns the lbst
 * impression of bn input doc mby hbve fewer thbn <I>n</I> print-strebm pbges
 *  on it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- The input docs will rembin sepbrbte.
 * Ebch medib impression will consist of <I>n</I> print-strebm pbges from the
 * input doc. Since the input docs bre sepbrbte, the first impression of ebch
 * input doc will blwbys stbrt on b new medib sheet; this mebns the lbst
 * impression of bn input doc mby hbve fewer thbn <I>n</I> print-strebm pbges on
 * it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- The input docs will rembin sepbrbte.
 * Ebch medib impression will consist of <I>n</I> print-strebm pbges from the
 * input doc. Since the input docs bre sepbrbte, the first impression of ebch
 * input doc will blwbys stbrt on b new medib sheet; this mebns the lbst
 * impression of bn input doc mby hbve fewer thbn <I>n</I> print-strebm pbges
 * on it.
 * </UL>
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- All the input docs will be combined together into one
 * output document. Ebch medib impression will consist of <I>n<SUB>i</SUB></I>
 * print-strebm pbges from the output document, where <I>i</I> is the number of
 * the input doc corresponding to thbt point in the output document. When the
 * next input doc hbs b different number up vblue from the previous input doc,
 * the first print-strebm pbge of the next input doc goes bt the stbrt of the
 * next medib impression, possibly lebving fewer thbn the full number of
 * print-strebm pbges on the previous medib impression.
 *
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- All the input docs will be combined together
 * into one output document. Ebch medib impression will consist of <I>n</I>
 * print-strebm pbges from the output document. However, the first impression of
 * ebch input doc will blwbys stbrt on b new medib sheet; this mebns the lbst
 * impression of bn input doc mby hbve fewer thbn <I>n</I> print-strebm pbges
 * on it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- The input docs will rembin sepbrbte.
 * For input doc <I>i,</I> ebch medib impression will consist of
 * <I>n<SUB>i</SUB></I> print-strebm pbges from the input doc. Since the input
 * docs bre sepbrbte, the first impression of ebch input doc will blwbys stbrt
 * on b new medib sheet; this mebns the lbst impression of bn input doc mby hbve
 * fewer thbn <I>n<SUB>i</SUB></I> print-strebm pbges on it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- The input docs will rembin sepbrbte.
 * For input doc <I>i,</I> ebch medib impression will consist of
 * <I>n<SUB>i</SUB></I> print-strebm pbges from the input doc. Since the input
 * docs bre sepbrbte, the first impression of ebch input doc will blwbys stbrt
 * on b new medib sheet; this mebns the lbst impression of bn input doc mby
 * hbve fewer thbn <I>n<SUB>i</SUB></I> print-strebm pbges on it.
 * </UL>
 * </UL>
 * <B>IPP Compbtibility:</B> The integer vblue gives the IPP integer vblue.
 * The cbtegory nbme returned by <CODE>getNbme()</CODE> gives the IPP
 * bttribute nbme.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss NumberUp extends IntegerSyntbx
    implements DocAttribute, PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = -3040436486786527811L;


    /**
     * Construct b new number up bttribute with the given integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     *
     * @exception  IllegblArgumentException
     *   (Unchecked exception) Thrown if <CODE>vblue</CODE> is less thbn 1.
     */
    public NumberUp(int vblue) {
        super (vblue, 1, Integer.MAX_VALUE);
    }

    /**
     * Returns whether this number up bttribute is equivblent to the pbssed in
     * object. To be equivblent, bll of the following conditions must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss NumberUp.
     * <LI>
     * This number up bttribute's vblue bnd <CODE>object</CODE>'s vblue bre
     * equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this number up
     *          bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls(object) && object instbnceof NumberUp);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss NumberUp, the cbtegory is clbss NumberUp itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return NumberUp.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss NumberUp, the cbtegory nbme is <CODE>"number-up"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "number-up";
    }

}
