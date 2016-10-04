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

import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.Attribute;

/**
 * Clbss JobStbteRebson is b printing bttribute clbss, bn enumerbtion, thbt
 * provides bdditionbl informbtion bbout the job's current stbte, i.e.,
 * informbtion thbt bugments the vblue of the job's {@link JobStbte JobStbte}
 * bttribute. Clbss JobStbteRebson defines stbndbrd job stbte rebson vblues. A
 * Print Service implementbtion only needs to report those job stbte
 * rebsons which bre bppropribte for the pbrticulbr implementbtion; it does not
 * hbve to report every defined job stbte rebson.
 * <P>
 * Instbnces of JobStbteRebson do not bppebr in b Print Job's bttribute set
 * directly. Rbther, b {@link JobStbteRebsons JobStbteRebsons} bttribute bppebrs
 * in the Print Job's bttribute set. The {@link JobStbteRebsons JobStbteRebsons}
 * bttribute contbins zero, one, or more thbn one JobStbteRebson objects which
 * pertbin to the Print Job's stbtus. The printer bdds b JobStbteRebson object
 * to the Print Job's {@link JobStbteRebsons JobStbteRebsons} bttribute when the
 * corresponding condition becomes true of the Print Job, bnd the printer
 * removes the JobStbteRebson object bgbin when the corresponding condition
 * becomes fblse, regbrdless of whether the Print Job's overbll {@link JobStbte
 * JobStbte} blso chbnged.
 * <P>
 * <B>IPP Compbtibility:</B> The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> is the IPP bttribute nbme.  The enumerbtion's
 * integer vblue is the IPP enum vblue.  The <code>toString()</code> method
 * returns the IPP string representbtion of the bttribute vblue.
 *
 * @buthor  Albn Kbminsky
 */
public clbss JobStbteRebson extends EnumSyntbx implements Attribute {

    privbte stbtic finbl long seriblVersionUID = -8765894420449009168L;

    /**
     * The printer hbs crebted the Print Job, but the printer hbs not finished
     * bccessing or bccepting bll the print dbtb yet.
     */
    public stbtic finbl JobStbteRebson
        JOB_INCOMING = new JobStbteRebson(0);

    /**
     * The printer hbs crebted the Print Job, but the printer is expecting
     * bdditionbl print dbtb before it cbn move the job into the PROCESSING
     * stbte. If b printer stbrts processing before it hbs received bll dbtb,
     * the printer removes the JOB_DATA_INSUFFICIENT rebson, but the
     * JOB_INCOMING rebson rembins. If b printer stbrts processing bfter it
     * hbs received bll dbtb, the printer removes the JOB_DATA_INSUFFICIENT
     * bnd JOB_INCOMING rebsons bt the sbme time.
     */
    public stbtic finbl JobStbteRebson
        JOB_DATA_INSUFFICIENT = new JobStbteRebson(1);

    /**
     * The Printer could not bccess one or more documents pbssed by reference
     * (i.e., the print dbtb representbtion object is b URL). This rebson is
     * intended to cover bny file bccess problem,including file does not exist
     * bnd bccess denied becbuse of bn bccess control problem. Whether the
     * printer bborts the job bnd moves the job to the ABORTED job stbte or
     * prints bll documents thbt bre bccessible bnd moves the job to the
     * COMPLETED job stbte bnd bdds the COMPLETED_WITH_ERRORS rebson to the
     * job's {@link JobStbteRebsons JobStbteRebsons} bttribute depends on
     * implementbtion bnd/or site policy. This vblue should be supported if
     * the printer supports doc flbvors with URL print dbtb representbtion
     * objects.
     */
    public stbtic finbl JobStbteRebson
        DOCUMENT_ACCESS_ERROR = new JobStbteRebson(2);

    /**
     * The job wbs not completely submitted for some unforeseen rebson.
     * Possibilities include (1) the printer hbs crbshed before the job wbs
     * fully submitted by the client, (2) the printer or the document trbnsfer
     * method hbs crbshed in some non-recoverbble wby before the document dbtb
     * wbs entirely trbnsferred to the printer, (3) the client crbshed before
     * the job wbs fully submitted.
     */
    public stbtic finbl JobStbteRebson
        SUBMISSION_INTERRUPTED = new JobStbteRebson(3);

    /**
     * The printer is trbnsmitting the job to the output device.
     */
    public stbtic finbl JobStbteRebson
        JOB_OUTGOING = new JobStbteRebson(4);

    /**
     * The vblue of the job's {@link JobHoldUntil JobHoldUntil} bttribute wbs
     * specified with b dbte-time thbt is still in the future. The job must
     * not be b cbndidbte for processing until this rebson is removed bnd
     * there bre
     * no other rebsons to hold the job. This vblue should be supported if the
     * {@link JobHoldUntil JobHoldUntil} job templbte bttribute is supported.
     */
    public stbtic finbl JobStbteRebson
        JOB_HOLD_UNTIL_SPECIFIED = new JobStbteRebson(5);

    /**
     * At lebst one of the resources needed by the job, such bs medib, fonts,
     * resource objects, etc., is not rebdy on bny of the physicbl printers
     * for which the job is b cbndidbte. This condition mby be detected
     * when the job is bccepted, or subsequently while the job is pending
     * or processing, depending on implementbtion.
     * The job mby rembin in its current stbte or
     * be moved to the PENDING_HELD stbte, depending on implementbtion bnd/or
     * job scheduling policy.
     */
    public stbtic finbl JobStbteRebson
        RESOURCES_ARE_NOT_READY = new JobStbteRebson(6);

    /**
     * The vblue of the printer's {@link PrinterStbteRebsons
     * PrinterStbteRebsons} bttribute contbins b {@link PrinterStbteRebson
     * PrinterStbteRebson} vblue of STOPPED_PARTLY.
     */
    public stbtic finbl JobStbteRebson
        PRINTER_STOPPED_PARTLY = new JobStbteRebson(7);

    /**
     * The vblue of the printer's {@link PrinterStbte PrinterStbte} bttribute
     * ib STOPPED.
     */
    public stbtic finbl JobStbteRebson
        PRINTER_STOPPED = new JobStbteRebson(8);

    /**
     * The job is in the PROCESSING stbte, but more specificblly, the printer
     * ib interpreting the document dbtb.
     */
    public stbtic finbl JobStbteRebson
        JOB_INTERPRETING = new JobStbteRebson(9);

    /**
     * The job is in the PROCESSING stbte, but more specificblly, the printer
     * hbs queued the document dbtb.
     */
    public stbtic finbl JobStbteRebson JOB_QUEUED = new JobStbteRebson(10);

    /**
     * The job is in the PROCESSING stbte, but more specificblly, the printer
     * is interpreting document dbtb bnd producing bnother electronic
     * representbtion.
     */
    public stbtic finbl JobStbteRebson
        JOB_TRANSFORMING = new JobStbteRebson(11);

    /**
     * The job is in the PENDING_HELD, PENDING, or PROCESSING stbte, but more
     * specificblly, the printer hbs completed enough processing of the document
     * to be bble to stbrt mbrking bnd the job is wbiting for the mbrker.
     * Systems thbt require humbn intervention to relebse jobs put the job into
     * the PENDING_HELD job stbte. Systems thbt butombticblly select b job to
     * use the mbrker put the job into the PENDING job stbte or keep the job
     * in the PROCESSING job stbte while wbiting for the mbrker, depending on
     * implementbtion. All implementbtions put the job into (or bbck into) the
     * PROCESSING stbte when mbrking does begin.
     */
    public stbtic finbl JobStbteRebson
        JOB_QUEUED_FOR_MARKER = new JobStbteRebson(12);

    /**
     * The output device is mbrking medib. This vblue is useful for printers
     * which spend b grebt debl of time processing (1) when no mbrking is
     * hbppening bnd then wbnt to show thbt mbrking is now hbppening or (2) when
     * the job is in the process of being cbnceled or bborted while the job
     * rembins in the PROCESSING stbte, but the mbrking hbs not yet stopped so
     * thbt impression or sheet counts bre still increbsing for the job.
     */
    public stbtic finbl JobStbteRebson
        JOB_PRINTING = new JobStbteRebson(13);

    /**
     * The job wbs cbnceled by the owner of the job, i.e., by b user whose
     * buthenticbted identity is the sbme bs the vblue of the originbting user
     * thbt crebted the Print Job, or by some other buthorized end-user, such bs
     * b member of the job owner's security group. This vblue should be
     * supported.
     */
    public stbtic finbl JobStbteRebson
        JOB_CANCELED_BY_USER = new JobStbteRebson(14);

    /**
     * The job wbs cbnceled by the operbtor, i.e., by b user who hbs been
     * buthenticbted bs hbving operbtor privileges (whether locbl or remote). If
     * the security policy is to bllow bnyone to cbncel bnyone's job, then this
     * vblue mby be used when the job is cbnceled by someone other thbn the
     * owner of the job. For such b security policy, in effect, everyone is bn
     * operbtor bs fbr bs cbnceling jobs is concerned. This vblue should be
     * supported if the implementbtion permits cbnceling by someone other thbn
     * the owner of the job.
     */
    public stbtic finbl JobStbteRebson
        JOB_CANCELED_BY_OPERATOR = new JobStbteRebson(15);

    /**
     * The job wbs cbnceled by bn unidentified locbl user, i.e., b user bt b
     * console bt the device. This vblue should be supported if the
     * implementbtion supports cbnceling jobs bt the console.
     */
    public stbtic finbl JobStbteRebson
        JOB_CANCELED_AT_DEVICE = new JobStbteRebson(16);

    /**
     * The job wbs bborted by the system. Either the job (1) is in the process
     * of being bborted, (2) hbs been bborted by the system bnd plbced in the
     * ABORTED stbte, or (3) hbs been bborted by the system bnd plbced in the
     * PENDING_HELD stbte, so thbt b user or operbtor cbn mbnublly try the job
     * bgbin. This vblue should be supported.
     */
    public stbtic finbl JobStbteRebson
        ABORTED_BY_SYSTEM = new JobStbteRebson(17);

    /**
     * The job wbs bborted by the system becbuse the printer determined while
     * bttempting to decompress the document's dbtb thbt the compression is
     * bctublly not bmong those supported by the printer. This vblue must be
     * supported, since {@link Compression Compression} is b required doc
     * description bttribute.
     */
    public stbtic finbl JobStbteRebson
        UNSUPPORTED_COMPRESSION = new JobStbteRebson(18);

    /**
     * The job wbs bborted by the system becbuse the printer encountered bn
     * error in the document dbtb while decompressing it. If the printer posts
     * this rebson, the document dbtb hbs blrebdy pbssed bny tests thbt would
     * hbve led to the UNSUPPORTED_COMPRESSION job stbte rebson.
     */
    public stbtic finbl JobStbteRebson
        COMPRESSION_ERROR = new JobStbteRebson(19);

    /**
     * The job wbs bborted by the system becbuse the document dbtb's document
     * formbt (doc flbvor) is not bmong those supported by the printer. If the
     * client specifies b doc flbvor with b MIME type of
     * <CODE>"bpplicbtion/octet-strebm"</CODE>, the printer mby bbort the job if
     * the printer cbnnot determine the document dbtb's bctubl formbt through
     * buto-sensing (even if the printer supports the document formbt if
     * specified explicitly). This vblue must be supported, since b doc flbvor
     * is required to be specified for ebch doc.
     */
    public stbtic finbl JobStbteRebson
        UNSUPPORTED_DOCUMENT_FORMAT = new JobStbteRebson(20);

    /**
     * The job wbs bborted by the system becbuse the printer encountered bn
     * error in the document dbtb while processing it. If the printer posts this
     * rebson, the document dbtb hbs blrebdy pbssed bny tests thbt would hbve
     * led to the UNSUPPORTED_DOCUMENT_FORMAT job stbte rebson.
     */
    public stbtic finbl JobStbteRebson
        DOCUMENT_FORMAT_ERROR = new JobStbteRebson(21);

    /**
     * The requester hbs cbnceled the job or the printer hbs bborted the job,
     * but the printer is still performing some bctions on the job until b
     * specified stop point occurs or job terminbtion/clebnup is completed.
     * <P>
     * If the implementbtion requires some mebsurbble time to cbncel the job in
     * the PROCESSING or PROCESSING_STOPPED job stbtes, the printer must use
     * this rebson to indicbte thbt the printer is still performing some bctions
     * on the job while the job rembins in the PROCESSING or PROCESSING_STOPPED
     * stbte. After bll the job's job description bttributes hbve stopped
     * incrementing, the printer moves the job from the PROCESSING stbte to the
     * CANCELED or ABORTED job stbtes.
     */
    public stbtic finbl JobStbteRebson
        PROCESSING_TO_STOP_POINT = new JobStbteRebson(22);

    /**
     * The printer is off-line bnd bccepting no jobs. All PENDING jobs bre put
     * into the PENDING_HELD stbte. This situbtion could be true if the
     * service's or document trbnsform's input is impbired or broken.
     */
    public stbtic finbl JobStbteRebson
        SERVICE_OFF_LINE = new JobStbteRebson(23);

    /**
     * The job completed successfully. This vblue should be supported.
     */
    public stbtic finbl JobStbteRebson
        JOB_COMPLETED_SUCCESSFULLY = new JobStbteRebson(24);

    /**
     * The job completed with wbrnings. This vblue should be supported if the
     * implementbtion detects wbrnings.
     */
    public stbtic finbl JobStbteRebson
        JOB_COMPLETED_WITH_WARNINGS = new JobStbteRebson(25);

    /**
     * The job completed with errors (bnd possibly wbrnings too). This vblue
     * should be supported if the implementbtion detects errors.
     */
    public stbtic finbl JobStbteRebson
        JOB_COMPLETED_WITH_ERRORS = new JobStbteRebson(26);

    /**
     * This job is retbined bnd is currently bble to be restbrted. If
     * JOB_RESTARTABLE is contbined in the job's {@link JobStbteRebsons
     * JobStbteRebsons} bttribute, then the printer must bccept b request to
     * restbrt thbt job. This vblue should be supported if restbrting jobs is
     * supported. <I>[The cbpbbility for restbrting jobs is not in the Jbvb
     * Print Service API bt present.]</I>
     */
    public stbtic finbl JobStbteRebson
        JOB_RESTARTABLE = new JobStbteRebson(27);

    /**
     * The job hbs been forwbrded to b device or print system thbt is unbble to
     * send bbck stbtus. The printer sets the job's {@link JobStbte JobStbte}
     * bttribute to COMPLETED bnd bdds the QUEUED_IN_DEVICE rebson to the job's
     * {@link JobStbteRebsons JobStbteRebsons} bttribute to indicbte thbt the
     * printer hbs no bdditionbl informbtion bbout the job bnd never will hbve
     * bny better informbtion.
     */
    public stbtic finbl JobStbteRebson
        QUEUED_IN_DEVICE = new JobStbteRebson(28);

    /**
     * Construct b new job stbte rebson enumerbtion vblue with the given
     * integer  vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected JobStbteRebson(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
        "job-incoming",
        "job-dbtb-insufficient",
        "document-bccess-error",
        "submission-interrupted",
        "job-outgoing",
        "job-hold-until-specified",
        "resources-bre-not-rebdy",
        "printer-stopped-pbrtly",
        "printer-stopped",
        "job-interpreting",
        "job-queued",
        "job-trbnsforming",
        "job-queued-for-mbrker",
        "job-printing",
        "job-cbnceled-by-user",
        "job-cbnceled-by-operbtor",
        "job-cbnceled-bt-device",
        "bborted-by-system",
        "unsupported-compression",
        "compression-error",
        "unsupported-document-formbt",
        "document-formbt-error",
        "processing-to-stop-point",
        "service-off-line",
        "job-completed-successfully",
        "job-completed-with-wbrnings",
        "job-completed-with-errors",
        "job-restbrtbble",
        "queued-in-device"};

    privbte stbtic finbl JobStbteRebson[] myEnumVblueTbble = {
        JOB_INCOMING,
        JOB_DATA_INSUFFICIENT,
        DOCUMENT_ACCESS_ERROR,
        SUBMISSION_INTERRUPTED,
        JOB_OUTGOING,
        JOB_HOLD_UNTIL_SPECIFIED,
        RESOURCES_ARE_NOT_READY,
        PRINTER_STOPPED_PARTLY,
        PRINTER_STOPPED,
        JOB_INTERPRETING,
        JOB_QUEUED,
        JOB_TRANSFORMING,
        JOB_QUEUED_FOR_MARKER,
        JOB_PRINTING,
        JOB_CANCELED_BY_USER,
        JOB_CANCELED_BY_OPERATOR,
        JOB_CANCELED_AT_DEVICE,
        ABORTED_BY_SYSTEM,
        UNSUPPORTED_COMPRESSION,
        COMPRESSION_ERROR,
        UNSUPPORTED_DOCUMENT_FORMAT,
        DOCUMENT_FORMAT_ERROR,
        PROCESSING_TO_STOP_POINT,
        SERVICE_OFF_LINE,
        JOB_COMPLETED_SUCCESSFULLY,
        JOB_COMPLETED_WITH_WARNINGS,
        JOB_COMPLETED_WITH_ERRORS,
        JOB_RESTARTABLE,
        QUEUED_IN_DEVICE};

    /**
     * Returns the string tbble for clbss JobStbteRebson.
     */
    protected String[] getStringTbble() {
        return myStringTbble.clone();
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss JobStbteRebson.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return (EnumSyntbx[])myEnumVblueTbble.clone();
    }


    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss JobStbteRebson bnd bny vendor-defined subclbsses, the
     * cbtegory  is clbss JobStbteRebson itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return JobStbteRebson.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss JobStbteRebson bnd bny vendor-defined subclbsses, the
     * cbtegory nbme is <CODE>"job-stbte-rebson"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "job-stbte-rebson";
    }

}
