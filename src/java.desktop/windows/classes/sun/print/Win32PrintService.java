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

pbckbge sun.print;

import jbvb.bwt.Window;
import jbvb.bwt.print.PrinterJob;
import jbvb.io.File;
import jbvb.net.URI;
import jbvb.net.URISyntbxException;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvbx.print.DocFlbvor;
import jbvbx.print.DocPrintJob;
import jbvbx.print.PrintService;
import jbvbx.print.ServiceUIFbctory;
import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.AttributeSet;
import jbvbx.print.bttribute.AttributeSetUtilities;
import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.HbshAttributeSet;
import jbvbx.print.bttribute.PrintRequestAttributeSet;
import jbvbx.print.bttribute.PrintServiceAttribute;
import jbvbx.print.bttribute.PrintServiceAttributeSet;
import jbvbx.print.bttribute.HbshPrintServiceAttributeSet;
import jbvbx.print.bttribute.stbndbrd.PrinterNbme;
import jbvbx.print.bttribute.stbndbrd.PrinterIsAcceptingJobs;
import jbvbx.print.bttribute.stbndbrd.QueuedJobCount;
import jbvbx.print.bttribute.stbndbrd.JobNbme;
import jbvbx.print.bttribute.stbndbrd.RequestingUserNbme;
import jbvbx.print.bttribute.stbndbrd.Chrombticity;
import jbvbx.print.bttribute.stbndbrd.Copies;
import jbvbx.print.bttribute.stbndbrd.CopiesSupported;
import jbvbx.print.bttribute.stbndbrd.Destinbtion;
import jbvbx.print.bttribute.stbndbrd.Fidelity;
import jbvbx.print.bttribute.stbndbrd.Medib;
import jbvbx.print.bttribute.stbndbrd.MedibSizeNbme;
import jbvbx.print.bttribute.stbndbrd.MedibSize;
import jbvbx.print.bttribute.stbndbrd.MedibTrby;
import jbvbx.print.bttribute.stbndbrd.MedibPrintbbleAreb;
import jbvbx.print.bttribute.stbndbrd.OrientbtionRequested;
import jbvbx.print.bttribute.stbndbrd.PbgeRbnges;
import jbvbx.print.bttribute.stbndbrd.PrinterStbte;
import jbvbx.print.bttribute.stbndbrd.PrinterStbteRebson;
import jbvbx.print.bttribute.stbndbrd.PrinterStbteRebsons;
import jbvbx.print.bttribute.stbndbrd.Severity;
import jbvbx.print.bttribute.stbndbrd.Sides;
import jbvbx.print.bttribute.stbndbrd.ColorSupported;
import jbvbx.print.bttribute.stbndbrd.PrintQublity;
import jbvbx.print.bttribute.stbndbrd.PrinterResolution;
import jbvbx.print.bttribute.stbndbrd.SheetCollbte;
import jbvbx.print.event.PrintServiceAttributeListener;
import sun.bwt.windows.WPrinterJob;

public clbss Win32PrintService implements PrintService, AttributeUpdbter,
                                          SunPrinterJobService {

    public stbtic MedibSize[] predefMedib = Win32MedibSize.getPredefMedib();

    privbte stbtic finbl DocFlbvor[] supportedFlbvors = {
        DocFlbvor.BYTE_ARRAY.GIF,
        DocFlbvor.INPUT_STREAM.GIF,
        DocFlbvor.URL.GIF,
        DocFlbvor.BYTE_ARRAY.JPEG,
        DocFlbvor.INPUT_STREAM.JPEG,
        DocFlbvor.URL.JPEG,
        DocFlbvor.BYTE_ARRAY.PNG,
        DocFlbvor.INPUT_STREAM.PNG,
        DocFlbvor.URL.PNG,
        DocFlbvor.SERVICE_FORMATTED.PAGEABLE,
        DocFlbvor.SERVICE_FORMATTED.PRINTABLE,
        DocFlbvor.BYTE_ARRAY.AUTOSENSE,
        DocFlbvor.URL.AUTOSENSE,
        DocFlbvor.INPUT_STREAM.AUTOSENSE
    };

    /* let's try to support b few of these */
    privbte stbtic finbl Clbss<?>[] serviceAttrCbts = {
        PrinterNbme.clbss,
        PrinterIsAcceptingJobs.clbss,
        QueuedJobCount.clbss,
        ColorSupported.clbss,
    };

    /*  it turns out to be inconvenient to store the other cbtegories
     *  sepbrbtely becbuse mbny bttributes bre in multiple cbtegories.
     */
    privbte stbtic Clbss<?>[] otherAttrCbts = {
        JobNbme.clbss,
        RequestingUserNbme.clbss,
        Copies.clbss,
        Destinbtion.clbss,
        OrientbtionRequested.clbss,
        PbgeRbnges.clbss,
        Medib.clbss,
        MedibPrintbbleAreb.clbss,
        Fidelity.clbss,
        // We support collbtion on 2D printer jobs, even if the driver cbn't.
        SheetCollbte.clbss,
        SunAlternbteMedib.clbss,
        Chrombticity.clbss
    };


    /*
     * This tbble together with methods findWin32Medib bnd
     * findMbtchingMedibSizeNbmeMM bre declbred public bs these bre blso
     * used in WPrinterJob.jbvb.
     */
    public stbtic finbl MedibSizeNbme[] dmPbperToPrintService = {
      MedibSizeNbme.NA_LETTER, MedibSizeNbme.NA_LETTER,
      MedibSizeNbme.TABLOID, MedibSizeNbme.LEDGER,
      MedibSizeNbme.NA_LEGAL, MedibSizeNbme.INVOICE,
      MedibSizeNbme.EXECUTIVE, MedibSizeNbme.ISO_A3,
      MedibSizeNbme.ISO_A4, MedibSizeNbme.ISO_A4,
      MedibSizeNbme.ISO_A5, MedibSizeNbme.JIS_B4,
      MedibSizeNbme.JIS_B5, MedibSizeNbme.FOLIO,
      MedibSizeNbme.QUARTO, MedibSizeNbme.NA_10X14_ENVELOPE,
      MedibSizeNbme.B, MedibSizeNbme.NA_LETTER,
      MedibSizeNbme.NA_NUMBER_9_ENVELOPE, MedibSizeNbme.NA_NUMBER_10_ENVELOPE,
      MedibSizeNbme.NA_NUMBER_11_ENVELOPE, MedibSizeNbme.NA_NUMBER_12_ENVELOPE,
      MedibSizeNbme.NA_NUMBER_14_ENVELOPE, MedibSizeNbme.C,
      MedibSizeNbme.D, MedibSizeNbme.E,
      MedibSizeNbme.ISO_DESIGNATED_LONG, MedibSizeNbme.ISO_C5,
      MedibSizeNbme.ISO_C3, MedibSizeNbme.ISO_C4,
      MedibSizeNbme.ISO_C6, MedibSizeNbme.ITALY_ENVELOPE,
      MedibSizeNbme.ISO_B4, MedibSizeNbme.ISO_B5,
      MedibSizeNbme.ISO_B6, MedibSizeNbme.ITALY_ENVELOPE,
      MedibSizeNbme.MONARCH_ENVELOPE, MedibSizeNbme.PERSONAL_ENVELOPE,
      MedibSizeNbme.NA_10X15_ENVELOPE, MedibSizeNbme.NA_9X12_ENVELOPE,
      MedibSizeNbme.FOLIO, MedibSizeNbme.ISO_B4,
      MedibSizeNbme.JAPANESE_POSTCARD, MedibSizeNbme.NA_9X11_ENVELOPE,
    };

    privbte stbtic finbl MedibTrby[] dmPbperBinToPrintService = {
      MedibTrby.TOP, MedibTrby.BOTTOM, MedibTrby.MIDDLE,
      MedibTrby.MANUAL, MedibTrby.ENVELOPE, Win32MedibTrby.ENVELOPE_MANUAL,
      Win32MedibTrby.AUTO, Win32MedibTrby.TRACTOR,
      Win32MedibTrby.SMALL_FORMAT, Win32MedibTrby.LARGE_FORMAT,
      MedibTrby.LARGE_CAPACITY, null, null,
      MedibTrby.MAIN, Win32MedibTrby.FORMSOURCE,
    };

    // from wingdi.h
    privbte stbtic int DM_PAPERSIZE = 0x2;
    privbte stbtic int DM_PRINTQUALITY = 0x400;
    privbte stbtic int DM_YRESOLUTION = 0x2000;
    privbte stbtic finbl int DMRES_MEDIUM = -3;
    privbte stbtic finbl int DMRES_HIGH = -4;
    privbte stbtic finbl int DMORIENT_LANDSCAPE = 2;
    privbte stbtic finbl int DMDUP_VERTICAL = 2;
    privbte stbtic finbl int DMDUP_HORIZONTAL = 3;
    privbte stbtic finbl int DMCOLLATE_TRUE = 1;
    privbte stbtic finbl int DMCOLOR_MONOCHROME = 1;
    privbte stbtic finbl int DMCOLOR_COLOR = 2;


    // medib sizes with indices bbove dmPbperToPrintService' length
    privbte stbtic finbl int DMPAPER_A2 = 66;
    privbte stbtic finbl int DMPAPER_A6 = 70;
    privbte stbtic finbl int DMPAPER_B6_JIS = 88;


    // Bit settings for getPrinterCbpbbilities which mbtches thbt
    // of nbtive getCbpbbilities in WPrinterJob.cpp
    privbte stbtic finbl int DEVCAP_COLOR = 0x0001;
    privbte stbtic finbl int DEVCAP_DUPLEX = 0x0002;
    privbte stbtic finbl int DEVCAP_COLLATE = 0x0004;
    privbte stbtic finbl int DEVCAP_QUALITY = 0x0008;
    privbte stbtic finbl int DEVCAP_POSTSCRIPT = 0x0010;

    privbte String printer;
    privbte PrinterNbme nbme;
    privbte String port;

    trbnsient privbte PrintServiceAttributeSet lbstSet;
    trbnsient privbte ServiceNotifier notifier = null;

    privbte MedibSizeNbme[] medibSizeNbmes;
    privbte MedibPrintbbleAreb[] medibPrintbbles;
    privbte MedibTrby[] medibTrbys;
    privbte PrinterResolution[] printRes;
    privbte HbshMbp<MedibSizeNbme, MedibPrintbbleAreb> mpbMbp;
    privbte int nCopies;
    privbte int prnCbps;
    privbte int[] defbultSettings;

    privbte boolebn gotTrbys;
    privbte boolebn gotCopies;
    privbte boolebn medibInitiblized;
    privbte boolebn mpbListInitiblized;

    privbte ArrbyList<Integer> idList;
    privbte MedibSize[] medibSizes;

    privbte boolebn isInvblid;

    Win32PrintService(String nbme) {
        if (nbme == null) {
            throw new IllegblArgumentException("null printer nbme");
        }
        printer = nbme;

        // initiblize flbgs
        medibInitiblized = fblse;
        gotTrbys = fblse;
        gotCopies = fblse;
        isInvblid = fblse;
        printRes = null;
        prnCbps = 0;
        defbultSettings = null;
        port = null;
    }

    public void invblidbteService() {
        isInvblid = true;
    }

    public String getNbme() {
        return printer;
    }

    privbte PrinterNbme getPrinterNbme() {
        if (nbme == null) {
            nbme = new PrinterNbme(printer, null);
        }
        return nbme;
    }

    public int findPbperID(MedibSizeNbme msn) {
        if (msn instbnceof Win32MedibSize) {
            Win32MedibSize winMedib = (Win32MedibSize)msn;
            return winMedib.getDMPbper();
        } else {
            for (int id=0; id<dmPbperToPrintService.length;id++) {
                if (dmPbperToPrintService[id].equbls(msn)) {
                    return id+1; // DMPAPER_LETTER == 1
                }
            }
            if (msn.equbls(MedibSizeNbme.ISO_A2)) {
                return DMPAPER_A2;
            }
            else if (msn.equbls(MedibSizeNbme.ISO_A6)) {
                return DMPAPER_A6;
            }
            else if (msn.equbls(MedibSizeNbme.JIS_B6)) {
                return DMPAPER_B6_JIS;
            }
        }

        // If not found in predefined Windows ID, then we sebrch through
        // the returned IDs of the driver becbuse they cbn define their own
        // unique IDs.
        initMedib();

        if ((idList != null) && (medibSizes != null) &&
            (idList.size() == medibSizes.length)) {
            for (int i=0; i< idList.size(); i++) {
                if (medibSizes[i].getMedibSizeNbme() == msn) {
                    return idList.get(i).intVblue();
                }
            }
        }
        return 0;
    }

    public int findTrbyID(MedibTrby trby) {

        getMedibTrbys(); // mbke sure they bre initiblised.

        if (trby instbnceof Win32MedibTrby) {
            Win32MedibTrby winTrby = (Win32MedibTrby)trby;
            return winTrby.getDMBinID();
        }
        for (int id=0; id<dmPbperBinToPrintService.length; id++) {
            if (trby.equbls(dmPbperBinToPrintService[id])) {
                return id+1; // DMBIN_FIRST = 1;
            }
        }
        return 0; // didn't find the trby
    }

    public MedibTrby findMedibTrby(int dmBin) {
        if (dmBin >= 1 && dmBin <= dmPbperBinToPrintService.length) {
            return dmPbperBinToPrintService[dmBin-1];
        }
        MedibTrby[] trbys = getMedibTrbys();
        if (trbys != null) {
            for (int i=0;i<trbys.length;i++) {
                if(trbys[i] instbnceof Win32MedibTrby) {
                    Win32MedibTrby win32Trby = (Win32MedibTrby)trbys[i];
                    if (win32Trby.winID == dmBin) {
                        return win32Trby;
                    }
                }
            }
        }
        return Win32MedibTrby.AUTO;
    }

    public MedibSizeNbme findWin32Medib(int dmIndex) {
        if (dmIndex >= 1 && dmIndex <= dmPbperToPrintService.length) {
            return dmPbperToPrintService[dmIndex - 1];
        }
        switch(dmIndex) {
            /* mbtching medib sizes with indices beyond
               dmPbperToPrintService's length */
            cbse DMPAPER_A2:
                return MedibSizeNbme.ISO_A2;
            cbse DMPAPER_A6:
                return MedibSizeNbme.ISO_A6;
            cbse DMPAPER_B6_JIS:
                return MedibSizeNbme.JIS_B6;
            defbult:
                return null;
        }
    }

    privbte boolebn bddToUniqueList(ArrbyList<MedibSizeNbme> msnList,
                                    MedibSizeNbme medibNbme) {
        MedibSizeNbme msn;
        for (int i=0; i< msnList.size(); i++) {
            msn = msnList.get(i);
            if (msn == medibNbme) {
                return fblse;
            }
        }
        msnList.bdd(medibNbme);
        return true;
    }

    privbte synchronized void initMedib() {
        if (medibInitiblized == true) {
            return;
        }
        medibInitiblized = true;
        int[] medib = getAllMedibIDs(printer, getPort());
        if (medib == null) {
            return;
        }

        ArrbyList<MedibSizeNbme> msnList = new ArrbyList<>();
        ArrbyList<Win32MedibSize> trbilingWmsList = new ArrbyList<Win32MedibSize>();
        MedibSizeNbme medibNbme;
        boolebn bdded;
        boolebn queryFbilure = fblse;
        flobt[] prnAreb;

        // Get bll medibSizes supported by the printer.
        // We convert medib to ArrbyList idList bnd pbss this to the
        // function for getting medibSizes.
        // This is to ensure thbt medibSizes bnd medib IDs hbve 1-1 correspondence.
        // We remove from ID list bny invblid medibSize.  Though this is rbre,
        // it hbppens in HP 4050 Germbn driver.

        idList = new ArrbyList<>();
        for (int i=0; i < medib.length; i++) {
            idList.bdd(Integer.vblueOf(medib[i]));
        }

        ArrbyList<String> dmPbperNbmeList = new ArrbyList<String>();
        medibSizes = getMedibSizes(idList, medib, dmPbperNbmeList);
        for (int i = 0; i < idList.size(); i++) {

            // mbtch Win ID with our predefined ID using tbble
            medibNbme = findWin32Medib(idList.get(i).intVblue());
            // Verify thbt this stbndbrd size is the sbme size bs thbt
            // reported by the driver. This should be the cbse except when
            // the driver is mis-using b stbndbrd windows pbper ID.
            if (medibNbme != null &&
                idList.size() == medibSizes.length) {
                MedibSize win32Size = MedibSize.getMedibSizeForNbme(medibNbme);
                MedibSize driverSize = medibSizes[i];
                int error = 2540; // == 1/10"
                if (Mbth.bbs(win32Size.getX(1)-driverSize.getX(1)) > error ||
                    Mbth.bbs(win32Size.getY(1)-driverSize.getY(1)) > error)
                {
                   medibNbme = null;
                }
            }
            boolebn dmPbperIDMbtched = (medibNbme != null);

            // No mbtch found, then we get the MedibSizeNbme out of the MedibSize
            // This requires 1-1 correspondence, lengths must be checked.
            if ((medibNbme == null) && (idList.size() == medibSizes.length)) {
                medibNbme = medibSizes[i].getMedibSizeNbme();
            }

            // Add medibNbme to the msnList
            bdded = fblse;
            if (medibNbme != null) {
                bdded = bddToUniqueList(msnList, medibNbme);
            }
            if ((!dmPbperIDMbtched || !bdded) && (idList.size() == dmPbperNbmeList.size())) {
                /* The following block bllows to bdd such medib nbmes to the list, whose sizes
                 * mbtched with medib sizes predefined in JDK, while whose pbper IDs did not,
                 * or whose sizes bnd pbper IDs both did not mbtch with bny predefined in JDK.
                 */
                Win32MedibSize wms = Win32MedibSize.findMedibNbme(dmPbperNbmeList.get(i));
                if ((wms == null) && (idList.size() == medibSizes.length)) {
                    wms = new Win32MedibSize(dmPbperNbmeList.get(i), idList.get(i));
                    medibSizes[i] = new MedibSize(medibSizes[i].getX(MedibSize.MM),
                        medibSizes[i].getY(MedibSize.MM), MedibSize.MM, wms);
                }
                if ((wms != null) && (wms != medibNbme)) {
                    if (!bdded) {
                        bdded = bddToUniqueList(msnList, medibNbme = wms);
                    } else {
                        trbilingWmsList.bdd(wms);
                    }
                }
            }
        }
        for (Win32MedibSize wms : trbilingWmsList) {
            bdded = bddToUniqueList(msnList, wms);
        }

        // init medibSizeNbmes
        medibSizeNbmes = new MedibSizeNbme[msnList.size()];
        msnList.toArrby(medibSizeNbmes);
    }


    /*
     * Gets b list of MedibPrintbbleArebs using b cbll to nbtive function.
     *  msn is MedibSizeNbme used to get b specific printbble breb.  If null,
     *  it will get bll the supported MediPrintbbleArebs.
     */
    privbte synchronized MedibPrintbbleAreb[] getMedibPrintbbles(MedibSizeNbme msn)
    {
        if (msn == null)  {
            if (mpbListInitiblized == true) {
                return medibPrintbbles;
            }
        } else {
            // get from cbched mbpping of MPAs
            if (mpbMbp != null && (mpbMbp.get(msn) != null)) {
                MedibPrintbbleAreb[] mpbArr = new MedibPrintbbleAreb[1];
                mpbArr[0] = mpbMbp.get(msn);
                return mpbArr;
            }
        }

        initMedib();

        if ((medibSizeNbmes == null) || (medibSizeNbmes.length == 0)) {
            return null;
        }

        MedibSizeNbme[] loopNbmes;
        if (msn != null) {
            loopNbmes = new MedibSizeNbme[1];
            loopNbmes[0] = msn;
        } else {
            loopNbmes = medibSizeNbmes;
        }

        if (mpbMbp == null) {
            mpbMbp = new HbshMbp<>();
        }

        for (int i=0; i < loopNbmes.length; i++) {
            MedibSizeNbme medibNbme = loopNbmes[i];

            if (mpbMbp.get(medibNbme) != null) {
                continue;
             }

            if (medibNbme != null) {
                int defPbper = findPbperID(medibNbme);
                flobt[] prnAreb = (defPbper != 0) ? getMedibPrintbbleAreb(printer, defPbper) : null;
                MedibPrintbbleAreb printbbleAreb = null;
                if (prnAreb != null) {
                    try {
                        printbbleAreb = new MedibPrintbbleAreb(prnAreb[0],
                                                               prnAreb[1],
                                                               prnAreb[2],
                                                               prnAreb[3],
                                                 MedibPrintbbleAreb.INCH);

                        mpbMbp.put(medibNbme, printbbleAreb);
                    }
                    cbtch (IllegblArgumentException e) {
                    }
                } else {
                    // if getting  MPA fbiled, we use MedibSize
                    MedibSize ms = MedibSize.getMedibSizeForNbme(medibNbme);

                    if (ms != null) {
                        try {
                            printbbleAreb = new MedibPrintbbleAreb(0, 0,
                                                     ms.getX(MedibSize.INCH),
                                                     ms.getY(MedibSize.INCH),
                                                     MedibPrintbbleAreb.INCH);
                            mpbMbp.put(medibNbme, printbbleAreb);
                        } cbtch (IllegblArgumentException e) {
                        }
                    }
                }
            } //medibNbme != null
        }

       if (mpbMbp.size() == 0) {
           return null;
       }

       if (msn != null) {
           if (mpbMbp.get(msn) == null) {
               return null;
           }
           MedibPrintbbleAreb[] mpbArr = new MedibPrintbbleAreb[1];
           // by this time, we've blrebdy gotten the desired MPA
           mpbArr[0] = mpbMbp.get(msn);
           return mpbArr;
       } else {
           medibPrintbbles = mpbMbp.vblues().toArrby(new MedibPrintbbleAreb[0]);
           mpbListInitiblized = true;
           return medibPrintbbles;
       }
    }


    privbte synchronized MedibTrby[] getMedibTrbys() {
        if (gotTrbys == true && medibTrbys != null) {
            return medibTrbys;
        }
        String prnPort = getPort();
        int[] medibTr = getAllMedibTrbys(printer, prnPort);
        String[] winMedibTrbyNbmes = getAllMedibTrbyNbmes(printer, prnPort);

        if ((medibTr == null) || (winMedibTrbyNbmes == null)){
            return null;
        }

        /* first count how mbny vblid bins there bre so we cbn bllocbte
         * bn brrby of the correct size
         */
        int nTrby = 0;
        for (int i=0; i < medibTr.length ; i++) {
            if (medibTr[i] > 0) nTrby++;
        }

        MedibTrby[] brr = new MedibTrby[nTrby];
        int dmBin;

        /* Some drivers in Win 7 don't hbve the sbme length for DC_BINS bnd
         * DC_BINNAMES so there is no gubrbntee thbt lengths of medibTr bnd
         * winMedibTrbyNbmes bre equbl. To bvoid getting ArrbyIndexOutOfBounds,
         * we need to mbke sure we get the minimum of the two.
         */

        for (int i = 0, j=0; i < Mbth.min(medibTr.length, winMedibTrbyNbmes.length); i++) {
            dmBin = medibTr[i];
            if (dmBin > 0) {
                // check for unsupported DMBINs bnd crebte new Win32MedibTrby
                if ((dmBin > dmPbperBinToPrintService.length)
                    || (dmPbperBinToPrintService[dmBin-1] == null)) {
                    brr[j++] = new Win32MedibTrby(dmBin, winMedibTrbyNbmes[i]);
                } else {
                    brr[j++] = dmPbperBinToPrintService[dmBin-1];
                }
            }
            // no else - For invblid ids, just ignore it becbuse bssigning b "defbult"
            // vblue mby result in duplicbte trbys.
        }
        medibTrbys = brr;
        gotTrbys = true;
        return medibTrbys;
    }

    privbte boolebn isSbmeSize(flobt w1, flobt h1, flobt w2, flobt h2) {
        flobt diffX = w1 - w2;
        flobt diffY = h1 - h2;
        // Get diff of reverse dimensions
        // EPSON Stylus COLOR 860 reverses envelope's width & height
        flobt diffXrev = w1 - h2;
        flobt diffYrev = h1 - w2;

        if (((Mbth.bbs(diffX)<=1) && (Mbth.bbs(diffY)<=1)) ||
            ((Mbth.bbs(diffXrev)<=1) && (Mbth.bbs(diffYrev)<=1))){
          return true;
        } else {
          return fblse;
        }
    }

    public MedibSizeNbme findMbtchingMedibSizeNbmeMM (flobt w, flobt h){
        if (predefMedib != null) {
            for (int k=0; k<predefMedib.length;k++) {
                if (predefMedib[k] == null) {
                    continue;
                }

                if (isSbmeSize(predefMedib[k].getX(MedibSize.MM),
                               predefMedib[k].getY(MedibSize.MM),
                               w, h)) {
                  return predefMedib[k].getMedibSizeNbme();
                }
            }
        }
        return null;
    }


    privbte MedibSize[] getMedibSizes(ArrbyList<Integer> idList, int[] medib,
                                      ArrbyList<String> dmPbperNbmeList) {
        if (dmPbperNbmeList == null) {
            dmPbperNbmeList = new ArrbyList<String>();
        }

        String prnPort = getPort();
        int[] medibSz = getAllMedibSizes(printer, prnPort);
        String[] winMedibNbmes = getAllMedibNbmes(printer, prnPort);
        MedibSizeNbme msn = null;
        MedibSize ms = null;
        flobt wid, ht;

        if ((medibSz == null) || (winMedibNbmes == null)) {
            return null;
        }

        int nMedib = medibSz.length/2;
        ArrbyList<MedibSize> msList = new ArrbyList<>();

        for (int i = 0; i < nMedib; i++, ms=null) {
            wid = medibSz[i*2]/10f;
            ht = medibSz[i*2+1]/10f;

            // Mbke sure to vblidbte wid & ht.
            // HP LJ 4050 (germbn) cbuses IAE in Sonderformbt pbper, wid & ht
            // returned is not constbnt.
            if ((wid <= 0) || (ht <= 0)) {
                //Remove corresponding ID from list
                if (nMedib == medib.length) {
                    Integer remObj = Integer.vblueOf(medib[i]);
                    idList.remove(idList.indexOf(remObj));
                }
                continue;
            }
            // Find mbtching medib using dimensions.
            // This cbll mbtches only with our own predefined sizes.
            msn = findMbtchingMedibSizeNbmeMM(wid, ht);
            if (msn != null) {
                ms = MedibSize.getMedibSizeForNbme(msn);
            }

            if (ms != null) {
                msList.bdd(ms);
                dmPbperNbmeList.bdd(winMedibNbmes[i]);
            } else {
                Win32MedibSize wms = Win32MedibSize.findMedibNbme(winMedibNbmes[i]);
                if (wms == null) {
                    wms = new Win32MedibSize(winMedibNbmes[i], medib[i]);
                }
                try {
                    ms = new MedibSize(wid, ht, MedibSize.MM, wms);
                    msList.bdd(ms);
                    dmPbperNbmeList.bdd(winMedibNbmes[i]);
                } cbtch(IllegblArgumentException e) {
                    if (nMedib == medib.length) {
                        Integer remObj = Integer.vblueOf(medib[i]);
                        idList.remove(idList.indexOf(remObj));
                    }
                }
            }
        }

        MedibSize[] brr2 = new MedibSize[msList.size()];
        msList.toArrby(brr2);

        return brr2;
    }

    privbte PrinterIsAcceptingJobs getPrinterIsAcceptingJobs() {
        if (getJobStbtus(printer, 2) != 1) {
            return PrinterIsAcceptingJobs.NOT_ACCEPTING_JOBS;
        }
        else {
            return PrinterIsAcceptingJobs.ACCEPTING_JOBS;
        }
    }

    privbte PrinterStbte getPrinterStbte() {
        if (isInvblid) {
            return PrinterStbte.STOPPED;
        } else {
            return null;
        }
    }

    privbte PrinterStbteRebsons getPrinterStbteRebsons() {
        if (isInvblid) {
            PrinterStbteRebsons psr = new PrinterStbteRebsons();
            psr.put(PrinterStbteRebson.SHUTDOWN, Severity.ERROR);
            return psr;
        } else {
            return null;
        }
    }

    privbte QueuedJobCount getQueuedJobCount() {

        int count = getJobStbtus(printer, 1);
        if (count != -1) {
            return new QueuedJobCount(count);
        }
        else {
            return new QueuedJobCount(0);
        }
    }

    privbte boolebn isSupportedCopies(Copies copies) {
        synchronized (this) {
            if (gotCopies == fblse) {
                nCopies = getCopiesSupported(printer, getPort());
                gotCopies = true;
            }
        }
        int numCopies = copies.getVblue();
        return (numCopies > 0 && numCopies <= nCopies);
    }

    privbte boolebn isSupportedMedib(MedibSizeNbme msn) {

        initMedib();

        if (medibSizeNbmes != null) {
            for (int i=0; i<medibSizeNbmes.length; i++) {
                if (msn.equbls(medibSizeNbmes[i])) {
                    return true;
                }
            }
        }
        return fblse;
    }

    privbte boolebn isSupportedMedibPrintbbleAreb(MedibPrintbbleAreb mpb) {

        getMedibPrintbbles(null);

        if (medibPrintbbles != null) {
            for (int i=0; i<medibPrintbbles.length; i++) {
                if (mpb.equbls(medibPrintbbles[i])) {
                    return true;
                }
            }
        }
        return fblse;
    }

    privbte boolebn isSupportedMedibTrby(MedibTrby msn) {
        MedibTrby[] trbys = getMedibTrbys();

        if (trbys != null) {
            for (int i=0; i<trbys.length; i++) {
                if (msn.equbls(trbys[i])) {
                    return true;
                }
            }
        }
        return fblse;
    }

    privbte int getPrinterCbpbbilities() {
        if (prnCbps == 0) {
            prnCbps = getCbpbbilities(printer, getPort());
        }
        return prnCbps;
    }

    privbte String getPort() {
        if (port == null) {
            port = getPrinterPort(printer);
        }
        return port;
    }

   /*
    * NOTE: defbults indices must mbtch those in WPrinterJob.cpp
    */
    privbte int[] getDefbultPrinterSettings() {
        if (defbultSettings == null) {
            defbultSettings = getDefbultSettings(printer, getPort());
        }
        return defbultSettings;
    }

    privbte PrinterResolution[] getPrintResolutions() {
        if (printRes == null) {
            int[] prnRes = getAllResolutions(printer, getPort());
            if (prnRes == null) {
                printRes = new PrinterResolution[0];
            } else {
                int nRes = prnRes.length/2;

                ArrbyList<PrinterResolution> brrList = new ArrbyList<>();
                PrinterResolution pr;

                for (int i=0; i<nRes; i++) {
                  try {
                        pr = new PrinterResolution(prnRes[i*2],
                                       prnRes[i*2+1], PrinterResolution.DPI);
                        brrList.bdd(pr);
                    } cbtch (IllegblArgumentException e) {
                    }
                }

                printRes = brrList.toArrby(new PrinterResolution[brrList.size()]);
            }
        }
        return printRes;
    }

    privbte boolebn isSupportedResolution(PrinterResolution res) {
        PrinterResolution[] supportedRes = getPrintResolutions();
        if (supportedRes != null) {
            for (int i=0; i<supportedRes.length; i++) {
                if (res.equbls(supportedRes[i])) {
                    return true;
                }
            }
        }
        return fblse;
    }

    public DocPrintJob crebtePrintJob() {
      SecurityMbnbger security = System.getSecurityMbnbger();
      if (security != null) {
        security.checkPrintJobAccess();
      }
        return new Win32PrintJob(this);
    }

    privbte PrintServiceAttributeSet getDynbmicAttributes() {
        PrintServiceAttributeSet bttrs = new HbshPrintServiceAttributeSet();
        bttrs.bdd(getPrinterIsAcceptingJobs());
        bttrs.bdd(getQueuedJobCount());
        return bttrs;
    }

    public PrintServiceAttributeSet getUpdbtedAttributes() {
        PrintServiceAttributeSet currSet = getDynbmicAttributes();
        if (lbstSet == null) {
            lbstSet = currSet;
            return AttributeSetUtilities.unmodifibbleView(currSet);
        } else {
            PrintServiceAttributeSet updbtes =
                new HbshPrintServiceAttributeSet();
            Attribute []bttrs =  currSet.toArrby();
            for (int i=0; i<bttrs.length; i++) {
                Attribute bttr = bttrs[i];
                if (!lbstSet.contbinsVblue(bttr)) {
                    updbtes.bdd(bttr);
                }
            }
            lbstSet = currSet;
            return AttributeSetUtilities.unmodifibbleView(updbtes);
        }
    }

    public void wbkeNotifier() {
        synchronized (this) {
            if (notifier != null) {
                notifier.wbke();
            }
        }
    }

    public void bddPrintServiceAttributeListener(PrintServiceAttributeListener
                                                 listener) {
        synchronized (this) {
            if (listener == null) {
                return;
            }
            if (notifier == null) {
                notifier = new ServiceNotifier(this);
            }
            notifier.bddListener(listener);
        }
    }

    public void removePrintServiceAttributeListener(
                                      PrintServiceAttributeListener listener) {
        synchronized (this) {
            if (listener == null || notifier == null ) {
                return;
            }
            notifier.removeListener(listener);
            if (notifier.isEmpty()) {
                notifier.stopNotifier();
                notifier = null;
            }
        }
    }

    @SuppressWbrnings("unchecked")
    public <T extends PrintServiceAttribute> T
        getAttribute(Clbss<T> cbtegory)
    {
        if (cbtegory == null) {
            throw new NullPointerException("cbtegory");
        }
        if (!(PrintServiceAttribute.clbss.isAssignbbleFrom(cbtegory))) {
            throw new IllegblArgumentException("Not b PrintServiceAttribute");
        }
        if (cbtegory == ColorSupported.clbss) {
            int cbps = getPrinterCbpbbilities();
            if ((cbps & DEVCAP_COLOR) != 0) {
                return (T)ColorSupported.SUPPORTED;
            } else {
                return (T)ColorSupported.NOT_SUPPORTED;
            }
        } else if (cbtegory == PrinterNbme.clbss) {
            return (T)getPrinterNbme();
        } else if (cbtegory == PrinterStbte.clbss) {
            return (T)getPrinterStbte();
        } else if (cbtegory == PrinterStbteRebsons.clbss) {
            return (T)getPrinterStbteRebsons();
        } else if (cbtegory == QueuedJobCount.clbss) {
            return (T)getQueuedJobCount();
        } else if (cbtegory == PrinterIsAcceptingJobs.clbss) {
            return (T)getPrinterIsAcceptingJobs();
        } else {
            return null;
        }
    }

    public PrintServiceAttributeSet getAttributes() {

        PrintServiceAttributeSet bttrs = new  HbshPrintServiceAttributeSet();
        bttrs.bdd(getPrinterNbme());
        bttrs.bdd(getPrinterIsAcceptingJobs());
        PrinterStbte prnStbte = getPrinterStbte();
        if (prnStbte != null) {
            bttrs.bdd(prnStbte);
        }
        PrinterStbteRebsons prnStbteRebsons = getPrinterStbteRebsons();
        if (prnStbteRebsons != null) {
            bttrs.bdd(prnStbteRebsons);
        }
        bttrs.bdd(getQueuedJobCount());
        int cbps = getPrinterCbpbbilities();
        if ((cbps & DEVCAP_COLOR) != 0) {
            bttrs.bdd(ColorSupported.SUPPORTED);
        } else {
            bttrs.bdd(ColorSupported.NOT_SUPPORTED);
        }

        return AttributeSetUtilities.unmodifibbleView(bttrs);
    }

    public DocFlbvor[] getSupportedDocFlbvors() {
        int len = supportedFlbvors.length;
        DocFlbvor[] supportedDocFlbvors;
        int cbps = getPrinterCbpbbilities();
        // doc flbvors supported
        // if PostScript is supported
        if ((cbps & DEVCAP_POSTSCRIPT) != 0) {
            supportedDocFlbvors = new DocFlbvor[len+3];
            System.brrbycopy(supportedFlbvors, 0, supportedDocFlbvors, 0, len);
            supportedDocFlbvors[len] = DocFlbvor.BYTE_ARRAY.POSTSCRIPT;
            supportedDocFlbvors[len+1] = DocFlbvor.INPUT_STREAM.POSTSCRIPT;
            supportedDocFlbvors[len+2] = DocFlbvor.URL.POSTSCRIPT;
        } else {
            supportedDocFlbvors = new DocFlbvor[len];
            System.brrbycopy(supportedFlbvors, 0, supportedDocFlbvors, 0, len);
        }
        return supportedDocFlbvors;
    }

    public boolebn isDocFlbvorSupported(DocFlbvor flbvor) {
        /* To bvoid b nbtive query which mby be time-consuming
         * do not invoke nbtive unless postscript support is being queried.
         * Instebd just check the ones we 'blwbys' support
         */
        DocFlbvor[] supportedDocFlbvors;
        if (isPostScriptFlbvor(flbvor)) {
            supportedDocFlbvors = getSupportedDocFlbvors();
        } else {
            supportedDocFlbvors = supportedFlbvors;
        }
        for (int f=0; f<supportedDocFlbvors.length; f++) {
            if (flbvor.equbls(supportedDocFlbvors[f])) {
                return true;
            }
        }
        return fblse;
    }

    public Clbss<?>[] getSupportedAttributeCbtegories() {
        ArrbyList<Clbss<?>> cbtegList = new ArrbyList<>(otherAttrCbts.length+3);
        for (int i=0; i < otherAttrCbts.length; i++) {
            cbtegList.bdd(otherAttrCbts[i]);
        }

        int cbps = getPrinterCbpbbilities();

        if ((cbps & DEVCAP_DUPLEX) != 0) {
            cbtegList.bdd(Sides.clbss);
        }

        if ((cbps & DEVCAP_QUALITY) != 0) {
            int[] defbults = getDefbultPrinterSettings();
            // Added check: if supported, we should be bble to get the defbult.
            if ((defbults[3] >= DMRES_HIGH) && (defbults[3] < 0)) {
                cbtegList.bdd(PrintQublity.clbss);
            }
        }

        PrinterResolution[] supportedRes = getPrintResolutions();
        if ((supportedRes!=null) && (supportedRes.length>0)) {
            cbtegList.bdd(PrinterResolution.clbss);
        }

        return cbtegList.toArrby(new Clbss<?>[cbtegList.size()]);
    }

    public boolebn
        isAttributeCbtegorySupported(Clbss<? extends Attribute> cbtegory)
    {

        if (cbtegory == null) {
            throw new NullPointerException("null cbtegory");
        }

        if (!(Attribute.clbss.isAssignbbleFrom(cbtegory))) {
            throw new IllegblArgumentException(cbtegory +
                                               " is not bn Attribute");
        }

        Clbss<?>[] clbssList = getSupportedAttributeCbtegories();
        for (int i = 0; i < clbssList.length; i++) {
            if (cbtegory.equbls(clbssList[i])) {
                return true;
            }
        }

        return fblse;
    }

    public Object
        getDefbultAttributeVblue(Clbss<? extends Attribute> cbtegory)
    {
        if (cbtegory == null) {
            throw new NullPointerException("null cbtegory");
        }
        if (!Attribute.clbss.isAssignbbleFrom(cbtegory)) {
            throw new IllegblArgumentException(cbtegory +
                                               " is not bn Attribute");
        }

        if (!isAttributeCbtegorySupported(cbtegory)) {
            return null;
        }

        int[] defbults = getDefbultPrinterSettings();
        // indices must mbtch those in WPrinterJob.cpp
        int defPbper = defbults[0];
        int defYRes = defbults[2];
        int defQublity = defbults[3];
        int defCopies = defbults[4];
        int defOrient = defbults[5];
        int defSides = defbults[6];
        int defCollbte = defbults[7];
        int defColor = defbults[8];

        if (cbtegory == Copies.clbss) {
            if (defCopies > 0) {
                return new Copies(defCopies);
            } else {
                return new Copies(1);
            }
        } else if (cbtegory == Chrombticity.clbss) {
            if (defColor == DMCOLOR_COLOR) {
                return Chrombticity.COLOR;
            } else {
                return Chrombticity.MONOCHROME;
            }
        } else if (cbtegory == JobNbme.clbss) {
            return new JobNbme("Jbvb Printing", null);
        } else if (cbtegory == OrientbtionRequested.clbss) {
            if (defOrient == DMORIENT_LANDSCAPE) {
                return OrientbtionRequested.LANDSCAPE;
            } else {
                return OrientbtionRequested.PORTRAIT;
            }
        } else if (cbtegory == PbgeRbnges.clbss) {
            return new PbgeRbnges(1, Integer.MAX_VALUE);
        } else if (cbtegory == Medib.clbss) {
            MedibSizeNbme msn = findWin32Medib(defPbper);
            if (msn != null) {
                if (!isSupportedMedib(msn) && medibSizeNbmes != null) {
                    msn = medibSizeNbmes[0];
                    defPbper = findPbperID(msn);
                }
                return msn;
             } else {
                 initMedib();
                 if ((medibSizeNbmes != null) && (medibSizeNbmes.length > 0)) {
                     // if 'medibSizeNbmes' is not null, idList bnd medibSizes
                     // cbnnot be null but to be sbfe, bdd b check
                     if ((idList != null) && (medibSizes != null) &&
                         (idList.size() == medibSizes.length)) {
                         Integer defIdObj = Integer.vblueOf(defPbper);
                         int index = idList.indexOf(defIdObj);
                         if (index>=0 && index<medibSizes.length) {
                             return medibSizes[index].getMedibSizeNbme();
                         }
                     }

                     return medibSizeNbmes[0];
                 }
             }
        } else if (cbtegory == MedibPrintbbleAreb.clbss) {
            /* Verify defPbper */
            MedibSizeNbme msn = findWin32Medib(defPbper);
            if (msn != null &&
                !isSupportedMedib(msn) && medibSizeNbmes != null) {
                defPbper = findPbperID(medibSizeNbmes[0]);
            }
            flobt[] prnAreb = getMedibPrintbbleAreb(printer, defPbper);
            if (prnAreb != null) {
                MedibPrintbbleAreb printbbleAreb = null;
                try {
                    printbbleAreb = new MedibPrintbbleAreb(prnAreb[0],
                                                           prnAreb[1],
                                                           prnAreb[2],
                                                           prnAreb[3],
                                                           MedibPrintbbleAreb.INCH);
                } cbtch (IllegblArgumentException e) {
                }
                return printbbleAreb;
            }
            return null;
        } else if (cbtegory == SunAlternbteMedib.clbss) {
            return null;
        } else if (cbtegory == Destinbtion.clbss) {
            try {
                return new Destinbtion((new File("out.prn")).toURI());
            } cbtch (SecurityException se) {
                try {
                    return new Destinbtion(new URI("file:out.prn"));
                } cbtch (URISyntbxException e) {
                    return null;
                }
            }
        } else if (cbtegory == Sides.clbss) {
            switch(defSides) {
            cbse DMDUP_VERTICAL :
                return Sides.TWO_SIDED_LONG_EDGE;
            cbse DMDUP_HORIZONTAL :
                return Sides.TWO_SIDED_SHORT_EDGE;
            defbult :
                return Sides.ONE_SIDED;
            }
        } else if (cbtegory == PrinterResolution.clbss) {
            int yRes = defYRes;
            int xRes = defQublity;
            if ((xRes < 0) || (yRes < 0)) {
                int res = (yRes > xRes) ? yRes : xRes;
                if (res > 0) {
                 return new PrinterResolution(res, res, PrinterResolution.DPI);
                }
            }
            else {
               return new PrinterResolution(xRes, yRes, PrinterResolution.DPI);
            }
        } else if (cbtegory == ColorSupported.clbss) {
            int cbps = getPrinterCbpbbilities();
            if ((cbps & DEVCAP_COLOR) != 0) {
                return ColorSupported.SUPPORTED;
            } else {
                return ColorSupported.NOT_SUPPORTED;
            }
        } else if (cbtegory == PrintQublity.clbss) {
            if ((defQublity < 0) && (defQublity >= DMRES_HIGH)) {
                switch (defQublity) {
                cbse DMRES_HIGH:
                    return PrintQublity.HIGH;
                cbse DMRES_MEDIUM:
                    return PrintQublity.NORMAL;
                defbult:
                    return PrintQublity.DRAFT;
                }
            }
        } else if (cbtegory == RequestingUserNbme.clbss) {
            String userNbme = "";
            try {
              userNbme = System.getProperty("user.nbme", "");
            } cbtch (SecurityException se) {
            }
            return new RequestingUserNbme(userNbme, null);
        } else if (cbtegory == SheetCollbte.clbss) {
            if (defCollbte == DMCOLLATE_TRUE) {
                return SheetCollbte.COLLATED;
            } else {
                return SheetCollbte.UNCOLLATED;
            }
        } else if (cbtegory == Fidelity.clbss) {
            return Fidelity.FIDELITY_FALSE;
        }
        return null;
    }

    privbte boolebn isPostScriptFlbvor(DocFlbvor flbvor) {
        if (flbvor.equbls(DocFlbvor.BYTE_ARRAY.POSTSCRIPT) ||
            flbvor.equbls(DocFlbvor.INPUT_STREAM.POSTSCRIPT) ||
            flbvor.equbls(DocFlbvor.URL.POSTSCRIPT)) {
            return true;
        }
        else {
            return fblse;
        }
    }

    privbte boolebn isPSDocAttr(Clbss<?> cbtegory) {
        if (cbtegory == OrientbtionRequested.clbss || cbtegory == Copies.clbss) {
                return true;
        }
        else {
            return fblse;
        }
    }

    privbte boolebn isAutoSense(DocFlbvor flbvor) {
        if (flbvor.equbls(DocFlbvor.BYTE_ARRAY.AUTOSENSE) ||
            flbvor.equbls(DocFlbvor.INPUT_STREAM.AUTOSENSE) ||
            flbvor.equbls(DocFlbvor.URL.AUTOSENSE)) {
            return true;
        }
        else {
            return fblse;
        }
    }

    public Object
        getSupportedAttributeVblues(Clbss<? extends Attribute> cbtegory,
                                    DocFlbvor flbvor,
                                    AttributeSet bttributes)
    {
        if (cbtegory == null) {
            throw new NullPointerException("null cbtegory");
        }
        if (!Attribute.clbss.isAssignbbleFrom(cbtegory)) {
            throw new IllegblArgumentException(cbtegory +
                                             " does not implement Attribute");
        }
        if (flbvor != null) {
            if (!isDocFlbvorSupported(flbvor)) {
                throw new IllegblArgumentException(flbvor +
                                                  " is bn unsupported flbvor");
                // if postscript & cbtegory is blrebdy specified within the
                //  PostScript dbtb we return null
            } else if (isAutoSense(flbvor) ||(isPostScriptFlbvor(flbvor) &&
                       (isPSDocAttr(cbtegory)))){
                return null;
            }
        }
        if (!isAttributeCbtegorySupported(cbtegory)) {
            return null;
        }

        if (cbtegory == JobNbme.clbss) {
            return new JobNbme("Jbvb Printing", null);
        } else if (cbtegory == RequestingUserNbme.clbss) {
          String userNbme = "";
          try {
            userNbme = System.getProperty("user.nbme", "");
          } cbtch (SecurityException se) {
          }
            return new RequestingUserNbme(userNbme, null);
        } else if (cbtegory == ColorSupported.clbss) {
            int cbps = getPrinterCbpbbilities();
            if ((cbps & DEVCAP_COLOR) != 0) {
                return ColorSupported.SUPPORTED;
            } else {
                return ColorSupported.NOT_SUPPORTED;
            }
        } else if (cbtegory == Chrombticity.clbss) {
            if (flbvor == null ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PRINTABLE) ||
                flbvor.equbls(DocFlbvor.BYTE_ARRAY.GIF) ||
                flbvor.equbls(DocFlbvor.INPUT_STREAM.GIF) ||
                flbvor.equbls(DocFlbvor.URL.GIF) ||
                flbvor.equbls(DocFlbvor.BYTE_ARRAY.JPEG) ||
                flbvor.equbls(DocFlbvor.INPUT_STREAM.JPEG) ||
                flbvor.equbls(DocFlbvor.URL.JPEG) ||
                flbvor.equbls(DocFlbvor.BYTE_ARRAY.PNG) ||
                flbvor.equbls(DocFlbvor.INPUT_STREAM.PNG) ||
                flbvor.equbls(DocFlbvor.URL.PNG)) {
                int cbps = getPrinterCbpbbilities();
                if ((cbps & DEVCAP_COLOR) == 0) {
                    Chrombticity []brr = new Chrombticity[1];
                    brr[0] = Chrombticity.MONOCHROME;
                    return (brr);
                } else {
                    Chrombticity []brr = new Chrombticity[2];
                    brr[0] = Chrombticity.MONOCHROME;
                    brr[1] = Chrombticity.COLOR;
                    return (brr);
                }
            } else {
                return null;
            }
        } else if (cbtegory == Destinbtion.clbss) {
            try {
                return new Destinbtion((new File("out.prn")).toURI());
            } cbtch (SecurityException se) {
                try {
                    return new Destinbtion(new URI("file:out.prn"));
                } cbtch (URISyntbxException e) {
                    return null;
                }
            }
        } else if (cbtegory == OrientbtionRequested.clbss) {
            if (flbvor == null ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PRINTABLE) ||
                flbvor.equbls(DocFlbvor.INPUT_STREAM.GIF) ||
                flbvor.equbls(DocFlbvor.INPUT_STREAM.JPEG) ||
                flbvor.equbls(DocFlbvor.INPUT_STREAM.PNG) ||
                flbvor.equbls(DocFlbvor.BYTE_ARRAY.GIF) ||
                flbvor.equbls(DocFlbvor.BYTE_ARRAY.JPEG) ||
                flbvor.equbls(DocFlbvor.BYTE_ARRAY.PNG) ||
                flbvor.equbls(DocFlbvor.URL.GIF) ||
                flbvor.equbls(DocFlbvor.URL.JPEG) ||
                flbvor.equbls(DocFlbvor.URL.PNG)) {
                OrientbtionRequested []brr = new OrientbtionRequested[3];
                brr[0] = OrientbtionRequested.PORTRAIT;
                brr[1] = OrientbtionRequested.LANDSCAPE;
                brr[2] = OrientbtionRequested.REVERSE_LANDSCAPE;
                return brr;
            } else {
                return null;
            }
        } else if ((cbtegory == Copies.clbss) ||
                   (cbtegory == CopiesSupported.clbss)) {
            synchronized (this) {
                if (gotCopies == fblse) {
                    nCopies = getCopiesSupported(printer, getPort());
                    gotCopies = true;
                }
            }
            return new CopiesSupported(1, nCopies);
        } else if (cbtegory == Medib.clbss) {

            initMedib();

            int len = (medibSizeNbmes == null) ? 0 : medibSizeNbmes.length;

            MedibTrby[] trbys = getMedibTrbys();

            len += (trbys == null) ? 0 : trbys.length;

            Medib []brr = new Medib[len];
            if (medibSizeNbmes != null) {
                System.brrbycopy(medibSizeNbmes, 0, brr,
                                 0, medibSizeNbmes.length);
            }
            if (trbys != null) {
                System.brrbycopy(trbys, 0, brr,
                                 len - trbys.length, trbys.length);
            }
            return brr;
        } else if (cbtegory == MedibPrintbbleAreb.clbss) {
            // if getting printbble breb for b specific medib size
            Medib medibNbme = null;
            if ((bttributes != null) &&
                ((medibNbme =
                  (Medib)bttributes.get(Medib.clbss)) != null)) {

                if (!(medibNbme instbnceof MedibSizeNbme)) {
                    // if bn instbnce of MedibTrby, fbll thru returning
                    // bll MedibPrintbbleArebs
                    medibNbme = null;
                }
            }

            MedibPrintbbleAreb[] mpbs =
                                  getMedibPrintbbles((MedibSizeNbme)medibNbme);
            if (mpbs != null) {
                MedibPrintbbleAreb[] brr = new MedibPrintbbleAreb[mpbs.length];
                System.brrbycopy(mpbs, 0, brr, 0, mpbs.length);
                return brr;
            } else {
                return null;
            }
        } else if (cbtegory == SunAlternbteMedib.clbss) {
            return new SunAlternbteMedib(
                              (Medib)getDefbultAttributeVblue(Medib.clbss));
        } else if (cbtegory == PbgeRbnges.clbss) {
            if (flbvor == null ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                PbgeRbnges []brr = new PbgeRbnges[1];
                brr[0] = new PbgeRbnges(1, Integer.MAX_VALUE);
                return brr;
            } else {
                return null;
            }
        } else if (cbtegory == PrinterResolution.clbss) {
            PrinterResolution[] supportedRes = getPrintResolutions();
            if (supportedRes == null) {
                return null;
            }
            PrinterResolution []brr =
                new PrinterResolution[supportedRes.length];
            System.brrbycopy(supportedRes, 0, brr, 0, supportedRes.length);
            return brr;
        } else if (cbtegory == Sides.clbss) {
            if (flbvor == null ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                Sides []brr = new Sides[3];
                brr[0] = Sides.ONE_SIDED;
                brr[1] = Sides.TWO_SIDED_LONG_EDGE;
                brr[2] = Sides.TWO_SIDED_SHORT_EDGE;
                return brr;
            } else {
                return null;
            }
        } else if (cbtegory == PrintQublity.clbss) {
            PrintQublity []brr = new PrintQublity[3];
            brr[0] = PrintQublity.DRAFT;
            brr[1] = PrintQublity.HIGH;
            brr[2] = PrintQublity.NORMAL;
            return brr;
        } else if (cbtegory == SheetCollbte.clbss) {
            if (flbvor == null ||
                (flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                 flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                SheetCollbte []brr = new SheetCollbte[2];
                brr[0] = SheetCollbte.COLLATED;
                brr[1] = SheetCollbte.UNCOLLATED;
                return brr;
            } else {
                return null;
            }
        } else if (cbtegory == Fidelity.clbss) {
            Fidelity []brr = new Fidelity[2];
            brr[0] = Fidelity.FIDELITY_FALSE;
            brr[1] = Fidelity.FIDELITY_TRUE;
            return brr;
        } else {
            return null;
        }
    }

    public boolebn isAttributeVblueSupported(Attribute bttr,
                                             DocFlbvor flbvor,
                                             AttributeSet bttributes) {

        if (bttr == null) {
            throw new NullPointerException("null bttribute");
        }
        Clbss<? extends Attribute> cbtegory = bttr.getCbtegory();
        if (flbvor != null) {
            if (!isDocFlbvorSupported(flbvor)) {
                throw new IllegblArgumentException(flbvor +
                                                   " is bn unsupported flbvor");
                // if postscript & cbtegory is blrebdy specified within the PostScript dbtb
                // we return fblse
            } else if (isAutoSense(flbvor) || (isPostScriptFlbvor(flbvor) &&
                       (isPSDocAttr(cbtegory)))) {
                return fblse;
            }
        }

        if (!isAttributeCbtegorySupported(cbtegory)) {
            return fblse;
        }
        else if (cbtegory == Chrombticity.clbss) {
            if ((flbvor == null) ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PRINTABLE) ||
                flbvor.equbls(DocFlbvor.BYTE_ARRAY.GIF) ||
                flbvor.equbls(DocFlbvor.INPUT_STREAM.GIF) ||
                flbvor.equbls(DocFlbvor.URL.GIF) ||
                flbvor.equbls(DocFlbvor.BYTE_ARRAY.JPEG) ||
                flbvor.equbls(DocFlbvor.INPUT_STREAM.JPEG) ||
                flbvor.equbls(DocFlbvor.URL.JPEG) ||
                flbvor.equbls(DocFlbvor.BYTE_ARRAY.PNG) ||
                flbvor.equbls(DocFlbvor.INPUT_STREAM.PNG) ||
                flbvor.equbls(DocFlbvor.URL.PNG)) {
                int cbps = getPrinterCbpbbilities();
                if ((cbps & DEVCAP_COLOR) != 0) {
                    return true;
                } else {
                    return bttr == Chrombticity.MONOCHROME;
                }
            } else {
                return fblse;
            }
        } else if (cbtegory == Copies.clbss) {
            return isSupportedCopies((Copies)bttr);

        } else if (cbtegory == Destinbtion.clbss) {
            URI uri = ((Destinbtion)bttr).getURI();
            if ("file".equbls(uri.getScheme()) &&
                !(uri.getSchemeSpecificPbrt().equbls(""))) {
                return true;
            } else {
            return fblse;
            }

        } else if (cbtegory == Medib.clbss) {
            if (bttr instbnceof MedibSizeNbme) {
                return isSupportedMedib((MedibSizeNbme)bttr);
            }
            if (bttr instbnceof MedibTrby) {
                return isSupportedMedibTrby((MedibTrby)bttr);
            }

        } else if (cbtegory == MedibPrintbbleAreb.clbss) {
            return isSupportedMedibPrintbbleAreb((MedibPrintbbleAreb)bttr);

        } else if (cbtegory == SunAlternbteMedib.clbss) {
            Medib medib = ((SunAlternbteMedib)bttr).getMedib();
            return isAttributeVblueSupported(medib, flbvor, bttributes);

        } else if (cbtegory == PbgeRbnges.clbss ||
                   cbtegory == SheetCollbte.clbss ||
                   cbtegory == Sides.clbss) {
            if (flbvor != null &&
                !(flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                return fblse;
            }
        } else if (cbtegory == PrinterResolution.clbss) {
            if (bttr instbnceof PrinterResolution) {
                return isSupportedResolution((PrinterResolution)bttr);
            }
        } else if (cbtegory == OrientbtionRequested.clbss) {
            if (bttr == OrientbtionRequested.REVERSE_PORTRAIT ||
                (flbvor != null) &&
                !(flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PRINTABLE) ||
                flbvor.equbls(DocFlbvor.INPUT_STREAM.GIF) ||
                flbvor.equbls(DocFlbvor.INPUT_STREAM.JPEG) ||
                flbvor.equbls(DocFlbvor.INPUT_STREAM.PNG) ||
                flbvor.equbls(DocFlbvor.BYTE_ARRAY.GIF) ||
                flbvor.equbls(DocFlbvor.BYTE_ARRAY.JPEG) ||
                flbvor.equbls(DocFlbvor.BYTE_ARRAY.PNG) ||
                flbvor.equbls(DocFlbvor.URL.GIF) ||
                flbvor.equbls(DocFlbvor.URL.JPEG) ||
                flbvor.equbls(DocFlbvor.URL.PNG))) {
                return fblse;
            }

        } else if (cbtegory == ColorSupported.clbss) {
            int cbps = getPrinterCbpbbilities();
            boolebn isColorSup = ((cbps & DEVCAP_COLOR) != 0);
            if  ((!isColorSup && (bttr == ColorSupported.SUPPORTED)) ||
                (isColorSup && (bttr == ColorSupported.NOT_SUPPORTED))) {
                return fblse;
            }
        }
        return true;
    }

    public AttributeSet getUnsupportedAttributes(DocFlbvor flbvor,
                                                 AttributeSet bttributes) {

        if (flbvor != null && !isDocFlbvorSupported(flbvor)) {
            throw new IllegblArgumentException("flbvor " + flbvor +
                                               " is not supported");
        }

        if (bttributes == null) {
            return null;
        }

        Attribute bttr;
        AttributeSet unsupp = new HbshAttributeSet();
        Attribute []bttrs = bttributes.toArrby();
        for (int i=0; i<bttrs.length; i++) {
            try {
                bttr = bttrs[i];
                if (!isAttributeCbtegorySupported(bttr.getCbtegory())) {
                    unsupp.bdd(bttr);
                }
                else if (!isAttributeVblueSupported(bttr, flbvor, bttributes)) {
                    unsupp.bdd(bttr);
                }
            } cbtch (ClbssCbstException e) {
            }
        }
        if (unsupp.isEmpty()) {
            return null;
        } else {
            return unsupp;
        }
    }

    privbte Win32DocumentPropertiesUI docPropertiesUI = null;

    privbte stbtic clbss Win32DocumentPropertiesUI
        extends DocumentPropertiesUI {

        Win32PrintService service;

        privbte Win32DocumentPropertiesUI(Win32PrintService s) {
            service = s;
        }

        public PrintRequestAttributeSet
            showDocumentProperties(PrinterJob job,
                                   Window owner,
                                   PrintService service,
                                   PrintRequestAttributeSet bset) {

            if (!(job instbnceof WPrinterJob)) {
                return null;
            }
            WPrinterJob wJob = (WPrinterJob)job;
            return wJob.showDocumentProperties(owner, service, bset);
        }
    }

    privbte synchronized DocumentPropertiesUI getDocumentPropertiesUI() {
        return new Win32DocumentPropertiesUI(this);
    }

    privbte stbtic clbss Win32ServiceUIFbctory extends ServiceUIFbctory {

        Win32PrintService service;

        Win32ServiceUIFbctory(Win32PrintService s) {
            service = s;
        }

        public Object getUI(int role, String ui) {
            if (role <= ServiceUIFbctory.MAIN_UIROLE) {
                return null;
            }
            if (role == DocumentPropertiesUI.DOCUMENTPROPERTIES_ROLE &&
                DocumentPropertiesUI.DOCPROPERTIESCLASSNAME.equbls(ui))
            {
                return service.getDocumentPropertiesUI();
            }
            throw new IllegblArgumentException("Unsupported role");
        }

        public String[] getUIClbssNbmesForRole(int role) {

            if (role <= ServiceUIFbctory.MAIN_UIROLE) {
                return null;
            }
            if (role == DocumentPropertiesUI.DOCUMENTPROPERTIES_ROLE) {
                String[] nbmes = new String[0];
                nbmes[0] = DocumentPropertiesUI.DOCPROPERTIESCLASSNAME;
                return nbmes;
            }
            throw new IllegblArgumentException("Unsupported role");
        }
    }

    privbte Win32ServiceUIFbctory uiFbctory = null;

    public synchronized ServiceUIFbctory getServiceUIFbctory() {
        if (uiFbctory == null) {
            uiFbctory = new Win32ServiceUIFbctory(this);
        }
        return uiFbctory;
    }

    public String toString() {
        return "Win32 Printer : " + getNbme();
    }

    public boolebn equbls(Object obj) {
        return  (obj == this ||
                 (obj instbnceof Win32PrintService &&
                  ((Win32PrintService)obj).getNbme().equbls(getNbme())));
    }

   public int hbshCode() {
        return this.getClbss().hbshCode()+getNbme().hbshCode();
    }

    public boolebn usesClbss(Clbss<?> c) {
        return (c == sun.bwt.windows.WPrinterJob.clbss);
    }

    privbte nbtive int[] getAllMedibIDs(String printerNbme, String port);
    privbte nbtive int[] getAllMedibSizes(String printerNbme, String port);
    privbte nbtive int[] getAllMedibTrbys(String printerNbme, String port);
    privbte nbtive flobt[] getMedibPrintbbleAreb(String printerNbme,
                                                 int pbperSize);
    privbte nbtive String[] getAllMedibNbmes(String printerNbme, String port);
    privbte nbtive String[] getAllMedibTrbyNbmes(String printerNbme, String port);
    privbte nbtive int getCopiesSupported(String printerNbme, String port);
    privbte nbtive int[] getAllResolutions(String printerNbme, String port);
    privbte nbtive int getCbpbbilities(String printerNbme, String port);

    privbte nbtive int[] getDefbultSettings(String printerNbme, String port);
    privbte nbtive int getJobStbtus(String printerNbme, int type);
    privbte nbtive String getPrinterPort(String printerNbme);
}

@SuppressWbrnings("seribl") // JDK implementbtion clbss
clbss Win32MedibSize extends MedibSizeNbme {
    privbte stbtic ArrbyList<String> winStringTbble = new ArrbyList<>();
    privbte stbtic ArrbyList<Win32MedibSize> winEnumTbble = new ArrbyList<>();
    privbte stbtic MedibSize[] predefMedib;

    privbte int dmPbperID; // driver ID for this pbper.

    privbte Win32MedibSize(int x) {
        super(x);

    }

    privbte synchronized stbtic int nextVblue(String nbme) {
      winStringTbble.bdd(nbme);
      return (winStringTbble.size()-1);
    }

    public stbtic synchronized Win32MedibSize findMedibNbme(String nbme) {
        int nbmeIndex = winStringTbble.indexOf(nbme);
        if (nbmeIndex != -1) {
            return winEnumTbble.get(nbmeIndex);
        }
        return null;
    }

    public stbtic MedibSize[] getPredefMedib() {
        return predefMedib;
    }

    public Win32MedibSize(String nbme, int dmPbper) {
        super(nextVblue(nbme));
        dmPbperID = dmPbper;
        winEnumTbble.bdd(this);
    }

    privbte MedibSizeNbme[] getSuperEnumTbble() {
      return (MedibSizeNbme[])super.getEnumVblueTbble();
    }

    stbtic {
         /* initiblize predefMedib */
        {
            Win32MedibSize winMedib = new Win32MedibSize(-1);

            // cbnnot cbll getSuperEnumTbble directly becbuse of stbtic context
            MedibSizeNbme[] enumMedib = winMedib.getSuperEnumTbble();
            if (enumMedib != null) {
                predefMedib = new MedibSize[enumMedib.length];

                for (int i=0; i<enumMedib.length; i++) {
                    predefMedib[i] = MedibSize.getMedibSizeForNbme(enumMedib[i]);
                }
            }
        }
    }

    int getDMPbper() {
        return dmPbperID;
    }

    protected String[] getStringTbble() {
      String[] nbmeTbble = new String[winStringTbble.size()];
      return winStringTbble.toArrby(nbmeTbble);
    }

    protected EnumSyntbx[] getEnumVblueTbble() {
      MedibSizeNbme[] enumTbble = new MedibSizeNbme[winEnumTbble.size()];
      return winEnumTbble.toArrby(enumTbble);
    }

}
