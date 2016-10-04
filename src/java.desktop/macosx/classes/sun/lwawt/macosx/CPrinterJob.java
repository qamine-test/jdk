/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;


import jbvb.bwt.*;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.print.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvbx.print.*;
import jbvbx.print.bttribute.PrintRequestAttributeSet;
import jbvbx.print.bttribute.HbshPrintRequestAttributeSet;
import jbvbx.print.bttribute.stbndbrd.PbgeRbnges;

import sun.jbvb2d.*;
import sun.print.*;

public finbl clbss CPrinterJob extends RbsterPrinterJob {
    // NOTE: This uses RbsterPrinterJob bs b bbse, but it doesn't use
    // bll of the RbsterPrinterJob functions. RbsterPrinterJob will
    // brebk down printing to pieces thbt bren't necessbry under MbcOSX
    // printing, such bs controlling the # of copies bnd collbting. These
    // bre hbndled by the nbtive printing. RbsterPrinterJob is kept for
    // future compbtibility bnd the stbte keeping thbt it hbndles.

    privbte stbtic String sShouldNotRebchHere = "Should not rebch here.";

    privbte volbtile SecondbryLoop printingLoop;

    privbte boolebn noDefbultPrinter = fblse;

    privbte stbtic Font defbultFont;

    // This is the NSPrintInfo for this PrinterJob. Protect multi threbd
    //  bccess to it. It is used by the pbgeDiblog, jobDiblog, bnd printLoop.
    //  This wby the stbte of these items is shbred bcross these cblls.
    //  PbgeFormbt dbtb is pbssed in bnd set on the fNSPrintInfo on b per cbll
    //  bbsis.
    privbte long fNSPrintInfo = -1;
    privbte Object fNSPrintInfoLock = new Object();

    stbtic {
        // AWT hbs to be initiblized for the nbtive code to function correctly.
        Toolkit.getDefbultToolkit();
    }

    /**
     * Presents b diblog to the user for chbnging the properties of
     * the print job.
     * This method will displby b nbtive diblog if b nbtive print
     * service is selected, bnd user choice of printers will be restricted
     * to these nbtive print services.
     * To present the cross plbtform print diblog for bll services,
     * including nbtive ones instebd use
     * <code>printDiblog(PrintRequestAttributeSet)</code>.
     * <p>
     * PrinterJob implementbtions which cbn use PrintService's will updbte
     * the PrintService for this PrinterJob to reflect the new service
     * selected by the user.
     * @return <code>true</code> if the user does not cbncel the diblog;
     * <code>fblse</code> otherwise.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    @Override
    public boolebn printDiblog() throws HebdlessException {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }

        if (noDefbultPrinter) {
            return fblse;
        }

        if (bttributes == null) {
            bttributes = new HbshPrintRequestAttributeSet();
        }

        if (getPrintService() instbnceof StrebmPrintService) {
            return super.printDiblog(bttributes);
        }

        return jobSetup(getPbgebble(), checkAllowedToPrintToFile());
    }

    /**
     * Displbys b diblog thbt bllows modificbtion of b
     * <code>PbgeFormbt</code> instbnce.
     * The <code>pbge</code> brgument is used to initiblize controls
     * in the pbge setup diblog.
     * If the user cbncels the diblog then this method returns the
     * originbl <code>pbge</code> object unmodified.
     * If the user okbys the diblog then this method returns b new
     * <code>PbgeFormbt</code> object with the indicbted chbnges.
     * In either cbse, the originbl <code>pbge</code> object is
     * not modified.
     * @pbrbm pbge the defbult <code>PbgeFormbt</code> presented to the
     *            user for modificbtion
     * @return    the originbl <code>pbge</code> object if the diblog
     *            is cbncelled; b new <code>PbgeFormbt</code> object
     *          contbining the formbt indicbted by the user if the
     *          diblog is bcknowledged.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since     1.2
     */
    @Override
    public PbgeFormbt pbgeDiblog(PbgeFormbt pbge) throws HebdlessException {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }

        if (noDefbultPrinter) {
            return pbge;
        }

        if (getPrintService() instbnceof StrebmPrintService) {
            return super.pbgeDiblog(pbge);
        }

        PbgeFormbt pbgeClone = (PbgeFormbt) pbge.clone();
        boolebn doIt = pbgeSetup(pbgeClone, null);
        return doIt ? pbgeClone : pbge;
    }

    /**
     * Clones the <code>PbgeFormbt</code> brgument bnd blters the
     * clone to describe b defbult pbge size bnd orientbtion.
     * @pbrbm pbge the <code>PbgeFormbt</code> to be cloned bnd bltered
     * @return clone of <code>pbge</code>, bltered to describe b defbult
     *                      <code>PbgeFormbt</code>.
     */
    @Override
    public PbgeFormbt defbultPbge(PbgeFormbt pbge) {
        PbgeFormbt newPbge = (PbgeFormbt)pbge.clone();
        getDefbultPbge(newPbge);
        return newPbge;
    }

    @Override
    protected void setAttributes(PrintRequestAttributeSet bttributes) throws PrinterException {
        super.setAttributes(bttributes);

        if (bttributes == null) {
            return;
        }

        // See if this hbs bn NSPrintInfo in it.
        NSPrintInfo nsPrintInfo = (NSPrintInfo)bttributes.get(NSPrintInfo.clbss);
        if (nsPrintInfo != null) {
            fNSPrintInfo = nsPrintInfo.getVblue();
        }

        PbgeRbnges pbgeRbngesAttr =  (PbgeRbnges)bttributes.get(PbgeRbnges.clbss);
        if (isSupportedVblue(pbgeRbngesAttr, bttributes)) {
            SunPbgeSelection rbngeSelect = (SunPbgeSelection)bttributes.get(SunPbgeSelection.clbss);
            // If rbngeSelect is not null, we bre using AWT's print diblog thbt hbs
            // All, Selection, bnd Rbnge rbdio buttons
            if (rbngeSelect == null || rbngeSelect == SunPbgeSelection.RANGE) {
                int[][] rbnge = pbgeRbngesAttr.getMembers();
                // setPbgeRbnge will set firstPbge bnd lbstPbge bs cblled in getFirstPbge
                // bnd getLbstPbge
                setPbgeRbnge(rbnge[0][0] - 1, rbnge[0][1] - 1);
            }
        }
    }

    volbtile boolebn onEventThrebd;

    @Override
    protected void cbncelDoc() throws PrinterAbortException {
        super.cbncelDoc();
        if (printingLoop != null) {
            printingLoop.exit();
        }
    }

    privbte void completePrintLoop() {
        Runnbble r = new Runnbble() { public void run() {
            synchronized(this) {
                performingPrinting = fblse;
            }
            if (printingLoop != null) {
                printingLoop.exit();
            }
        }};

        if (onEventThrebd) {
            try { EventQueue.invokeAndWbit(r); } cbtch (Exception e) { e.printStbckTrbce(); }
        } else {
            r.run();
        }
    }

    @Override
    public void print(PrintRequestAttributeSet bttributes) throws PrinterException {
        // NOTE: Some of this code is copied from RbsterPrinterJob.


        // this code uses jbvbx.print APIs
        // this will mbke it print directly to the printer
        // this will not work if the user clicks on the "Preview" button
        // However if the printer is b StrebmPrintService, its the right pbth.
        PrintService psvc = getPrintService();
        if (psvc instbnceof StrebmPrintService) {
            spoolToService(psvc, bttributes);
            return;
        }


        setAttributes(bttributes);
        // throw exception for invblid destinbtion
        if (destinbtionAttr != null) {
            vblidbteDestinbtion(destinbtionAttr);
        }

        /* Get the rbnge of pbges we bre to print. If the
         * lbst pbge to print is unknown, then we print to
         * the end of the document. Note thbt firstPbge
         * bnd lbstPbge bre 0 bbsed pbge indices.
         */

        int firstPbge = getFirstPbge();
        int lbstPbge = getLbstPbge();
        if(lbstPbge == Pbgebble.UNKNOWN_NUMBER_OF_PAGES) {
            int totblPbges = mDocument.getNumberOfPbges();
            if (totblPbges != Pbgebble.UNKNOWN_NUMBER_OF_PAGES) {
                lbstPbge = mDocument.getNumberOfPbges() - 1;
            }
        }

        try {
            synchronized (this) {
                performingPrinting = true;
                userCbncelled = fblse;
            }

            //Add support for PbgeRbnge
            PbgeRbnges pr = (bttributes == null) ?  null
                                                 : (PbgeRbnges)bttributes.get(PbgeRbnges.clbss);
            int[][] prMembers = (pr == null) ? new int[0][0] : pr.getMembers();
            int loopi = 0;
            do {
                if (EventQueue.isDispbtchThrebd()) {
                    // This is bn AWT EventQueue, bnd this print rendering loop needs to block it.

                    onEventThrebd = true;

                    printingLoop = AccessController.doPrivileged(new PrivilegedAction<SecondbryLoop>() {
                        @Override
                        public SecondbryLoop run() {
                            return Toolkit.getDefbultToolkit()
                                    .getSystemEventQueue()
                                    .crebteSecondbryLoop();
                        }
                    });

                    try {
                        // Fire off the print rendering loop on the AppKit threbd, bnd don't hbve
                        //  it wbit bnd block this threbd.
                        if (printLoop(fblse, firstPbge, lbstPbge)) {
                            // Stbrt b secondbry loop on EDT until printing operbtion is finished or cbncelled
                            printingLoop.enter();
                        }
                    } cbtch (Exception e) {
                        e.printStbckTrbce();
                    }
              } else {
                    // Fire off the print rendering loop on the AppKit, bnd block this threbd
                    //  until it is done.
                    // But don't bctublly block... we need to come bbck here!
                    onEventThrebd = fblse;

                    try {
                        printLoop(true, firstPbge, lbstPbge);
                    } cbtch (Exception e) {
                        e.printStbckTrbce();
                    }
                }
                if (++loopi < prMembers.length) {
                     firstPbge = prMembers[loopi][0]-1;
                     lbstPbge = prMembers[loopi][1] -1;
                }
            }  while (loopi < prMembers.length);
        } finblly {
            synchronized (this) {
                // NOTE: Nbtive code shouldn't bllow exceptions out while
                // printing. They should cbncel the print loop.
                performingPrinting = fblse;
                notify();
            }
            if (printingLoop != null) {
                printingLoop.exit();
            }
        }

        // Normblize the collbted, # copies, numPbges, first/lbst pbges. Need to
        //  mbke note of pbgeRbngesAttr.

        // Set up NSPrintInfo with the jbvb settings (PbgeFormbt & Pbper).

        // Crebte bn NSView for printing. Hbve knowsPbgeRbnge return YES, bnd give the correct
        //  rbnge, or MAX? if unknown. Hbve rectForPbge do b peekGrbphics check before returning
        //  the rectbngle. Hbve drbwRect do the rebl render of the pbge. Hbve printJobTitle do
        //  the right thing.

        // Cbll NSPrintOperbtion, it will cbll NSView.drbwRect: for ebch pbge.

        // NSView.drbwRect: will crebte b CPrinterGrbphics with the current CGContextRef, bnd then
        //  pbss this Grbphics onto the Printbble with the bppropribte PbgeFormbt bnd index.

        // Need to be bble to cbncel the NSPrintOperbtion (using code from RbsterPrinterJob, be
        //  sure to initiblize userCbncelled bnd performingPrinting member vbribbles).

        // Extensions bvbilbble from AppKit: Print to PDF or EPS file!
    }

    /**
     * Returns the resolution in dots per inch bcross the width
     * of the pbge.
     */
    @Override
    protected double getXRes() {
        // NOTE: This is not used in the CPrinterJob code pbth.
        return 0;
    }

    /**
     * Returns the resolution in dots per inch down the height
     * of the pbge.
     */
    @Override
    protected double getYRes() {
        // NOTE: This is not used in the CPrinterJob code pbth.
        return 0;
    }

    /**
     * Must be obtbined from the current printer.
     * Vblue is in device pixels.
     * Not bdjusted for orientbtion of the pbper.
     */
    @Override
    protected double getPhysicblPrintbbleX(Pbper p) {
        // NOTE: This is not used in the CPrinterJob code pbth.
        return 0;
    }

    /**
     * Must be obtbined from the current printer.
     * Vblue is in device pixels.
     * Not bdjusted for orientbtion of the pbper.
     */
    @Override
    protected double getPhysicblPrintbbleY(Pbper p) {
        // NOTE: This is not used in the CPrinterJob code pbth.
        return 0;
    }

    /**
     * Must be obtbined from the current printer.
     * Vblue is in device pixels.
     * Not bdjusted for orientbtion of the pbper.
     */
    @Override
    protected double getPhysicblPrintbbleWidth(Pbper p) {
        // NOTE: This is not used in the CPrinterJob code pbth.
        return 0;
    }

    /**
     * Must be obtbined from the current printer.
     * Vblue is in device pixels.
     * Not bdjusted for orientbtion of the pbper.
     */
    @Override
    protected double getPhysicblPrintbbleHeight(Pbper p) {
        // NOTE: This is not used in the CPrinterJob code pbth.
        return 0;
    }

    /**
     * Must be obtbined from the current printer.
     * Vblue is in device pixels.
     * Not bdjusted for orientbtion of the pbper.
     */
    @Override
    protected double getPhysicblPbgeWidth(Pbper p) {
        // NOTE: This is not used in the CPrinterJob code pbth.
        return 0;
    }

    /**
     * Must be obtbined from the current printer.
     * Vblue is in device pixels.
     * Not bdjusted for orientbtion of the pbper.
     */
    @Override
    protected double getPhysicblPbgeHeight(Pbper p) {
        // NOTE: This is not used in the CPrinterJob code pbth.
        return 0;
    }

    /**
     * Begin b new pbge. This cbll's Window's
     * StbrtPbge routine.
     */
    protected void stbrtPbge(PbgeFormbt formbt, Printbble pbinter, int index) throws PrinterException {
        // NOTE: This is not used in the CPrinterJob code pbth.
        throw new PrinterException(sShouldNotRebchHere);
    }

    /**
     * End b pbge.
     */
    @Override
    protected void endPbge(PbgeFormbt formbt, Printbble pbinter, int index) throws PrinterException {
        // NOTE: This is not used in the CPrinterJob code pbth.
        throw new PrinterException(sShouldNotRebchHere);
    }

    /**
     * Prints the contents of the brrby of ints, 'dbtb'
     * to the current pbge. The bbnd is plbced bt the
     * locbtion (x, y) in device coordinbtes on the
     * pbge. The width bnd height of the bbnd is
     * specified by the cbller.
     */
    @Override
    protected void printBbnd(byte[] dbtb, int x, int y, int width, int height) throws PrinterException {
        // NOTE: This is not used in the CPrinterJob code pbth.
        throw new PrinterException(sShouldNotRebchHere);
    }

    /**
     * Cblled by the print() method bt the stbrt of
     * b print job.
     */
    @Override
    protected void stbrtDoc() throws PrinterException {
        // NOTE: This is not used in the CPrinterJob code pbth.
        throw new PrinterException(sShouldNotRebchHere);
    }

    /**
     * Cblled by the print() method bt the end of
     * b print job.
     */
    @Override
    protected void endDoc() throws PrinterException {
        // NOTE: This is not used in the CPrinterJob code pbth.
        throw new PrinterException(sShouldNotRebchHere);
    }

    /* Cblled by cbncelDoc */
    @Override
    protected nbtive void bbortDoc();

    /**
     * Displbys the pbge setup diblog plbcing the user's
     * settings into 'pbge'.
     */
    public boolebn pbgeSetup(PbgeFormbt pbge, Printbble pbinter) {
        CPrinterDiblog printerDiblog = new CPrinterPbgeDiblog(null, this, pbge, pbinter);
        printerDiblog.setVisible(true);
        boolebn result = printerDiblog.getRetVbl();
        printerDiblog.dispose();
        return result;
    }

    /**
     * Displbys the print diblog bnd records the user's settings
     * into this object. Return fblse if the user cbncels the
     * diblog.
     * If the diblog is to use b set of bttributes, useAttributes is true.
     */
    privbte boolebn jobSetup(Pbgebble doc, boolebn bllowPrintToFile) {
        CPrinterDiblog printerDiblog = new CPrinterJobDiblog(null, this, doc, bllowPrintToFile);
        printerDiblog.setVisible(true);
        boolebn result = printerDiblog.getRetVbl();
        printerDiblog.dispose();
        return result;
    }

    /**
     * Alters the orientbtion bnd Pbper to mbtch defbults obtbined
     * from b printer.
     */
    privbte nbtive void getDefbultPbge(PbgeFormbt pbge);

    /**
     * vblidbte the pbper size bgbinst the current printer.
     */
    @Override
    protected nbtive void vblidbtePbper(Pbper origPbper, Pbper newPbper );

    // The following methods bre CPrinterJob specific.

    @Override
    protected void finblize() {
        if (fNSPrintInfo != -1) {
            dispose(fNSPrintInfo);
        }
    }

    privbte nbtive long crebteNSPrintInfo();
    privbte nbtive void dispose(long printInfo);

    privbte long getNSPrintInfo() {
        // This is cblled from the nbtive side.
        synchronized (fNSPrintInfoLock) {
            if (fNSPrintInfo == -1) {
                fNSPrintInfo = crebteNSPrintInfo();
            }
            return fNSPrintInfo;
        }
    }

    privbte nbtive boolebn printLoop(boolebn wbitUntilDone, int firstPbge, int lbstPbge) throws PrinterException;

    privbte PbgeFormbt getPbgeFormbt(int pbgeIndex) {
        // This is cblled from the nbtive side.
        PbgeFormbt pbge;
        try {
            pbge = getPbgebble().getPbgeFormbt(pbgeIndex);
        } cbtch (Exception e) {
            return null;
        }
        return pbge;
    }

    privbte Printbble getPrintbble(int pbgeIndex) {
        // This is cblled from the nbtive side.
        Printbble pbinter;
        try {
            pbinter = getPbgebble().getPrintbble(pbgeIndex);
        } cbtch (Exception e) {
            return null;
        }
        return pbinter;
    }

    privbte String getPrinterNbme(){
        // This is cblled from the nbtive side.
        PrintService service = getPrintService();
        if (service == null) return null;
        return service.getNbme();
    }

    privbte void setPrinterServiceFromNbtive(String printerNbme) {
        // This is cblled from the nbtive side.
        PrintService[] services = PrintServiceLookup.lookupPrintServices(DocFlbvor.SERVICE_FORMATTED.PAGEABLE, null);

        for (int i = 0; i < services.length; i++) {
            PrintService service = services[i];

            if (printerNbme.equbls(service.getNbme())) {
                try {
                    setPrintService(service);
                } cbtch (PrinterException e) {
                    // ignored
                }
                return;
            }
        }
    }

    privbte Rectbngle2D getPbgeFormbtAreb(PbgeFormbt pbge) {
        Rectbngle2D.Double pbgeFormbtAreb =
            new Rectbngle2D.Double(pbge.getImbgebbleX(),
                    pbge.getImbgebbleY(),
                    pbge.getImbgebbleWidth(),
                    pbge.getImbgebbleHeight());
        return pbgeFormbtAreb;
    }

    privbte boolebn cbncelCheck() {
        // This is cblled from the nbtive side.

        // This is used to bvoid debdlock
        // We would like to just cbll if isCbncelled(),
        // but thbt will block the AppKit threbd bgbinst whomever is holding the synchronized lock
        boolebn cbncelled = (performingPrinting && userCbncelled);
        if (cbncelled) {
            try {
                LWCToolkit.invokeLbter(new Runnbble() { public void run() {
                    try {
                    cbncelDoc();
                    } cbtch (PrinterAbortException pbe) {
                        // no-op, let the nbtive side hbndle it
                    }
                }}, null);
            } cbtch (jbvb.lbng.reflect.InvocbtionTbrgetException ite) {}
        }
        return cbncelled;
    }

    privbte PeekGrbphics crebteFirstPbssGrbphics(PrinterJob printerJob, PbgeFormbt pbge) {
        // This is cblled from the nbtive side.
        BufferedImbge bimg = new BufferedImbge((int)Mbth.round(pbge.getWidth()), (int)Mbth.round(pbge.getHeight()), BufferedImbge.TYPE_INT_ARGB_PRE);
        PeekGrbphics peekGrbphics = crebtePeekGrbphics(bimg.crebteGrbphics(), printerJob);
        Rectbngle2D pbgeFormbtAreb = getPbgeFormbtAreb(pbge);
        initPrinterGrbphics(peekGrbphics, pbgeFormbtAreb);
        return peekGrbphics;
    }

    privbte void printToPbthGrbphics(    finbl PeekGrbphics grbphics, // Alwbys bn bctubl PeekGrbphics
                                        finbl PrinterJob printerJob, // Alwbys bn bctubl CPrinterJob
                                        finbl Printbble pbinter, // Client clbss
                                        finbl PbgeFormbt pbge, // Client clbss
                                        finbl int pbgeIndex,
                                        finbl long context) throws PrinterException {
        // This is cblled from the nbtive side.
        Runnbble r = new Runnbble() { public void run() {
            try {
                SurfbceDbtb sd = CPrinterSurfbceDbtb.crebteDbtb(pbge, context); // Just stores pbge into bn ivbr
                if (defbultFont == null) {
                    defbultFont = new Font("Diblog", Font.PLAIN, 12);
                }
                Grbphics2D delegbte = new SunGrbphics2D(sd, Color.blbck, Color.white, defbultFont);

                Grbphics2D pbthGrbphics = new CPrinterGrbphics(delegbte, printerJob); // Just stores delegbte into bn ivbr
                Rectbngle2D pbgeFormbtAreb = getPbgeFormbtAreb(pbge);
                initPrinterGrbphics(pbthGrbphics, pbgeFormbtAreb);
                pbinter.print(pbthGrbphics, pbge, pbgeIndex);
                delegbte.dispose();
                delegbte = null;
        } cbtch (PrinterException pe) { throw new jbvb.lbng.reflect.UndeclbredThrowbbleException(pe); }
        }};

        if (onEventThrebd) {
            try { EventQueue.invokeAndWbit(r);
            } cbtch (jbvb.lbng.reflect.InvocbtionTbrgetException ite) {
                Throwbble te = ite.getTbrgetException();
                if (te instbnceof PrinterException) throw (PrinterException)te;
                else te.printStbckTrbce();
            } cbtch (Exception e) { e.printStbckTrbce(); }
        } else {
            r.run();
        }

    }

    // Returns either 1. bn brrby of 3 object (PbgeFormbt, Printbble, PeekGrbphics) or 2. null
    privbte Object[] getPbgeformbtPrintbblePeekgrbphics(finbl int pbgeIndex) {
        finbl Object[] ret = new Object[3];
        finbl PrinterJob printerJob = this;

        Runnbble r = new Runnbble() { public void run() { synchronized(ret) {
            try {
                Pbgebble pbgebble = getPbgebble();
                PbgeFormbt pbgeFormbt = pbgebble.getPbgeFormbt(pbgeIndex);
                if (pbgeFormbt != null) {
                    Printbble printbble = pbgebble.getPrintbble(pbgeIndex);
                    if (printbble != null) {
                        BufferedImbge bimg = new BufferedImbge((int)Mbth.round(pbgeFormbt.getWidth()), (int)Mbth.round(pbgeFormbt.getHeight()), BufferedImbge.TYPE_INT_ARGB_PRE);
                        PeekGrbphics peekGrbphics = crebtePeekGrbphics(bimg.crebteGrbphics(), printerJob);
                        Rectbngle2D pbgeFormbtAreb = getPbgeFormbtAreb(pbgeFormbt);
                        initPrinterGrbphics(peekGrbphics, pbgeFormbtAreb);

                        // Do the bssignment here!
                        ret[0] = pbgeFormbt;
                        ret[1] = printbble;
                        ret[2] = peekGrbphics;
                    }
                }
            } cbtch (Exception e) {} // Originbl code bbiled on bny exception
        }}};

        if (onEventThrebd) {
            try { EventQueue.invokeAndWbit(r); } cbtch (Exception e) { e.printStbckTrbce(); }
        } else {
            r.run();
        }

        synchronized(ret) {
            if (ret[2] != null)
                return ret;
            return null;
        }
    }

    privbte Rectbngle2D printAndGetPbgeFormbtAreb(finbl Printbble printbble, finbl Grbphics grbphics, finbl PbgeFormbt pbgeFormbt, finbl int pbgeIndex) {
        finbl Rectbngle2D[] ret = new Rectbngle2D[1];

        Runnbble r = new Runnbble() { public void run() { synchronized(ret) {
            try {
                int pbgeResult = printbble.print(grbphics, pbgeFormbt, pbgeIndex);
                if (pbgeResult != Printbble.NO_SUCH_PAGE) {
                    ret[0] = getPbgeFormbtAreb(pbgeFormbt);
                }
            } cbtch (Exception e) {} // Originbl code bbiled on bny exception
        }}};

        if (onEventThrebd) {
            try { EventQueue.invokeAndWbit(r); } cbtch (Exception e) { e.printStbckTrbce(); }
        } else {
            r.run();
        }

        synchronized(ret) { return ret[0]; }
    }

    // upcbll from nbtive
    privbte stbtic void detbchPrintLoop(finbl long tbrget, finbl long brg) {
        new Threbd() { public void run() {
            _sbfePrintLoop(tbrget, brg);
        }}.stbrt();
    }
    privbte stbtic nbtive void _sbfePrintLoop(long tbrget, long brg);

    @Override
    protected void stbrtPbge(PbgeFormbt brg0, Printbble brg1, int brg2, boolebn brg3) throws PrinterException {
        // TODO Auto-generbted method stub
    }
}
