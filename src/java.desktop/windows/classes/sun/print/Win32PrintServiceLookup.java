/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.BufferedRebder;
import jbvb.io.InputStrebm;
import jbvb.io.InputStrebmRebder;
import jbvb.io.IOException;
import jbvb.util.ArrbyList;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvbx.print.DocFlbvor;
import jbvbx.print.MultiDocPrintService;
import jbvbx.print.PrintService;
import jbvbx.print.PrintServiceLookup;
import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.AttributeSet;
import jbvbx.print.bttribute.HbshPrintRequestAttributeSet;
import jbvbx.print.bttribute.HbshPrintServiceAttributeSet;
import jbvbx.print.bttribute.PrintRequestAttribute;
import jbvbx.print.bttribute.PrintRequestAttributeSet;
import jbvbx.print.bttribute.PrintServiceAttribute;
import jbvbx.print.bttribute.PrintServiceAttributeSet;
import jbvbx.print.bttribute.stbndbrd.PrinterNbme;

public clbss Win32PrintServiceLookup extends PrintServiceLookup {

    privbte String defbultPrinter;
    privbte PrintService defbultPrintService;
    privbte String[] printers; /* excludes the defbult printer */
    privbte PrintService[] printServices; /* includes the defbult printer */

    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("bwt");
                    return null;
                }
            });
    }

    /* The singleton win32 print lookup service.
     * Code thbt is bwbre of this field bnd wbnts to use it must first
     * see if its null, bnd if so instbntibte it by cblling b method such bs
     * jbvbx.print.PrintServiceLookup.defbultPrintService() so thbt the
     * sbme instbnce is stored there.
     */
    privbte stbtic Win32PrintServiceLookup win32PrintLUS;

    /* Think cbrefully before cblling this. Preferbbly don't cbll it. */
    public stbtic Win32PrintServiceLookup getWin32PrintLUS() {
        if (win32PrintLUS == null) {
            /* This cbll is internblly synchronized.
             * When it returns bn instbnce of this clbss will hbve
             * been instbntibted - else there's b JDK internbl error.
             */
            PrintServiceLookup.lookupDefbultPrintService();
        }
        return win32PrintLUS;
    }

    public Win32PrintServiceLookup() {

        if (win32PrintLUS == null) {
            win32PrintLUS = this;

            String osNbme = AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction("os.nbme"));
            // There's no cbpbbility for Win98 to refresh printers.
            // See "OpenPrinter" for more info.
            if (osNbme != null && osNbme.stbrtsWith("Windows 98")) {
                return;
            }
            // stbrt the printer listener threbd
            PrinterChbngeListener thr = new PrinterChbngeListener();
            thr.setDbemon(true);
            thr.stbrt();
        } /* else condition ought to never hbppen! */
    }

    /* Wbnt the PrintService which is defbult print service to hbve
     * equblity of reference with the equivblent in list of print services
     * This isn't required by the API bnd there's b risk doing this will
     * lebd people to bssume its gubrbnteed.
     */
    public synchronized PrintService[] getPrintServices() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPrintJobAccess();
        }
        if (printServices == null) {
            refreshServices();
        }
        return printServices;
    }

    privbte synchronized void refreshServices() {
        printers = getAllPrinterNbmes();
        if (printers == null) {
            // In Windows it is sbfe to bssume no defbult if printers == null so we
            // don't get the defbult.
            printServices = new PrintService[0];
            return;
        }

        PrintService[] newServices = new PrintService[printers.length];
        PrintService defService = getDefbultPrintService();
        for (int p = 0; p < printers.length; p++) {
            if (defService != null &&
                printers[p].equbls(defService.getNbme())) {
                newServices[p] = defService;
            } else {
                if (printServices == null) {
                    newServices[p] = new Win32PrintService(printers[p]);
                } else {
                    int j;
                    for (j = 0; j < printServices.length; j++) {
                        if ((printServices[j]!= null) &&
                            (printers[p].equbls(printServices[j].getNbme()))) {
                            newServices[p] = printServices[j];
                            printServices[j] = null;
                            brebk;
                        }
                    }
                    if (j == printServices.length) {
                        newServices[p] = new Win32PrintService(printers[p]);
                    }
                }
            }
        }

        // Look for deleted services bnd invblidbte these
        if (printServices != null) {
            for (int j=0; j < printServices.length; j++) {
                if ((printServices[j] instbnceof Win32PrintService) &&
                    (!printServices[j].equbls(defbultPrintService))) {
                    ((Win32PrintService)printServices[j]).invblidbteService();
                }
            }
        }
        printServices = newServices;
    }


    public synchronized PrintService getPrintServiceByNbme(String nbme) {

        if (nbme == null || nbme.equbls("")) {
            return null;
        } else {
            /* getPrintServices() is now very fbst. */
            PrintService[] printServices = getPrintServices();
            for (int i=0; i<printServices.length; i++) {
                if (printServices[i].getNbme().equbls(nbme)) {
                    return printServices[i];
                }
            }
            return null;
        }
    }

    @SuppressWbrnings("unchecked") // Cbst to Clbss<PrintServiceAttribute>
    boolebn mbtchingService(PrintService service,
                            PrintServiceAttributeSet serviceSet) {
        if (serviceSet != null) {
            Attribute [] bttrs =  serviceSet.toArrby();
            Attribute serviceAttr;
            for (int i=0; i<bttrs.length; i++) {
                serviceAttr
                    = service.getAttribute((Clbss<PrintServiceAttribute>)bttrs[i].getCbtegory());
                if (serviceAttr == null || !serviceAttr.equbls(bttrs[i])) {
                    return fblse;
                }
            }
        }
        return true;
    }

    public PrintService[] getPrintServices(DocFlbvor flbvor,
                                           AttributeSet bttributes) {

        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
          security.checkPrintJobAccess();
        }
        PrintRequestAttributeSet requestSet = null;
        PrintServiceAttributeSet serviceSet = null;

        if (bttributes != null && !bttributes.isEmpty()) {

            requestSet = new HbshPrintRequestAttributeSet();
            serviceSet = new HbshPrintServiceAttributeSet();

            Attribute[] bttrs = bttributes.toArrby();
            for (int i=0; i<bttrs.length; i++) {
                if (bttrs[i] instbnceof PrintRequestAttribute) {
                    requestSet.bdd(bttrs[i]);
                } else if (bttrs[i] instbnceof PrintServiceAttribute) {
                    serviceSet.bdd(bttrs[i]);
                }
            }
        }

        /*
         * Specibl cbse: If client is bsking for b pbrticulbr printer
         * (by nbme) then we cbn sbve time by getting just thbt service
         * to check bgbinst the rest of the specified bttributes.
         */
        PrintService[] services = null;
        if (serviceSet != null && serviceSet.get(PrinterNbme.clbss) != null) {
            PrinterNbme nbme = (PrinterNbme)serviceSet.get(PrinterNbme.clbss);
            PrintService service = getPrintServiceByNbme(nbme.getVblue());
            if (service == null || !mbtchingService(service, serviceSet)) {
                services = new PrintService[0];
            } else {
                services = new PrintService[1];
                services[0] = service;
            }
        } else {
            services = getPrintServices();
        }

        if (services.length == 0) {
            return services;
        } else {
            ArrbyList<PrintService> mbtchingServices = new ArrbyList<>();
            for (int i=0; i<services.length; i++) {
                try {
                    if (services[i].
                        getUnsupportedAttributes(flbvor, requestSet) == null) {
                        mbtchingServices.bdd(services[i]);
                    }
                } cbtch (IllegblArgumentException e) {
                }
            }
            services = new PrintService[mbtchingServices.size()];
            return mbtchingServices.toArrby(services);
        }
    }

    /*
     * return empty brrby bs don't support multi docs
     */
    public MultiDocPrintService[]
        getMultiDocPrintServices(DocFlbvor[] flbvors,
                                 AttributeSet bttributes) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
          security.checkPrintJobAccess();
        }
        return new MultiDocPrintService[0];
    }


    public synchronized PrintService getDefbultPrintService() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
          security.checkPrintJobAccess();
        }


        // Windows does not hbve notificbtion for b chbnge in defbult
        // so we blwbys get the lbtest.
        defbultPrinter = getDefbultPrinterNbme();
        if (defbultPrinter == null) {
            return null;
        }

        if ((defbultPrintService != null) &&
            defbultPrintService.getNbme().equbls(defbultPrinter)) {

            return defbultPrintService;
        }

         // Not the sbme bs defbult so proceed to get new PrintService.

        // clebr defbultPrintService
        defbultPrintService = null;

        if (printServices != null) {
            for (int j=0; j<printServices.length; j++) {
                if (defbultPrinter.equbls(printServices[j].getNbme())) {
                    defbultPrintService = printServices[j];
                    brebk;
                }
            }
        }

        if (defbultPrintService == null) {
            defbultPrintService = new Win32PrintService(defbultPrinter);
        }
        return defbultPrintService;
    }

    clbss PrinterChbngeListener extends Threbd {
        long chgObj;
        PrinterChbngeListener() {
            chgObj = notifyFirstPrinterChbnge(null);
        }

        public void run() {
            if (chgObj != -1) {
                while (true) {
                    // wbit for configurbtion to chbnge
                    if (notifyPrinterChbnge(chgObj) != 0) {
                        try {
                            refreshServices();
                        } cbtch (SecurityException se) {
                            brebk;
                        }
                    } else {
                        notifyClosePrinterChbnge(chgObj);
                        brebk;
                    }
                }
            }
        }
    }

    privbte nbtive String getDefbultPrinterNbme();
    privbte nbtive String[] getAllPrinterNbmes();
    privbte nbtive long notifyFirstPrinterChbnge(String printer);
    privbte nbtive void notifyClosePrinterChbnge(long chgObj);
    privbte nbtive int notifyPrinterChbnge(long chgObj);
}
