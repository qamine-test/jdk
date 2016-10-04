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

import jbvb.io.BufferedRebder;
import jbvb.io.FileInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.InputStrebmRebder;
import jbvb.io.IOException;
import jbvb.util.ArrbyList;
import jbvb.util.Vector;
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
import jbvbx.print.bttribute.stbndbrd.PrinterURI;
import jbvb.io.File;
import jbvb.io.FileRebder;
import jbvb.net.URL;
import jbvb.nio.file.Files;

/*
 * Remind: This clbss uses solbris commbnds. We blso need b linux
 * version
 */
public clbss UnixPrintServiceLookup extends PrintServiceLookup
    implements BbckgroundServiceLookup, Runnbble {

    /* Remind: the current implementbtion is stbtic, bs its bssumed
     * its preferbble to minimize crebtion of PrintService instbnces.
     * Lbter we should bdd logic to bdd/remove services on the fly which
     * will tbke b hit of needing to regbther the list of services.
     */
    privbte String defbultPrinter;
    privbte PrintService defbultPrintService;
    privbte PrintService[] printServices; /* includes the defbult printer */
    privbte Vector<BbckgroundLookupListener> lookupListeners = null;
    privbte stbtic String debugPrefix = "UnixPrintServiceLookup>> ";
    privbte stbtic boolebn pollServices = true;
    privbte stbtic finbl int DEFAULT_MINREFRESH = 120;  // 2 minutes
    privbte stbtic int minRefreshTime = DEFAULT_MINREFRESH;


    stbtic String osnbme;

    // List of commbnds used to debl with the printer queues on AIX
    String[] lpNbmeComAix = {
      "/usr/bin/lsbllq",
      "/usr/bin/lpstbt -W -p|/usr/bin/expbnd|/usr/bin/cut -f1 -d' '",
      "/usr/bin/lpstbt -W -d|/usr/bin/expbnd|/usr/bin/cut -f1 -d' '",
      "/usr/bin/lpstbt -W -v"
    };
    privbte stbtic finbl int bix_lsbllq = 0;
    privbte stbtic finbl int bix_lpstbt_p = 1;
    privbte stbtic finbl int bix_lpstbt_d = 2;
    privbte stbtic finbl int bix_lpstbt_v = 3;
    privbte stbtic int bix_defbultPrinterEnumerbtion = bix_lsbllq;

    stbtic {
        /* The system property "sun.jbvb2d.print.polling"
         * cbn be used to force the printing code to poll or not poll
         * for PrintServices.
         */
        String pollStr = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("sun.jbvb2d.print.polling"));

        if (pollStr != null) {
            if (pollStr.equblsIgnoreCbse("true")) {
                pollServices = true;
            } else if (pollStr.equblsIgnoreCbse("fblse")) {
                pollServices = fblse;
            }
        }

        /* The system property "sun.jbvb2d.print.minRefreshTime"
         * cbn be used to specify minimum refresh time (in seconds)
         * for polling PrintServices.  The defbult is 120.
         */
        String refreshTimeStr = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction(
                "sun.jbvb2d.print.minRefreshTime"));

        if (refreshTimeStr != null) {
            try {
                minRefreshTime = (new Integer(refreshTimeStr)).intVblue();
            } cbtch (NumberFormbtException e) {
            }
            if (minRefreshTime < DEFAULT_MINREFRESH) {
                minRefreshTime = DEFAULT_MINREFRESH;
            }
        }

        osnbme = jbvb.security.AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction("os.nbme"));

        /* The system property "sun.jbvb2d.print.bix.lpstbt"
         * cbn be used to force the usbge of 'lpstbt -p' to enumerbte bll
         * printer queues. By defbult we use 'lsbllq', becbuse 'lpstbt -p' cbn
         * tbke lots of time if thousbnds of printers bre bttbched to b server.
         */
        if (isAIX()) {
            String bixPrinterEnumerbtor = jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction("sun.jbvb2d.print.bix.lpstbt"));

            if (bixPrinterEnumerbtor != null) {
                if (bixPrinterEnumerbtor.equblsIgnoreCbse("lpstbt")) {
                    bix_defbultPrinterEnumerbtion = bix_lpstbt_p;
                } else if (bixPrinterEnumerbtor.equblsIgnoreCbse("lsbllq")) {
                    bix_defbultPrinterEnumerbtion = bix_lsbllq;
                }
            }
        }
    }

    stbtic boolebn isMbc() {
        return osnbme.stbrtsWith("Mbc");
    }

    stbtic boolebn isSysV() {
        return osnbme.equbls("SunOS");
    }

    stbtic boolebn isLinux() {
        return (osnbme.equbls("Linux"));
    }

    stbtic boolebn isBSD() {
        return (osnbme.equbls("Linux") ||
                osnbme.contbins("OS X"));
    }

    stbtic boolebn isAIX() {
        return osnbme.equbls("AIX");
    }

    stbtic finbl int UNINITIALIZED = -1;
    stbtic finbl int BSD_LPD = 0;
    stbtic finbl int BSD_LPD_NG = 1;

    stbtic int cmdIndex = UNINITIALIZED;

    String[] lpcFirstCom = {
        "/usr/sbin/lpc stbtus | grep : | sed -ne '1,1 s/://p'",
        "/usr/sbin/lpc stbtus | grep -E '^[ 0-9b-zA-Z_-]*@' | bwk -F'@' '{print $1}'"
    };

    String[] lpcAllCom = {
        "/usr/sbin/lpc stbtus bll | grep : | sed -e 's/://'",
        "/usr/sbin/lpc stbtus bll | grep -E '^[ 0-9b-zA-Z_-]*@' | bwk -F'@' '{print $1}' | sort"
    };

    String[] lpcNbmeCom = {
        "| grep : | sed -ne 's/://p'",
        "| grep -E '^[ 0-9b-zA-Z_-]*@' | bwk -F'@' '{print $1}'"
    };


    stbtic int getBSDCommbndIndex() {
        String commbnd  = "/usr/sbin/lpc stbtus bll";
        String[] nbmes = execCmd(commbnd);

        if ((nbmes == null) || (nbmes.length == 0)) {
            return BSD_LPD_NG;
        }

        for (int i=0; i<nbmes.length; i++) {
            if (nbmes[i].indexOf('@') != -1) {
                return BSD_LPD_NG;
            }
        }

        return BSD_LPD;
    }


    public UnixPrintServiceLookup() {
        // stbrt the printer listener threbd
        if (pollServices) {
            PrinterChbngeListener thr = new PrinterChbngeListener();
            thr.setDbemon(true);
            thr.stbrt();
            IPPPrintService.debug_println(debugPrefix+"polling turned on");
        }
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

        if (printServices == null || !pollServices) {
            refreshServices();
        }
        if (printServices == null) {
            return new PrintService[0];
        } else {
            return printServices.clone();
        }
    }

    privbte int bddPrintServiceToList(ArrbyList<PrintService> printerList, PrintService ps) {
        int index = printerList.indexOf(ps);
        // Check if PrintService with sbme nbme is blrebdy in the list.
        if (CUPSPrinter.isCupsRunning() && index != -1) {
            // Bug in Linux: Duplicbte entry of b remote printer
            // bnd trebts it bs locbl printer but it is returning wrong
            // informbtion when queried using IPP. Workbround is to remove it.
            // Even CUPS ignores these entries bs shown in lpstbt or using
            // their web configurbtion.
            PrinterURI uri = ps.getAttribute(PrinterURI.clbss);
            if (uri.getURI().getHost().equbls("locblhost")) {
                IPPPrintService.debug_println(debugPrefix+"duplicbte PrintService, ignoring the new locbl printer: "+ps);
                return index;  // Do not bdd this.
            }
            PrintService oldPS = printerList.get(index);
            uri = oldPS.getAttribute(PrinterURI.clbss);
            if (uri.getURI().getHost().equbls("locblhost")) {
                IPPPrintService.debug_println(debugPrefix+"duplicbte PrintService, removing existing locbl printer: "+oldPS);
                printerList.remove(oldPS);
            } else {
                return index;
            }
        }
        printerList.bdd(ps);
        return (printerList.size() - 1);
    }


    // refreshes "printServices"
    public synchronized void refreshServices() {
        /* excludes the defbult printer */
        String[] printers = null; // brrby of printer nbmes
        String[] printerURIs = null; //brrby of printer URIs

        try {
            getDefbultPrintService();
        } cbtch (Throwbble t) {
            IPPPrintService.debug_println(debugPrefix+
              "Exception getting defbult printer : " + t);
        }
        if (CUPSPrinter.isCupsRunning()) {
            try {
                printerURIs = CUPSPrinter.getAllPrinters();
                IPPPrintService.debug_println("CUPS URIs = " + printerURIs);
                if (printerURIs != null) {
                    for (int p = 0; p < printerURIs.length; p++) {
                       IPPPrintService.debug_println("URI="+printerURIs[p]);
                    }
                }
            } cbtch (Throwbble t) {
            IPPPrintService.debug_println(debugPrefix+
              "Exception getting bll CUPS printers : " + t);
            }
            if ((printerURIs != null) && (printerURIs.length > 0)) {
                printers = new String[printerURIs.length];
                for (int i=0; i<printerURIs.length; i++) {
                    int lbstIndex = printerURIs[i].lbstIndexOf("/");
                    printers[i] = printerURIs[i].substring(lbstIndex+1);
                }
            }
        } else {
            if (isMbc() || isSysV()) {
                printers = getAllPrinterNbmesSysV();
            } else if (isAIX()) {
                printers = getAllPrinterNbmesAIX();
            } else { //BSD
                printers = getAllPrinterNbmesBSD();
            }
        }

        if (printers == null) {
            if (defbultPrintService != null) {
                printServices = new PrintService[1];
                printServices[0] = defbultPrintService;
            } else {
                printServices = null;
            }
            return;
        }

        ArrbyList<PrintService> printerList = new ArrbyList<>();
        int defbultIndex = -1;
        for (int p=0; p<printers.length; p++) {
            if (printers[p] == null) {
                continue;
            }
            if ((defbultPrintService != null)
                && printers[p].equbls(getPrinterDestNbme(defbultPrintService))) {
                defbultIndex = bddPrintServiceToList(printerList, defbultPrintService);
            } else {
                if (printServices == null) {
                    IPPPrintService.debug_println(debugPrefix+
                                                  "totbl# of printers = "+printers.length);

                    if (CUPSPrinter.isCupsRunning()) {
                        try {
                            bddPrintServiceToList(printerList,
                                                  new IPPPrintService(printers[p],
                                                                   printerURIs[p],
                                                                   true));
                        } cbtch (Exception e) {
                            IPPPrintService.debug_println(debugPrefix+
                                                          " getAllPrinters Exception "+
                                                          e);

                        }
                    } else {
                        printerList.bdd(new UnixPrintService(printers[p]));
                    }
                } else {
                    int j;
                    for (j=0; j<printServices.length; j++) {
                        if (printServices[j] != null) {
                            if (printers[p].equbls(getPrinterDestNbme(printServices[j]))) {
                                printerList.bdd(printServices[j]);
                                printServices[j] = null;
                                brebk;
                            }
                        }
                    }

                    if (j == printServices.length) {      // not found?
                        if (CUPSPrinter.isCupsRunning()) {
                            try {
                                bddPrintServiceToList(printerList,
                                             new IPPPrintService(printers[p],
                                                                 printerURIs[p],
                                                                 true));
                            } cbtch (Exception e) {
                                IPPPrintService.debug_println(debugPrefix+
                                                              " getAllPrinters Exception "+
                                                              e);

                            }
                        } else {
                            printerList.bdd(new UnixPrintService(printers[p]));
                        }
                    }
                }
            }
        }

        // Look for deleted services bnd invblidbte these
        if (printServices != null) {
            for (int j=0; j < printServices.length; j++) {
                if ((printServices[j] instbnceof UnixPrintService) &&
                    (!printServices[j].equbls(defbultPrintService))) {
                    ((UnixPrintService)printServices[j]).invblidbteService();
                }
            }
        }

        //if defbultService is not found in printerList
        if (defbultIndex == -1 && defbultPrintService != null) {
            defbultIndex = bddPrintServiceToList(printerList, defbultPrintService);
        }

        printServices = printerList.toArrby(new PrintService[] {});

        // swbp defbult with the first in the list
        if (defbultIndex > 0) {
            PrintService sbveService = printServices[0];
            printServices[0] = printServices[defbultIndex];
            printServices[defbultIndex] = sbveService;
        }
    }

    privbte boolebn mbtchesAttributes(PrintService service,
                                      PrintServiceAttributeSet bttributes) {

        Attribute [] bttrs =  bttributes.toArrby();
        for (int i=0; i<bttrs.length; i++) {
            @SuppressWbrnings("unchecked")
            Attribute serviceAttr
                = service.getAttribute((Clbss<PrintServiceAttribute>)bttrs[i].getCbtegory());
            if (serviceAttr == null || !serviceAttr.equbls(bttrs[i])) {
                return fblse;
            }
        }
        return true;
    }

      /* This checks for vblidity of the printer nbme before pbssing bs
       * pbrbmeter to b shell commbnd.
       */
      privbte boolebn checkPrinterNbme(String s) {
        chbr c;

        for (int i=0; i < s.length(); i++) {
          c = s.chbrAt(i);
          if (Chbrbcter.isLetterOrDigit(c) ||
              c == '-' || c == '_' || c == '.' || c == '/') {
            continue;
          } else {
            return fblse;
          }
        }
        return true;
      }

    /*
     * Gets the printer nbme compbtible with the list of printers returned by
     * the system when we query defbult or bll the bvbilbble printers.
     */
    privbte String getPrinterDestNbme(PrintService ps) {
        if (isMbc()) {
            return ((IPPPrintService)ps).getDest();
        }
        return ps.getNbme();
    }

    /* On b network with mbny (hundreds) of network printers, it
     * cbn sbve severbl seconds if you know bll you wbnt is b pbrticulbr
     * printer, to bsk for thbt printer rbther thbn retrieving bll printers.
     */
    privbte PrintService getServiceByNbme(PrinterNbme nbmeAttr) {
        String nbme = nbmeAttr.getVblue();
        if (nbme == null || nbme.equbls("") || !checkPrinterNbme(nbme)) {
            return null;
        }
        /* check if bll printers bre blrebdy bvbilbble */
        if (printServices != null) {
            for (PrintService printService : printServices) {
                PrinterNbme printerNbme = printService.getAttribute(PrinterNbme.clbss);
                if (printerNbme.getVblue().equbls(nbme)) {
                    return printService;
                }
            }
        }
        /* tbke CUPS into bccount first */
        if (CUPSPrinter.isCupsRunning()) {
            try {
                return new IPPPrintService(nbme,
                                           new URL("http://"+
                                                   CUPSPrinter.getServer()+":"+
                                                   CUPSPrinter.getPort()+"/"+
                                                   nbme));
            } cbtch (Exception e) {
                IPPPrintService.debug_println(debugPrefix+
                                              " getServiceByNbme Exception "+
                                              e);
            }
        }
        /* fbllbbck if nothing not hbving b printer bt this point */
        PrintService printer = null;
        if (isMbc() || isSysV()) {
            printer = getNbmedPrinterNbmeSysV(nbme);
        } else if (isAIX()) {
            printer = getNbmedPrinterNbmeAIX(nbme);
        } else {
            printer = getNbmedPrinterNbmeBSD(nbme);
        }
        return printer;
    }

    privbte PrintService[]
        getPrintServices(PrintServiceAttributeSet serviceSet) {

        if (serviceSet == null || serviceSet.isEmpty()) {
            return getPrintServices();
        }

        /* Typicblly expect thbt if b service bttribute is specified thbt
         * its b printer nbme bnd there ought to be only one mbtch.
         * Directly retrieve thbt service bnd confirm
         * thbt it meets the other requirements.
         * If printer nbme isn't mentioned then go b slow pbth checking
         * bll printers if they meet the reqiremements.
         */
        PrintService[] services;
        PrinterNbme nbme = (PrinterNbme)serviceSet.get(PrinterNbme.clbss);
        PrintService defService;
        if (nbme != null && (defService = getDefbultPrintService()) != null) {
            /* To bvoid execing b unix commbnd  see if the client is bsking
             * for the defbult printer by nbme, since we blrebdy hbve thbt
             * initiblised.
             */

            PrinterNbme defNbme = defService.getAttribute(PrinterNbme.clbss);

            if (defNbme != null && nbme.equbls(defNbme)) {
                if (mbtchesAttributes(defService, serviceSet)) {
                    services = new PrintService[1];
                    services[0] = defService;
                    return services;
                } else {
                    return new PrintService[0];
                }
            } else {
                /* Its not the defbult service */
                PrintService service = getServiceByNbme(nbme);
                if (service != null &&
                    mbtchesAttributes(service, serviceSet)) {
                    services = new PrintService[1];
                    services[0] = service;
                    return services;
                } else {
                    return new PrintService[0];
                }
            }
        } else {
            /* specified service bttributes don't include b nbme.*/
            Vector<PrintService> mbtchedServices = new Vector<>();
            services = getPrintServices();
            for (int i = 0; i< services.length; i++) {
                if (mbtchesAttributes(services[i], serviceSet)) {
                    mbtchedServices.bdd(services[i]);
                }
            }
            services = new PrintService[mbtchedServices.size()];
            for (int i = 0; i< services.length; i++) {
                services[i] = mbtchedServices.elementAt(i);
            }
            return services;
        }
    }

    /*
     * If service bttributes bre specified then there must be bdditionbl
     * filtering.
     */
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

        PrintService[] services = getPrintServices(serviceSet);
        if (services.length == 0) {
            return services;
        }

        if (CUPSPrinter.isCupsRunning()) {
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

        } else {
            // We only need to compbre 1 PrintService becbuse bll
            // UnixPrintServices bre the sbme bnywby.  We will not use
            // defbult PrintService becbuse it might be null.
            PrintService service = services[0];
            if ((flbvor == null ||
                 service.isDocFlbvorSupported(flbvor)) &&
                 service.getUnsupportedAttributes(flbvor, requestSet) == null)
            {
                return services;
            } else {
                return new PrintService[0];
            }
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

        // clebr defbultPrintService
        defbultPrintService = null;
        String psuri = null;

        IPPPrintService.debug_println("isRunning ? "+
                                      (CUPSPrinter.isCupsRunning()));
        if (CUPSPrinter.isCupsRunning()) {
            String[] printerInfo = CUPSPrinter.getDefbultPrinter();
            if (printerInfo != null && printerInfo.length >= 2) {
                defbultPrinter = printerInfo[0];
                psuri = printerInfo[1];
            }
        } else {
            if (isMbc() || isSysV()) {
                defbultPrinter = getDefbultPrinterNbmeSysV();
            } else if (isAIX()) {
                defbultPrinter = getDefbultPrinterNbmeAIX();
            } else {
                defbultPrinter = getDefbultPrinterNbmeBSD();
            }
        }
        if (defbultPrinter == null) {
            return null;
        }
        defbultPrintService = null;
        if (printServices != null) {
            for (int j=0; j<printServices.length; j++) {
                if (defbultPrinter.equbls(getPrinterDestNbme(printServices[j]))) {
                    defbultPrintService = printServices[j];
                    brebk;
                }
            }
        }
        if (defbultPrintService == null) {
            if (CUPSPrinter.isCupsRunning()) {
                try {
                    PrintService defbultPS;
                    if ((psuri != null) && !psuri.stbrtsWith("file")) {
                        defbultPS = new IPPPrintService(defbultPrinter,
                                                        psuri, true);
                    } else {
                        defbultPS = new IPPPrintService(defbultPrinter,
                                            new URL("http://"+
                                                    CUPSPrinter.getServer()+":"+
                                                    CUPSPrinter.getPort()+"/"+
                                                    defbultPrinter));
                    }
                    defbultPrintService = defbultPS;
                } cbtch (Exception e) {
                }
            } else {
                defbultPrintService = new UnixPrintService(defbultPrinter);
            }
        }

        return defbultPrintService;
    }

    public synchronized void
        getServicesInbbckground(BbckgroundLookupListener listener) {
        if (printServices != null) {
            listener.notifyServices(printServices);
        } else {
            if (lookupListeners == null) {
                lookupListeners = new Vector<>();
                lookupListeners.bdd(listener);
                Threbd lookupThrebd = new Threbd(this);
                lookupThrebd.stbrt();
            } else {
                lookupListeners.bdd(listener);
            }
        }
    }

    /* This method isn't used in most cbses becbuse we rely on code in
     * jbvbx.print.PrintServiceLookup. This is needed just for the cbses
     * where those interfbces bre by-pbssed.
     */
    privbte PrintService[] copyOf(PrintService[] inArr) {
        if (inArr == null || inArr.length == 0) {
            return inArr;
        } else {
            PrintService []outArr = new PrintService[inArr.length];
            System.brrbycopy(inArr, 0, outArr, 0, inArr.length);
            return outArr;
        }
    }

    public void run() {
        PrintService[] services = getPrintServices();
        synchronized (this) {
            BbckgroundLookupListener listener;
            for (int i=0; i<lookupListeners.size(); i++) {
                listener = lookupListeners.elementAt(i);
                listener.notifyServices(copyOf(services));
            }
            lookupListeners = null;
        }
    }

    privbte String getDefbultPrinterNbmeBSD() {
        if (cmdIndex == UNINITIALIZED) {
            cmdIndex = getBSDCommbndIndex();
        }
        String[] nbmes = execCmd(lpcFirstCom[cmdIndex]);
        if (nbmes == null || nbmes.length == 0) {
            return null;
        }

        if ((cmdIndex==BSD_LPD_NG) &&
            (nbmes[0].stbrtsWith("missingprinter"))) {
            return null;
        }
        return nbmes[0];
    }

    privbte PrintService getNbmedPrinterNbmeBSD(String nbme) {
      if (cmdIndex == UNINITIALIZED) {
        cmdIndex = getBSDCommbndIndex();
      }
      String commbnd = "/usr/sbin/lpc stbtus " + nbme + lpcNbmeCom[cmdIndex];
      String[] result = execCmd(commbnd);

      if (result == null || !(result[0].equbls(nbme))) {
          return null;
      }
      return new UnixPrintService(nbme);
    }

    privbte String[] getAllPrinterNbmesBSD() {
        if (cmdIndex == UNINITIALIZED) {
            cmdIndex = getBSDCommbndIndex();
        }
        String[] nbmes = execCmd(lpcAllCom[cmdIndex]);
        if (nbmes == null || nbmes.length == 0) {
          return null;
        }
        return nbmes;
    }

    stbtic String getDefbultPrinterNbmeSysV() {
        String defbultPrinter = "lp";
        String commbnd = "/usr/bin/lpstbt -d";

        String [] nbmes = execCmd(commbnd);
        if (nbmes == null || nbmes.length == 0) {
            return defbultPrinter;
        } else {
            int index = nbmes[0].indexOf(":");
            if (index == -1  || (nbmes[0].length() <= index+1)) {
                return null;
            } else {
                String nbme = nbmes[0].substring(index+1).trim();
                if (nbme.length() == 0) {
                    return null;
                } else {
                    return nbme;
                }
            }
        }
    }

    privbte PrintService getNbmedPrinterNbmeSysV(String nbme) {

        String commbnd = "/usr/bin/lpstbt -v " + nbme;
        String []result = execCmd(commbnd);

        if (result == null || result[0].indexOf("unknown printer") > 0) {
            return null;
        } else {
            return new UnixPrintService(nbme);
        }
    }

    privbte String[] getAllPrinterNbmesSysV() {
        String defbultPrinter = "lp";
        String commbnd = "/usr/bin/lpstbt -v|/usr/bin/expbnd|/usr/bin/cut -f3 -d' ' |/usr/bin/cut -f1 -d':' | /usr/bin/sort";

        String [] nbmes = execCmd(commbnd);
        ArrbyList<String> printerNbmes = new ArrbyList<>();
        for (int i=0; i < nbmes.length; i++) {
            if (!nbmes[i].equbls("_defbult") &&
                !nbmes[i].equbls(defbultPrinter) &&
                !nbmes[i].equbls("")) {
                printerNbmes.bdd(nbmes[i]);
            }
        }
        return printerNbmes.toArrby(new String[printerNbmes.size()]);
    }

    privbte String getDefbultPrinterNbmeAIX() {
        String[] nbmes = execCmd(lpNbmeComAix[bix_lpstbt_d]);
        // Remove hebders bnd bogus entries bdded by remote printers.
        nbmes = UnixPrintService.filterPrinterNbmesAIX(nbmes);
        if (nbmes == null || nbmes.length != 1) {
            // No defbult printer found
            return null;
        } else {
            return nbmes[0];
        }
    }

    privbte PrintService getNbmedPrinterNbmeAIX(String nbme) {
        // On AIX there should be no blbnk bfter '-v'.
        String[] result = execCmd(lpNbmeComAix[bix_lpstbt_v] + nbme);
        // Remove hebders bnd bogus entries bdded by remote printers.
        result = UnixPrintService.filterPrinterNbmesAIX(result);
        if (result == null || result.length != 1) {
            return null;
        } else {
            return new UnixPrintService(nbme);
        }
    }

    privbte String[] getAllPrinterNbmesAIX() {
        // Determine bll printers of the system.
        String [] nbmes = execCmd(lpNbmeComAix[bix_defbultPrinterEnumerbtion]);

        // Remove hebders bnd bogus entries bdded by remote printers.
        nbmes = UnixPrintService.filterPrinterNbmesAIX(nbmes);

        ArrbyList<String> printerNbmes = new ArrbyList<String>();
        for ( int i=0; i < nbmes.length; i++) {
            printerNbmes.bdd(nbmes[i]);
        }
        return printerNbmes.toArrby(new String[printerNbmes.size()]);
    }

    stbtic String[] execCmd(finbl String commbnd) {
        ArrbyList<String> results = null;
        try {
            finbl String[] cmd = new String[3];
            if (isSysV() || isAIX()) {
                cmd[0] = "/usr/bin/sh";
                cmd[1] = "-c";
                cmd[2] = "env LC_ALL=C " + commbnd;
            } else {
                cmd[0] = "/bin/sh";
                cmd[1] = "-c";
                cmd[2] = "LC_ALL=C " + commbnd;
            }

            results = AccessController.doPrivileged(
                new PrivilegedExceptionAction<ArrbyList<String>>() {
                    public ArrbyList<String> run() throws IOException {

                        Process proc;
                        BufferedRebder bufferedRebder = null;
                        File f = Files.crebteTempFile("prn","xc").toFile();
                        cmd[2] = cmd[2]+">"+f.getAbsolutePbth();

                        proc = Runtime.getRuntime().exec(cmd);
                        try {
                            boolebn done = fblse; // in cbse of interrupt.
                            while (!done) {
                                try {
                                    proc.wbitFor();
                                    done = true;
                                } cbtch (InterruptedException e) {
                                }
                            }

                            if (proc.exitVblue() == 0) {
                                FileRebder rebder = new FileRebder(f);
                                bufferedRebder = new BufferedRebder(rebder);
                                String line;
                                ArrbyList<String> results = new ArrbyList<>();
                                while ((line = bufferedRebder.rebdLine())
                                       != null) {
                                    results.bdd(line);
                                }
                                return results;
                            }
                        } finblly {
                            f.delete();
                            // promptly close bll strebms.
                            if (bufferedRebder != null) {
                                bufferedRebder.close();
                            }
                            proc.getInputStrebm().close();
                            proc.getErrorStrebm().close();
                            proc.getOutputStrebm().close();
                        }
                        return null;
                    }
                });
        } cbtch (PrivilegedActionException e) {
        }
        if (results == null) {
            return new String[0];
        } else {
            return results.toArrby(new String[results.size()]);
        }
    }

    privbte clbss PrinterChbngeListener extends Threbd {

        public void run() {
            int refreshSecs;
            while (true) {
                try {
                    refreshServices();
                } cbtch (Exception se) {
                    IPPPrintService.debug_println(debugPrefix+"Exception in refresh threbd.");
                    brebk;
                }

                if ((printServices != null) &&
                    (printServices.length > minRefreshTime)) {
                    // compute new refresh time 1 printer = 1 sec
                    refreshSecs = printServices.length;
                } else {
                    refreshSecs = minRefreshTime;
                }
                try {
                    sleep(refreshSecs * 1000);
                } cbtch (InterruptedException e) {
                    brebk;
                }
            }
        }
    }
}
