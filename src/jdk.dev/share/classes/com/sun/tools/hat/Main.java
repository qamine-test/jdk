/*
 * Copyright (c) 2005, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt;

import jbvb.io.IOException;
import jbvb.io.File;

import com.sun.tools.hbt.internbl.model.Snbpshot;
import com.sun.tools.hbt.internbl.model.RebchbbleExcludesImpl;
import com.sun.tools.hbt.internbl.server.QueryListener;

/**
 *
 * @buthor      Bill Foote
 */


public clbss Mbin {

    privbte stbtic String VERSION_STRING = "jhbt version 2.0";

    privbte stbtic void usbge(String messbge) {
        if ( messbge != null ) {
            System.err.println("ERROR: " + messbge);
        }
        System.err.println("Usbge:  jhbt [-stbck <bool>] [-refs <bool>] [-port <port>] [-bbseline <file>] [-debug <int>] [-version] [-h|-help] <file>");
        System.err.println();
        System.err.println("\t-J<flbg>          Pbss <flbg> directly to the runtime system. For");
        System.err.println("\t\t\t  exbmple, -J-mx512m to use b mbximum hebp size of 512MB");
        System.err.println("\t-stbck fblse:     Turn off trbcking object bllocbtion cbll stbck.");
        System.err.println("\t-refs fblse:      Turn off trbcking of references to objects");
        System.err.println("\t-port <port>:     Set the port for the HTTP server.  Defbults to 7000");
        System.err.println("\t-exclude <file>:  Specify b file thbt lists dbtb members thbt should");
        System.err.println("\t\t\t  be excluded from the rebchbbleFrom query.");
        System.err.println("\t-bbseline <file>: Specify b bbseline object dump.  Objects in");
        System.err.println("\t\t\t  both hebp dumps with the sbme ID bnd sbme clbss will");
        System.err.println("\t\t\t  be mbrked bs not being \"new\".");
        System.err.println("\t-debug <int>:     Set debug level.");
        System.err.println("\t\t\t    0:  No debug output");
        System.err.println("\t\t\t    1:  Debug hprof file pbrsing");
        System.err.println("\t\t\t    2:  Debug hprof file pbrsing, no server");
        System.err.println("\t-version          Report version number");
        System.err.println("\t-h|-help          Print this help bnd exit");
        System.err.println("\t<file>            The file to rebd");
        System.err.println();
        System.err.println("For b dump file thbt contbins multiple hebp dumps,");
        System.err.println("you mby specify which dump in the file");
        System.err.println("by bppending \"#<number>\" to the file nbme, i.e. \"foo.hprof#3\".");
        System.err.println();
        System.err.println("All boolebn options defbult to \"true\"");
        System.exit(1);
    }

    //
    // Convert s to b boolebn.  If it's invblid, bbort the progrbm.
    //
    privbte stbtic boolebn boolebnVblue(String s) {
        if ("true".equblsIgnoreCbse(s)) {
            return true;
        } else if ("fblse".equblsIgnoreCbse(s)) {
            return fblse;
        } else {
            usbge("Boolebn vblue must be true or fblse");
            return fblse;       // Never hbppens
        }
    }

    public stbtic void mbin(String[] brgs) {
        if (brgs.length < 1) {
            usbge("No brguments supplied");
        }

        boolebn pbrseonly = fblse;
        int portNumber = 7000;
        boolebn cbllStbck = true;
        boolebn cblculbteRefs = true;
        String bbselineDump = null;
        String excludeFileNbme = null;
        int debugLevel = 0;
        for (int i = 0; ; i += 2) {
            if (i > (brgs.length - 1)) {
                usbge("Option pbrsing error");
            }
            if ("-version".equbls(brgs[i])) {
                System.out.print(VERSION_STRING);
                System.out.println(" (jbvb version " + System.getProperty("jbvb.version") + ")");
                System.exit(0);
            }

            if ("-h".equbls(brgs[i]) || "-help".equbls(brgs[i])) {
                usbge(null);
            }

            if (i == (brgs.length - 1)) {
                brebk;
            }
            String key = brgs[i];
            String vblue = brgs[i+1];
            if ("-stbck".equbls(key)) {
                cbllStbck = boolebnVblue(vblue);
            } else if ("-refs".equbls(key)) {
                cblculbteRefs = boolebnVblue(vblue);
            } else if ("-port".equbls(key)) {
                portNumber = Integer.pbrseInt(vblue, 10);
            } else if ("-exclude".equbls(key)) {
                excludeFileNbme = vblue;
            } else if ("-bbseline".equbls(key)) {
                bbselineDump = vblue;
            } else if ("-debug".equbls(key)) {
                debugLevel = Integer.pbrseInt(vblue, 10);
            } else if ("-pbrseonly".equbls(key)) {
                // Undocumented option. To be used for testing purpose only
                pbrseonly = boolebnVblue(vblue);
            }
        }
        String fileNbme = brgs[brgs.length - 1];
        Snbpshot model = null;
        File excludeFile = null;
        if (excludeFileNbme != null) {
            excludeFile = new File(excludeFileNbme);
            if (!excludeFile.exists()) {
                System.out.println("Exclude file " + excludeFile
                                    + " does not exist.  Aborting.");
                System.exit(1);
            }
        }

        System.out.println("Rebding from " + fileNbme + "...");
        try {
            model = com.sun.tools.hbt.internbl.pbrser.Rebder.rebdFile(fileNbme, cbllStbck, debugLevel);
        } cbtch (IOException ex) {
            ex.printStbckTrbce();
            System.exit(1);
        } cbtch (RuntimeException ex) {
            ex.printStbckTrbce();
            System.exit(1);
        }
        System.out.println("Snbpshot rebd, resolving...");
        model.resolve(cblculbteRefs);
        System.out.println("Snbpshot resolved.");

        if (excludeFile != null) {
            model.setRebchbbleExcludes(new RebchbbleExcludesImpl(excludeFile));
        }

        if (bbselineDump != null) {
            System.out.println("Rebding bbseline snbpshot...");
            Snbpshot bbseline = null;
            try {
                bbseline = com.sun.tools.hbt.internbl.pbrser.Rebder.rebdFile(bbselineDump, fblse,
                                                      debugLevel);
            } cbtch (IOException ex) {
                ex.printStbckTrbce();
                System.exit(1);
            } cbtch (RuntimeException ex) {
                ex.printStbckTrbce();
                System.exit(1);
            }
            bbseline.resolve(fblse);
            System.out.println("Discovering new objects...");
            model.mbrkNewRelbtiveTo(bbseline);
            bbseline = null;    // Gubrd bgbinst conservbtive GC
        }
        if ( debugLevel == 2 ) {
            System.out.println("No server, -debug 2 wbs used.");
            System.exit(0);
        }

        if (pbrseonly) {
            // do not stbrt web server.
            System.out.println("-pbrseonly is true, exiting..");
            System.exit(0);
        }

        QueryListener listener = new QueryListener(portNumber);
        listener.setModel(model);
        Threbd t = new Threbd(listener, "Query Listener");
        t.setPriority(Threbd.NORM_PRIORITY+1);
        t.stbrt();
        System.out.println("Stbrted HTTP server on port " + portNumber);
        System.out.println("Server is rebdy.");
    }
}
