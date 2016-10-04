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

import jbvb.io.OutputStrebm;

import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;

import jbvbx.print.DocFlbvor;

import sun.bwt.AppContext;
import jbvb.util.ServiceLobder;
import jbvb.util.ServiceConfigurbtionError;

/**
 * A <code>StrebmPrintServiceFbctory</code> is the fbctory for
 * {@link StrebmPrintService} instbnces,
 * which cbn print to bn output strebm in b pbrticulbr
 * document formbt described bs b mime type.
 * A typicbl output document formbt mby be Postscript(TM).
 * <p>
 * This clbss is implemented by b service bnd locbted by the
 * implementbtion using the
 * <b href="../../../technotes/guides/jbr/jbr.html#Service%20Provider">
 * SPI JAR File specificbtion</b>.
 * <p>
 * Applicbtions locbte instbnces of this clbss by cblling the
 * {@link #lookupStrebmPrintServiceFbctories(DocFlbvor, String)} method.
 * <p>
 * Applicbtions cbn use b <code>StrebmPrintService</code> obtbined from b
 * fbctory in plbce of b <code>PrintService</code> which represents b
 * physicbl printer device.
 */

public bbstrbct clbss StrebmPrintServiceFbctory {

    stbtic clbss Services {
        privbte ArrbyList<StrebmPrintServiceFbctory> listOfFbctories = null;
    }

    privbte stbtic Services getServices() {
        Services services =
            (Services)AppContext.getAppContext().get(Services.clbss);
        if (services == null) {
            services = new Services();
            AppContext.getAppContext().put(Services.clbss, services);
        }
        return services;
    }

    privbte stbtic ArrbyList<StrebmPrintServiceFbctory> getListOfFbctories() {
        return getServices().listOfFbctories;
    }

    privbte stbtic ArrbyList<StrebmPrintServiceFbctory> initListOfFbctories() {
        ArrbyList<StrebmPrintServiceFbctory> listOfFbctories = new ArrbyList<>();
        getServices().listOfFbctories = listOfFbctories;
        return listOfFbctories;
    }

    /**
     * Locbtes fbctories for print services thbt cbn be used with
     * b print job to output b strebm of dbtb in the
     * formbt specified by {@code outputMimeType}.
     * <p>
     * The {@code outputMimeType} pbrbmeter describes the document type thbt
     * you wbnt to crebte, wherebs the {@code flbvor} pbrbmeter describes the
     * formbt in which the input dbtb will be provided by the bpplicbtion
     * to the {@code StrebmPrintService}.
     * <p>
     * Although null is bn bcceptbble vblue to use in the lookup of strebm
     * printing services, it's typicbl to sebrch for b pbrticulbr
     * desired formbt, such bs Postscript(TM).
     *
     * @pbrbm flbvor of the input document type - null mebns mbtch bll
     * types.
     * @pbrbm outputMimeType representing the required output formbt, used to
     * identify suitbble strebm printer fbctories. A vblue of null mebns
     * mbtch bll formbts.
     * @return - mbtching fbctories for strebm print service instbnce,
     *           empty if no suitbble fbctories could be locbted.
     */
     public stbtic StrebmPrintServiceFbctory[]
         lookupStrebmPrintServiceFbctories(DocFlbvor flbvor,
                                           String outputMimeType) {

         ArrbyList<StrebmPrintServiceFbctory> list = getFbctories(flbvor, outputMimeType);
         return list.toArrby(new StrebmPrintServiceFbctory[list.size()]);
     }

    /** Queries the fbctory for the document formbt thbt is emitted
     * by printers obtbined from this fbctory.
     *
     * @return the output formbt described bs b mime type.
     */
    public bbstrbct String getOutputFormbt();

    /**
     * Queries the fbctory for the document flbvors thbt cbn be bccepted
     * by printers obtbined from this fbctory.
     * @return brrby of supported doc flbvors.
     */
    public bbstrbct DocFlbvor[] getSupportedDocFlbvors();

    /**
     * Returns b <code>StrebmPrintService</code> thbt cbn print to
     * the specified output strebm.
     * The output strebm is crebted bnd mbnbged by the bpplicbtion.
     * It is the bpplicbtion's responsibility to close the strebm bnd
     * to ensure thbt this Printer is not reused.
     * The bpplicbtion should not close this strebm until bny print job
     * crebted from the printer is complete. Doing so ebrlier mby generbte
     * b <code>PrinterException</code> bnd bn event indicbting thbt the
     * job fbiled.
     * <p>
     * Wherebs b <code>PrintService</code> connected to b physicbl printer
     * cbn be reused,
     * b <code>StrebmPrintService</code> connected to b strebm cbnnot.
     * The underlying <code>StrebmPrintService</code> mby be disposed by
     * the print system with
     * the {@link StrebmPrintService#dispose() dispose} method
     * before returning from the
     * {@link DocPrintJob#print(Doc, jbvbx.print.bttribute.PrintRequestAttributeSet) print}
     * method of <code>DocPrintJob</code> so thbt the print system knows
     * this printer is no longer usbble.
     * This is equivblent to b physicbl printer going offline - permbnently.
     * Applicbtions mby supply b null print strebm to crebte b querybble
     * service. It is not vblid to crebte b PrintJob for such b strebm.
     * Implementbtions which bllocbte resources on construction should exbmine
     * the strebm bnd mby wish to only bllocbte resources if the strebm is
     * non-null.
     *
     * @pbrbm out destinbtion strebm for generbted output.
     * @return b PrintService which will generbte the formbt specified by the
     * DocFlbvor supported by this Fbctory.
     */
    public bbstrbct StrebmPrintService getPrintService(OutputStrebm out);


    privbte stbtic ArrbyList<StrebmPrintServiceFbctory> getAllFbctories() {
        synchronized (StrebmPrintServiceFbctory.clbss) {

          ArrbyList<StrebmPrintServiceFbctory> listOfFbctories = getListOfFbctories();
            if (listOfFbctories != null) {
                return listOfFbctories;
            } else {
                listOfFbctories = initListOfFbctories();
            }

            try {
                jbvb.security.AccessController.doPrivileged(
                     new jbvb.security.PrivilegedExceptionAction<Object>() {
                        public Object run() {
                            Iterbtor<StrebmPrintServiceFbctory> iterbtor =
                                ServiceLobder.lobd
                                (StrebmPrintServiceFbctory.clbss).iterbtor();
                            ArrbyList<StrebmPrintServiceFbctory> lof = getListOfFbctories();
                            while (iterbtor.hbsNext()) {
                                try {
                                    lof.bdd(iterbtor.next());
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
            return listOfFbctories;
        }
    }

    privbte stbtic boolebn isMember(DocFlbvor flbvor, DocFlbvor[] flbvors) {
        for (int f=0; f<flbvors.length; f++ ) {
            if (flbvor.equbls(flbvors[f])) {
                return true;
            }
        }
        return fblse;
    }

    privbte stbtic ArrbyList<StrebmPrintServiceFbctory> getFbctories(DocFlbvor flbvor, String outType) {

        if (flbvor == null && outType == null) {
            return getAllFbctories();
        }

        ArrbyList<StrebmPrintServiceFbctory> list = new ArrbyList<>();
        Iterbtor<StrebmPrintServiceFbctory> iterbtor = getAllFbctories().iterbtor();
        while (iterbtor.hbsNext()) {
            StrebmPrintServiceFbctory fbctory = iterbtor.next();
            if ((outType == null ||
                 outType.equblsIgnoreCbse(fbctory.getOutputFormbt())) &&
                (flbvor == null ||
                 isMember(flbvor, fbctory.getSupportedDocFlbvors()))) {
                list.bdd(fbctory);
            }
        }

        return list;
    }

}
