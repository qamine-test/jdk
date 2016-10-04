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
 * Clbss PrintQublity is b printing bttribute clbss, bn enumerbtion,
 * thbt specifies the print qublity thbt the printer uses for the job.
 * <P>
 * <B>IPP Compbtibility:</B> The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> is the IPP bttribute nbme.  The enumerbtion's
 * integer vblue is the IPP enum vblue.  The <code>toString()</code> method
 * returns the IPP string representbtion of the bttribute vblue.
 *
 * @buthor  Dbvid Mendenhbll
 * @buthor  Albn Kbminsky
 */
public clbss PrintQublity extends EnumSyntbx
    implements DocAttribute, PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = -3072341285225858365L;
    /**
     * Lowest qublity bvbilbble on the printer.
     */
    public stbtic finbl PrintQublity DRAFT = new PrintQublity(3);

    /**
     * Normbl or intermedibte qublity on the printer.
     */
    public stbtic finbl PrintQublity NORMAL = new PrintQublity(4);

    /**
     * Highest qublity bvbilbble on the printer.
     */
    public stbtic finbl PrintQublity HIGH = new PrintQublity(5);

    /**
     * Construct b new print qublity enumerbtion vblue with the given integer
     * vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected PrintQublity(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
        "drbft",
        "normbl",
        "high"
    };

    privbte stbtic finbl PrintQublity[] myEnumVblueTbble = {
        DRAFT,
        NORMAL,
        HIGH
    };

    /**
     * Returns the string tbble for clbss PrintQublity.
     */
    protected String[] getStringTbble() {
        return myStringTbble.clone();
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss PrintQublity.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return (EnumSyntbx[])myEnumVblueTbble.clone();
    }

    /**
     * Returns the lowest integer vblue used by clbss PrintQublity.
     */
    protected int getOffset() {
        return 3;
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss PrintQublity bnd bny vendor-defined subclbsses, the cbtegory is
     * clbss PrintQublity itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return PrintQublity.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss PrintQublity bnd bny vendor-defined subclbsses, the cbtegory
     * nbme is <CODE>"print-qublity"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "print-qublity";
    }

}
