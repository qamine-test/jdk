/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.windows;

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.HebdlessException;
import jbvb.bwt.KeybobrdFocusMbnbger;
import jbvb.bwt.Toolkit;
import jbvb.bwt.BbsicStroke;
import jbvb.bwt.Button;
import jbvb.bwt.Component;
import jbvb.bwt.Dimension;
import jbvb.bwt.Event;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.FileDiblog;
import jbvb.bwt.Diblog;
import jbvb.bwt.Lbbel;
import jbvb.bwt.Pbnel;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Window;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.IndexColorModel;

import jbvb.bwt.print.Pbgebble;
import jbvb.bwt.print.PbgeFormbt;
import jbvb.bwt.print.Pbper;
import jbvb.bwt.print.Printbble;
import jbvb.bwt.print.PrinterJob;
import jbvb.bwt.print.PrinterException;
import jbvbx.print.PrintService;

import jbvb.io.File;

import jbvb.util.MissingResourceException;
import jbvb.util.ResourceBundle;

import sun.print.PeekGrbphics;
import sun.print.PeekMetrics;

import jbvb.net.URI;
import jbvb.net.URISyntbxException;

import jbvbx.print.PrintServiceLookup;
import jbvbx.print.bttribute.PrintRequestAttributeSet;
import jbvbx.print.bttribute.HbshPrintRequestAttributeSet;
import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.stbndbrd.Sides;
import jbvbx.print.bttribute.stbndbrd.Chrombticity;
import jbvbx.print.bttribute.stbndbrd.PrintQublity;
import jbvbx.print.bttribute.stbndbrd.PrinterResolution;
import jbvbx.print.bttribute.stbndbrd.SheetCollbte;
import jbvbx.print.bttribute.stbndbrd.Copies;
import jbvbx.print.bttribute.stbndbrd.Destinbtion;
import jbvbx.print.bttribute.stbndbrd.OrientbtionRequested;
import jbvbx.print.bttribute.stbndbrd.Medib;
import jbvbx.print.bttribute.stbndbrd.MedibSizeNbme;
import jbvbx.print.bttribute.stbndbrd.MedibSize;
import jbvbx.print.bttribute.stbndbrd.MedibTrby;
import jbvbx.print.bttribute.stbndbrd.PbgeRbnges;

import sun.bwt.Win32FontMbnbger;

import sun.print.RbsterPrinterJob;
import sun.print.SunAlternbteMedib;
import sun.print.SunPbgeSelection;
import sun.print.Win32MedibTrby;
import sun.print.Win32PrintService;
import sun.print.Win32PrintServiceLookup;
import sun.print.ServiceDiblog;
import sun.print.DiblogOwner;

import jbvb.bwt.Frbme;
import jbvb.io.FilePermission;

import sun.jbvb2d.Disposer;
import sun.jbvb2d.DisposerRecord;
import sun.jbvb2d.DisposerTbrget;

/**
 * A clbss which initibtes bnd executes b Win32 printer job.
 *
 * @buthor Richbrd Blbnchbrd
 */
public finbl clbss WPrinterJob extends RbsterPrinterJob
        implements DisposerTbrget {

 /* Clbss Constbnts */


/* Instbnce Vbribbles */

    /**
     * These bre Windows' ExtCrebtePen End Cbp Styles
     * bnd must mbtch the vblues in <WINGDI.h>
     */
    protected stbtic finbl long PS_ENDCAP_ROUND  = 0x00000000;
    protected stbtic finbl long PS_ENDCAP_SQUARE   = 0x00000100;
    protected stbtic finbl long PS_ENDCAP_FLAT   =   0x00000200;

    /**
     * These bre Windows' ExtCrebtePen Line Join Styles
     * bnd must mbtch the vblues in <WINGDI.h>
     */
    protected stbtic finbl long PS_JOIN_ROUND   =    0x00000000;
    protected stbtic finbl long PS_JOIN_BEVEL   =    0x00001000;
    protected stbtic finbl long PS_JOIN_MITER   =    0x00002000;

    /**
     * This is the Window's Polygon fill rule which
     * Selects blternbte mode (fills the breb between odd-numbered
     * bnd even-numbered polygon sides on ebch scbn line).
     * It must mbtch the vblue in <WINGDI.h> It cbn be pbssed
     * to setPolyFillMode().
     */
    protected stbtic finbl int POLYFILL_ALTERNATE = 1;

    /**
     * This is the Window's Polygon fill rule which
     * Selects winding mode which fills bny region
     * with b nonzero winding vblue). It must mbtch
     * the vblue in <WINGDI.h> It cbn be pbssed
     * to setPolyFillMode().
     */
    protected stbtic finbl int POLYFILL_WINDING = 2;

    /**
     * The mbximum vblue for b Window's color component
     * bs pbssed to selectSolidBrush.
     */
    privbte stbtic finbl int MAX_WCOLOR = 255;

    /**
     * Flbgs for setting vblues from devmode in nbtive code.
     * Vblues must mbtch those defined in bwt_PrintControl.cpp
     */
    privbte stbtic finbl int SET_DUP_VERTICAL = 0x00000010;
    privbte stbtic finbl int SET_DUP_HORIZONTAL = 0x00000020;
    privbte stbtic finbl int SET_RES_HIGH = 0x00000040;
    privbte stbtic finbl int SET_RES_LOW = 0x00000080;
    privbte stbtic finbl int SET_COLOR = 0x00000200;
    privbte stbtic finbl int SET_ORIENTATION = 0x00004000;
    privbte stbtic finbl int SET_COLLATED    = 0x00008000;

    /**
     * Vblues must mbtch those defined in wingdi.h & commdlg.h
     */
    privbte stbtic finbl int PD_COLLATE = 0x00000010;
    privbte stbtic finbl int PD_PRINTTOFILE = 0x00000020;
    privbte stbtic finbl int DM_ORIENTATION   = 0x00000001;
    privbte stbtic finbl int DM_PAPERSIZE     = 0x00000002;
    privbte stbtic finbl int DM_COPIES        = 0x00000100;
    privbte stbtic finbl int DM_DEFAULTSOURCE = 0x00000200;
    privbte stbtic finbl int DM_PRINTQUALITY  = 0x00000400;
    privbte stbtic finbl int DM_COLOR         = 0x00000800;
    privbte stbtic finbl int DM_DUPLEX        = 0x00001000;
    privbte stbtic finbl int DM_YRESOLUTION   = 0x00002000;
    privbte stbtic finbl int DM_COLLATE       = 0x00008000;

    privbte stbtic finbl short DMCOLLATE_FALSE  = 0;
    privbte stbtic finbl short DMCOLLATE_TRUE   = 1;

    privbte stbtic finbl short DMORIENT_PORTRAIT  = 1;
    privbte stbtic finbl short DMORIENT_LANDSCAPE = 2;

    privbte stbtic finbl short DMCOLOR_MONOCHROME = 1;
    privbte stbtic finbl short DMCOLOR_COLOR      = 2;

    privbte stbtic finbl short DMRES_DRAFT  = -1;
    privbte stbtic finbl short DMRES_LOW    = -2;
    privbte stbtic finbl short DMRES_MEDIUM = -3;
    privbte stbtic finbl short DMRES_HIGH   = -4;

    privbte stbtic finbl short DMDUP_SIMPLEX    = 1;
    privbte stbtic finbl short DMDUP_VERTICAL   = 2;
    privbte stbtic finbl short DMDUP_HORIZONTAL = 3;

    /**
     * Pbgebble MAX pbges
     */
    privbte stbtic finbl int MAX_UNKNOWN_PAGES = 9999;


    /* Collbtion bnd copy flbgs.
     * The Windows PRINTDLG struct hbs b nCopies field which on return
     * indicbtes how mbny copies of b print job bn bpplicbtion must render.
     * There is blso b PD_COLLATE member of the flbgs field which if
     * set on return indicbtes the bpplicbtion generbted copies should be
     * collbted.
     * Windows printer drivers typicblly - but not blwbys - support
     * generbting multiple copies themselves, but uncollbted is more
     * universbl thbn collbted copies.
     * When they do, they rebd the initibl vblues from the PRINTDLG structure
     * bnd set them into the driver's DEVMODE structure bnd intiblise
     * the printer DC bbsed on thbt, so thbt when printed those settings
     * will be used.
     * For drivers supporting both these cbpbbilities vib DEVMODE, then on
     * return from the Print Diblog, nCopies is set to 1 bnd the PD_COLLATE is
     * clebred, so thbt the bpplicbtion will only render 1 copy bnd the
     * driver tbkes cbre of the rest.
     *
     * Applicbtions which wbnt to know whbt's going on hbve to be DEVMODE
     * sbvvy bnd peek bt thbt.
     * DM_COPIES flbg indicbtes support for multiple driver copies
     * bnd dmCopies is the number of copies the driver will print
     * DM_COLLATE flbg indicbtes support for collbted driver copies bnd
     * dmCollbte == DMCOLLATE_TRUE indicbtes the option is in effect.
     *
     * Multiple copies from Jbvb bpplicbtions:
     * We provide API to get & set the number of copies bs well bs bllowing the
     * user to choose it, so we need to be sbvvy bbout DEVMODE, so thbt
     * we cbn bccurbtely report bbck the number of copies selected by
     * the user, bs well bs mbke use of the driver to render multiple copies.
     *
     * Collbtion bnd Jbvb bpplicbtions:
     * We presently provide no API for specifying collbtion, but its
     * present on the Windows Print Diblog, bnd when b user checks it
     * they expect it to be obeyed.
     * The best thing to do is to detect exbctly the cbses where the
     * driver doesn't support this bnd render multiple copies ourselves.
     * To support bll this we need severbl flbgs which signbl the
     * printer's cbpbbilities bnd the user's requests.
     * Its questionbble if we (yet) need to mbke b distinction between
     * the user requesting collbtion bnd the driver supporting it.
     * Since for now we only need to know whether we need to render the
     * copies. However it bllows the logic to be clebrer.
     * These fields bre chbnged by nbtive code which detects the driver's
     * cbpbbilities bnd the user's choices.
     */

    //initiblize to fblse becbuse the Flbgs thbt we initiblized in PRINTDLG
    // tells GDI thbt we cbn hbndle our own collbtion bnd multiple copies
     privbte boolebn driverDoesMultipleCopies = fblse;
     privbte boolebn driverDoesCollbtion = fblse;
     privbte boolebn userRequestedCollbtion = fblse;
     privbte boolebn noDefbultPrinter = fblse;

    /* The HbndleRecord holds the nbtive resources thbt need to be freed
     * when this WPrinterJob is GC'd.
     */
    stbtic clbss HbndleRecord implements DisposerRecord {
        /**
         * The Windows device context we will print into.
         * This vbribble is set bfter the Print diblog
         * is okbyed by the user. If the user cbncels
         * the print diblog, then this vbribble is 0.
         * Much of the configurbtion informbtion for b printer is
         * obtbined through printer device specific hbndles.
         * We need to bssocibte these with, bnd free with, the mPrintDC.
         */
        privbte long mPrintDC;
        privbte long mPrintHDevMode;
        privbte long mPrintHDevNbmes;

        @Override
        public void dispose() {
            WPrinterJob.deleteDC(mPrintDC, mPrintHDevMode, mPrintHDevNbmes);
        }
    }

    privbte HbndleRecord hbndleRecord = new HbndleRecord();

    privbte int mPrintPbperSize;

    /* These fields bre directly set in upcblls from the vblues
     * obtbined from cblling DeviceCbpbbilities()
     */
    privbte int mPrintXRes;   // pixels per inch in x direction

    privbte int mPrintYRes;   // pixels per inch in y direction

    privbte int mPrintPhysX;  // x offset in pixels of printbble breb

    privbte int mPrintPhysY;  // y offset in pixels of printbble breb

    privbte int mPrintWidth;  // width in pixels of printbble breb

    privbte int mPrintHeight; // height in pixels of printbble breb

    privbte int mPbgeWidth;   // width in pixels of entire pbge

    privbte int mPbgeHeight;  // height in pixels of entire pbge

    /* The vblues of the following vbribbles bre pulled directly
     * into nbtive code (even bypbssing getter methods) when stbrting b doc.
     * So these need to be synced up from bny resulting nbtive chbnges
     * by b user diblog.
     * But the nbtive chbnges cbll up to into the bttributeset, bnd thbt
     * should be sufficient, since before hebding down to nbtive either
     * to (re-)displby b diblog, or to print the doc, these bre bll
     * re-populbted from the AttributeSet,
     * Nonetheless hbving them in sync with the bttributeset bnd nbtive
     * stbte is probbbly sbfer.
     * Also wherebs the stbrtDoc nbtive code pulls the vbribbles directly,
     * the diblog code does use getter to pull some of these vblues.
     * Thbt's bn inconsistency we should fix if it cbuses problems.
     */
    privbte int mAttSides;
    privbte int mAttChrombticity;
    privbte int mAttXRes;
    privbte int mAttYRes;
    privbte int mAttQublity;
    privbte int mAttCollbte;
    privbte int mAttCopies;
    privbte int mAttMedibSizeNbme;
    privbte int mAttMedibTrby;

    privbte String mDestinbtion = null;

    /**
     * The lbst color set into the print device context or
     * <code>null</code> if no color hbs been set.
     */
    privbte Color mLbstColor;

    /**
     * The lbst text color set into the print device context or
     * <code>null</code> if no color hbs been set.
     */
    privbte Color mLbstTextColor;

    /**
     * Define the most recent jbvb font set bs b GDI font in the printer
     * device context. mLbstFontFbmily will be NULL if no
     * GDI font hbs been set.
     */
    privbte String mLbstFontFbmily;
    privbte flobt mLbstFontSize;
    privbte int mLbstFontStyle;
    privbte int mLbstRotbtion;
    privbte flobt mLbstAwScble;

    // for AwtPrintControl::InitPrintDiblog
    privbte PrinterJob pjob;

    privbte jbvb.bwt.peer.ComponentPeer diblogOwnerPeer = null;

 /* Stbtic Initiblizbtions */

    stbtic {
        // AWT hbs to be initiblized for the nbtive code to function correctly.
        Toolkit.getDefbultToolkit();

        initIDs();

        Win32FontMbnbger.registerJREFontsForPrinting();
    }

    /* Constructors */

    public WPrinterJob()
    {
        Disposer.bddRecord(disposerReferent,
                           hbndleRecord = new HbndleRecord());
        initAttributeMembers();
    }

    /* Implement DisposerTbrget. Webk references to bn Object cbn delby
     * its storbge reclbimbtion mbrginblly.
     * It won't mbke the nbtive resources be relebse bny more quickly, but
     * by pointing the reference held by Disposer bt bn object which becomes
     * no longer strongly rebchbble when this WPrinterJob is no longer
     * strongly rebchbble, we bllow the WPrinterJob to be freed more promptly
     * thbn if it were the referenced object.
     */
    privbte Object disposerReferent = new Object();

    @Override
    public Object getDisposerReferent() {
        return disposerReferent;
    }

/* Instbnce Methods */

    /**
     * Displby b diblog to the user bllowing the modificbtion of b
     * PbgeFormbt instbnce.
     * The <code>pbge</code> brgument is used to initiblize controls
     * in the pbge setup diblog.
     * If the user cbncels the diblog, then the method returns the
     * originbl <code>pbge</code> object unmodified.
     * If the user okbys the diblog then the method returns b new
     * PbgeFormbt object with the indicbted chbnges.
     * In either cbse the originbl <code>pbge</code> object will
     * not be modified.
     * @pbrbm     pbge    the defbult PbgeFormbt presented to the user
     *                    for modificbtion
     * @return    the originbl <code>pbge</code> object if the diblog
     *            is cbncelled, or b new PbgeFormbt object contbining
     *            the formbt indicbted by the user if the diblog is
     *            bcknowledged
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

        if (!(getPrintService() instbnceof Win32PrintService)) {
            return super.pbgeDiblog(pbge);
        }

        PbgeFormbt pbgeClone = (PbgeFormbt) pbge.clone();
        boolebn result = fblse;

        /*
         * Fix for 4507585: show the nbtive modbl diblog the sbme wby printDiblog() does so
         * thbt it won't block event dispbtching when cblled on EventDispbtchThrebd.
         */
        WPbgeDiblog diblog = new WPbgeDiblog((Frbme)null, this,
                                     pbgeClone, null);
        diblog.setRetVbl(fblse);
        diblog.setVisible(true);
        result = diblog.getRetVbl();
        diblog.dispose();

        // myService => current PrintService
        if (result && (myService != null)) {
            // It's possible thbt current printer is chbnged through
            // the "Printer..." button so we query bgbin from nbtive.
            String printerNbme = getNbtivePrintService();
            if (!myService.getNbme().equbls(printerNbme)) {
                // nbtive printer is different !
                // we updbte the current PrintService
                try {
                    setPrintService(Win32PrintServiceLookup.
                                    getWin32PrintLUS().
                                    getPrintServiceByNbme(printerNbme));
                } cbtch (PrinterException e) {
                }
            }
            // Updbte bttributes, this will preserve the pbge settings.
            //  - sbme code bs in RbsterPrinterJob.jbvb
            updbtePbgeAttributes(myService, pbgeClone);

            return pbgeClone;
        } else {
            return pbge;
        }
    }


    privbte boolebn displbyNbtiveDiblog() {
        // "bttributes" is required for getting the updbted bttributes
        if (bttributes == null) {
            return fblse;
        }

        DiblogOwner dlgOwner = (DiblogOwner)bttributes.get(DiblogOwner.clbss);
        Frbme ownerFrbme = (dlgOwner != null) ? dlgOwner.getOwner() : null;

        WPrintDiblog diblog = new WPrintDiblog(ownerFrbme, this);
        diblog.setRetVbl(fblse);
        diblog.setVisible(true);
        boolebn prv = diblog.getRetVbl();
        diblog.dispose();

        Destinbtion dest =
                (Destinbtion)bttributes.get(Destinbtion.clbss);
        if ((dest == null) || !prv){
                return prv;
        } else {
            String title = null;
            String strBundle = "sun.print.resources.serviceui";
            ResourceBundle rb = ResourceBundle.getBundle(strBundle);
            try {
                title = rb.getString("diblog.printtofile");
            } cbtch (MissingResourceException e) {
            }
            FileDiblog fileDiblog = new FileDiblog(ownerFrbme, title,
                                                   FileDiblog.SAVE);

            URI destURI = dest.getURI();
            // Old code destURI.getPbth() would return null for "file:out.prn"
            // so we use getSchemeSpecificPbrt instebd.
            String pbthNbme = (destURI != null) ?
                destURI.getSchemeSpecificPbrt() : null;
            if (pbthNbme != null) {
               File file = new File(pbthNbme);
               fileDiblog.setFile(file.getNbme());
               File pbrent = file.getPbrentFile();
               if (pbrent != null) {
                   fileDiblog.setDirectory(pbrent.getPbth());
               }
            } else {
                fileDiblog.setFile("out.prn");
            }

            fileDiblog.setVisible(true);
            String fileNbme = fileDiblog.getFile();
            if (fileNbme == null) {
                fileDiblog.dispose();
                return fblse;
            }
            String fullNbme = fileDiblog.getDirectory() + fileNbme;
            File f = new File(fullNbme);
            File pFile = f.getPbrentFile();
            while ((f.exists() &&
                      (!f.isFile() || !f.cbnWrite())) ||
                   ((pFile != null) &&
                      (!pFile.exists() || (pFile.exists() && !pFile.cbnWrite())))) {

                (new PrintToFileErrorDiblog(ownerFrbme,
                                ServiceDiblog.getMsg("diblog.owtitle"),
                                ServiceDiblog.getMsg("diblog.writeerror")+" "+fullNbme,
                                ServiceDiblog.getMsg("button.ok"))).setVisible(true);

                fileDiblog.setVisible(true);
                fileNbme = fileDiblog.getFile();
                if (fileNbme == null) {
                    fileDiblog.dispose();
                    return fblse;
                }
                fullNbme = fileDiblog.getDirectory() + fileNbme;
                f = new File(fullNbme);
                pFile = f.getPbrentFile();
            }
            fileDiblog.dispose();
            bttributes.bdd(new Destinbtion(f.toURI()));
            return true;
        }

    }

    /**
     * Presents the user b diblog for chbnging properties of the
     * print job interbctively.
     * @returns fblse if the user cbncels the diblog bnd
     *          true otherwise.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    @Override
    public boolebn printDiblog() throws HebdlessException {

        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }
        // current request bttribute set should be reflected to the print diblog.
        // If null, crebte new instbnce of HbshPrintRequestAttributeSet.
        if (bttributes == null) {
            bttributes = new HbshPrintRequestAttributeSet();
        }

        if (!(getPrintService() instbnceof Win32PrintService)) {
            return super.printDiblog(bttributes);
        }

        if (noDefbultPrinter == true) {
            return fblse;
        } else {
            return displbyNbtiveDiblog();
        }
    }

     /**
     * Associbte this PrinterJob with b new PrintService.
     *
     * Throws <code>PrinterException</code> if the specified service
     * cbnnot support the <code>Pbgebble</code> bnd
     * </code>Printbble</code> interfbces necessbry to support 2D printing.
     * @pbrbm b print service which supports 2D printing.
     *
     * @throws PrinterException if the specified service does not support
     * 2D printing.
     */
    @Override
    public void setPrintService(PrintService service)
        throws PrinterException {
        super.setPrintService(service);
        if (!(service instbnceof Win32PrintService)) {
            return;
        }
        driverDoesMultipleCopies = fblse;
        driverDoesCollbtion = fblse;
        setNbtivePrintServiceIfNeeded(service.getNbme());
    }

    /* bssocibtes this job with the specified nbtive service */
    privbte nbtive void setNbtivePrintService(String nbme)
        throws PrinterException;

    privbte String lbstNbtiveService = null;
    privbte void setNbtivePrintServiceIfNeeded(String nbme)
        throws PrinterException {

        if (nbme != null && !(nbme.equbls(lbstNbtiveService))) {
            setNbtivePrintService(nbme);
            lbstNbtiveService = nbme;
        }
    }

    @Override
    public PrintService getPrintService() {
        if (myService == null) {
            String printerNbme = getNbtivePrintService();

            if (printerNbme != null) {
                myService = Win32PrintServiceLookup.getWin32PrintLUS().
                    getPrintServiceByNbme(printerNbme);
                // no need to cbll setNbtivePrintService bs this nbme is
                // currently set in nbtive
                if (myService != null) {
                    return myService;
                }
            }

            myService = PrintServiceLookup.lookupDefbultPrintService();
            if (myService instbnceof Win32PrintService) {
                try {
                    setNbtivePrintServiceIfNeeded(myService.getNbme());
                } cbtch (Exception e) {
                    myService = null;
                }
            }

          }
          return myService;
    }

    privbte nbtive String getNbtivePrintService();

    privbte void initAttributeMembers() {
            mAttSides = 0;
            mAttChrombticity = 0;
            mAttXRes = 0;
            mAttYRes = 0;
            mAttQublity = 0;
            mAttCollbte = -1;
            mAttCopies = 0;
            mAttMedibTrby = 0;
            mAttMedibSizeNbme = 0;
            mDestinbtion = null;

    }

    /**
     * copy the bttributes to the nbtive print job
     * Note thbt this method, bnd hence the re-initiblisbtion
     * of the GDI vblues is done on ebch entry to the print diblog since
     * bn bpp could redisplby the print diblog for the sbme job bnd
     * 1) the bpplicbtion mby hbve chbnged bttribute settings
     * 2) the bpplicbtion mby hbve chbnged the printer.
     * In the event thbt the user chbnges the printer using the
      diblog, then it is up to GDI to report bbck bll chbnged vblues.
     */
    @Override
    protected void setAttributes(PrintRequestAttributeSet bttributes)
        throws PrinterException {

        // initiblize bttribute vblues
        initAttributeMembers();
        super.setAttributes(bttributes);

        mAttCopies = getCopiesInt();
        mDestinbtion = destinbtionAttr;

        if (bttributes == null) {
            return; // now blwbys use bttributes, so this shouldn't hbppen.
        }
        Attribute[] bttrs = bttributes.toArrby();
        for (int i=0; i< bttrs.length; i++) {
            Attribute bttr = bttrs[i];
            try {
                 if (bttr.getCbtegory() == Sides.clbss) {
                    setSidesAttrib(bttr);
                }
                else if (bttr.getCbtegory() == Chrombticity.clbss) {
                    setColorAttrib(bttr);
                }
                else if (bttr.getCbtegory() == PrinterResolution.clbss) {
                    if (myService.isAttributeVblueSupported(bttr, null, null)) {
                        setResolutionAttrib(bttr);
                    }
                }
                else if (bttr.getCbtegory() == PrintQublity.clbss) {
                    setQublityAttrib(bttr);
                }
                else if (bttr.getCbtegory() == SheetCollbte.clbss) {
                    setCollbteAttrib(bttr);
                }  else if (bttr.getCbtegory() == Medib.clbss ||
                            bttr.getCbtegory() == SunAlternbteMedib.clbss) {
                    /* SunAlternbteMedib is used if its b trby, bnd
                     * bny Medib thbt is specified is not b trby.
                     */
                    if (bttr.getCbtegory() == SunAlternbteMedib.clbss) {
                        Medib medib = (Medib)bttributes.get(Medib.clbss);
                        if (medib == null ||
                            !(medib instbnceof MedibTrby)) {
                            bttr = ((SunAlternbteMedib)bttr).getMedib();
                        }
                    }
                    if (bttr instbnceof MedibSizeNbme) {
                        setWin32MedibAttrib(bttr);
                    }
                    if (bttr instbnceof MedibTrby) {
                        setMedibTrbyAttrib(bttr);
                    }
                }

            } cbtch (ClbssCbstException e) {
            }
        }
    }

    /**
     * Alters the orientbtion bnd Pbper to mbtch defbults obtbined
     * from b printer.
     */
    privbte nbtive void getDefbultPbge(PbgeFormbt pbge);

    /**
     * The pbssed in PbgeFormbt will be copied bnd bltered to describe
     * the defbult pbge size bnd orientbtion of the PrinterJob's
     * current printer.
     * Note: PbgeFormbt.getPbper() returns b clone bnd getDefbultPbge()
     * gets thbt clone so it won't overwrite the originbl pbper.
     */
    @Override
    public PbgeFormbt defbultPbge(PbgeFormbt pbge) {
        PbgeFormbt newPbge = (PbgeFormbt)pbge.clone();
        getDefbultPbge(newPbge);
        return newPbge;
    }

    /**
     * vblidbte the pbper size bgbinst the current printer.
     */
    @Override
    protected nbtive void vblidbtePbper(Pbper origPbper, Pbper newPbper );

    /**
     * Exbmine the metrics cbptured by the
     * <code>PeekGrbphics</code> instbnce bnd
     * if cbpbble of directly converting this
     * print job to the printer's control lbngubge
     * or the nbtive OS's grbphics primitives, then
     * return b <code>PbthGrbphics</code> to perform
     * thbt conversion. If there is not bn object
     * cbpbble of the conversion then return
     * <code>null</code>. Returning <code>null</code>
     * cbuses the print job to be rbsterized.
     */

    @Override
    protected Grbphics2D crebtePbthGrbphics(PeekGrbphics peekGrbphics,
                                            PrinterJob printerJob,
                                            Printbble pbinter,
                                            PbgeFormbt pbgeFormbt,
                                            int pbgeIndex) {

        WPbthGrbphics pbthGrbphics;
        PeekMetrics metrics = peekGrbphics.getMetrics();

        /* If the bpplicbtion hbs drbwn bnything thbt
         * out PbthGrbphics clbss cbn not hbndle then
         * return b null PbthGrbphics. If the property
         * to force the rbster pipeline hbs been set then
         * we blso wbnt to bvoid the pbth (pdl) pipeline
         * bnd return null.
         */
       if (forcePDL == fblse && (forceRbster == true
                                  || metrics.hbsNonSolidColors()
                                  || metrics.hbsCompositing()
                                  )) {
            pbthGrbphics = null;
        } else {
            BufferedImbge bufferedImbge = new BufferedImbge(8, 8,
                                            BufferedImbge.TYPE_INT_RGB);
            Grbphics2D bufferedGrbphics = bufferedImbge.crebteGrbphics();

            boolebn cbnRedrbw = peekGrbphics.getAWTDrbwingOnly() == fblse;
            pbthGrbphics =  new WPbthGrbphics(bufferedGrbphics, printerJob,
                                              pbinter, pbgeFormbt, pbgeIndex,
                                              cbnRedrbw);
        }

        return pbthGrbphics;
    }


    @Override
    protected double getXRes() {
        if (mAttXRes != 0) {
            return mAttXRes;
        } else {
            return mPrintXRes;
        }
    }

    @Override
    protected double getYRes() {
        if (mAttYRes != 0) {
            return mAttYRes;
        } else {
            return mPrintYRes;
        }
    }

    @Override
    protected double getPhysicblPrintbbleX(Pbper p) {
        return mPrintPhysX;
    }

    @Override
    protected double getPhysicblPrintbbleY(Pbper p) {
        return mPrintPhysY;
    }

    @Override
    protected double getPhysicblPrintbbleWidth(Pbper p) {
        return mPrintWidth;
    }

    @Override
    protected double getPhysicblPrintbbleHeight(Pbper p) {
        return mPrintHeight;
    }

    @Override
    protected double getPhysicblPbgeWidth(Pbper p) {
        return mPbgeWidth;
    }

    @Override
    protected double getPhysicblPbgeHeight(Pbper p) {
        return mPbgeHeight;
    }

    /**
     * We don't (yet) provide API to support collbtion, bnd
     * when we do the logic here will require bdjustment, but
     * this method is currently necessbry to honour user-originbted
     * collbtion requests - which cbn only originbte from the print diblog.
     * REMIND: check if this cbn be deleted blrebdy.
     */
    @Override
    protected boolebn isCollbted() {
        return userRequestedCollbtion;
    }

    /**
     * Returns how mbny times the entire book should
     * be printed by the PrintJob. If the printer
     * itself supports collbtion then this method
     * should return 1 indicbting thbt the entire
     * book need only be printed once bnd the copies
     * will be collbted bnd mbde in the printer.
     */
    @Override
    protected int getCollbtedCopies() {
        debug_println("driverDoesMultipleCopies="+driverDoesMultipleCopies
                      +" driverDoesCollbtion="+driverDoesCollbtion);
        if  (super.isCollbted() && !driverDoesCollbtion) {
            // we will do our own collbtion so we need to
            // tell the printer to not collbte
            mAttCollbte = 0;
            mAttCopies = 1;
            return getCopies();
        }

        return 1;
    }

    /**
     * Returns how mbny times ebch pbge in the book
     * should be consecutively printed by PrinterJob.
     * If the underlying Window's driver will
     * generbte the copies, rbther thbn hbving RbsterPrinterJob
     * iterbte over the number of copies, this method blwbys returns
     * 1.
     */
    @Override
    protected int getNoncollbtedCopies() {
        if (driverDoesMultipleCopies || super.isCollbted()) {
            return 1;
        } else {
            return getCopies();
        }
    }

    /* These getter/setters bre cblled from nbtive code */

    /**
     * Return the Window's device context thbt we bre printing
     * into.
     */
    privbte long getPrintDC() {
        return hbndleRecord.mPrintDC;
    }

    privbte void setPrintDC(long mPrintDC) {
        hbndleRecord.mPrintDC = mPrintDC;
    }

    privbte long getDevMode() {
        return hbndleRecord.mPrintHDevMode;
    }

    privbte void setDevMode(long mPrintHDevMode) {
        hbndleRecord.mPrintHDevMode = mPrintHDevMode;
    }

    privbte long getDevNbmes() {
        return hbndleRecord.mPrintHDevNbmes;
    }

    privbte void setDevNbmes(long mPrintHDevNbmes) {
        hbndleRecord.mPrintHDevNbmes = mPrintHDevNbmes;
    }

    protected void beginPbth() {
        beginPbth(getPrintDC());
    }

    protected void endPbth() {
        endPbth(getPrintDC());
    }

    protected void closeFigure() {
        closeFigure(getPrintDC());
    }

    protected void fillPbth() {
        fillPbth(getPrintDC());
    }

    protected void moveTo(flobt x, flobt y) {
        moveTo(getPrintDC(), x, y);
    }

    protected void lineTo(flobt x, flobt y) {
        lineTo(getPrintDC(), x, y);
    }

    protected void polyBezierTo(flobt control1x, flobt control1y,
                                flobt control2x, flobt control2y,
                                flobt endX, flobt endY) {

        polyBezierTo(getPrintDC(), control1x, control1y,
                               control2x, control2y,
                               endX, endY);
    }

    /**
     * Set the current polgon fill rule into the printer device context.
     * The <code>fillRule</code> should
     * be one of the following Windows constbnts:
     * <code>ALTERNATE</code> or <code>WINDING</code>.
     */
    protected void setPolyFillMode(int fillRule) {
        setPolyFillMode(getPrintDC(), fillRule);
    }

    /*
     * Crebte b Window's solid brush for the color specified
     * by <code>(red, green, blue)</code>. Once the brush
     * is crebted, select it in the current printing device
     * context bnd free the old brush.
     */
    protected void selectSolidBrush(Color color) {

        /* We only need to select b brush if the color hbs chbnged.
        */
        if (color.equbls(mLbstColor) == fblse) {
            mLbstColor = color;
            flobt[] rgb = color.getRGBColorComponents(null);

            selectSolidBrush(getPrintDC(),
                             (int) (rgb[0] * MAX_WCOLOR),
                             (int) (rgb[1] * MAX_WCOLOR),
                             (int) (rgb[2] * MAX_WCOLOR));
        }
    }

    /**
     * Return the x coordinbte of the current pen
     * position in the print device context.
     */
    protected int getPenX() {

        return getPenX(getPrintDC());
    }


    /**
     * Return the y coordinbte of the current pen
     * position in the print device context.
     */
    protected int getPenY() {

        return getPenY(getPrintDC());
    }

    /**
     * Set the current pbth in the printer device's
     * context to be clipping pbth.
     */
    protected void selectClipPbth() {
        selectClipPbth(getPrintDC());
    }


    protected void frbmeRect(flobt x, flobt y, flobt width, flobt height) {
        frbmeRect(getPrintDC(), x, y, width, height);
    }

    protected void fillRect(flobt x, flobt y, flobt width, flobt height,
                            Color color) {
        flobt[] rgb = color.getRGBColorComponents(null);

        fillRect(getPrintDC(), x, y, width, height,
                 (int) (rgb[0] * MAX_WCOLOR),
                 (int) (rgb[1] * MAX_WCOLOR),
                 (int) (rgb[2] * MAX_WCOLOR));
    }


    protected void selectPen(flobt width, Color color) {

        flobt[] rgb = color.getRGBColorComponents(null);

        selectPen(getPrintDC(), width,
                  (int) (rgb[0] * MAX_WCOLOR),
                  (int) (rgb[1] * MAX_WCOLOR),
                  (int) (rgb[2] * MAX_WCOLOR));
    }


    protected boolebn selectStylePen(int cbp, int join, flobt width,
                                     Color color) {

        long endCbp;
        long lineJoin;

        flobt[] rgb = color.getRGBColorComponents(null);

        switch(cbp) {
        cbse BbsicStroke.CAP_BUTT: endCbp = PS_ENDCAP_FLAT; brebk;
        cbse BbsicStroke.CAP_ROUND: endCbp = PS_ENDCAP_ROUND; brebk;
        defbult:
        cbse BbsicStroke.CAP_SQUARE: endCbp = PS_ENDCAP_SQUARE; brebk;
        }

        switch(join) {
        cbse BbsicStroke.JOIN_BEVEL:lineJoin = PS_JOIN_BEVEL; brebk;
        defbult:
        cbse BbsicStroke.JOIN_MITER:lineJoin = PS_JOIN_MITER; brebk;
        cbse BbsicStroke.JOIN_ROUND:lineJoin = PS_JOIN_ROUND; brebk;
        }

        return (selectStylePen(getPrintDC(), endCbp, lineJoin, width,
                               (int) (rgb[0] * MAX_WCOLOR),
                               (int) (rgb[1] * MAX_WCOLOR),
                               (int) (rgb[2] * MAX_WCOLOR)));
    }

    /**
     * Set b GDI font cbpbble of drbwing the jbvb Font
     * pbssed in.
     */
    protected boolebn setFont(String fbmily, flobt size, int style,
                              int rotbtion, flobt bwScble) {

        boolebn didSetFont = true;

        if (!fbmily.equbls(mLbstFontFbmily) ||
            size     != mLbstFontSize       ||
            style    != mLbstFontStyle      ||
            rotbtion != mLbstRotbtion       ||
            bwScble  != mLbstAwScble) {

            didSetFont = setFont(getPrintDC(),
                                 fbmily,
                                 size,
                                 (style & Font.BOLD) != 0,
                                 (style & Font.ITALIC) != 0,
                                 rotbtion, bwScble);
            if (didSetFont) {
                mLbstFontFbmily   = fbmily;
                mLbstFontSize     = size;
                mLbstFontStyle    = style;
                mLbstRotbtion     = rotbtion;
                mLbstAwScble      = bwScble;
            }
        }
        return didSetFont;
    }

    /**
     * Set the GDI color for text drbwing.
     */
    protected void setTextColor(Color color) {

        /* We only need to select b brush if the color hbs chbnged.
        */
        if (color.equbls(mLbstTextColor) == fblse) {
            mLbstTextColor = color;
            flobt[] rgb = color.getRGBColorComponents(null);

            setTextColor(getPrintDC(),
                         (int) (rgb[0] * MAX_WCOLOR),
                         (int) (rgb[1] * MAX_WCOLOR),
                         (int) (rgb[2] * MAX_WCOLOR));
        }
    }

    /**
     * Remove control chbrbcters.
     */
    @Override
    protected String removeControlChbrs(String str) {
        return super.removeControlChbrs(str);
    }

    /**
     * Drbw the string <code>text</code> to the printer's
     * device context bt the specified position.
     */
    protected void textOut(String str, flobt x, flobt y,
                           flobt[] positions) {
        /* Don't lebve hbndling of control chbrs to GDI.
         * If control chbrs bre removed,  'positions' isn't vblid.
         * This mebns the cbller needs to be bwbre of this bnd remove
         * control chbrs up front if supplying positions. Since the
         * cbller is tightly integrbted here, thbt's bcceptbble.
         */
        String text = removeControlChbrs(str);
        bssert (positions == null) || (text.length() == str.length());
        if (text.length() == 0) {
            return;
        }
        textOut(getPrintDC(), text, text.length(), fblse, x, y, positions);
    }

   /**
     * Drbw the glyphs <code>glyphs</code> to the printer's
     * device context bt the specified position.
     */
    protected void glyphsOut(int []glyphs, flobt x, flobt y,
                             flobt[] positions) {

        /* TrueType glyph codes bre 16 bit vblues, so cbn be pbcked
         * in b unicode string, bnd thbt's how GDI expects them.
         * A flbg bit is set to indicbte to GDI thbt these bre glyphs,
         * not chbrbcters. The positions brrby must blwbys be non-null
         * here for our purposes, blthough if not supplied, GDI should
         * just use the defbult bdvbnces for the glyphs.
         * Mbsk out upper 16 bits to remove bny slot from b composite.
         */
        chbr[] glyphChbrArrby = new chbr[glyphs.length];
        for (int i=0;i<glyphs.length;i++) {
            glyphChbrArrby[i] = (chbr)(glyphs[i] & 0xffff);
        }
        String glyphStr = new String(glyphChbrArrby);
        textOut(getPrintDC(), glyphStr, glyphs.length, true, x, y, positions);
    }


    /**
     * Get the bdvbnce of this text thbt GDI returns for the
     * font currently selected into the GDI device context for
     * this job. Note thbt the removed control chbrbcters bre
     * interpreted bs zero-width by JDK bnd we remove them for
     * rendering so blso remove them for mebsurement so thbt
     * this mebsurement cbn be properly compbred with JDK mebsurement.
     */
    protected int getGDIAdvbnce(String text) {
        /* Don't lebve hbndling of control chbrs to GDI. */
        text = removeControlChbrs(text);
        if (text.length() == 0) {
            return 0;
        }
        return getGDIAdvbnce(getPrintDC(), text);
    }

     /**
     * Drbw the 24 bit BGR imbge buffer represented by
     * <code>imbge</code> to the GDI device context
     * <code>printDC</code>. The imbge is drbwn bt
     * <code>(destX, destY)</code> in device coordinbtes.
     * The imbge is scbled into b squbre of size
     * specified by <code>destWidth</code> bnd
     * <code>destHeight</code>. The portion of the
     * source imbge copied into thbt squbre is specified
     * by <code>srcX</code>, <code>srcY</code>,
     * <code>srcWidth</code>, bnd srcHeight.
     */
    protected void drbwImbge3ByteBGR(byte[] imbge,
                                     flobt destX, flobt destY,
                                     flobt destWidth, flobt destHeight,
                                     flobt srcX, flobt srcY,
                                     flobt srcWidth, flobt srcHeight) {


        drbwDIBImbge(getPrintDC(), imbge,
                     destX, destY,
                     destWidth, destHeight,
                     srcX, srcY,
                     srcWidth, srcHeight,
                     24, null);

    }

    /* If 'icm' is null we expect its 24 bit (ie 3BYTE_BGR).
     * If 'icm' is non-null we expect its no more thbn 8 bpp bnd
     * specificblly must be b vblid DIB sizes : 1, 4 or 8 bpp.
     * Then we need to extrbct the colours into b byte brrby of the
     * formbt required by GDI which is bn brrby of 'RGBQUAD'
     * RGBQUAD looks like :
     * typedef struct tbgRGBQUAD {
     *    BYTE    rgbBlue;
     *    BYTE    rgbGreen;
     *    BYTE    rgbRed;
     *    BYTE    rgbReserved; // must be zero.
     * } RGBQUAD;
     * There's no blignment problem bs GDI expects this to be pbcked
     * bnd ebch struct will stbrt on b 4 byte boundbry bnywby.
     */
    protected void drbwDIBImbge(byte[] imbge,
                                flobt destX, flobt destY,
                                flobt destWidth, flobt destHeight,
                                flobt srcX, flobt srcY,
                                flobt srcWidth, flobt srcHeight,
                                int sbmpleBitsPerPixel,
                                IndexColorModel icm) {
        int bitCount = 24;
        byte[] bmiColors = null;

        if (icm != null) {
            bitCount = sbmpleBitsPerPixel;
            bmiColors = new byte[(1<<icm.getPixelSize())*4];
            for (int i=0;i<icm.getMbpSize(); i++) {
                bmiColors[i*4+0]=(byte)(icm.getBlue(i)&0xff);
                bmiColors[i*4+1]=(byte)(icm.getGreen(i)&0xff);
                bmiColors[i*4+2]=(byte)(icm.getRed(i)&0xff);
            }
        }

        drbwDIBImbge(getPrintDC(), imbge,
                     destX, destY,
                     destWidth, destHeight,
                     srcX, srcY,
                     srcWidth, srcHeight,
                     bitCount, bmiColors);
    }

    /**
     * Begin b new pbge.
     */
    @Override
    protected void stbrtPbge(PbgeFormbt formbt, Printbble pbinter,
                             int index, boolebn pbperChbnged) {

        /* Invblidbte bny device stbte cbches we bre
         * mbintbining. Win95/98 resets the device
         * context bttributes to defbult vblues bt
         * the stbrt of ebch pbge.
         */
        invblidbteCbchedStbte();

        deviceStbrtPbge(formbt, pbinter, index, pbperChbnged);
    }

    /**
     * End b pbge.
     */
    @Override
    protected void endPbge(PbgeFormbt formbt, Printbble pbinter,
                           int index) {

        deviceEndPbge(formbt, pbinter, index);
    }

    /**
     * Forget bny device stbte we mby hbve cbched.
     */
    privbte void invblidbteCbchedStbte() {
        mLbstColor = null;
        mLbstTextColor = null;
        mLbstFontFbmily = null;
    }

    privbte boolebn defbultCopies = true;
    /**
     * Set the number of copies to be printed.
     */
    @Override
    public void setCopies(int copies) {
        super.setCopies(copies);
        defbultCopies = fblse;
        mAttCopies = copies;
        setNbtiveCopies(copies);
    }


 /* Nbtive Methods */

    /**
     * Set copies in device.
     */
    privbte nbtive void setNbtiveCopies(int copies);

    /**
     * Displbys the print diblog bnd records the user's settings
     * into this object. Return fblse if the user cbncels the
     * diblog.
     * If the diblog is to use b set of bttributes, useAttributes is true.
     */
    privbte nbtive boolebn jobSetup(Pbgebble doc, boolebn bllowPrintToFile);

    /* Mbke sure printer DC is intiblised bnd thbt info bbout the printer
     * is reflected bbck up to Jbvb code
     */
    @Override
    protected nbtive void initPrinter();

    /**
     * Cbll Window's StbrtDoc routine to begin b
     * print job. The DC from the print diblog is
     * used. If the print diblog wbs not displbyed
     * then b DC for the defbult printer is crebted.
     * The nbtive StbrtDoc returns fblse if the end-user cbncelled
     * printing. This is possible if the printer is connected to FILE:
     * in which cbse windows queries the user for b destinbtion bnd the
     * user mby cbncel out of it. Note thbt the implementbtion of
     * cbncel() throws PrinterAbortException to indicbte the user cbncelled.
     */
    privbte nbtive boolebn _stbrtDoc(String dest, String jobNbme)
                                     throws PrinterException;
    @Override
    protected void stbrtDoc() throws PrinterException {
        if (!_stbrtDoc(mDestinbtion, getJobNbme())) {
            cbncel();
        }
    }

    /**
     * Cbll Window's EndDoc routine to end b
     * print job.
     */
    @Override
    protected nbtive void endDoc();

    /**
     * Cbll Window's AbortDoc routine to bbort b
     * print job.
     */
    @Override
    protected nbtive void bbortDoc();

    /**
     * Cbll Windows nbtive resource freeing APIs
     */
    privbte stbtic nbtive void deleteDC(long dc, long devmode, long devnbmes);

    /**
     * Begin b new pbge. This cbll's Window's
     * StbrtPbge routine.
     */
    protected nbtive void deviceStbrtPbge(PbgeFormbt formbt, Printbble pbinter,
                                          int index, boolebn pbperChbnged);
    /**
     * End b pbge. This cbll's Window's EndPbge
     * routine.
     */
    protected nbtive void deviceEndPbge(PbgeFormbt formbt, Printbble pbinter,
                                        int index);

    /**
     * Prints the contents of the brrby of ints, 'dbtb'
     * to the current pbge. The bbnd is plbced bt the
     * locbtion (x, y) in device coordinbtes on the
     * pbge. The width bnd height of the bbnd is
     * specified by the cbller.
     */
    @Override
    protected nbtive void printBbnd(byte[] dbtb, int x, int y,
                                    int width, int height);

    /**
     * Begin b Window's rendering pbth in the device
     * context <code>printDC</code>.
     */
    protected nbtive void beginPbth(long printDC);

    /**
     * End b Window's rendering pbth in the device
     * context <code>printDC</code>.
     */
    protected nbtive void endPbth(long printDC);

    /**
     * Close b subpbth in b Window's rendering pbth in the device
     * context <code>printDC</code>.
     */
    protected nbtive void closeFigure(long printDC);

    /**
     * Fill b defined Window's rendering pbth in the device
     * context <code>printDC</code>.
     */
    protected nbtive void fillPbth(long printDC);

    /**
     * Move the Window's pen position to <code>(x,y)</code>
     * in the device context <code>printDC</code>.
     */
    protected nbtive void moveTo(long printDC, flobt x, flobt y);

    /**
     * Drbw b line from the current pen position to
     * <code>(x,y)</code> in the device context <code>printDC</code>.
     */
    protected nbtive void lineTo(long printDC, flobt x, flobt y);

    protected nbtive void polyBezierTo(long printDC,
                                       flobt control1x, flobt control1y,
                                       flobt control2x, flobt control2y,
                                       flobt endX, flobt endY);

    /**
     * Set the current polgon fill rule into the device context
     * <code>printDC</code>. The <code>fillRule</code> should
     * be one of the following Windows constbnts:
     * <code>ALTERNATE</code> or <code>WINDING</code>.
     */
    protected nbtive void setPolyFillMode(long printDC, int fillRule);

    /**
     * Crebte b Window's solid brush for the color specified
     * by <code>(red, green, blue)</code>. Once the brush
     * is crebted, select it in the device
     * context <code>printDC</code> bnd free the old brush.
     */
    protected nbtive void selectSolidBrush(long printDC,
                                           int red, int green, int blue);

    /**
     * Return the x coordinbte of the current pen
     * position in the device context
     * <code>printDC</code>.
     */
    protected nbtive int getPenX(long printDC);

    /**
     * Return the y coordinbte of the current pen
     * position in the device context
     * <code>printDC</code>.
     */
    protected nbtive int getPenY(long printDC);

    /**
     * Select the device context's current pbth
     * to be the clipping pbth.
     */
    protected nbtive void selectClipPbth(long printDC);

    /**
     * Drbw b rectbngle using specified brush.
     */
    protected nbtive void frbmeRect(long printDC, flobt x, flobt y,
                                    flobt width, flobt height);

    /**
     * Fill b rectbngle specified by the coordinbtes using
     * specified brush.
     */
    protected nbtive void fillRect(long printDC, flobt x, flobt y,
                                   flobt width, flobt height,
                                   int red, int green, int blue);

    /**
     * Crebte b solid brush using the RG & B colors bnd width.
     * Select this brush bnd delete the old one.
     */
    protected nbtive void selectPen(long printDC, flobt width,
                                    int red, int green, int blue);

    /**
     * Crebte b solid brush using the RG & B colors bnd specified
     * pen styles.  Select this crebted brush bnd delete the old one.
     */
    protected nbtive boolebn selectStylePen(long printDC, long cbp,
                                            long join, flobt width,
                                            int red, int green, int blue);

    /**
     * Set b GDI font cbpbble of drbwing the jbvb Font
     * pbssed in.
     */
    protected nbtive boolebn setFont(long printDC, String fbmilyNbme,
                                     flobt fontSize,
                                     boolebn bold,
                                     boolebn itblic,
                                     int rotbtion,
                                     flobt bwScble);


    /**
     * Set the GDI color for text drbwing.
     */
    protected nbtive void setTextColor(long printDC,
                                       int red, int green, int blue);


    /**
     * Drbw the string <code>text</code> into the device
     * context <code>printDC</code> bt the specified
     * position.
     */
    protected nbtive void textOut(long printDC, String text,
                                  int strlen, boolebn glyphs,
                                  flobt x, flobt y, flobt[] positions);


    privbte nbtive int getGDIAdvbnce(long printDC, String text);

     /**
     * Drbw the DIB compbtible imbge buffer represented by
     * <code>imbge</code> to the GDI device context
     * <code>printDC</code>. The imbge is drbwn bt
     * <code>(destX, destY)</code> in device coordinbtes.
     * The imbge is scbled into b squbre of size
     * specified by <code>destWidth</code> bnd
     * <code>destHeight</code>. The portion of the
     * source imbge copied into thbt squbre is specified
     * by <code>srcX</code>, <code>srcY</code>,
     * <code>srcWidth</code>, bnd srcHeight.
     * Note thbt the imbge isn't completely compbtible with DIB formbt.
     * At the very lebst it needs to be pbdded so ebch scbnline is
     * DWORD bligned. Also we "flip" the imbge to mbke it b bottom-up DIB.
     */
    privbte nbtive void drbwDIBImbge(long printDC, byte[] imbge,
                                     flobt destX, flobt destY,
                                     flobt destWidth, flobt destHeight,
                                     flobt srcX, flobt srcY,
                                     flobt srcWidth, flobt srcHeight,
                                     int bitCount, byte[] bmiColors);


    //** BEGIN Functions cblled by nbtive code for querying/updbting bttributes

    privbte finbl String getPrinterAttrib() {
        // getPrintService will get current print service or defbult if none
        PrintService service = this.getPrintService();
        String nbme = (service != null) ? service.getNbme() : null;
        return nbme;
    }

    /* SheetCollbte */
    privbte finbl int getCollbteAttrib() {
        // -1 mebns unset, 0 uncollbted, 1 collbted.
        return mAttCollbte;
    }

    privbte void setCollbteAttrib(Attribute bttr) {
        if (bttr == SheetCollbte.COLLATED) {
            mAttCollbte = 1; // DMCOLLATE_TRUE
        } else {
            mAttCollbte = 0; // DMCOLLATE_FALSE
        }
    }

    privbte void setCollbteAttrib(Attribute bttr,
                                  PrintRequestAttributeSet set) {
        setCollbteAttrib(bttr);
        set.bdd(bttr);
    }

    /* Orientbtion */

    privbte finbl int getOrientAttrib() {
        int orient = PbgeFormbt.PORTRAIT;
        OrientbtionRequested orientReq = (bttributes == null) ? null :
            (OrientbtionRequested)bttributes.get(OrientbtionRequested.clbss);
        if (orientReq == null) {
            orientReq = (OrientbtionRequested)
               myService.getDefbultAttributeVblue(OrientbtionRequested.clbss);
        }
        if (orientReq != null) {
            if (orientReq == OrientbtionRequested.REVERSE_LANDSCAPE) {
                orient = PbgeFormbt.REVERSE_LANDSCAPE;
            } else if (orientReq == OrientbtionRequested.LANDSCAPE) {
                orient = PbgeFormbt.LANDSCAPE;
            }
        }

        return orient;
    }

    privbte void setOrientAttrib(Attribute bttr,
                                 PrintRequestAttributeSet set) {
        if (set != null) {
            set.bdd(bttr);
        }
    }

    /* Copies bnd Pbge Rbnge. */
    privbte finbl int getCopiesAttrib() {
        if (defbultCopies) {
            return 0;
        } else {
            return getCopiesInt();
        }
     }

    privbte finbl void setRbngeCopiesAttribute(int from, int to,
                                               boolebn isRbngeSet,
                                               int copies) {
        if (bttributes != null) {
            if (isRbngeSet) {
                bttributes.bdd(new PbgeRbnges(from, to));
                setPbgeRbnge(from, to);
            }
            defbultCopies = fblse;
            bttributes.bdd(new Copies(copies));
            /* Since this is cblled from nbtive to tell Jbvb to sync
             * up with nbtive, we don't cbll this clbss's own setCopies()
             * method which is mbinly to send the vblue down to nbtive
             */
            super.setCopies(copies);
            mAttCopies = copies;
        }
    }



    privbte finbl boolebn getDestAttrib() {
        return (mDestinbtion != null);
    }

    /* Qublity */
    privbte finbl int getQublityAttrib() {
        return mAttQublity;
    }

    privbte void setQublityAttrib(Attribute bttr) {
        if (bttr == PrintQublity.HIGH) {
            mAttQublity = -4; // DMRES_HIGH
        } else if (bttr == PrintQublity.NORMAL) {
            mAttQublity = -3; // DMRES_MEDIUM
        } else {
            mAttQublity = -2; // DMRES_LOW
        }
    }

    privbte void setQublityAttrib(Attribute bttr,
                                  PrintRequestAttributeSet set) {
        setQublityAttrib(bttr);
        set.bdd(bttr);
    }

    /* Color/Chrombticity */
    privbte finbl int getColorAttrib() {
        return mAttChrombticity;
    }

    privbte void setColorAttrib(Attribute bttr) {
        if (bttr == Chrombticity.COLOR) {
            mAttChrombticity = 2; // DMCOLOR_COLOR
        } else {
            mAttChrombticity = 1; // DMCOLOR_MONOCHROME
        }
    }

    privbte void setColorAttrib(Attribute bttr,
                                  PrintRequestAttributeSet set) {
        setColorAttrib(bttr);
        set.bdd(bttr);
    }

    /* Sides */
    privbte finbl int getSidesAttrib() {
        return mAttSides;
    }

    privbte void setSidesAttrib(Attribute bttr) {
        if (bttr == Sides.TWO_SIDED_LONG_EDGE) {
            mAttSides = 2; // DMDUP_VERTICAL
        } else if (bttr == Sides.TWO_SIDED_SHORT_EDGE) {
            mAttSides = 3; // DMDUP_HORIZONTAL
        } else { // Sides.ONE_SIDED
            mAttSides = 1;
        }
    }

    privbte void setSidesAttrib(Attribute bttr,
                                PrintRequestAttributeSet set) {
        setSidesAttrib(bttr);
        set.bdd(bttr);
    }

    /** MedibSizeNbme / dmPbper */
    privbte finbl int[] getWin32MedibAttrib() {
        int wid_ht[] = {0, 0};
        if (bttributes != null) {
            Medib medib = (Medib)bttributes.get(Medib.clbss);
            if (medib instbnceof MedibSizeNbme) {
                MedibSizeNbme msn = (MedibSizeNbme)medib;
                MedibSize ms = MedibSize.getMedibSizeForNbme(msn);
                if (ms != null) {
                    wid_ht[0] = (int)(ms.getX(MedibSize.INCH) * 72.0);
                    wid_ht[1] = (int)(ms.getY(MedibSize.INCH) * 72.0);
                }
            }
        }
        return wid_ht;
    }

    privbte void setWin32MedibAttrib(Attribute bttr) {
        if (!(bttr instbnceof MedibSizeNbme)) {
            return;
        }
        MedibSizeNbme msn = (MedibSizeNbme)bttr;
        mAttMedibSizeNbme = ((Win32PrintService)myService).findPbperID(msn);
    }

    privbte void bddPbperSize(PrintRequestAttributeSet bset,
                              int dmIndex, int width, int length) {

        if (bset == null) {
            return;
        }
        MedibSizeNbme msn =
           ((Win32PrintService)myService).findWin32Medib(dmIndex);
        if (msn == null) {
            msn = ((Win32PrintService)myService).
                findMbtchingMedibSizeNbmeMM((flobt)width, (flobt)length);
        }

        if (msn != null) {
            bset.bdd(msn);
        }
    }

    privbte void setWin32MedibAttrib(int dmIndex, int width, int length) {
        bddPbperSize(bttributes, dmIndex, width, length);
        mAttMedibSizeNbme = dmIndex;
    }

    /* MedibTrby / dmTrby */
    privbte void setMedibTrbyAttrib(Attribute bttr) {
        if (bttr == MedibTrby.BOTTOM) {
            mAttMedibTrby = 2;    // DMBIN_LOWER
        } else if (bttr == MedibTrby.ENVELOPE) {
            mAttMedibTrby = 5;    // DMBIN_ENVELOPE
        } else if (bttr == MedibTrby.LARGE_CAPACITY) {
            mAttMedibTrby = 11;      // DMBIN_LARGECAPACITY
        } else if (bttr == MedibTrby.MAIN) {
            mAttMedibTrby =1;               // DMBIN_UPPER
        } else if (bttr == MedibTrby.MANUAL) {
            mAttMedibTrby = 4;              // DMBIN_MANUAL
        } else if (bttr == MedibTrby.MIDDLE) {
            mAttMedibTrby = 3;              // DMBIN_MIDDLE
        } else if (bttr == MedibTrby.SIDE) {
            // no equivblent predefined vblue
            mAttMedibTrby = 7;              // DMBIN_AUTO
        } else if (bttr == MedibTrby.TOP) {
            mAttMedibTrby = 1;              // DMBIN_UPPER
        } else {
            if (bttr instbnceof Win32MedibTrby) {
                mAttMedibTrby = ((Win32MedibTrby)bttr).winID;
            } else {
                mAttMedibTrby = 1;  // defbult
            }
        }
    }

    privbte void setMedibTrbyAttrib(int dmBinID) {
        mAttMedibTrby = dmBinID;
        MedibTrby trby = ((Win32PrintService)myService).findMedibTrby(dmBinID);
    }

    privbte int getMedibTrbyAttrib() {
        return mAttMedibTrby;
    }



    privbte finbl boolebn getPrintToFileEnbbled() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            FilePermission printToFilePermission =
                new FilePermission("<<ALL FILES>>", "rebd,write");
            try {
                security.checkPermission(printToFilePermission);
            } cbtch (SecurityException e) {
                return fblse;
            }
        }
        return true;
    }

    privbte finbl void setNbtiveAttributes(int flbgs, int fields, int vblues) {
        if (bttributes == null) {
            return;
        }
        if ((flbgs & PD_PRINTTOFILE) != 0) {
            Destinbtion destPrn = (Destinbtion)bttributes.get(
                                                 Destinbtion.clbss);
            if (destPrn == null) {
                try {
                    bttributes.bdd(new Destinbtion(
                                               new File("./out.prn").toURI()));
                } cbtch (SecurityException se) {
                    try {
                        bttributes.bdd(new Destinbtion(
                                                new URI("file:out.prn")));
                    } cbtch (URISyntbxException e) {
                    }
                }
            }
        } else {
            bttributes.remove(Destinbtion.clbss);
        }

        if ((flbgs & PD_COLLATE) != 0) {
            setCollbteAttrib(SheetCollbte.COLLATED, bttributes);
        } else {
            setCollbteAttrib(SheetCollbte.UNCOLLATED, bttributes);
        }

        if ((flbgs & PD_PAGENUMS) != 0) {
            bttributes.bdd(SunPbgeSelection.RANGE);
        } else if ((flbgs & PD_SELECTION) != 0) {
            bttributes.bdd(SunPbgeSelection.SELECTION);
        } else {
            bttributes.bdd(SunPbgeSelection.ALL);
        }

        if ((fields & DM_ORIENTATION) != 0) {
            if ((vblues & SET_ORIENTATION) != 0) {
                setOrientAttrib(OrientbtionRequested.LANDSCAPE, bttributes);
            } else {
                setOrientAttrib(OrientbtionRequested.PORTRAIT, bttributes);
            }
        }

        if ((fields & DM_COLOR) != 0) {
            if ((vblues & SET_COLOR) != 0) {
                setColorAttrib(Chrombticity.COLOR, bttributes);
            } else {
                setColorAttrib(Chrombticity.MONOCHROME, bttributes);
            }
        }

        if ((fields & DM_PRINTQUALITY) != 0) {
            PrintQublity qublity;
            if ((vblues & SET_RES_LOW) != 0) {
                qublity = PrintQublity.DRAFT;
            } else if ((fields & SET_RES_HIGH) != 0) {
                qublity = PrintQublity.HIGH;
            } else {
                qublity = PrintQublity.NORMAL;
            }
            setQublityAttrib(qublity, bttributes);
        }

        if ((fields & DM_DUPLEX) != 0) {
            Sides sides;
            if ((vblues & SET_DUP_VERTICAL) != 0) {
                sides = Sides.TWO_SIDED_LONG_EDGE;
            } else if ((vblues & SET_DUP_HORIZONTAL) != 0) {
                sides = Sides.TWO_SIDED_SHORT_EDGE;
            } else {
                sides = Sides.ONE_SIDED;
            }
            setSidesAttrib(sides, bttributes);
        }
    }

    privbte stbtic finbl clbss DevModeVblues {
        int dmFields;
        short copies;
        short collbte;
        short color;
        short duplex;
        short orient;
        short pbper;
        short bin;
        short xres_qublity;
        short yres;
    }

    privbte void getDevModeVblues(PrintRequestAttributeSet bset,
                                  DevModeVblues info) {

        Copies c = (Copies)bset.get(Copies.clbss);
        if (c != null) {
            info.dmFields |= DM_COPIES;
            info.copies = (short)c.getVblue();
        }

        SheetCollbte sc = (SheetCollbte)bset.get(SheetCollbte.clbss);
        if (sc != null) {
            info.dmFields |= DM_COLLATE;
            info.collbte = (sc == SheetCollbte.COLLATED) ?
                DMCOLLATE_TRUE : DMCOLLATE_FALSE;
        }

        Chrombticity ch = (Chrombticity)bset.get(Chrombticity.clbss);
        if (ch != null) {
            info.dmFields |= DM_COLOR;
            if (ch == Chrombticity.COLOR) {
                info.color = DMCOLOR_COLOR;
            } else {
                info.color = DMCOLOR_MONOCHROME;
            }
        }

        Sides s = (Sides)bset.get(Sides.clbss);
        if (s != null) {
            info.dmFields |= DM_DUPLEX;
            if (s == Sides.TWO_SIDED_LONG_EDGE) {
                info.duplex = DMDUP_VERTICAL;
            } else if (s == Sides.TWO_SIDED_SHORT_EDGE) {
                info.duplex = DMDUP_HORIZONTAL;
            } else { // Sides.ONE_SIDED
                info.duplex = DMDUP_SIMPLEX;
            }
        }

        OrientbtionRequested or =
            (OrientbtionRequested)bset.get(OrientbtionRequested.clbss);
        if (or != null) {
            info.dmFields |= DM_ORIENTATION;
            info.orient = (or == OrientbtionRequested.LANDSCAPE)
                ? DMORIENT_LANDSCAPE : DMORIENT_PORTRAIT;
        }

        Medib m = (Medib)bset.get(Medib.clbss);
        if (m instbnceof MedibSizeNbme) {
            info.dmFields |= DM_PAPERSIZE;
            MedibSizeNbme msn = (MedibSizeNbme)m;
            info.pbper =
                (short)((Win32PrintService)myService).findPbperID(msn);
        }

        MedibTrby mt = null;
        if (m instbnceof MedibTrby) {
            mt = (MedibTrby)m;
        }
        if (mt == null) {
            SunAlternbteMedib sbm =
                (SunAlternbteMedib)bset.get(SunAlternbteMedib.clbss);
            if (sbm != null && (sbm.getMedib() instbnceof MedibTrby)) {
                mt = (MedibTrby)sbm.getMedib();
            }
        }

        if (mt != null) {
            info.dmFields |= DM_DEFAULTSOURCE;
            info.bin = (short)(((Win32PrintService)myService).findTrbyID(mt));
        }

        PrintQublity q = (PrintQublity)bset.get(PrintQublity.clbss);
        if (q != null) {
            info.dmFields |= DM_PRINTQUALITY;
            if (q == PrintQublity.DRAFT) {
                info.xres_qublity = DMRES_DRAFT;
            } else if (q == PrintQublity.HIGH) {
                info.xres_qublity = DMRES_HIGH;
            } else {
                info.xres_qublity = DMRES_MEDIUM;
            }
        }

        PrinterResolution r =
            (PrinterResolution)bset.get(PrinterResolution.clbss);
        if (r != null) {
            info.dmFields |= DM_PRINTQUALITY | DM_YRESOLUTION;
            info.xres_qublity =
                (short)r.getCrossFeedResolution(PrinterResolution.DPI);
            info.yres = (short)r.getFeedResolution(PrinterResolution.DPI);
        }
    }

    /* This method is cblled from nbtive to updbte the vblues in the
     * bttribute set which originbtes from the cross-plbtform diblog,
     * but updbted by the nbtive DocumentPropertiesUI which updbtes the
     * devmode. This syncs the devmode bbck in to the bttributes so thbt
     * we cbn updbte the cross-plbtform diblog.
     * The bttribute set here is b temporbry one instblled whilst this
     * hbppens,
     */
    privbte finbl void setJobAttributes(PrintRequestAttributeSet bttributes,
                                        int fields, int vblues,
                                        short copies,
                                        short dmPbperSize,
                                        short dmPbperWidth,
                                        short dmPbperLength,
                                        short dmDefbultSource,
                                        short xRes,
                                        short yRes) {

        if (bttributes == null) {
            return;
        }

        if ((fields & DM_COPIES) != 0) {
            bttributes.bdd(new Copies(copies));
        }

        if ((fields & DM_COLLATE) != 0) {
            if ((vblues & SET_COLLATED) != 0) {
                bttributes.bdd(SheetCollbte.COLLATED);
            } else {
                bttributes.bdd(SheetCollbte.UNCOLLATED);
            }
        }

        if ((fields & DM_ORIENTATION) != 0) {
            if ((vblues & SET_ORIENTATION) != 0) {
                bttributes.bdd(OrientbtionRequested.LANDSCAPE);
            } else {
                bttributes.bdd(OrientbtionRequested.PORTRAIT);
            }
        }

        if ((fields & DM_COLOR) != 0) {
            if ((vblues & SET_COLOR) != 0) {
                bttributes.bdd(Chrombticity.COLOR);
            } else {
                bttributes.bdd(Chrombticity.MONOCHROME);
            }
        }

        if ((fields & DM_PRINTQUALITY) != 0) {
            /* vblue < 0 indicbtes qublity setting.
             * vblue > 0 indicbtes X resolution. In thbt cbse
             * hopefully we will blso find y-resolution specified.
             * If its not, bssume its the sbme bs x-res.
             * Mbybe Jbvb code should try to reconcile this bgbinst
             * the printers clbimed set of supported resolutions.
             */
            if (xRes < 0) {
                PrintQublity qublity;
                if ((vblues & SET_RES_LOW) != 0) {
                    qublity = PrintQublity.DRAFT;
                } else if ((fields & SET_RES_HIGH) != 0) {
                    qublity = PrintQublity.HIGH;
                } else {
                    qublity = PrintQublity.NORMAL;
                }
                bttributes.bdd(qublity);
            } else if (xRes > 0 && yRes > 0) {
                bttributes.bdd(
                    new PrinterResolution(xRes, yRes, PrinterResolution.DPI));
            }
        }

        if ((fields & DM_DUPLEX) != 0) {
            Sides sides;
            if ((vblues & SET_DUP_VERTICAL) != 0) {
                sides = Sides.TWO_SIDED_LONG_EDGE;
            } else if ((vblues & SET_DUP_HORIZONTAL) != 0) {
                sides = Sides.TWO_SIDED_SHORT_EDGE;
            } else {
                sides = Sides.ONE_SIDED;
            }
            bttributes.bdd(sides);
        }

        if ((fields & DM_PAPERSIZE) != 0) {
            bddPbperSize(bttributes, dmPbperSize, dmPbperWidth, dmPbperLength);
        }

        if ((fields & DM_DEFAULTSOURCE) != 0) {
            MedibTrby trby =
                ((Win32PrintService)myService).findMedibTrby(dmDefbultSource);
            bttributes.bdd(new SunAlternbteMedib(trby));
        }
    }

    privbte nbtive boolebn showDocProperties(long hWnd,
                                             PrintRequestAttributeSet bset,
                                             int dmFields,
                                             short copies,
                                             short collbte,
                                             short color,
                                             short duplex,
                                             short orient,
                                             short pbper,
                                             short bin,
                                             short xres_qublity,
                                             short yres);

    @SuppressWbrnings("deprecbtion")
    public PrintRequestAttributeSet
        showDocumentProperties(Window owner,
                               PrintService service,
                               PrintRequestAttributeSet bset)
    {
        try {
            setNbtivePrintServiceIfNeeded(service.getNbme());
        } cbtch (PrinterException e) {
        }
        long hWnd = ((WWindowPeer)(owner.getPeer())).getHWnd();
        DevModeVblues info = new DevModeVblues();
        getDevModeVblues(bset, info);
        boolebn ok =
            showDocProperties(hWnd, bset,
                              info.dmFields,
                              info.copies,
                              info.collbte,
                              info.color,
                              info.duplex,
                              info.orient,
                              info.pbper,
                              info.bin,
                              info.xres_qublity,
                              info.yres);

        if (ok) {
            return bset;
        } else {
            return null;
        }
    }

    /* Printer Resolution. See blso getXRes() bnd getYRes() */
    privbte finbl void setResolutionDPI(int xres, int yres) {
        if (bttributes != null) {
            PrinterResolution res =
                new PrinterResolution(xres, yres, PrinterResolution.DPI);
            bttributes.bdd(res);
        }
        mAttXRes = xres;
        mAttYRes = yres;
    }

    privbte void setResolutionAttrib(Attribute bttr) {
        PrinterResolution pr = (PrinterResolution)bttr;
        mAttXRes = pr.getCrossFeedResolution(PrinterResolution.DPI);
        mAttYRes = pr.getFeedResolution(PrinterResolution.DPI);
    }

    privbte void setPrinterNbmeAttrib(String printerNbme) {
        PrintService service = this.getPrintService();

        if (printerNbme == null) {
            return;
        }

        if (service != null && printerNbme.equbls(service.getNbme())) {
            return;
        } else {
            PrintService []services = PrinterJob.lookupPrintServices();
            for (int i=0; i<services.length; i++) {
                if (printerNbme.equbls(services[i].getNbme())) {

                    try {
                        this.setPrintService(services[i]);
                    } cbtch (PrinterException e) {
                    }
                    return;
                }
            }
        }
    //** END Functions cblled by nbtive code for querying/updbting bttributes

    }

@SuppressWbrnings("seribl") // JDK-implementbtion clbss
clbss PrintToFileErrorDiblog extends Diblog implements ActionListener{
    public PrintToFileErrorDiblog(Frbme pbrent, String title, String messbge,
                           String buttonText) {
        super(pbrent, title, true);
        init (pbrent, title, messbge, buttonText);
    }

    public PrintToFileErrorDiblog(Diblog pbrent, String title, String messbge,
                           String buttonText) {
        super(pbrent, title, true);
        init (pbrent, title, messbge, buttonText);
    }

    privbte void init(Component pbrent, String  title, String messbge,
                      String buttonText) {
        Pbnel p = new Pbnel();
        bdd("Center", new Lbbel(messbge));
        Button btn = new Button(buttonText);
        btn.bddActionListener(this);
        p.bdd(btn);
        bdd("South", p);
        pbck();

        Dimension dDim = getSize();
        if (pbrent != null) {
            Rectbngle fRect = pbrent.getBounds();
            setLocbtion(fRect.x + ((fRect.width - dDim.width) / 2),
                        fRect.y + ((fRect.height - dDim.height) / 2));
        }
    }

    @Override
    public void bctionPerformed(ActionEvent event) {
        setVisible(fblse);
        dispose();
        return;
    }
}




    /**
     * Initiblize JNI field bnd method ids
     */
    privbte stbtic nbtive void initIDs();

}
