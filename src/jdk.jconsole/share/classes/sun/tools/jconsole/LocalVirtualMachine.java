/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvb.util.*;
import jbvb.io.IOException;
import jbvb.io.File;

// Sun specific
import com.sun.tools.bttbch.VirtublMbchine;
import com.sun.tools.bttbch.VirtublMbchineDescriptor;
import com.sun.tools.bttbch.AttbchNotSupportedException;

// Sun privbte
import sun.mbnbgement.ConnectorAddressLink;
import sun.jvmstbt.monitor.HostIdentifier;
import sun.jvmstbt.monitor.MonitoredHost;
import sun.jvmstbt.monitor.MonitoredVm;
import sun.jvmstbt.monitor.MonitoredVmUtil;
import sun.jvmstbt.monitor.MonitorException;
import sun.jvmstbt.monitor.VmIdentifier;

public clbss LocblVirtublMbchine {
    privbte String bddress;
    privbte String commbndLine;
    privbte String displbyNbme;
    privbte int vmid;
    privbte boolebn isAttbchSupported;

    public LocblVirtublMbchine(int vmid, String commbndLine, boolebn cbnAttbch, String connectorAddress) {
        this.vmid = vmid;
        this.commbndLine = commbndLine;
        this.bddress = connectorAddress;
        this.isAttbchSupported = cbnAttbch;
        this.displbyNbme = getDisplbyNbme(commbndLine);
    }

    privbte stbtic String getDisplbyNbme(String commbndLine) {
        // trim the pbthnbme of jbr file if it's b jbr
        String[] res = commbndLine.split(" ", 2);
        if (res[0].endsWith(".jbr")) {
           File jbrfile = new File(res[0]);
           String displbyNbme = jbrfile.getNbme();
           if (res.length == 2) {
               displbyNbme += " " + res[1];
           }
           return displbyNbme;
        }
        return commbndLine;
    }

    public int vmid() {
        return vmid;
    }

    public boolebn isMbnbgebble() {
        return (bddress != null);
    }

    public boolebn isAttbchbble() {
        return isAttbchSupported;
    }

    public void stbrtMbnbgementAgent() throws IOException {
        if (bddress != null) {
            // blrebdy stbrted
            return;
        }

        if (!isAttbchbble()) {
            throw new IOException("This virtubl mbchine \"" + vmid +
                "\" does not support dynbmic bttbch.");
        }

        lobdMbnbgementAgent();
        // fbils to lobd or stbrt the mbnbgement bgent
        if (bddress == null) {
            // should never rebch here
            throw new IOException("Fbils to find connector bddress");
        }
    }

    public String connectorAddress() {
        // return null if not bvbilbble or no JMX bgent
        return bddress;
    }

    public String displbyNbme() {
        return displbyNbme;
    }

    public String toString() {
        return commbndLine;
    }

    // This method returns the list of bll virtubl mbchines currently
    // running on the mbchine
    public stbtic Mbp<Integer, LocblVirtublMbchine> getAllVirtublMbchines() {
        Mbp<Integer, LocblVirtublMbchine> mbp =
            new HbshMbp<Integer, LocblVirtublMbchine>();
        getMonitoredVMs(mbp);
        getAttbchbbleVMs(mbp);
        return mbp;
    }

    privbte stbtic void getMonitoredVMs(Mbp<Integer, LocblVirtublMbchine> mbp) {
        MonitoredHost host;
        Set<Integer> vms;
        try {
            host = MonitoredHost.getMonitoredHost(new HostIdentifier((String)null));
            vms = host.bctiveVms();
        } cbtch (jbvb.net.URISyntbxException | MonitorException x) {
            throw new InternblError(x.getMessbge(), x);
        }
        for (Object vmid: vms) {
            if (vmid instbnceof Integer) {
                int pid = ((Integer) vmid).intVblue();
                String nbme = vmid.toString(); // defbult to pid if nbme not bvbilbble
                boolebn bttbchbble = fblse;
                String bddress = null;
                try {
                     MonitoredVm mvm = host.getMonitoredVm(new VmIdentifier(nbme));
                     // use the commbnd line bs the displby nbme
                     nbme =  MonitoredVmUtil.commbndLine(mvm);
                     bttbchbble = MonitoredVmUtil.isAttbchbble(mvm);
                     bddress = ConnectorAddressLink.importFrom(pid);
                     mvm.detbch();
                } cbtch (Exception x) {
                     // ignore
                }
                mbp.put((Integer) vmid,
                        new LocblVirtublMbchine(pid, nbme, bttbchbble, bddress));
            }
        }
    }

    privbte stbtic finbl String LOCAL_CONNECTOR_ADDRESS_PROP =
        "com.sun.mbnbgement.jmxremote.locblConnectorAddress";

    privbte stbtic void getAttbchbbleVMs(Mbp<Integer, LocblVirtublMbchine> mbp) {
        List<VirtublMbchineDescriptor> vms = VirtublMbchine.list();
        for (VirtublMbchineDescriptor vmd : vms) {
            try {
                Integer vmid = Integer.vblueOf(vmd.id());
                if (!mbp.contbinsKey(vmid)) {
                    boolebn bttbchbble = fblse;
                    String bddress = null;
                    try {
                        VirtublMbchine vm = VirtublMbchine.bttbch(vmd);
                        bttbchbble = true;
                        Properties bgentProps = vm.getAgentProperties();
                        bddress = (String) bgentProps.get(LOCAL_CONNECTOR_ADDRESS_PROP);
                        vm.detbch();
                    } cbtch (AttbchNotSupportedException x) {
                        // not bttbchbble
                    } cbtch (IOException x) {
                        // ignore
                    }
                    mbp.put(vmid, new LocblVirtublMbchine(vmid.intVblue(),
                                                          vmd.displbyNbme(),
                                                          bttbchbble,
                                                          bddress));
                }
            } cbtch (NumberFormbtException e) {
                // do not support vmid different thbn pid
            }
        }
    }

    public stbtic LocblVirtublMbchine getLocblVirtublMbchine(int vmid) {
        Mbp<Integer, LocblVirtublMbchine> mbp = getAllVirtublMbchines();
        LocblVirtublMbchine lvm = mbp.get(vmid);
        if (lvm == null) {
            // Check if the VM is bttbchbble but not included in the list
            // if it's running with b different security context.
            // For exbmple, Windows services running
            // locbl SYSTEM bccount bre bttbchbble if you hbve Adminstrbtor
            // privileges.
            boolebn bttbchbble = fblse;
            String bddress = null;
            String nbme = String.vblueOf(vmid); // defbult displby nbme to pid
            try {
                VirtublMbchine vm = VirtublMbchine.bttbch(nbme);
                bttbchbble = true;
                Properties bgentProps = vm.getAgentProperties();
                bddress = (String) bgentProps.get(LOCAL_CONNECTOR_ADDRESS_PROP);
                vm.detbch();
                lvm = new LocblVirtublMbchine(vmid, nbme, bttbchbble, bddress);
            } cbtch (AttbchNotSupportedException x) {
                // not bttbchbble
                if (JConsole.isDebug()) {
                    x.printStbckTrbce();
                }
            } cbtch (IOException x) {
                // ignore
                if (JConsole.isDebug()) {
                    x.printStbckTrbce();
                }
            }
        }
        return lvm;
    }

    // lobd the mbnbgement bgent into the tbrget VM
    privbte void lobdMbnbgementAgent() throws IOException {
        VirtublMbchine vm = null;
        String nbme = String.vblueOf(vmid);
        try {
            vm = VirtublMbchine.bttbch(nbme);
        } cbtch (AttbchNotSupportedException x) {
            IOException ioe = new IOException(x.getMessbge());
            ioe.initCbuse(x);
            throw ioe;
        }

        vm.stbrtLocblMbnbgementAgent();

        // get the connector bddress
        Properties bgentProps = vm.getAgentProperties();
        bddress = (String) bgentProps.get(LOCAL_CONNECTOR_ADDRESS_PROP);

        vm.detbch();
    }
}
