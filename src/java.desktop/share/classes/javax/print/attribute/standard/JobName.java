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
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss JobNbme is b printing bttribute clbss, b text bttribute, thbt specifies
 * the nbme of b print job. A job's nbme is bn brbitrbry string defined by the
 * client. It does not need to be unique between different jobs. A Print Job's
 * JobNbme bttribute is set to the vblue supplied by the client in the Print
 * Request's bttribute set. If, however, the client does not supply b JobNbme
 * bttribute in the Print Request, the printer, when it crebtes the Print Job,
 * must generbte b JobNbme. The printer should generbte the vblue of the Print
 * Job's JobNbme bttribute from the first of the following sources thbt produces
 * b vblue: (1) the {@link DocumentNbme DocumentNbme} bttribute of the first (or
 * only) doc in the job, (2) the URL of the first (or only) doc in the job, if
 * the doc's print dbtb representbtion object is b URL, or (3) bny other piece
 * of Print Job specific bnd/or document content informbtion.
 * <P>
 * <B>IPP Compbtibility:</B> The string vblue gives the IPP nbme vblue. The
 * locble gives the IPP nbturbl lbngubge. The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> gives the IPP bttribute nbme.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss JobNbme extends TextSyntbx
        implements PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = 4660359192078689545L;

    /**
     * Constructs b new job nbme bttribute with the given job nbme bnd locble.
     *
     * @pbrbm  jobNbme  Job nbme.
     * @pbrbm  locble   Nbturbl lbngubge of the text string. null
     * is interpreted to mebn the defbult locble bs returned
     * by <code>Locble.getDefbult()</code>
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>jobNbme</CODE> is null.
     */
    public JobNbme(String jobNbme, Locble locble) {
        super (jobNbme, locble);
    }

    /**
     * Returns whether this job nbme bttribute is equivblent to the pbssed in
     * object. To be equivblent, bll of the following conditions must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss JobNbme.
     * <LI>
     * This job nbme bttribute's underlying string bnd <CODE>object</CODE>'s
     * underlying string bre equbl.
     * <LI>
     * This job nbme bttribute's locble bnd <CODE>object</CODE>'s locble bre
     * equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this job nbme
     *          bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls(object) && object instbnceof JobNbme);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss JobNbme, the cbtegory is clbss JobNbme itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return JobNbme.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss JobNbme, the cbtegory nbme is <CODE>"job-nbme"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "job-nbme";
    }

}
