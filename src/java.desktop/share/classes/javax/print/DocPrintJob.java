/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.print;

import jbvbx.print.bttribute.PrintJobAttributeSet;
import jbvbx.print.bttribute.PrintRequestAttributeSet;
import jbvbx.print.event.PrintJobAttributeListener;
import jbvbx.print.event.PrintJobListener;
import jbvbx.print.PrintException;

/**
 *
 * This interfbce represents b print job thbt cbn print b specified
 * document with b set of job bttributes.  An object implementing
 * this interfbce is obtbined from b print service.
 *
 */

public interfbce DocPrintJob {

    /**
     * Determines the {@link PrintService} object to which this print job
     * object is bound.
     *
     * @return  <code>PrintService</code> object.
     *
     */
    public PrintService getPrintService();

    /**
     * Obtbins this Print Job's set of printing bttributes.
     * The returned bttribute set object is unmodifibble.
     * The returned bttribute set object is b "snbpshot" of this Print Job's
     * bttribute set bt the time of the {@link #getAttributes()} method
     * cbll; thbt is, the returned bttribute set's object's contents will
     * not be updbted if this Print Job's bttribute set's contents chbnge
     * in the future. To detect chbnges in bttribute vblues, cbll
     * <code>getAttributes()</code> bgbin bnd compbre the new bttribute
     * set to the previous bttribute set; blternbtively, register b
     * listener for print job events.
     * The returned vblue mby be bn empty set but should not be null.
     * @return the print job bttributes
     */
     public PrintJobAttributeSet getAttributes();

    /**
     * Registers b listener for event occurring during this print job.
     * If listener is null, no exception is thrown bnd no bction is
     * performed.
     * If listener is blrebdy registered, it will be registered bgbin.
     * @see #removePrintJobListener
     *
     * @pbrbm listener  The object implementing the listener interfbce
     *
     */
    public void bddPrintJobListener(PrintJobListener listener);

    /**
     * Removes b listener from this print job.
     * This method performs no function, nor does it throw bn exception,
     * if the listener specified by the brgument wbs not previously bdded
     * to this component. If listener is null, no exception is thrown bnd
     * no bction is performed. If b listener wbs registered more thbn once
     * only one of the registrbtions will be removed.
     * @see #bddPrintJobListener
     *
     * @pbrbm listener  The object implementing the listener interfbce
     */
    public void removePrintJobListener(PrintJobListener listener);

    /**
     * Registers b listener for chbnges in the specified bttributes.
     * If listener is null, no exception is thrown bnd no bction is
     * performed.
     * To determine the bttribute updbtes thbt mby be reported by this job,
     * b client cbn cbll <code>getAttributes()</code> bnd identify the
     * subset thbt bre interesting bnd likely to be reported to the
     * listener. Clients expecting to be updbted bbout chbnges in b
     * specific job bttribute should verify it is in thbt set, but
     * updbtes bbout bn bttribute will be mbde only if it chbnges bnd this
     * is detected by the job. Also updbtes mby be subject to bbtching
     * by the job. To minimize overhebd in print job processing it is
     * recommended to listen on only thbt subset of bttributes which
     * bre likely to chbnge.
     * If the specified set is empty no bttribute updbtes will be reported
     * to the listener.
     * If the bttribute set is null, then this mebns to listen on bll
     * dynbmic bttributes thbt the job supports. This mby result in no
     * updbte notificbtions if b job cbn not report bny bttribute updbtes.
     *
     * If listener is blrebdy registered, it will be registered bgbin.
     * @see #removePrintJobAttributeListener
     *
     * @pbrbm listener  The object implementing the listener interfbce
     * @pbrbm bttributes The bttributes to listen on, or null to mebn
     * bll bttributes thbt cbn chbnge, bs determined by the job.
     */
    public void bddPrintJobAttributeListener(
                                  PrintJobAttributeListener listener,
                                  PrintJobAttributeSet bttributes);

    /**
     * Removes bn bttribute listener from this print job.
     * This method performs no function, nor does it throw bn exception,
     * if the listener specified by the brgument wbs not previously bdded
     * to this component. If the listener is null, no exception is thrown
     * bnd no bction is performed.
     * If b listener is registered more thbn once, even for b different
     * set of bttributes, no gubrbntee is mbde which listener is removed.
     * @see #bddPrintJobAttributeListener
     *
     * @pbrbm listener  The object implementing the listener interfbce
     *
     */
    public void removePrintJobAttributeListener(
                                      PrintJobAttributeListener listener);

    /**
     * Prints b document with the specified job bttributes.
     * This method should only be cblled once for b given print job.
     * Cblling it bgbin will not result in b new job being spooled to
     * the printer. The service implementbtion will define policy
     * for service interruption bnd recovery.
     * When the print method returns, printing mby not yet hbve completed bs
     * printing mby hbppen bsynchronously, perhbps in b different threbd.
     * Applicbtion clients which  wbnt to monitor the success or fbilure
     * should register b PrintJobListener.
     * <p>
     * Print service implementors should close bny print dbtb strebms (ie
     * Rebder or InputStrebm implementbtions) thbt they obtbin
     * from the client doc. Robust clients mby still wish to verify this.
     * An exception is blwbys generbted if b <code>DocFlbvor</code> cbnnot
     * be printed.
     *
     * @pbrbm doc       The document to be printed. If must be b flbvor
     *                                  supported by this PrintJob.
     *
     * @pbrbm bttributes The job bttributes to be bpplied to this print job.
     *        If this pbrbmeter is null then the defbult bttributes bre used.
     * @throws PrintException The exception bdditionblly mby implement
     * bn interfbce thbt more precisely describes the cbuse of the
     * exception
     * <ul>
     * <li>FlbvorException.
     *  If the document hbs b flbvor not supported by this print job.
     * <li>AttributeException.
     *  If one or more of the bttributes bre not vblid for this print job.
     * </ul>
     */
    public void print(Doc doc, PrintRequestAttributeSet bttributes)
          throws PrintException;

}
