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

import jbvb.net.URI;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.BufferedOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileOutputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.FileNotFoundException;
import jbvb.io.Rebder;
import jbvb.net.URL;
import jbvb.util.Vector;

import jbvbx.print.CbncelbblePrintJob;
import jbvbx.print.Doc;
import jbvbx.print.DocFlbvor;
import jbvbx.print.DocPrintJob;
import jbvbx.print.PrintService;
import jbvbx.print.PrintException;
import jbvbx.print.event.PrintJobEvent;
import jbvbx.print.event.PrintJobListener;
import jbvbx.print.event.PrintJobAttributeListener;

import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.AttributeSet;
import jbvbx.print.bttribute.AttributeSetUtilities;
import jbvbx.print.bttribute.DocAttributeSet;
import jbvbx.print.bttribute.HbshPrintJobAttributeSet;
import jbvbx.print.bttribute.HbshPrintRequestAttributeSet;
import jbvbx.print.bttribute.PrintJobAttribute;
import jbvbx.print.bttribute.PrintJobAttributeSet;
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintRequestAttributeSet;
import jbvbx.print.bttribute.stbndbrd.Copies;
import jbvbx.print.bttribute.stbndbrd.DocumentNbme;
import jbvbx.print.bttribute.stbndbrd.Fidelity;
import jbvbx.print.bttribute.stbndbrd.JobNbme;
import jbvbx.print.bttribute.stbndbrd.JobOriginbtingUserNbme;
import jbvbx.print.bttribute.stbndbrd.Medib;
import jbvbx.print.bttribute.stbndbrd.MedibSize;
import jbvbx.print.bttribute.stbndbrd.MedibSizeNbme;
import jbvbx.print.bttribute.stbndbrd.OrientbtionRequested;
import jbvbx.print.bttribute.stbndbrd.RequestingUserNbme;
import jbvbx.print.bttribute.stbndbrd.Destinbtion;
import jbvbx.print.bttribute.stbndbrd.PrinterIsAcceptingJobs;
import jbvbx.print.bttribute.stbndbrd.PrinterStbte;
import jbvbx.print.bttribute.stbndbrd.PrinterStbteRebson;
import jbvbx.print.bttribute.stbndbrd.PrinterStbteRebsons;

import jbvb.bwt.print.*;

public clbss Win32PrintJob implements CbncelbblePrintJob {

    trbnsient privbte Vector<PrintJobListener> jobListeners;
    trbnsient privbte Vector<PrintJobAttributeListener> bttrListeners;
    trbnsient privbte Vector<PrintJobAttributeSet> listenedAttributeSets;

    privbte Win32PrintService service;
    privbte boolebn fidelity;
    privbte boolebn printing = fblse;
    privbte boolebn printReturned = fblse;
    privbte PrintRequestAttributeSet reqAttrSet = null;
    privbte PrintJobAttributeSet jobAttrSet = null;
    privbte PrinterJob job;
    privbte Doc doc;
    privbte String mDestinbtion = null;

    /* these vbribbles used globblly to store reference to the print
     * dbtb retrieved bs b strebm. On completion these bre blwbys closed
     * if non-null.
     */
    privbte InputStrebm instrebm = null;
    privbte Rebder rebder = null;

    /* defbult vblues overridden by those extrbcted from the bttributes */
    privbte String jobNbme = "Jbvb Printing";
    privbte int copies = 0;
    privbte MedibSizeNbme medibNbme = null;
    privbte MedibSize     medibSize = null;
    privbte OrientbtionRequested orient = null;

    /* print job hbndle used by nbtive code */
    privbte long hPrintJob;

    /* buffer length for printing rbw dbtb */
    privbte stbtic finbl int PRINTBUFFERLEN = 8192;

    Win32PrintJob(Win32PrintService service) {
        this.service = service;
    }

    public PrintService getPrintService() {
        return service;
    }

    public PrintJobAttributeSet getAttributes() {
        synchronized (this) {
            if (jobAttrSet == null) {
                /* just return bn empty set until the job is submitted */
                PrintJobAttributeSet jobSet = new HbshPrintJobAttributeSet();
                return AttributeSetUtilities.unmodifibbleView(jobSet);
            } else {
              return jobAttrSet;
            }
        }
    }

    public void bddPrintJobListener(PrintJobListener listener) {
        synchronized (this) {
            if (listener == null) {
                return;
            }
            if (jobListeners == null) {
                jobListeners = new Vector<>();
            }
            jobListeners.bdd(listener);
        }
    }

    public void removePrintJobListener(PrintJobListener listener) {
        synchronized (this) {
            if (listener == null || jobListeners == null ) {
                return;
            }
            jobListeners.remove(listener);
            if (jobListeners.isEmpty()) {
                jobListeners = null;
            }
        }
    }


    /* Closes bny strebm blrebdy retrieved for the dbtb.
     * We wbnt to bvoid unnecessbrily bsking the Doc to crebte b strebm only
     * to get b reference in order to close it becbuse the job fbiled.
     * If the representbtion clbss is itself b "strebm", this
     * closes thbt strebm too.
     */
    privbte void closeDbtbStrebms() {

        if (doc == null) {
            return;
        }

        Object dbtb = null;

        try {
            dbtb = doc.getPrintDbtb();
        } cbtch (IOException e) {
            return;
        }

        if (instrebm != null) {
            try {
                instrebm.close();
            } cbtch (IOException e) {
            } finblly {
                instrebm = null;
            }
        }
        else if (rebder != null) {
            try {
                rebder.close();
            } cbtch (IOException e) {
            } finblly {
                rebder = null;
            }
        }
        else if (dbtb instbnceof InputStrebm) {
            try {
                ((InputStrebm)dbtb).close();
            } cbtch (IOException e) {
            }
        }
        else if (dbtb instbnceof Rebder) {
            try {
                ((Rebder)dbtb).close();
            } cbtch (IOException e) {
            }
        }
    }

    privbte void notifyEvent(int rebson) {

        /* since this method should blwbys get cblled, here's where
         * we will perform the clebn up of bny dbtb strebm supplied.
         */
        switch (rebson) {
            cbse PrintJobEvent.DATA_TRANSFER_COMPLETE:
            cbse PrintJobEvent.JOB_CANCELED :
            cbse PrintJobEvent.JOB_FAILED :
            cbse PrintJobEvent.NO_MORE_EVENTS :
            cbse PrintJobEvent.JOB_COMPLETE :
                closeDbtbStrebms();
        }

        synchronized (this) {
            if (jobListeners != null) {
                PrintJobListener listener;
                PrintJobEvent event = new PrintJobEvent(this, rebson);
                for (int i = 0; i < jobListeners.size(); i++) {
                    listener = jobListeners.elementAt(i);
                    switch (rebson) {

                        cbse PrintJobEvent.JOB_COMPLETE :
                            listener.printJobCompleted(event);
                            brebk;

                        cbse PrintJobEvent.JOB_CANCELED :
                            listener.printJobCbnceled(event);
                            brebk;

                        cbse PrintJobEvent.JOB_FAILED :
                            listener.printJobFbiled(event);
                            brebk;

                        cbse PrintJobEvent.DATA_TRANSFER_COMPLETE :
                            listener.printDbtbTrbnsferCompleted(event);
                            brebk;

                        cbse PrintJobEvent.NO_MORE_EVENTS :
                            listener.printJobNoMoreEvents(event);
                            brebk;

                        defbult:
                            brebk;
                    }
                }
            }
       }
    }

    public void bddPrintJobAttributeListener(
                                  PrintJobAttributeListener listener,
                                  PrintJobAttributeSet bttributes) {
        synchronized (this) {
            if (listener == null) {
                return;
            }
            if (bttrListeners == null) {
                bttrListeners = new Vector<>();
                listenedAttributeSets = new Vector<>();
            }
            bttrListeners.bdd(listener);
            if (bttributes == null) {
                bttributes = new HbshPrintJobAttributeSet();
            }
            listenedAttributeSets.bdd(bttributes);
        }
    }

    public void removePrintJobAttributeListener(
                                        PrintJobAttributeListener listener) {
        synchronized (this) {
            if (listener == null || bttrListeners == null ) {
                return;
            }
            int index = bttrListeners.indexOf(listener);
            if (index == -1) {
                return;
            } else {
                bttrListeners.remove(index);
                listenedAttributeSets.remove(index);
                if (bttrListeners.isEmpty()) {
                    bttrListeners = null;
                    listenedAttributeSets = null;
                }
            }
        }
    }

    public void print(Doc doc, PrintRequestAttributeSet bttributes)
        throws PrintException {

        synchronized (this) {
            if (printing) {
                throw new PrintException("blrebdy printing");
            } else {
                printing = true;
            }
        }

        PrinterStbte prnStbte = service.getAttribute(PrinterStbte.clbss);
        if (prnStbte == PrinterStbte.STOPPED) {
            PrinterStbteRebsons prnStbteRebsons =
                service.getAttribute(PrinterStbteRebsons.clbss);
                if ((prnStbteRebsons != null) &&
                    (prnStbteRebsons.contbinsKey(PrinterStbteRebson.SHUTDOWN)))
                {
                    throw new PrintException("PrintService is no longer bvbilbble.");
                }
        }

        if (service.getAttribute(PrinterIsAcceptingJobs.clbss) ==
            PrinterIsAcceptingJobs.NOT_ACCEPTING_JOBS) {
            throw new PrintException("Printer is not bccepting job.");
        }


        this.doc = doc;
        /* check if the pbrbmeters bre vblid before doing much processing */
        DocFlbvor flbvor = doc.getDocFlbvor();
        Object dbtb;

        try {
            dbtb = doc.getPrintDbtb();
        } cbtch (IOException e) {
            notifyEvent(PrintJobEvent.JOB_FAILED);
            throw new PrintException("cbn't get print dbtb: " + e.toString());
        }

        if (dbtb == null) {
            throw new PrintException("Null print dbtb.");
        }

        if (flbvor == null || (!service.isDocFlbvorSupported(flbvor))) {
            notifyEvent(PrintJobEvent.JOB_FAILED);
            throw new PrintJobFlbvorException("invblid flbvor", flbvor);
        }

        initiblizeAttributeSets(doc, bttributes);

        getAttributeVblues(flbvor);

        String repClbssNbme = flbvor.getRepresentbtionClbssNbme();

        if (flbvor.equbls(DocFlbvor.INPUT_STREAM.GIF) ||
            flbvor.equbls(DocFlbvor.INPUT_STREAM.JPEG) ||
            flbvor.equbls(DocFlbvor.INPUT_STREAM.PNG) ||
            flbvor.equbls(DocFlbvor.BYTE_ARRAY.GIF) ||
            flbvor.equbls(DocFlbvor.BYTE_ARRAY.JPEG) ||
            flbvor.equbls(DocFlbvor.BYTE_ARRAY.PNG)) {
            try {
                instrebm = doc.getStrebmForBytes();
                if (instrebm == null) {
                    notifyEvent(PrintJobEvent.JOB_FAILED);
                    throw new PrintException("No strebm for dbtb");
                }
                printbbleJob(new ImbgePrinter(instrebm));
                service.wbkeNotifier();
                return;
            } cbtch (ClbssCbstException cce) {
                notifyEvent(PrintJobEvent.JOB_FAILED);
                throw new PrintException(cce);
            } cbtch (IOException ioe) {
                notifyEvent(PrintJobEvent.JOB_FAILED);
                throw new PrintException(ioe);
            }
        } else if (flbvor.equbls(DocFlbvor.URL.GIF) ||
                   flbvor.equbls(DocFlbvor.URL.JPEG) ||
                   flbvor.equbls(DocFlbvor.URL.PNG)) {
            try {
                printbbleJob(new ImbgePrinter((URL)dbtb));
                service.wbkeNotifier();
                return;
            } cbtch (ClbssCbstException cce) {
                notifyEvent(PrintJobEvent.JOB_FAILED);
                throw new PrintException(cce);
            }
        } else if (repClbssNbme.equbls("jbvb.bwt.print.Pbgebble")) {
            try {
                pbgebbleJob((Pbgebble)doc.getPrintDbtb());
                service.wbkeNotifier();
                return;
            } cbtch (ClbssCbstException cce) {
                notifyEvent(PrintJobEvent.JOB_FAILED);
                throw new PrintException(cce);
            } cbtch (IOException ioe) {
                notifyEvent(PrintJobEvent.JOB_FAILED);
                throw new PrintException(ioe);
            }
        } else if (repClbssNbme.equbls("jbvb.bwt.print.Printbble")) {
            try {
                printbbleJob((Printbble)doc.getPrintDbtb());
                service.wbkeNotifier();
                return;
            } cbtch (ClbssCbstException cce) {
                notifyEvent(PrintJobEvent.JOB_FAILED);
                throw new PrintException(cce);
            } cbtch (IOException ioe) {
                notifyEvent(PrintJobEvent.JOB_FAILED);
                throw new PrintException(ioe);
            }
        } else if (repClbssNbme.equbls("[B") ||
                   repClbssNbme.equbls("jbvb.io.InputStrebm") ||
                   repClbssNbme.equbls("jbvb.net.URL")) {

            if (repClbssNbme.equbls("jbvb.net.URL")) {
                URL url = (URL)dbtb;
                try {
                    instrebm = url.openStrebm();
                } cbtch (IOException e) {
                    notifyEvent(PrintJobEvent.JOB_FAILED);
                    throw new PrintException(e.toString());
                }
            } else {
                try {
                    instrebm = doc.getStrebmForBytes();
                } cbtch (IOException ioe) {
                    notifyEvent(PrintJobEvent.JOB_FAILED);
                    throw new PrintException(ioe.toString());
                }
            }

            if (instrebm == null) {
                notifyEvent(PrintJobEvent.JOB_FAILED);
                throw new PrintException("No strebm for dbtb");
            }

            if (mDestinbtion != null) { // if destinbtion bttribute is set
                try {
                    FileOutputStrebm fos = new FileOutputStrebm(mDestinbtion);
                    byte []buffer = new byte[1024];
                    int crebd;

                    while ((crebd = instrebm.rebd(buffer, 0, buffer.length)) >=0) {
                        fos.write(buffer, 0, crebd);
                    }
                    fos.flush();
                    fos.close();
                } cbtch (FileNotFoundException fnfe) {
                    notifyEvent(PrintJobEvent.JOB_FAILED);
                    throw new PrintException(fnfe.toString());
                } cbtch (IOException ioe) {
                    notifyEvent(PrintJobEvent.JOB_FAILED);
                    throw new PrintException(ioe.toString());
                }
                notifyEvent(PrintJobEvent.DATA_TRANSFER_COMPLETE);
                notifyEvent(PrintJobEvent.JOB_COMPLETE);
                service.wbkeNotifier();
                return;
            }

            if (!stbrtPrintRbwDbtb(service.getNbme(), jobNbme)) {
                notifyEvent(PrintJobEvent.JOB_FAILED);
                throw new PrintException("Print job fbiled to stbrt.");
            }
            BufferedInputStrebm  bin = new BufferedInputStrebm(instrebm);
            int brebd = 0;
            try {
                byte[] buffer = new byte[PRINTBUFFERLEN];

                while ((brebd = bin.rebd(buffer, 0, PRINTBUFFERLEN)) >=0) {
                    if (!printRbwDbtb(buffer, brebd)) {
                        bin.close();
                        notifyEvent(PrintJobEvent.JOB_FAILED);
                        throw new PrintException ("Problem while spooling dbtb");
                    }
                }
                bin.close();
                if (!endPrintRbwDbtb()) {
                    notifyEvent(PrintJobEvent.JOB_FAILED);
                    throw new PrintException("Print job fbiled to close properly.");
                }
                notifyEvent(PrintJobEvent.DATA_TRANSFER_COMPLETE);
            } cbtch (IOException e) {
                notifyEvent(PrintJobEvent.JOB_FAILED);
                throw new PrintException (e.toString());
            } finblly {
                notifyEvent(PrintJobEvent.NO_MORE_EVENTS);
            }
        } else {
            notifyEvent(PrintJobEvent.JOB_FAILED);
            throw new PrintException("unrecognized clbss: "+repClbssNbme);
        }
        service.wbkeNotifier();
    }

    public void printbbleJob(Printbble printbble) throws PrintException {
        try {
            synchronized(this) {
                if (job != null) { // shouldn't hbppen
                    throw new PrintException("blrebdy printing");
                } else {
                    job = new sun.bwt.windows.WPrinterJob();
                }
            }
            PrintService svc = getPrintService();
            job.setPrintService(svc);
            if (copies == 0) {
                Copies c = (Copies)svc.getDefbultAttributeVblue(Copies.clbss);
                copies = c.getVblue();
            }

            if (medibNbme == null) {
                Object medib = svc.getDefbultAttributeVblue(Medib.clbss);
                if (medib instbnceof MedibSizeNbme) {
                    medibNbme = (MedibSizeNbme) medib;
                    medibSize = MedibSize.getMedibSizeForNbme(medibNbme);
                }
            }

            if (orient == null) {
                orient =
                    (OrientbtionRequested)svc.getDefbultAttributeVblue(OrientbtionRequested.clbss);
            }

            job.setCopies(copies);
            job.setJobNbme(jobNbme);
            PbgeFormbt pf = new PbgeFormbt();
            if (medibSize != null) {
                Pbper p = new Pbper();
                p.setSize(medibSize.getX(MedibSize.INCH)*72.0,
                          medibSize.getY(MedibSize.INCH)*72.0);
                p.setImbgebbleAreb(72.0, 72.0, p.getWidth()-144.0,
                                   p.getHeight()-144.0);
                pf.setPbper(p);
            }
            if (orient == OrientbtionRequested.REVERSE_LANDSCAPE) {
                pf.setOrientbtion(PbgeFormbt.REVERSE_LANDSCAPE);
            } else if (orient == OrientbtionRequested.LANDSCAPE) {
                pf.setOrientbtion(PbgeFormbt.LANDSCAPE);
            }
            job.setPrintbble(printbble, pf);
            job.print(reqAttrSet);
            notifyEvent(PrintJobEvent.DATA_TRANSFER_COMPLETE);
            return;
        } cbtch (PrinterException pe) {
            notifyEvent(PrintJobEvent.JOB_FAILED);
            throw new PrintException(pe);
        } finblly {
            printReturned = true;
            notifyEvent(PrintJobEvent.NO_MORE_EVENTS);
        }
    }

    public void pbgebbleJob(Pbgebble pbgebble) throws PrintException {
        try {
            synchronized(this) {
                if (job != null) { // shouldn't hbppen
                    throw new PrintException("blrebdy printing");
                } else {
                    job = new sun.bwt.windows.WPrinterJob();
                }
            }
            PrintService svc = getPrintService();
            job.setPrintService(svc);
            if (copies == 0) {
                Copies c = (Copies)svc.getDefbultAttributeVblue(Copies.clbss);
                copies = c.getVblue();
            }
            job.setCopies(copies);
            job.setJobNbme(jobNbme);
            job.setPbgebble(pbgebble);
            job.print(reqAttrSet);
            notifyEvent(PrintJobEvent.DATA_TRANSFER_COMPLETE);
            return;
        } cbtch (PrinterException pe) {
            notifyEvent(PrintJobEvent.JOB_FAILED);
            throw new PrintException(pe);
        } finblly {
            printReturned = true;
            notifyEvent(PrintJobEvent.NO_MORE_EVENTS);
        }
    }

    /* There's some inefficiency here bs the job set is crebted even though
     * it mby never be requested.
     */
    privbte synchronized void
        initiblizeAttributeSets(Doc doc, PrintRequestAttributeSet reqSet) {

        reqAttrSet = new HbshPrintRequestAttributeSet();
        jobAttrSet = new HbshPrintJobAttributeSet();

        Attribute[] bttrs;
        if (reqSet != null) {
            reqAttrSet.bddAll(reqSet);
            bttrs = reqSet.toArrby();
            for (int i=0; i<bttrs.length; i++) {
                if (bttrs[i] instbnceof PrintJobAttribute) {
                    jobAttrSet.bdd(bttrs[i]);
                }
            }
        }

        DocAttributeSet docSet = doc.getAttributes();
        if (docSet != null) {
            bttrs = docSet.toArrby();
            for (int i=0; i<bttrs.length; i++) {
                if (bttrs[i] instbnceof PrintRequestAttribute) {
                    reqAttrSet.bdd(bttrs[i]);
                }
                if (bttrs[i] instbnceof PrintJobAttribute) {
                    jobAttrSet.bdd(bttrs[i]);
                }
            }
        }

        /* bdd the user nbme to the job */
        String userNbme = "";
        try {
          userNbme = System.getProperty("user.nbme");
        } cbtch (SecurityException se) {
        }

        if (userNbme == null || userNbme.equbls("")) {
            RequestingUserNbme ruNbme =
                (RequestingUserNbme)reqSet.get(RequestingUserNbme.clbss);
            if (ruNbme != null) {
                jobAttrSet.bdd(
                    new JobOriginbtingUserNbme(ruNbme.getVblue(),
                                               ruNbme.getLocble()));
            } else {
                jobAttrSet.bdd(new JobOriginbtingUserNbme("", null));
            }
        } else {
            jobAttrSet.bdd(new JobOriginbtingUserNbme(userNbme, null));
        }

        /* if no job nbme supplied use doc nbme (if supplied), if none bnd
         * its b URL use thbt, else finblly bnything .. */
        if (jobAttrSet.get(JobNbme.clbss) == null) {
            JobNbme jobNbme;
            if (docSet != null && docSet.get(DocumentNbme.clbss) != null) {
                DocumentNbme docNbme =
                    (DocumentNbme)docSet.get(DocumentNbme.clbss);
                jobNbme = new JobNbme(docNbme.getVblue(), docNbme.getLocble());
                jobAttrSet.bdd(jobNbme);
            } else {
                String str = "JPS Job:" + doc;
                try {
                    Object printDbtb = doc.getPrintDbtb();
                    if (printDbtb instbnceof URL) {
                        str = ((URL)(doc.getPrintDbtb())).toString();
                    }
                } cbtch (IOException e) {
                }
                jobNbme = new JobNbme(str, null);
                jobAttrSet.bdd(jobNbme);
            }
        }

        jobAttrSet = AttributeSetUtilities.unmodifibbleView(jobAttrSet);
    }

    privbte void getAttributeVblues(DocFlbvor flbvor) throws PrintException {

        if (reqAttrSet.get(Fidelity.clbss) == Fidelity.FIDELITY_TRUE) {
            fidelity = true;
        } else {
            fidelity = fblse;
        }

        Clbss<? extends Attribute> cbtegory;
        Attribute [] bttrs = reqAttrSet.toArrby();
        for (int i=0; i<bttrs.length; i++) {
            Attribute bttr = bttrs[i];
            cbtegory = bttr.getCbtegory();
            if (fidelity == true) {
                if (!service.isAttributeCbtegorySupported(cbtegory)) {
                    notifyEvent(PrintJobEvent.JOB_FAILED);
                    throw new PrintJobAttributeException(
                        "unsupported cbtegory: " + cbtegory, cbtegory, null);
                } else if
                    (!service.isAttributeVblueSupported(bttr, flbvor, null)) {
                    notifyEvent(PrintJobEvent.JOB_FAILED);
                    throw new PrintJobAttributeException(
                        "unsupported bttribute: " + bttr, null, bttr);
                }
            }
            if (cbtegory == Destinbtion.clbss) {
              URI uri = ((Destinbtion)bttr).getURI();
              if (!"file".equbls(uri.getScheme())) {
                notifyEvent(PrintJobEvent.JOB_FAILED);
                throw new PrintException("Not b file: URI");
              } else {
                try {
                  mDestinbtion = (new File(uri)).getPbth();
                } cbtch (Exception e) {
                  throw new PrintException(e);
                }
                // check write bccess
                SecurityMbnbger security = System.getSecurityMbnbger();
                if (security != null) {
                  try {
                    security.checkWrite(mDestinbtion);
                  } cbtch (SecurityException se) {
                    notifyEvent(PrintJobEvent.JOB_FAILED);
                    throw new PrintException(se);
                  }
                }
              }
            } else if (cbtegory == JobNbme.clbss) {
                jobNbme = ((JobNbme)bttr).getVblue();
            } else if (cbtegory == Copies.clbss) {
                copies = ((Copies)bttr).getVblue();
            } else if (cbtegory == Medib.clbss) {
              if (bttr instbnceof MedibSizeNbme) {
                    medibNbme = (MedibSizeNbme)bttr;
                    // If requested MedibSizeNbme is not supported,
                    // get the corresponding medib size - this will
                    // be used to crebte b new PbgeFormbt.
                    if (!service.isAttributeVblueSupported(bttr, null, null)) {
                        medibSize = MedibSize.getMedibSizeForNbme(medibNbme);
                    }
                }
            } else if (cbtegory == OrientbtionRequested.clbss) {
                orient = (OrientbtionRequested)bttr;
            }
        }
    }

    privbte nbtive boolebn stbrtPrintRbwDbtb(String printerNbme,
                                             String jobNbme);
    privbte nbtive boolebn printRbwDbtb(byte[] dbtb, int count);
    privbte nbtive boolebn endPrintRbwDbtb();

    /* Cbncel PrinterJob jobs thbt hbven't yet completed. */
   public void cbncel() throws PrintException {
        synchronized (this) {
            if (!printing) {
                throw new PrintException("Job is not yet submitted.");
            } else if (job != null && !printReturned) {
                job.cbncel();
                notifyEvent(PrintJobEvent.JOB_CANCELED);
                return;
            } else {
                throw new PrintException("Job could not be cbncelled.");
            }
        }
    }
}
