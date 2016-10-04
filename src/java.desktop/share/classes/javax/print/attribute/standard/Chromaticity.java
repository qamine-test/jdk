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
 * Clbss Chrombticity is b printing bttribute clbss, bn enumerbtion, thbt
 * specifies monochrome or color printing. This is used by b print client
 * to specify how the print dbtb should be generbted or processed. It is not
 * descriptive of the color cbpbbilities of the device. Query the service's
 * {@link ColorSupported ColorSupported} bttribute to determine if the device
 * cbn be verified to support color printing.
 * <P>
 * The tbble below shows the effects of specifying b Chrombticity bttribute of
 * {@link #MONOCHROME MONOCHROME} or {@link #COLOR COLOR}
 * for b monochrome or color document.
 *
 * <TABLE BORDER=1 CELLPADDING=2 CELLSPACING=1 SUMMARY="Shows effects of specifying MONOCHROME or COLOR Chrombticity bttributes">
 * <TR>
 * <TH>
 * Chrombticity<BR>Attribute
 * </TH>
 * <TH>
 * Effect on<BR>Monochrome Document
 * </TH>
 * <TH>
 * Effect on<BR>Color Document
 * </TH>
 * </TR>
 * <TR>
 * <TD>
 * {@link #MONOCHROME MONOCHROME}
 * </TD>
 * <TD>
 * Printed bs is, in monochrome
 * </TD>
 * <TD>
 * Printed in monochrome, with colors converted to shbdes of grby
 * </TD>
 * </TR>
 * <TR>
 * <TD>
 * {@link #COLOR COLOR}
 * </TD>
 * <TD>
 * Printed bs is, in monochrome
 * </TD>
 * <TD>
 * Printed bs is, in color
 * </TD>
 * </TR>
 * </TABLE>
 * <P>
 * <B>IPP Compbtibility:</B> Chrombticity is not bn IPP bttribute bt present.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss Chrombticity extends EnumSyntbx
    implements DocAttribute, PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = 4660543931355214012L;

    /**
     * Monochrome printing.
     */
    public stbtic finbl Chrombticity MONOCHROME = new Chrombticity(0);

    /**
     * Color printing.
     */
    public stbtic finbl Chrombticity COLOR = new Chrombticity(1);


    /**
     * Construct b new chrombticity enumerbtion vblue with the given integer
     * vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected Chrombticity(int vblue) {
        super(vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {"monochrome",
                                                   "color"};

    privbte stbtic finbl Chrombticity[] myEnumVblueTbble = {MONOCHROME,
                                                            COLOR};

    /**
     * Returns the string tbble for clbss Chrombticity.
     */
    protected String[] getStringTbble() {
        return myStringTbble;
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss Chrombticity.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return myEnumVblueTbble;
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss Chrombticity, the cbtegory is the clbss Chrombticity itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return Chrombticity.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss Chrombticity, the cbtegory nbme is <CODE>"chrombticity"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
        public finbl String getNbme() {
            return "chrombticity";
        }

        }
