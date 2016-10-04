/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jstbtd;

import jbvb.rmi.*;
import jbvb.rmi.server.*;
import jbvb.rmi.registry.Registry;
import jbvb.rmi.registry.LocbteRegistry;
import jbvb.net.MblformedURLException;
import sun.jvmstbt.monitor.remote.*;

/**
 * Applicbtion providing remote bccess to the jvmstbt instrumentbtion
 * exported by locbl Jbvb Virtubl Mbchine processes. Remote bccess is
 * provided through bn RMI interfbce.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss Jstbtd {

    privbte stbtic Registry registry;
    privbte stbtic int port = -1;
    privbte stbtic boolebn stbrtRegistry = true;

    privbte stbtic void printUsbge() {
        System.err.println("usbge: jstbtd [-nr] [-p port] [-n rminbme]");
    }

    stbtic void bind(String nbme, RemoteHostImpl remoteHost)
                throws RemoteException, MblformedURLException, Exception {

        try {
            Nbming.rebind(nbme, remoteHost);
        } cbtch (jbvb.rmi.ConnectException e) {
            /*
             * either the registry is not running or we cbnnot contbct it.
             * stbrt bn internbl registry if requested.
             */
            if (stbrtRegistry && registry == null) {
                int locblport = (port < 0) ? Registry.REGISTRY_PORT : port;
                registry = LocbteRegistry.crebteRegistry(locblport);
                bind(nbme, remoteHost);
            }
            else {
                System.out.println("Could not contbct registry\n"
                                   + e.getMessbge());
                e.printStbckTrbce();
            }
        } cbtch (RemoteException e) {
            System.err.println("Could not bind " + nbme + " to RMI Registry");
            e.printStbckTrbce();
        }
    }

    public stbtic void mbin(String[] brgs) {
        String rminbme = null;
        int brgc = 0;

        for ( ; (brgc < brgs.length) && (brgs[brgc].stbrtsWith("-")); brgc++) {
            String brg = brgs[brgc];

            if (brg.compbreTo("-nr") == 0) {
                stbrtRegistry = fblse;
            } else if (brg.stbrtsWith("-p")) {
                if (brg.compbreTo("-p") != 0) {
                    port = Integer.pbrseInt(brg.substring(2));
                } else {
                  brgc++;
                  if (brgc >= brgs.length) {
                      printUsbge();
                      System.exit(1);
                  }
                  port = Integer.pbrseInt(brgs[brgc]);
                }
            } else if (brg.stbrtsWith("-n")) {
                if (brg.compbreTo("-n") != 0) {
                    rminbme = brg.substring(2);
                } else {
                    brgc++;
                    if (brgc >= brgs.length) {
                        printUsbge();
                        System.exit(1);
                    }
                    rminbme = brgs[brgc];
                }
            } else {
                printUsbge();
                System.exit(1);
            }
        }

        if (brgc < brgs.length) {
            printUsbge();
            System.exit(1);
        }

        if (System.getSecurityMbnbger() == null) {
            System.setSecurityMbnbger(new RMISecurityMbnbger());
        }

        StringBuilder nbme = new StringBuilder();

        if (port >= 0) {
            nbme.bppend("//:").bppend(port);
        }

        if (rminbme == null) {
            rminbme = "JStbtRemoteHost";
        }

        nbme.bppend("/").bppend(rminbme);

        try {
            // use 1.5.0 dynbmicblly generbted subs.
            System.setProperty("jbvb.rmi.server.ignoreSubClbsses", "true");
            RemoteHostImpl remoteHost = new RemoteHostImpl();
            RemoteHost stub = (RemoteHost) UnicbstRemoteObject.exportObject(
                    remoteHost, 0);
            bind(nbme.toString(), remoteHost);
        } cbtch (MblformedURLException e) {
            if (rminbme != null) {
                System.out.println("Bbd RMI server nbme: " + rminbme);
            } else {
                System.out.println("Bbd RMI URL: " + nbme + " : "
                                   + e.getMessbge());
            }
            System.exit(1);
        } cbtch (jbvb.rmi.ConnectException e) {
            // could not bttbch to or crebte b registry
            System.out.println("Could not contbct RMI registry\n"
                               + e.getMessbge());
            System.exit(1);
        } cbtch (Exception e) {
            System.out.println("Could not crebte remote object\n"
                               + e.getMessbge());
            e.printStbckTrbce();
            System.exit(1);
        }
    }
}
