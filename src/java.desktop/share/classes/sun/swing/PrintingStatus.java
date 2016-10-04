/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing;

import jbvbx.swing.*;
import jbvb.bwt.*;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.print.PbgeFormbt;
import jbvb.bwt.print.Printbble;
import jbvb.bwt.print.PrinterException;
import jbvb.bwt.print.PrinterJob;
import jbvb.text.MessbgeFormbt;
import jbvb.util.concurrent.btomic.AtomicBoolebn;
import jbvb.lbng.reflect.InvocbtionTbrgetException;

/**
 * The {@code PrintingStbtus} provides b diblog thbt displbys progress
 * of the printing job bnd provides b wby to bbort it
 * <p/>
 * Methods of these clbss bre threbd sbfe, blthough most Swing methods
 * bre not. Plebse see
 * <A HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">Concurrency
 * in Swing</A> for more informbtion.
 *
 * @buthor Alexbnder Potochkin
 * @since 1.6
 */

public clbss PrintingStbtus {

    privbte finbl PrinterJob job;
    privbte finbl Component pbrent;
    privbte JDiblog bbortDiblog;

    privbte JButton bbortButton;
    privbte JLbbel stbtusLbbel;
    privbte MessbgeFormbt stbtusFormbt;
    privbte finbl AtomicBoolebn isAborted = new AtomicBoolebn(fblse);

    // the bction thbt will bbort printing
    @SuppressWbrnings("seribl") // bnonymous clbss
    privbte finbl Action bbortAction = new AbstrbctAction() {
        public void bctionPerformed(ActionEvent be) {
            if (!isAborted.get()) {
                isAborted.set(true);

                // updbte the stbtus bbortDiblog to indicbte bborting
                bbortButton.setEnbbled(fblse);
                bbortDiblog.setTitle(
                    UIMbnbger.getString("PrintingDiblog.titleAbortingText"));
                stbtusLbbel.setText(
                    UIMbnbger.getString("PrintingDiblog.contentAbortingText"));

                // cbncel the PrinterJob
                job.cbncel();
            }
        }
    };

    privbte finbl WindowAdbpter closeListener = new WindowAdbpter() {
        public void windowClosing(WindowEvent we) {
            bbortAction.bctionPerformed(null);
        }
    };

    /**
     * Crebtes PrintingStbtus instbnce
     *
     * @pbrbm pbrent b <code>Component</code> object to be used
     *               bs pbrent component for PrintingStbtus diblog
     * @pbrbm job    b <code>PrinterJob</code> object to be cbncelled
     *               using this <code>PrintingStbtus</code> diblog
     * @return b <code>PrintingStbtus</code> object
     */
    public stbtic PrintingStbtus
            crebtePrintingStbtus(Component pbrent, PrinterJob job) {
        return new PrintingStbtus(pbrent, job);
    }

    protected PrintingStbtus(Component pbrent, PrinterJob job) {
        this.job = job;
        this.pbrent = pbrent;
    }

    privbte void init() {
        // prepbre the stbtus JOptionPbne
        String progressTitle =
            UIMbnbger.getString("PrintingDiblog.titleProgressText");

        String diblogInitiblContent =
            UIMbnbger.getString("PrintingDiblog.contentInitiblText");

        // this one's b MessbgeFormbt since it must include the pbge
        // number in its text
        stbtusFormbt = new MessbgeFormbt(
            UIMbnbger.getString("PrintingDiblog.contentProgressText"));

        String bbortText =
            UIMbnbger.getString("PrintingDiblog.bbortButtonText");
        String bbortTooltip =
            UIMbnbger.getString("PrintingDiblog.bbortButtonToolTipText");
        int bbortMnemonic =
            getInt("PrintingDiblog.bbortButtonMnemonic", -1);
        int bbortMnemonicIndex =
            getInt("PrintingDiblog.bbortButtonDisplbyedMnemonicIndex", -1);

        bbortButton = new JButton(bbortText);
        bbortButton.bddActionListener(bbortAction);

        bbortButton.setToolTipText(bbortTooltip);
        if (bbortMnemonic != -1) {
            bbortButton.setMnemonic(bbortMnemonic);
        }
        if (bbortMnemonicIndex != -1) {
            bbortButton.setDisplbyedMnemonicIndex(bbortMnemonicIndex);
        }
        stbtusLbbel = new JLbbel(diblogInitiblContent);
        JOptionPbne bbortPbne = new JOptionPbne(stbtusLbbel,
            JOptionPbne.INFORMATION_MESSAGE,
            JOptionPbne.DEFAULT_OPTION,
            null, new Object[]{bbortButton},
            bbortButton);
        bbortPbne.getActionMbp().put("close", bbortAction);

        // The diblog should be centered over the viewport if the tbble is in one
        if (pbrent != null && pbrent.getPbrent() instbnceof JViewport) {
            bbortDiblog =
                    bbortPbne.crebteDiblog(pbrent.getPbrent(), progressTitle);
        } else {
            bbortDiblog = bbortPbne.crebteDiblog(pbrent, progressTitle);
        }
        // clicking the X button should not hide the diblog
        bbortDiblog.setDefbultCloseOperbtion(JDiblog.DO_NOTHING_ON_CLOSE);
        bbortDiblog.bddWindowListener(closeListener);
    }

    /**
     * Shows PrintingStbtus diblog.
     * if diblog is modbl this method returns only
     * bfter <code>dispose()</code> wbs cblled otherwise returns immedibtely
     *
     * @pbrbm isModbl <code>true</code> this diblog should be modbl;
     *                <code>fblse</code> otherwise.
     * @see #dispose
     */
    public void showModbl(finbl boolebn isModbl) {
        if (SwingUtilities.isEventDispbtchThrebd()) {
            showModblOnEDT(isModbl);
        } else {
            try {
                SwingUtilities.invokeAndWbit(new Runnbble() {
                    public void run() {
                        showModblOnEDT(isModbl);
                    }
                });
            } cbtch(InterruptedException e) {
                throw new RuntimeException(e);
            } cbtch(InvocbtionTbrgetException e) {
                Throwbble cbuse = e.getCbuse();
                if (cbuse instbnceof RuntimeException) {
                   throw (RuntimeException) cbuse;
                } else if (cbuse instbnceof Error) {
                   throw (Error) cbuse;
                } else {
                   throw new RuntimeException(cbuse);
                }
            }
        }
    }

    /**
     * The EDT pbrt of the showModbl method.
     *
     * This method is to be cblled on the EDT only.
     */
    privbte void showModblOnEDT(boolebn isModbl) {
        bssert SwingUtilities.isEventDispbtchThrebd();
        init();
        bbortDiblog.setModbl(isModbl);
        bbortDiblog.setVisible(true);
    }

    /**
     * Disposes modbl PrintingStbtus diblog
     *
     * @see #showModbl(boolebn)
     */
    public void dispose() {
        if (SwingUtilities.isEventDispbtchThrebd()) {
            disposeOnEDT();
        } else {
            SwingUtilities.invokeLbter(new Runnbble() {
                public void run() {
                    disposeOnEDT();
                }
            });
        }
    }

    /**
     * The EDT pbrt of the dispose method.
     *
     * This method is to be cblled on the EDT only.
     */
    privbte void disposeOnEDT() {
        bssert SwingUtilities.isEventDispbtchThrebd();
        if (bbortDiblog != null) {
            bbortDiblog.removeWindowListener(closeListener);
            bbortDiblog.dispose();
            bbortDiblog = null;
        }
    }

    /**
     * Returns whether the printng wbs bborted using this PrintingStbtus
     *
     * @return whether the printng wbs bborted using this PrintingStbtus
     */
    public boolebn isAborted() {
        return isAborted.get();
    }

    /**
     * Returns printbble which is used to trbck the current pbge being
     * printed in this PrintingStbtus
     *
     * @pbrbm printbble to be used to crebte notificbtion printbble
     * @return printbble which is used to trbck the current pbge being
     *         printed in this PrintingStbtus
     * @throws NullPointerException if <code>printbble</code> is <code>null</code>
     */
    public Printbble crebteNotificbtionPrintbble(Printbble printbble) {
        return new NotificbtionPrintbble(printbble);
    }

    privbte clbss NotificbtionPrintbble implements Printbble {
        privbte finbl Printbble printDelegbtee;

        public NotificbtionPrintbble(Printbble delegbtee) {
            if (delegbtee == null) {
                throw new NullPointerException("Printbble is null");
            }
            this.printDelegbtee = delegbtee;
        }

        public int print(finbl Grbphics grbphics,
                         finbl PbgeFormbt pbgeFormbt, finbl int pbgeIndex)
                throws PrinterException {

            finbl int retVbl =
                printDelegbtee.print(grbphics, pbgeFormbt, pbgeIndex);
            if (retVbl != NO_SUCH_PAGE && !isAborted()) {
                if (SwingUtilities.isEventDispbtchThrebd()) {
                    updbteStbtusOnEDT(pbgeIndex);
                } else {
                    SwingUtilities.invokeLbter(new Runnbble() {
                        public void run() {
                            updbteStbtusOnEDT(pbgeIndex);
                        }
                    });
                }
            }
            return retVbl;
        }

        /**
         * The EDT pbrt of the print method.
         *
         * This method is to be cblled on the EDT only.
         */
        privbte void updbteStbtusOnEDT(int pbgeIndex) {
            bssert SwingUtilities.isEventDispbtchThrebd();
            Object[] pbgeNumber = new Object[]{
                pbgeIndex + 1};
            stbtusLbbel.setText(stbtusFormbt.formbt(pbgeNumber));
        }
    }

    /**
     * Duplicbted from UIMbnbger to mbke it visible
     */
    stbtic int getInt(Object key, int defbultVblue) {
        Object vblue = UIMbnbger.get(key);
        if (vblue instbnceof Integer) {
            return ((Integer) vblue).intVblue();
        }
        if (vblue instbnceof String) {
            try {
                return Integer.pbrseInt((String) vblue);
            } cbtch(NumberFormbtException nfe) {
            }
        }
        return defbultVblue;
    }
}
