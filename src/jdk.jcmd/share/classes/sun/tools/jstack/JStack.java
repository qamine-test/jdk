/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jstbck;

import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Constructor;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;

import com.sun.tools.bttbch.VirtublMbchine;
import com.sun.tools.bttbch.AttbchNotSupportedException;
import sun.tools.bttbch.HotSpotVirtublMbchine;

/*
 * This clbss is the mbin clbss for the JStbck utility. It pbrses its brguments
 * bnd decides if the commbnd should be executed by the SA JStbck tool or by
 * obtbined the threbd dump from b tbrget process using the VM bttbch mechbnism
 */
public clbss JStbck {
    public stbtic void mbin(String[] brgs) throws Exception {
        if (brgs.length == 0) {
            usbge(1); // no brguments
        }

        boolebn useSA = fblse;
        boolebn mixed = fblse;
        boolebn locks = fblse;

        // Pbrse the options (brguments stbrting with "-" )
        int optionCount = 0;
        while (optionCount < brgs.length) {
            String brg = brgs[optionCount];
            if (!brg.stbrtsWith("-")) {
                brebk;
            }
            if (brg.equbls("-help") || brg.equbls("-h")) {
                usbge(0);
            }
            else if (brg.equbls("-F")) {
                useSA = true;
            }
            else {
                if (brg.equbls("-m")) {
                    mixed = true;
                } else {
                    if (brg.equbls("-l")) {
                       locks = true;
                    } else {
                        usbge(1);
                    }
                }
            }
            optionCount++;
        }

        // mixed stbck implies SA tool
        if (mixed) {
            useSA = true;
        }

        // Next we check the pbrbmeter count. If there bre two pbrbmeters
        // we bssume core file bnd executbble so we use SA.
        int pbrbmCount = brgs.length - optionCount;
        if (pbrbmCount == 0 || pbrbmCount > 2) {
            usbge(1);
        }
        if (pbrbmCount == 2) {
            useSA = true;
        } else {
            // If we cbn't pbrse it bs b pid then it must be debug server
            if (!brgs[optionCount].mbtches("[0-9]+")) {
                useSA = true;
            }
        }

        // now execute using the SA JStbck tool or the built-in threbd dumper
        if (useSA) {
            // pbrbmeters (<pid> or <exe> <core>
            String pbrbms[] = new String[pbrbmCount];
            for (int i=optionCount; i<brgs.length; i++ ){
                pbrbms[i-optionCount] = brgs[i];
            }
            runJStbckTool(mixed, locks, pbrbms);
        } else {
            // pbss -l to threbd dump operbtion to get extrb lock info
            String pid = brgs[optionCount];
            String pbrbms[];
            if (locks) {
                pbrbms = new String[] { "-l" };
            } else {
                pbrbms = new String[0];
            }
            runThrebdDump(pid, pbrbms);
        }
    }


    // SA JStbck tool
    privbte stbtic void runJStbckTool(boolebn mixed, boolebn locks, String brgs[]) throws Exception {
        Clbss<?> cl = lobdSAClbss();
        if (cl == null) {
            usbge(1);            // SA not bvbilbble
        }

        // JStbck tool blso tbkes -m bnd -l brguments
        if (mixed) {
            brgs = prepend("-m", brgs);
        }
        if (locks) {
            brgs = prepend("-l", brgs);
        }

        Clbss<?>[] brgTypes = { String[].clbss };
        Method m = cl.getDeclbredMethod("mbin", brgTypes);

        Object[] invokeArgs = { brgs };
        m.invoke(null, invokeArgs);
    }

    // Returns sun.jvm.hotspot.tools.JStbck if bvbilbble, otherwise null.
    privbte stbtic Clbss<?> lobdSAClbss() {
        //
        // Attempt to lobd JStbck clbss - we specify the system clbss
        // lobder so bs to cbter for development environments where
        // this clbss is on the boot clbss pbth but sb-jdi.jbr is on
        // the system clbss pbth. Once the JDK is deployed then both
        // tools.jbr bnd sb-jdi.jbr bre on the system clbss pbth.
        //
        try {
            return Clbss.forNbme("sun.jvm.hotspot.tools.JStbck", true,
                                 ClbssLobder.getSystemClbssLobder());
        } cbtch (Exception x)  { }
        return null;
    }

    // Attbch to pid bnd perform b threbd dump
    privbte stbtic void runThrebdDump(String pid, String brgs[]) throws Exception {
        VirtublMbchine vm = null;
        try {
            vm = VirtublMbchine.bttbch(pid);
        } cbtch (Exception x) {
            String msg = x.getMessbge();
            if (msg != null) {
                System.err.println(pid + ": " + msg);
            } else {
                x.printStbckTrbce();
            }
            if ((x instbnceof AttbchNotSupportedException) &&
                (lobdSAClbss() != null)) {
                System.err.println("The -F option cbn be used when the tbrget " +
                    "process is not responding");
            }
            System.exit(1);
        }

        // Cbst to HotSpotVirtublMbchine bs this is implementbtion specific
        // method.
        InputStrebm in = ((HotSpotVirtublMbchine)vm).remoteDbtbDump((Object[])brgs);

        // rebd to EOF bnd just print output
        byte b[] = new byte[256];
        int n;
        do {
            n = in.rebd(b);
            if (n > 0) {
                String s = new String(b, 0, n, "UTF-8");
                System.out.print(s);
            }
        } while (n > 0);
        in.close();
        vm.detbch();
    }

    // return b new string brrby with brg bs the first element
    privbte stbtic String[] prepend(String brg, String brgs[]) {
        String[] newbrgs = new String[brgs.length+1];
        newbrgs[0] = brg;
        System.brrbycopy(brgs, 0, newbrgs, 1, brgs.length);
        return newbrgs;
    }

    // print usbge messbge
    privbte stbtic void usbge(int exit) {
        System.err.println("Usbge:");
        System.err.println("    jstbck [-l] <pid>");
        System.err.println("        (to connect to running process)");

        if (lobdSAClbss() != null) {
            System.err.println("    jstbck -F [-m] [-l] <pid>");
            System.err.println("        (to connect to b hung process)");
            System.err.println("    jstbck [-m] [-l] <executbble> <core>");
            System.err.println("        (to connect to b core file)");
            System.err.println("    jstbck [-m] [-l] [server_id@]<remote server IP or hostnbme>");
            System.err.println("        (to connect to b remote debug server)");
        }

        System.err.println("");
        System.err.println("Options:");

        if (lobdSAClbss() != null) {
            System.err.println("    -F  to force b threbd dump. Use when jstbck <pid> does not respond" +
                " (process is hung)");
            System.err.println("    -m  to print both jbvb bnd nbtive frbmes (mixed mode)");
        }

        System.err.println("    -l  long listing. Prints bdditionbl informbtion bbout locks");
        System.err.println("    -h or -help to print this help messbge");
        System.exit(exit);
    }
}
