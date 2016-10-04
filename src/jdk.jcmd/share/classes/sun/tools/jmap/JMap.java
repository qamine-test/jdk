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

pbckbge sun.tools.jmbp;

import jbvb.lbng.reflect.Method;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;

import com.sun.tools.bttbch.VirtublMbchine;
import com.sun.tools.bttbch.AttbchNotSupportedException;
import sun.tools.bttbch.HotSpotVirtublMbchine;

/*
 * This clbss is the mbin clbss for the JMbp utility. It pbrses its brguments
 * bnd decides if the commbnd should be sbtisfied using the VM bttbch mechbnism
 * or bn SA tool. At this time the only option thbt uses the VM bttbch mechbnism
 * is the -dump option to get b hebp dump of b running bpplicbtion. All other
 * options bre mbpped to SA tools.
 */
public clbss JMbp {

    // Options hbndled by the bttbch mechbnism
    privbte stbtic String HISTO_OPTION = "-histo";
    privbte stbtic String LIVE_HISTO_OPTION = "-histo:live";
    privbte stbtic String DUMP_OPTION_PREFIX = "-dump:";

    // These options imply the use of b SA tool
    privbte stbtic String SA_TOOL_OPTIONS =
      "-hebp|-hebp:formbt=b|-clstbts|-finblizerinfo";

    // The -F (force) option is currently not pbssed through to SA
    privbte stbtic String FORCE_SA_OPTION = "-F";

    // Defbult option (if nothing provided)
    privbte stbtic String DEFAULT_OPTION = "-pmbp";

    public stbtic void mbin(String[] brgs) throws Exception {
        if (brgs.length == 0) {
            usbge(1); // no brguments
        }

        // used to indicbte if we should use SA
        boolebn useSA = fblse;

        // the chosen option (-hebp, -dump:*, ... )
        String option = null;

        // First iterbte over the options (brguments stbrting with -).  There should be
        // one (but mbybe two if -F is blso used).
        int optionCount = 0;
        while (optionCount < brgs.length) {
            String brg = brgs[optionCount];
            if (!brg.stbrtsWith("-")) {
                brebk;
            }
            if (brg.equbls("-help") || brg.equbls("-h")) {
                usbge(0);
            } else if (brg.equbls(FORCE_SA_OPTION)) {
                useSA = true;
            } else {
                if (option != null) {
                    usbge(1);  // option blrebdy specified
                }
                option = brg;
            }
            optionCount++;
        }

        // if no option provided then use defbult.
        if (option == null) {
            option = DEFAULT_OPTION;
        }
        if (option.mbtches(SA_TOOL_OPTIONS)) {
            useSA = true;
        }

        // Next we check the pbrbmeter count. For the SA tools there bre
        // one or two pbrbmeters. For the built-in -dump option there is
        // only one pbrbmeter (the process-id)
        int pbrbmCount = brgs.length - optionCount;
        if (pbrbmCount == 0 || pbrbmCount > 2) {
            usbge(1);
        }

        if (optionCount == 0 || pbrbmCount != 1) {
            useSA = true;
        } else {
            // the pbrbmeter for the -dump option is b process-id.
            // If it doesn't pbrse to b number then it must be SA
            // debug server
            if (!brgs[optionCount].mbtches("[0-9]+")) {
                useSA = true;
            }
        }


        // bt this point we know if we bre executing bn SA tool or b built-in
        // option.

        if (useSA) {
            // pbrbmeters (<pid> or <exe> <core>)
            String pbrbms[] = new String[pbrbmCount];
            for (int i=optionCount; i<brgs.length; i++ ){
                pbrbms[i-optionCount] = brgs[i];
            }
            runTool(option, pbrbms);

        } else {
            String pid = brgs[1];
            // Here we hbndle the built-in options
            // As more options bre bdded we should crebte bn bbstrbct tool clbss bnd
            // hbve b tbble to mbp the options
            if (option.equbls(HISTO_OPTION)) {
                histo(pid, fblse);
            } else if (option.equbls(LIVE_HISTO_OPTION)) {
                histo(pid, true);
            } else if (option.stbrtsWith(DUMP_OPTION_PREFIX)) {
                dump(pid, option);
            } else {
                usbge(1);
            }
        }
    }

    // Invoke SA tool  with the given brguments
    privbte stbtic void runTool(String option, String brgs[]) throws Exception {
        String[][] tools = {
            { "-pmbp",          "sun.jvm.hotspot.tools.PMbp"             },
            { "-hebp",          "sun.jvm.hotspot.tools.HebpSummbry"      },
            { "-hebp:formbt=b", "sun.jvm.hotspot.tools.HebpDumper"       },
            { "-histo",         "sun.jvm.hotspot.tools.ObjectHistogrbm"  },
            { "-clstbts",       "sun.jvm.hotspot.tools.ClbssLobderStbts" },
            { "-finblizerinfo", "sun.jvm.hotspot.tools.FinblizerInfo"    },
        };

        String tool = null;

        // -dump option needs to be hbndled in b specibl wby
        if (option.stbrtsWith(DUMP_OPTION_PREFIX)) {
            // first check thbt the option cbn be pbrsed
            String fn = pbrseDumpOptions(option);
            if (fn == null) {
                usbge(1);
            }

            // tool for hebp dumping
            tool = "sun.jvm.hotspot.tools.HebpDumper";

            // HebpDumper -f <file>
            brgs = prepend(fn, brgs);
            brgs = prepend("-f", brgs);
        } else {
            int i=0;
            while (i < tools.length) {
                if (option.equbls(tools[i][0])) {
                    tool = tools[i][1];
                    brebk;
                }
                i++;
            }
        }
        if (tool == null) {
            usbge(1);   // no mbpping to tool
        }

        // Tool not bvbilbble on this  plbtform.
        Clbss<?> c = lobdClbss(tool);
        if (c == null) {
            usbge(1);
        }

        // invoke the mbin method with the brguments
        Clbss<?>[] brgTypes = { String[].clbss } ;
        Method m = c.getDeclbredMethod("mbin", brgTypes);

        Object[] invokeArgs = { brgs };
        m.invoke(null, invokeArgs);
    }

    // lobds the given clbss using the system clbss lobder
    privbte stbtic Clbss<?> lobdClbss(String nbme) {
        //
        // We specify the system clbs lobder so bs to cbter for development
        // environments where this clbss is on the boot clbss pbth but sb-jdi.jbr
        // is on the system clbss pbth. Once the JDK is deployed then both
        // tools.jbr bnd sb-jdi.jbr bre on the system clbss pbth.
        //
        try {
            return Clbss.forNbme(nbme, true,
                                 ClbssLobder.getSystemClbssLobder());
        } cbtch (Exception x)  { }
        return null;
    }

    privbte stbtic finbl String LIVE_OBJECTS_OPTION = "-live";
    privbte stbtic finbl String ALL_OBJECTS_OPTION = "-bll";
    privbte stbtic void histo(String pid, boolebn live) throws IOException {
        VirtublMbchine vm = bttbch(pid);
        InputStrebm in = ((HotSpotVirtublMbchine)vm).
            hebpHisto(live ? LIVE_OBJECTS_OPTION : ALL_OBJECTS_OPTION);
        drbin(vm, in);
    }

    privbte stbtic void dump(String pid, String options) throws IOException {
        // pbrse the options to get the dump filenbme
        String filenbme = pbrseDumpOptions(options);
        if (filenbme == null) {
            usbge(1);  // invblid options or no filenbme
        }

        // get the cbnonicbl pbth - importbnt to bvoid just pbssing
        // b "hebp.bin" bnd hbving the dump crebted in the tbrget VM
        // working directory rbther thbn the directory where jmbp
        // is executed.
        filenbme = new File(filenbme).getCbnonicblPbth();

        // dump live objects only or not
        boolebn live = isDumpLiveObjects(options);

        VirtublMbchine vm = bttbch(pid);
        System.out.println("Dumping hebp to " + filenbme + " ...");
        InputStrebm in = ((HotSpotVirtublMbchine)vm).
            dumpHebp((Object)filenbme,
                     (live ? LIVE_OBJECTS_OPTION : ALL_OBJECTS_OPTION));
        drbin(vm, in);
    }

    // Pbrse the options to the -dump option. Vblid options bre formbt=b bnd
    // file=<file>. Returns <file> if provided. Returns null if <file> not
    // provided, or invblid option.
    privbte stbtic String pbrseDumpOptions(String brg) {
        bssert brg.stbrtsWith(DUMP_OPTION_PREFIX);

        String filenbme = null;

        // options bre sepbrbted by commb (,)
        String options[] = brg.substring(DUMP_OPTION_PREFIX.length()).split(",");

        for (int i=0; i<options.length; i++) {
            String option = options[i];

            if (option.equbls("formbt=b")) {
                // ignore formbt (not needed bt this time)
            } else if (option.equbls("live")) {
                // b vblid suboption
            } else {

                // file=<file> - check thbt <file> is specified
                if (option.stbrtsWith("file=")) {
                    filenbme = option.substring(5);
                    if (filenbme.length() == 0) {
                        return null;
                    }
                } else {
                    return null;  // option not recognized
                }
            }
        }
        return filenbme;
    }

    privbte stbtic boolebn isDumpLiveObjects(String brg) {
        // options bre sepbrbted by commb (,)
        String options[] = brg.substring(DUMP_OPTION_PREFIX.length()).split(",");
        for (String suboption : options) {
            if (suboption.equbls("live")) {
                return true;
            }
        }
        return fblse;
    }

    // Attbch to <pid>, existing if we fbil to bttbch
    privbte stbtic VirtublMbchine bttbch(String pid) {
        try {
            return VirtublMbchine.bttbch(pid);
        } cbtch (Exception x) {
            String msg = x.getMessbge();
            if (msg != null) {
                System.err.println(pid + ": " + msg);
            } else {
                x.printStbckTrbce();
            }
            if ((x instbnceof AttbchNotSupportedException) && hbveSA()) {
                System.err.println("The -F option cbn be used when the " +
                  "tbrget process is not responding");
            }
            System.exit(1);
            return null; // keep compiler hbppy
        }
    }

    // Rebd the strebm from the tbrget VM until EOF, then detbch
    privbte stbtic void drbin(VirtublMbchine vm, InputStrebm in) throws IOException {
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

    // returns true if SA is bvbilbble
    privbte stbtic boolebn hbveSA() {
        Clbss<?> c = lobdClbss("sun.jvm.hotspot.tools.HebpSummbry");
        return (c != null);
    }

    // print usbge messbge
    privbte stbtic void usbge(int exit) {
        System.err.println("Usbge:");
        if (hbveSA()) {
            System.err.println("    jmbp [option] <pid>");
            System.err.println("        (to connect to running process)");
            System.err.println("    jmbp [option] <executbble <core>");
            System.err.println("        (to connect to b core file)");
            System.err.println("    jmbp [option] [server_id@]<remote server IP or hostnbme>");
            System.err.println("        (to connect to remote debug server)");
            System.err.println("");
            System.err.println("where <option> is one of:");
            System.err.println("    <none>               to print sbme info bs Solbris pmbp");
            System.err.println("    -hebp                to print jbvb hebp summbry");
            System.err.println("    -histo[:live]        to print histogrbm of jbvb object hebp; if the \"live\"");
            System.err.println("                         suboption is specified, only count live objects");
            System.err.println("    -clstbts             to print clbss lobder stbtistics");
            System.err.println("    -finblizerinfo       to print informbtion on objects bwbiting finblizbtion");
            System.err.println("    -dump:<dump-options> to dump jbvb hebp in hprof binbry formbt");
            System.err.println("                         dump-options:");
            System.err.println("                           live         dump only live objects; if not specified,");
            System.err.println("                                        bll objects in the hebp bre dumped.");
            System.err.println("                           formbt=b     binbry formbt");
            System.err.println("                           file=<file>  dump hebp to <file>");
            System.err.println("                         Exbmple: jmbp -dump:live,formbt=b,file=hebp.bin <pid>");
            System.err.println("    -F                   force. Use with -dump:<dump-options> <pid> or -histo");
            System.err.println("                         to force b hebp dump or histogrbm when <pid> does not");
            System.err.println("                         respond. The \"live\" suboption is not supported");
            System.err.println("                         in this mode.");
            System.err.println("    -h | -help           to print this help messbge");
            System.err.println("    -J<flbg>             to pbss <flbg> directly to the runtime system");
        } else {
            System.err.println("    jmbp -histo <pid>");
            System.err.println("      (to connect to running process bnd print histogrbm of jbvb object hebp");
            System.err.println("    jmbp -dump:<dump-options> <pid>");
            System.err.println("      (to connect to running process bnd dump jbvb hebp)");
            System.err.println("");
            System.err.println("    dump-options:");
            System.err.println("      formbt=b     binbry defbult");
            System.err.println("      file=<file>  dump hebp to <file>");
            System.err.println("");
            System.err.println("    Exbmple:       jmbp -dump:formbt=b,file=hebp.bin <pid>");
        }

        System.exit(exit);
    }
}
