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
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss MultipleDocumentHbndling is b printing bttribute clbss, bn enumerbtion,
 * thbt controls finishing operbtions bnd the plbcement of one or more
 * print-strebm pbges into impressions bnd onto medib sheets. When the vblue of
 * the {@link Copies Copies} bttribute exceeds 1, MultipleDocumentHbndling blso
 * controls the order in which the copies thbt result from processing the
 * documents bre produced. This bttribute is relevbnt only for b multidoc print
 * job consisting of two or more individubl docs.
 * <P>
 * Briefly, MultipleDocumentHbndling determines the relbtionship between the
 * multiple input (electronic) documents fed into b multidoc print job bnd the
 * output (physicbl) document or documents produced by the multidoc print job.
 * There bre two possibilities:
 * <UL>
 * <LI>
 * The multiple input documents bre combined into b single output document.
 * Finishing operbtions ({@link Finishings Finishings}),
 * bre performed on this single output
 * document. The {@link Copies Copies} bttribute tells how mbny copies of this
 * single output document to produce. The MultipleDocumentHbndling vblues
 * SINGLE_DOCUMENT bnd SINGLE_DOCUMENT_NEW_SHEET specify two vbribtions of
 * this  possibility.
 *
 * <LI>
 * The multiple input documents rembin sepbrbte output documents. Finishing
 * operbtions ({@link Finishings Finishings}),
 * bre performed on ebch output document
 * sepbrbtely. The {@link Copies Copies} bttribute tells how mbny copies of ebch
 * sepbrbte output document to produce. The MultipleDocumentHbndling vblues
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES bnd SEPARATE_DOCUMENTS_COLLATED_COPIES
 * specify two vbribtions of this possibility.
 * </UL>
 * <P>
 * In the detbiled explbnbtions below, if "<CODE>b</CODE>" represents bn
 * instbnce of document dbtb, then the result of processing the dbtb in
 * document "<CODE>b</CODE>" is b sequence of medib sheets represented by
 * "<CODE>b(*)</CODE>".
 * <P>
 * The stbndbrd MultipleDocumentHbndling vblues bre:
 * <UL>
 * <LI>
 * <A NAME="sdfi">{@link #SINGLE_DOCUMENT
 * <B>SINGLE_DOCUMENT</B>}</A>. If b print job hbs multiple
 * documents -- sby, the document dbtb is cblled <CODE>b</CODE> bnd
 * <CODE>b</CODE> -- then the result of processing bll the document dbtb
 * (<CODE>b</CODE> bnd then <CODE>b</CODE>) must be trebted bs b single sequence
 * of medib sheets for finishing operbtions; thbt is, finishing would be
 * performed on the concbtenbtion of the sequences <CODE>b(*),b(*)</CODE>. The
 * printer must not force the dbtb in ebch document instbnce to be formbtted
 * onto b new print-strebm pbge, nor to stbrt b new impression on b new medib
 * sheet. If more thbn one copy is mbde, the ordering of the sets of medib
 * sheets resulting from processing the document dbtb must be
 * <CODE>b(*),b(*),b(*),b(*),...</CODE>, bnd the printer object must force
 * ebch copy (<CODE>b(*),b(*)</CODE>) to stbrt on b new medib sheet.
 *
 * <LI>
 * <A NAME="sducfi">{@link #SEPARATE_DOCUMENTS_UNCOLLATED_COPIES
 * <B>SEPARATE_DOCUMENTS_UNCOLLATED_COPIES</B>}</A>. If b print job
 * hbs multiple documents -- sby, the document dbtb is cblled <CODE>b</CODE> bnd
 * <CODE>b</CODE> -- then the result of processing the dbtb in ebch document
 * instbnce must be trebted bs b single sequence of medib sheets for finishing
 * operbtions; thbt is, the sets <CODE>b(*)</CODE> bnd <CODE>b(*)</CODE> would
 * ebch be finished sepbrbtely. The printer must force ebch copy of the result
 * of processing the dbtb in b single document to stbrt on b new medib sheet.
 * If more thbn one copy is mbde, the ordering of the sets of medib sheets
 * resulting from processing the document dbtb must be
 * <CODE>b(*),b(*),...,b(*),b(*)...</CODE>.
 *
 * <LI>
 * <A NAME="sdccfi">{@link #SEPARATE_DOCUMENTS_COLLATED_COPIES
 * <B>SEPARATE_DOCUMENTS_COLLATED_COPIES</B>}</A>. If b print job
 * hbs multiple documents -- sby, the document dbtb is cblled <CODE>b</CODE> bnd
 * <CODE>b</CODE> -- then the result of processing the dbtb in ebch document
 * instbnce must be trebted bs b single sequence of medib sheets for finishing
 * operbtions; thbt is, the sets <CODE>b(*)</CODE> bnd <CODE>b(*)</CODE> would
 * ebch be finished sepbrbtely. The printer must force ebch copy of the result
 * of processing the dbtb in b single document to stbrt on b new medib sheet.
 * If more thbn one copy is mbde, the ordering of the sets of medib sheets
 * resulting from processing the document dbtb must be
 * <CODE>b(*),b(*),b(*),b(*),...</CODE>.
 *
 * <LI>
 * <A NAME="sdnsfi">{@link #SINGLE_DOCUMENT_NEW_SHEET
 * <B>SINGLE_DOCUMENT_NEW_SHEET</B>}</A>. Sbme bs SINGLE_DOCUMENT,
 * except thbt the printer must ensure thbt the first impression of ebch
 * document instbnce in the job is plbced on b new medib sheet. This vblue
 * bllows multiple documents to be stbpled together with b single stbple where
 * ebch document stbrts on b new sheet.
 * </UL>
 * <P>
 * SINGLE_DOCUMENT is the sbme bs SEPARATE_DOCUMENTS_COLLATED_COPIES with
 * respect to ordering of print-strebm pbges, but not medib sheet generbtion,
 * since SINGLE_DOCUMENT will put the first pbge of the next document on the
 * bbck side of b sheet if bn odd number of pbges hbve been produced so fbr
 * for the job, while SEPARATE_DOCUMENTS_COLLATED_COPIES blwbys forces the
 * next document or document copy on to b new sheet.
 * <P>
 * In bddition, if b {@link Finishings Finishings} bttribute of
 * {@link Finishings#STAPLE STAPLE} is specified, then:
 * <UL>
 * <LI>
 * With SINGLE_DOCUMENT, documents <CODE>b</CODE> bnd <CODE>b</CODE> bre
 * stbpled together bs b single document with no regbrd to new sheets.
 *
 * <LI>
 * With SINGLE_DOCUMENT_NEW_SHEET, documents <CODE>b</CODE> bnd <CODE>b</CODE>
 * bre stbpled together bs b single document, but document <CODE>b</CODE>
 * stbrts on b new sheet.
 *
 * <LI>
 * With SEPARATE_DOCUMENTS_UNCOLLATED_COPIES bnd
 * SEPARATE_DOCUMENTS_COLLATED_COPIES, documents <CODE>b</CODE> bnd
 * <CODE>b</CODE> bre stbpled sepbrbtely.
 * </UL>
 * <P>
 * <I>Note:</I> None of these vblues provide mebns to produce uncollbted
 * sheets within b document, i.e., where multiple copies of sheet <I>n</I>
 * bre produced before sheet <I>n</I>+1 of the sbme document.
 * To specify thbt, see the {@link SheetCollbte SheetCollbte} bttribute.
 * <P>
 * <B>IPP Compbtibility:</B> The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> is the IPP bttribute nbme.  The enumerbtion's
 * integer vblue is the IPP enum vblue.  The <code>toString()</code> method
 * returns the IPP string representbtion of the bttribute vblue.
 * <P>
 *
 * @see  Copies
 * @see  Finishings
 * @see  NumberUp
 * @see  PbgeRbnges
 * @see  SheetCollbte
 * @see  Sides
 *
 * @buthor  Dbvid Mendenhbll
 * @buthor  Albn Kbminsky
 */
public clbss MultipleDocumentHbndling extends EnumSyntbx
    implements PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = 8098326460746413466L;


    /**
     * Single document -- see bbove for <A HREF="#sdfi">further
     * informbtion</A>.
     */
    public stbtic finbl MultipleDocumentHbndling
        SINGLE_DOCUMENT = new MultipleDocumentHbndling (0);

    /**
     * Sepbrbte documents uncollbted copies -- see bbove for
     * <A HREF="#sducfi">further informbtion</A>.
     */
    public stbtic finbl MultipleDocumentHbndling
       SEPARATE_DOCUMENTS_UNCOLLATED_COPIES = new MultipleDocumentHbndling (1);

    /**
     * Sepbrbte documents collbted copies -- see bbove for
     * <A HREF="#sdccfi">further informbtion</A>.
     */
    public stbtic finbl MultipleDocumentHbndling
        SEPARATE_DOCUMENTS_COLLATED_COPIES = new MultipleDocumentHbndling (2);

    /**
     * Single document new sheet -- see bbove for
     * <A HREF="#sdnsfi">further informbtion</A>.
     */
    public stbtic finbl MultipleDocumentHbndling
        SINGLE_DOCUMENT_NEW_SHEET = new MultipleDocumentHbndling (3);


    /**
     * Construct b new multiple document hbndling enumerbtion vblue with the
     * given integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected MultipleDocumentHbndling(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
        "single-document",
        "sepbrbte-documents-uncollbted-copies",
        "sepbrbte-documents-collbted-copies",
        "single-document-new-sheet"
    };

    privbte stbtic finbl MultipleDocumentHbndling[] myEnumVblueTbble = {
        SINGLE_DOCUMENT,
        SEPARATE_DOCUMENTS_UNCOLLATED_COPIES,
        SEPARATE_DOCUMENTS_COLLATED_COPIES,
        SINGLE_DOCUMENT_NEW_SHEET
    };

    /**
     * Returns the string tbble for clbss MultipleDocumentHbndling.
     */
    protected String[] getStringTbble() {
        return myStringTbble.clone();
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss MultipleDocumentHbndling.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return (EnumSyntbx[])myEnumVblueTbble.clone();
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss MultipleDocumentHbndling bnd bny vendor-defined subclbsses,
     * the cbtegory is clbss MultipleDocumentHbndling itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return MultipleDocumentHbndling.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss MultipleDocumentHbndling bnd bny vendor-defined subclbsses,
     * the cbtegory nbme is <CODE>"multiple-document-hbndling"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "multiple-document-hbndling";
    }

}
