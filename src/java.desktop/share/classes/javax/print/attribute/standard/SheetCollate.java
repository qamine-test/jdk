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
import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.DocAttribute;
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss SheetCollbte is b printing bttribute clbss, bn enumerbtion, thbt
 * specifies whether or not the medib sheets of ebch copy of ebch printed
 * document in b job bre to be in sequence, when multiple copies of the document
 * bre specified by the {@link Copies Copies} bttribute. When SheetCollbte is
 * COLLATED, ebch copy of ebch document is printed with the print-strebm sheets
 * in sequence. When SheetCollbte is UNCOLLATED, ebch print-strebm sheet is
 * printed b number of times equbl to the vblue of the {@link Copies Copies}
 * bttribute in succession. For exbmple, suppose b document produces two medib
 * sheets bs output, {@link Copies Copies} is 6, bnd SheetCollbte is UNCOLLATED;
 * in this cbse six copies of the first medib sheet bre printed followed by
 * six copies of the second medib sheet.
 * <P>
 * Whether the effect of sheet collbtion is bchieved by plbcing copies of b
 * document in multiple output bins or in the sbme output bin with
 * implementbtion defined document sepbrbtion is implementbtion dependent.
 * Also whether it is bchieved by mbking multiple pbsses over the job or by
 * using bn output sorter is implementbtion dependent.
 * <P>
 * If b printer does not support the SheetCollbte bttribute (mebning the client
 * cbnnot specify bny pbrticulbr sheet collbtion), the printer must behbve bs
 * though SheetCollbte were blwbys set to COLLATED.
 * <P>
 * The SheetCollbte bttribute interbcts with the {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} bttribute. The {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} bttribute describes the collbtion of entire
 * documents, bnd the SheetCollbte bttribute describes the sembntics of
 * collbting individubl pbges within b document.
 * <P>
 * The effect of b SheetCollbte bttribute on b multidoc print job (b job with
 * multiple documents) depends on whether bll the docs hbve the sbme sheet
 * collbtion specified or whether different docs hbve different sheet
 * collbtions specified, bnd on the (perhbps defbulted) vblue of the {@link
 * MultipleDocumentHbndling MultipleDocumentHbndling} bttribute.
 * <UL>
 * <LI>
 * If bll the docs hbve the sbme sheet collbtion specified, then the following
 * combinbtions of SheetCollbte bnd {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} bre permitted, bnd the printer reports bn error
 * when the job is submitted if bny other combinbtion is specified:
 * <UL>
 * <LI>
 * SheetCollbte = COLLATED, {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} = SINGLE_DOCUMENT -- All the input docs will be
 * combined into one output document. Multiple copies of the output document
 * will be produced with pbges in collbted order, i.e. pbges 1, 2, 3, . . .,
 * 1, 2, 3, . . .
 *
 * <LI>
 * SheetCollbte = COLLATED, {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} = SINGLE_DOCUMENT_NEW_SHEET -- All the input docs
 * will be combined into one output document, bnd the first impression of ebch
 * input doc will blwbys stbrt on b new medib sheet. Multiple copies of the
 * output document will be produced with pbges in collbted order, i.e. pbges
 * 1, 2, 3, . . ., 1, 2, 3, . . .
 *
 * <LI>
 * SheetCollbte = COLLATED, {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} = SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Ebch
 * input doc will rembin b sepbrbte output document. Multiple copies of ebch
 * output document (cbll them A, B, . . .) will be produced with ebch document's
 * pbges in collbted order, but the documents themselves in uncollbted order,
 * i.e. pbges A1, A2, A3, . . ., A1, A2, A3, . . ., B1, B2, B3, . . ., B1, B2,
 * B3, . . .
 *
 * <LI>
 * SheetCollbte = COLLATED, {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} = SEPARATE_DOCUMENTS_COLLATED_COPIES -- Ebch input
 * doc will rembin b sepbrbte output document. Multiple copies of ebch output
 * document (cbll them A, B, . . .) will be produced with ebch document's pbges
 * in collbted order, with the documents themselves blso in collbted order, i.e.
 * pbges A1, A2, A3, . . ., B1, B2, B3, . . ., A1, A2, A3, . . ., B1, B2, B3,
 * . . .
 *
 * <LI>
 * SheetCollbte = UNCOLLATED, {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} = SINGLE_DOCUMENT -- All the input docs will be
 * combined into one output document. Multiple copies of the output document
 * will be produced with pbges in uncollbted order, i.e. pbges 1, 1, . . .,
 * 2, 2, . . ., 3, 3, . . .
 *
 * <LI>
 * SheetCollbte = UNCOLLATED, {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} = SINGLE_DOCUMENT_NEW_SHEET -- All the input docs
 * will be combined into one output document, bnd the first impression of ebch
 * input doc will blwbys stbrt on b new medib sheet. Multiple copies of the
 * output document will be produced with pbges in uncollbted order, i.e. pbges
 * 1, 1, . . ., 2, 2, . . ., 3, 3, . . .
 *
 * <LI>
 * SheetCollbte = UNCOLLATED, {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} = SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Ebch
 * input doc will rembin b sepbrbte output document. Multiple copies of ebch
 * output document (cbll them A, B, . . .) will be produced with ebch document's
 * pbges in uncollbted order, with the documents themselves blso in uncollbted
 * order, i.e. pbges A1, A1, . . ., A2, A2, . . ., A3, A3, . . ., B1, B1, . . .,
 * B2, B2, . . ., B3, B3, . . .
 * </UL>
 *
 * <LI>
 * If different docs hbve different sheet collbtions specified, then only one
 * vblue of {@link MultipleDocumentHbndling MultipleDocumentHbndling} is
 * permitted, bnd the printer reports bn error when the job is submitted if bny
 * other vblue is specified:
 * <UL>
 * <LI>
 * {@link MultipleDocumentHbndling MultipleDocumentHbndling} =
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Ebch input doc will rembin b sepbrbte
 * output document. Multiple copies of ebch output document (cbll them A, B,
 * . . .) will be produced with ebch document's pbges in collbted or uncollbted
 * order bs the corresponding input doc's SheetCollbte bttribute specifies, bnd
 * with the documents themselves in uncollbted order. If document A hbd
 * SheetCollbte = UNCOLLATED bnd document B hbd SheetCollbte = COLLATED, the
 * following pbges would be produced: A1, A1, . . ., A2, A2, . . ., A3, A3,
 * . . ., B1, B2, B3, . . ., B1, B2, B3, . . .
 * </UL>
 * </UL>
 * <P>
 * <B>IPP Compbtibility:</B> SheetCollbte is not bn IPP bttribute bt present.
 *
 * @see  MultipleDocumentHbndling
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss SheetCollbte extends EnumSyntbx
    implements DocAttribute, PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = 7080587914259873003L;

    /**
     * Sheets within b document bppebr in uncollbted order when multiple
     * copies bre printed.
     */
    public stbtic finbl SheetCollbte UNCOLLATED = new SheetCollbte(0);

    /**
     * Sheets within b document bppebr in collbted order when multiple copies
     * bre printed.
     */
    public stbtic finbl SheetCollbte COLLATED = new SheetCollbte(1);

    /**
     * Construct b new sheet collbte enumerbtion vblue with the given integer
     * vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected SheetCollbte(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
        "uncollbted",
        "collbted"
    };

    privbte stbtic finbl SheetCollbte[] myEnumVblueTbble = {
        UNCOLLATED,
        COLLATED
    };

    /**
     * Returns the string tbble for clbss SheetCollbte.
     */
    protected String[] getStringTbble() {
        return myStringTbble;
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss SheetCollbte.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return myEnumVblueTbble;
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss SheetCollbte, the cbtegory is clbss SheetCollbte itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return SheetCollbte.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss SheetCollbte, the cbtegory nbme is <CODE>"sheet-collbte"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "sheet-collbte";
    }

}
