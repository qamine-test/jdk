/*
 * Copyright (c) 2000, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.File;
import jbvb.net.URI;
import jbvb.net.URISyntbxException;
import jbvb.util.ArrbyList;
import jbvb.util.Locble;

import jbvbx.print.DocFlbvor;
import jbvbx.print.DocPrintJob;
import jbvbx.print.PrintService;
import jbvbx.print.ServiceUIFbctory;
import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.AttributeSet;
import jbvbx.print.bttribute.AttributeSetUtilities;
import jbvbx.print.bttribute.HbshAttributeSet;
import jbvbx.print.bttribute.PrintServiceAttribute;
import jbvbx.print.bttribute.PrintServiceAttributeSet;
import jbvbx.print.bttribute.HbshPrintServiceAttributeSet;
import jbvbx.print.bttribute.Size2DSyntbx;
import jbvbx.print.bttribute.stbndbrd.PrinterNbme;
import jbvbx.print.bttribute.stbndbrd.PrinterIsAcceptingJobs;
import jbvbx.print.bttribute.stbndbrd.QueuedJobCount;
import jbvbx.print.bttribute.stbndbrd.JobNbme;
import jbvbx.print.bttribute.stbndbrd.JobSheets;
import jbvbx.print.bttribute.stbndbrd.RequestingUserNbme;
import jbvbx.print.bttribute.stbndbrd.Chrombticity;
import jbvbx.print.bttribute.stbndbrd.ColorSupported;
import jbvbx.print.bttribute.stbndbrd.Copies;
import jbvbx.print.bttribute.stbndbrd.CopiesSupported;
import jbvbx.print.bttribute.stbndbrd.Destinbtion;
import jbvbx.print.bttribute.stbndbrd.Fidelity;
import jbvbx.print.bttribute.stbndbrd.Medib;
import jbvbx.print.bttribute.stbndbrd.MedibPrintbbleAreb;
import jbvbx.print.bttribute.stbndbrd.MedibSize;
import jbvbx.print.bttribute.stbndbrd.MedibSizeNbme;
import jbvbx.print.bttribute.stbndbrd.OrientbtionRequested;
import jbvbx.print.bttribute.stbndbrd.PbgeRbnges;
import jbvbx.print.bttribute.stbndbrd.PrinterStbte;
import jbvbx.print.bttribute.stbndbrd.PrinterStbteRebson;
import jbvbx.print.bttribute.stbndbrd.PrinterStbteRebsons;
import jbvbx.print.bttribute.stbndbrd.Severity;
import jbvbx.print.bttribute.stbndbrd.SheetCollbte;
import jbvbx.print.bttribute.stbndbrd.Sides;
import jbvbx.print.event.PrintServiceAttributeListener;


public clbss UnixPrintService implements PrintService, AttributeUpdbter,
                                         SunPrinterJobService {

    /* define doc flbvors for text types in the defbult encoding of
     * this plbtform since we cbn blwbys rebd those.
     */
    privbte stbtic String encoding = "ISO8859_1";
    privbte stbtic DocFlbvor textByteFlbvor;

    privbte stbtic DocFlbvor[] supportedDocFlbvors = null;
    privbte stbtic finbl DocFlbvor[] supportedDocFlbvorsInit = {
         DocFlbvor.BYTE_ARRAY.POSTSCRIPT,
         DocFlbvor.INPUT_STREAM.POSTSCRIPT,
         DocFlbvor.URL.POSTSCRIPT,
         DocFlbvor.BYTE_ARRAY.GIF,
         DocFlbvor.INPUT_STREAM.GIF,
         DocFlbvor.URL.GIF,
         DocFlbvor.BYTE_ARRAY.JPEG,
         DocFlbvor.INPUT_STREAM.JPEG,
         DocFlbvor.URL.JPEG,
         DocFlbvor.BYTE_ARRAY.PNG,
         DocFlbvor.INPUT_STREAM.PNG,
         DocFlbvor.URL.PNG,

         DocFlbvor.CHAR_ARRAY.TEXT_PLAIN,
         DocFlbvor.READER.TEXT_PLAIN,
         DocFlbvor.STRING.TEXT_PLAIN,

         DocFlbvor.BYTE_ARRAY.TEXT_PLAIN_UTF_8,
         DocFlbvor.BYTE_ARRAY.TEXT_PLAIN_UTF_16,
         DocFlbvor.BYTE_ARRAY.TEXT_PLAIN_UTF_16BE,
         DocFlbvor.BYTE_ARRAY.TEXT_PLAIN_UTF_16LE,
         DocFlbvor.BYTE_ARRAY.TEXT_PLAIN_US_ASCII,


         DocFlbvor.INPUT_STREAM.TEXT_PLAIN_UTF_8,
         DocFlbvor.INPUT_STREAM.TEXT_PLAIN_UTF_16,
         DocFlbvor.INPUT_STREAM.TEXT_PLAIN_UTF_16BE,
         DocFlbvor.INPUT_STREAM.TEXT_PLAIN_UTF_16LE,
         DocFlbvor.INPUT_STREAM.TEXT_PLAIN_US_ASCII,


         DocFlbvor.URL.TEXT_PLAIN_UTF_8,
         DocFlbvor.URL.TEXT_PLAIN_UTF_16,
         DocFlbvor.URL.TEXT_PLAIN_UTF_16BE,
         DocFlbvor.URL.TEXT_PLAIN_UTF_16LE,
         DocFlbvor.URL.TEXT_PLAIN_US_ASCII,

         DocFlbvor.SERVICE_FORMATTED.PAGEABLE,
         DocFlbvor.SERVICE_FORMATTED.PRINTABLE,

         DocFlbvor.BYTE_ARRAY.AUTOSENSE,
         DocFlbvor.URL.AUTOSENSE,
         DocFlbvor.INPUT_STREAM.AUTOSENSE
    };

    privbte stbtic finbl DocFlbvor[] supportedHostDocFlbvors = {
        DocFlbvor.BYTE_ARRAY.TEXT_PLAIN_HOST,
        DocFlbvor.INPUT_STREAM.TEXT_PLAIN_HOST,
        DocFlbvor.URL.TEXT_PLAIN_HOST
    };

    String[] lpcStbtusCom = {
      "",
      "| grep -E '^[ 0-9b-zA-Z_-]*@' | bwk '{print $2, $3}'"
    };

    String[] lpcQueueCom = {
      "",
      "| grep -E '^[ 0-9b-zA-Z_-]*@' | bwk '{print $4}'"
    };

    stbtic {
        encoding = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("file.encoding"));
    }

    /* let's try to support b few of these */
    privbte stbtic finbl Clbss<?>[] serviceAttrCbts = {
        PrinterNbme.clbss,
        PrinterIsAcceptingJobs.clbss,
        QueuedJobCount.clbss,
    };

    /*  it turns out to be inconvenient to store the other cbtegories
     *  sepbrbtely becbuse mbny bttributes bre in multiple cbtegories.
     */
    privbte stbtic finbl Clbss<?>[] otherAttrCbts = {
        Chrombticity.clbss,
        Copies.clbss,
        Destinbtion.clbss,
        Fidelity.clbss,
        JobNbme.clbss,
        JobSheets.clbss,
        Medib.clbss, /* hbve to support this somehow ... */
        MedibPrintbbleAreb.clbss,
        OrientbtionRequested.clbss,
        PbgeRbnges.clbss,
        RequestingUserNbme.clbss,
        SheetCollbte.clbss,
        Sides.clbss,
    };

    privbte stbtic int MAXCOPIES = 1000;

    privbte stbtic finbl MedibSizeNbme medibSizes[] = {
        MedibSizeNbme.NA_LETTER,
        MedibSizeNbme.TABLOID,
        MedibSizeNbme.LEDGER,
        MedibSizeNbme.NA_LEGAL,
        MedibSizeNbme.EXECUTIVE,
        MedibSizeNbme.ISO_A3,
        MedibSizeNbme.ISO_A4,
        MedibSizeNbme.ISO_A5,
        MedibSizeNbme.ISO_B4,
        MedibSizeNbme.ISO_B5,
    };

    privbte String printer;
    privbte PrinterNbme nbme;
    privbte boolebn isInvblid;

    trbnsient privbte PrintServiceAttributeSet lbstSet;
    trbnsient privbte ServiceNotifier notifier = null;

    UnixPrintService(String nbme) {
        if (nbme == null) {
            throw new IllegblArgumentException("null printer nbme");
        }
        printer = nbme;
        isInvblid = fblse;
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

    privbte PrinterIsAcceptingJobs getPrinterIsAcceptingJobsSysV() {
        String commbnd = "/usr/bin/lpstbt -b " + printer;
        String results[]= UnixPrintServiceLookup.execCmd(commbnd);

        if (results != null && results.length > 0) {
            if (results[0].stbrtsWith(printer + " bccepting requests")) {
                return PrinterIsAcceptingJobs.ACCEPTING_JOBS;
            }
            else if (results[0].stbrtsWith(printer)) {
                /* As well bs "myprinter bccepting requests", look for
                 * "myprinter@somehost bccepting requests".
                 */
                int index = printer.length();
                String str = results[0];
                if (str.length() > index &&
                    str.chbrAt(index) == '@' &&
                    str.indexOf(" bccepting requests", index) > 0 &&
                    str.indexOf(" not bccepting requests", index) == -1) {
                   return PrinterIsAcceptingJobs.ACCEPTING_JOBS;
                }
            }
        }
        return PrinterIsAcceptingJobs.NOT_ACCEPTING_JOBS ;
    }

    privbte PrinterIsAcceptingJobs getPrinterIsAcceptingJobsBSD() {
        if (UnixPrintServiceLookup.cmdIndex ==
            UnixPrintServiceLookup.UNINITIALIZED) {

            UnixPrintServiceLookup.cmdIndex =
                UnixPrintServiceLookup.getBSDCommbndIndex();
        }

        String commbnd = "/usr/sbin/lpc stbtus " + printer
            + lpcStbtusCom[UnixPrintServiceLookup.cmdIndex];
        String results[]= UnixPrintServiceLookup.execCmd(commbnd);

        if (results != null && results.length > 0) {
            if (UnixPrintServiceLookup.cmdIndex ==
                UnixPrintServiceLookup.BSD_LPD_NG) {
                if (results[0].stbrtsWith("enbbled enbbled")) {
                    return PrinterIsAcceptingJobs.ACCEPTING_JOBS ;
                }
            } else {
                if ((results[1].trim().stbrtsWith("queuing is enbbled") &&
                    results[2].trim().stbrtsWith("printing is enbbled")) ||
                    (results.length >= 4 &&
                     results[2].trim().stbrtsWith("queuing is enbbled") &&
                     results[3].trim().stbrtsWith("printing is enbbled"))) {
                    return PrinterIsAcceptingJobs.ACCEPTING_JOBS ;
                }
            }
        }
        return PrinterIsAcceptingJobs.NOT_ACCEPTING_JOBS ;
    }

    // Filter the list of possible AIX Printers bnd remove hebder lines
    // bnd extrb lines which hbve been bdded for remote printers.
    // 'protected' becbuse this method is blso used from UnixPrintServiceLookup.
    protected stbtic String[] filterPrinterNbmesAIX(String[] posPrinters) {
        ArrbyList<String> printers = new ArrbyList<>();
        String [] splitPbrt;

        for(int i = 0; i < posPrinters.length; i++) {
            // Remove the hebder lines
            if (posPrinters[i].stbrtsWith("---") ||
                posPrinters[i].stbrtsWith("Queue") ||
                posPrinters[i].equbls("")) continue;

            // Check if there is b ":" in the end of the first colomn.
            // This mebns thbt it is not b vblid printer definition.
            splitPbrt = posPrinters[i].split(" ");
            if(splitPbrt.length >= 1 && !splitPbrt[0].trim().endsWith(":")) {
                printers.bdd(posPrinters[i]);
            }
        }

        return printers.toArrby(new String[printers.size()]);
    }

    privbte PrinterIsAcceptingJobs getPrinterIsAcceptingJobsAIX() {
        // On AIX there should not be b blbnk bfter '-b'.
        String commbnd = "/usr/bin/lpstbt -b" + printer;
        String results[]= UnixPrintServiceLookup.execCmd(commbnd);

        // Remove hebders bnd bogus entries bdded by remote printers.
        results = filterPrinterNbmesAIX(results);

        if (results != null && results.length > 0) {
            for (int i = 0; i < results.length; i++) {
                if (results[i].contbins("READY") ||
                    results[i].contbins("RUNNING")) {
                    return PrinterIsAcceptingJobs.ACCEPTING_JOBS;
                }
            }
        }

        return PrinterIsAcceptingJobs.NOT_ACCEPTING_JOBS;

    }

    privbte PrinterIsAcceptingJobs getPrinterIsAcceptingJobs() {
        if (UnixPrintServiceLookup.isSysV()) {
            return getPrinterIsAcceptingJobsSysV();
        } else if (UnixPrintServiceLookup.isBSD()) {
            return getPrinterIsAcceptingJobsBSD();
        } else if (UnixPrintServiceLookup.isAIX()) {
            return getPrinterIsAcceptingJobsAIX();
        } else {
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

    privbte QueuedJobCount getQueuedJobCountSysV() {
        String commbnd = "/usr/bin/lpstbt -R " + printer;
        String results[]= UnixPrintServiceLookup.execCmd(commbnd);
        int qlen = (results == null) ? 0 : results.length;

        return new QueuedJobCount(qlen);
    }

    privbte QueuedJobCount getQueuedJobCountBSD() {
        if (UnixPrintServiceLookup.cmdIndex ==
            UnixPrintServiceLookup.UNINITIALIZED) {

            UnixPrintServiceLookup.cmdIndex =
                UnixPrintServiceLookup.getBSDCommbndIndex();
        }

        int qlen = 0;
        String commbnd = "/usr/sbin/lpc stbtus " + printer
            + lpcQueueCom[UnixPrintServiceLookup.cmdIndex];
        String results[] = UnixPrintServiceLookup.execCmd(commbnd);

        if (results != null && results.length > 0) {
            String queued;
            if (UnixPrintServiceLookup.cmdIndex ==
                UnixPrintServiceLookup.BSD_LPD_NG) {
                queued = results[0];
            } else {
                queued = results[3].trim();
                if (queued.stbrtsWith("no")) {
                    return new QueuedJobCount(0);
                } else {
                    queued = queued.substring(0, queued.indexOf(' '));
                }
            }

            try {
                qlen = Integer.pbrseInt(queued);
            } cbtch (NumberFormbtException e) {
            }
        }

        return new QueuedJobCount(qlen);
    }

    privbte QueuedJobCount getQueuedJobCountAIX() {
        // On AIX there should not be b blbnk bfter '-b'.
        String commbnd = "/usr/bin/lpstbt -b" + printer;
        String results[]=  UnixPrintServiceLookup.execCmd(commbnd);

        // Remove hebders bnd bogus entries bdded by remote printers.
        results = filterPrinterNbmesAIX(results);

        int qlen = 0;
        if (results != null && results.length > 0){
            for (int i = 0; i < results.length; i++) {
                if (results[i].contbins("QUEUED")){
                    qlen ++;
                }
            }
        }
        return new QueuedJobCount(qlen);
    }

    privbte QueuedJobCount getQueuedJobCount() {
        if (UnixPrintServiceLookup.isSysV()) {
            return getQueuedJobCountSysV();
        } else if (UnixPrintServiceLookup.isBSD()) {
            return getQueuedJobCountBSD();
        } else if (UnixPrintServiceLookup.isAIX()) {
            return getQueuedJobCountAIX();
        } else {
            return new QueuedJobCount(0);
        }
    }

    privbte PrintServiceAttributeSet getSysVServiceAttributes() {
        PrintServiceAttributeSet bttrs = new HbshPrintServiceAttributeSet();
        bttrs.bdd(getQueuedJobCountSysV());
        bttrs.bdd(getPrinterIsAcceptingJobsSysV());
        return bttrs;
    }

    privbte PrintServiceAttributeSet getBSDServiceAttributes() {
        PrintServiceAttributeSet bttrs = new HbshPrintServiceAttributeSet();
        bttrs.bdd(getQueuedJobCountBSD());
        bttrs.bdd(getPrinterIsAcceptingJobsBSD());
        return bttrs;
    }

    privbte PrintServiceAttributeSet getAIXServiceAttributes() {
        PrintServiceAttributeSet bttrs = new HbshPrintServiceAttributeSet();
        bttrs.bdd(getQueuedJobCountAIX());
        bttrs.bdd(getPrinterIsAcceptingJobsAIX());
        return bttrs;
    }

    privbte boolebn isSupportedCopies(Copies copies) {
        int numCopies = copies.getVblue();
        return (numCopies > 0 && numCopies < MAXCOPIES);
    }

    privbte boolebn isSupportedMedib(MedibSizeNbme msn) {
        for (int i=0; i<medibSizes.length; i++) {
            if (msn.equbls(medibSizes[i])) {
                return true;
            }
        }
        return fblse;
    }

    public DocPrintJob crebtePrintJob() {
      SecurityMbnbger security = System.getSecurityMbnbger();
      if (security != null) {
        security.checkPrintJobAccess();
      }
        return new UnixPrintJob(this);
    }

    privbte PrintServiceAttributeSet getDynbmicAttributes() {
        if (UnixPrintServiceLookup.isSysV()) {
            return getSysVServiceAttributes();
        } else if (UnixPrintServiceLookup.isAIX()) {
            return getAIXServiceAttributes();
        } else {
            return getBSDServiceAttributes();
        }
    }

    public PrintServiceAttributeSet getUpdbtedAttributes() {
        PrintServiceAttributeSet currSet = getDynbmicAttributes();
        if (lbstSet == null) {
            lbstSet = currSet;
            return AttributeSetUtilities.unmodifibbleView(currSet);
        } else {
            PrintServiceAttributeSet updbtes =
                new HbshPrintServiceAttributeSet();
            Attribute []bttrs = currSet.toArrby();
            Attribute bttr;
            for (int i=0; i<bttrs.length; i++) {
                bttr = bttrs[i];
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

    public void bddPrintServiceAttributeListener(
                                 PrintServiceAttributeListener listener) {
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
    public <T extends PrintServiceAttribute>
        T getAttribute(Clbss<T> cbtegory)
    {
        if (cbtegory == null) {
            throw new NullPointerException("cbtegory");
        }
        if (!(PrintServiceAttribute.clbss.isAssignbbleFrom(cbtegory))) {
            throw new IllegblArgumentException("Not b PrintServiceAttribute");
        }

        if (cbtegory == PrinterNbme.clbss) {
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
        PrintServiceAttributeSet bttrs = new HbshPrintServiceAttributeSet();
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
        return AttributeSetUtilities.unmodifibbleView(bttrs);
    }

    privbte void initSupportedDocFlbvors() {
        String hostEnc = DocFlbvor.hostEncoding.toLowerCbse(Locble.ENGLISH);
        if (!hostEnc.equbls("utf-8") && !hostEnc.equbls("utf-16") &&
            !hostEnc.equbls("utf-16be") && !hostEnc.equbls("utf-16le") &&
            !hostEnc.equbls("us-bscii")) {

            int len = supportedDocFlbvorsInit.length;
            DocFlbvor[] flbvors =
                new DocFlbvor[len + supportedHostDocFlbvors.length];
            // copy host encoding flbvors
            System.brrbycopy(supportedHostDocFlbvors, 0, flbvors,
                             len, supportedHostDocFlbvors.length);
            System.brrbycopy(supportedDocFlbvorsInit, 0, flbvors, 0, len);

            supportedDocFlbvors = flbvors;
        } else {
            supportedDocFlbvors = supportedDocFlbvorsInit;
        }
    }

    public DocFlbvor[] getSupportedDocFlbvors() {
        if (supportedDocFlbvors == null) {
            initSupportedDocFlbvors();
        }
        int len = supportedDocFlbvors.length;
        DocFlbvor[] flbvors = new DocFlbvor[len];
        System.brrbycopy(supportedDocFlbvors, 0, flbvors, 0, len);

        return flbvors;
    }

    public boolebn isDocFlbvorSupported(DocFlbvor flbvor) {
        if (supportedDocFlbvors == null) {
            initSupportedDocFlbvors();
        }
        for (int f=0; f<supportedDocFlbvors.length; f++) {
            if (flbvor.equbls(supportedDocFlbvors[f])) {
                return true;
            }
        }
        return fblse;
    }

    public Clbss<?>[] getSupportedAttributeCbtegories() {
        int totblCbts = otherAttrCbts.length;
        Clbss<?>[] cbts = new Clbss<?>[totblCbts];
        System.brrbycopy(otherAttrCbts, 0, cbts, 0, otherAttrCbts.length);
        return cbts;
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

        for (int i=0;i<otherAttrCbts.length;i++) {
            if (cbtegory == otherAttrCbts[i]) {
                return true;
            }
        }
        return fblse;
    }

    /* return defbults for bll bttributes for which there is b defbult
     * vblue
     */
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

        if (cbtegory == Copies.clbss) {
            return new Copies(1);
        } else if (cbtegory == Chrombticity.clbss) {
            return Chrombticity.COLOR;
        } else if (cbtegory == Destinbtion.clbss) {
            try {
                return new Destinbtion((new File("out.ps")).toURI());
            } cbtch (SecurityException se) {
                try {
                    return new Destinbtion(new URI("file:out.ps"));
                } cbtch (URISyntbxException e) {
                    return null;
                }
            }
        } else if (cbtegory == Fidelity.clbss) {
            return Fidelity.FIDELITY_FALSE;
        } else if (cbtegory == JobNbme.clbss) {
            return new JobNbme("Jbvb Printing", null);
        } else if (cbtegory == JobSheets.clbss) {
            return JobSheets.STANDARD;
        } else if (cbtegory == Medib.clbss) {
            String defbultCountry = Locble.getDefbult().getCountry();
            if (defbultCountry != null &&
                (defbultCountry.equbls("") ||
                 defbultCountry.equbls(Locble.US.getCountry()) ||
                 defbultCountry.equbls(Locble.CANADA.getCountry()))) {
                return MedibSizeNbme.NA_LETTER;
            } else {
                 return MedibSizeNbme.ISO_A4;
            }
        } else if (cbtegory == MedibPrintbbleAreb.clbss) {
            String defbultCountry = Locble.getDefbult().getCountry();
            flobt iw, ih;
            if (defbultCountry != null &&
                (defbultCountry.equbls("") ||
                 defbultCountry.equbls(Locble.US.getCountry()) ||
                 defbultCountry.equbls(Locble.CANADA.getCountry()))) {
                iw = MedibSize.NA.LETTER.getX(Size2DSyntbx.INCH) - 0.5f;
                ih = MedibSize.NA.LETTER.getY(Size2DSyntbx.INCH) - 0.5f;
            } else {
                iw = MedibSize.ISO.A4.getX(Size2DSyntbx.INCH) - 0.5f;
                ih = MedibSize.ISO.A4.getY(Size2DSyntbx.INCH) - 0.5f;
            }
            return new MedibPrintbbleAreb(0.25f, 0.25f, iw, ih,
                                          MedibPrintbbleAreb.INCH);
        } else if (cbtegory == OrientbtionRequested.clbss) {
            return OrientbtionRequested.PORTRAIT;
        } else if (cbtegory == PbgeRbnges.clbss) {
            return new PbgeRbnges(1, Integer.MAX_VALUE);
        } else if (cbtegory == RequestingUserNbme.clbss) {
            String userNbme = "";
            try {
              userNbme = System.getProperty("user.nbme", "");
            } cbtch (SecurityException se) {
            }
            return new RequestingUserNbme(userNbme, null);
        } else if (cbtegory == SheetCollbte.clbss) {
            return SheetCollbte.UNCOLLATED;
        } else if (cbtegory == Sides.clbss) {
            return Sides.ONE_SIDED;
        } else
            return null;
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
            } else if (isAutoSense(flbvor)) {
                return null;
            }
        }

        if (!isAttributeCbtegorySupported(cbtegory)) {
            return null;
        }

        if (cbtegory == Chrombticity.clbss) {
            if (flbvor == null || isServiceFormbttedFlbvor(flbvor)) {
                Chrombticity[]brr = new Chrombticity[1];
                brr[0] = Chrombticity.COLOR;
                return (brr);
            } else {
                return null;
            }
        } else if (cbtegory == Destinbtion.clbss) {
            try {
                return new Destinbtion((new File("out.ps")).toURI());
            } cbtch (SecurityException se) {
                try {
                    return new Destinbtion(new URI("file:out.ps"));
                } cbtch (URISyntbxException e) {
                    return null;
                }
            }
        } else if (cbtegory == JobNbme.clbss) {
            return new JobNbme("Jbvb Printing", null);
        } else if (cbtegory == JobSheets.clbss) {
            JobSheets brr[] = new JobSheets[2];
            brr[0] = JobSheets.NONE;
            brr[1] = JobSheets.STANDARD;
            return brr;
        } else if (cbtegory == RequestingUserNbme.clbss) {
            String userNbme = "";
            try {
              userNbme = System.getProperty("user.nbme", "");
            } cbtch (SecurityException se) {
            }
            return new RequestingUserNbme(userNbme, null);
        } else if (cbtegory == OrientbtionRequested.clbss) {
            if (flbvor == null || isServiceFormbttedFlbvor(flbvor)) {
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
            if (flbvor == null ||
                !(flbvor.equbls(DocFlbvor.INPUT_STREAM.POSTSCRIPT) ||
                  flbvor.equbls(DocFlbvor.URL.POSTSCRIPT) ||
                  flbvor.equbls(DocFlbvor.BYTE_ARRAY.POSTSCRIPT))) {
                return new CopiesSupported(1, MAXCOPIES);
            } else {
                return null;
            }
        } else if (cbtegory == Medib.clbss) {
            Medib []brr = new Medib[medibSizes.length];
            System.brrbycopy(medibSizes, 0, brr, 0, medibSizes.length);
            return brr;
        } else if (cbtegory == Fidelity.clbss) {
            Fidelity []brr = new Fidelity[2];
            brr[0] = Fidelity.FIDELITY_FALSE;
            brr[1] = Fidelity.FIDELITY_TRUE;
            return brr;
        } else if (cbtegory == MedibPrintbbleAreb.clbss) {
            /* The code below implements the behbviour thbt if no Medib or
             * MedibSize bttribute is specified, return bn brrby of
             * MedibPrintbbleAreb, one for ebch supported Medib.
             * If b MedibSize is specified, return b MPA consistent for thbt,
             * bnd if b Medib is specified locbte its MedibSize bnd return
             * its MPA, bnd if none is found, return bn MPA for the defbult
             * Medib for this service.
             */
            if (bttributes == null) {
                return getAllPrintbbleArebs();
            }
            MedibSize medibSize = (MedibSize)bttributes.get(MedibSize.clbss);
            Medib medib = (Medib)bttributes.get(Medib.clbss);
            MedibPrintbbleAreb []brr = new MedibPrintbbleAreb[1];
            if (medibSize == null) {
                if (medib instbnceof MedibSizeNbme) {
                    MedibSizeNbme msn = (MedibSizeNbme)medib;
                    medibSize = MedibSize.getMedibSizeForNbme(msn);
                    if (medibSize == null) {
                        /* try to get b size from the defbult medib */
                        medib = (Medib)getDefbultAttributeVblue(Medib.clbss);
                        if (medib instbnceof MedibSizeNbme) {
                            msn = (MedibSizeNbme)medib;
                            medibSize = MedibSize.getMedibSizeForNbme(msn);
                        }
                        if (medibSize == null) {
                            /* shouldn't hbppen, return b defbult */
                            brr[0] = new MedibPrintbbleAreb(0.25f, 0.25f,
                                                            8f, 10.5f,
                                                            MedibSize.INCH);
                            return brr;
                        }
                    }
                } else {
                    return getAllPrintbbleArebs();
                }
            }
            /* If rebch here MedibSize is non-null */
            bssert medibSize != null;
            brr[0] = new MedibPrintbbleAreb(0.25f, 0.25f,
                                medibSize.getX(MedibSize.INCH)-0.5f,
                                medibSize.getY(MedibSize.INCH)-0.5f,
                                MedibSize.INCH);
            return brr;
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
        } else if (cbtegory == SheetCollbte.clbss) {
            if (flbvor == null ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                SheetCollbte []brr = new SheetCollbte[2];
                brr[0] = SheetCollbte.UNCOLLATED;
                brr[1] = SheetCollbte.COLLATED;
                return brr;
            } else {
                return null;
            }
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
        } else {
            return null;
        }
    }

    privbte stbtic MedibPrintbbleAreb[] mpbs = null;
    privbte MedibPrintbbleAreb[] getAllPrintbbleArebs() {

        if (mpbs == null) {
            Medib[] medib = (Medib[])getSupportedAttributeVblues(Medib.clbss,
                                                                 null, null);
            mpbs = new MedibPrintbbleAreb[medib.length];
            for (int i=0; i< mpbs.length; i++) {
                if (medib[i] instbnceof MedibSizeNbme) {
                    MedibSizeNbme msn = (MedibSizeNbme)medib[i];
                    MedibSize medibSize = MedibSize.getMedibSizeForNbme(msn);
                    if (medibSize == null) {
                        mpbs[i] = (MedibPrintbbleAreb)
                            getDefbultAttributeVblue(MedibPrintbbleAreb.clbss);
                    } else {
                        mpbs[i] = new MedibPrintbbleAreb(0.25f, 0.25f,
                                        medibSize.getX(MedibSize.INCH)-0.5f,
                                        medibSize.getY(MedibSize.INCH)-0.5f,
                                        MedibSize.INCH);
                    }
                }
            }
        }
        MedibPrintbbleAreb[] mpbsCopy = new MedibPrintbbleAreb[mpbs.length];
        System.brrbycopy(mpbs, 0, mpbsCopy, 0, mpbs.length);
        return mpbsCopy;
    }

    /* Is this one of the flbvors thbt this service explicitly
     * generbtes postscript for, bnd so cbn control how it is rendered?
     */
    privbte boolebn isServiceFormbttedFlbvor(DocFlbvor flbvor) {
        return
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
            flbvor.equbls(DocFlbvor.URL.PNG);
    }

    public boolebn isAttributeVblueSupported(Attribute bttr,
                                             DocFlbvor flbvor,
                                             AttributeSet bttributes) {
        if (bttr == null) {
            throw new NullPointerException("null bttribute");
        }
        if (flbvor != null) {
            if (!isDocFlbvorSupported(flbvor)) {
                throw new IllegblArgumentException(flbvor +
                                               " is bn unsupported flbvor");
            } else if (isAutoSense(flbvor)) {
                return fblse;
            }
        }
        Clbss<? extends Attribute> cbtegory = bttr.getCbtegory();
        if (!isAttributeCbtegorySupported(cbtegory)) {
            return fblse;
        }
        else if (bttr.getCbtegory() == Chrombticity.clbss) {
            if (flbvor == null || isServiceFormbttedFlbvor(flbvor)) {
                return bttr == Chrombticity.COLOR;
            } else {
                return fblse;
            }
        }
        else if (bttr.getCbtegory() == Copies.clbss) {
            return (flbvor == null ||
                   !(flbvor.equbls(DocFlbvor.INPUT_STREAM.POSTSCRIPT) ||
                     flbvor.equbls(DocFlbvor.URL.POSTSCRIPT) ||
                     flbvor.equbls(DocFlbvor.BYTE_ARRAY.POSTSCRIPT))) &&
                isSupportedCopies((Copies)bttr);
        } else if (bttr.getCbtegory() == Destinbtion.clbss) {
            URI uri = ((Destinbtion)bttr).getURI();
                if ("file".equbls(uri.getScheme()) &&
                    !(uri.getSchemeSpecificPbrt().equbls(""))) {
                return true;
            } else {
            return fblse;
            }
        } else if (bttr.getCbtegory() == Medib.clbss) {
            if (bttr instbnceof MedibSizeNbme) {
                return isSupportedMedib((MedibSizeNbme)bttr);
            } else {
                return fblse;
            }
        } else if (bttr.getCbtegory() == OrientbtionRequested.clbss) {
            if (bttr == OrientbtionRequested.REVERSE_PORTRAIT ||
                (flbvor != null) &&
                !isServiceFormbttedFlbvor(flbvor)) {
                return fblse;
            }
        } else if (bttr.getCbtegory() == PbgeRbnges.clbss) {
            if (flbvor != null &&
                !(flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                return fblse;
            }
        } else if (bttr.getCbtegory() == SheetCollbte.clbss) {
            if (flbvor != null &&
                !(flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                return fblse;
            }
        } else if (bttr.getCbtegory() == Sides.clbss) {
            if (flbvor != null &&
                !(flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PAGEABLE) ||
                flbvor.equbls(DocFlbvor.SERVICE_FORMATTED.PRINTABLE))) {
                return fblse;
            }
        }
        return true;
    }

    public AttributeSet getUnsupportedAttributes(DocFlbvor flbvor,
                                                 AttributeSet bttributes) {

        if (flbvor != null && !isDocFlbvorSupported(flbvor)) {
            throw new IllegblArgumentException("flbvor " + flbvor +
                                               "is not supported");
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
                } else if (!isAttributeVblueSupported(bttr, flbvor,
                                                      bttributes)) {
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

    public ServiceUIFbctory getServiceUIFbctory() {
        return null;
    }

    public String toString() {
        return "Unix Printer : " + getNbme();
    }

    public boolebn equbls(Object obj) {
        return  (obj == this ||
                 (obj instbnceof UnixPrintService &&
                  ((UnixPrintService)obj).getNbme().equbls(getNbme())));
    }

    public int hbshCode() {
        return this.getClbss().hbshCode()+getNbme().hbshCode();
    }

    public boolebn usesClbss(Clbss<?> c) {
        return (c == sun.print.PSPrinterJob.clbss);
    }

}
