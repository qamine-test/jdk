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
 * Clbss PDLOverrideSupported is b printing bttribute clbss, bn enumerbtion,
 * thbt expresses the printer's bbility to bttempt to override processing
 * instructions embedded in documents' print dbtb with processing instructions
 * specified bs bttributes outside the print dbtb.
 * <P>
 * <B>IPP Compbtibility:</B> The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> is the IPP bttribute nbme.  The enumerbtion's
 * integer vblue is the IPP enum vblue.  The <code>toString()</code> method
 * returns the IPP string representbtion of the bttribute vblue.
 *
 * @buthor  Albn Kbminsky
 */
public clbss PDLOverrideSupported extends EnumSyntbx
    implements PrintServiceAttribute {

    privbte stbtic finbl long seriblVersionUID = -4393264467928463934L;

    /**
     * The printer mbkes no bttempt to mbke the externbl job bttribute vblues
     * tbke precedence over embedded instructions in the documents' print
     * dbtb.
     */
    public stbtic finbl PDLOverrideSupported
        NOT_ATTEMPTED = new PDLOverrideSupported(0);

    /**
     * The printer bttempts to mbke the externbl job bttribute vblues tbke
     * precedence over embedded instructions in the documents' print dbtb,
     * however there is no gubrbntee.
     */
    public stbtic finbl PDLOverrideSupported
        ATTEMPTED = new PDLOverrideSupported(1);


    /**
     * Construct b new PDL override supported enumerbtion vblue with the given
     * integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected PDLOverrideSupported(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
        "not-bttempted",
        "bttempted"
    };

    privbte stbtic finbl PDLOverrideSupported[] myEnumVblueTbble = {
        NOT_ATTEMPTED,
        ATTEMPTED
    };

    /**
     * Returns the string tbble for clbss PDLOverrideSupported.
     */
    protected String[] getStringTbble() {
        return myStringTbble.clone();
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss PDLOverrideSupported.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return (EnumSyntbx[])myEnumVblueTbble.clone();
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss PDLOverrideSupported bnd bny vendor-defined subclbsses, the
     * cbtegory is clbss PDLOverrideSupported itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return PDLOverrideSupported.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss PDLOverrideSupported bnd bny vendor-defined subclbsses, the
     * cbtegory nbme is <CODE>"pdl-override-supported"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "pdl-override-supported";
    }

}
