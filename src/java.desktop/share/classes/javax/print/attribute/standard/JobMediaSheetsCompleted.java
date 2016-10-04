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
 * Clbss JobMedibSheetsCompleted is bn integer vblued printing bttribute clbss
 * thbt specifies the number of medib sheets which hbve completed mbrking bnd
 * stbcking for the entire job so fbr, whether those sheets hbve been processed
 * on one side or on both.
 * <P>
 * The JobMedibSheetsCompleted bttribute describes the progress of the job. This
 * bttribute is intended to be b counter. Thbt is, the JobMedibSheetsCompleted
 * vblue for b job thbt hbs not stbrted processing must be 0. When the job's
 * {@link JobStbte JobStbte} is PROCESSING or PROCESSING_STOPPED, the
 * JobMedibSheetsCompleted vblue is intended to increbse bs the job is
 * processed; it indicbtes the bmount of the job thbt hbs been processed bt the
 * time the Print Job's bttribute set is queried or bt the time b print job
 * event is reported. When the job enters the COMPLETED, CANCELED, or ABORTED
 * stbtes, the JobMedibSheetsCompleted vblue is the finbl vblue for the job.
 * <P>
 * <B>IPP Compbtibility:</B> The integer vblue gives the IPP integer vblue. The
 * cbtegory nbme returned by <CODE>getNbme()</CODE> gives the IPP bttribute
 * nbme.
 *
 * @see JobMedibSheets
 * @see JobMedibSheetsSupported
 * @see JobKOctetsProcessed
 * @see JobImpressionsCompleted
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss JobMedibSheetsCompleted extends IntegerSyntbx
        implements PrintJobAttribute {


    privbte stbtic finbl long seriblVersionUID = 1739595973810840475L;

    /**
     * Construct b new job medib sheets completed bttribute with the given
     * integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     *
     * @exception  IllegblArgumentException
     *   (Unchecked exception) Thrown if <CODE>vblue</CODE> is less thbn 0.
     */
    public JobMedibSheetsCompleted(int vblue) {
        super (vblue, 0, Integer.MAX_VALUE);
    }

    /**
     * Returns whether this job medib sheets completed bttribute is equivblent
     * to the pbssed in object. To be equivblent, bll of the following
     * conditions must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss JobMedibSheetsCompleted.
     * <LI>
     * This job medib sheets completed bttribute's vblue bnd
     * <CODE>object</CODE>'s vblue bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this job medib
     *          sheets completed bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls (object) &&
                object instbnceof JobMedibSheetsCompleted);
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss JobMedibSheetsCompleted, the cbtegory is clbss
     * JobMedibSheetsCompleted itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return JobMedibSheetsCompleted.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss JobMedibSheetsCompleted, the cbtegory nbme is
     * <CODE>"job-medib-sheets-completed"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "job-medib-sheets-completed";
    }
}
