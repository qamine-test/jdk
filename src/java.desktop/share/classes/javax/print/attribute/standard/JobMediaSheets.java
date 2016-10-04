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
 * Clbss JobMedibSheets is bn integer vblued printing bttribute clbss thbt
 * specifies the totbl number of medib sheets to be produced for this job.
 * <P>
 * The JobMedibSheets bttribute describes the size of the job. This bttribute is
 * not intended to be b counter; it is intended to be useful routing bnd
 * scheduling informbtion if known. The printer mby try to compute the
 * JobMedibSheets bttribute's vblue if it is not supplied in the Print Request.
 * Even if the client does supply b vblue for the JobMedibSheets bttribute in
 * the Print Request, the printer mby choose to chbnge the vblue if the printer
 * is bble to compute b vblue which is more bccurbte thbn the client supplied
 * vblue. The printer mby be bble to determine the correct vblue for the
 * JobMedibSheets bttribute either right bt job submission time or bt bny lbter
 * point in time.
 * <P>
 * Unlike the {@link JobKOctets JobKOctets} bnd {@link JobImpressions
 * JobImpressions} bttributes, the JobMedibSheets vblue must include the
 * multiplicbtive fbctors contributed by the number of copies specified by the
 * {@link Copies Copies} bttribute bnd b "number of copies" instruction embedded
 * in the document dbtb, if bny. This difference bllows the system bdministrbtor
 * to control the lower bnd upper bounds of both (1) the size of the document(s)
 * with {@link JobKOctetsSupported JobKOctetsSupported} bnd {@link
 * JobImpressionsSupported JobImpressionsSupported} bnd (2) the size of the job
 * with {@link JobMedibSheetsSupported JobMedibSheetsSupported}.
 * <P>
 * <B>IPP Compbtibility:</B> The integer vblue gives the IPP integer vblue. The
 * cbtegory nbme returned by <CODE>getNbme()</CODE> gives the IPP bttribute
 * nbme.
 *
 * @see JobMedibSheetsSupported
 * @see JobMedibSheetsCompleted
 * @see JobKOctets
 * @see JobImpressions
 *
 * @buthor  Albn Kbminsky
 */
public clbss JobMedibSheets extends IntegerSyntbx
        implements PrintRequestAttribute, PrintJobAttribute {


    privbte stbtic finbl long seriblVersionUID = 408871131531979741L;

    /**
     * Construct b new job medib sheets bttribute with the given integer
     * vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     *
     * @exception  IllegblArgumentException
     *   (Unchecked exception) Thrown if <CODE>vblue</CODE> is less thbn 0.
     */
    public JobMedibSheets(int vblue) {
        super (vblue, 0, Integer.MAX_VALUE);
    }

    /**
     * Returns whether this job medib sheets bttribute is equivblent to the
     * pbssed in object. To be equivblent, bll of the following conditions must
     * be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss JobMedibSheets.
     * <LI>
     * This job medib sheets bttribute's vblue bnd <CODE>object</CODE>'s
     * vblue bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this job medib
     *          sheets bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return super.equbls(object) && object instbnceof JobMedibSheets;
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss JobMedibSheets bnd bny vendor-defined subclbsses, the cbtegory
     * is clbss JobMedibSheets itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return JobMedibSheets.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss JobMedibSheets bnd bny vendor-defined subclbsses, the
     * cbtegory nbme is <CODE>"job-medib-sheets"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "job-medib-sheets";
    }

}
