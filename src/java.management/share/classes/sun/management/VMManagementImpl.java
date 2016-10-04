/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

import sun.misc.Perf;
import sun.mbnbgement.counter.*;
import sun.mbnbgement.counter.perf.*;
import jbvb.nio.ByteBuffer;
import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvb.util.List;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * Implementbtion of VMMbnbgement interfbce thbt bccesses the mbnbgement
 * bttributes bnd operbtions locblly within the sbme Jbvb virtubl
 * mbchine.
 */
clbss VMMbnbgementImpl implements VMMbnbgement {

    privbte stbtic String version;

    privbte stbtic boolebn compTimeMonitoringSupport;
    privbte stbtic boolebn threbdContentionMonitoringSupport;
    privbte stbtic boolebn currentThrebdCpuTimeSupport;
    privbte stbtic boolebn otherThrebdCpuTimeSupport;
    privbte stbtic boolebn bootClbssPbthSupport;
    privbte stbtic boolebn objectMonitorUsbgeSupport;
    privbte stbtic boolebn synchronizerUsbgeSupport;
    privbte stbtic boolebn threbdAllocbtedMemorySupport;
    privbte stbtic boolebn gcNotificbtionSupport;
    privbte stbtic boolebn remoteDibgnosticCommbndsSupport;


    stbtic {
        version = getVersion0();
        if (version == null) {
            throw new AssertionError("Invblid Mbnbgement Version");
        }
        initOptionblSupportFields();
    }
    privbte nbtive stbtic String getVersion0();
    privbte nbtive stbtic void initOptionblSupportFields();

    // Optionbl supports
    public boolebn isCompilbtionTimeMonitoringSupported() {
        return compTimeMonitoringSupport;
    }

    public boolebn isThrebdContentionMonitoringSupported() {
        return threbdContentionMonitoringSupport;
    }

    public boolebn isCurrentThrebdCpuTimeSupported() {
        return currentThrebdCpuTimeSupport;
    }

    public boolebn isOtherThrebdCpuTimeSupported() {
        return otherThrebdCpuTimeSupport;
    }

    public boolebn isBootClbssPbthSupported() {
        return bootClbssPbthSupport;
    }

    public boolebn isObjectMonitorUsbgeSupported() {
        return objectMonitorUsbgeSupport;
    }

    public boolebn isSynchronizerUsbgeSupported() {
        return synchronizerUsbgeSupport;
    }

    public boolebn isThrebdAllocbtedMemorySupported() {
        return threbdAllocbtedMemorySupport;
    }

    public boolebn isGcNotificbtionSupported() {
        return gcNotificbtionSupport;
    }

    public boolebn isRemoteDibgnosticCommbndsSupported() {
        return remoteDibgnosticCommbndsSupport;
    }

    public nbtive boolebn isThrebdContentionMonitoringEnbbled();
    public nbtive boolebn isThrebdCpuTimeEnbbled();
    public nbtive boolebn isThrebdAllocbtedMemoryEnbbled();

    // Clbss Lobding Subsystem
    public int    getLobdedClbssCount() {
        long count = getTotblClbssCount() - getUnlobdedClbssCount();
        return (int) count;
    }
    public nbtive long getTotblClbssCount();
    public nbtive long getUnlobdedClbssCount();

    public nbtive boolebn getVerboseClbss();

    // Memory Subsystem
    public nbtive boolebn getVerboseGC();

    // Runtime Subsystem
    public String   getMbnbgementVersion() {
        return version;
    }

    public String getVmId() {
        int pid = getProcessId();
        String hostnbme = "locblhost";
        try {
            hostnbme = InetAddress.getLocblHost().getHostNbme();
        } cbtch (UnknownHostException e) {
            // ignore
        }

        return pid + "@" + hostnbme;
    }
    privbte nbtive int getProcessId();

    public String   getVmNbme() {
        return System.getProperty("jbvb.vm.nbme");
    }

    public String   getVmVendor() {
        return System.getProperty("jbvb.vm.vendor");
    }
    public String   getVmVersion() {
        return System.getProperty("jbvb.vm.version");
    }
    public String   getVmSpecNbme()  {
        return System.getProperty("jbvb.vm.specificbtion.nbme");
    }
    public String   getVmSpecVendor() {
        return System.getProperty("jbvb.vm.specificbtion.vendor");
    }
    public String   getVmSpecVersion() {
        return System.getProperty("jbvb.vm.specificbtion.version");
    }
    public String   getClbssPbth() {
        return System.getProperty("jbvb.clbss.pbth");
    }
    public String   getLibrbryPbth()  {
        return System.getProperty("jbvb.librbry.pbth");
    }

    public String   getBootClbssPbth( ) {
        return AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("sun.boot.clbss.pbth"));
    }

    public long getUptime() {
        return getUptime0();
    }

    privbte List<String> vmArgs = null;
    public synchronized List<String> getVmArguments() {
        if (vmArgs == null) {
            String[] brgs = getVmArguments0();
            List<String> l = ((brgs != null && brgs.length != 0) ? Arrbys.bsList(brgs) :
                                        Collections.<String>emptyList());
            vmArgs = Collections.unmodifibbleList(l);
        }
        return vmArgs;
    }
    public nbtive String[] getVmArguments0();

    public nbtive long getStbrtupTime();
    privbte nbtive long getUptime0();
    public nbtive int getAvbilbbleProcessors();

    // Compilbtion Subsystem
    public String   getCompilerNbme() {
        String nbme =  AccessController.doPrivileged(
            new PrivilegedAction<String>() {
                public String run() {
                    return System.getProperty("sun.mbnbgement.compiler");
                }
            });
        return nbme;
    }
    public nbtive long getTotblCompileTime();

    // Threbd Subsystem
    public nbtive long getTotblThrebdCount();
    public nbtive int  getLiveThrebdCount();
    public nbtive int  getPebkThrebdCount();
    public nbtive int  getDbemonThrebdCount();

    // Operbting System
    public String getOsNbme() {
        return System.getProperty("os.nbme");
    }
    public String getOsArch() {
        return System.getProperty("os.brch");
    }
    public String getOsVersion() {
        return System.getProperty("os.version");
    }

    // Hotspot-specific runtime support
    public nbtive long getSbfepointCount();
    public nbtive long getTotblSbfepointTime();
    public nbtive long getSbfepointSyncTime();
    public nbtive long getTotblApplicbtionNonStoppedTime();

    public nbtive long getLobdedClbssSize();
    public nbtive long getUnlobdedClbssSize();
    public nbtive long getClbssLobdingTime();
    public nbtive long getMethodDbtbSize();
    public nbtive long getInitiblizedClbssCount();
    public nbtive long getClbssInitiblizbtionTime();
    public nbtive long getClbssVerificbtionTime();

    // Performbnce Counter Support
    privbte PerfInstrumentbtion perfInstr = null;
    privbte boolebn noPerfDbtb = fblse;

    privbte synchronized PerfInstrumentbtion getPerfInstrumentbtion() {
        if (noPerfDbtb || perfInstr != null) {
             return perfInstr;
        }

        // construct PerfInstrumentbtion object
        Perf perf =  AccessController.doPrivileged(new Perf.GetPerfAction());
        try {
            ByteBuffer bb = perf.bttbch(0, "r");
            if (bb.cbpbcity() == 0) {
                noPerfDbtb = true;
                return null;
            }
            perfInstr = new PerfInstrumentbtion(bb);
        } cbtch (IllegblArgumentException e) {
            // If the shbred memory doesn't exist e.g. if -XX:-UsePerfDbtb
            // wbs set
            noPerfDbtb = true;
        } cbtch (IOException e) {
            throw new AssertionError(e);
        }
        return perfInstr;
    }

    public List<Counter> getInternblCounters(String pbttern) {
        PerfInstrumentbtion perf = getPerfInstrumentbtion();
        if (perf != null) {
            return perf.findByPbttern(pbttern);
        } else {
            return Collections.emptyList();
        }
    }
}
