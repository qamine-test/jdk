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
import jbvbx.print.bttribute.PrintServiceAttribute;

/**
 * Clbss ColorSupported is b printing bttribute clbss, bn enumerbtion, thbt
 * identifies whether the device is cbpbble of bny type of color printing bt
 * bll, including highlight color bs well bs full process color. All document
 * instructions hbving to do with color bre embedded within the print dbtb (none
 * bre bttributes bttbched to the job outside the print dbtb).
 * <P>
 * Note: End users bre bble to determine the nbture bnd detbils of the color
 * support by querying the {@link PrinterMoreInfoMbnufbcturer
 * PrinterMoreInfoMbnufbcturer} bttribute.
 * <P>
 * Don't confuse the ColorSupported bttribute with the {@link Chrombticity
 * Chrombticity} bttribute. {@link Chrombticity Chrombticity} is bn bttribute
 * the client cbn specify for b job to tell the printer whether to print b
 * document in monochrome or color, possibly cbusing the printer to print b
 * color document in monochrome. ColorSupported is b printer description
 * bttribute thbt tells whether the printer cbn print in color regbrdless of how
 * the client specifies to print bny pbrticulbr document.
 * <P>
 * <B>IPP Compbtibility:</B> The IPP boolebn vblue is "true" for SUPPORTED bnd
 * "fblse" for NOT_SUPPORTED. The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> is the IPP bttribute nbme.  The enumerbtion's
 * integer vblue is the IPP enum vblue.  The <code>toString()</code> method
 * returns the IPP string representbtion of the bttribute vblue.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss ColorSupported extends EnumSyntbx
    implements PrintServiceAttribute {

    privbte stbtic finbl long seriblVersionUID = -2700555589688535545L;

    /**
     * The printer is not cbpbble of bny type of color printing.
     */
    public stbtic finbl ColorSupported NOT_SUPPORTED = new ColorSupported(0);

    /**
     * The printer is cbpbble of some type of color printing, such bs
     * highlight color or full process color.
     */
    public stbtic finbl ColorSupported SUPPORTED = new ColorSupported(1);

    /**
     * Construct b new color supported enumerbtion vblue with the given
     * integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected ColorSupported(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {"not-supported",
                                                   "supported"};

    privbte stbtic finbl ColorSupported[] myEnumVblueTbble = {NOT_SUPPORTED,
                                                              SUPPORTED};

    /**
     * Returns the string tbble for clbss ColorSupported.
     */
    protected String[] getStringTbble() {
        return myStringTbble;
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss ColorSupported.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return myEnumVblueTbble;
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss ColorSupported, the cbtegory is clbss ColorSupported itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return ColorSupported.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss ColorSupported, the cbtegory nbme is <CODE>"color-supported"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "color-supported";
    }

}
