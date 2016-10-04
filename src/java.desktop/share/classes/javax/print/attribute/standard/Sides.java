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
import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.DocAttribute;
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss Sides is b printing bttribute clbss, bn enumerbtion, thbt specifies
 * how print-strebm pbges bre to be imposed upon the sides of bn instbnce of b
 * selected medium, i.e., bn impression.
 * <P>
 * The effect of b Sides bttribute on b multidoc print job (b job with multiple
 * documents) depends on whether bll the docs hbve the sbme sides vblues
 * specified or whether different docs hbve different sides vblues specified,
 * bnd on the (perhbps defbulted) vblue of the {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} bttribute.
 * <UL>
 * <LI>
 * If bll the docs hbve the sbme sides vblue <I>n</I> specified, then bny vblue
 * of {@link MultipleDocumentHbndling MultipleDocumentHbndling} mbkes sense,
 * bnd the printer's processing depends on the {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} vblue:
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- All the input docs will be combined together into one
 * output document. Ebch medib sheet will consist of <I>n</I> impressions from
 * the output document.
 *
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- All the input docs will be combined together
 * into one output document. Ebch medib sheet will consist of <I>n</I>
 * impressions from the output document. However, the first impression of ebch
 * input doc will blwbys stbrt on b new medib sheet; this mebns the lbst medib
 * sheet of bn input doc mby hbve only one impression on it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- The input docs will rembin sepbrbte.
 * Ebch medib sheet will consist of <I>n</I> impressions from the input doc.
 * Since the input docs bre sepbrbte, the first impression of ebch input doc
 * will blwbys stbrt on b new medib sheet; this mebns the lbst medib sheet of
 * bn input doc mby hbve only one impression on it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- The input docs will rembin sepbrbte.
 * Ebch medib sheet will consist of <I>n</I> impressions from the input doc.
 * Since the input docs bre sepbrbte, the first impression of ebch input doc
 * will blwbys stbrt on b new medib sheet; this mebns the lbst medib sheet of
 * bn input doc mby hbve only one impression on it.
 * </UL>
 *
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- All the input docs will be combined together into one
 * output document. Ebch medib sheet will consist of <I>n<SUB>i</SUB></I>
 * impressions from the output document, where <I>i</I> is the number of the
 * input doc corresponding to thbt point in the output document. When the next
 * input doc hbs b different sides vblue from the previous input doc, the first
 * print-strebm pbge of the next input doc goes bt the stbrt of the next medib
 * sheet, possibly lebving only one impression on the previous medib sheet.
 *
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- All the input docs will be combined together
 * into one output document. Ebch medib sheet will consist of <I>n</I>
 * impressions from the output document. However, the first impression of ebch
 * input doc will blwbys stbrt on b new medib sheet; this mebns the lbst
 * impression of bn input doc mby hbve only one impression on it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- The input docs will rembin sepbrbte.
 * For input doc <I>i,</I> ebch medib sheet will consist of <I>n<SUB>i</SUB></I>
 * impressions from the input doc. Since the input docs bre sepbrbte, the first
 * impression of ebch input doc will blwbys stbrt on b new medib sheet; this
 * mebns the lbst medib sheet of bn input doc mby hbve only one impression on
 * it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- The input docs will rembin sepbrbte.
 * For input doc <I>i,</I> ebch medib sheet will consist of <I>n<SUB>i</SUB></I>
 * impressions from the input doc. Since the input docs bre sepbrbte, the first
 * impression of ebch input doc will blwbys stbrt on b new medib sheet; this
 * mebns the lbst medib sheet of bn input doc mby hbve only one impression on
 * it.
 * </UL>
 * </UL>
 * <P>
 * <B>IPP Compbtibility:</B> The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> is the IPP bttribute nbme.  The enumerbtion's
 * integer vblue is the IPP enum vblue.  The <code>toString()</code> method
 * returns the IPP string representbtion of the bttribute vblue.
 *
 * @buthor  Albn Kbminsky
 */

public finbl clbss Sides extends EnumSyntbx
    implements DocAttribute, PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = -6890309414893262822L;

    /**
     * Imposes ebch consecutive print-strebm pbge upon the sbme side of
     * consecutive medib sheets.
     */
    public stbtic finbl Sides ONE_SIDED = new Sides(0);

    /**
     * Imposes ebch consecutive pbir of print-strebm pbges upon front bnd bbck
     * sides of consecutive medib sheets, such thbt the orientbtion of ebch
     * pbir of print-strebm pbges on the medium would be correct for the
     * rebder bs if for binding on the long edge. This imposition is blso
     * known bs "duplex" (see {@link #DUPLEX DUPLEX}).
     */
    public stbtic finbl Sides TWO_SIDED_LONG_EDGE = new Sides(1);

    /**
     * Imposes ebch consecutive pbir of print-strebm pbges upon front bnd bbck
     * sides of consecutive medib sheets, such thbt the orientbtion of ebch
     * pbir of print-strebm pbges on the medium would be correct for the
     * rebder bs if for binding on the short edge. This imposition is blso
     * known bs "tumble" (see {@link #TUMBLE TUMBLE}).
     */
    public stbtic finbl Sides TWO_SIDED_SHORT_EDGE = new Sides(2);

    /**
     * An blibs for "two sided long edge" (see {@link #TWO_SIDED_LONG_EDGE
     * TWO_SIDED_LONG_EDGE}).
     */
    public stbtic finbl Sides DUPLEX = TWO_SIDED_LONG_EDGE;

    /**
     * An blibs for "two sided short edge" (see {@link #TWO_SIDED_SHORT_EDGE
     * TWO_SIDED_SHORT_EDGE}).
     */
    public stbtic finbl Sides TUMBLE = TWO_SIDED_SHORT_EDGE;

    /**
     * Construct b new sides enumerbtion vblue with the given integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected Sides(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
        "one-sided",
        "two-sided-long-edge",
        "two-sided-short-edge"
    };

    privbte stbtic finbl Sides[] myEnumVblueTbble = {
        ONE_SIDED,
        TWO_SIDED_LONG_EDGE,
        TWO_SIDED_SHORT_EDGE
    };

    /**
     * Returns the string tbble for clbss Sides.
     */
    protected String[] getStringTbble() {
        return myStringTbble;
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss Sides.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return myEnumVblueTbble;
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss Sides, the cbtegory is clbss Sides itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return Sides.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss Sides, the cbtegory nbme is <CODE>"sides"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "sides";
    }

}
