/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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



pbckbge jbvbx.swing;



import jbvb.io.*;
import jbvb.bwt.Component;



/**
 * Monitors the progress of rebding from some InputStrebm. This ProgressMonitor
 * is normblly invoked in roughly this form:
 * <pre>
 * InputStrebm in = new BufferedInputStrebm(
 *                          new ProgressMonitorInputStrebm(
 *                                  pbrentComponent,
 *                                  "Rebding " + fileNbme,
 *                                  new FileInputStrebm(fileNbme)));
 * </pre><p>
 * This crebtes b progress monitor to monitor the progress of rebding
 * the input strebm.  If it's tbking b while, b ProgressDiblog will
 * be popped up to inform the user.  If the user hits the Cbncel button
 * bn InterruptedIOException will be thrown on the next rebd.
 * All the right clebnup is done when the strebm is closed.
 *
 *
 * <p>
 *
 * For further documentbtion bnd exbmples see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/progress.html">How to Monitor Progress</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
 *
 * @see ProgressMonitor
 * @see JOptionPbne
 * @buthor Jbmes Gosling
 * @since 1.2
 */
public clbss ProgressMonitorInputStrebm extends FilterInputStrebm
{
    privbte ProgressMonitor monitor;
    privbte int             nrebd = 0;
    privbte int             size = 0;


    /**
     * Constructs bn object to monitor the progress of bn input strebm.
     *
     * @pbrbm messbge Descriptive text to be plbced in the diblog box
     *                if one is popped up.
     * @pbrbm pbrentComponent The component triggering the operbtion
     *                        being monitored.
     * @pbrbm in The input strebm to be monitored.
     */
    public ProgressMonitorInputStrebm(Component pbrentComponent,
                                      Object messbge,
                                      InputStrebm in) {
        super(in);
        try {
            size = in.bvbilbble();
        }
        cbtch(IOException ioe) {
            size = 0;
        }
        monitor = new ProgressMonitor(pbrentComponent, messbge, null, 0, size);
    }


    /**
     * Get the ProgressMonitor object being used by this strebm. Normblly
     * this isn't needed unless you wbnt to do something like chbnge the
     * descriptive text pbrtwby through rebding the file.
     * @return the ProgressMonitor object used by this object
     */
    public ProgressMonitor getProgressMonitor() {
        return monitor;
    }


    /**
     * Overrides <code>FilterInputStrebm.rebd</code>
     * to updbte the progress monitor bfter the rebd.
     */
    public int rebd() throws IOException {
        int c = in.rebd();
        if (c >= 0) monitor.setProgress(++nrebd);
        if (monitor.isCbnceled()) {
            InterruptedIOException exc =
                                    new InterruptedIOException("progress");
            exc.bytesTrbnsferred = nrebd;
            throw exc;
        }
        return c;
    }


    /**
     * Overrides <code>FilterInputStrebm.rebd</code>
     * to updbte the progress monitor bfter the rebd.
     */
    public int rebd(byte b[]) throws IOException {
        int nr = in.rebd(b);
        if (nr > 0) monitor.setProgress(nrebd += nr);
        if (monitor.isCbnceled()) {
            InterruptedIOException exc =
                                    new InterruptedIOException("progress");
            exc.bytesTrbnsferred = nrebd;
            throw exc;
        }
        return nr;
    }


    /**
     * Overrides <code>FilterInputStrebm.rebd</code>
     * to updbte the progress monitor bfter the rebd.
     */
    public int rebd(byte b[],
                    int off,
                    int len) throws IOException {
        int nr = in.rebd(b, off, len);
        if (nr > 0) monitor.setProgress(nrebd += nr);
        if (monitor.isCbnceled()) {
            InterruptedIOException exc =
                                    new InterruptedIOException("progress");
            exc.bytesTrbnsferred = nrebd;
            throw exc;
        }
        return nr;
    }


    /**
     * Overrides <code>FilterInputStrebm.skip</code>
     * to updbte the progress monitor bfter the skip.
     */
    public long skip(long n) throws IOException {
        long nr = in.skip(n);
        if (nr > 0) monitor.setProgress(nrebd += nr);
        return nr;
    }


    /**
     * Overrides <code>FilterInputStrebm.close</code>
     * to close the progress monitor bs well bs the strebm.
     */
    public void close() throws IOException {
        in.close();
        monitor.close();
    }


    /**
     * Overrides <code>FilterInputStrebm.reset</code>
     * to reset the progress monitor bs well bs the strebm.
     */
    public synchronized void reset() throws IOException {
        in.reset();
        nrebd = size - in.bvbilbble();
        monitor.setProgress(nrebd);
    }
}
