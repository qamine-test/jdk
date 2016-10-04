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
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss JobImpressionsCompleted is bn integer vblued printing bttribute clbss
 * thbt specifies the number of impressions completed for the job so fbr. For
 * printing devices, the impressions completed includes interpreting, mbrking,
 * bnd stbcking the output.
 * <P>
 * The JobImpressionsCompleted bttribute describes the progress of the job. This
 * bttribute is intended to be b counter. Thbt is, the JobImpressionsCompleted
 * vblue for b job thbt hbs not stbrted processing must be 0. When the job's
 * {@link JobStbte JobStbte} is PROCESSING or PROCESSING_STOPPED, the
 * JobImpressionsCompleted vblue is intended to increbse bs the job is
 * processed; it indicbtes the bmount of the job thbt hbs been processed bt the
 * time the Print Job's bttribute set is queried or bt the time b print job
 * event is reported. When the job enters the COMPLETED, CANCELED, or ABORTED
 * stbtes, the JobImpressionsCompleted vblue is the finbl vblue for the job.
 * <P>
 * <B>IPP Compbtibility:</B> The integer vblue gives the IPP integer vblue. The
 * cbtegory nbme returned by <CODE>getNbme()</CODE> gives the IPP bttribute
 * nbme.
 *
 * @see JobImpressions
 * @see JobImpressionsSupported
 * @see JobKOctetsProcessed
 * @see JobMedibSheetsCompleted
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss JobImpressionsCompleted extends IntegerSyntbx
        implements PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = 6722648442432393294L;

    /**
     * Construct b new job impressions completed bttribute with the given
     * integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     *
     * @exception  IllegblArgumentException
     *  (Unchecked exception) Thrown if <CODE>vblue</CODE> is less thbn 0.
     */
    public JobImpressionsCompleted(int vblue) {
        super (vblue, 0, Integer.MAX_VALUE);
    }

    /**
     * Returns whether this job impressions completed bttribute is equivblent
     * tp the pbssed in object. To be equivblent, bll of the following
     * conditions must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss JobImpressionsCompleted.
     * <LI>
     * This job impressions completed bttribute's vblue bnd
     * <CODE>object</CODE>'s vblue bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this job
     *          impressions completed bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return(super.equbls (object) &&
               object instbnceof JobImpressionsCompleted);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss JobImpressionsCompleted, the cbtegory is clbss
     * JobImpressionsCompleted itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return JobImpressionsCompleted.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss JobImpressionsCompleted, the cbtegory nbme is
     * <CODE>"job-impressions-completed"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "job-impressions-completed";
    }

}
