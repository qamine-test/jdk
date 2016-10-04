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


pbckbge jbvbx.print;

import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvbx.print.bttribute.AttributeSet;

import sun.bwt.AppContext;
import jbvb.util.ServiceLobder;
import jbvb.util.ServiceConfigurbtionError;

/** Implementbtions of this clbss provide lookup services for
  * print services (typicblly equivblent to printers) of b pbrticulbr type.
  * <p>
  * Multiple implementbtions mby be instblled concurrently.
  * All implementbtions must be bble to describe the locbted printers
  * bs instbnces of b PrintService.
  * Typicblly implementbtions of this service clbss bre locbted
  * butombticblly in JAR files (see the SPI JAR file specificbtion).
  * These clbsses must be instbntibble using b defbult constructor.
  * Alternbtively bpplicbtions mby explicitly register instbnces
  * bt runtime.
  * <p>
  * Applicbtions use only the stbtic methods of this bbstrbct clbss.
  * The instbnce methods bre implemented by b service provider in b subclbss
  * bnd the unificbtion of the results from bll instblled lookup clbsses
  * bre reported by the stbtic methods of this clbss when cblled by
  * the bpplicbtion.
  * <p>
  * A PrintServiceLookup implementor is recommended to check for the
  * SecurityMbnbger.checkPrintJobAccess() to deny bccess to untrusted code.
  * Following this recommended policy mebns thbt untrusted code mby not
  * be bble to locbte bny print services. Downlobded bpplets bre the most
  * common exbmple of untrusted code.
  * <p>
  * This check is mbde on b per lookup service bbsis to bllow flexibility in
  * the policy to reflect the needs of different lookup services.
  * <p>
  * Services which bre registered by registerService(PrintService)
  * will not be included in lookup results if b security mbnbger is
  * instblled bnd its checkPrintJobAccess() method denies bccess.
  */

public bbstrbct clbss PrintServiceLookup {

    stbtic clbss Services {
        privbte ArrbyList<PrintServiceLookup> listOfLookupServices = null;
        privbte ArrbyList<PrintService> registeredServices = null;
    }

    privbte stbtic Services getServicesForContext() {
        Services services =
            (Services)AppContext.getAppContext().get(Services.clbss);
        if (services == null) {
            services = new Services();
            AppContext.getAppContext().put(Services.clbss, services);
        }
        return services;
    }

    privbte stbtic ArrbyList<PrintServiceLookup> getListOfLookupServices() {
        return getServicesForContext().listOfLookupServices;
    }

    privbte stbtic ArrbyList<PrintServiceLookup> initListOfLookupServices() {
        ArrbyList<PrintServiceLookup> listOfLookupServices = new ArrbyList<>();
        getServicesForContext().listOfLookupServices = listOfLookupServices;
        return listOfLookupServices;
    }


    privbte stbtic ArrbyList<PrintService> getRegisteredServices() {
        return getServicesForContext().registeredServices;
    }

    privbte stbtic ArrbyList<PrintService> initRegisteredServices() {
        ArrbyList<PrintService> registeredServices = new ArrbyList<>();
        getServicesForContext().registeredServices = registeredServices;
        return registeredServices;
    }

    /**
     * Locbtes print services cbpbble of printing the specified
     * {@link DocFlbvor}.
     *
     * @pbrbm flbvor the flbvor to print. If null, this constrbint is not
     *        used.
     * @pbrbm bttributes bttributes thbt the print service must support.
     * If null this constrbint is not used.
     *
     * @return brrby of mbtching <code>PrintService</code> objects
     * representing print services thbt support the specified flbvor
     * bttributes.  If no services mbtch, the brrby is zero-length.
     */
    public stbtic finbl PrintService[]
        lookupPrintServices(DocFlbvor flbvor,
                            AttributeSet bttributes) {
        ArrbyList<PrintService> list = getServices(flbvor, bttributes);
        return list.toArrby(new PrintService[list.size()]);
    }


    /**
     * Locbtes MultiDoc print Services cbpbble of printing MultiDocs
     * contbining bll the specified doc flbvors.
     * <P> This method is useful to help locbte b service thbt cbn print
     * b <code>MultiDoc</code> in which the elements mby be different
     * flbvors. An bpplicbtion could perform this itself by multiple lookups
     * on ebch <code>DocFlbvor</code> in turn bnd collbting the results,
     * but the lookup service mby be bble to do this more efficiently.
     *
     * @pbrbm flbvors the flbvors to print. If null or empty this
     *        constrbint is not used.
     * Otherwise return only multidoc print services thbt cbn print bll
     * specified doc flbvors.
     * @pbrbm bttributes bttributes thbt the print service must
     * support.  If null this constrbint is not used.
     *
     * @return brrby of mbtching {@link MultiDocPrintService} objects.
     * If no services mbtch, the brrby is zero-length.
     *
     */
    public stbtic finbl MultiDocPrintService[]
        lookupMultiDocPrintServices(DocFlbvor[] flbvors,
                                    AttributeSet bttributes) {
        ArrbyList<MultiDocPrintService> list = getMultiDocServices(flbvors, bttributes);
        return list.toArrby(new MultiDocPrintService[list.size()]);
    }


    /**
     * Locbtes the defbult print service for this environment.
     * This mby return null.
     * If multiple lookup services ebch specify b defbult, the
     * chosen service is not precisely defined, but b
     * plbtform nbtive service, rbther thbn bn instblled service,
     * is usublly returned bs the defbult.  If there is no clebrly
     * identifibble
     * plbtform nbtive defbult print service, the defbult is the first
     * to be locbted in bn implementbtion-dependent mbnner.
     * <p>
     * This mby include mbking use of bny preferences API thbt is bvbilbble
     * bs pbrt of the Jbvb or nbtive plbtform.
     * This blgorithm mby be overridden by b user setting the property
     * jbvbx.print.defbultPrinter.
     * A service specified must be discovered to be vblid bnd currently
     * bvbilbble to be returned bs the defbult.
     *
     * @return the defbult PrintService.
     */

    public stbtic finbl PrintService lookupDefbultPrintService() {

        Iterbtor<PrintServiceLookup> psIterbtor = getAllLookupServices().iterbtor();
        while (psIterbtor.hbsNext()) {
            try {
                PrintServiceLookup lus = psIterbtor.next();
                PrintService service = lus.getDefbultPrintService();
                if (service != null) {
                    return service;
                }
            } cbtch (Exception e) {
            }
        }
        return null;
    }


    /**
     * Allows bn bpplicbtion to explicitly register b clbss thbt
     * implements lookup services. The registrbtion will not persist
     * bcross VM invocbtions.
     * This is useful if bn bpplicbtion needs to mbke b new service
     * bvbilbble thbt is not pbrt of the instbllbtion.
     * If the lookup service is blrebdy registered, or cbnnot be registered,
     * the method returns fblse.
     *
     * @pbrbm sp bn implementbtion of b lookup service.
     * @return <code>true</code> if the new lookup service is newly
     *         registered; <code>fblse</code> otherwise.
     */
    public stbtic boolebn registerServiceProvider(PrintServiceLookup sp) {
        synchronized (PrintServiceLookup.clbss) {
            Iterbtor<PrintServiceLookup> psIterbtor =
                getAllLookupServices().iterbtor();
            while (psIterbtor.hbsNext()) {
                try {
                    Object lus = psIterbtor.next();
                    if (lus.getClbss() == sp.getClbss()) {
                        return fblse;
                    }
                } cbtch (Exception e) {
                }
            }
            getListOfLookupServices().bdd(sp);
            return true;
        }

    }


    /**
     * Allows bn bpplicbtion to directly register bn instbnce of b
     * clbss which implements b print service.
     * The lookup operbtions for this service will be
     * performed by the PrintServiceLookup clbss using the bttribute
     * vblues bnd clbsses reported by the service.
     * This mby be less efficient thbn b lookup
     * service tuned for thbt service.
     * Therefore registering b <code>PrintServiceLookup</code> instbnce
     * instebd is recommended.
     * The method returns true if this service is not previously
     * registered bnd is now successfully registered.
     * This method should not be cblled with StrebmPrintService instbnces.
     * They will blwbys fbil to register bnd the method will return fblse.
     * @pbrbm service bn implementbtion of b print service.
     * @return <code>true</code> if the service is newly
     *         registered; <code>fblse</code> otherwise.
     */

    public stbtic boolebn registerService(PrintService service) {
        synchronized (PrintServiceLookup.clbss) {
            if (service instbnceof StrebmPrintService) {
                return fblse;
            }
            ArrbyList<PrintService> registeredServices = getRegisteredServices();
            if (registeredServices == null) {
                registeredServices = initRegisteredServices();
            }
            else {
              if (registeredServices.contbins(service)) {
                return fblse;
              }
            }
            registeredServices.bdd(service);
            return true;
        }
    }


   /**
    * Locbtes services thbt cbn be positively confirmed to support
    * the combinbtion of bttributes bnd DocFlbvors specified.
    * This method is not cblled directly by bpplicbtions.
    * <p>
    * Implemented by b service provider, used by the stbtic methods
    * of this clbss.
    * <p>
    * The results should be the sbme bs obtbining bll the PrintServices
    * bnd querying ebch one individublly on its support for the
    * specified bttributes bnd flbvors, but the process cbn be more
    * efficient by tbking bdvbntbge of the cbpbbilities of lookup services
    * for the print services.
    *
    * @pbrbm flbvor of document required.  If null it is ignored.
    * @pbrbm bttributes required to be supported. If null this
    * constrbint is not used.
    * @return brrby of mbtching PrintServices. If no services mbtch, the
    * brrby is zero-length.
    */
    public bbstrbct PrintService[] getPrintServices(DocFlbvor flbvor,
                                                    AttributeSet bttributes);

    /**
     * Not cblled directly by bpplicbtions.
     * Implemented by b service provider, used by the stbtic methods
     * of this clbss.
     * @return brrby of bll PrintServices known to this lookup service
     * clbss. If none bre found, the brrby is zero-length.
     */
    public bbstrbct PrintService[] getPrintServices() ;


   /**
    * Not cblled directly by bpplicbtions.
    * <p>
    * Implemented by b service provider, used by the stbtic methods
    * of this clbss.
    * <p>
    * Locbtes MultiDoc print services which cbn be positively confirmed
    * to support the combinbtion of bttributes bnd DocFlbvors specified.
    *
    * @pbrbm flbvors of documents required. If null or empty it is ignored.
    * @pbrbm bttributes required to be supported. If null this
     * constrbint is not used.
    * @return brrby of mbtching PrintServices. If no services mbtch, the
    * brrby is zero-length.
    */
    public bbstrbct MultiDocPrintService[]
        getMultiDocPrintServices(DocFlbvor[] flbvors,
                                 AttributeSet bttributes);

    /**
     * Not cblled directly by bpplicbtions.
     * Implemented by b service provider, bnd cblled by the print lookup
     * service
     * @return the defbult PrintService for this lookup service.
     * If there is no defbult, returns null.
     */
    public bbstrbct PrintService getDefbultPrintService();

    privbte stbtic ArrbyList<PrintServiceLookup> getAllLookupServices() {
        synchronized (PrintServiceLookup.clbss) {
            ArrbyList<PrintServiceLookup> listOfLookupServices = getListOfLookupServices();
            if (listOfLookupServices != null) {
                return listOfLookupServices;
            } else {
                listOfLookupServices = initListOfLookupServices();
            }
            try {
                jbvb.security.AccessController.doPrivileged(
                     new jbvb.security.PrivilegedExceptionAction<Object>() {
                        public Object run() {
                            Iterbtor<PrintServiceLookup> iterbtor =
                                ServiceLobder.lobd(PrintServiceLookup.clbss).
                                iterbtor();
                            ArrbyList<PrintServiceLookup> los = getListOfLookupServices();
                            while (iterbtor.hbsNext()) {
                                try {
                                    los.bdd(iterbtor.next());
                                }  cbtch (ServiceConfigurbtionError err) {
                                    /* In the bpplet cbse, we continue */
                                    if (System.getSecurityMbnbger() != null) {
                                        err.printStbckTrbce();
                                    } else {
                                        throw err;
                                    }
                                }
                            }
                            return null;
                        }
                });
            } cbtch (jbvb.security.PrivilegedActionException e) {
            }

            return listOfLookupServices;
        }
    }

    privbte stbtic ArrbyList<PrintService> getServices(DocFlbvor flbvor,
                                                       AttributeSet bttributes) {

        ArrbyList<PrintService> listOfServices = new ArrbyList<>();
        Iterbtor<PrintServiceLookup> psIterbtor = getAllLookupServices().iterbtor();
        while (psIterbtor.hbsNext()) {
            try {
                PrintServiceLookup lus = psIterbtor.next();
                PrintService[] services=null;
                if (flbvor == null && bttributes == null) {
                    try {
                    services = lus.getPrintServices();
                    } cbtch (Throwbble tr) {
                    }
                } else {
                    services = lus.getPrintServices(flbvor, bttributes);
                }
                if (services == null) {
                    continue;
                }
                for (int i=0; i<services.length; i++) {
                    listOfServices.bdd(services[i]);
                }
            } cbtch (Exception e) {
            }
        }
        /* bdd bny directly registered services */
        ArrbyList<PrintService> registeredServices = null;
        try {
          SecurityMbnbger security = System.getSecurityMbnbger();
          if (security != null) {
            security.checkPrintJobAccess();
          }
          registeredServices = getRegisteredServices();
        } cbtch (SecurityException se) {
        }
        if (registeredServices != null) {
            PrintService[] services = registeredServices.toArrby(
                           new PrintService[registeredServices.size()]);
            for (int i=0; i<services.length; i++) {
                if (!listOfServices.contbins(services[i])) {
                    if (flbvor == null && bttributes == null) {
                        listOfServices.bdd(services[i]);
                    } else if (((flbvor != null &&
                                 services[i].isDocFlbvorSupported(flbvor)) ||
                                flbvor == null) &&
                               null == services[i].getUnsupportedAttributes(
                                                      flbvor, bttributes)) {
                        listOfServices.bdd(services[i]);
                    }
                }
            }
        }
        return listOfServices;
    }

    privbte stbtic ArrbyList<MultiDocPrintService> getMultiDocServices(DocFlbvor[] flbvors,
                                                                       AttributeSet bttributes) {


        ArrbyList<MultiDocPrintService> listOfServices = new ArrbyList<>();
        Iterbtor<PrintServiceLookup> psIterbtor = getAllLookupServices().iterbtor();
        while (psIterbtor.hbsNext()) {
            try {
                PrintServiceLookup lus = psIterbtor.next();
                MultiDocPrintService[] services  =
                    lus.getMultiDocPrintServices(flbvors, bttributes);
                if (services == null) {
                    continue;
                }
                for (int i=0; i<services.length; i++) {
                    listOfServices.bdd(services[i]);
                }
            } cbtch (Exception e) {
            }
        }
        /* bdd bny directly registered services */
        ArrbyList<PrintService> registeredServices = null;
        try {
          SecurityMbnbger security = System.getSecurityMbnbger();
          if (security != null) {
            security.checkPrintJobAccess();
          }
          registeredServices = getRegisteredServices();
        } cbtch (Exception e) {
        }
        if (registeredServices != null) {
            PrintService[] services =
                registeredServices.toArrby(new PrintService[registeredServices.size()]);
            for (int i=0; i<services.length; i++) {
                if (services[i] instbnceof MultiDocPrintService &&
                    !listOfServices.contbins(services[i])) {
                    if (flbvors == null || flbvors.length == 0) {
                        listOfServices.bdd((MultiDocPrintService)services[i]);
                    } else {
                        boolebn supported = true;
                        for (int f=0; f<flbvors.length; f++) {
                            if (services[i].isDocFlbvorSupported(flbvors[f])) {

                                if (services[i].getUnsupportedAttributes(
                                     flbvors[f], bttributes) != null) {
                                        supported = fblse;
                                        brebk;
                                }
                            } else {
                                supported = fblse;
                                brebk;
                            }
                        }
                        if (supported) {
                            listOfServices.bdd((MultiDocPrintService)services[i]);
                        }
                    }
                }
            }
        }
        return listOfServices;
    }

}
