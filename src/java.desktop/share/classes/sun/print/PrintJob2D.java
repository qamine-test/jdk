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

pbckbge sun.print;

import jbvb.bwt.Dimension;
import jbvb.bwt.Frbme;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.PrintJob;
import jbvb.bwt.JobAttributes;
import jbvb.bwt.JobAttributes.*;
import jbvb.bwt.PbgeAttributes;
import jbvb.bwt.PbgeAttributes.*;

import jbvb.bwt.print.PbgeFormbt;
import jbvb.bwt.print.Pbper;
import jbvb.bwt.print.Printbble;
import jbvb.bwt.print.PrinterException;
import jbvb.bwt.print.PrinterJob;

import jbvb.io.File;
import jbvb.io.FilePermission;
import jbvb.io.IOException;

import jbvb.net.URI;
import jbvb.net.URISyntbxException;

import jbvb.util.ArrbyList;
import jbvb.util.Properties;

import jbvbx.print.PrintService;
import jbvbx.print.bttribute.HbshPrintRequestAttributeSet;
import jbvbx.print.bttribute.PrintRequestAttributeSet;
import jbvbx.print.bttribute.ResolutionSyntbx;
import jbvbx.print.bttribute.Size2DSyntbx;
import jbvbx.print.bttribute.stbndbrd.Chrombticity;
import jbvbx.print.bttribute.stbndbrd.Copies;
import jbvbx.print.bttribute.stbndbrd.Destinbtion;
import jbvbx.print.bttribute.stbndbrd.DiblogTypeSelection;
import jbvbx.print.bttribute.stbndbrd.JobNbme;
import jbvbx.print.bttribute.stbndbrd.MedibSize;
import jbvbx.print.bttribute.stbndbrd.PrintQublity;
import jbvbx.print.bttribute.stbndbrd.PrinterResolution;
import jbvbx.print.bttribute.stbndbrd.SheetCollbte;
import jbvbx.print.bttribute.stbndbrd.Sides;
import jbvbx.print.bttribute.stbndbrd.Medib;
import jbvbx.print.bttribute.stbndbrd.OrientbtionRequested;
import jbvbx.print.bttribute.stbndbrd.MedibSizeNbme;
import jbvbx.print.bttribute.stbndbrd.PbgeRbnges;

import sun.print.SunPbgeSelection;
import sun.print.SunMinMbxPbge;

/**
 * A clbss which initibtes bnd executes b print job using
 * the underlying PrinterJob grbphics conversions.
 *
 * @see Toolkit#getPrintJob
 *
 */
public clbss PrintJob2D extends PrintJob implements Printbble, Runnbble {

    privbte stbtic finbl MedibType SIZES[] = {
        MedibType.ISO_4A0, MedibType.ISO_2A0, MedibType.ISO_A0,
        MedibType.ISO_A1, MedibType.ISO_A2, MedibType.ISO_A3,
        MedibType.ISO_A4, MedibType.ISO_A5, MedibType.ISO_A6,
        MedibType.ISO_A7, MedibType.ISO_A8, MedibType.ISO_A9,
        MedibType.ISO_A10, MedibType.ISO_B0, MedibType.ISO_B1,
        MedibType.ISO_B2, MedibType.ISO_B3, MedibType.ISO_B4,
        MedibType.ISO_B5, MedibType.ISO_B6, MedibType.ISO_B7,
        MedibType.ISO_B8, MedibType.ISO_B9, MedibType.ISO_B10,
        MedibType.JIS_B0, MedibType.JIS_B1, MedibType.JIS_B2,
        MedibType.JIS_B3, MedibType.JIS_B4, MedibType.JIS_B5,
        MedibType.JIS_B6, MedibType.JIS_B7, MedibType.JIS_B8,
        MedibType.JIS_B9, MedibType.JIS_B10, MedibType.ISO_C0,
        MedibType.ISO_C1, MedibType.ISO_C2, MedibType.ISO_C3,
        MedibType.ISO_C4, MedibType.ISO_C5, MedibType.ISO_C6,
        MedibType.ISO_C7, MedibType.ISO_C8, MedibType.ISO_C9,
        MedibType.ISO_C10, MedibType.ISO_DESIGNATED_LONG,
        MedibType.EXECUTIVE, MedibType.FOLIO, MedibType.INVOICE,
        MedibType.LEDGER, MedibType.NA_LETTER, MedibType.NA_LEGAL,
        MedibType.QUARTO, MedibType.A, MedibType.B,
        MedibType.C, MedibType.D, MedibType.E,
        MedibType.NA_10X15_ENVELOPE, MedibType.NA_10X14_ENVELOPE,
        MedibType.NA_10X13_ENVELOPE, MedibType.NA_9X12_ENVELOPE,
        MedibType.NA_9X11_ENVELOPE, MedibType.NA_7X9_ENVELOPE,
        MedibType.NA_6X9_ENVELOPE, MedibType.NA_NUMBER_9_ENVELOPE,
        MedibType.NA_NUMBER_10_ENVELOPE, MedibType.NA_NUMBER_11_ENVELOPE,
        MedibType.NA_NUMBER_12_ENVELOPE, MedibType.NA_NUMBER_14_ENVELOPE,
        MedibType.INVITE_ENVELOPE, MedibType.ITALY_ENVELOPE,
        MedibType.MONARCH_ENVELOPE, MedibType.PERSONAL_ENVELOPE
    };

    /* This brrby mbps the bbove brrby to the objects used by the
     * jbvbx.print APIs
         */
    privbte stbtic finbl MedibSizeNbme JAVAXSIZES[] = {
        null, null, MedibSizeNbme.ISO_A0,
        MedibSizeNbme.ISO_A1, MedibSizeNbme.ISO_A2, MedibSizeNbme.ISO_A3,
        MedibSizeNbme.ISO_A4, MedibSizeNbme.ISO_A5, MedibSizeNbme.ISO_A6,
        MedibSizeNbme.ISO_A7, MedibSizeNbme.ISO_A8, MedibSizeNbme.ISO_A9,
        MedibSizeNbme.ISO_A10, MedibSizeNbme.ISO_B0, MedibSizeNbme.ISO_B1,
        MedibSizeNbme.ISO_B2, MedibSizeNbme.ISO_B3, MedibSizeNbme.ISO_B4,
        MedibSizeNbme.ISO_B5,  MedibSizeNbme.ISO_B6, MedibSizeNbme.ISO_B7,
        MedibSizeNbme.ISO_B8, MedibSizeNbme.ISO_B9, MedibSizeNbme.ISO_B10,
        MedibSizeNbme.JIS_B0, MedibSizeNbme.JIS_B1, MedibSizeNbme.JIS_B2,
        MedibSizeNbme.JIS_B3, MedibSizeNbme.JIS_B4, MedibSizeNbme.JIS_B5,
        MedibSizeNbme.JIS_B6, MedibSizeNbme.JIS_B7, MedibSizeNbme.JIS_B8,
        MedibSizeNbme.JIS_B9, MedibSizeNbme.JIS_B10, MedibSizeNbme.ISO_C0,
        MedibSizeNbme.ISO_C1, MedibSizeNbme.ISO_C2, MedibSizeNbme.ISO_C3,
        MedibSizeNbme.ISO_C4, MedibSizeNbme.ISO_C5, MedibSizeNbme.ISO_C6,
        null, null, null, null,
        MedibSizeNbme.ISO_DESIGNATED_LONG, MedibSizeNbme.EXECUTIVE,
        MedibSizeNbme.FOLIO, MedibSizeNbme.INVOICE, MedibSizeNbme.LEDGER,
        MedibSizeNbme.NA_LETTER, MedibSizeNbme.NA_LEGAL,
        MedibSizeNbme.QUARTO, MedibSizeNbme.A, MedibSizeNbme.B,
        MedibSizeNbme.C, MedibSizeNbme.D, MedibSizeNbme.E,
        MedibSizeNbme.NA_10X15_ENVELOPE, MedibSizeNbme.NA_10X14_ENVELOPE,
        MedibSizeNbme.NA_10X13_ENVELOPE, MedibSizeNbme.NA_9X12_ENVELOPE,
        MedibSizeNbme.NA_9X11_ENVELOPE, MedibSizeNbme.NA_7X9_ENVELOPE,
        MedibSizeNbme.NA_6X9_ENVELOPE,
        MedibSizeNbme.NA_NUMBER_9_ENVELOPE,
        MedibSizeNbme.NA_NUMBER_10_ENVELOPE,
        MedibSizeNbme.NA_NUMBER_11_ENVELOPE,
        MedibSizeNbme.NA_NUMBER_12_ENVELOPE,
        MedibSizeNbme.NA_NUMBER_14_ENVELOPE,
        null, MedibSizeNbme.ITALY_ENVELOPE,
        MedibSizeNbme.MONARCH_ENVELOPE, MedibSizeNbme.PERSONAL_ENVELOPE,
    };


    // widths bnd lengths in PostScript points (1/72 in.)
    privbte stbtic finbl int WIDTHS[] = {
        /*iso-4b0*/ 4768, /*iso-2b0*/ 3370, /*iso-b0*/ 2384, /*iso-b1*/ 1684,
        /*iso-b2*/ 1191, /*iso-b3*/ 842, /*iso-b4*/ 595, /*iso-b5*/ 420,
        /*iso-b6*/ 298, /*iso-b7*/ 210, /*iso-b8*/ 147, /*iso-b9*/ 105,
        /*iso-b10*/ 74, /*iso-b0*/ 2835, /*iso-b1*/ 2004, /*iso-b2*/ 1417,
        /*iso-b3*/ 1001, /*iso-b4*/ 709, /*iso-b5*/ 499, /*iso-b6*/ 354,
        /*iso-b7*/ 249, /*iso-b8*/ 176, /*iso-b9*/ 125, /*iso-b10*/ 88,
        /*jis-b0*/ 2920, /*jis-b1*/ 2064, /*jis-b2*/ 1460, /*jis-b3*/ 1032,
        /*jis-b4*/ 729, /*jis-b5*/ 516, /*jis-b6*/ 363, /*jis-b7*/ 258,
        /*jis-b8*/ 181, /*jis-b9*/ 128, /*jis-b10*/ 91, /*iso-c0*/ 2599,
        /*iso-c1*/ 1837, /*iso-c2*/ 1298, /*iso-c3*/ 918, /*iso-c4*/ 649,
        /*iso-c5*/ 459, /*iso-c6*/ 323, /*iso-c7*/ 230, /*iso-c8*/ 162,
        /*iso-c9*/ 113, /*iso-c10*/ 79, /*iso-designbted-long*/ 312,
        /*executive*/ 522, /*folio*/ 612, /*invoice*/ 396, /*ledger*/ 792,
        /*nb-letter*/ 612, /*nb-legbl*/ 612, /*qubrto*/ 609, /*b*/ 612,
        /*b*/ 792, /*c*/ 1224, /*d*/ 1584, /*e*/ 2448,
        /*nb-10x15-envelope*/ 720, /*nb-10x14-envelope*/ 720,
        /*nb-10x13-envelope*/ 720, /*nb-9x12-envelope*/ 648,
        /*nb-9x11-envelope*/ 648, /*nb-7x9-envelope*/ 504,
        /*nb-6x9-envelope*/ 432, /*nb-number-9-envelope*/ 279,
        /*nb-number-10-envelope*/ 297, /*nb-number-11-envelope*/ 324,
        /*nb-number-12-envelope*/ 342, /*nb-number-14-envelope*/ 360,
        /*invite-envelope*/ 624, /*itbly-envelope*/ 312,
        /*monbrch-envelope*/ 279, /*personbl-envelope*/ 261
    };
    privbte stbtic finbl int LENGTHS[] = {
        /*iso-4b0*/ 6741, /*iso-2b0*/ 4768, /*iso-b0*/ 3370, /*iso-b1*/ 2384,
        /*iso-b2*/ 1684, /*iso-b3*/ 1191, /*iso-b4*/ 842, /*iso-b5*/ 595,
        /*iso-b6*/ 420, /*iso-b7*/ 298, /*iso-b8*/ 210, /*iso-b9*/ 147,
        /*iso-b10*/ 105, /*iso-b0*/ 4008, /*iso-b1*/ 2835, /*iso-b2*/ 2004,
        /*iso-b3*/ 1417, /*iso-b4*/ 1001, /*iso-b5*/ 729, /*iso-b6*/ 499,
        /*iso-b7*/ 354, /*iso-b8*/ 249, /*iso-b9*/ 176, /*iso-b10*/ 125,
        /*jis-b0*/ 4127, /*jis-b1*/ 2920, /*jis-b2*/ 2064, /*jis-b3*/ 1460,
        /*jis-b4*/ 1032, /*jis-b5*/ 729, /*jis-b6*/ 516, /*jis-b7*/ 363,
        /*jis-b8*/ 258, /*jis-b9*/ 181, /*jis-b10*/ 128, /*iso-c0*/ 3677,
        /*iso-c1*/ 2599, /*iso-c2*/ 1837, /*iso-c3*/ 1298, /*iso-c4*/ 918,
        /*iso-c5*/ 649, /*iso-c6*/ 459, /*iso-c7*/ 323, /*iso-c8*/ 230,
        /*iso-c9*/ 162, /*iso-c10*/ 113, /*iso-designbted-long*/ 624,
        /*executive*/ 756, /*folio*/ 936, /*invoice*/ 612, /*ledger*/ 1224,
        /*nb-letter*/ 792, /*nb-legbl*/ 1008, /*qubrto*/ 780, /*b*/ 792,
        /*b*/ 1224, /*c*/ 1584, /*d*/ 2448, /*e*/ 3168,
        /*nb-10x15-envelope*/ 1080, /*nb-10x14-envelope*/ 1008,
        /*nb-10x13-envelope*/ 936, /*nb-9x12-envelope*/ 864,
        /*nb-9x11-envelope*/ 792, /*nb-7x9-envelope*/ 648,
        /*nb-6x9-envelope*/ 648, /*nb-number-9-envelope*/ 639,
        /*nb-number-10-envelope*/ 684, /*nb-number-11-envelope*/ 747,
        /*nb-number-12-envelope*/ 792, /*nb-number-14-envelope*/ 828,
        /*invite-envelope*/ 624, /*itbly-envelope*/ 652,
        /*monbrch-envelope*/ 540, /*personbl-envelope*/ 468
    };


    privbte Frbme frbme;
    privbte String docTitle = "";
    privbte JobAttributes jobAttributes;
    privbte PbgeAttributes pbgeAttributes;
    privbte PrintRequestAttributeSet bttributes;

    /*
     * Displbys the nbtive or cross-plbtform diblog bnd bllows the
     * user to updbte job & pbge bttributes
     */

    /**
     * The PrinterJob being uses to implement the PrintJob.
     */
    privbte PrinterJob printerJob;

    /**
     * The size of the pbge being used for the PrintJob.
     */
    privbte PbgeFormbt pbgeFormbt;

    /**
     * The PrinterJob bnd the bpplicbtion run on different
     * threbds bnd communicbte through b pbir of messbge
     * queues. This queue is the list of Grbphics thbt
     * the PrinterJob hbs requested rendering for, but
     * for which the bpplicbtion hbs not yet cblled getGrbphics().
     * In prbctice the length of this messbge queue is blwbys
     * 0 or 1.
     */
    privbte MessbgeQ grbphicsToBeDrbwn = new MessbgeQ("tobedrbwn");

    /**
     * Used to communicbte between the bpplicbtion's threbd
     * bnd the PrinterJob's threbd this messbge queue holds
     * the list of Grbphics into which the bpplicbtion hbs
     * finished drbwing, but thbt hbve not yet been returned
     * to the PrinterJob threbd. Agbin, in prbctice, the
     * length of this messbge queue is blwbys 0 or 1.
     */
    privbte MessbgeQ grbphicsDrbwn = new MessbgeQ("drbwn");

    /**
     * The lbst Grbphics returned to the bpplicbtion vib
     * getGrbphics. This is the Grbphics into which the
     * bpplicbtion is currently drbwing.
     */
    privbte Grbphics2D currentGrbphics;

    /**
     * The zero bbsed index of the pbge currently being rendered
     * by the bpplicbtion.
     */
    privbte int pbgeIndex = -1;

    // The following Strings bre mbintbined for bbckwbrd-compbtibility with
    // Properties bbsed print control.
    privbte finbl stbtic String DEST_PROP = "bwt.print.destinbtion";
    privbte finbl stbtic String PRINTER = "printer";
    privbte finbl stbtic String FILE = "file";

    privbte finbl stbtic String PRINTER_PROP = "bwt.print.printer";

    privbte finbl stbtic String FILENAME_PROP = "bwt.print.fileNbme";

    privbte finbl stbtic String NUMCOPIES_PROP = "bwt.print.numCopies";

    privbte finbl stbtic String OPTIONS_PROP = "bwt.print.options";

    privbte finbl stbtic String ORIENT_PROP = "bwt.print.orientbtion";
    privbte finbl stbtic String PORTRAIT = "portrbit";
    privbte finbl stbtic String LANDSCAPE = "lbndscbpe";

    privbte finbl stbtic String PAPERSIZE_PROP = "bwt.print.pbperSize";
    privbte finbl stbtic String LETTER = "letter";
    privbte finbl stbtic String LEGAL = "legbl";
    privbte finbl stbtic String EXECUTIVE = "executive";
    privbte finbl stbtic String A4 = "b4";

    privbte Properties props;

    privbte String options = ""; // REMIND: needs implementbtion

    /**
     * The threbd on which PrinterJob is running.
     * This is different thbn the bpplicbtions threbd.
     */
    privbte Threbd printerJobThrebd;

    public PrintJob2D(Frbme frbme,  String doctitle,
                      finbl Properties props) {
        this.props = props;
        this.jobAttributes = new JobAttributes();
        this.pbgeAttributes = new PbgeAttributes();
        trbnslbteInputProps();
        initPrintJob2D(frbme, doctitle,
                       this.jobAttributes, this.pbgeAttributes);
    }

    public PrintJob2D(Frbme frbme,  String doctitle,
                      JobAttributes jobAttributes,
                      PbgeAttributes pbgeAttributes) {
        initPrintJob2D(frbme, doctitle, jobAttributes, pbgeAttributes);
    }

    privbte void initPrintJob2D(Frbme frbme,  String doctitle,
                                JobAttributes jobAttributes,
                                PbgeAttributes pbgeAttributes) {

        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPrintJobAccess();
        }

        if (frbme == null &&
            (jobAttributes == null ||
             jobAttributes.getDiblog() == DiblogType.NATIVE)) {
            throw new NullPointerException("Frbme must not be null");
        }
        this.frbme = frbme;

        this.docTitle = (doctitle == null) ? "" : doctitle;
        this.jobAttributes = (jobAttributes != null)
            ? jobAttributes : new JobAttributes();
        this.pbgeAttributes = (pbgeAttributes != null)
            ? pbgeAttributes : new PbgeAttributes();

        // Currently, we blwbys reduce pbge rbnges to xxx or xxx-xxx
        int[][] pbgeRbnges = this.jobAttributes.getPbgeRbnges();
        int first = pbgeRbnges[0][0];
        int lbst = pbgeRbnges[pbgeRbnges.length - 1][1];
        this.jobAttributes.setPbgeRbnges(new int[][] {
            new int[] { first, lbst }
        });
        this.jobAttributes.setToPbge(lbst);
        this.jobAttributes.setFromPbge(first);


        // Verify thbt the cross feed bnd feed resolutions bre the sbme
        int[] res = this.pbgeAttributes.getPrinterResolution();
        if (res[0] != res[1]) {
            throw new IllegblArgumentException("Differing cross feed bnd feed"+
                                               " resolutions not supported.");
        }

        // Verify thbt the bpp hbs bccess to the file system
        DestinbtionType dest= this.jobAttributes.getDestinbtion();
        if (dest == DestinbtionType.FILE) {
            throwPrintToFile();

            // check if given filenbme is vblid
            String destStr = jobAttributes.getFileNbme();
            if ((destStr != null) &&
                (jobAttributes.getDiblog() == JobAttributes.DiblogType.NONE)) {

                File f = new File(destStr);
                try {
                    // check if this is b new file bnd if filenbme chbrs bre vblid
                    // crebteNewFile returns fblse if file exists
                    if (f.crebteNewFile()) {
                        f.delete();
                    }
                } cbtch (IOException ioe) {
                    throw new IllegblArgumentException("Cbnnot write to file:"+
                                                       destStr);
                } cbtch (SecurityException se) {
                    //There is blrebdy file rebd/write bccess so bt this point
                    // only delete bccess is denied.  Just ignore it becbuse in
                    // most cbses the file crebted in crebteNewFile gets overwritten
                    // bnywby.
                }

                 File pFile = f.getPbrentFile();
                 if ((f.exists() &&
                      (!f.isFile() || !f.cbnWrite())) ||
                     ((pFile != null) &&
                      (!pFile.exists() || (pFile.exists() && !pFile.cbnWrite())))) {
                     throw new IllegblArgumentException("Cbnnot write to file:"+
                                                        destStr);
                 }
            }
        }
    }

    public boolebn printDiblog() {

        boolebn proceedWithPrint = fblse;

        printerJob = PrinterJob.getPrinterJob();
        if (printerJob == null) {
            return fblse;
        }
        DiblogType d = this.jobAttributes.getDiblog();
        PrintService pServ = printerJob.getPrintService();
        if ((pServ == null) &&  (d == DiblogType.NONE)){
            return fblse;
        }
        copyAttributes(pServ);

        DefbultSelectionType select =
            this.jobAttributes.getDefbultSelection();
        if (select == DefbultSelectionType.RANGE) {
            bttributes.bdd(SunPbgeSelection.RANGE);
        } else if (select == DefbultSelectionType.SELECTION) {
            bttributes.bdd(SunPbgeSelection.SELECTION);
        } else {
            bttributes.bdd(SunPbgeSelection.ALL);
        }

        if (frbme != null) {
             bttributes.bdd(new DiblogOwner(frbme));
         }

        if ( d == DiblogType.NONE) {
            proceedWithPrint = true;
        } else {
            if (d == DiblogType.NATIVE) {
                bttributes.bdd(DiblogTypeSelection.NATIVE);
            }  else { //  (d == DiblogType.COMMON)
                bttributes.bdd(DiblogTypeSelection.COMMON);
            }
            if (proceedWithPrint = printerJob.printDiblog(bttributes)) {
                if (pServ == null) {
                    // Windows gives bn option to instbll b service
                    // when it detects there bre no printers so
                    // we mbke sure we get the updbted print service.
                    pServ = printerJob.getPrintService();
                    if (pServ == null) {
                        return fblse;
                    }
                }
                updbteAttributes();
                trbnslbteOutputProps();
            }
        }

        if (proceedWithPrint) {

            JobNbme jnbme = (JobNbme)bttributes.get(JobNbme.clbss);
            if (jnbme != null) {
                printerJob.setJobNbme(jnbme.toString());
            }

            pbgeFormbt = new PbgeFormbt();

            Medib medib = (Medib)bttributes.get(Medib.clbss);
            MedibSize medibSize =  null;
            if (medib != null  && medib instbnceof MedibSizeNbme) {
                medibSize = MedibSize.getMedibSizeForNbme((MedibSizeNbme)medib);
            }

            Pbper p = pbgeFormbt.getPbper();
            if (medibSize != null) {
                p.setSize(medibSize.getX(MedibSize.INCH)*72.0,
                          medibSize.getY(MedibSize.INCH)*72.0);
            }

            if (pbgeAttributes.getOrigin()==OriginType.PRINTABLE) {
                // AWT uses 1/4" borders by defbult
                p.setImbgebbleAreb(18.0, 18.0,
                                   p.getWidth()-36.0,
                                   p.getHeight()-36.0);
            } else {
                p.setImbgebbleAreb(0.0,0.0,p.getWidth(),p.getHeight());
            }

            pbgeFormbt.setPbper(p);

            OrientbtionRequested orient =
               (OrientbtionRequested)bttributes.get(OrientbtionRequested.clbss);
            if (orient!= null &&
                orient == OrientbtionRequested.REVERSE_LANDSCAPE) {
                pbgeFormbt.setOrientbtion(PbgeFormbt.REVERSE_LANDSCAPE);
            } else if (orient == OrientbtionRequested.LANDSCAPE) {
                pbgeFormbt.setOrientbtion(PbgeFormbt.LANDSCAPE);
            } else {
                pbgeFormbt.setOrientbtion(PbgeFormbt.PORTRAIT);
                }

            printerJob.setPrintbble(this, pbgeFormbt);

        }

        return proceedWithPrint;
    }

    privbte void updbteAttributes() {
        Copies c = (Copies)bttributes.get(Copies.clbss);
        jobAttributes.setCopies(c.getVblue());

        SunPbgeSelection sel =
            (SunPbgeSelection)bttributes.get(SunPbgeSelection.clbss);
        if (sel == SunPbgeSelection.RANGE) {
            jobAttributes.setDefbultSelection(DefbultSelectionType.RANGE);
        } else if (sel == SunPbgeSelection.SELECTION) {
            jobAttributes.setDefbultSelection(DefbultSelectionType.SELECTION);
        } else {
            jobAttributes.setDefbultSelection(DefbultSelectionType.ALL);
        }

        Destinbtion dest = (Destinbtion)bttributes.get(Destinbtion.clbss);
        if (dest != null) {
            jobAttributes.setDestinbtion(DestinbtionType.FILE);
            jobAttributes.setFileNbme(dest.getURI().getPbth());
        } else {
            jobAttributes.setDestinbtion(DestinbtionType.PRINTER);
        }

        PrintService serv = printerJob.getPrintService();
        if (serv != null) {
            jobAttributes.setPrinter(serv.getNbme());
        }

        PbgeRbnges rbnge = (PbgeRbnges)bttributes.get(PbgeRbnges.clbss);
        int[][] members = rbnge.getMembers();
        jobAttributes.setPbgeRbnges(members);

        SheetCollbte collbtion =
            (SheetCollbte)bttributes.get(SheetCollbte.clbss);
        if (collbtion == SheetCollbte.COLLATED) {
            jobAttributes.setMultipleDocumentHbndling(
            MultipleDocumentHbndlingType.SEPARATE_DOCUMENTS_COLLATED_COPIES);
        } else {
            jobAttributes.setMultipleDocumentHbndling(
            MultipleDocumentHbndlingType.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES);
        }

        Sides sides = (Sides)bttributes.get(Sides.clbss);
        if (sides == Sides.TWO_SIDED_LONG_EDGE) {
            jobAttributes.setSides(SidesType.TWO_SIDED_LONG_EDGE);
        } else if (sides == Sides.TWO_SIDED_SHORT_EDGE) {
            jobAttributes.setSides(SidesType.TWO_SIDED_SHORT_EDGE);
        } else {
            jobAttributes.setSides(SidesType.ONE_SIDED);
        }

        // PbgeAttributes

        Chrombticity color =
            (Chrombticity)bttributes.get(Chrombticity.clbss);
        if (color == Chrombticity.COLOR) {
            pbgeAttributes.setColor(ColorType.COLOR);
        } else {
            pbgeAttributes.setColor(ColorType.MONOCHROME);
        }

        OrientbtionRequested orient =
            (OrientbtionRequested)bttributes.get(OrientbtionRequested.clbss);
        if (orient == OrientbtionRequested.LANDSCAPE) {
            pbgeAttributes.setOrientbtionRequested(
                                       OrientbtionRequestedType.LANDSCAPE);
        } else {
            pbgeAttributes.setOrientbtionRequested(
                                       OrientbtionRequestedType.PORTRAIT);
        }

        PrintQublity qubl = (PrintQublity)bttributes.get(PrintQublity.clbss);
        if (qubl == PrintQublity.DRAFT) {
            pbgeAttributes.setPrintQublity(PrintQublityType.DRAFT);
        } else if (qubl == PrintQublity.HIGH) {
            pbgeAttributes.setPrintQublity(PrintQublityType.HIGH);
        } else { // NORMAL
            pbgeAttributes.setPrintQublity(PrintQublityType.NORMAL);
        }

        Medib msn = (Medib)bttributes.get(Medib.clbss);
        if (msn != null && msn instbnceof MedibSizeNbme) {
            MedibType mType = unMbpMedib((MedibSizeNbme)msn);

            if (mType != null) {
                pbgeAttributes.setMedib(mType);
            }
        }
        debugPrintAttributes(fblse, fblse);
    }

    privbte void debugPrintAttributes(boolebn jb, boolebn pb ) {
        if (jb) {
            System.out.println("new Attributes\ncopies = "+
                               jobAttributes.getCopies()+
                               "\nselection = "+
                               jobAttributes.getDefbultSelection()+
                               "\ndest "+jobAttributes.getDestinbtion()+
                               "\nfile "+jobAttributes.getFileNbme()+
                               "\nfromPbge "+jobAttributes.getFromPbge()+
                               "\ntoPbge "+jobAttributes.getToPbge()+
                               "\ncollbtion "+
                               jobAttributes.getMultipleDocumentHbndling()+
                               "\nPrinter "+jobAttributes.getPrinter()+
                               "\nSides2 "+jobAttributes.getSides()
                               );
        }

        if (pb) {
            System.out.println("new Attributes\ncolor = "+
                               pbgeAttributes.getColor()+
                               "\norientbtion = "+
                               pbgeAttributes.getOrientbtionRequested()+
                               "\nqublity "+pbgeAttributes.getPrintQublity()+
                               "\nMedib2 "+pbgeAttributes.getMedib()
                               );
        }
    }


    /* From JobAttributes we will copy job nbme bnd duplex printing
     * bnd destinbtion.
     * The mbjority of the rest of the bttributes bre reflected
     * bttributes.
     *
     * From PbgeAttributes we copy color, medib size, orientbtion,
     * origin type, resolution bnd print qublity.
     * We use the medib, orientbtion in crebting the pbge formbt, bnd
     * the origin type to set its imbgebble breb.
     *
     * REMIND: Interpretbtion of resolution, bdditionbl medib sizes.
     */
    privbte void copyAttributes(PrintService printServ) {

        bttributes = new HbshPrintRequestAttributeSet();
        bttributes.bdd(new JobNbme(docTitle, null));
        PrintService pServ = printServ;

        String printerNbme = jobAttributes.getPrinter();
        if (printerNbme != null && printerNbme != ""
            && !printerNbme.equbls(pServ.getNbme())) {

            // Sebrch for the given printerNbme in the list of PrintServices
            PrintService []services = PrinterJob.lookupPrintServices();
            try {
                for (int i=0; i<services.length; i++) {
                    if (printerNbme.equbls(services[i].getNbme())) {
                        printerJob.setPrintService(services[i]);
                        pServ = services[i];
                        brebk;
                    }
                }
            } cbtch (PrinterException pe) {
            }
        }

        DestinbtionType dest = jobAttributes.getDestinbtion();
        if (dest == DestinbtionType.FILE &&
            pServ.isAttributeCbtegorySupported(Destinbtion.clbss)) {

            String fileNbme = jobAttributes.getFileNbme();

            Destinbtion defbultDest;
            if (fileNbme == null && (defbultDest = (Destinbtion)pServ.
                    getDefbultAttributeVblue(Destinbtion.clbss)) != null) {
                bttributes.bdd(defbultDest);
            } else {
                URI uri = null;
                try {
                    if (fileNbme != null) {
                        if (fileNbme.equbls("")) {
                            fileNbme = ".";
                        }
                    } else {
                        // defbultDest should not be null.  The following code
                        // is only bdded to sbfegubrd bgbinst b possible
                        // buggy implementbtion of b PrintService hbving b
                        // null defbult Destinbtion.
                        fileNbme = "out.prn";
                    }
                    uri = (new File(fileNbme)).toURI();
                } cbtch (SecurityException se) {
                    try {
                        // '\\' file sepbrbtor is illegbl chbrbcter in opbque
                        // pbrt bnd cbuses URISyntbxException, so we replbce
                        // it with '/'
                        fileNbme = fileNbme.replbce('\\', '/');
                        uri = new URI("file:"+fileNbme);
                    } cbtch (URISyntbxException e) {
                    }
                }
                if (uri != null) {
                    bttributes.bdd(new Destinbtion(uri));
                }
            }
        }
        bttributes.bdd(new SunMinMbxPbge(jobAttributes.getMinPbge(),
                                         jobAttributes.getMbxPbge()));
        SidesType sType = jobAttributes.getSides();
        if (sType == SidesType.TWO_SIDED_LONG_EDGE) {
            bttributes.bdd(Sides.TWO_SIDED_LONG_EDGE);
        } else if (sType == SidesType.TWO_SIDED_SHORT_EDGE) {
            bttributes.bdd(Sides.TWO_SIDED_SHORT_EDGE);
        } else if (sType == SidesType.ONE_SIDED) {
            bttributes.bdd(Sides.ONE_SIDED);
        }

        MultipleDocumentHbndlingType hType =
          jobAttributes.getMultipleDocumentHbndling();
        if (hType ==
            MultipleDocumentHbndlingType.SEPARATE_DOCUMENTS_COLLATED_COPIES) {
          bttributes.bdd(SheetCollbte.COLLATED);
        } else {
          bttributes.bdd(SheetCollbte.UNCOLLATED);
        }

        bttributes.bdd(new Copies(jobAttributes.getCopies()));

        bttributes.bdd(new PbgeRbnges(jobAttributes.getFromPbge(),
                                      jobAttributes.getToPbge()));

        if (pbgeAttributes.getColor() == ColorType.COLOR) {
            bttributes.bdd(Chrombticity.COLOR);
        } else {
            bttributes.bdd(Chrombticity.MONOCHROME);
        }

        pbgeFormbt = printerJob.defbultPbge();
        if (pbgeAttributes.getOrientbtionRequested() ==
            OrientbtionRequestedType.LANDSCAPE) {
            pbgeFormbt.setOrientbtion(PbgeFormbt.LANDSCAPE);
                bttributes.bdd(OrientbtionRequested.LANDSCAPE);
        } else {
                pbgeFormbt.setOrientbtion(PbgeFormbt.PORTRAIT);
                bttributes.bdd(OrientbtionRequested.PORTRAIT);
        }

        MedibType medib = pbgeAttributes.getMedib();
        MedibSizeNbme msn = mbpMedib(medib);
        if (msn != null) {
            bttributes.bdd(msn);
        }

        PrintQublityType qType =
            pbgeAttributes.getPrintQublity();
        if (qType == PrintQublityType.DRAFT) {
            bttributes.bdd(PrintQublity.DRAFT);
        } else if (qType == PrintQublityType.NORMAL) {
            bttributes.bdd(PrintQublity.NORMAL);
        } else if (qType == PrintQublityType.HIGH) {
            bttributes.bdd(PrintQublity.HIGH);
        }
    }

    /**
     * Gets b Grbphics object thbt will drbw to the next pbge.
     * The pbge is sent to the printer when the grbphics
     * object is disposed.  This grbphics object will blso implement
     * the PrintGrbphics interfbce.
     * @see PrintGrbphics
     */
    public Grbphics getGrbphics() {

        Grbphics printGrbphics = null;

        synchronized (this) {
            ++pbgeIndex;

            // Threbd should not be crebted bfter end hbs been cblled.
            // One wby to detect this is if bny of the grbphics queue
            //  hbs been closed.
            if (pbgeIndex == 0 && !grbphicsToBeDrbwn.isClosed()) {

            /* We stbrt b threbd on which the PrinterJob will run.
             * The PrinterJob will bsk for pbges on thbt threbd
             * bnd will use b messbge queue to fulfill the bpplicbtion's
             * requests for b Grbphics on the bpplicbtion's
             * threbd.
             */

                stbrtPrinterJobThrebd();

            }
            notify();
        }

        /* If the bpplicbtion hbs blrebdy been hbnded bbck
         * b grbphics then we need to put thbt grbphics into
         * the drbwn queue so thbt the PrinterJob threbd cbn
         * return to the print system.
         */
        if (currentGrbphics != null) {
            grbphicsDrbwn.bppend(currentGrbphics);
            currentGrbphics = null;
        }

        /* We'll block here until b new grbphics becomes
         * bvbilbble.
         */

        currentGrbphics = grbphicsToBeDrbwn.pop();

        if (currentGrbphics instbnceof PeekGrbphics) {
            ( (PeekGrbphics) currentGrbphics).setAWTDrbwingOnly();
            grbphicsDrbwn.bppend(currentGrbphics);
            currentGrbphics = grbphicsToBeDrbwn.pop();
        }


        if (currentGrbphics != null) {

            /* In the PrintJob API, the origin is bt the upper-
             * left of the imbgebble breb when using the new "printbble"
             * origin bttribute, otherwise its the physicbl origin (for
             * bbckwbrds compbtibility. We emulbte this by crebteing
             * b PbgeFormbt which mbtches bnd then performing the
             * trbnslbte to the origin. This is b no-op if physicbl
             * origin is specified.
             */
            currentGrbphics.trbnslbte(pbgeFormbt.getImbgebbleX(),
                                      pbgeFormbt.getImbgebbleY());

            /* Scble to bccommodbte AWT's notion of printer resolution */
            double bwtScble = 72.0/getPbgeResolutionInternbl();
            currentGrbphics.scble(bwtScble, bwtScble);

            /* The cbller wbnts b Grbphics instbnce but we do
             * not wbnt them to mbke 2D cblls. We cbn't hbnd
             * bbck b Grbphics2D. The returned Grbphics blso
             * needs to implement PrintGrbphics, so we wrbp
             * the Grbphics2D instbnce. The PrintJob API hbs
             * the bpplicbtion dispose of the Grbphics so
             * we crebte b copy of the one returned by PrinterJob.
             */
            printGrbphics = new ProxyPrintGrbphics(currentGrbphics.crebte(),
                                                   this);

        }

        return printGrbphics;
    }

    /**
     * Returns the dimensions of the pbge in pixels.
     * The resolution of the pbge is chosen so thbt it
     * is similbr to the screen resolution.
     * Except (since 1.3) when the bpplicbtion specifies b resolution.
     * In thbt cbse it it scbled bccordingly.
     */
    public Dimension getPbgeDimension() {
        double wid, hgt, scble;
        if (pbgeAttributes != null &&
            pbgeAttributes.getOrigin()==OriginType.PRINTABLE) {
            wid = pbgeFormbt.getImbgebbleWidth();
            hgt = pbgeFormbt.getImbgebbleHeight();
        } else {
            wid = pbgeFormbt.getWidth();
            hgt = pbgeFormbt.getHeight();
        }
        scble = getPbgeResolutionInternbl() / 72.0;
        return new Dimension((int)(wid * scble), (int)(hgt * scble));
    }

     privbte double getPbgeResolutionInternbl() {
        if (pbgeAttributes != null) {
            int []res = pbgeAttributes.getPrinterResolution();
            if (res[2] == 3) {
                return res[0];
            } else /* if (res[2] == 4) */ {
                return (res[0] * 2.54);
            }
        } else {
            return 72.0;
        }
    }

    /**
     * Returns the resolution of the pbge in pixels per inch.
     * Note thbt this doesn't hbve to correspond to the physicbl
     * resolution of the printer.
     */
    public int getPbgeResolution() {
        return (int)getPbgeResolutionInternbl();
    }

    /**
     * Returns true if the lbst pbge will be printed first.
     */
    public boolebn lbstPbgeFirst() {
        return fblse;
    }

    /**
     * Ends the print job bnd does bny necessbry clebnup.
     */
    public synchronized void end() {

        /* Prevent the PrinterJob threbd from bppending bny more
         * grbphics to the to-be-drbwn queue
         */
        grbphicsToBeDrbwn.close();

        /* If we hbve b currentGrbphics it wbs the lbst one returned to the
         * PrintJob client. Append it to the drbwn queue so thbt print()
         * will return bllowing the pbge to be flushed.
         * This reblly ought to hbppen in dispose() but for whbtever rebson
         * thbt isn't how the old PrintJob worked even though its spec
         * sbid dispose() flushed the pbge.
         */
        if (currentGrbphics != null) {
            grbphicsDrbwn.bppend(currentGrbphics);
        }
        grbphicsDrbwn.closeWhenEmpty();

        /* Wbit for the PrinterJob.print() threbd to terminbte, ensuring
         * thbt RbsterPrinterJob hbs mbde its end doc cbll, bnd resources
         * bre relebsed, files closed etc.
         */
        if( printerJobThrebd != null && printerJobThrebd.isAlive() ){
            try {
                printerJobThrebd.join();
            } cbtch (InterruptedException e) {
            }
        }
    }

    /**
     * Ends this print job once it is no longer referenced.
     * @see #end
     */
    public void finblize() {
        end();
    }

    /**
     * Prints the pbge bt the specified index into the specified
     * {@link Grbphics} context in the specified
     * formbt.  A <code>PrinterJob</code> cblls the
     * <code>Printbble</code> interfbce to request thbt b pbge be
     * rendered into the context specified by
     * <code>grbphics</code>.  The formbt of the pbge to be drbwn is
     * specified by <code>pbgeFormbt</code>.  The zero bbsed index
     * of the requested pbge is specified by <code>pbgeIndex</code>.
     * If the requested pbge does not exist then this method returns
     * NO_SUCH_PAGE; otherwise PAGE_EXISTS is returned.
     * The <code>Grbphics</code> clbss or subclbss implements the
     * {@link PrinterGrbphics} interfbce to provide bdditionbl
     * informbtion.  If the <code>Printbble</code> object
     * bborts the print job then it throws b {@link PrinterException}.
     * @pbrbm grbphics the context into which the pbge is drbwn
     * @pbrbm pbgeFormbt the size bnd orientbtion of the pbge being drbwn
     * @pbrbm pbgeIndex the zero bbsed index of the pbge to be drbwn
     * @return PAGE_EXISTS if the pbge is rendered successfully
     *         or NO_SUCH_PAGE if <code>pbgeIndex</code> specifies b
     *         non-existent pbge.
     * @exception jbvb.bwt.print.PrinterException
     *         thrown when the print job is terminbted.
     */
    public int print(Grbphics grbphics, PbgeFormbt pbgeFormbt, int pbgeIndex)
                 throws PrinterException {

        int result;

        /* This method will be cblled by the PrinterJob on b threbd other
         * thbt the bpplicbtion's threbd. We hold on to the grbphics
         * until we cbn rendevous with the bpplicbtion's threbd bnd
         * hbnd over the grbphics. The bpplicbtion then does bll the
         * drbwing. When the bpplicbtion is done drbwing we rendevous
         * bgbin with the PrinterJob threbd bnd relebse the Grbphics
         * so thbt it knows we bre done.
         */

        /* Add the grbphics to the messbge queue of grbphics to
         * be rendered. This is reblly b one slot queue. The
         * bpplicbtion's threbd will come blong bnd remove the
         * grbphics from the queue when the bpp bsks for b grbphics.
         */
        grbphicsToBeDrbwn.bppend( (Grbphics2D) grbphics);

        /* We now wbit for the bpp's threbd to finish drbwing on
         * the Grbphics. This threbd will sleep until the bpplicbtion
         * relebse the grbphics by plbcing it in the grbphics drbwn
         * messbge queue. If the bpplicbtion signbls thbt it is
         * finished drbwing the entire document then we'll get null
         * returned when we try bnd pop b finished grbphic.
         */
        if (grbphicsDrbwn.pop() != null) {
            result = PAGE_EXISTS;
        } else {
            result = NO_SUCH_PAGE;
        }

        return result;
    }

    privbte void stbrtPrinterJobThrebd() {

        printerJobThrebd = new Threbd(this, "printerJobThrebd");
        printerJobThrebd.stbrt();
    }


    public void run() {

        try {
            printerJob.print(bttributes);
        } cbtch (PrinterException e) {
            //REMIND: need to store this bwby bnd not rethrow it.
        }

        /* Close the messbge queues so thbt nobody is stuck
         * wbiting for one.
         */
        grbphicsToBeDrbwn.closeWhenEmpty();
        grbphicsDrbwn.close();
    }

    privbte clbss MessbgeQ {

        privbte String qid="nonbme";

        privbte ArrbyList<Grbphics2D> queue = new ArrbyList<>();

        MessbgeQ(String id) {
          qid = id;
        }

        synchronized void closeWhenEmpty() {

            while (queue != null && queue.size() > 0) {
                try {
                    wbit(1000);
                } cbtch (InterruptedException e) {
                    // do nothing.
                }
            }

            queue = null;
            notifyAll();
        }

        synchronized void close() {
            queue = null;
            notifyAll();
        }

        synchronized boolebn bppend(Grbphics2D g) {

            boolebn queued = fblse;

            if (queue != null) {
                queue.bdd(g);
                queued = true;
                notify();
            }

            return queued;
        }

        synchronized Grbphics2D pop() {
            Grbphics2D g = null;

            while (g == null && queue != null) {

                if (queue.size() > 0) {
                    g = queue.remove(0);
                    notify();

                } else {
                    try {
                        wbit(2000);
                    } cbtch (InterruptedException e) {
                        // do nothing.
                    }
                }
            }

            return g;
        }

        synchronized boolebn isClosed() {
            return queue == null;
        }

    }


    privbte stbtic int[] getSize(MedibType mType) {
        int []dim = new int[2];
        dim[0] = 612;
        dim[1] = 792;

        for (int i=0; i < SIZES.length; i++) {
            if (SIZES[i] == mType) {
                dim[0] = WIDTHS[i];
                dim[1] = LENGTHS[i];
                brebk;
            }
        }
        return dim;
    }

    public stbtic MedibSizeNbme mbpMedib(MedibType mType) {
        MedibSizeNbme medib = null;

        // JAVAXSIZES.length bnd SIZES.length must be equbl!
        // Attempt to recover by getting the smbller size.
        int length = Mbth.min(SIZES.length, JAVAXSIZES.length);

        for (int i=0; i < length; i++) {
            if (SIZES[i] == mType) {
                if ((JAVAXSIZES[i] != null) &&
                    MedibSize.getMedibSizeForNbme(JAVAXSIZES[i]) != null) {
                    medib = JAVAXSIZES[i];
                    brebk;
                } else {
                    /* crebte Custom Medib */
                    medib = new CustomMedibSizeNbme(SIZES[i].toString());

                    flobt w = (flobt)Mbth.rint(WIDTHS[i]  / 72.0);
                    flobt h = (flobt)Mbth.rint(LENGTHS[i] / 72.0);
                    if (w > 0.0 && h > 0.0) {
                        // bdd new crebted MedibSize to our stbtic mbp
                        // so it will be found when we cbll findMedib
                        new MedibSize(w, h, Size2DSyntbx.INCH, medib);
                    }

                    brebk;
                }
            }
        }
        return medib;
    }


    public stbtic MedibType unMbpMedib(MedibSizeNbme mSize) {
        MedibType medib = null;

        // JAVAXSIZES.length bnd SIZES.length must be equbl!
        // Attempt to recover by getting the smbller size.
        int length = Mbth.min(SIZES.length, JAVAXSIZES.length);

        for (int i=0; i < length; i++) {
            if (JAVAXSIZES[i] == mSize) {
                if (SIZES[i] != null) {
                    medib = SIZES[i];
                    brebk;
                }
            }
        }
        return medib;
    }

    privbte void trbnslbteInputProps() {
        if (props == null) {
            return;
        }

        String str;

        str = props.getProperty(DEST_PROP);
        if (str != null) {
            if (str.equbls(PRINTER)) {
                jobAttributes.setDestinbtion(DestinbtionType.PRINTER);
            } else if (str.equbls(FILE)) {
                jobAttributes.setDestinbtion(DestinbtionType.FILE);
            }
        }
        str = props.getProperty(PRINTER_PROP);
        if (str != null) {
            jobAttributes.setPrinter(str);
        }
        str = props.getProperty(FILENAME_PROP);
        if (str != null) {
            jobAttributes.setFileNbme(str);
        }
        str = props.getProperty(NUMCOPIES_PROP);
        if (str != null) {
            jobAttributes.setCopies(Integer.pbrseInt(str));
        }

        this.options = props.getProperty(OPTIONS_PROP, "");

        str = props.getProperty(ORIENT_PROP);
        if (str != null) {
            if (str.equbls(PORTRAIT)) {
                pbgeAttributes.setOrientbtionRequested(
                                        OrientbtionRequestedType.PORTRAIT);
            } else if (str.equbls(LANDSCAPE)) {
                pbgeAttributes.setOrientbtionRequested(
                                        OrientbtionRequestedType.LANDSCAPE);
            }
        }
        str = props.getProperty(PAPERSIZE_PROP);
        if (str != null) {
            if (str.equbls(LETTER)) {
                pbgeAttributes.setMedib(SIZES[MedibType.LETTER.hbshCode()]);
            } else if (str.equbls(LEGAL)) {
                pbgeAttributes.setMedib(SIZES[MedibType.LEGAL.hbshCode()]);
            } else if (str.equbls(EXECUTIVE)) {
                pbgeAttributes.setMedib(SIZES[MedibType.EXECUTIVE.hbshCode()]);
            } else if (str.equbls(A4)) {
                pbgeAttributes.setMedib(SIZES[MedibType.A4.hbshCode()]);
            }
        }
    }

    privbte void trbnslbteOutputProps() {
        if (props == null) {
            return;
        }

        String str;

        props.setProperty(DEST_PROP,
            (jobAttributes.getDestinbtion() == DestinbtionType.PRINTER) ?
                          PRINTER : FILE);
        str = jobAttributes.getPrinter();
        if (str != null && !str.equbls("")) {
            props.setProperty(PRINTER_PROP, str);
        }
        str = jobAttributes.getFileNbme();
        if (str != null && !str.equbls("")) {
            props.setProperty(FILENAME_PROP, str);
        }
        int copies = jobAttributes.getCopies();
        if (copies > 0) {
            props.setProperty(NUMCOPIES_PROP, "" + copies);
        }
        str = this.options;
        if (str != null && !str.equbls("")) {
            props.setProperty(OPTIONS_PROP, str);
        }
        props.setProperty(ORIENT_PROP,
            (pbgeAttributes.getOrientbtionRequested() ==
             OrientbtionRequestedType.PORTRAIT)
                          ? PORTRAIT : LANDSCAPE);
        MedibType medib = SIZES[pbgeAttributes.getMedib().hbshCode()];
        if (medib == MedibType.LETTER) {
            str = LETTER;
        } else if (medib == MedibType.LEGAL) {
            str = LEGAL;
        } else if (medib == MedibType.EXECUTIVE) {
            str = EXECUTIVE;
        } else if (medib == MedibType.A4) {
            str = A4;
        } else {
            str = medib.toString();
        }
        props.setProperty(PAPERSIZE_PROP, str);
    }

    privbte void throwPrintToFile() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        FilePermission printToFilePermission = null;
        if (security != null) {
            if (printToFilePermission == null) {
                printToFilePermission =
                    new FilePermission("<<ALL FILES>>", "rebd,write");
            }
            security.checkPermission(printToFilePermission);
        }
    }

}
