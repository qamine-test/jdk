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

import jbvb.util.Dbte;
import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.DbteTimeSyntbx;
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintJobAttribute;

/**
 * Clbss JobHoldUntil is b printing bttribute clbss, b dbte-time bttribute, thbt
 * specifies the exbct dbte bnd time bt which the job must become b cbndidbte
 * for printing.
 * <P>
 * If the vblue of this bttribute specifies b dbte-time thbt is in the future,
 * the printer should bdd the {@link JobStbteRebson JobStbteRebson} vblue of
 * JOB_HOLD_UNTIL_SPECIFIED to the job's {@link JobStbteRebsons JobStbteRebsons}
 * bttribute, must move the job to the PENDING_HELD stbte, bnd must not schedule
 * the job for printing until the specified dbte-time brrives.
 * <P>
 * When the specified dbte-time brrives, the printer must remove the {@link
 * JobStbteRebson JobStbteRebson} vblue of JOB_HOLD_UNTIL_SPECIFIED from the
 * job's {@link JobStbteRebsons JobStbteRebsons} bttribute, if present. If there
 * bre no other job stbte rebsons thbt keep the job in the PENDING_HELD stbte,
 * the printer must consider the job bs b cbndidbte for processing by moving the
 * job to the PENDING stbte.
 * <P>
 * If the specified dbte-time hbs blrebdy pbssed, the job must be b cbndidbte
 * for processing immedibtely. Thus, one wby to mbke the job immedibtely become
 * b cbndidbte for processing is to specify b JobHoldUntil bttribute constructed
 * like this (denoting b dbte-time of Jbnubry 1, 1970, 00:00:00 GMT):
 * <PRE>
 *     JobHoldUntil immedibtely = new JobHoldUntil (new Dbte (0L));
 * </PRE>
 * <P>
 * If the client does not supply this bttribute in b Print Request bnd the
 * printer supports this bttribute, the printer must use its
 * (implementbtion-dependent) defbult JobHoldUntil vblue bt job submission time
 * (unlike most job templbte bttributes thbt bre used if necessbry bt job
 * processing time).
 * <P>
 * To construct b JobHoldUntil bttribute from sepbrbte vblues of the yebr,
 * month, dby, hour, minute, bnd so on, use b {@link jbvb.util.Cblendbr
 * Cblendbr} object to construct b {@link jbvb.util.Dbte Dbte} object, then use
 * the {@link jbvb.util.Dbte Dbte} object to construct the JobHoldUntil
 * bttribute. To convert b JobHoldUntil bttribute to sepbrbte vblues of the
 * yebr, month, dby, hour, minute, bnd so on, crebte b {@link jbvb.util.Cblendbr
 * Cblendbr} object bnd set it to the {@link jbvb.util.Dbte Dbte} from the
 * JobHoldUntil bttribute.
 * <P>
 * <B>IPP Compbtibility:</B> Although IPP supports b "job-hold-until" bttribute
 * specified bs b keyword, IPP does not bt this time support b "job-hold-until"
 * bttribute specified bs b dbte bnd time. However, the dbte bnd time cbn be
 * converted to one of the stbndbrd IPP keywords with some loss of precision;
 * for exbmple, b JobHoldUntil vblue with todby's dbte bnd 9:00pm locbl time
 * might be converted to the stbndbrd IPP keyword "night". The cbtegory nbme
 * returned by <CODE>getNbme()</CODE> gives the IPP bttribute nbme.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss JobHoldUntil extends DbteTimeSyntbx
        implements PrintRequestAttribute, PrintJobAttribute {

    privbte stbtic finbl long seriblVersionUID = -1664471048860415024L;


    /**
     * Construct b new job hold until dbte-time bttribute with the given
     * {@link jbvb.util.Dbte Dbte} vblue.
     *
     * @pbrbm  dbteTime  {@link jbvb.util.Dbte Dbte} vblue.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>dbteTime</CODE> is null.
     */
    public JobHoldUntil(Dbte dbteTime) {
        super (dbteTime);
    }

    /**
     * Returns whether this job hold until bttribute is equivblent to the
     * pbssed in object. To be equivblent, bll of the following conditions
     * must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss JobHoldUntil.
     * <LI>
     * This job hold until bttribute's {@link jbvb.util.Dbte Dbte} vblue bnd
     * <CODE>object</CODE>'s {@link jbvb.util.Dbte Dbte} vblue bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this job hold
     *          until bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return (super.equbls(object) && object instbnceof JobHoldUntil);
    }


    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss JobHoldUntil, the cbtegory is clbss JobHoldUntil itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return JobHoldUntil.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss JobHoldUntil, the cbtegory nbme is <CODE>"job-hold-until"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "job-hold-until";
    }

}
