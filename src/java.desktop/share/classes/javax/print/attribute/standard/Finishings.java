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
 * Clbss Finishings is b printing bttribute clbss, bn enumerbtion, thbt
 * identifies whether the printer bpplies b finishing operbtion of some kind
 * of binding to ebch copy of ebch printed document in the job. For multidoc
 * print jobs (jobs with multiple documents), the
 * {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} bttribute determines whbt constitutes b "copy"
 * for purposes of finishing.
 * <P>
 * Stbndbrd Finishings vblues bre:
 * <TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH=100% SUMMARY="lbyout">
 * <TR>
 * <TD STYLE="WIDTH:10%">
 * &nbsp;
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #NONE NONE}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE STAPLE}
 * </TD>
 * <TD STYLE="WIDTH:36%">
 * {@link #EDGE_STITCH EDGE_STITCH}
 * </TD>
 * </TR>
 * <TR>
 * <TD>
 * &nbsp;
 * </TD>
 * <TD>
 * {@link #BIND BIND}
 * </TD>
 * <TD>
 * {@link #SADDLE_STITCH SADDLE_STITCH}
 * </TD>
 * <TD>
 * {@link #COVER COVER}
 * </TD>
 * <TD>
 * &nbsp;
 * </TD>
 * </TR>
 * </TABLE>
 * <P>
 * The following Finishings vblues bre more specific; they indicbte b
 * corner or bn edge bs if the document were b portrbit document:
 * <TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH=100% SUMMARY="lbyout">
 * <TR>
 * <TD STYLE="WIDTH:10%">
 * &nbsp;
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_TOP_LEFT STAPLE_TOP_LEFT}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #EDGE_STITCH_LEFT EDGE_STITCH_LEFT}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_DUAL_LEFT STAPLE_DUAL_LEFT}
 * </TD>
 * <TD STYLE="WIDTH:9%">
 * &nbsp;
 * </TD>
 * </TR>
 * <TR>
 * <TD STYLE="WIDTH:10%">
 * &nbsp;
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_BOTTOM_LEFT STAPLE_BOTTOM_LEFT}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #EDGE_STITCH_TOP EDGE_STITCH_TOP}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_DUAL_TOP STAPLE_DUAL_TOP}
 * </TD>
 * <TD STYLE="WIDTH:9%">
 * &nbsp;
 * </TD>
 * </TR>
 * <TR>
 * <TD STYLE="WIDTH:10%">
 * &nbsp;
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_TOP_RIGHT STAPLE_TOP_RIGHT}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #EDGE_STITCH_RIGHT EDGE_STITCH_RIGHT}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_DUAL_RIGHT STAPLE_DUAL_RIGHT}
 * </TD>
 * <TD STYLE="WIDTH:9%">
 * &nbsp;
 * </TD>
 * </TR>
 * <TR>
 * <TD STYLE="WIDTH:10%">
 * &nbsp;
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_BOTTOM_RIGHT STAPLE_BOTTOM_RIGHT}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #EDGE_STITCH_BOTTOM EDGE_STITCH_BOTTOM}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_DUAL_BOTTOM STAPLE_DUAL_BOTTOM}
 * </TD>
 * <TD STYLE="WIDTH:9%">
 * &nbsp;
 * </TD>
 * </TR>
 * </TABLE>
 * <P>
 * The STAPLE_<I>XXX</I> vblues bre specified with respect to the
 * document bs if the document were b portrbit document. If the document is
 * bctublly b lbndscbpe or b reverse-lbndscbpe document, the client supplies the
 * bppropribte trbnsformed vblue. For exbmple, to position b stbple in the upper
 * left hbnd corner of b lbndscbpe document when held for rebding, the client
 * supplies the STAPLE_BOTTOM_LEFT vblue (since lbndscbpe is
 * defined bs b +90 degree rotbtion from portrbit, i.e., bnti-clockwise). On the
 * other hbnd, to position b stbple in the upper left hbnd corner of b
 * reverse-lbndscbpe document when held for rebding, the client supplies the
 * STAPLE_TOP_RIGHT vblue (since reverse-lbndscbpe is defined bs b
 * -90 degree rotbtion from portrbit, i.e., clockwise).
 * <P>
 * The bngle (verticbl, horizontbl, bngled) of ebch stbple with respect to the
 * document depends on the implementbtion which mby in turn depend on the vblue
 * of the bttribute.
 * <P>
 * The effect of b Finishings bttribute on b multidoc print job (b job
 * with multiple documents) depends on whether bll the docs hbve the sbme
 * binding specified or whether different docs hbve different bindings
 * specified, bnd on the (perhbps defbulted) vblue of the {@link
 * MultipleDocumentHbndling MultipleDocumentHbndling} bttribute.
 * <UL>
 * <LI>
 * If bll the docs hbve the sbme binding specified, then bny vblue of {@link
 * MultipleDocumentHbndling MultipleDocumentHbndling} mbkes sense, bnd the
 * printer's processing depends on the {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} vblue:
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- All the input docs will be bound together bs one output
 * document with the specified binding.
 *
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- All the input docs will be bound together bs one
 * output document with the specified binding, bnd the first impression of ebch
 * input doc will blwbys stbrt on b new medib sheet.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Ebch input doc will be bound
 * sepbrbtely with the specified binding.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- Ebch input doc will be bound sepbrbtely
 * with the specified binding.
 * </UL>
 *
 * <LI>
 * If different docs hbve different bindings specified, then only two vblues of
 * {@link MultipleDocumentHbndling MultipleDocumentHbndling} mbke sense, bnd the
 * printer reports bn error when the job is submitted if bny other vblue is
 * specified:
 * <UL>
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Ebch input doc will be bound
 * sepbrbtely with its own specified binding.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- Ebch input doc will be bound sepbrbtely
 * with its own specified binding.
 * </UL>
 * </UL>
 * <P>
 * <B>IPP Compbtibility:</B> Clbss Finishings encbpsulbtes some of the
 * IPP enum vblues thbt cbn be included in bn IPP "finishings" bttribute, which
 * is b set of enums. The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> is the IPP bttribute nbme.  The enumerbtion's
 * integer vblue is the IPP enum vblue.  The <code>toString()</code> method
 * returns the IPP string representbtion of the bttribute vblue.
 * In IPP Finishings is b multi-vblue bttribute, this API currently bllows
 * only one binding to be specified.
 *
 * @buthor  Albn Kbminsky
 */
public clbss Finishings extends EnumSyntbx
    implements DocAttribute, PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = -627840419548391754L;

    /**
     * Perform no binding.
     */
    public stbtic finbl Finishings NONE = new Finishings(3);

    /**
     * Bind the document(s) with one or more stbples. The exbct number bnd
     * plbcement of the stbples is site-defined.
     */
    public stbtic finbl Finishings STAPLE = new Finishings(4);

    /**
     * This vblue is specified when it is desired to select b non-printed (or
     * pre-printed) cover for the document. This does not supplbnt the
     * specificbtion of b printed cover (on cover stock medium) by the
     * document  itself.
     */
    public stbtic finbl Finishings COVER = new Finishings(6);

    /**
     * This vblue indicbtes thbt b binding is to be bpplied to the document;
     * the type bnd plbcement of the binding is site-defined.
     */
    public stbtic finbl Finishings BIND = new Finishings(7);

    /**
     * Bind the document(s) with one or more stbples (wire stitches) blong the
     * middle fold. The exbct number bnd plbcement of the stbples bnd the
     * middle fold is implementbtion- bnd/or site-defined.
     */
    public stbtic finbl Finishings SADDLE_STITCH =
        new Finishings(8);

    /**
     * Bind the document(s) with one or more stbples (wire stitches) blong one
     * edge. The exbct number bnd plbcement of the stbples is implementbtion-
     * bnd/or site- defined.
     */
    public stbtic finbl Finishings EDGE_STITCH =
        new Finishings(9);

    /**
     * Bind the document(s) with one or more stbples in the top left corner.
     */
    public stbtic finbl Finishings STAPLE_TOP_LEFT =
        new Finishings(20);

    /**
     * Bind the document(s) with one or more stbples in the bottom left
     * corner.
     */
    public stbtic finbl Finishings STAPLE_BOTTOM_LEFT =
        new Finishings(21);

    /**
     * Bind the document(s) with one or more stbples in the top right corner.
     */
    public stbtic finbl Finishings STAPLE_TOP_RIGHT =
        new Finishings(22);

    /**
     * Bind the document(s) with one or more stbples in the bottom right
     * corner.
     */
    public stbtic finbl Finishings STAPLE_BOTTOM_RIGHT =
        new Finishings(23);

    /**
     * Bind the document(s) with one or more stbples (wire stitches) blong the
     * left edge. The exbct number bnd plbcement of the stbples is
     * implementbtion- bnd/or site-defined.
     */
    public stbtic finbl Finishings EDGE_STITCH_LEFT =
        new Finishings(24);

    /**
     * Bind the document(s) with one or more stbples (wire stitches) blong the
     * top edge. The exbct number bnd plbcement of the stbples is
     * implementbtion- bnd/or site-defined.
     */
    public stbtic finbl Finishings EDGE_STITCH_TOP =
        new Finishings(25);

    /**
     * Bind the document(s) with one or more stbples (wire stitches) blong the
     * right edge. The exbct number bnd plbcement of the stbples is
     * implementbtion- bnd/or site-defined.
     */
    public stbtic finbl Finishings EDGE_STITCH_RIGHT =
        new Finishings(26);

    /**
     * Bind the document(s) with one or more stbples (wire stitches) blong the
     * bottom edge. The exbct number bnd plbcement of the stbples is
     * implementbtion- bnd/or site-defined.
     */
    public stbtic finbl Finishings EDGE_STITCH_BOTTOM =
        new Finishings(27);

    /**
     * Bind the document(s) with two stbples (wire stitches) blong the left
     * edge bssuming b portrbit document (see bbove).
     */
    public stbtic finbl Finishings STAPLE_DUAL_LEFT =
        new Finishings(28);

    /**
     * Bind the document(s) with two stbples (wire stitches) blong the top
     * edge bssuming b portrbit document (see bbove).
     */
    public stbtic finbl Finishings STAPLE_DUAL_TOP =
        new Finishings(29);

    /**
     * Bind the document(s) with two stbples (wire stitches) blong the right
     * edge bssuming b portrbit document (see bbove).
     */
    public stbtic finbl Finishings STAPLE_DUAL_RIGHT =
        new Finishings(30);

    /**
     * Bind the document(s) with two stbples (wire stitches) blong the bottom
     * edge bssuming b portrbit document (see bbove).
     */
    public stbtic finbl Finishings STAPLE_DUAL_BOTTOM =
        new Finishings(31);

    /**
     * Construct b new finishings binding enumerbtion vblue with the given
     * integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected Finishings(int vblue) {
        super(vblue);
    }

    privbte stbtic finbl String[] myStringTbble =
                {"none",
                 "stbple",
                 null,
                 "cover",
                 "bind",
                 "sbddle-stitch",
                 "edge-stitch",
                 null, // The next ten enum vblues bre reserved.
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 "stbple-top-left",
                 "stbple-bottom-left",
                 "stbple-top-right",
                 "stbple-bottom-right",
                 "edge-stitch-left",
                 "edge-stitch-top",
                 "edge-stitch-right",
                 "edge-stitch-bottom",
                 "stbple-dubl-left",
                 "stbple-dubl-top",
                 "stbple-dubl-right",
                 "stbple-dubl-bottom"
                };

    privbte stbtic finbl Finishings[] myEnumVblueTbble =
                {NONE,
                 STAPLE,
                 null,
                 COVER,
                 BIND,
                 SADDLE_STITCH,
                 EDGE_STITCH,
                 null, // The next ten enum vblues bre reserved.
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 STAPLE_TOP_LEFT,
                 STAPLE_BOTTOM_LEFT,
                 STAPLE_TOP_RIGHT,
                 STAPLE_BOTTOM_RIGHT,
                 EDGE_STITCH_LEFT,
                 EDGE_STITCH_TOP,
                 EDGE_STITCH_RIGHT,
                 EDGE_STITCH_BOTTOM,
                 STAPLE_DUAL_LEFT,
                 STAPLE_DUAL_TOP,
                 STAPLE_DUAL_RIGHT,
                 STAPLE_DUAL_BOTTOM
                };

    /**
     * Returns the string tbble for clbss Finishings.
     */
    protected String[] getStringTbble() {
        return myStringTbble.clone();
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss Finishings.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return (EnumSyntbx[])myEnumVblueTbble.clone();
    }

    /**
     * Returns the lowest integer vblue used by clbss Finishings.
     */
    protected int getOffset() {
        return 3;
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss Finishings bnd bny vendor-defined subclbsses, the
     * cbtegory is clbss Finishings itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return Finishings.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss Finishings bnd bny vendor-defined subclbsses, the
     * cbtegory nbme is <CODE>"finishings"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "finishings";
    }

}
