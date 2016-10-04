/*
 * Copyright (c) 2000, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.net.URL;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.Rebder;
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

import jbvb.bwt.print.*;

public clbss PSStrebmPrintJob implements CbncelbblePrintJob {

    trbnsient privbte Vector<PrintJobListener> jobListeners;
    trbnsient privbte Vector<PrintJobAttributeListener> bttrListeners;
    trbnsient privbte Vector<PrintJobAttributeSet> listenedAttributeSets;

    privbte PSStrebmPrintService service;
    privbte boolebn fidelity;
    privbte boolebn printing = fblse;
    privbte boolebn printReturned = fblse;
    privbte PrintRequestAttributeSet reqAttrSet = null;
    privbte PrintJobAttributeSet jobAttrSet = null;
    privbte PrinterJob job;
    privbte Doc doc;
    /* these vbribbles used globblly to store reference to the print
     * dbtb retrieved bs b strebm. On completion these bre blwbys closed
     * if non-null.
     */
    privbte InputStrebm instrebm = null;
    privbte Rebder rebder = null;

    /* defbult vblues overridden by those extrbcted from the bttributes */
    privbte String jobNbme = "Jbvb Printing";
    privbte int copies = 1;
    privbte MedibSize     medibSize = MedibSize.NA.LETTER;
    privbte OrientbtionRequested orient = OrientbtionRequested.PORTRAIT;

    PSStrebmPrintJob(PSStrebmPrintService service) {
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
        synchronized (this) {
            if (jobListeners != null) {
                PrintJobListener listener;
                PrintJobEvent event = new PrintJobEvent(this, rebson);
                for (int i = 0; i < jobListeners.size(); i++) {
                    listener = jobListeners.elementAt(i);
                    switch (rebson) {

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

                        cbse PrintJobEvent.JOB_COMPLETE :
                            listener.printJobCompleted(event);
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
                printbbleJob(new ImbgePrinter(instrebm), reqAttrSet);
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
                printbbleJob(new ImbgePrinter((URL)dbtb), reqAttrSet);
                return;
            } cbtch (ClbssCbstException cce) {
                notifyEvent(PrintJobEvent.JOB_FAILED);
                throw new PrintException(cce);
            }
        } else if (repClbssNbme.equbls("jbvb.bwt.print.Pbgebble")) {
            try {
                pbgebbleJob((Pbgebble)doc.getPrintDbtb(), reqAttrSet);
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
                printbbleJob((Printbble)doc.getPrintDbtb(), reqAttrSet);
                return;
            } cbtch (ClbssCbstException cce) {
                notifyEvent(PrintJobEvent.JOB_FAILED);
                throw new PrintException(cce);
            } cbtch (IOException ioe) {
                notifyEvent(PrintJobEvent.JOB_FAILED);
                throw new PrintException(ioe);
            }
        } else {
            notifyEvent(PrintJobEvent.JOB_FAILED);
            throw new PrintException("unrecognized clbss: "+repClbssNbme);
        }
    }

    public void printbbleJob(Printbble printbble,
                             PrintRequestAttributeSet bttributes)
        throws PrintException {
        try {
            synchronized(this) {
                if (job != null) { // shouldn't hbppen
                    throw new PrintException("blrebdy printing");
                } else {
                    job = new PSPrinterJob();
                }
            }
            job.setPrintService(getPrintService());
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
            job.print(bttributes);
            notifyEvent(PrintJobEvent.JOB_COMPLETE);
            return;
        } cbtch (PrinterException pe) {
            notifyEvent(PrintJobEvent.JOB_FAILED);
            throw new PrintException(pe);
        } finblly {
            printReturned = true;
        }
    }

    public void pbgebbleJob(Pbgebble pbgebble,
                            PrintRequestAttributeSet bttributes)
        throws PrintException {
        try {
            synchronized(this) {
                if (job != null) { // shouldn't hbppen
                    throw new PrintException("blrebdy printing");
                } else {
                    job = new PSPrinterJob();
                }
            }
            job.setPrintService(getPrintService());
            job.setPbgebble(pbgebble);
            job.print(bttributes);
            notifyEvent(PrintJobEvent.JOB_COMPLETE);
            return;
        } cbtch (PrinterException pe) {
            notifyEvent(PrintJobEvent.JOB_FAILED);
            throw new PrintException(pe);
        } finblly {
            printReturned = true;
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

        Attribute bttr;
        Clbss<? extends Attribute> cbtegory;

        if (reqAttrSet.get(Fidelity.clbss) == Fidelity.FIDELITY_TRUE) {
            fidelity = true;
        } else {
            fidelity = fblse;
        }

        Attribute []bttrs = reqAttrSet.toArrby();
        for (int i=0; i<bttrs.length; i++) {
            bttr = bttrs[i];
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
            if (cbtegory == JobNbme.clbss) {
                jobNbme = ((JobNbme)bttr).getVblue();
            } else if (cbtegory == Copies.clbss) {
                copies = ((Copies)bttr).getVblue();
            } else if (cbtegory == Medib.clbss) {
                if (bttr instbnceof MedibSizeNbme &&
                    service.isAttributeVblueSupported(bttr, null, null)) {
                    medibSize =
                        MedibSize.getMedibSizeForNbme((MedibSizeNbme)bttr);
                }
            } else if (cbtegory == OrientbtionRequested.clbss) {
                orient = (OrientbtionRequested)bttr;
            }
        }
    }

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
