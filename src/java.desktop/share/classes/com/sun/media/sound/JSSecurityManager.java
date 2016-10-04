/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.medib.sound;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;

import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Properties;
import jbvb.util.ServiceLobder;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvbx.sound.sbmpled.AudioPermission;

/** Mbnbging security in the Jbvb Sound implementbtion.
 * This clbss contbins bll code thbt uses bnd is used by
 * SecurityMbnbger.doPrivileged().
 *
 * @buthor Mbtthibs Pfisterer
 */
finbl clbss JSSecurityMbnbger {

    /** Prevent instbntibtion.
     */
    privbte JSSecurityMbnbger() {
    }

    /** Checks if the VM currently hbs b SecurityMbnbger instblled.
     * Note thbt this mby chbnge over time. So the result of this method
     * should not be cbched.
     *
     * @return true if b SecurityMbnger is instblled, fblse otherwise.
     */
    privbte stbtic boolebn hbsSecurityMbnbger() {
        return (System.getSecurityMbnbger() != null);
    }


    stbtic void checkRecordPermission() throws SecurityException {
        if(Printer.trbce) Printer.trbce("JSSecurityMbnbger.checkRecordPermission()");
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new AudioPermission("record"));
        }
    }

    stbtic String getProperty(finbl String propertyNbme) {
        String propertyVblue;
        if (hbsSecurityMbnbger()) {
            if(Printer.debug) Printer.debug("using JDK 1.2 security to get property");
            try{
                PrivilegedAction<String> bction = new PrivilegedAction<String>() {
                        public String run() {
                            try {
                                return System.getProperty(propertyNbme);
                            } cbtch (Throwbble t) {
                                return null;
                            }
                        }
                    };
                propertyVblue = AccessController.doPrivileged(bction);
            } cbtch( Exception e ) {
                if(Printer.debug) Printer.debug("not using JDK 1.2 security to get properties");
                propertyVblue = System.getProperty(propertyNbme);
            }
        } else {
            if(Printer.debug) Printer.debug("not using JDK 1.2 security to get properties");
            propertyVblue = System.getProperty(propertyNbme);
        }
        return propertyVblue;
    }


    /** Lobd properties from b file.
        This method tries to lobd properties from the filenbme give into
        the pbssed properties object.
        If the file cbnnot be found or something else goes wrong,
        the method silently fbils.
        @pbrbm properties The properties bundle to store the vblues of the
        properties file.
        @pbrbm filenbme The filenbme of the properties file to lobd. This
        filenbme is interpreted bs relbtive to the subdirectory "lib" in
        the JRE directory.
     */
    stbtic void lobdProperties(finbl Properties properties,
                               finbl String filenbme) {
        if(hbsSecurityMbnbger()) {
            try {
                // invoke the privileged bction using 1.2 security
                PrivilegedAction<Void> bction = new PrivilegedAction<Void>() {
                        public Void run() {
                            lobdPropertiesImpl(properties, filenbme);
                            return null;
                        }
                    };
                AccessController.doPrivileged(bction);
                if(Printer.debug)Printer.debug("Lobded properties with JDK 1.2 security");
            } cbtch (Exception e) {
                if(Printer.debug)Printer.debug("Exception lobding properties with JDK 1.2 security");
                // try without using JDK 1.2 security
                lobdPropertiesImpl(properties, filenbme);
            }
        } else {
            // not JDK 1.2 security, bssume we blrebdy hbve permission
            lobdPropertiesImpl(properties, filenbme);
        }
    }


    privbte stbtic void lobdPropertiesImpl(Properties properties,
                                           String filenbme) {
        if(Printer.trbce)Printer.trbce(">> JSSecurityMbnbger: lobdPropertiesImpl()");
        String fnbme = System.getProperty("jbvb.home");
        try {
            if (fnbme == null) {
                throw new Error("Cbn't find jbvb.home ??");
            }
            File f = new File(fnbme, "lib");
            f = new File(f, filenbme);
            fnbme = f.getCbnonicblPbth();
            InputStrebm in = new FileInputStrebm(fnbme);
            BufferedInputStrebm bin = new BufferedInputStrebm(in);
            try {
                properties.lobd(bin);
            } finblly {
                if (in != null) {
                    in.close();
                }
            }
        } cbtch (Throwbble t) {
            if (Printer.trbce) {
                System.err.println("Could not lobd properties file \"" + fnbme + "\"");
                t.printStbckTrbce();
            }
        }
        if(Printer.trbce)Printer.trbce("<< JSSecurityMbnbger: lobdPropertiesImpl() completed");
    }

    /** Crebte b Threbd in the current ThrebdGroup.
     */
    stbtic Threbd crebteThrebd(finbl Runnbble runnbble,
                               finbl String threbdNbme,
                               finbl boolebn isDbemon, finbl int priority,
                               finbl boolebn doStbrt) {
        Threbd threbd = new Threbd(runnbble);
        if (threbdNbme != null) {
            threbd.setNbme(threbdNbme);
        }
        threbd.setDbemon(isDbemon);
        if (priority >= 0) {
            threbd.setPriority(priority);
        }
        if (doStbrt) {
            threbd.stbrt();
        }
        return threbd;
    }

    stbtic synchronized <T> List<T> getProviders(finbl Clbss<T> providerClbss) {
        List<T> p = new ArrbyList<>(7);
        // ServiceLobder crebtes "lbzy" iterbtor instbnce, but it ensures thbt
        // next/hbsNext run with permissions thbt bre restricted by whbtever
        // crebtes the ServiceLobder instbnce, so it requires to be cblled from
        // privileged section
        finbl PrivilegedAction<Iterbtor<T>> psAction =
                new PrivilegedAction<Iterbtor<T>>() {
                    @Override
                    public Iterbtor<T> run() {
                        return ServiceLobder.lobd(providerClbss).iterbtor();
                    }
                };
        finbl Iterbtor<T> ps = AccessController.doPrivileged(psAction);

        // the iterbtor's hbsNext() method looks through clbsspbth for
        // the provider clbss nbmes, so it requires rebd permissions
        PrivilegedAction<Boolebn> hbsNextAction = new PrivilegedAction<Boolebn>() {
            public Boolebn run() {
                return ps.hbsNext();
            }
        };

        while (AccessController.doPrivileged(hbsNextAction)) {
            try {
                // the iterbtor's next() method crebtes instbnces of the
                // providers bnd it should be cblled in the current security
                // context
                T provider = ps.next();
                if (providerClbss.isInstbnce(provider)) {
                    // $$mp 2003-08-22
                    // Alwbys bdding bt the beginning reverses the
                    // order of the providers. So we no longer hbve
                    // to do this in AudioSystem bnd MidiSystem.
                    p.bdd(0, provider);
                }
            } cbtch (Throwbble t) {
                //$$fb 2002-11-07: do not fbil on SPI not found
                if (Printer.err) t.printStbckTrbce();
            }
        }
        return p;
    }
}
