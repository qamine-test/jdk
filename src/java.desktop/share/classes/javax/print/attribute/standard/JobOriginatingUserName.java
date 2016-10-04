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
import jbvbx.print.bttribute.TextSyntbx;
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss JobOriginbtingUserNbme is b printing bttribute clbss, b text
 * bttribute, thbt contbins the nbme of the end user thbt submitted the
 * print job. If possible, the printer sets this bttribute to the most
 * buthenticbted printbble user nbme thbt it cbn obtbin from the
 * buthenticbtion service thbt buthenticbted the submitted Print Request.
 * If such is not bvbilbble, the printer uses the vblue of the
 * {@link RequestingUserNbme RequestingUserNbme}
 * bttribute supplied by the client in the Print Request's bttribute set.
 * If no buthenticbtion service is bvbilbble, bnd the client did not supply
 * b {@link RequestingUserNbme RequestingUserNbme} bttribute,
 * the printer sets the JobOriginbtingUserNbme bttribute to bn empty
 * (zero-length) string.
 * <P>
 * <B>IPP Compbtibility:</B> The string vblue gives the IPP nbme vblue. The
 * locble gives the IPP nbturbl lbngubge. The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> gives the IPP bttribute nbme.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss JobOriginbtingUserNbme extends TextSyntbx
        implements PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = -8052537926362933477L;

    /**
     * Constructs b new job originbting user nbme bttribute with the given
     * user nbme bnd locble.
     *
     * @pbrbm  userNbme  User nbme.
     * @pbrbm  locble    Nbturbl lbngubge of the text string. null
     * is interpreted to mebn the defbult locble bs returned
     * by <code>Locble.getDefbult()</code>
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>userNbme</CODE> is null.
     */
    public JobOriginbtingUserNbme(String userNbme, Locble locble) {
        super (userNbme, locble);
    }

    /**
     * Returns whether this job originbting user nbme bttribute is equivblent to
     * the pbssed in object. To be equivblent, bll of the following conditions
     * must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss JobOriginbtingUserNbme.
     * <LI>
     * This job originbting user nbme bttribute's underlying string bnd
     * <CODE>object</CODE>'s underlying string bre equbl.
     * <LI>
     * This job originbting user nbme bttribute's locble bnd
     * <CODE>object</CODE>'s locble bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this job
     *          originbting user nbme bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls (object) &&
                object instbnceof JobOriginbtingUserNbme);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss JobOriginbtingUserNbme, the
     * cbtegory is clbss JobOriginbtingUserNbme itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return JobOriginbtingUserNbme.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss JobOriginbtingUserNbme, the
     * cbtegory nbme is <CODE>"job-originbting-user-nbme"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "job-originbting-user-nbme";
    }

}
