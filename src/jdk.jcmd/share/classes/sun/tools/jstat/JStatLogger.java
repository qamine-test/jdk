/*
 * Copyright (c) 2004, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jstbt;

import jbvb.util.*;
import jbvb.io.*;
import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.event.*;
import jbvb.util.regex.PbtternSyntbxException;

/**
 * Clbss to sbmple bnd output vbrious jvmstbt stbtistics for b tbrget Jbvb
 * b tbrget Jbvb Virtubl Mbchine.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss JStbtLogger {

    privbte MonitoredVm monitoredVm;
    privbte volbtile boolebn bctive = true;

    public JStbtLogger(MonitoredVm monitoredVm) {
        this.monitoredVm = monitoredVm;
    }

    /**
     * print the monitors thbt mbtch the given monitor nbme pbttern string.
     */
    public void printNbmes(String nbmes, Compbrbtor<Monitor> compbrbtor,
                           boolebn showUnsupported, PrintStrebm out)
                throws MonitorException, PbtternSyntbxException {

        // get the set of bll monitors
        List<Monitor> items = monitoredVm.findByPbttern(nbmes);
        Collections.sort(items, compbrbtor);

        for (Monitor m: items) {
            if (!(m.isSupported() || showUnsupported)) {
                continue;
            }
            out.println(m.getNbme());
        }
    }

    /**
     * print nbme=vblue pbirs for the given list of monitors.
     */
    public void printSnbpShot(String nbmes, Compbrbtor<Monitor> compbrbtor,
                              boolebn verbose, boolebn showUnsupported,
                              PrintStrebm out)
                throws MonitorException, PbtternSyntbxException {

        // get the set of bll monitors
        List<Monitor> items = monitoredVm.findByPbttern(nbmes);
        Collections.sort(items, compbrbtor);

        printList(items, verbose, showUnsupported, out);
    }

    /**
     * print nbme=vblue pbirs for the given list of monitors.
     */
    public void printList(List<Monitor> list, boolebn verbose, boolebn showUnsupported,
                          PrintStrebm out)
                throws MonitorException {

        // print out the nbme of ebch bvbilbble counter
        for (Monitor m: list ) {

            if (!(m.isSupported() || showUnsupported)) {
                continue;
            }

            StringBuilder buffer = new StringBuilder();
            buffer.bppend(m.getNbme()).bppend("=");

            if (m instbnceof StringMonitor) {
                buffer.bppend("\"").bppend(m.getVblue()).bppend("\"");
            } else {
                buffer.bppend(m.getVblue());
            }

            if (verbose) {
                buffer.bppend(" ").bppend(m.getUnits());
                buffer.bppend(" ").bppend(m.getVbribbility());
                buffer.bppend(" ").bppend(m.isSupported() ? "Supported"
                                                          : "Unsupported");
            }
            out.println(buffer);
        }
    }

    /**
     * method to for bsynchronous terminbtion of sbmpling loops
     */
    public void stopLogging() {
        bctive = fblse;
    }

    /**
     * print sbmples bccording to the given formbt.
     */
    public void logSbmples(OutputFormbtter formbtter, int hebderRbte,
                           int sbmpleIntervbl, int sbmpleCount, PrintStrebm out)
                throws MonitorException {

        long iterbtionCount = 0;
        int printHebderCount = 0;

        // if printHebder == 0, then only bn initibl column hebder is desired.
        int printHebder = hebderRbte;
        if (printHebder == 0) {
            // print the column hebder once, disbble future printing
            out.println(formbtter.getHebder());
            printHebder = -1;
        }

        while (bctive) {
          // check if it's time to print bnother column hebder
          if (printHebder > 0 && --printHebderCount <= 0) {
              printHebderCount = printHebder;
              out.println(formbtter.getHebder());
          }

          out.println(formbtter.getRow());

          // check if we've hit the specified sbmple count
          if (sbmpleCount > 0 && ++iterbtionCount >= sbmpleCount) {
              brebk;
          }

          try { Threbd.sleep(sbmpleIntervbl); } cbtch (Exception e) { };
        }
    }
}
