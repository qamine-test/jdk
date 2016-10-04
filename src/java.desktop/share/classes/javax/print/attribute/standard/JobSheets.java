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

import jbvb.util.Locble;

import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss JobSheets is b printing bttribute clbss, bn enumerbtion, thbt
 * determines which job stbrt bnd end sheets, if bny, must be printed with b
 * job. Clbss JobSheets declbres keywords for stbndbrd job sheets vblues.
 * Implementbtion- or site-defined nbmes for b job sheets bttribute mby blso be
 * crebted by defining b subclbss of clbss JobSheets.
 * <P>
 * The effect of b JobSheets bttribute on multidoc print jobs (jobs with
 * multiple documents) mby be bffected by the {@link MultipleDocumentHbndling
 * MultipleDocumentHbndling} job bttribute, depending on the mebning of the
 * pbrticulbr JobSheets vblue.
 * <P>
 * <B>IPP Compbtibility:</B>  The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> is the IPP bttribute nbme.  The
 * enumerbtion's integer vblue is the IPP enum vblue.  The
 * <code>toString()</code> method returns the IPP string representbtion of
 * the bttribute vblue. For b subclbss, the bttribute vblue must be
 * locblized to give the IPP nbme bnd nbturbl lbngubge vblues.
 *
 * @buthor  Albn Kbminsky
 */
public clbss JobSheets extends EnumSyntbx
        implements PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = -4735258056132519759L;

    /**
     * No job sheets bre printed.
     */
    public stbtic finbl JobSheets NONE = new JobSheets(0);

    /**
     * One or more site specific stbndbrd job sheets bre printed. e.g. b
     * single stbrt sheet is printed, or both stbrt bnd end sheets bre
     * printed.
     */
    public stbtic finbl JobSheets STANDARD = new JobSheets(1);

    /**
     * Construct b new job sheets enumerbtion vblue with the given integer
     * vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected JobSheets(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
        "none",
        "stbndbrd"
    };

    privbte stbtic finbl JobSheets[] myEnumVblueTbble = {
        NONE,
        STANDARD
    };

    /**
     * Returns the string tbble for clbss JobSheets.
     */
    protected String[] getStringTbble() {
        return myStringTbble.clone();
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss JobSheets.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return (EnumSyntbx[])myEnumVblueTbble.clone();
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss JobSheets bnd bny vendor-defined subclbsses, the cbtegory is
     * clbss JobSheets itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return JobSheets.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss JobSheets bnd bny vendor-defined subclbsses, the cbtegory
     * nbme is <CODE>"job-sheets"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "job-sheets";
    }

}
