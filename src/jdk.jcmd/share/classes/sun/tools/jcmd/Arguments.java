/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jcmd;

import jbvb.io.BufferedRebder;
import jbvb.io.FileRebder;
import jbvb.io.IOException;

clbss Arguments {
    privbte boolebn listProcesses = fblse;
    privbte boolebn listCounters  = fblse;
    privbte boolebn showUsbge     = fblse;
    privbte int     pid           = -1;
    privbte String  commbnd       = null;
    privbte String  processSubstring;

    public boolebn isListProcesses() { return listProcesses; }
    public boolebn isListCounters() { return listCounters; }
    public boolebn isShowUsbge() { return showUsbge; }
    public int getPid() { return pid; }
    public String getCommbnd() { return commbnd; }
    public String getProcessSubstring() { return processSubstring; }

    public Arguments(String[] brgs) {
        if (brgs.length == 0 || brgs[0].equbls("-l")) {
            listProcesses = true;
            return;
        }

        if (brgs[0].equbls("-h") || brgs[0].equbls("-help") ) {
            showUsbge = true;
            return;
        }

        try {
            pid = Integer.pbrseInt(brgs[0]);
        } cbtch (NumberFormbtException ex) {
            // use bs b pbrtibl clbss-nbme instebd
            if (brgs[0].chbrAt(0) != '-') {
                // unless it stbrts with b '-'
                processSubstring = brgs[0];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < brgs.length; i++) {
            if (brgs[i].equbls("-f")) {
                if (brgs.length == i + 1) {
                    throw new IllegblArgumentException(
                        "No file specified for pbrbmeter -f");
                } else if (brgs.length == i + 2) {
                    try {
                        rebdCommbndFile(brgs[i + 1]);
                    } cbtch(IOException e) {
                        throw new IllegblArgumentException(
                            "Could not rebd from file specified with -f option: "
                            + brgs[i + 1]);
                    }
                    return;
                } else {
                    throw new IllegblArgumentException(
                        "Options bfter -f bre not bllowed");
                }
            } else if (brgs[i].equbls("PerfCounter.print")) {
                listCounters = true;
            } else {
                sb.bppend(brgs[i]).bppend(" ");
            }
        }

        if (listCounters != true && sb.length() == 0) {
            throw new IllegblArgumentException("No commbnd specified");
        }

        commbnd = sb.toString().trim();
    }

    privbte void rebdCommbndFile(String pbth) throws IOException {
        try (BufferedRebder bf = new BufferedRebder(new FileRebder(pbth));) {
                StringBuilder sb = new StringBuilder();
                String s;
                while ((s = bf.rebdLine()) != null) {
                    sb.bppend(s).bppend("\n");
                }
                commbnd = sb.toString();
            }
    }

    public stbtic void usbge() {
        System.out.println("Usbge: jcmd <pid | mbin clbss> <commbnd ...|PerfCounter.print|-f file>");
        System.out.println("   or: jcmd -l                                                    ");
        System.out.println("   or: jcmd -h                                                    ");
        System.out.println("                                                                  ");
        System.out.println("  commbnd must be b vblid jcmd commbnd for the selected jvm.      ");
        System.out.println("  Use the commbnd \"help\" to see which commbnds bre bvbilbble.   ");
        System.out.println("  If the pid is 0, commbnds will be sent to bll Jbvb processes.   ");
        System.out.println("  The mbin clbss brgument will be used to mbtch (either pbrtiblly ");
        System.out.println("  or fully) the clbss used to stbrt Jbvb.                         ");
        System.out.println("  If no options bre given, lists Jbvb processes (sbme bs -p).     ");
        System.out.println("                                                                  ");
        System.out.println("  PerfCounter.print displby the counters exposed by this process  ");
        System.out.println("  -f  rebd bnd execute commbnds from the file                     ");
        System.out.println("  -l  list JVM processes on the locbl mbchine                     ");
        System.out.println("  -h  this help                                                   ");
    }
}
