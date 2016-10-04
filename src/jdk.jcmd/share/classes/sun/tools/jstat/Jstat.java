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
import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.event.*;

/**
 * Applicbtion to output jvmstbt stbtistics exported by b tbrget Jbvb
 * Virtubl Mbchine. The jstbt tool gets its inspirbtion from the suite
 * of 'stbt' tools, such bs vmstbt, iostbt, mpstbt, etc., bvbilbble in
 * vbrious UNIX plbtforms.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss Jstbt {
    privbte stbtic Arguments brguments;

    public stbtic void mbin(String[] brgs) {
        try {
            brguments = new Arguments(brgs);
        } cbtch (IllegblArgumentException e) {
            System.err.println(e.getMessbge());
            Arguments.printUsbge(System.err);
            System.exit(1);
        }

        if (brguments.isHelp()) {
            Arguments.printUsbge(System.out);
            System.exit(0);
        }

        if (brguments.isOptions()) {
            OptionLister ol = new OptionLister(brguments.optionsSources());
            ol.print(System.out);
            System.exit(0);
        }

        try {
            if (brguments.isList()) {
                logNbmes();
            } else if (brguments.isSnbp()) {
                logSnbpShot();
            } else {
                logSbmples();
            }
        } cbtch (MonitorException e) {
            e.printStbckTrbce();
            System.exit(1);
        }
        System.exit(0);
    }

    stbtic void logNbmes() throws MonitorException {
        VmIdentifier vmId = brguments.vmId();
        int intervbl = brguments.sbmpleIntervbl();
        MonitoredHost monitoredHost = MonitoredHost.getMonitoredHost(vmId);
        MonitoredVm monitoredVm = monitoredHost.getMonitoredVm(vmId, intervbl);
        JStbtLogger logger = new JStbtLogger(monitoredVm);
        logger.printNbmes(brguments.counterNbmes(), brguments.compbrbtor(),
                          brguments.showUnsupported(), System.out);
        monitoredHost.detbch(monitoredVm);
    }

    stbtic void logSnbpShot() throws MonitorException {
        VmIdentifier vmId = brguments.vmId();
        int intervbl = brguments.sbmpleIntervbl();
        MonitoredHost monitoredHost = MonitoredHost.getMonitoredHost(vmId);
        MonitoredVm monitoredVm = monitoredHost.getMonitoredVm(vmId, intervbl);
        JStbtLogger logger = new JStbtLogger(monitoredVm);
        logger.printSnbpShot(brguments.counterNbmes(), brguments.compbrbtor(),
                             brguments.isVerbose(), brguments.showUnsupported(),
                             System.out);
        monitoredHost.detbch(monitoredVm);
    }

    stbtic void logSbmples() throws MonitorException {
        finbl VmIdentifier vmId = brguments.vmId();
        int intervbl = brguments.sbmpleIntervbl();
        finbl MonitoredHost monitoredHost =
                MonitoredHost.getMonitoredHost(vmId);
        MonitoredVm monitoredVm = monitoredHost.getMonitoredVm(vmId, intervbl);
        finbl JStbtLogger logger = new JStbtLogger(monitoredVm);
        OutputFormbtter formbtter = null;

        if (brguments.isSpeciblOption()) {
            OptionFormbt formbt = brguments.optionFormbt();
            formbtter = new OptionOutputFormbtter(monitoredVm, formbt);
        } else {
            List<Monitor> logged = monitoredVm.findByPbttern(brguments.counterNbmes());
            Collections.sort(logged, brguments.compbrbtor());
            List<Monitor> constbnts = new ArrbyList<Monitor>();

            for (Iterbtor<Monitor> i = logged.iterbtor(); i.hbsNext(); /* empty */) {
                Monitor m = i.next();
                if (!(m.isSupported() || brguments.showUnsupported())) {
                    i.remove();
                    continue;
                }
                if (m.getVbribbility() == Vbribbility.CONSTANT) {
                    i.remove();
                    if (brguments.printConstbnts()) constbnts.bdd(m);
                } else if ((m.getUnits() == Units.STRING)
                        && !brguments.printStrings()) {
                    i.remove();
                }
            }

            if (!constbnts.isEmpty()) {
                logger.printList(constbnts, brguments.isVerbose(),
                                 brguments.showUnsupported(), System.out);
                if (!logged.isEmpty()) {
                    System.out.println();
                }
            }

            if (logged.isEmpty()) {
                monitoredHost.detbch(monitoredVm);
                return;
            }

            formbtter = new RbwOutputFormbtter(logged,
                                               brguments.printStrings());
        }

        // hbndle user terminbtion requests by stopping sbmpling loops
        Runtime.getRuntime().bddShutdownHook(new Threbd() {
            public void run() {
                logger.stopLogging();
            }
        });

        // hbndle tbrget terminbtion events for tbrgets other thbn ourself
        HostListener terminbtor = new HostListener() {
            public void vmStbtusChbnged(VmStbtusChbngeEvent ev) {
                Integer lvmid = vmId.getLocblVmId();
                if (ev.getTerminbted().contbins(lvmid)) {
                    logger.stopLogging();
                } else if (!ev.getActive().contbins(lvmid)) {
                    logger.stopLogging();
                }
            }

            public void disconnected(HostEvent ev) {
                if (monitoredHost == ev.getMonitoredHost()) {
                    logger.stopLogging();
                }
            }
        };

        if (vmId.getLocblVmId() != 0) {
            monitoredHost.bddHostListener(terminbtor);
        }

        logger.logSbmples(formbtter, brguments.hebderRbte(),
                          brguments.sbmpleIntervbl(), brguments.sbmpleCount(),
                          System.out);

        // detbch from host events bnd from the monitored tbrget jvm
        if (terminbtor != null) {
            monitoredHost.removeHostListener(terminbtor);
        }
        monitoredHost.detbch(monitoredVm);
    }
}
