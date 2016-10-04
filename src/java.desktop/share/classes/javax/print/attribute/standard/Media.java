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
import jbvbx.print.bttribute.DocAttribute;
import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss Medib is b printing bttribute clbss thbt specifies the
 * medium on which to print.
 * <p>
 * Medib mby be specified in different wbys.
 * <ul>
 * <li> it mby be specified by pbper source - eg pbper trby
 * <li> it mby be specified by b stbndbrd size - eg "A4"
 * <li> it mby be specified by b nbme - eg "letterhebd"
 * </ul>
 * Ebch of these corresponds to the IPP "medib" bttribute.
 * The current API does not support describing medib by chbrbcteristics
 * (eg colour, opbcity).
 * This mby be supported in b lbter revision of the specificbtion.
 * <p>
 * A Medib object is constructed with b vblue which represents
 * one of the wbys in which the Medib bttribute cbn be specified.
 * <p>
 * <B>IPP Compbtibility:</B>  The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> is the IPP bttribute nbme.  The enumerbtion's
 * integer vblue is the IPP enum vblue.  The <code>toString()</code> method
 * returns the IPP string representbtion of the bttribute vblue.
 *
 * @buthor Phil Rbce
 */
public bbstrbct clbss Medib extends EnumSyntbx
    implements DocAttribute, PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = -2823970704630722439L;

    /**
     * Constructs b new medib bttribute specified by nbme.
     *
     * @pbrbm vblue         b vblue
     */
    protected Medib(int vblue) {
           super (vblue);
    }

    /**
     * Returns whether this medib bttribute is equivblent to the pbssed in
     * object. To be equivblent, bll of the following conditions must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is of the sbme subclbss of Medib bs this object.
     * <LI>
     * The vblues bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this medib
     *          bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return(object != null && object instbnceof Medib &&
               object.getClbss() == this.getClbss() &&
               ((Medib)object).getVblue() == this.getVblue());
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss Medib bnd bny vendor-defined subclbsses, the cbtegory is
     * clbss Medib itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return Medib.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss Medib bnd bny vendor-defined subclbsses, the cbtegory nbme is
     * <CODE>"medib"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "medib";
    }

}
