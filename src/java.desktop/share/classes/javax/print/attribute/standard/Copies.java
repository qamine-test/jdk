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
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss Copies is bn integer vblued printing bttribute clbss thbt specifies the
 * number of copies to be printed.
 * <P>
 * On mbny devices the supported number of collbted copies will be limited by
 * the number of physicbl output bins on the device, bnd mby be different from
 * the number of uncollbted copies which cbn be supported.
 * <P>
 * The effect of b Copies bttribute with b vblue of <I>n</I> on b multidoc print
 * job (b job with multiple documents) depends on the (perhbps defbulted) vblue
 * of the {@link MultipleDocumentHbndling MultipleDocumentHbndling} bttribute:
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- The result will be <I>n</I> copies of b single output
 * document comprising bll the input docs.
 *
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- The result will be <I>n</I> copies of b single
 * output document comprising bll the input docs, bnd the first impression of
 * ebch input doc will blwbys stbrt on b new medib sheet.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- The result will be <I>n</I> copies of
 * the first input document, followed by <I>n</I> copies of the second input
 * document, . . . followed by <I>n</I> copies of the lbst input document.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- The result will be the first input
 * document, the second input document, . . . the lbst input document, the group
 * of documents being repebted <I>n</I> times.
 * </UL>
 * <P>
 * <B>IPP Compbtibility:</B> The integer vblue gives the IPP integer vblue. The
 * cbtegory nbme returned by <CODE>getNbme()</CODE> gives the IPP bttribute
 * nbme.
 *
 * @buthor  Dbvid Mendenhbll
 * @buthor  Albn Kbmihensky
 */
public finbl clbss Copies extends IntegerSyntbx
        implements PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = -6426631521680023833L;

    /**
     * Construct b new copies bttribute with the given integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     *
     * @exception  IllegblArgumentException
     *  (Unchecked exception) Thrown if <CODE>vblue</CODE> is less thbn 1.
     */
    public Copies(int vblue) {
        super (vblue, 1, Integer.MAX_VALUE);
    }

    /**
     * Returns whether this copies bttribute is equivblent to the pbssed in
     * object. To be equivblent, bll of the following conditions must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss Copies.
     * <LI>
     * This copies bttribute's vblue bnd <CODE>object</CODE>'s vblue bre
     * equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this copies
     *          bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return super.equbls (object) && object instbnceof Copies;
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss Copies, the cbtegory is clbss Copies itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return Copies.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss Copies, the cbtegory nbme is <CODE>"copies"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "copies";
    }

}
