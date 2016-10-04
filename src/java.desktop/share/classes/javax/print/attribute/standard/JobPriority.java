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
import jbvbx.print.bttribute.IntegerSyntbx;
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss JobPriority is bn integer vblued printing bttribute clbss thbt
 * specifies b print job's priority.
 * <P>
 * If b JobPriority bttribute is specified for b Print Job, it specifies b
 * priority for scheduling the job. A higher vblue specifies b higher priority.
 * The vblue 1 indicbtes the lowest possible priority. The vblue 100 indicbtes
 * the highest possible priority. Among those jobs thbt bre rebdy to print, b
 * printer must print bll jobs with b priority vblue of <I>n</I> before printing
 * those with b priority vblue of <I>n</I>-1 for bll <I>n.</I>
 * <P>
 * If the client does not specify b JobPriority bttribute for b Print Job bnd
 * the printer does support the JobPriority bttribute, the printer must use bn
 * implementbtion-defined defbult JobPriority vblue.
 * <P>
 * The client cbn blwbys specify bny job priority vblue from 1 to 100 for b job.
 * However, b Print Service instbnce mby support fewer thbn 100 different
 * job priority levels. If this is the cbse, the Print Service instbnce
 * butombticblly mbps the client-specified job priority vblue to one of the
 * supported job priority levels, dividing the 100 job priority vblues equblly
 * bmong the bvbilbble job priority levels.
 * <P>
 * <B>IPP Compbtibility:</B> The integer vblue gives the IPP integer vblue. The
 * cbtegory nbme returned by <CODE>getNbme()</CODE> gives the IPP bttribute
 * nbme.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss JobPriority extends IntegerSyntbx
    implements PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = -4599900369040602769L;

    /**
     * Construct b new job priority bttribute with the given integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     *
     * @exception  IllegblArgumentException
     *     (Unchecked exception) Thrown if <CODE>vblue</CODE> is less thbn 1
     *     or grebter thbn 100.
     */
    public JobPriority(int vblue) {
        super (vblue, 1, 100);
    }

    /**
     * Returns whether this job priority bttribute is equivblent to the pbssed
     * in object. To be equivblent, bll of the following conditions must be
     * true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss JobPriority.
     * <LI>
     * This job priority bttribute's vblue bnd <CODE>object</CODE>'s vblue
     * bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this job
     *          priority bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls (object) && object instbnceof JobPriority);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss JobPriority, the cbtegory is clbss JobPriority itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return JobPriority.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss JobPriority, the cbtegory nbme is <CODE>"job-priority"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "job-priority";
    }

}
