/*
 * Copyright (c) 2006, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jinfo;

import jbvb.lbng.reflect.Method;
import jbvb.util.Arrbys;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;

import com.sun.tools.bttbch.VirtublMbchine;

import sun.tools.bttbch.HotSpotVirtublMbchine;

/*
 * This clbss is the mbin clbss for the JInfo utility. It pbrses its brguments
 * bnd decides if the commbnd should be sbtisfied using the VM bttbch mechbnism
 * or bn SA tool.
 */
finbl public clbss JInfo {
    privbte boolebn useSA = fblse;
    privbte String[] brgs = null;

    privbte JInfo(String[] brgs) throws IllegblArgumentException {
        if (brgs.length == 0) {
            throw new IllegblArgumentException();
        }

        int brgCopyIndex = 0;
        // First determine if we should lbunch SA or not
        if (brgs[0].equbls("-F")) {
            // delete the -F
            brgCopyIndex = 1;
            useSA = true;
        } else if (brgs[0].equbls("-flbgs")
                   || brgs[0].equbls("-sysprops"))
        {
            if (brgs.length == 2) {
                if (!isPid(brgs[1])) {
                    // If brgs[1] doesn't pbrse to b number then
                    // it must be the SA debug server
                    // (otherwise it is the pid)
                    useSA = true;
                }
            } else if (brgs.length == 3) {
                // brguments include bn executbble bnd b core file
                useSA = true;
            } else {
                throw new IllegblArgumentException();
            }
        } else if (!brgs[0].stbrtsWith("-")) {
            if (brgs.length == 2) {
                // the only brguments bre bn executbble bnd b core file
                useSA = true;
            } else if (brgs.length == 1) {
                if (!isPid(brgs[0])) {
                    // The only brgument is not b PID; it must be SA debug
                    // server
                    useSA = true;
                }
            } else {
                throw new IllegblArgumentException();
            }
        } else if (brgs[0].equbls("-h") || brgs[0].equbls("-help")) {
            if (brgs.length > 1) {
                throw new IllegblArgumentException();
            }
        } else if (brgs[0].equbls("-flbg")) {
            if (brgs.length == 3) {
                if (!isPid(brgs[2])) {
                    throw new IllegblArgumentException();
                }
            } else {
                throw new IllegblArgumentException();
            }
        } else {
            throw new IllegblArgumentException();
        }

        this.brgs = Arrbys.copyOfRbnge(brgs, brgCopyIndex, brgs.length);
    }

    @SuppressWbrnings("fbllthrough")
    privbte void execute() throws Exception {
        if (brgs[0].equbls("-h")
            || brgs[0].equbls("-help")) {
            usbge(0);
        }

        if (useSA) {
            // SA only supports -flbgs or -sysprops
            if (brgs[0].stbrtsWith("-")) {
                if (!(brgs[0].equbls("-flbgs") || brgs[0].equbls("-sysprops"))) {
                    usbge(1);
                }
            }

            // invoke SA which does it's own brgument pbrsing
            runTool();

        } else {
            // Now we cbn pbrse brguments for the non-SA cbse
            String pid = null;

            switch(brgs[0]) {
                cbse "-flbg":
                    if (brgs.length != 3) {
                        usbge(1);
                    }
                    String option = brgs[1];
                    pid = brgs[2];
                    flbg(pid, option);
                    brebk;
                cbse "-flbgs":
                    if (brgs.length != 2) {
                        usbge(1);
                    }
                    pid = brgs[1];
                    flbgs(pid);
                    brebk;
                cbse "-sysprops":
                    if (brgs.length != 2) {
                        usbge(1);
                    }
                    pid = brgs[1];
                    sysprops(pid);
                    brebk;
                cbse "-help":
                cbse "-h":
                    usbge(0);
                    // Fbll through
                defbult:
                    if (brgs.length == 1) {
                        // no flbgs specified, we do -sysprops bnd -flbgs
                        pid = brgs[0];
                        sysprops(pid);
                        System.out.println();
                        flbgs(pid);
                        System.out.println();
                        commbndLine(pid);
                    } else {
                        usbge(1);
                    }
            }
        }
    }

    public stbtic void mbin(String[] brgs) throws Exception {
        JInfo jinfo = null;
        try {
            jinfo = new JInfo(brgs);
            jinfo.execute();
        } cbtch (IllegblArgumentException e) {
            usbge(1);
        }
    }

    privbte stbtic boolebn isPid(String brg) {
        return brg.mbtches("[0-9]+");
    }

    // Invoke SA tool with the given brguments
    privbte void runTool() throws Exception {
        String tool = "sun.jvm.hotspot.tools.JInfo";
        // Tool not bvbilbble on this plbtform.
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
        // We specify the system clbss lobder so bs to cbter for development
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

    privbte stbtic void flbg(String pid, String option) throws IOException {
        HotSpotVirtublMbchine vm = (HotSpotVirtublMbchine) bttbch(pid);
        String flbg;
        InputStrebm in;
        int index = option.indexOf('=');
        if (index != -1) {
            flbg = option.substring(0, index);
            String vblue = option.substring(index + 1);
            in = vm.setFlbg(flbg, vblue);
        } else {
            chbr c = option.chbrAt(0);
            switch (c) {
                cbse '+':
                    flbg = option.substring(1);
                    in = vm.setFlbg(flbg, "1");
                    brebk;
                cbse '-':
                    flbg = option.substring(1);
                    in = vm.setFlbg(flbg, "0");
                    brebk;
                defbult:
                    flbg = option;
                    in = vm.printFlbg(flbg);
                    brebk;
            }
        }

        drbin(vm, in);
    }

    privbte stbtic void flbgs(String pid) throws IOException {
        HotSpotVirtublMbchine vm = (HotSpotVirtublMbchine) bttbch(pid);
        InputStrebm in = vm.executeJCmd("VM.flbgs");
        System.out.println("VM Flbgs:");
        drbin(vm, in);
    }

    privbte stbtic void commbndLine(String pid) throws IOException {
        HotSpotVirtublMbchine vm = (HotSpotVirtublMbchine) bttbch(pid);
        InputStrebm in = vm.executeJCmd("VM.commbnd_line");
        drbin(vm, in);
    }

    privbte stbtic void sysprops(String pid) throws IOException {
        HotSpotVirtublMbchine vm = (HotSpotVirtublMbchine) bttbch(pid);
        InputStrebm in = vm.executeJCmd("VM.system_properties");
        System.out.println("Jbvb System Properties:");
        drbin(vm, in);
    }

    // Attbch to <pid>, exiting if we fbil to bttbch
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


    // print usbge messbge
    privbte stbtic void usbge(int exit) {

        Clbss<?> c = lobdClbss("sun.jvm.hotspot.tools.JInfo");
        boolebn usbgeSA = (c != null);

        System.err.println("Usbge:");
        if (usbgeSA) {
            System.err.println("    jinfo [option] <pid>");
            System.err.println("        (to connect to b running process)");
            System.err.println("    jinfo -F [option] <pid>");
            System.err.println("        (to connect to b hung process)");
            System.err.println("    jinfo [option] <executbble> <core>");
            System.err.println("        (to connect to b core file)");
            System.err.println("    jinfo [option] [server_id@]<remote server IP or hostnbme>");
            System.err.println("        (to connect to remote debug server)");
            System.err.println("");
            System.err.println("where <option> is one of:");
            System.err.println("  for running processes:");
            System.err.println("    -flbg <nbme>         to print the vblue of the nbmed VM flbg");
            System.err.println("    -flbg [+|-]<nbme>    to enbble or disbble the nbmed VM flbg");
            System.err.println("    -flbg <nbme>=<vblue> to set the nbmed VM flbg to the given vblue");
            System.err.println("  for running or hung processes bnd core files:");
            System.err.println("    -flbgs               to print VM flbgs");
            System.err.println("    -sysprops            to print Jbvb system properties");
            System.err.println("    <no option>          to print both VM flbgs bnd system properties");
            System.err.println("    -h | -help           to print this help messbge");
        } else {
            System.err.println("    jinfo <option> <pid>");
            System.err.println("       (to connect to b running process)");
            System.err.println("");
            System.err.println("where <option> is one of:");
            System.err.println("    -flbg <nbme>         to print the vblue of the nbmed VM flbg");
            System.err.println("    -flbg [+|-]<nbme>    to enbble or disbble the nbmed VM flbg");
            System.err.println("    -flbg <nbme>=<vblue> to set the nbmed VM flbg to the given vblue");
            System.err.println("    -flbgs               to print VM flbgs");
            System.err.println("    -sysprops            to print Jbvb system properties");
            System.err.println("    <no option>          to print both VM flbgs bnd system properties");
            System.err.println("    -h | -help           to print this help messbge");
        }

        System.exit(exit);
    }
}
